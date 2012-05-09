package smartest;

/**
 * Implements AST node representation for Identifiers
 * 
 * @author Aiman
 */
public class IDNode extends ASTNode {

    private String name;
    private boolean isDeclaration;
    private String scopeId; /*
                             * this is set after the identifier has been
                             * inserted into symbol table
                             */

    /**
     * Instantiates IDNode. This represents ID nodes in the grammar
     * 
     * @param name
     *            the name of the identifier
     * @param yyline
     *            the line where this operator was found in the source code
     * @param yycolumn
     *            the column where this operator was found in the source code
     */
    public IDNode(String name, boolean isDeclaration, int yyline, int yycolumn) {
        super(yyline, yycolumn);
        this.name = name;
        this.isDeclaration = isDeclaration;
    }

    /**
     * ID semantic check. If this is not part of a declaration, it verifies that
     * this ID exists in the symbol table of the current scope and sets the type
     * of this node to be the type declared in the symbol table. If this is part
     * of a declaration, it makes sure this identifier has not ben declared
     * before.
     * 
     * @throws Exception
     *             thrown when a semantic error is found
     * @see ASTNode#checkSemantics()
     */
    @Override
    public void checkSemantics() throws Exception {
        String lowerCase = null;
        if (this.name != null)
            lowerCase = this.name.toLowerCase();
        boolean inSymbolTable = SymbolsTables.containsSymbol(lowerCase);

        if (!isDeclaration) {
            if (!inSymbolTable) {
                throw new Exception("Line " + this.getYyline() + ":"
                        + this.getYycolumn() + ": " + "cannot find symbol: "
                        + this.name + " make sure variable has been declared");
            }
            this.setType(SymbolsTables.lookupSymbol(lowerCase)[0]);
            this.setScopeId(SymbolsTables.lookupSymbol(lowerCase)[3]);
        } else {
            // Verify that this variable has not been already declared before
            if (inSymbolTable) {
                throw new Exception("Line " + this.getYyline() + ":"
                        + this.getYycolumn() + ": variable " + name
                        + " has already been declared at line "
                        + SymbolsTables.lookupSymbol(lowerCase)[2]);
            }

        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ASTNode#generateCode()
     */
    @Override
    public StringBuffer generateCode() {
        StringBuffer output = new StringBuffer();
        String lowerCase = null;
        if (name != null)
            lowerCase = name.toLowerCase();
        String[] symbolTableEntry = SymbolsTables.lookupInScope(lowerCase,
                scopeId);
        if (symbolTableEntry != null) {
            output.append(symbolTableEntry[1]);
        }
        return output;
    }

    /**
     * Returns the identifier name
     * 
     * @return identifier name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the identifier name
     * 
     * @param name
     *            sets the name of the identifier
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the scopeId
     */
    public String getScopeId() {
        return scopeId;
    }

    /**
     * @param scopeId
     *            the scopeId to set
     */
    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

}
