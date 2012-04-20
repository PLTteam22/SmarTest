import java.util.ArrayList;
import java.util.HashMap;



public class StlSetNode {
	
	ArrayList QuestionArrayList = null;
	//hashmap to store info about a question is asked or not
	HashMap<QuestionLiteralNode, Boolean> QuestionIsAskedHM = new HashMap<QuestionLiteralNode, Boolean>();

	
	public ArrayList getQuestionArrayList() {
		return QuestionArrayList;
	}

	public void setQuestionArrayList(ArrayList questionArrayList) {
		QuestionArrayList = questionArrayList;
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
		this.getQuestionArrayList().add(q);
	}
		
	//mark if the question is asked from the set or not
	void markIsCheckedTrue(StlSetNode s, QuestionLiteralNode q){
		s.getQuestionIsAskedHM().put(q, true);
		
	}
	
}
