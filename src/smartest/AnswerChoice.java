package smartest;

/**
 * AnswerChoice represents a single option for a test question, including
 * associated text and points.
 * 
 * @author Daniel Walker
 */
public class AnswerChoice {

    /** The text of this answer */
    private String text;

    /** The points for this answer */
    private int points;

    /**
     * Instantiates a new answer choice.
     * 
     * @param text
     *            the text
     * @param points
     *            the points
     */
    public AnswerChoice(String text, int points) {
        this.text = text;
        this.points = points;
    }

    /**
     * Gets the text of this answer choice
     * 
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text of this answer choice
     * 
     * @param text
     *            the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the points associated with this answer
     * 
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the points associated with this answer
     * 
     * @param points
     *            the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

}
