/*
 * Implements semantic checking and output code generation
 * of assignment operator ( = )
 * Example: x = y * d
 */
public class AssignmentOperatorNode extends ASTNode {

	private ASTNode idType, id, expr;
	private boolean isDeclaration = false;
	
	/*
	 * Instantiates AssignmentOperatorNode invoked by this grammar:
	 * type ID '=' expression. Note this contains a declaration as well.
	 * 
	 *  Example:
	 *  int x = 5
	 *  
	 *  @param idType represents the type of the identifier (e.g. int)
	 *  @param id represents the identifier node 
	 *  @param expr represents the right-hand side of the operator
	 */
	public AssignmentOperatorNode(ASTNode idType, ASTNode id, ASTNode expr, int yyline, int yycolumn) {
		super(yyline, yycolumn);
		this.addChild(idType);
		this.addChild(id);
		this.addChild(expr);
		this.setDeclaration(true);
		this.isDeclaration = true;
		this.idType = idType;
		this.id = id;
		this.expr = expr;		
	}
	
	
	/*
	 * Instantiates AssignmentOperatorNode invoked by this grammar:
	 * ID '=' expression
	 * 
	 *  Example:
	 *  x = 5
	 *  
	 *  @param idType represents the type of the identifier (e.g. int)
	 *  @param id represents the identifier node 
	 *  @param expr represents the right-hand side of the operator
	 */
	public AssignmentOperatorNode(ASTNode id, ASTNode expr, int yyline, int yycolumn) {
		super(yyline, yycolumn);
		this.addChild(idType);
		this.addChild(id);
		this.addChild(expr);
		this.isDeclaration = false;
		this.id = id;
		this.expr = expr;
	}
	
	/*
	 * Verifies that right side type matches the type of the identifier as in the symbol table
	 * @see ASTNode#checkSemantics()
	 */
	@Override
	public void checkSemantics() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public String generateCode() {
		// TODO Auto-generated method stub
		return null;
	}

	

	public ASTNode getIdType() {
		return idType;
	}

	public void setIdType(ASTNode idType) {
		this.idType = idType;
	}

	public ASTNode getId() {
		return id;
	}

	public void setId(ASTNode id) {
		this.id = id;
	}

	public ASTNode getExpr() {
		return expr;
	}

	public void setExpr(ASTNode expr) {
		this.expr = expr;
	}


	public boolean isDeclaration() {
		return isDeclaration;
	}


	public void setDeclaration(boolean isDeclaration) {
		this.isDeclaration = isDeclaration;
	}
	
	
}
