/**
 * Implements node representation for Identifiers
 * @author Aiman 
 */
public class IDNode extends ASTNode {


	private String name;
        private boolean isDeclaration;
	
	/**
	 * Instantiates IDNode
	 * This represents ID nodes in the grammar
	 *  @param name the name of the identifier
	 *  @param yyline the line where this operator was found in the source code
	 *  @param yycolumn the column where this operator was found in the source code
	 */
	public IDNode(String name, boolean isDeclaration, int yyline, int yycolumn) {
		super(yyline, yycolumn);
		this.name = name;
                this.isDeclaration = isDeclaration;
	}


	/**
	 * ID semantics: verifies that this ID exists in the symbol table
	 * and sets the type of this node on the type declared in the symbol table
	 * @throws Exception thrown when a semantic error is found
	 * @see ASTNode#checkSemantics()
	 */
	@Override
	public void checkSemantics() throws Exception {
                boolean inSymbolTable = Parser.symbolsTable.containsKey(this.name);
                if (!isDeclaration)
                {
                        if (! inSymbolTable)
		        {
		                throw new Exception("Line " + this.getYyline() + ":" + this.getYycolumn() + ": " + 
								"cannot find symbol: " + this.name + " make sure variable has been declared");
		        }
		        this.setType(Parser.symbolsTable.get(this.name)[0]);
                }
                else
                {



		        // Verify that this variable has not been already declared before
        		if (inSymbolTable)
        		{
        			throw new Exception("Line " + this.getYyline() + ":" + this.getYycolumn() + ": variable " + name +
        								" has already been declared at line " + Parser.symbolsTable.get(name)[2]);
        		}
		

                }

        }

	/* (non-Javadoc)
	 * @see ASTNode#generateCode()
	 */
	@Override
	public StringBuffer generateCode() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the identifier name
	 * @return identifier name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the identifier name
	 * @param name sets the name of the identifier
	 */
	public void setName(String name) {
		this.name = name;
	}

}
