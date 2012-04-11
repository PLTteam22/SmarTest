
public class LiteralNode extends ASTNode {

	public LiteralNode(String type, int yyline, int yycolumn) {
		super(yyline, yycolumn);
		this.setType(type);
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
