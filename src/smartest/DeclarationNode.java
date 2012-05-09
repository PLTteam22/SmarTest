package smartest;

/**
 * Represents a declaration operation. This is where identifiers are inserted
 * into the symbols tables.
 * 
 * @author Aiman and Daniel Walker
 */
public class DeclarationNode extends ASTNode {

    /** The declared type. */
    private String declaredType;

    /** The identifier node. */
    private IDNode idNode;

    /** If this declaration is a statement. */
    private boolean isStatement;

    /**
     * Instantiates a DeclarationNode that has been invoked by this grammar
     * production:
     * 
     * <pre>
     * type ID
     * </pre>
     * 
     * Example:
     * 
     * <pre>
     * int x
     * </pre>
     * 
     * .
     * 
     * @param idType
     *            the type of the identifier (e.g. int)
     * @param idNode
     *            the identifier node (e.g. x)
     * @param yyline
     *            the line where this operator was found in the source code
     * @param yycolumn
     *            the column where this operator was found in the source code
     */
    public DeclarationNode(String idType, ASTNode idNode, int yyline,
            int yycolumn) {
        super(yyline, yycolumn);
        this.addChild(idNode);
        this.declaredType = idType;
        this.idNode = (IDNode) idNode;
        this.setType(idType);
        isStatement = false;
    }

    /**
     * Verify declaration semantics. Verify that the declared name has not been
     * declared before in this scope. Inserts the symbol into the symbols table.
     * 
     * @throws Exception
     *             if the identifier is already found in the symbol table
     */
    @Override
    public void checkSemantics() throws Exception {

        this.getChildAt(0).checkSemantics();

        // Now, insert this new ID into symbols table, and generate a new valid
        // target variable name for it
        String varName = "_smartestVar_" + this.getIdNode().getName();

        if (Parser.DEBUG) {
            System.out.println("** Inserting new variable into symbols table:");
            System.out.println("Type:" + declaredType);
            System.out.println("Var Name: " + varName);
            System.out.println("Line Number: " + "" + this.getYyline());
        }

        String scopeId = SymbolsTables.insertSymbol(this.getDeclaredType(),
                this.getIdNode().getName().toLowerCase(), varName,
                "" + this.getYyline());
        this.getChildAt(0).setType(getDeclaredType());
        this.idNode.setScopeId(scopeId);
        this.setType(this.getChildAt(0).getType());

    }

    /**
     * Generates target Java source code. If this declaration is a statement,
     * the identifier is initialized to the default value for its type here
     * 
     * @see ASTNode#generateCode()
     */
    @Override
    public StringBuffer generateCode() {
        StringBuffer output = new StringBuffer();
        String[] data = SymbolsTables.lookupInScope(this.getIdNode().getName()
                .toLowerCase(), this.getIdNode().getScopeId());

        String javaType = data[0];
        if (javaType == "question")
            javaType = "Question";
        else if (javaType == "set")
            javaType = "StlSetNode";
        else if (javaType == "float")
            javaType = "double";

        output.append(javaType + " ");
        output.append(this.getChildAt(0).generateCode());
        if (isStatement) {
            if ("string".equalsIgnoreCase(getType())) {
                output.append(" = \"\"");
            } else if ("boolean".equalsIgnoreCase(getType())) {
                output.append(" = false");
            } else if ("float".equalsIgnoreCase(getType())) {
                output.append(" = 0");
            } else if ("int".equalsIgnoreCase(getType())) {
                output.append(" = 0");
            } else if ("char".equalsIgnoreCase(getType())) {
                output.append(" = '\0'");
            } else {
                output.append(" = null");
            }
        }
        return output;
    }

    /**
     * Gets the identifier node that is being declared
     * 
     * @return the identifier node
     */
    public IDNode getIdNode() {
        return idNode;
    }

    /**
     * Sets the identifier node.
     * 
     * @param idNode
     *            the new identifier node
     */
    public void setIdNode(IDNode idNode) {
        this.idNode = idNode;
    }

    /**
     * Gets the declared type.
     * 
     * @return the declared type
     */
    public String getDeclaredType() {
        return declaredType;
    }

    /**
     * Sets whether or not this declaration is itself a statement
     * 
     * @param newValue
     *            the new checks if is statement
     */
    public void setIsStatement(boolean newValue) {
        isStatement = newValue;
    }

    /**
     * Sets the declared type.
     * 
     * @param declaredType
     *            the declared type to set
     */
    public void setDeclaredType(String declaredType) {
        this.declaredType = declaredType;
    }

}
