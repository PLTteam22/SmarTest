package smartest;

import java.util.ArrayList;


public class QuestionList {

	private ArrayList<Question> qList = new ArrayList<Question>();
	
	
	public QuestionList(Question ... list)
	{
		for (int i = 0; i < list.length; i++)	
		{
			qList.add(list[i]);
		}
	}


	public ArrayList<Question> getQuestionList()
        {
		return qList;
	}


	public void setQuestionList(ArrayList<Question> list)
        {
		this.qList = list;
	}
        public ArrayList<Question> getQuestions()
        {
                return qList;
        }
	
}
