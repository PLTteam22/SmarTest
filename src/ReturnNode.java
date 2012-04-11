
public class ReturnNode extends ASTNode{

	private String returnType;
	
	ReturnNode(String returnType,int yyline, int yycolumn)
	{
		super(yyline,yycolumn);
		this.returnType = returnType;
	}
	
	public void checkSemantics() 
	{
			
	}
	public String generateCode() 
	{
		return null;
	}
	public String getReturnType()
	{
		return returnType; 
	}
	public void setReturnType(String returnType)
	{
		this.returnType = returnType;
	}
	
}
