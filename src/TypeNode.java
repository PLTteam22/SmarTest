package smartest;

public class TypeNode extends ASTNode{
	
	TypeNode(String type,int yyline, int yycolumn)
	{
		super(yyline,yycolumn);
		this.setType(type);
	}

	
	public void checkSemantics() 
	{
		
	}

	
	public String generateCode() 
	{
		return null;
	}

	

}
