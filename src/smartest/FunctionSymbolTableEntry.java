package smartest;

import java.util.ArrayList;

/**
 * The Class FunctionSymbolTableEntry is a wrapper for information that is
 * mapped to by a function identifier in the function symbol table.
 */
public class FunctionSymbolTableEntry {

    /** The java identifier for target code. */
    private String stlID, javaID;

    /** The parameter types. */
    private ArrayList<String> paramTypes;

    /** The return type. */
    private String returnType;

    /**
     * Instantiates a new function symbol table entry.
     * 
     * @param id
     *            the STL identifier
     * @param javaID
     *            the java identifier
     * @param returnType
     *            the return type
     * @param parameterTypes
     *            the list of parameter types
     */
    public FunctionSymbolTableEntry(String id, String javaID,
            String returnType, ArrayList<String> parameterTypes) {
        stlID = id;
        this.javaID = javaID;
        this.setReturnType(returnType);
        paramTypes = parameterTypes;
    }

    /**
     * Gets the STL identifier
     * 
     * @return the identifier
     */
    public String getID() {
        return stlID;
    }

    /**
     * Gets the java identifier
     * 
     * @return the java id
     */
    public String getJavaID() {
        return javaID;
    }

    /**
     * Gets the list of parameter types
     * 
     * @return the parameter types
     */
    public ArrayList<String> getParamTypes() {
        return paramTypes;
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
     *            the return type to set
     */
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
