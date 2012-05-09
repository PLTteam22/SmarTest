package smartest;

/**
 * The Class QuestionListNode represents a comma-separated list of questions in
 * STL source, such as in a set literal.
 * 
 * @author Daniel Walker
 */
public class QuestionListNode extends ASTNode {
    /**
     * Instantiates a new question list node.
     * 
     * @param yyline
     *            the corresponding line in the input file
     * @param yycolumn
     *            the corresponding column in the input file
     */
    public QuestionListNode(int yyline, int yycolumn) {
        super(yyline, yycolumn);
    }

    /**
     * Ensures all elements are of type question.
     * 
     * @see ASTNode#checkSemantics()
     */
    public void checkSemantics() throws Exception {
        for (ASTNode question : this.getChildren()) {
            question.checkSemantics();
            if (!"question".equalsIgnoreCase(question.getType())) {
                throw new Exception("Line " + this.getYyline() + ":"
                        + this.getYycolumn() + " "
                        + " expecting question, found: " + question.getType());
            }
        }
    }

    /**
     * Makes use of the QuestionList object to aggregate questions
     * 
     * @see ASTNode#generateCode()
     */
    public StringBuffer generateCode() {
        StringBuffer output = new StringBuffer();
        output.append("new QuestionList(");
        boolean first = true;
        for (ASTNode question : this.getChildren()) {
            if (!first) {
                output.append(", ");
            } else {
                first = false;
            }
            output.append(question.generateCode());
        }
        output.append(")");
        return output;
    }
}
