package smartest;

import java.util.ArrayList;

/**
 * The Class StatementsNode represents a statement block. It is associated with
 * if statements, loop while statements, and function definitions.
 * 
 * @author Daniel Walker
 */
public class StatementsNode extends ASTNode {
    /**
     * Instantiates a new statements node.
     * 
     * @param yyline
     *            the corresponding line in the input file
     * @param yycol
     *            the corresponding colum in the input file
     */
    public StatementsNode(int yyline, int yycol) {
        super(yyline, yycol);
    }

    /**
     * Verifies the semantics of this statement block. It makes sure any
     * non-void function has a return statement that is guaranteed to be
     * executed. It also ensures there are no statements after such a return
     * statement, as this would be unreachable code.
     * 
     * @see ASTNode#checkSemantics()
     */
    public void checkSemantics() throws Exception {
        for (ASTNode statement : this.getChildren()) {
            statement.checkSemantics();
        }
        boolean hasReturn = false;
        for (ASTNode statement : this.getChildren()) {
            if (hasReturn == true) {
                throw new Exception("Line: " + this.getYyline()
                        + ": Unreachable code!");
            }
            if ("return".equalsIgnoreCase(statement.getType())) {
                hasReturn = true;
            }
        }
        if (hasReturn == true) {
            setType("return");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ASTNode#generateCode()
     */
    public StringBuffer generateCode() {
        StringBuffer output = new StringBuffer();
        boolean needsSemi = true;
        for (ASTNode statement : this.getChildren()) {
            Class[] interfaces = statement.getClass().getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                if (interfaces[i].getSimpleName().equalsIgnoreCase(
                        "NoSemiColonStatement"))
                    needsSemi = false;
            }
            if (statement instanceof AssignmentOperatorNode) {
                if (!(((AssignmentOperatorNode) statement).isDeclaration())) {
                    output.append("try {");
                }
            } else if (needsSemi && !(statement instanceof DeclarationNode)
                    && !(statement instanceof AssignmentOperatorNode)
                    && !(statement instanceof ReturnNode))
                output.append("try {");
            output.append(statement.generateCode());
            if (statement instanceof AssignmentOperatorNode) {
                if (!(((AssignmentOperatorNode) statement).isDeclaration())) {
                    output.append(";");
                    output.append("}");
                    output.append("\n");
                    output.append("catch(Exception e) {");
                    output.append("System.out.println(\"SmarTest: Runtime error at line: ");
                    output.append(statement.getYyline());
                    output.append(" (\"+e.getMessage() + \")\"); \nSystem.exit(0);\n}");
                    output.append("\n");
                } else {
                    output.append(";");
                }
            } else if (needsSemi && !(statement instanceof DeclarationNode)
                    && !(statement instanceof AssignmentOperatorNode)
                    && !(statement instanceof ReturnNode)) {
                output.append(";");
                output.append("}");
                output.append("\n");
                output.append("catch(Exception e) {");
                output.append("System.out.println(\"SmarTest: Runtime error at line: ");
                output.append(statement.getYyline());
                output.append(" (\"+e.getMessage() + \")\"); \nSystem.exit(0);\n}");
                output.append("\n");
            } else if (needsSemi) {
                output.append(";");
            }
            output.append("\n");
            needsSemi = true;
        }
        return output;
    }
}
