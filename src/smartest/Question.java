package smartest;

import java.util.ArrayList;

/**
 * The Class Question is a wrapper for the STL question type.
 * 
 * @author Parth
 */
public class Question {
    /** The question database id. */
    private int questionDBID;
    /** The question text. */
    private String questionText;
    /** The question category. */
    private String questionCategory;
    /** A wrapper for the answers choices list. */
    private AnswerChoicesList answersList;
    /** The answer choices list. */
    private ArrayList<AnswerChoice> answers;

    /**
     * Instantiates a new question.
     * 
     * @param questionCategory
     *            the question category
     * @param questionText
     *            the question text
     * @param answersList
     *            the answers choices list
     */
    public Question(String questionCategory, String questionText,
            AnswerChoicesList answersList) {
        this.questionText = questionText;
        this.questionCategory = questionCategory;
        this.setAnswersList(answersList);
        this.setAnswers(answersList.getChoices());
    }

    /**
     * Gets the question text.
     * 
     * @return the question text
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Sets the question text.
     * 
     * @param questionText
     *            the question text to set
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Gets the question category.
     * 
     * @return the question category
     */
    public String getQuestionCategory() {
        return questionCategory;
    }

    /**
     * Sets the question category.
     * 
     * @param questionCategory
     *            the question category to set
     */
    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory;
    }

    /**
     * Gets the answers choices list.
     * 
     * @return the answer choices
     */
    public ArrayList<AnswerChoice> getAnswers() {
        return answers;
    }

    /**
     * Sets the answer choices list.
     * 
     * @param answers
     *            the answer choices to set
     */
    public void setAnswers(ArrayList<AnswerChoice> answers) {
        this.answers = answers;
    }

    /**
     * Gets the answers list.
     * 
     * @return the answersList
     */
    public AnswerChoicesList getAnswersList() {
        return answersList;
    }

    /**
     * Sets the answers list.
     * 
     * @param answersList
     *            the answers list to set
     */
    public void setAnswersList(AnswerChoicesList answersList) {
        this.answersList = answersList;
    }

    /**
     * Gets the question database id.
     * 
     * @return the question database id
     */
    public int getQuestionDBID() {
        return questionDBID;
    }

    /**
     * Sets the question database id.
     * 
     * @param questionDBID
     *            the question database id to set
     */
    public void setQuestionDBID(int questionDBID) {
        this.questionDBID = questionDBID;
    }
}