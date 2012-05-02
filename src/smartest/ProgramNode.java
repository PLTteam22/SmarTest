package smartest;

import java.util.ArrayList;

public class ProgramNode extends ASTNode
{
        
        public ProgramNode(ArrayList<ASTNode> functionList, int yyline, int yycol)
        {
                super(yyline, yycol);
                if (functionList != null)
                        this.getChildren().addAll(functionList);
        }
	public void checkSemantics() throws Exception
        {      
                for (ASTNode function : this.getChildren())
                {
                        function.checkSemantics();
                }
                if (! Parser.functionSymbolsTable.containsKey("main") )
                {
                        throw new Exception(this.getYyline() + ":" + this.getYycolumn() + ": No function called main defined");
                }

        }
        public StringBuffer generateCode(String file_name)
        {
        	StringBuffer output = new StringBuffer();
        	output.append("import smartest.*;\nimport java.util.ArrayList;\n");
        	
        	output.append("public class " + file_name + " {\n");
        	output.append("public static void main(String[] args) {\n");
        	output.append("_smartestFunction_" + Parser.functionSymbolsTable.get("main").getID() + "();\n}\n");
            for (ASTNode function : this.getChildren())
            {
                    output.append(function.generateCode());
            }
        	
        	output.append("}");
        	
        	System.out.println(output);
        	
                return null;
        }
        public StringBuffer generateCode()
        {
                return generateCode("STL");
        }
        
}
