package smartest;

/**
 * Represents a question literal node. It is defined according to the following
 * grammar production:
 * 
 * <pre>
 * question_literal : '$' expr1 ':' expr2 '[' answer_choices ']' '$'
 * </pre>
 * 
 * <br />
 * Example:
 * 
 * <pre>
 * $"Math-Easy":"What is  2 * 4 / 2?" [ "4":5, "6":0 ]$
 * </pre>
 * 
 * Semantics: expr1 and expr2 must be strings
 * 
 * @author Aiman
 */
public class QuestionLiteralNode extends ASTNode {
    private ASTNode questionCategory;
    private ASTNode questionText;
    private ASTNode answerChoices;

    /**
     * Instantiates a question literal node.
     * 
     * @param expr1
     *            the question category
     * @param expr2
     *            the question text
     * @param answerChoices
     *            a list of answer choices (AnswerChoicesNode)
     */
    public QuestionLiteralNode(ASTNode expr1, ASTNode expr2,
            ASTNode answerChoices, int yyline, int yycolumn) {
        super(yyline, yycolumn);
        this.addChild(expr1);
        this.addChild(expr2);
        this.addChild(answerChoices);
        this.questionCategory = expr1;
        this.questionText = expr2;
        this.answerChoices = answerChoices;
    }

    /**
     * Verifies the semantics of the question literal. Question category must be
     * a string Question text must be a string
     * 
     * @see ASTNode#checkSemantics()
     */
    @Override
    public void checkSemantics() throws Exception {
        questionCategory.checkSemantics();
        questionText.checkSemantics();
        answerChoices.checkSemantics();
        if (!questionCategory.getType().equalsIgnoreCase("string")) {
            throw new Exception("Line " + this.getYyline() + ":"
                    + this.getYycolumn() + " "
                    + " question category must be a string, found: "
                    + questionCategory.getType());
        }
        if (!questionText.getType().equalsIgnoreCase("string")) {
            throw new Exception("Line " + this.getYyline() + ":"
                    + this.getYycolumn() + " "
                    + " question text must be a string, found: "
                    + questionText.getType());
        }
        this.setType("question");
    }

    /**
     * Makes use of the Question object.
     * 
     * @see ASTNode#generateCode()
     */
    @Override
    public StringBuffer generateCode() {
        StringBuffer output = new StringBuffer();
        output.append("new Question(");
        output.append(this.questionCategory.generateCode());
        output.append(", ");
        output.append(this.questionText.generateCode());
        output.append(", ");
        output.append(this.answerChoices.generateCode());
        output.append(")");
        return output;
    }

    /**
     * @return the question category
     */
    public ASTNode getQuestionCategory() {
        return questionCategory;
    }

    /**
     * @param questionCategory
     *            the question category to set
     */
    public void setQuestionCategory(ASTNode questionCategory) {
        this.questionCategory = questionCategory;
    }

    /**
     * @return the question text
     */
    public ASTNode getQuestionText() {
        return questionText;
    }

    /**
     * @param questionText
     *            the question text to set
     */
    public void setQuestionText(ASTNode questionText) {
        this.questionText = questionText;
    }

    /**
     * @return the answer choices
     */
    public ASTNode getAnswerChoices() {
        return answerChoices;
    }

    /**
     * @param answerChoices
     *            the answer choices to set
     */
    public void setAnswerChoices(ASTNode answerChoices) {
        this.answerChoices = answerChoices;
    }
}
