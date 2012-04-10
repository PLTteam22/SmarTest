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
	 * @see ASTNode#checkSemantics()
	 */
	@Override
	public void checkSemantics() throws Exception {
		for (int i=0; i<this.getChildCount(); i++)
			this.getChildAt(0).checkSemantics();
		
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
