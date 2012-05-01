import java.util.ArrayList;


public class QuestionList {

	private ArrayList<Question> qlist = new ArrayList<Question>();
	
	
	public QuestionList(Question ... list)
	{
		for (int i = 0; i < list.length; i++)	
		{
			this.getQuestionList().add(list[i]);
		}
	}


	/**
	 * @return the choices
	 */
	public ArrayList<AnswerChoice> getQuestionList() {
		return qList;
	}


	/**
	 * @param choices the choices to set
	 */
	public void setQuestionList(ArrayList<Question> list) {
		this.qList = list;
	}

	
}
