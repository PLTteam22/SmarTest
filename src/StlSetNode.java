import java.util.ArrayList;
import java.util.HashMap;


/**
 * The Class StlSetNode.
 */
public class StlSetNode {
	
	/** The Question array list. */
	ArrayList<QuestionLiteralNode> QuestionArrayList = new ArrayList<QuestionLiteralNode>();
	
	
	//hashmap to store info about a question is asked or not
	/** The Question is asked hm. */
	HashMap<QuestionLiteralNode, Boolean> QuestionIsAskedHM = new HashMap<QuestionLiteralNode, Boolean>();

	
	/**
	 * Gets the question array list.
	 *
	 * @return the question array list
	 */
	public ArrayList<QuestionLiteralNode> getQuestionArrayList() {
		return QuestionArrayList;
	}

	/**
	 * Sets the question array list.
	 *
	 * @param questionArrayList the new question array list
	 */
	public void setQuestionArrayList(ArrayList questionArrayList) {
		QuestionArrayList = questionArrayList;
	}

	/**
	 * Gets the question is asked hash map
	 *
	 * @return the question is asked hash map
	 */
	public HashMap<QuestionLiteralNode, Boolean> getQuestionIsAskedHM() {
		return QuestionIsAskedHM;
	}

	/**
	 * Sets the question is asked hash map.
	 *
	 * @param questionIsAskedHM the question is asked hash map
	 */
	public void setQuestionIsAskedHM(
			HashMap<QuestionLiteralNode, Boolean> questionIsAskedHM) {
		QuestionIsAskedHM = questionIsAskedHM;
	}

	/**
	 * Adds the question.
	 * Add question literal node to set
	 * @param q the question literal node
	 */
	void addQuestion(QuestionLiteralNode q){
		this.getQuestionArrayList().add(q);
	}
		
	//mark if the question is asked from the set or not
	/**
	 * Mark is checked true.
	 *
	 * @param s the stlset node
	 * @param q the question literal node
	 */
	void markIsCheckedTrue(StlSetNode s, QuestionLiteralNode q){
		s.getQuestionIsAskedHM().put(q, true);
		
	}
	
}
