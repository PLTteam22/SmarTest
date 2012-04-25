/*
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Random;


/**
 * The Class BuiltInFunction.
 * 
 * Contains a list of Built In Functions in STL
 * @author Parth
 */
public class BuiltInFunction {

	// load method
	/**
	 * Load.
	 *
	 * @param connection_string the connection_string
	 * @param category the category
	 * @return the stl set node
	 */
	StlSetNode load(String connection_string, String category) {

		return null;
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
	public static void printVar(int x) {
		System.out.println(x);
	}

	// printVar for char
	/**
	 * Prints the char variable
	 *
	 * @param x the char
	 */
	public static void printVar(char x) {
		System.out.println(x);
	}

	// printVar for float
	/**
	 * Prints the float variable
	 *
	 * @param x the 
	 */
	public static void printVar(float x) {
		System.out.println(x);
	}

	// printVar for set and questions
	/**
	 * Prints the set and question variable
	 *
	 * @param x the object of type set/question
	 */
	public static void printVar(Object x) {
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
