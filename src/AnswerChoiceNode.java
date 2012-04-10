

/**
 * Represents a single answer choice as defined in this grammar:
 * answer_choice : expr1 ':' expr2
 * Example: "Answer 1":55 
 * Semantics:
 * expr1 must be a string
 * expr2 must be an integer
 * @author Aiman
 */
public class AnswerChoiceNode extends ASTNode {

	private ASTNode answerCaption;
	private ASTNode answerPoints;
	
	/**
	 * Instantiates a single answer choice node 
	 * @param expr1 the answer caption
	 * @param expr2 how many points this answer reward if chosen
	 */
	public AnswerChoiceNode(ASTNode expr1, ASTNode expr2, int yyline, int yycolumn)
	{
		super(yyline, yycolumn);
		this.addChild(expr1);
		this.addChild(expr2);
		this.answerCaption = expr1;
		this.answerPoints = expr2;
	}
	
	
	
	
	/**
	 * Verifies the semantics of answer choice:
	 * The answer caption must be a string
	 * Answer points must be an integer
	 * @see ASTNode#checkSemantics()
	 */
	@Override
	public void checkSemantics() throws Exception {
		answerCaption.checkSemantics();
		answerPoints.checkSemantics();
		
		if (! answerCaption.getType().equals("string"))
		{
			throw new Exception("Line " + this.getYyline() + ":" + this.getYycolumn() + " "
					+ " answer caption must be a string, found: " + answerCaption.getType());
		}
		
		if (! answerPoints.getType().equals("int"))
		{
			throw new Exception("Line " + this.getYyline() + ":" + this.getYycolumn() + " "
					+ " answer points must be an int, found: " + answerPoints.getType());
		}		

		this.setType("answer");		

	}

	/* (non-Javadoc)
	 * @see ASTNode#generateCode()
	 */
	@Override
	public String generateCode() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the answerCaption
	 */
	public ASTNode getAnswerCaption() {
		return answerCaption;
	}

	/**
	 * @param answerCaption the answerCaption to set
	 */
	public void setAnswerCaption(ASTNode answerCaption) {
		this.answerCaption = answerCaption;
	}

	/**
	 * @return the answerPoints
	 */
	public ASTNode getAnswerPoints() {
		return answerPoints;
	}

	/**
	 * @param answerPoints the answerPoints to set
	 */
	public void setAnswerPoints(ASTNode answerPoints) {
		this.answerPoints = answerPoints;
	}

}
