/*
 * Implements Semantic checking and code output generation for
 * Factor
 * 
 */
class Factor extends ASTNode {
	/*
	 * Instantiates Factor invoked by this grammar:
	 *
	 *  @param lcNode represent child node
	 *  @param yyline,yycolumn represents nodes line number and column number 
	 */
	public Factor(ASTNode lcNode, int yyline, int yycolumn) {
		super(yyline, yycolumn);
		this.addChild(lcNode);
	}

	/*
	 * symnatic check
	 * 
	 */
	@Override
	public void checkSemantics() throws Exception{
			//symantic check for children
		   this.getChildAt(0).checkSemantics();
		   this.getChildAt(1).checkSemantics();
           
		   //no symantic check required for the parent node 
		   
		   return;	
	}

	@Override
	public String generateCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
