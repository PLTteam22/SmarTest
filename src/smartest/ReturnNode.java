package smartest;

/**
 * The Class ReturnNode represents a return statement. A return statement may
 * have an associated expression whose value will be returned to the caller of
 * this function.
 * 
 * @author Harpreet
 */
public class ReturnNode extends ASTNode {
    /** The return type. */
    private String returnType;

    /**
     * Instantiates a new return node.
     * 
     * @param returnType
     *            the return type
     * @param returnExpression
     *            the associated expression, null if none
     * @param yyline
     *            the corresponding line in the input file
     * @param yycolumn
     *            the corresponding column in the input file
     */
    ReturnNode(String returnType, ASTNode returnExpression, int yyline,
            int yycolumn) {
        super(yyline, yycolumn);
        this.returnType = returnType;
        this.addChild(returnExpression);
    }

    /**
     * Semantic check of the return statement. Ensures no value is returned in a
     * void function and that the type of the associated expression is what is
     * expected by the return type of the function.
     * 
     * @see ASTNode#checkSemantics()
     */
    public void checkSemantics() throws Exception {
        for (ASTNode child : this.getChildren()) {
            if (child != null)
                child.checkSemantics();
        }
        if (returnType.equalsIgnoreCase("void") && this.getChildAt(0) != null) {
            throw new Exception("Cannot return a value in a void function");
        }
        String returnExpressionType;
        if (this.getChildAt(0) == null)
            returnExpressionType = "void";
        else
            returnExpressionType = this.getChildAt(0).getType();
        if (!returnType.equalsIgnoreCase(returnExpressionType)) {
            if (!("float".equalsIgnoreCase(returnType) && "int"
                    .equalsIgnoreCase(returnExpressionType)))
                throw new Exception("Expecting return type " + returnType
                        + " not " + returnExpressionType);
        }
        this.setType("return");
    }

    /*
     * (non-Javadoc)
     * 
     * @see ASTNode#generateCode()
     */
    public StringBuffer generateCode() {
        StringBuffer output = new StringBuffer();
        if (this.getChildAt(0) != null) {
            String type = this.getChildAt(0).getType();
            if ("float".equalsIgnoreCase(type))
                type = "double";
            output.append(type);
            output.append(" temp_var = ");
            output.append(initialValueForType(this.getChildAt(0).getType()));
            output.append(";\ntry {\ntemp_var = ");
            output.append(this.getChildAt(0).generateCode());
            output.append(";\n} catch (Exception e) {\n System.out.println(\"SmarTest: Runtime error at line: "
                    + getYyline()
                    + " (\" + e.getMessage() + \")\"); \nSystem.exit(0);}");
        }
        output.append("return ");
        if (this.getChildAt(0) != null)
            output.append("temp_var");
        return output;
    }

    /**
     * Gets the return type.
     * 
     * @return the return type
     */
    public String getReturnType() {
        return returnType;
    }

    /**
     * Sets the return type.
     * 
     * @param returnType
     *            the new return type
     */
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    /**
     * Determines the default initial value for the given type.
     * 
     * @param type
     *            the type
     * @return the default value
     */
    private String initialValueForType(String type) {
        if ("string".equalsIgnoreCase(type))
            return "\"\"";
        if ("float".equalsIgnoreCase(type))
            return "" + 0.0;
        if ("int".equalsIgnoreCase(type))
            return "" + 0;
        if ("boolean".equalsIgnoreCase(type))
            return "false";
        if ("question".equalsIgnoreCase(type))
            return "null";
        if ("set".equalsIgnoreCase(type))
            return "null";
        return "(" + type + ")" + 0;
    }
}
