package smartest;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Class StlSetNode is a wrapper for the STL type set.
 * 
 * @author Parth
 */
public class StlSetNode {
    /** The Question array list. */
    ArrayList<Question> QuestionArrayList;
    /** hashmap to store info about whether a question is asked or not. */
    HashMap<Question, Boolean> QuestionIsAskedHM = new HashMap<Question, Boolean>();

    /**
     * Instantiates a new STL set with no questions.
     */
    public StlSetNode() {
        QuestionArrayList = new ArrayList<Question>();
    }

    /**
     * Instantiates a new stl set node.
     * 
     * @param ql
     *            the ql
     */
    public StlSetNode(QuestionList ql) {
        QuestionArrayList = ql.getQuestionList();
    }

    /**
     * Gets the question array list.
     * 
     * @return the question array list
     */
    public ArrayList<Question> getQuestionArrayList() {
        return QuestionArrayList;
    }

    /**
     * Sets the question array list.
     * 
     * @param questionArrayList
     *            the new question array list
     */
    public void setQuestionArrayList(ArrayList<Question> questionArrayList) {
        QuestionArrayList = questionArrayList;
    }

    /**
     * Gets the hash map with info on whether or not a question has been asked.
     * 
     * @return the question is asked hash map
     */
    public HashMap<Question, Boolean> getQuestionIsAskedHM() {
        return QuestionIsAskedHM;
    }

    /**
     * Sets the hash map that has info on whether or not a question has been
     * asked.
     * 
     * @param questionIsAskedHM
     *            the question is asked hash map
     */
    public void setQuestionIsAskedHM(
            HashMap<Question, Boolean> questionIsAskedHM) {
        QuestionIsAskedHM = questionIsAskedHM;
    }

    /**
     * Adds a question to this set.
     * 
     * @param q
     *            the question literal node
     * @return the stl set node
     */
    public StlSetNode addQuestion(Question q) {
        this.getQuestionArrayList().add(q);
        return this;
    }

    /**
     * Set the flag for the given question having been asked to true
     * 
     * @param s
     *            the set
     * @param q
     *            the question
     */
    public void markIsCheckedTrue(StlSetNode s, Question q) {
        s.getQuestionIsAskedHM().put(q, true);
    }
}
