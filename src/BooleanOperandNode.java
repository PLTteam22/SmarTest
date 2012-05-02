
/*
 * Implements semantic checking and output code generation
 * for NOT statement
 * Example :
 * 			NOT true
 * 			OR
 * 			true
 */
public class BooleanOperandNode extends ASTNode{
	
	private int equality; // is 1 for equality and 0 for inequality 
	
	/* 
	 *
	 * Instantiates AssignmentOperatorNode invoked by this grammar:
	 * boolean_operand EQUALEQUAL equality_operand .
	 * 
	 * OR
	 * 
	 * boolean_operand NOTEQUAL equality_operand
	 *  
	 *  Example:
	 * true == false
	 * 1+2 == 3*1
	 * i == j
	 * 
	 * true != false
	 * 1+2 != 3*1
	 * i != j
	 *  
	 *  @param str specifies whether the boolean operand was for equals or not equals
	 *  @param expr represents an expression which is either relational or boolean
	 *  @param stmt represents the statements to be executed when 'if' condition is satisfied.
	 */

	public int getEquality() {
		return equality;
	}

	public void setEquality(int equality) {
		this.equality = equality;
	}

	public BooleanOperandNode(String str, ASTNode expr1, ASTNode expr2, int yyline, int yycolumn) {
		super(yyline, yycolumn);
		// TODO Auto-generated constructor stub
		this.addChild(expr1);
		this.addChild(expr2);
		
		if(str.equals("equal"))
			this.equality = 1;
		else if(str.equals("not_equal"))
				this.equality=0;
	}

	/*
	 * Semantic analysis makes sure that both sides of the operator are of the same type
	 * (non-Javadoc)
	 * @see ASTNode#checkSemantics()
	 */
	@Override
	public void checkSemantics() throws Exception {
		// TODO Auto-generated method stub
		this.getChildAt(0).checkSemantics();
		this.getChildAt(1).checkSemantics();
		
		//if (! this.getChildAt(0).getType().equals(this.getChildAt(1).getType()))
		if(!((this.getChildAt(0).getType().equals("double") || this.getChildAt(0).getType().equals("int"))
                                        && (this.getChildAt(1).getType().equals("double") || this.getChildAt(1).getType().equals("int"))))
		{
			throw new Exception("Type mismatch: statement at Line " + this.getYyline() + ":" + 
						this.getYycolumn()+". Left is of type "+this.getChildAt(0).getType()+
						" and Right operand is of type "+this.getChildAt(1).getType());
		}
		this.setType("boolean");
		
		
	}

	@Override
	public StringBuffer generateCode() {
		
		StringBuffer output =  new StringBuffer();
		if(this.equality==1)
		{
			if(!this.getChildAt(0).getType().equalsIgnoreCase("string"))
			{	
				output.append(this.getChildAt(0).generateCode());
				output.append(" == ");
				output.append(this.getChildAt(1).generateCode());
			}
			else
			{
				output.append(this.getChildAt(0).generateCode());
				output.append(".equalsIgnoreCase(");
				output.append(this.getChildAt(1).generateCode());
				output.append(")");
			}
		}
		else if(this.equality==0)
		{
			if(!this.getChildAt(0).getType().equalsIgnoreCase("string"))
			{
				output.append(this.getChildAt(0).generateCode());
				output.append(" != ");
				output.append(this.getChildAt(1).generateCode());
			}
			else
			{
				output.append("!");
				output.append(this.getChildAt(0).generateCode());
				output.append(".equalsIgnoreCase(");
				output.append(this.getChildAt(1).generateCode());
				output.append(")");
			}
		}
			
		return output;
	}

	
	
}

//boolean_operand EQUALEQUAL equality_operand
