
public class LiteralNode extends ASTNode {

	public LiteralNode(String type, Object object, int yyline, int yycolumn) 
	{
		super(yyline, yycolumn);
		this.setType(type);
		if(type.equalsIgnoreCase("boolean"))
		{
			Boolean value=(Boolean)object;
			boolean result=value.booleanValue();
			
			this.setBvalue(result);
		}
		else if(type.equalsIgnoreCase("int"))
		{
			Integer value=(Integer)object;
			int result=value.intValue();
			this.setIvalue(result);
		}
		else if(type.equalsIgnoreCase("float"))
		{
			Double value=(Double)object;
			double result= value.doubleValue();
			this.setDvalue(result);
		}
		else if(type.equalsIgnoreCase("string"))
		{
			String value=(String)object;
			this.setSvalue(value);
		}
		else
		{
			Integer value =(Integer)object;
			int result=value.intValue();
			char c=(char)result;
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

