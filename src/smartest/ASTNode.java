package smartest;

import java.util.ArrayList;


/*
 * 
 */
public class ASTNode {
	
	private ArrayList<ASTNode> children;
	
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
	

}
