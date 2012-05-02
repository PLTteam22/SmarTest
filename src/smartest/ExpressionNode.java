package smartest;

public class ExpressionNode extends ASTNode
{
	private int booleanType = 0;
	
	ExpressionNode(String str, ASTNode expression, ASTNode booleanOperand , int yyline, int yycolumn){
		
		super(yyline,yycolumn);
		this.addChild(expression);
		this.addChild(booleanOperand);
		
		if(str.equalsIgnoreCase("or"))
			this.setBooleanType(1);
		
		else if(str.equalsIgnoreCase("and"))
			this.setBooleanType(0);
	}

	public void checkSemantics() throws Exception {

		this.getChildAt(0).checkSemantics();
		this.getChildAt(1).checkSemantics();
		
		if(!this.getChildAt(0).getType().equalsIgnoreCase(this.getChildAt(1).getType()))
		{
			throw new Exception("Type mismatch: statement at Line " + this.getYyline() + ":" + 
					this.getYycolumn()+"should be of the same type.");
		}
		else if(!this.getChildAt(0).getType().equalsIgnoreCase("boolean") | !this.getChildAt(1).getType().equalsIgnoreCase("boolean")) 
		{	
				throw new Exception("Incompatible Type: statement at Line "+ this.getYyline() + ":" +
						this.getYycolumn()+"should be a boolean.");
		}
		
		this.setType("boolean");
	}

	
	
	public void setBooleanType(int booleanType)
	{
		this.booleanType = booleanType;
	}
	public int getBooleanType()
	{
		return this.booleanType;
	}

	
	public StringBuffer generateCode() {
	
		StringBuffer output = new StringBuffer();
		output.append(this.getChildAt(0).generateCode());
		if (getBooleanType() == 0)
			output.append(" && ");
		else if (getBooleanType() == 1)
			output.append(" || ");
		output.append(this.getChildAt(1).generateCode());
		return output;
	}

}
