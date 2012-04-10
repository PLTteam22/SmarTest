
import java.util.ArrayList;


/*
 * 
 */
public class ASTNode {
	
	private ArrayList<ASTNode> children;
	private String type;	

	public ASTNode()
	{
		this.children = new ArrayList<ASTNode>();
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


}
