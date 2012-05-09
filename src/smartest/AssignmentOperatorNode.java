package smartest;

/**
 * Implements semantic checking and output code generation of assignment
 * statements.
 * 
 * 
 * <p>
 * Example:
 * 
 * <pre>
 * x = y * d (without declaration)
 * int x = y + z (with declaration)
 * </pre>
 * </p>
 * 
 * @author Aiman
 */
public class AssignmentOperatorNode extends ASTNode {

    /**
     * Indicates whether this assignment statement includes a declaration as
     * well.
     */
    private boolean isDeclaration = false;

    /**
     * Instantiates an AssignmentOperatorNode that includes a declaration.
     * 
     * This node represents the following grammar production:
     * 
     * <pre>
     * declaration ID '=' expression
     * </pre>
     * 
     * <br />
     * Example:
     * 
     * <pre>
     * int x = 5
     * </pre>
     * 
     * @param declaration
     *            represents the left-side operand which is a declaration
     *            statement
     * @param expr
     *            represents the right-hand side of the operator
     * @param yyline
     *            the line where this operator was found in the source code
     * @param yycolumn
     *            the column where this operator was found in the source code
     */
    public AssignmentOperatorNode(DeclarationNode declaration, ASTNode expr,
            int yyline, int yycolumn) {
        super(yyline, yycolumn);
        this.addChild(declaration);
        this.addChild(expr);
        this.isDeclaration = true;

    }

    /**
     * Instantiates an AssignmentOperatorNode without a declaration.
     * 
     * This node represents the grammar production:
     * 
     * <pre>
     * ID '=' expression
     * </pre>
     * 
     * <br />
     * Example:
     * 
     * <pre>
     * x = 5
     * </pre>
     * 
     * @param id
     *            represents the identifier node
     * @param expr
     *            represents the right-hand side of the operator
     * @param yyline
     *            the line where this operator was found in the source code
     * @param yycolumn
     *            the column where this operator was found in the source code
     */
    public AssignmentOperatorNode(IDNode id, ASTNode expr, int yyline,
            int yycolumn) {
        super(yyline, yycolumn);
        this.addChild(id);
        this.addChild(expr);
    }

    /**
     * Assignment operator semantic check. Verifies that right side type
     * matches, or is compatible with, the left hand side type
     * 
     * @throws Exception
     *             the exception
     * @see ASTNode#checkSemantics()
     */
    @Override
    public void checkSemantics() throws Exception {
        this.getChildAt(0).checkSemantics();
        this.getChildAt(1).checkSemantics();

        // Now, verify the rhs and lhs types are equal
        if (!this.getChildAt(0).getType().equals(this.getChildAt(1).getType())) {
            String varName = "";
            if (this.isDeclaration)
                varName = ((DeclarationNode) this.getChildAt(0)).getIdNode()
                        .getName();
            else
                varName = ((IDNode) this.getChildAt(0)).getName();

            if (!(this.getChildAt(0).getType().equals("float") && this
                    .getChildAt(1).getType().equals("int")))
                throw new Exception("Type mismatch: variable " + varName
                        + " was declared " + this.getChildAt(0).getType()
                        + ", but right-hand side value is "
                        + this.getChildAt(1).getType() + ". Line "
                        + this.getYyline() + ":" + this.getYycolumn());
        }

        this.setType(this.getChildAt(0).getType());

    }

    /*
     * @see ASTNode#generateCode()
     */
    @Override
    public StringBuffer generateCode() {
        StringBuffer output = new StringBuffer();
        if (this.isDeclaration) {

            if (this.getChildAt(0) instanceof DeclarationNode)
                ((DeclarationNode) this.getChildAt(0)).setIsStatement(true);

            output.append(this.getChildAt(0).generateCode());

            output.append(";");
            output.append("try {");
            output.append(this.getChildAt(0).getChildAt(0).generateCode());
            output.append(" = ");
            output.append(this.getChildAt(1).generateCode());
            output.append(";");
            output.append("}");
            output.append("\n");
            output.append("catch(Exception e) {");
            output.append("System.out.println(\"SmarTest: Runtime error found at line: ");
            output.append(this.getChildAt(0).getYyline());
            output.append(" (\"+e.getMessage() + \")\"); \nSystem.exit(0);\n}");
        } else {
            output.append(this.getChildAt(0).generateCode());
            output.append(" = ");
            output.append(this.getChildAt(1).generateCode());
        }
        return output;
    }

    /**
     * Checks if this node represents an assignment that includes a declaration.
     * 
     * @return true, if this assignment includes a declaration
     */
    public boolean isDeclaration() {
        return isDeclaration;
    }

}
