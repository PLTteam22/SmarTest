package smartest;

import java.util.ArrayList;

/**
 * A wrapper for an arbitrarily-long list of AnswerChoice's. It is used in
 * target code generation to allow for nesting question literals within other
 * expressions.
 */
public class AnswerChoicesList {
    /** Internal representation of the answer choices. */
    private ArrayList<AnswerChoice> choices = new ArrayList<AnswerChoice>();

    /**
     * Instantiates a new answer choices list with an arbitrary number of
     * AnswerChoice parameters.
     * 
     * @param answers
     *            a comma-separated list of AnswerChoice's
     */
    public AnswerChoicesList(AnswerChoice... answers) {
        for (int i = 0; i < answers.length; i++) {
            this.getChoices().add(answers[i]);
        }
    }

    /**
     * Gets the choices.
     * 
     * @return the choices
     */
    public ArrayList<AnswerChoice> getChoices() {
        return choices;
    }

    /**
     * Sets the choices.
     * 
     * @param choices
     *            the choices to set
     */
    public void setChoices(ArrayList<AnswerChoice> choices) {
        this.choices = choices;
    }
}
