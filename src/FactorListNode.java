/*
 * Implements Semantic checking and code output generation for
 * FactorListNode
 * 
 */
/**
 * The Class FactorListNode.
 * @author Parth
 */
class FactorListNode extends ASTNode {
	/*
	 * Instantiates FactorListNode invoked by this grammar:
	 *
	 *  @param lcNode represent child node
	 *  @param yyline,yycolumn represents nodes line number and column number 
	 */
	/**
	 * Instantiates a new factor list node.
	 *
	 * @param lcNode the lc node
	 * @param rcNode the rc node
	 * @param yyline the yyline
	 * @param yycolumn the yycolumn
	 */
	public FactorListNode(ASTNode lcNode, ASTNode rcNode, int yyline, int yycolumn) {
		super(yyline, yycolumn);
		this.addChild(lcNode);
		this.addChild(rcNode);
	}
	
	/**
	 * Instantiates a new factor list node.
	 *
	 * @param lcNode the lc node
	 * @param yyline the yyline
	 * @param yycolumn the yycolumn
	 */
	public FactorListNode(ASTNode lcNode, int yyline, int yycolumn) {
		super(yyline, yycolumn);
		
		this.addChild(lcNode);
	}

	/*
	 * symnatic check
	 * 
	 */
	/* (non-Javadoc)
	 * @see ASTNode#checkSemantics()
	 */
	@Override
	public void checkSemantics() throws Exception{
			//symantic check for children

                for (ASTNode factor : this.getChildren())
                   {
                        factor.checkSemantics();
                   }
		   //no symantic check required for the parent node 
		   
		   return;	
	}

	/* (non-Javadoc)
	 * @see ASTNode#generateCode()
	 */
	@Override
	public StringBuffer generateCode() {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();
		boolean firstFactor = true;
		for (ASTNode factor : this.getChildren())
		{
			if (!firstFactor)
				output.append(", ");
			else
				firstFactor = false;
			output.append(factor.generateCode());
		}
		return output;
	}

}
