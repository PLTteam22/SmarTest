package smartest;

import java.util.ArrayList;

public class FunctionNode extends ASTNode
{
        
        public FunctionNode(String id, ArrayList<ASTNode> paramList, ArrayList<ASTNode> statementList, int yycol)
        {
                super(yyline, yycol);
                this.children.addAll(functionList);
        }
	public void semanticCheck()
        {
                for (param : paramList)
                {
                        param.semanticCheck();
                }
                for (statement : statementList)
                {
                        statement.semanticCheck();
                }
                if (Parser.getFunctionSymbolTable().contains(id.toLowerCase()))
                {
                        throw new Exception("Line " + this.getYyline() +
                                ": Function " + id.toLowerCase() +
                                " is already defined");
                }
        }
        
}
