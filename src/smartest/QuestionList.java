package smartest;

import java.util.ArrayList;

/**
 * The Class QuestionList is a wrapper for a list of one or more questions.
 * 
 * @author Daniel Walker
 */
public class QuestionList {
    /** The question list. */
    private ArrayList<Question> qList = new ArrayList<Question>();

    /**
     * Instantiates a new question list.
     * 
     * @param list
     *            the comma-separated list of questions
     */
    public QuestionList(Question... list) {
        for (int i = 0; i < list.length; i++) {
            qList.add(list[i]);
        }
    }

    /**
     * Gets the question list.
     * 
     * @return the question list
     */
    public ArrayList<Question> getQuestionList() {
        return qList;
    }

    /**
     * Sets the question list.
     * 
     * @param list
     *            the new question list
     */
    public void setQuestionList(ArrayList<Question> list) {
        this.qList = list;
    }
}
