%{

import java.io.*;
import java.util.*;

%}

%token  IF ELSE ID INT FLOAT QUESTION SET AND OR NOT NOTEQUAL CHAR BOOLEAN VOID EQUALEQUAL GE LE LT GT MOD BOOLLITERAL LOOP WHILE STRING INSERT RETURN LPARAN RPARAN LCURLY RCURLY COMMA SEMICOLON EQUAL DOLLAR LSQUARE RSQUARE COLON PLUS MINUS MULTIPLY DIVIDE CHARLITERAL STRINGLITERAL FLOATLITERAL INTLITERAL
 


%%


// ================================= //
// Grammar Definition 				 //
// ================================= //


program : optional_function_list
{
        
        success = true;
        $$ = new ParserVal(new ProgramNode((ArrayList<ASTNode>)$1.obj, line, column));
        try
        {
        	((ASTNode)$$.obj).checkSemantics();
//        	System.out.println("\nCompiled successfully");
        }
        catch (Exception e)
        {
        	System.out.println(e.getMessage());
        	if (Parser.DEBUG)
        	{
        		e.printStackTrace();
        	}
        	success = false;
			
        }
        if ( success ) ((ProgramNode)$$.obj).generateCode(INPUT_FILE);
}

optional_function_list : function_list {  $$ = $1; }
| /* emtpy */ {  $$ = new ParserVal(null); }

function_list : function_list function  {  ((ArrayList<ASTNode>)$1.obj).add((ASTNode)$2.obj); $$ = $1; }
| function {  ArrayList<ASTNode> flist = new ArrayList<ASTNode>(); flist.add((ASTNode)$1.obj); $$ = new ParserVal(flist); }

function : return_type ID '(' optional_param_list ')' '{' statements '}'
{
        
	try
	{
	        $$ = new ParserVal(new FunctionNode($1.sval, $2.sval, (ArrayList<ASTNode>)$4.obj, (ASTNode)$7.obj, line, column));
	}
	catch (Exception e)
        {
        	System.out.println(e.getMessage());
        	if (Parser.DEBUG) e.printStackTrace();
		System.exit(1);
        }
}

optional_param_list : /*empty*/ {  $$ = new ParserVal(null); }
| param_list {  $$ = $1; }

param_list: param_list ',' declaration {  ((ArrayList<ASTNode>)$1.obj).add((ASTNode)$3.obj); }
| declaration {  ArrayList<ASTNode> plist = new ArrayList<ASTNode>(); plist.add((ASTNode)$1.obj); $$ = new ParserVal(plist); }

statements : statements statement {  ((StatementsNode)$1.obj).addChild((ASTNode)$2.obj); $$ = $1;}
|/*  empty */ {  $$ = new ParserVal(new StatementsNode(line, column)); }

/* statement productions go here */

declaration : type ID { $$ = new ParserVal(new DeclarationNode($1.sval, new IDNode($2.sval, true, line, column), line, column)); }

statement : declaration ';' {  ((DeclarationNode)$1.obj).setIsStatement(true); $$ = $1; }
| declaration '=' expression ';' {  $$ = new ParserVal(new AssignmentOperatorNode((DeclarationNode)$1.obj, (ASTNode)$3.obj, line, column)); }
| ID '=' expression ';' {  $$ = new ParserVal(new AssignmentOperatorNode(new IDNode($1.sval, false, line, column), (ASTNode)$3.obj, line, column)); }
| function_call ';' {  }
| loop {  }
| if_statement {  }
//| ID INSERT expression ';' {  $$ = new ParserVal(new InsertOperatorNode(new IDNode($1.sval, false, line, column), (ASTNode)$3.obj, line, column)); }
| set_insert ';' { $$ = $1; }
| RETURN optional_expression ';' {  $$ = new ParserVal(new ReturnNode(currentReturnType, (ASTNode)$2.obj, line, column)); }
//| declaration '=' '[' question_list ']' ';' {$$ = new ParserVal(new AssignmentOperatorNode((DeclarationNode)$1.obj, (ASTNode)$4.obj, line, column)); } 


optional_question_list : question_list { $$ = $1; }
| /* empty */   { QuestionListNode ql = new QuestionListNode(line, column);  $$ = new ParserVal(ql); }

