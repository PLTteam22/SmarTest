/**
 * 
 */

/**
 * Represents a list of answer choices
 * @author Aiman
 */
public class AnswerChoicesListNode extends ASTNode {

	
	public AnswerChoicesListNode(int yyline, int yycolumn) {
		super(yyline, yycolumn);		
	}
	
	/**
	 * Adds a single answer to the list
	 * @param answer the answer to be added
	 */
	public void addAnswer(ASTNode answer)
	{
		this.addChild(answer);
	}
	

	/**
	 * Verifies the semantics of each answer choice in the list
	 * Verifies all children are of type "answer"
	 * @see ASTNode#checkSemantics()
	 */
	@Override
	public void checkSemantics() throws Exception {
		for (int i=0; i<this.getChildCount(); i++)
			this.getChildAt(0).checkSemantics();

		for (int i=0; i<this.getChildCount(); i++)
			if (!this.getChildAt(0).getType().equalsIgnoreCase("answer"))
			{
				throw new Exception("Line " + this.getYyline() + ":" + this.getYycolumn() + " "
						+ " question answers must be of type answer");				
			}
		
		
		this.setType("answer_list");
	}

	/* (non-Javadoc)
	 * @see ASTNode#generateCode()
	 */
	@Override
	public String generateCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
