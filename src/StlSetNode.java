import java.util.HashMap;
import java.util.Set;


public class StlSetNode {
	
	Set setNode = null;
	//hashmap to store info about a question is asked or not
	HashMap<QuestionLiteralNode, Boolean> QuestionIsAskedHM = new HashMap<QuestionLiteralNode, Boolean>();

	public Set getSetNode() {
		return setNode;
	}

	public void setSetNode(Set setNode) {
		this.setNode = setNode;
	}

	
	public HashMap<QuestionLiteralNode, Boolean> getQuestionIsAskedHM() {
		return QuestionIsAskedHM;
	}

	public void setQuestionIsAskedHM(
			HashMap<QuestionLiteralNode, Boolean> questionIsAskedHM) {
		QuestionIsAskedHM = questionIsAskedHM;
	}

		//add question literal node to set
		void addQuestion(QuestionLiteralNode q){
			this.getSetNode().add(q);
		}
		
	//mark if the question is asked from the set or not
	void markIsCheckedTrue(StlSetNode s, QuestionLiteralNode q){
		s.getQuestionIsAskedHM().put(q, true);
		
	}
	
}
