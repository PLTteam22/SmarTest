package smartest;

/**
 * A FactorListNode represents a comma-separated list of parameters passed to a
 * function call
 * <p>
 * Example:
 * 
 * <pre>
 * 38, some_variable, "hi"
 * </pre>
 * 
 * </p>
 * 
 * @author Parth
 */
class FactorListNode extends ASTNode {
    /**
     * Instantiates a FactorListNode
     * 
     * @param node
     *            the first parameter in the list
     * @param yyline
     *            the corresponding line in the input file
     * @param yycolumn
     *            the corresponding column in the input file
     */
    public FactorListNode(ASTNode node, int yyline, int yycolumn) {
        super(yyline, yycolumn);
        this.addChild(node);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ASTNode#checkSemantics()
     */
    @Override
    public void checkSemantics() throws Exception {
        for (ASTNode factor : this.getChildren()) {
            factor.checkSemantics();
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
        boolean firstFactor = true;
        for (ASTNode factor : this.getChildren()) {
            if (!firstFactor)
                output.append(", ");
            else
                firstFactor = false;
            output.append(factor.generateCode());
        }
        return output;
    }

}
