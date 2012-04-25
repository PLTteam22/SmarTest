/*
 * 
 */
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
 * The Class BuiltInFunction.
 * 
 * Contains a list of Built In Functions in STL
 * @author Parth
 */
public class BuiltInFunction {

	// load method
	public static StlSetNode load(String connection_string, String userName, String password, String category)
	{
		
		Connection conn = null;
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(connection_string,userName,password);
			if (Parser.DEBUG)  System.out.println("Connected to the database");
		} catch (Exception e) {
			e.printStackTrace();
		}

        PreparedStatement pst = null;
        ResultSet rs = null;
        StlSetNode set = new StlSetNode();
        
        try {
	       	pst = conn.prepareStatement("SELECT * FROM questions WHERE category = ?");
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
				q.setQuestionDBID(rs.getInt("ID"));
				set.addQuestion(q);
			}
			
			
			conn.close();
			if (Parser.DEBUG) System.out.println("Disconnected from database");

	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return set;
	}
	
	
	public static void save(String connection_string, String userName, String password, StlSetNode set)
	{
		
		Connection conn = null;
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(connection_string,userName,password);
			if (Parser.DEBUG)  System.out.println("Connected to the database");
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
	    	
	        for (int i = 0; i < set.getQuestionArrayList().size(); i++)
	        {
	        	Question q  = set.getQuestionArrayList().get(i);

	        	// Verify the question does not already exist in database
	        	pstSelect.setInt(1, q.getQuestionDBID());
	        	ResultSet rs = pstSelect.executeQuery();
	        	rs.next();
	        	if (rs.getInt(1) > 0) continue; // pass if question already exists
	        	
	        	pst.setString(1, q.getQuestionCategory());
	        	pst.setString(2, q.getQuestionText());
	        	
	        	String answersTexts = "";
	        	String answersPoints = "";
	        	for (int j = 0; j < q.getAnswers().size(); j++)
	        	{
	        		AnswerChoice answer = q.getAnswers().get(j);
	        		answersTexts += answer.getText();
	        		answersPoints += answer.getPoints();
	        		
	        		if (j < q.getAnswers().size() - 1)
	        		{
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
	
	
	// askQuestion method
	/**
	 * Ask question.
	 *
	 * @param s the StlSetNode
	 * @return the int
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
					ArrayList<AnswerChoice> answerChoiceArrayList = s.getQuestionArrayList().get(i)
							.getAnswers();
					
					if (i == item) {
						// if all questions have been asked once pick randomly
						if (isFull) {

							// print question
							System.out.println(s.getQuestionArrayList().get(i).getQuestionText()+"\n");
							
							for(int k=0;k<answerChoiceArrayList.size();k++){
								System.out.println(answerChoiceArrayList.get(k).getText()+
										":"+answerChoiceArrayList.get(k).getPoints()+"\n");
							}
							
							// wait to read the input from user
							System.out.print("Select Answer > ");
							BufferedReader reader = new BufferedReader(
									new InputStreamReader(System.in));
							try {
								inputAnswer = reader.readLine();
							} catch (IOException e) {
								e.printStackTrace();
								System.out.println("Invalid Input");
							}

							// set the points obtained to points variable
							for(int j=0 ; j<answerChoiceArrayList.size() ; j++){
								if(inputAnswer.equals(answerChoiceArrayList.get(j).getText())){
									points = answerChoiceArrayList.get(j).getPoints();
								}
								
							}
							isDone = true;// break;
						}
						// check if the question is in the hashmap or not and
						// add to the hashmap if not exists
						else if (!(s.getQuestionIsAskedHM().containsKey(s
								.getQuestionArrayList().get(i)))) {
							s.getQuestionIsAskedHM().put(
									(Question) s
											.getQuestionArrayList().get(i),
									true);

							// print question
							System.out.println(s.getQuestionArrayList().get(i).getQuestionText()+"\n");
							
							for(int k=0;k<answerChoiceArrayList.size();k++){
								System.out.println(answerChoiceArrayList.get(k).getText()+
										":"+answerChoiceArrayList.get(k).getPoints()+"\n");
							}
							
							// wait to read the input from user
							System.out.print("Select Answer > ");
							BufferedReader reader = new BufferedReader(
									new InputStreamReader(System.in));
							try {
								inputAnswer = reader.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println("Invalid Input");
							}

							// set the points obtained to points variable
							for(int j=0 ; j<answerChoiceArrayList.size() ; j++){
								if(inputAnswer.equals(answerChoiceArrayList.get(j).getText())){
									points = answerChoiceArrayList.get(j).getPoints();
								}
								
							}
							
							isDone = true;// break;
						}
					}
				}
			}
		}
		return points;
	}

	// readLine
	/**
	 * Readline input
	 *
	 * @return the string
	 */
	public static String readline() {
		return null;
	}

	// print
	/**
	 * Prints the string
	 *
	 * @param s the string
	 */
	public static void print(String s) {
		System.out.println(s);
	}

	// printVar for int
	/**
	 * Prints the int variable
	 *
	 * @param x the int
	 */
	public static void printInteger(int x) {
		System.out.println(x);
	}

	// printVar for char
	/**
	 * Prints the char variable
	 *
	 * @param x the char
	 */
	public static void printChar(char x) {
		System.out.println(x);
	}

	// printVar for float
	/**
	 * Prints the float variable
	 *
	 * @param x the 
	 */
	public static void printFloat(double x) {
		System.out.println(x);
	}


	// length of a set
	/**
	 * Length of set
	 *
	 * @param s the StlSetNode
	 * @return the length of the set as int
	 */
	public static int len(StlSetNode s) {
		return s.getQuestionArrayList().size();
	}

}
