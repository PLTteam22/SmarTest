package smartest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * The Class BuiltInFunction contains target source implementations of STL
 * built-in functions.
 * 
 * @author Parth and Aiman
 */
public class BuiltInFunction {
    /**
     * The built-in load method. This method connects to the specified database
     * with the specified credentials, and loads the questions of the specified
     * category into a STL Set
     * 
     * Example:
     * 
     * <pre>
     * load(&quot;jdbc:mysql://localhost:8889/smartest&quot;, &quot;root&quot;, &quot;root&quot;, &quot;math&quot;)
     * </pre>
     * 
     * @param connection_string
     *            the connection string for the database
     * @param userName
     *            the user name to connect as
     * @param password
     *            the password
     * @param category
     *            the question category
     * @return the created STL Set with the matching questions
     */
    public static StlSetNode load(String connection_string, String userName,
            String password, String category) {
        Connection conn = null;
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(connection_string, userName,
                    password);
            if (Parser.DEBUG)
                System.out.println("Connected to the database");
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pst = null;
        ResultSet rs = null;
        StlSetNode set = new StlSetNode();
        try {
            pst = conn
                    .prepareStatement("SELECT * FROM questions WHERE category = ?");
            pst.setString(1, category);
            rs = pst.executeQuery();
            while (rs.next()) {
                String text = rs.getString("text");
                String answers = rs.getString("answersTexts");
                String points = rs.getString("answersPoints");
                StringTokenizer textsSt = new StringTokenizer(answers, "|||");
                StringTokenizer pointsSt = new StringTokenizer(points, ",");
                AnswerChoicesList answersList = new AnswerChoicesList();
                while (textsSt.hasMoreTokens()) {
                    String answerText = textsSt.nextToken();
                    int point = Integer.parseInt(pointsSt.nextToken());
                    AnswerChoice answerChoice = new AnswerChoice(answerText,
                            point);
                    answersList.getChoices().add(answerChoice);
                }
                Question q = new Question(category, text, answersList);
                q.setQuestionDBID(rs.getInt("ID"));
                set.addQuestion(q);
            }
            conn.close();
            if (Parser.DEBUG)
                System.out.println("Disconnected from database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

    /**
     * The built-in save method. This method connects to the specified database
     * with the specified credentials, and adds any questions from the specified
     * set to the repository if they are not already present.
     * 
     * Example:
     * 
     * <pre>
     * save(&quot;jdbc:mysql://localhost:8889/smartest&quot;, &quot;root&quot;, &quot;root&quot;, set_of_questions);
     * </pre>
     * 
     * @param connection_string
     *            the connection string
     * @param userName
     *            the user name to connect as
     * @param password
     *            the password
     * @param set
     *            the set to save
     */
    public static void save(String connection_string, String userName,
            String password, StlSetNode set) {
        Connection conn = null;
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(connection_string, userName,
                    password);
            if (Parser.DEBUG)
                System.out.println("Connected to the database");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String query = "INSERT INTO questions VALUES (NULL, ?, ?, ?, ?)";
        String selectQuery = "SELECT COUNT(*) FROM questions WHERE ID = ?";
        PreparedStatement pst;
        PreparedStatement pstSelect;
        try {
            pst = conn.prepareStatement(query);
            pstSelect = conn.prepareStatement(selectQuery);
            for (int i = 0; i < set.getQuestionArrayList().size(); i++) {
                Question q = set.getQuestionArrayList().get(i);
                // Verify the question does not already exist in database
                pstSelect.setInt(1, q.getQuestionDBID());
                ResultSet rs = pstSelect.executeQuery();
                rs.next();
                if (rs.getInt(1) > 0)
                    continue; // pass if question already exists
                pst.setString(1, q.getQuestionCategory());
                pst.setString(2, q.getQuestionText());
                String answersTexts = "";
                String answersPoints = "";
                for (int j = 0; j < q.getAnswers().size(); j++) {
                    AnswerChoice answer = q.getAnswers().get(j);
                    answersTexts += answer.getText();
                    answersPoints += answer.getPoints();
                    if (j < q.getAnswers().size() - 1) {
                        answersTexts += "|||";
                        answersPoints += ",";
                    }
                }
                pst.setString(3, answersTexts);
                pst.setString(4, answersPoints);
                pst.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * The built-in askQuestion method. This method displays for the test taker
     * a random un-asked question from the specified set, and determines the
     * score for their answer. If all questions have been asked before, a random
     * one is chosen.
     * 
     * @param s
     *            the set from which to select a question to display
     * @return the points for the answer selected by the test-taker
     */
    public static int askQuestion(StlSetNode s) {
        boolean isFull = false;
        int points = 0;
        boolean isDone = false;
        String inputAnswer = null;
        while (!isDone) {
            int size = s.getQuestionArrayList().size();
            // generate random number for picking question from set randomly
            int item = new Random().nextInt(size);
            if (size > 0) {
                // check if all questions have been asked once
                if (size == s.getQuestionIsAskedHM().size()) {
                    isFull = true;
                }
                for (int i = 0; i < s.getQuestionArrayList().size(); i++) {
                    ArrayList<AnswerChoice> answerChoiceArrayList = s
                            .getQuestionArrayList().get(i).getAnswers();
                    if (i == item) {
                        // if all questions have been asked once pick randomly
                        if (isFull) {
                            // print question
                            System.out.println(s.getQuestionArrayList().get(i)
                                    .getQuestionText()
                                    + "\n");
                            for (int k = 0; k < answerChoiceArrayList.size(); k++) {
                                System.out.println((k + 1)
                                        + ") "
                                        + answerChoiceArrayList.get(k)
                                                .getText() + "\n");
                            }
                            boolean validInput = false;
                            int intInputAnswer = 0;
                            while (!validInput) {
                                // wait to read the input from user
                                System.out.print("Select Answer > ");
                                BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(System.in));
                                try {
                                    inputAnswer = reader.readLine();
                                    try {
                                        intInputAnswer = Integer
                                                .parseInt(inputAnswer);
                                    } catch (NumberFormatException ne) {
                                        System.out
                                                .println("Enter a number between 1 and "
                                                        + answerChoiceArrayList
                                                                .size());
                                        continue;
                                    }
                                    if (intInputAnswer < 1
                                            || intInputAnswer > answerChoiceArrayList
                                                    .size())
                                        System.out
                                                .println("Enter a number between 1 and "
                                                        + answerChoiceArrayList
                                                                .size());
                                    else
                                        validInput = true;
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                    System.out.println("Invalid Input");
                                }
                            }
                            points = answerChoiceArrayList.get(
                                    intInputAnswer - 1).getPoints();
                            isDone = true;// break;
                        }
                        // check if the question is in the hashmap or not and
                        // add to the hashmap if not exists
                        else if (!(s.getQuestionIsAskedHM().containsKey(s
                                .getQuestionArrayList().get(i)))) {
                            s.getQuestionIsAskedHM().put(
                                    (Question) s.getQuestionArrayList().get(i),
                                    true);
                            // print question
                            System.out.println(s.getQuestionArrayList().get(i)
                                    .getQuestionText()
                                    + "\n");
                            for (int k = 0; k < answerChoiceArrayList.size(); k++) {
                                System.out.println((k + 1)
                                        + ") "
                                        + answerChoiceArrayList.get(k)
                                                .getText() + "\n");
                            }
                            boolean validInput = false;
                            int intInputAnswer = 0;
                            while (!validInput) {
                                // wait to read the input from user
                                System.out.print("Select Answer > ");
                                BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(System.in));
                                try {
                                    inputAnswer = reader.readLine();
                                    try {
                                        intInputAnswer = Integer
                                                .parseInt(inputAnswer);
                                    } catch (NumberFormatException ne) {
                                        System.out
                                                .println("Enter a number between 1 and "
                                                        + answerChoiceArrayList
                                                                .size());
                                        continue;
                                    }
                                    if (intInputAnswer < 1
                                            || intInputAnswer > answerChoiceArrayList
                                                    .size())
                                        System.out
                                                .println("Enter a number between 1 and "
                                                        + answerChoiceArrayList
                                                                .size());
                                    else
                                        validInput = true;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    System.out.println("Invalid Input");
                                }
                            }
                            points = answerChoiceArrayList.get(
                                    intInputAnswer - 1).getPoints();
                            isDone = true;
                        }
                    }
                }
            }
        }
        return points;
    }

    /**
     * The built-in readLine function. This method reads a single line from
     * standard in.
     * 
     * @return the string read
     */
    public static String readLine() {
        String input = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Invalid Input");
        }
        return input;
    }

    /**
     * The built-in function to print an STL string to standard out.
     * 
     * @param s
     *            a string
     */
    public static void print(String s) {
        System.out.print(s);
    }

    /**
     * The built-in function to print an STL int to standard out.
     * 
     * @param x
     *            an int
     */
    public static void printInteger(int x) {
        System.out.print(x);
    }

    /**
     * The built-in function to print an STL char to standard out.
     * 
     * @param x
     *            the char
     */
    public static void printChar(char x) {
        System.out.print(x);
    }

    /**
     * The built-in function to print an STL float to standard out.
     * 
     * @param x
     *            a double
     */
    public static void printFloat(double x) {
        System.out.print(x);
    }

    /**
     * The built-in function to print an STL boolean to standard out.
     * 
     * @param x
     *            the
     */
    public static void printBoolean(Boolean x) {
        System.out.print(x);
    }

    /**
     * The built-in function to calculate the number of questions in the given
     * set.
     * 
     * @param s
     *            the set
     * @return the number of questions in the set
     */
    public static int len(StlSetNode s) {
        return s.getQuestionArrayList().size();
    }
}