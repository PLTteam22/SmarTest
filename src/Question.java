import java.util.ArrayList;


public class Question {

	
	private int questionDBID;
	private String questionText;
	private String questionCategory;
	private AnswerChoicesList answersList;
	private ArrayList<AnswerChoice> answers;
	
	
	
	public Question(String questionCategory, String questionText, AnswerChoicesList answersList)
	{		
		this.questionText = questionText;
		this.questionCategory = questionCategory;
		this.setAnswersList(answersList);
		this.setAnswers(answersList.getChoices());	
	}


	/**
	 * @return the questionText
	 */
	public String getQuestionText() {
		
		
		return questionText;
	}


	/**
	 * @param questionText the questionText to set
	 */
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}


	/**
	 * @return the questionCategory
	 */
	public String getQuestionCategory() {
		return questionCategory;
	}


	/**
	 * @param questionCategory the questionCategory to set
	 */
	public void setQuestionCategory(String questionCategory) {
		this.questionCategory = questionCategory;
	}


	/**
	 * @return the answers
	 */
	public ArrayList<AnswerChoice> getAnswers() {
		return answers;
	}


	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(ArrayList<AnswerChoice> answers) {
		this.answers = answers;
	}


	/**
	 * @return the answersList
	 */
	public AnswerChoicesList getAnswersList() {
		return answersList;
	}


	/**
	 * @param answersList the answersList to set
	 */
	public void setAnswersList(AnswerChoicesList answersList) {
		this.answersList = answersList;
	}


	/**
	 * @return the questionDBID
	 */
	public int getQuestionDBID() {
		return questionDBID;
	}


	/**
	 * @param questionDBID the questionDBID to set
	 */
	public void setQuestionDBID(int questionDBID) {
		this.questionDBID = questionDBID;
	}
	
}