question_list : question_list ',' expression {  ((QuestionListNode)$1.obj).addChild((ASTNode)$3.obj);  }
| expression { QuestionListNode ql = new QuestionListNode(line, column); ql.addChild((ASTNode)$1.obj); $$ = new ParserVal(ql); }

set_literal : '[' optional_question_list ']' { SetLiteralNode sl = new SetLiteralNode((ASTNode)$2.obj, line, column); $$ = new ParserVal(sl); }

set_insert : set_insert INSERT expression { ((ASTNode)$1.obj).addChild((ASTNode)$3.obj); $$ = $1; }
| ID INSERT expression { $$ = new ParserVal(new InsertOperatorNode(new IDNode($1.sval, false, line, column), (ASTNode)$3.obj, line, column)); }

type : INT {  $$ = new ParserVal("int"); }
| FLOAT {  $$ = new ParserVal("float"); }
| CHAR {  $$ = new ParserVal("char"); }
| BOOLEAN {  $$ = new ParserVal("boolean"); }
| STRING {  $$ = new ParserVal("String"); } 
| QUESTION {  $$ = new ParserVal("question"); }
| SET { $$ = new ParserVal("set"); }

return_type : type 
	{ 
		$$ = new ParserVal($1.sval);
		Parser.currentReturnType = $1.sval;
	}
| VOID 
	{ 
		 
		$$ = new ParserVal("void");
		Parser.currentReturnType = "void";
	}

optional_expression : expression {  $$ = $1; }
| /* empty */ {  $$ = new ParserVal(null);}


/* begin if productions */

if_statement : IF '(' expression ')' '{' statements '}' 
	{ 
		$$ = new ParserVal(new IfStatementNode((ASTNode)$3.obj, (ASTNode)$6.obj, line, column));
		 
	}
| IF '(' expression ')' '{' statements '}' ELSE '{' statements '}' 
	{
		$$ = new ParserVal(new IfStatementNode((ASTNode)$3.obj, (ASTNode)$6.obj, (ASTNode)$10.obj, line, column));
	 	
	}

loop : LOOP WHILE '(' expression ')' '{' statements '}' 
{
	$$ = new ParserVal(new LoopNode((ASTNode)$4.obj, (ASTNode)$7.obj, line, column));

}


question_literal : '$' expression ':' expression '[' answer_choices ']' '$' {  $$ = new ParserVal(new QuestionLiteralNode((ASTNode)$2.obj, (ASTNode)$4.obj, (ASTNode)$6.obj, line, column)); }

answer_choices : answer_choices ',' answer_choice {  ((AnswerChoicesListNode)$1.obj).addAnswer((ASTNode)$3.obj); $$ = $1; }
| answer_choice {  $$ = new ParserVal(new AnswerChoicesListNode(line, column)); ((AnswerChoicesListNode)$$.obj).addAnswer((ASTNode)$1.obj); }

answer_choice : expression ':' expression {  $$ = new ParserVal(new AnswerChoiceNode((ASTNode)$1.obj, (ASTNode)$3.obj, line, column)); }


expression : expression AND not_boolean_operand 
	{ 	
		$$ = new ParserVal(new ExpressionNode("and",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column)); 
	}
| expression OR not_boolean_operand 
	{ 
		 $$ = new ParserVal(new ExpressionNode("or",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column)); 
	}
| not_boolean_operand 
	{ 
		 $$ = $1;
	}

not_boolean_operand: NOT not_boolean_operand 
	{ 
		$$ = new ParserVal(new NotBooleanOperandNode("not",(ASTNode)$2.obj, line, column));
		
	}
| boolean_operand 
	{ 
		//$$ = new ParserVal(new NotBooleanOperandNode((ASTNode)$1.obj, line, column));
		$$ = $1;		
		
	}

