
import java.util.ArrayList;


/*
 * 
 */
public abstract class ASTNode {
	
	private ArrayList<ASTNode> children;
	private String type;	
	private int yyline;
	private int yycolumn;
	
	public abstract void checkSemantics() throws Exception;
	public abstract String generateCode();
	
	
	public ASTNode(int yyline, int yycolumn)
	{
		this.setChildren(new ArrayList<ASTNode>());
		this.yyline = yyline;
		this.yycolumn = yycolumn;
	}
	
	ASTNode getChildAt(int index)
	{
		return this.getChildren().get(index);	
	}
	
	void addChild(ASTNode node)
	{
		this.getChildren().add(node);
	}
	

	int getChildCount()
	{
		return this.getChildren().size();
	}
	
	String getType(){
		return this.type;
	} 

	void setType(String typ){
		this.type= typ;
	}

	public int getYyline() {
		return yyline;
	}

	public void setYyline(int yyline) {
		this.yyline = yyline;
	}

	public int getYycolumn() {
		return yycolumn;
	}

	public void setYycolumn(int yycolumn) {
		this.yycolumn = yycolumn;
	}
	/**
	 * @return the children
	 */
	public ArrayList<ASTNode> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(ArrayList<ASTNode> children) {
		this.children = children;
	}


}
