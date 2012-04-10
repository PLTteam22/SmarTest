package smartest;

import java.util.ArrayList;

public class ProgramNode extends ASTNode
{
        
        public ProgramNode(ArrayList<ASTNode> functionList, int yyline, int yycol)
        {
                super(yyline, yycol);
                this.children.addAll(functionList);
        }
	public void semanticCheck()
        {
                if (!((FunctionNode)this.children.getChildAt(0)).getIdentifier().equalsIgnoreCase("main"))
                {
                        throw new Exception("No function called main defined");
                }
                for (function : this.children)
                {
                        function.semanticCheck();
                }
        }
        
}
