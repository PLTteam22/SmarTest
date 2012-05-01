
public class QuestionListNode extends ASTNode
{	
	public QuestionListNode(int yyline, int yycolumn)
        {
		super(yyline, yycolumn);

	}

	public void checkSemantics() throws Exception
        {
		
		for (ASTNode question : this.getChildren())
                {
                        question.checkSemantics();
                        if (!"question".equalsIgnoreCase(question.getType()))
                        {
                                throw new Exception("Line " + this.getYyline() + ":" + this.getYycolumn() + " "
					+ " expecting question, found: " + question.getType());
                        }
                }
                
	}


	public StringBuffer generateCode()
        {
                StringBuffer output = new StringBuffer();
                output.append("new QuestionList(");
                boolean first = true;
                for (ASTNode question : this.getChildren())
                {
                        if (!first)
                        {
                                output.append(", ");      
                        }
                        else
                        {
                                first = false;
                        }
                        output.append(question.generateCode());
                }
                output.append(")");
		/*StringBuffer output = new StringBuffer();
		output.append("new Question(");
		output.append(this.questionCategory.generateCode());
		output.append(", ");
		output.append(this.questionText.generateCode());
		output.append(", ");
		output.append(this.answerChoices.generateCode());
		output.append(")");*/
		
		return output;

		
	}
	

}
