package smartest;

/**
 * The Class SetLiteralNode is a wrapper for the STL type set.
 * 
 * @author Daniel Walker
 */
public class SetLiteralNode extends ASTNode {
    /**
     * Instantiates a new SetLiteralNode.
     * 
     * @param questionList
     *            the question list
     * @param yyline
     *            the corresponding line in the input file
     * @param yycolumn
     *            the corresponding column in the input file
     */
    public SetLiteralNode(ASTNode questionList, int yyline, int yycolumn) {
        super(yyline, yycolumn);
        this.addChild(questionList);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ASTNode#checkSemantics()
     */
    public void checkSemantics() throws Exception {
        this.getChildAt(0).checkSemantics();
        this.setType("set");
    }

    /**
     * Generates target Java source code. Makes use of StlSetNode as a wrapper
     * for the questions.
     * 
     * @see ASTNode#generateCode()
     */
    public StringBuffer generateCode() {
        StringBuffer output = new StringBuffer();
        output.append("new StlSetNode(");
        output.append(this.getChildAt(0).generateCode());
        output.append(")");
        return output;
    }
}
