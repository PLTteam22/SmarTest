import java.util.ArrayList;


public class AnswerChoicesList {

	private ArrayList<AnswerChoice> choices = new ArrayList<AnswerChoice>();
	
	
	public AnswerChoicesList(AnswerChoice ... answers)
	{
		for (int i = 0; i < answers.length; i++)	
		{
			this.getChoices().add(answers[i]);
		}
	}


	/**
	 * @return the choices
	 */
	public ArrayList<AnswerChoice> getChoices() {
		return choices;
	}


	/**
	 * @param choices the choices to set
	 */
	public void setChoices(ArrayList<AnswerChoice> choices) {
		this.choices = choices;
	}

	
}