boolean_operand : boolean_operand EQUALEQUAL equality_operand 
	{ 
		$$ = new ParserVal(new BooleanOperandNode("equal",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
		
	}
| boolean_operand NOTEQUAL equality_operand 
	{
		$$ = new ParserVal(new BooleanOperandNode("not_equal",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
		
	}
| equality_operand 
	{ 	
		$$ = $1;
		
	}

equality_operand : equality_operand  LT relational_operand 
				{
					
					$$ = new ParserVal(new RelationalOperatorNode("LT", (ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
				}
   | equality_operand GT relational_operand 
				{
					
					$$ = new ParserVal(new RelationalOperatorNode("GT", (ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
				}   
   | equality_operand GE relational_operand 
				{
					
					$$ = new ParserVal(new RelationalOperatorNode("GE", (ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
				}   
   | equality_operand LE relational_operand 
				{
					
					$$ = new ParserVal(new RelationalOperatorNode("LE", (ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
				}   
   | relational_operand {  $$ = $1; }

relational_operand : relational_operand '+' term 
	{ 
		$$ = new ParserVal(new ArithmeticOperatorNode("addition",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
		
	}
| relational_operand '-' term 
	{ 
		$$ = new ParserVal(new ArithmeticOperatorNode("subtraction",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
		
	}
| term 
	{ 
	 	$$ = $1;
	 	
	}

term : term '*' factor 
	{
		$$ = new ParserVal(new ArithmeticOperatorNode("multiplication",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
		
	}
| term '/' factor 
	{
		$$ = new ParserVal(new ArithmeticOperatorNode("division",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column)); 
		
	}
| term MOD factor 
	{
		$$ = new ParserVal(new ArithmeticOperatorNode("modulus",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column)); 
		
	}
| factor 
	{ 
		$$ = $1;
		
	}
| '-' factor 
	{ 
		$$ = new ParserVal(new ArithmeticOperatorNode("unary",(ASTNode)$2.obj, line, column));
		
	}


factor : INTLITERAL {  $$ = new ParserVal(new LiteralNode("int",(Object) $1.ival, line, column)); }
| FLOATLITERAL {  $$ = new ParserVal(new LiteralNode("float", (Object) $1.dval, line, column)); }
| CHARLITERAL {  $$ = new ParserVal(new LiteralNode("char", (Object) $1.ival,line, column)); }
| STRINGLITERAL {  $$ = new ParserVal(new LiteralNode("String", (Object) $1.sval, line, column));}
| BOOLLITERAL {  $$ = new ParserVal(new LiteralNode("boolean", (Object) $1.sval,line, column)); }		// Do checking for true and false ignoreCase
| ID 					{ $$ = new ParserVal(new IDNode($1.sval, false, line, column)); }
| question_literal 		{ $$ = $1; }
| set_literal           { $$ = $1; }
| '(' expression ')'	{ $$ = new ParserVal(new ParenthesesNode((ASTNode)$2.obj, line, column)); }  	
| function_call			{ $$ = $1; }

function_call : ID '(' optional_factor_list ')' {  $$ = new ParserVal(new FunctionCallNode($1.sval, (FactorListNode)$3.obj, line,column)); }

optional_factor_list : factor_list {  $$ = $1; }
| /* empty */ { $$= new ParserVal(null); }


factor_list: factor_list ',' expression { 
					
                                        ((FactorListNode)$1.obj).addChild((ASTNode)$3.obj); 
					$$ = $1;
				    }
| expression  { 
		
		$$ = new ParserVal(new FactorListNode((ASTNode)$1.obj, line, column)); 
	}

%%

/**************************************
* Variables
***************************************/
private Yylex lexer;
private static String INPUT_FILE;
public int line = 1, column;
public static boolean DEBUG = false;
public static boolean success;



public static HashMap<String, FunctionSymbolTableEntry> functionSymbolsTable = new HashMap<String, FunctionSymbolTableEntry>();


public static String currentReturnType = "";



/**************************************
* Error handler					 
***************************************/
public void yyerror(String error)
{
    try{      
      if(stateptr >= 0) {
        System.out.print("Syntax Error");
        System.out.println(": Illegal token '" + lexer.yytext() + "'");
      }
    }
    catch(Exception ex){      
    }
}


/**************************************
* Constructor
***************************************/
public Parser(Reader r)
{
  lexer = new Yylex(r, this);
}




/***************************************************
* Lexer: yylex() implementation
****************************************************/
private int yylex()
{  
  int yyl_return = -1;
  
  try
  {
    yylval = new ParserVal(0);
    yyl_return = lexer.yylex();
  }
  catch (IOException e)
  {
    System.err.println("IO error: " + e.getMessage());
  }
  catch (Error ex)
  {
    System.out.println(ex.getMessage());
  }
  
  return yyl_return;
}

public static void main(String args[]) throws IOException
{

  Parser yyparser;
  boolean createFile = false;
  
  // Initialize symbols tables
  SymbolsTables.initialize();

  if (args.length < 1)
  {
    System.out.println("Usage: java Parser <filename.stl>");
    return;
  }
 
  // parse a file
  yyparser = new Parser(new FileReader(args[0]));
  INPUT_FILE = new File(args[0]).getName();
  int lastIndex = INPUT_FILE.lastIndexOf('.');
  if (lastIndex != -1)
  {
        if (lastIndex == INPUT_FILE.length() - 1)
                INPUT_FILE = INPUT_FILE.substring(0, INPUT_FILE.length() - 1);
        else
                INPUT_FILE = INPUT_FILE.substring(0, lastIndex);
  }

  //System.out.println("\nCompiling ...\n");

  ArrayList<String> paraList1 = new ArrayList<String>();
  paraList1.add("string");
  functionSymbolsTable.put("print", new FunctionSymbolTableEntry("print", "BuiltInFunction.print" , "void", paraList1));

  ArrayList<String> paraList2 = new ArrayList<String>(); 
  paraList2.add("int");
  functionSymbolsTable.put("printinteger", new FunctionSymbolTableEntry("printInteger", "BuiltInFunction.printInteger" , "void", paraList2));

  ArrayList<String> paraList3 = new ArrayList<String>();
  functionSymbolsTable.put("readline", new FunctionSymbolTableEntry("readLine", "BuiltInFunction.readLine" , "String", paraList3));

  ArrayList<String> paraList4 = new ArrayList<String>();
  paraList4.add("string");
  paraList4.add("string");
  paraList4.add("string");
  paraList4.add("string");
  functionSymbolsTable.put("load", new FunctionSymbolTableEntry("load", "BuiltInFunction.load" , "set", paraList4));

  ArrayList<String> paraList5 = new ArrayList<String>();
  paraList5.add("char");
  functionSymbolsTable.put("printchar", new FunctionSymbolTableEntry("printChar", "BuiltInFunction.printChar" , "void", paraList5));

  ArrayList<String> paraList6 = new ArrayList<String>();
  paraList6.add("float");
  functionSymbolsTable.put("printfloat", new FunctionSymbolTableEntry("printFloat", "BuiltInFunction.printFloat" , "void", paraList6));
 

  ArrayList<String> paraList7 = new ArrayList<String>();
  paraList7.add("set");
  functionSymbolsTable.put("askquestion", new FunctionSymbolTableEntry("askQuestion", "BuiltInFunction.askQuestion" , "int", paraList7));


  ArrayList<String> paraList8 = new ArrayList<String>();
  paraList8.add("set");
  functionSymbolsTable.put("len", new FunctionSymbolTableEntry("len", "BuiltInFunction.len" , "int", paraList8));


  ArrayList<String> paraList9 = new ArrayList<String>();
  paraList9.add("string");
  paraList9.add("string");
  paraList9.add("string");
  paraList9.add("set");
  functionSymbolsTable.put("save", new FunctionSymbolTableEntry("save", "BuiltInFunction.save" , "void", paraList9));

  ArrayList<String> paraList10 = new ArrayList<String>();
  paraList10.add("boolean");
  functionSymbolsTable.put("printboolean", new FunctionSymbolTableEntry("printBoolean", "BuiltInFunction.printBoolean" , "void", paraList10));


  // Add 2 more pre-defined functions. 


  yyparser.yyparse();
  
  if ( ! success ) 
  {
  	System.exit(1);
  }
  
/*  
  System.out.println("\n========================\n");
  System.out.println("\nSymbols Table:\n");
  Iterator itr = Parser.symbolsTable.keySet().iterator();
  while (itr.hasNext())
  {
  	System.out.println("Variable: " + itr.next());
  }

  
  System.out.println("\n========================\n");
  System.out.println("Functions Table:\n");
  itr = Parser.functionSymbolsTable.keySet().iterator();
  while (itr.hasNext())
  {
  	System.out.println("Variable: " + itr.next());
  }
*/
}

