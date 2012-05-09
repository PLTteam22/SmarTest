package smartest;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Class FunctionNode represents a function definition. An STL function
 * definition has the following form:
 * 
 * <pre>
 * return_type identifier(optional_parameter_list)
 * {
 *      optional_statements
 * }
 * </pre>
 */
public class FunctionNode extends ASTNode {
    /** The parameter list. */
    private ArrayList<ASTNode> paramList;
    /** The statement block of the body. */
    private ASTNode stmtList;
    /** The return type. */
    private String rtrnType;
    /** The function identifier. */
    private String identifier;

    /**
     * Instantiates a new function node.
     * 
     * @param returnType
     *            the return type
     * @param id
     *            the identifier name
     * @param parameterList
     *            the parameter list
     * @param statementList
     *            the statement list of the body
     * @param yyline
     *            the corresponding line in the input file
     * @param yycol
     *            the corresponding column in the input file
     * @throws Exception
     *             if a function with the same identifier has already been
     *             defined
     */
    public FunctionNode(String returnType, String id,
            ArrayList<ASTNode> parameterList, ASTNode statementList,
            int yyline, int yycol) throws Exception {
        super(yyline, yycol);
        rtrnType = returnType;
        paramList = parameterList;
        stmtList = statementList;
        identifier = id;
        if (Parser.functionSymbolsTable.containsKey(identifier.toLowerCase())) {
            throw new Exception("Line " + this.getYyline() + ": Function "
                    + identifier.toLowerCase() + " is already defined");
        } else {
            String javaID = "_smartestFunction_" + identifier;
            ArrayList<String> myParameterList = new ArrayList<String>();
            if (paramList != null) {
                for (ASTNode param : paramList) {
                    myParameterList.add(param.getType());
                }
            }
            Parser.functionSymbolsTable.put(identifier.toLowerCase(),
                    new FunctionSymbolTableEntry(identifier, javaID, rtrnType,
                            myParameterList));
            HashMap<String, FunctionSymbolTableEntry> hashMap = Parser.functionSymbolsTable;
        }
    }

    /**
     * Ensures a non-void function has a return that is guaranteed to execute
     * 
     * @see ASTNode#checkSemantics()
     */
    public void checkSemantics() throws Exception {
        SymbolsTables.enterNewScope();
        if (paramList != null) {
            for (ASTNode param : paramList) {
                param.checkSemantics();
            }
        }
        if (stmtList != null) {
            stmtList.checkSemantics();
        }
        if (!"void".equalsIgnoreCase(rtrnType)
                && !"return".equalsIgnoreCase(stmtList.getType())) {
            throw new Exception("Line " + this.getYyline()
                    + ": Missing return statement");
        }
        setType(rtrnType);
        SymbolsTables.leaveCurrentScope();
    }

    /**
     * Gets the identifier.
     * 
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ASTNode#generateCode()
     */
    public StringBuffer generateCode() {
        StringBuffer output = new StringBuffer();
        StringBuffer statementsOutput;
        FunctionSymbolTableEntry entry = Parser.functionSymbolsTable
                .get(identifier.toLowerCase());
        if (entry == null)
            return null;
        output.append("public static ");
        if ("float".equalsIgnoreCase(rtrnType))
            rtrnType = "double";
        output.append(rtrnType);
        output.append(" ");
        output.append(entry.getJavaID());
        output.append("( ");
        if (paramList != null) {
            boolean firstParam = true;
            for (ASTNode param : paramList) {
                if (!firstParam)
                    output.append(", ");
                else
                    firstParam = false;
                output.append(param.generateCode());
                firstParam = false;
            }
        }
        output.append(" )\n");
        output.append("{\n");
        statementsOutput = stmtList.generateCode();
        if (statementsOutput != null)
            output.append(statementsOutput);
        output.append("}\n");
        return output;
    }
}
