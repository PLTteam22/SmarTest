
public class SetLiteralNode extends ASTNode
{	
	public SetLiteralNode(ASTNode questionList, int yyline, int yycolumn)
        {
		super(yyline, yycolumn);
		this.addChild(questionList);

	}

	public void checkSemantics() throws Exception
        {
		
		
	}


	public StringBuffer generateCode()
        {

		/*StringBuffer output = new StringBuffer();
		output.append("new Question(");
		output.append(this.questionCategory.generateCode());
		output.append(", ");
		output.append(this.questionText.generateCode());
		output.append(", ");
		output.append(this.answerChoices.generateCode());
		output.append(")");*/
		
		return null;

		
	}
	

}
