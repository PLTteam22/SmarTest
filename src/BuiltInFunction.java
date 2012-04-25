
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;

public class BuiltInFunction {

	// load method
	StlSetNode load(String connection_string, String userName, String password, String category){
		
		Connection conn = null;
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(connection_string,userName,password);
			System.out.println("Connected to the database");
			conn.close();
			System.out.println("Disconnected from database");
		} catch (Exception e) {
			e.printStackTrace();
		}

        PreparedStatement pst = null;
        ResultSet rs = null;
        StlSetNode set = new StlSetNode();
        
        try {
	       	pst = conn.prepareStatement("SELECT * FROM questions WHERE category = 1");
			pst.setString(1, category);
			rs = pst.executeQuery();
			
			while (rs.next())
			{
				String text = rs.getString("text");
				String answers = rs.getString("answersTexts");
				String points = rs.getString("answersPoints");
				
				StringTokenizer textsSt = new StringTokenizer(answers, "|||");
				StringTokenizer pointsSt = new StringTokenizer(points, ",");
				AnswerChoicesList answersList = new AnswerChoicesList();
				
				while (textsSt.hasMoreTokens())
				{
					String answerText = textsSt.nextToken();
					int point = Integer.parseInt(pointsSt.nextToken());
					AnswerChoice answerChoice = new AnswerChoice(answerText, point);
					answersList.getChoices().add(answerChoice);
				}
				
				Question q = new Question(text, category, answersList);
				set.addQuestion(q);
			}
			
			

	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return set;
	}
	
	
	void save(String connection_string, String userName, String password, String category, StlSetNode set)
	{
		
		Connection conn = null;
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(connection_string,userName,password);
			System.out.println("Connected to the database");
			conn.close();
			System.out.println("Disconnected from database");
		} catch (Exception e) {
			e.printStackTrace();
		}

		String query = "INSERT INTO questions VALUES (NULL, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = null;

    	
        for (int i = 0; i < set.getQuestionArrayList().size(); i++)
        {
        	
        	Question q  = set.getQuestionArrayList().get(i);
        	pst.setString(1, q);
        	
        	
        	
        }
		
	}

	// askQuestion method
	int askQuestion(StlSetNode s) {
		boolean isFull = false;
		int points = 0;
		boolean isDone = false;
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
					if (i == item) {
						// if all questions have been asked once pick randomly
						if (isFull) {
							
							// print question
							
							// wait to read the input from user
							BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
							try {
								String inputAnswer = reader.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println("Invalid Input");
							}
							
							// set the points obtained to points variable
							//points = Integer.parseInt(s.getQuestionArrayList().get(i).getAnswerChoices().getSvalue());
							
							isDone = true;// break;
						}
						// check if the question is in the hashmap or not and
						// add to the hashmap if not exists
						else if (!(s.getQuestionIsAskedHM().containsKey(s
								.getQuestionArrayList().get(i)))) {
							s.getQuestionIsAskedHM().put(
									(QuestionLiteralNode) s
											.getQuestionArrayList().get(i),
									true);
							
							// print question
							
							// wait to read the input from user
							BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
							try {
								String inputAnswer = reader.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println("Invalid Input");
							}
							
							// set the points obtained to points variable
							isDone = true;// break;
						}
					}
				}
			}
		}
		return points;
	}

	// readLine
	String readline() {
		return null;
	}

	// print
	void print(String s) {
		System.out.println(s);
	}

	// printVar for int
	void printVar(int x) {
		System.out.println(x);
	}

	// printVar for char
	void printVar(char x) {
		System.out.println(x);
	}

	// printVar for float
	void printVar(float x) {
		System.out.println(x);
	}

	// printVar for set and questions
	void printVar(Object x) {
		System.out.println(x);
	}

	// length of a set
	int len(StlSetNode s) {
		return s.getQuestionArrayList().size();
	}

}
