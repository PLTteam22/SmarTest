package smartest;

/**
 * The Class ExpressionNode. Represents the lowest-precedence operation of
 * boolean and AND and boolean OR.
 * 
 * @author Harpreet
 */
public class ExpressionNode extends ASTNode {

    /** The operation type. 0 is AND 1 is OR */
    private int booleanType = 0;

    /**
     * Instantiates a new expression node.
     * 
     * @param str
     *            the type of operation, "or" or "and"
     * @param expression
     *            the left side
     * @param booleanOperand
     *            the right side
     * @param yyline
     *            the corresponding line in the input file
     * @param yycolumn
     *            the corresponding column in the input file
     */
    ExpressionNode(String str, ASTNode expression, ASTNode booleanOperand,
            int yyline, int yycolumn) {

        super(yyline, yycolumn);
        this.addChild(expression);
        this.addChild(booleanOperand);

        if (str.equalsIgnoreCase("or"))
            this.setBooleanType(1);

        else if (str.equalsIgnoreCase("and"))
            this.setBooleanType(0);
    }

    /**
     * Ensures both operands evaluate to type boolean
     * 
     * @see ASTNode#checkSemantics()
     */
    public void checkSemantics() throws Exception {

        this.getChildAt(0).checkSemantics();
        this.getChildAt(1).checkSemantics();

        if (!this.getChildAt(0).getType()
                .equalsIgnoreCase(this.getChildAt(1).getType())) {
            throw new Exception("Type mismatch: statement at Line "
                    + this.getYyline() + ":" + this.getYycolumn()
                    + "should be of the same type.");
        } else if (!this.getChildAt(0).getType().equalsIgnoreCase("boolean")
                | !this.getChildAt(1).getType().equalsIgnoreCase("boolean")) {
            throw new Exception("Incompatible Type: statement at Line "
                    + this.getYyline() + ":" + this.getYycolumn()
                    + "should be a boolean.");
        }
        this.setType("boolean");
    }

    /**
     * Sets the operation type.
     * 
     * @param booleanType
     *            the new boolean type
     */
    public void setBooleanType(int booleanType) {
        this.booleanType = booleanType;
    }

    /**
     * Gets the operation type.
     * 
     * @return the boolean type
     */
    public int getBooleanType() {
        return this.booleanType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ASTNode#generateCode()
     */
    public StringBuffer generateCode() {

        StringBuffer output = new StringBuffer();
        output.append(this.getChildAt(0).generateCode());
        if (getBooleanType() == 0)
            output.append(" && ");
        else if (getBooleanType() == 1)
            output.append(" || ");
        output.append(this.getChildAt(1).generateCode());
        return output;
    }

}
