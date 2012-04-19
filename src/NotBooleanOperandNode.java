
/*
 * Implements semantic checking and output code generation
 * for NOT statement
 * Example :
 * 			NOT true
 * 			OR
 * 			true
 */
public class NotBooleanOperandNode extends ASTNode{

	private int boolWithNot=0;
	/* 
	 * Instantiates NotBooleanOperandNode invoked by this grammar:
	 * NOT not_boolean_operand
	 * 
	 *  Example:
	 *  NOT true
	 *
	 *  @param str represents a string specifying that it has to be negated.
	 *  @param expr represents an expression which is either relational or boolean
	 *  @param stmt represents the statements to be executed when 'if' condition is satisfied.
	 */

	public NotBooleanOperandNode(String str, ASTNode expr, int yyline, int yycolumn) {
		super(yyline, yycolumn);
		this.addChild(expr);
		this.boolWithNot=1;
		// TODO Auto-generated constructor stub
	}
	
	public int getBoolWithNot() {
		return boolWithNot;
	}

	public void setBoolWithNot(int boolWithNot) {
		this.boolWithNot = boolWithNot;
	}

	/* 
	 * Instantiates NotBooleanOperandNode invoked by this grammar:
	 * NOT not_boolean_operand
	 * 
	 *  Example:
	 *  true
	 *
	 *	(Never called)
	 *  @param expr represents an expression which is either relational or boolean
	 *  @param stmt represents the statements to be executed when 'if' condition is satisfied.
	 */
	public NotBooleanOperandNode(ASTNode expr, int yyline, int yycolumn) {
		super(yyline, yycolumn);
		this.addChild(expr);
		// TODO Auto-generated constructor stub
	}

	/*
	 * Semantic check to make what has been received is a boolean
	 * (non-Javadoc)
	 * @see ASTNode#checkSemantics()
	 */
	@Override
	public void checkSemantics() throws Exception {
		// TODO Auto-generated method stub
		
		this.getChildAt(0).checkSemantics();
		
		
		if (! this.getChildAt(0).getType().equals("boolean"))
		{
			throw new Exception("Type mismatch: statement at Line " + this.getYyline() + ":" + this.getYycolumn()+"should be a booelean");
		}
		this.setType(this.getChildAt(0).getType());
	}

	@Override
	public StringBuffer generateCode() {
		// TODO Auto-generated method stub
		if(boolWithNot==1)
		{
			// Take appropriate action.
		}
		return null;
	}
}