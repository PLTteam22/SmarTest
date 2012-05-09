package smartest;

/**
 * Represents the insert operator ( << ). As defined in this grammar
 * production:
 * 
 * <pre>
 * set_insert: set_insert INSERT expression
 * | ID INSERT expression
 * </pre>
 * 
 * Where INSERT token is "<<". Expression is of type question and ID is of type
 * set.<br />
 * Example:
 * 
 * <pre>
 * s << $"Math":"1+1 is ?": [ "2":4, "5":0 ]
 * </pre>
 * 
 * @author Aiman
 */
public class InsertOperatorNode extends ASTNode {
    /**
     * Instantiates an insert operator node
     * 
     * @param set
     *            the set
     * @param question
     *            the first question to insert
     * @param yyline
     *            line where this operator was found in the source code
     * @param yycolumn
     *            column where this operator was found in the source code
     */
    public InsertOperatorNode(ASTNode set, ASTNode question, int yyline,
            int yycolumn) {
        super(yyline, yycolumn);
        this.addChild(set);
        this.addChild(question);
    }

    /**
     * Verifies the semantics of the set operator.<br />
     * Example:
     * 
     * <pre>
     * s << q1 << q2
     * </pre>
     * 
     * s must bet a set q1 and q2 must be question's
     * 
     * @throws Exception
     *             if semantic error found
     * @see ASTNode#checkSemantics()
     */
    @Override
    public void checkSemantics() throws Exception {
        for (ASTNode child : this.getChildren()) {
            child.checkSemantics();
        }
        if (!"set".equalsIgnoreCase(getSet().getType())) {
            throw new Exception("Line " + this.getYyline() + ":"
                    + this.getYycolumn() + " "
                    + " insert operator << left operand must be a set, found: "
                    + getSet().getType());
        }
        for (ASTNode quest : this.getChildren()) {
            if (quest != getSet()
                    && !"question".equalsIgnoreCase(quest.getType())) {
                throw new Exception(
                        "Line "
                                + this.getYyline()
                                + ":"
                                + this.getYycolumn()
                                + " "
                                + " insert operator << right operand must be a question, found: "
                                + quest.getType());
            }
        }
        this.setType("set");
    }

    /*
     * (non-Javadoc)
     * 
     * @see ASTNode#generateCode()
     */
    @Override
    public StringBuffer generateCode() {
        StringBuffer output = new StringBuffer();
        ASTNode setID = getSet();
        output.append(setID.generateCode());
        for (ASTNode questionE : this.getChildren()) {
            if (questionE == setID)
                continue;
            output.append(".addQuestion(");
            output.append(questionE.generateCode());
            output.append(")");
        }
        return output;
    }

    /**
     * Gets the set to insert into.
     * 
     * @return the set
     */
    public ASTNode getSet() {
        return this.getChildAt(0);
    }
}
