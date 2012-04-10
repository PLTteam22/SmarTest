
import java.util.ArrayList;


/*
 * 
 */
public class ASTNode {
	
	private ArrayList<ASTNode> children;
	private String type;	
	private int yyline;
	private int yycolumn;
	
	
	public ASTNode(int yyline, int yycolumn)
	{
		this.children = new ArrayList<ASTNode>();
		this.yyline = yyline;
		this.yycolumn = yycolumn;
	}
	
	ASTNode getChildAt(int index)
	{
		return this.children.get(index);	
	}

	int getChildCount()
	{
		return this.children.size();
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


}
