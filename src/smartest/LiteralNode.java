package smartest;

/**
 * LiteralNode is a wrapper for a literal in STL source. It represents literals
 * of type int, float, boolean, char, and string. Not question or set.
 * 
 * @author Harpreet
 */
public class LiteralNode extends ASTNode {
    /**
     * Instantiates a new literal node.
     * 
     * @param type
     *            the type
     * @param object
     *            the object containing the value
     * @param yyline
     *            the corresponding line in the input file
     * @param yycolumn
     *            the corresponding column in the input file
     */
    public LiteralNode(String type, Object object, int yyline, int yycolumn) {
        super(yyline, yycolumn);
        this.setType(type);
        if (type.equalsIgnoreCase("boolean")) {
            if (object.toString().equalsIgnoreCase("true"))
                this.setBvalue("true");
            else
                this.setBvalue("false");
        } else if (type.equalsIgnoreCase("int")) {
            Integer value = (Integer) object;
            int result = value.intValue();
            this.setIvalue(result);
        } else if (type.equalsIgnoreCase("float")) {
            Double value = (Double) object;
            double result = value.doubleValue();
            this.setDvalue(result);
        } else if (type.equalsIgnoreCase("string")) {
            String value = (String) object;
            this.setSvalue("\"" + value + "\"");
        } else {
            Integer value = (Integer) object;
            int result = value.intValue();
            char c = (char) result;
            this.setCvalue(c);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ASTNode#checkSemantics()
     */
    @Override
    public void checkSemantics() throws Exception {
    }

    /*
     * (non-Javadoc)
     * 
     * @see ASTNode#generateCode()
     */
    @Override
    public StringBuffer generateCode() {
        String type = getType();
        StringBuffer output = new StringBuffer();
        if (type.equalsIgnoreCase("boolean")) {
            output.append(this.getBvalue());
        } else if (type.equalsIgnoreCase("int")) {
            output.append(this.getIvalue());
        } else if (type.equalsIgnoreCase("float")) {
            if (this.getDvalue() == Double.POSITIVE_INFINITY) {
                output.append("Double.POSITIVE_INFINITY");
            } else if (this.getDvalue() == Double.NEGATIVE_INFINITY) {
                output.append("Double.NEGATIVE_INFINITY");
            } else {
                output.append(this.getDvalue());
            }
        } else if (type.equalsIgnoreCase("string")) {
            output.append(this.getSvalue());
        } else // char
        {
            output.append("'");
            if (this.getCvalue() == '\\')
                output.append("\\\\");
            else
                output.append(this.getCvalue());
            output.append("'");
        }
        return output;
    }
}
