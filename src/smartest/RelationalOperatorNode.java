package smartest;

/**
 * Implements Semantic checking and code output generation for numerical
 * comparison operations. <br />
 * Examples:
 * 
 * <pre>
 * a  < 3
 * b >= 5
 * </pre>
 * 
 * @author Kshitij
 */
class RelationalOperatorNode extends ASTNode {
    /** The operator. */
    private String operator;

    /**
     * Instantiates a RelationalOperatorNode.
     * 
     * @param op
     *            specifies the type of operator
     * @param lcNode
     *            the left side
     * @param rcNode
     *            the right side
     * @param yyline
     *            the corresponding line in the input file
     * @param yycolumn
     *            the corresponding column in the input file
     */
    public RelationalOperatorNode(String op, ASTNode lcNode, ASTNode rcNode,
            int yyline, int yycolumn) {
        super(yyline, yycolumn);
        this.addChild(lcNode);
        this.addChild(rcNode);
        this.operator = op;
    }

    /**
     * Ensures both operands are numerical, ie of type float or int.
     * 
     * @throws Exception
     *             the exception
     * @see ASTNode#checkSemantics()
     */
    @Override
    public void checkSemantics() throws Exception {
        this.getChildAt(0).checkSemantics();
        this.getChildAt(1).checkSemantics();
        if (!((this.getChildAt(0).getType().equals("float") || this
                .getChildAt(0).getType().equals("int")) && (this.getChildAt(1)
                .getType().equals("float") || this.getChildAt(1).getType()
                .equals("int")))) {
            throw new Exception("Cannot do Relational operation on "
                    + this.getChildAt(0).getType() + " & "
                    + this.getChildAt(1).getType() + " on line "
                    + this.getYyline() + ":" + this.getYycolumn());
        }
        this.setType("boolean");
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ASTNode#generateCode()
     */
    @Override
    public StringBuffer generateCode() {
        StringBuffer output = new StringBuffer();
        output.append(this.getChildAt(0).generateCode());
        if ("LT".equalsIgnoreCase(operator)) {
            output.append(" < ");
        } else if ("LE".equalsIgnoreCase(operator)) {
            output.append(" <= ");
        } else if ("GT".equalsIgnoreCase(operator)) {
            output.append(" > ");
        } else if ("GE".equalsIgnoreCase(operator)) {
            output.append(" >= ");
        }
        output.append(this.getChildAt(1).generateCode());
        return output;
    }
}
