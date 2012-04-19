import java.util.ArrayList;

public class FunctionNode extends ASTNode
{
        private ArrayList<ASTNode> paramList;
        private ASTNode stmtList;
        private String rtrnType;
        private String identifier;

        public FunctionNode(String returnType, String id, ArrayList<ASTNode> parameterList, ASTNode statementList, int yyline, int yycol)
        {
                super(yyline, yycol);
                rtrnType = returnType;
                paramList = parameterList;
                stmtList = statementList;
                identifier = id;
        }
	public void checkSemantics() throws Exception
        {
                
                if (paramList != null)
                {
                        for (ASTNode param : paramList)
                        {
                                param.checkSemantics();
                        }
                }
                if (stmtList != null)
                {
                        stmtList.checkSemantics();
                }
                if (Parser.functionSymbolsTable.containsKey(identifier.toLowerCase()))
                {
                        throw new Exception("Line " + this.getYyline() +
                                ": Function " + identifier.toLowerCase() +
                                " is already defined");
                }
                else
                {
                        String javaID = "_smartestFunction_" + identifier;
                        ArrayList<String> parameterList = new ArrayList<String>();
                        if (paramList != null)
                        {
	                        for (ASTNode param : paramList)
	                        {
	                                parameterList.add(param.getType());
	                        }
                        }
                        Parser.functionSymbolsTable.put(identifier.toLowerCase(), new FunctionSymbolTableEntry(identifier, javaID, rtrnType, parameterList));
                }

                if (!"void".equalsIgnoreCase(rtrnType) && !"return".equalsIgnoreCase(stmtList.getType()))
                {
                        throw new Exception("Line " + this.getYyline() + 
                                ": Missing return statement");
                }




                setType(rtrnType);
        }
	
		public String getIdentifier()
		{
			return identifier;
		}
	
        public StringBuffer generateCode()
        {
        		StringBuffer output = new StringBuffer();
        		StringBuffer statementsOutput;
        		FunctionSymbolTableEntry entry = Parser.functionSymbolsTable.get(identifier);
        		if (entry == null)
        			return null;
        		
        		
        		output.append("public static ");
        		output.append(rtrnType);
        		output.append(" ");
        		output.append(entry.getJavaID());
        		output.append("( ");
                if (paramList != null)
                {
                		boolean firstParam = true;
                        for (ASTNode param : paramList)
                        {
                        		if (!firstParam)
                        			output.append(", ");
                                output.append(param.generateCode());
                                firstParam = false;
                                
                        }
                }
                output.append(" )\n");
                output.append("{\n");
                statementsOutput = stmtList.generateCode();
                if (statementsOutput != null)
                output.append(statementsOutput);
                output.append("}\n");
                return output;
        }
        
}
