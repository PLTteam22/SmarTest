
public class LiteralNode extends ASTNode {

	public LiteralNode(String type, Object object, int yyline, int yycolumn) 
	{
		super(yyline, yycolumn);
		this.setType(type);
		if(type.equalsIgnoreCase("boolean"))
		{
			boolean value=(boolean)object;
			this.setBvalue(value);
		}
		else if(type.equalsIgnoreCase("int"))
		{
			int value=(int)object;
			this.setIvalue(value);
		}
		else if(type.equalsIgnoreCase("float"))
		{
			float value=(float)object;
			this.setFvalue(value);
		}
		else if(type.equalsIgnoreCase("string"))
		{
			String value=(String)object;
			this.setSvalue(value);
		}
		else
		{
			int value =(int)object;
			char c=(char)value;
			this.setCvalue(c);
		}
	}

	@Override
	public void checkSemantics() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
