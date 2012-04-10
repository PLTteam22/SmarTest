
import java.util.ArrayList;

class FactorNode extends ASTNode
{
	
}


public class FactorListNode extends ArrayList {
	
	public FactorListNode(ASTNode node)
	{
		super();
		this.add(node);
	}

	public FactorListNode add(FactorNode factor)
	{ this.add(factor); return this; }
}

