package smartest;

public class LiteralNode extends ASTNode {

	public LiteralNode(String type, Object object, int yyline, int yycolumn) 
	{
		super(yyline, yycolumn);
		this.setType(type);
		if(type.equalsIgnoreCase("boolean"))
		{
			if(object.toString().equalsIgnoreCase("true"))
				this.setBvalue("true");
			else 
				this.setBvalue("false");
		}
		else if(type.equalsIgnoreCase("int"))
		{
			Integer value=(Integer)object;
			int result=value.intValue();
			this.setIvalue(result);
		}
		else if(type.equalsIgnoreCase("double"))
		{
			Double value=(Double)object;
			double result= value.doubleValue();
			this.setDvalue(result);
		}
		else if(type.equalsIgnoreCase("string"))
		{
			String value=(String)object;
			this.setSvalue("\"" + value + "\"");
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
	public StringBuffer generateCode() {
		// TODO Auto-generated method stub
		String type = getType();
		StringBuffer output = new StringBuffer();
		if(type.equalsIgnoreCase("boolean"))
		{
			output.append(this.getBvalue());
		}
		else if(type.equalsIgnoreCase("int"))
		{
			output.append(this.getIvalue());
		}
		else if(type.equalsIgnoreCase("double"))
		{
			output.append(this.getDvalue());
		}
		else if(type.equalsIgnoreCase("string"))
		{
//			output.append("\"");
			output.append(this.getSvalue());
//			output.append("\"");
		}
		else //char
		{
			output.append("'");
                        if (this.getCvalue() == '\\')
                                output.append("\\\\");
                        else
			        output.append(this.getCvalue());
			output.append("'");
		}
		return output;
	}

}

