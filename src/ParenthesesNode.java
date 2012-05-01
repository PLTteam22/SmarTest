
public class ParenthesesNode extends ASTNode {

	
	public ParenthesesNode(ASTNode expr, int yyline, int yycolumn) {
		super(yyline, yycolumn);
		this.addChild(expr);
		
	}


	@Override
	public void checkSemantics() throws Exception
        {
                this.getChildAt(0).checkSemantics();
                this.setType(this.getChildAt(0).getType());

	}

	@Override
	public StringBuffer generateCode()
        {
		
		StringBuffer output = new StringBuffer();
                
                output.append("(");
                output.append(this.getChildAt(0).generateCode());
                output.append(")");
		
		
		return output;
	}


	/**
	 * @return the set
	 */
	public ASTNode getSet() {
		return this.getChildAt(0);
	}


	
}
