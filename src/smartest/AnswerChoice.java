package smartest;


public class AnswerChoice {

	private String text;
	private int points;
	
	public AnswerChoice(String text, int points)
	{
		this.text = text;
		this.points = points;		
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

}
