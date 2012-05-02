package smartest;

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
                /*
                 * Check to make sure function has a return statement that 
                 * is guarenteed to be executed (if it is not a void function)
                 * and does not have any unreachable code
                 *
                 * The type of the return statement is verified in ReturnNode
                 */
                
                boolean hasReturn = false;
                for (ASTNode statement : this.getChildren())
                {
                        if (hasReturn == true)
                        {
                                throw new Exception("Line: " + this.getYyline() + ": Unreachable code!");
                        }
                        if ("return".equalsIgnoreCase(statement.getType()))
                        {
                                hasReturn = true;
                        }
                }
                if (hasReturn == true)
                {
                        setType("return");
                }
        }
	
        public StringBuffer generateCode()
        {
        		StringBuffer output = new StringBuffer();
        		boolean needsSemi = true;
        		for (ASTNode statement : this.getChildren())
        		{
        			output.append(statement.generateCode());
        			Class[] interfaces = statement.getClass().getInterfaces();
        			for (int i = 0; i < interfaces.length; i++)
        			{
        				if (interfaces[i].getSimpleName().equalsIgnoreCase("NoSemiColonStatement"))
        					needsSemi = false;
        			}
        			if (needsSemi)
        				output.append(";");
        			output.append("\n");
        			needsSemi = true;
        		}
                return output;
        }       
}