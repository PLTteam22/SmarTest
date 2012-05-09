package smartest;

/**
 * The Class ParenthesesNode represents an expression contained within
 * parentheses, which has the highest precedence. <br />
 * Example:
 * 
 * <pre>
 * (4 + 7 < 9 + 12)
 * </pre>
 * 
 * @author Daniel Walker
 */
public class ParenthesesNode extends ASTNode {
    /**
     * Instantiates a new parentheses node.
     * 
     * @param expr
     *            the expr
     * @param yyline
     *            the corresponding line in the input file
     * @param yycolumn
     *            the corresponding column in the input file
     */
    public ParenthesesNode(ASTNode expr, int yyline, int yycolumn) {
        super(yyline, yycolumn);
        this.addChild(expr);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ASTNode#checkSemantics()
     */
    @Override
    public void checkSemantics() throws Exception {
        this.getChildAt(0).checkSemantics();
        this.setType(this.getChildAt(0).getType());
    }

    /*
     * (non-Javadoc)
     * 
     * @see ASTNode#generateCode()
     */
    @Override
    public StringBuffer generateCode() {
        StringBuffer output = new StringBuffer();
        output.append("(");
        output.append(this.getChildAt(0).generateCode());
        output.append(")");
        return output;
    }
}
