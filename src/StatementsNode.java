import java.util.ArrayList;

public class StatementsNode extends ASTNode
{
        public StatementsNode(int yyline, int yycol)
        {
                super(yyline, yycol);
        }
	public void checkSemantics() throws Exception
        {
                
                for (ASTNode statement : this.getChildren())
                {
                        statement.checkSemantics();
                }
        }
	
        public String generateCode()
        {
                return "";
        }
        
}
