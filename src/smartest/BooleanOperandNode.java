package smartest;

/**
 * Represents a equalitiy/inequality test operation. It implements semantic
 * checking and output code generation for the expressions:
 * 
 * <pre>
 * expression == expression
 * expression != expression
 * </pre>
 * 
 * Examples:
 * 
 * <pre>
 * i == j
 * "hello" != "yes"
 * </pre>
 * 
 * @author Kshitij
 */
public class BooleanOperandNode extends ASTNode {
    /** The equality. */
    private int equality; // is 1 for equality and 0 for inequality

    /**
     * Determines if this ASTNode represents an equality operation or inequality
     * operation
     * 
     * @return 0 for equal 1 for not equal
     */
    public int getEquality() {
        return equality;
    }

    /**
     * Sets the equality.
     * 
     * @param equality
     *            the new equality
     */
    public void setEquality(int equality) {
        this.equality = equality;
    }

    /**
     * Instantiates a new boolean operand node.
     * 
     * @param str
     *            the type of operation, "equal" or "not_equal"
     * @param expr1
     *            the left side
     * @param expr2
     *            the right side
     * @param yyline
     *            the corresponding line in the input file
     * @param yycolumn
     *            the corresponding column in the input file
     */
    public BooleanOperandNode(String str, ASTNode expr1, ASTNode expr2,
            int yyline, int yycolumn) {
        super(yyline, yycolumn);
        // TODO Auto-generated constructor stub
        this.addChild(expr1);
        this.addChild(expr2);
        if (str.equals("equal"))
            this.equality = 1;
        else if (str.equals("not_equal"))
            this.equality = 0;
    }

    /**
     * Semantic analysis makes sure that both sides of the operator are of the
     * same, or compatible, type
     * 
     * @throws Exception
     *             if a semantic error is found
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
            if (!(this.getChildAt(0).getType().equalsIgnoreCase("string") && this
                    .getChildAt(1).getType().equalsIgnoreCase("string")))
                throw new Exception("Type mismatch: statement at Line "
                        + this.getYyline() + ":" + this.getYycolumn()
                        + ". Left is of type " + this.getChildAt(0).getType()
                        + " and Right operand is of type "
                        + this.getChildAt(1).getType());
        }
        this.setType("boolean");
    }

    /**
     * Generates the target Java code for this operation. Equality means equal
     * value, including string equality in STL means all letters are equivalent.
     * 
     * @see ASTNode#generateCode()
     */
    @Override
    public StringBuffer generateCode() {
        StringBuffer output = new StringBuffer();
        if (this.equality == 1) {
            if (!this.getChildAt(0).getType().equalsIgnoreCase("string")) {
                output.append(this.getChildAt(0).generateCode());
                output.append(" == ");
                output.append(this.getChildAt(1).generateCode());
            } else {
                output.append(this.getChildAt(0).generateCode());
                output.append(".equals(");
                output.append(this.getChildAt(1).generateCode());
                output.append(")");
            }
        } else if (this.equality == 0) {
            if (!this.getChildAt(0).getType().equalsIgnoreCase("string")) {
                output.append(this.getChildAt(0).generateCode());
                output.append(" != ");
                output.append(this.getChildAt(1).generateCode());
            } else {
                output.append("!");
                output.append(this.getChildAt(0).generateCode());
                output.append(".equals(");
                output.append(this.getChildAt(1).generateCode());
                output.append(")");
            }
        }
        return output;
    }
}