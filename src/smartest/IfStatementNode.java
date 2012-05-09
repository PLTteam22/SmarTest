package smartest;

/**
 * Implements semantic checking and output code generation for an 'if-else'
 * statement. This node represents the following grammar production.
 * 
 * <pre>
 * IF '(' expression ')' '{' statements '}'
 * </pre>
 * 
 * Note expression must evaluate to type boolean<br />
 * 
 * Examples:
 * 
 * <pre>
 * if (i &lt;= 4) {
 *     i = i + 1;
 * }
 * 
 * if (i &lt;= 4) {
 *     i = i + 1;
 * } else {
 *     i = i - 1;
 * }
 * </pre>
 * 
 * @author Kshitij
 */
public class IfStatementNode extends ASTNode implements NoSemiColonStatement {
    /** if or if-else. */
    private int ifType;

    /**
     * Instantiates an IfStatementNode
     * 
     * @param expr
     *            represents a boolean expression
     * @param stmt
     *            represents the statements to be executed iff 'if' condition is
     *            satisfied.
     * @param yyline
     *            the corresponding line in the input file
     * @param yycolumn
     *            the corresponding column in the input file
     */
    public IfStatementNode(ASTNode expr, ASTNode stmt, int yyline, int yycolumn) {
        super(yyline, yycolumn);
        this.addChild(expr);
        this.addChild(stmt);
        this.ifType = 1;
    }

    /**
     * Instantiates an ifStatementNode.
     * 
     * @param expr
     *            represents a boolean expression
     * @param stmt1
     *            represents the statements to be executed iff 'if' condition is
     *            satisfied.
     * @param stmt2
     *            represents the statements to be executed iff 'if' condition is
     *            not satisfied.
     * @param yyline
     *            the corresponding line in the input file
     * @param yycolumn
     *            the corresponding column in the input ifle
     */
    public IfStatementNode(ASTNode expr, ASTNode stmt1, ASTNode stmt2,
            int yyline, int yycolumn) {
        super(yyline, yycolumn);
        this.addChild(expr);
        this.addChild(stmt1);
        this.addChild(stmt2);
        this.ifType = 2;
    }

    /**
     * This is the semantic analysis for the if statement. The expression should
     * be of type boolean.
     * 
     * @throws Exception
     *             if the expression is not of type boolean
     * @see ASTNode#checkSemantics()
     */
    @Override
    public void checkSemantics() throws Exception {
        SymbolsTables.enterNewScope();
        if (this.getChildCount() == 2) {
            this.getChildAt(0).checkSemantics();
            this.getChildAt(1).checkSemantics();
        } else if (this.getChildCount() == 3) {
            this.getChildAt(0).checkSemantics();
            this.getChildAt(1).checkSemantics();
            this.getChildAt(2).checkSemantics();
            if ("return".equalsIgnoreCase(this.getChildAt(1).getType())
                    && "return".equalsIgnoreCase(this.getChildAt(2).getType()))
                this.setType("return");
        }
        if (!this.getChildAt(0).getType().equals("boolean")) {
            throw new Exception("Type mismatch: statement at Line "
                    + this.getYyline() + ":" + this.getYycolumn()
                    + " should be a boolean; found: "
                    + this.getChildAt(0).getType());
        }
        SymbolsTables.leaveCurrentScope();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ASTNode#generateCode()
     */
    @Override
    public StringBuffer generateCode() {
        StringBuffer output = new StringBuffer();
        output.append("if(" + this.getChildAt(0).generateCode() + ")");
        output.append("\n");
        output.append("{");
        if (this.ifType == 1) {
            output.append(this.getChildAt(1).generateCode());
        } else if (this.ifType == 2) {
            output.append(this.getChildAt(1).generateCode());
            output.append("\n");
            output.append("}");
            output.append("else");
            output.append("{");
            output.append(this.getChildAt(2).generateCode());
        }
        output.append("\n");
        output.append("}");
        return output;
    }

    /**
     * Gets the if type.
     * 
     * @return the if type
     */
    public int getIfType() {
        return ifType;
    }

    /**
     * Sets the if type.
     * 
     * @param ifType
     *            the new if type
     */
    public void setIfType(int ifType) {
        this.ifType = ifType;
    }
}
