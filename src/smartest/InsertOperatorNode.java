package smartest;

/**
 * 
 */

/**
 * Represents the insert operator ( << )
 * As defined in this grammar:
 * ID INSERT expression ';'
 * Where INSERT token is "<<"
 * Expression is a question type
 * and ID is set type
 * Example:
 * s << $"Math":"1+1 is ?": [ "2":4, "5":0 ]
 * @author Aiman
 */
public class InsertOperatorNode extends ASTNode {

	
	/**
	 * Instantiates an insert operator node, invoked by this grammar production:
	 * statement: ID INSERT expression ';'
	 * @param id the set to be modified
	 * @param expression the question to be inserted
	 * @param yyline line where this operator was found in the source code
	 * @param yycolumn column where this operator was found in the source code
	 */
	public InsertOperatorNode(ASTNode set, ASTNode question, int yyline, int yycolumn) {
		super(yyline, yycolumn);
		this.addChild(set);
		this.addChild(question);
		
	}

	/**
	 * Verifies the semantic of the set operator:
	 * Example: s << q
	 * s must bet a set
	 * q must be a question
	 * Insert operator << has a side effect, it returns set
	 * @see ASTNode#checkSemantics()
	 */
	@Override
	public void checkSemantics() throws Exception {

		for (ASTNode child : this.getChildren())
                {
                        child.checkSemantics();
                }
		
		if (! "set".equalsIgnoreCase(getSet().getType()))
		{
			throw new Exception("Line " + this.getYyline() + ":" + this.getYycolumn() + " "
					+ " insert operator << left operand must be a set, found: " + getSet().getType());
		}
                for (ASTNode quest : this.getChildren())
                {
		        if (quest != getSet() && !"question".equalsIgnoreCase(quest.getType()))
		        {
		        	throw new Exception("Line " + this.getYyline() + ":" + this.getYycolumn() + " "
					+ " insert operator << right operand must be a question, found: " + quest.getType());
		        }
		}
		this.setType("set");
		


	}

	/* (non-Javadoc)
	 * @see ASTNode#generateCode()
	 */
	@Override
	public StringBuffer generateCode() {
		
		StringBuffer output = new StringBuffer();
                ASTNode setID = getSet();
                output.append(setID.generateCode());
                for (ASTNode questionE : this.getChildren())
                {
                        if (questionE == setID)
                                continue;
                        output.append(".addQuestion(");
                        output.append(questionE.generateCode());
                        output.append(")");
                }
		
		
		
		return output;
	}


	/**
	 * @return the set
	 */
	public ASTNode getSet() {
		return this.getChildAt(0);
	}


	
}
