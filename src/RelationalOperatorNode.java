
/*
 * Implements Semantic checking and code output generation for
 * Addition and Subtraction.
 * 
 * Example - a + 3
 * 			 a - 3
 */

public class RelationalOperatorNode extends ASTNode{
	
	int arithType=0;
	/*
	 * Instantiates RelationalOperator invoked by this grammar:
	 * relational_operand '+' term 
	 * relational_operand '-' term
	 * 
	 *  Example:
	 *  a + 3
	 *  a - 3
	 *  
	 *  @param str specifies whether the expression was addition or subtraction
	 *  @param expr represents a relational operand
	 *  @param stmt represents terms 
	 */

	public RelationalOperatorNode(String str, ASTNode relOp, ASTNode terms, int yyline, int yycolumn) {
		super(yyline, yycolumn);
		addChild(relOp);
		addChild(terms);
		if(str.equals("addition"))
			arithType=1;
		else if(str.equals("subtraction"))
			arithType=0;
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Checks the semantics to make sure that integer is being added to an integer.
	 * Implicit conversions are not allowed
	 * Throw an exception otherwise.
	 * (non-Javadoc)
	 * @see ASTNode#checkSemantics()
	 */
	@Override
	public void checkSemantics() throws Exception {
		// TODO Auto-generated method stub
		this.getChildAt(0).checkSemantics();
		this.getChildAt(1).checkSemantics();
		/*
		 * The grammar should look into that only boolean expressions or relational expressions are executed.
		 */
		if (! this.getChildAt(0).getType().equals(this.getChildAt(1).getType()))
		{
			throw new Exception("Type mismatch: statement at Line " + this.getYyline() + ":" + 
						this.getYycolumn()+"should be of the same type.");
		}
		this.setType(this.getChildAt(0).getType());
		
	}

	@Override
	public String generateCode() {
		// TODO Auto-generated method stub
		// Should use arithType
		return null;
	}

}
