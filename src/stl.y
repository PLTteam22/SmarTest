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
        System.out.print("found a program\n");
        $$ = new ParserVal(new ProgramNode((ArrayList<ASTNode>)$1.obj, line, column));
        try
        {
        	((ASTNode)$$.obj).checkSemantics();
        	System.out.println("\nCompiled successfully");
        }
        catch (Exception e)
        {
        	System.out.println(e.getMessage());
        	e.printStackTrace();
        	
        }
}

optional_function_list : function_list { System.out.println("found optional_function_list\n"); $$ = $1; }
| /* emtpy */ { System.out.println("found optional_function_list\n"); $$ = new ParserVal(null); }

function_list : function_list function  { System.out.print("found function_list\n"); ((ArrayList<ASTNode>)$1.obj).add((ASTNode)$2.obj); $$ = $1; }
| function { System.out.print("found function_list\n"); ArrayList<ASTNode> flist = new ArrayList<ASTNode>(); flist.add((ASTNode)$1.obj); $$ = new ParserVal(flist); }

function : return_type ID '(' optional_param_list ')' '{' statements '}'
{
        System.out.print("found function\n");
        $$ = new ParserVal(new FunctionNode($1.sval, $2.sval, (ArrayList<ASTNode>)$4.obj, (ASTNode)$7.obj, line, column));
}

optional_param_list : /*empty*/ { System.out.print("found optional_param_list\n"); $$ = new ParserVal(null); }
| param_list { System.out.print("found optional_param_list\n"); $$ = $1; }

param_list: param_list ',' declaration { System.out.print("found param_list\n"); ((ArrayList<ASTNode>)$1.obj).add((ASTNode)$3.obj); }
| declaration { System.out.print("found param_list\n"); ArrayList<ASTNode> plist = new ArrayList<ASTNode>(); plist.add((ASTNode)$1.obj); $$ = new ParserVal(plist); }

statements : statements statement { System.out.print("found statements\n"); ((StatementsNode)$1.obj).addChild((ASTNode)$2.obj); $$ = $1;}
|/*  empty */ { System.out.print("found statements\n"); $$ = new ParserVal(new StatementsNode(line, column)); }

/* statement productions go here */

declaration : type ID { $$ = new ParserVal(new DeclarationNode($1.sval, new IDNode($2.sval, true, line, column), line, column)); }

statement : declaration ';' { System.out.print("found statement (int i;)\n"); $$ = new ParserVal((ASTNode)$1.obj); }
| declaration '=' expression ';' { System.out.print("found statement (int i=5;)\n"); $$ = new ParserVal(new AssignmentOperatorNode((DeclarationNode)$1.obj, (ASTNode)$3.obj, line, column)); }
| ID '=' expression ';' { System.out.print("found statement (i=5;)\n"); $$ = new ParserVal(new AssignmentOperatorNode(new IDNode($1.sval, false, line, column), (ASTNode)$3.obj, line, column)); }
| function_call ';' { System.out.print("found statement (func call)\n"); }
| loop { System.out.print("found statement (loop)\n"); }
| if_statement { System.out.print("found statement (if statement)\n"); }
| ID INSERT expression ';' { System.out.print("found statement (insert ques to set)\n"); $$ = new ParserVal(new InsertOperatorNode(new IDNode($1.sval, false, line, column), (ASTNode)$3.obj, line, column)); }
| RETURN optional_expression ';' { System.out.print("found statement (return)\n"); $$ = new ParserVal(new ReturnNode(currentReturnType, (ASTNode)$2.obj, line, column)); }

type : INT { System.out.print("found type (int)\n"); $$ = new ParserVal("int"); }
| FLOAT { System.out.print("found type (float)\n"); $$ = new ParserVal("float"); }
| CHAR { System.out.print("found type (char)\n"); $$ = new ParserVal("char"); }
| BOOLEAN { System.out.print("found type (boolean)\n"); $$ = new ParserVal("boolean"); }
| STRING { System.out.print("found type (string)\n"); $$ = new ParserVal("string"); } 
| QUESTION { System.out.print("found type (question)\n"); $$ = new ParserVal("question"); }
| SET { System.out.print("found type (set)\n"); $$ = new ParserVal("set"); }

return_type : type 
	{ 
		$$ = new ParserVal($1.sval);
		Parser.currentReturnType = $1.sval;
	}
| VOID 
	{ 
		System.out.print("found return_type\n"); 
		$$ = new ParserVal("void");
		Parser.currentReturnType = "void";
	}

optional_expression : expression { System.out.print("found optional_expression\n"); $$ = $1; }
| /* empty */ { System.out.print("found optional_expression\n"); $$ = new ParserVal(null);}


/* begin if productions */

if_statement : IF '(' expression ')' '{' statements '}' 
	{ 
		$$ = new ParserVal(new IfStatementNode((ASTNode)$3.obj, (ASTNode)$6.obj, line, column));
		System.out.print("found if_statement\n"); 
	}
| IF '(' expression ')' '{' statements '}' ELSE '{' statements '}' 
	{
		$$ = new ParserVal(new IfStatementNode((ASTNode)$3.obj, (ASTNode)$6.obj, (ASTNode)$10.obj, line, column));
	 	System.out.print("found if_statement\n"); 
	}
/*
if_statement : matched_statement
 | open_statement

matched_statement : IF '(' expression ')' '{' matched_statement '}' ELSE '{' matched_statement '}'
| statement

open_statement : IF '(' expression ')' '{' statement '}'
 | IF '(' expression ')' '{' if_statement '}'
| IF '(' expression ')' '{' matched_statement '}' ELSE '{' open_statement '}'
*/
/* end if productions */

loop : LOOP WHILE '(' expression ')' '{' statements '}' 
{
	$$ = new ParserVal(new LoopNode((ASTNode)$4.obj, (ASTNode)$7.obj, line, column));
	System.out.print("found loop\n"); 
}


question_literal : '$' expression ':' expression '[' answer_choices ']' '$' { System.out.print("found question_literal\n"); $$ = new ParserVal(new QuestionLiteralNode((ASTNode)$2.obj, (ASTNode)$4.obj, (ASTNode)$6.obj, line, column)); }

answer_choices : answer_choices ',' answer_choice { System.out.print("found answer_choices\n"); ((AnswerChoicesListNode)$1.obj).addAnswer((ASTNode)$3.obj); $$ = $1; }
| answer_choice { System.out.print("found answer_choices\n"); $$ = new ParserVal(new AnswerChoicesListNode(line, column)); ((AnswerChoicesListNode)$$.obj).addAnswer((ASTNode)$1.obj); }

answer_choice : expression ':' expression { System.out.print("found an answer_choice\n"); $$ = new ParserVal(new AnswerChoiceNode((ASTNode)$1.obj, (ASTNode)$3.obj, line, column)); }


expression : expression AND not_boolean_operand 
	{ 	
		System.out.print("found an expression (and)\n"); $$ = new ParserVal(new ExpressionNode("and",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column)); 
	}
| expression OR not_boolean_operand 
	{ 
		System.out.print("found an expression (or)\n"); $$ = new ParserVal(new ExpressionNode("or",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column)); 
	}
| not_boolean_operand 
	{ 
		System.out.print("found an expression (not boolean)\n"); $$ = $1;
	}

not_boolean_operand: NOT not_boolean_operand 
	{ 
		$$ = new ParserVal(new NotBooleanOperandNode("not",(ASTNode)$2.obj, line, column));
		System.out.print("found a not_boolean_operand\n"); 
	}
| boolean_operand 
	{ 
		//$$ = new ParserVal(new NotBooleanOperandNode((ASTNode)$1.obj, line, column));
		$$ = $1;		
		System.out.print("found a not_boolean_operand\n"); 
	}

boolean_operand : boolean_operand EQUALEQUAL equality_operand 
	{ 
		$$ = new ParserVal(new BooleanOperandNode("equal",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
		System.out.print("found a boolean_operand\n"); 
	}
| boolean_operand NOTEQUAL equality_operand 
	{
		$$ = new ParserVal(new BooleanOperandNode("not_equal",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
		System.out.print("found a boolean_operand\n"); 
	}
| equality_operand 
	{ 	
		$$ = $1;
		System.out.print("found a boolean_operand\n"); 
	}

equality_operand : equality_operand  LT relational_operand 
				{
					System.out.print("found a equality_operand (LT)\n");
					$$ = new ParserVal(new RelationalOperatorNode("LT", (ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
				}
   | equality_operand GT relational_operand 
				{
					System.out.print("found a equality_operand (GT)\n");
					$$ = new ParserVal(new RelationalOperatorNode("GT", (ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
				}   
   | equality_operand GE relational_operand 
				{
					System.out.print("found a equality_operand (GE)\n");
					$$ = new ParserVal(new RelationalOperatorNode("GE", (ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
				}   
   | equality_operand LE relational_operand 
				{
					System.out.print("found a equality_operand (LE)\n");
					$$ = new ParserVal(new RelationalOperatorNode("LE", (ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
				}   
   | relational_operand { System.out.print("found a equality_operand\n"); $$ = $1; }

relational_operand : relational_operand '+' term 
	{ 
		$$ = new ParserVal(new ArithmeticOperatorNode("addition",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
		System.out.print("found a relational_operand (+)\n"); 
	}
| relational_operand '-' term 
	{ 
		$$ = new ParserVal(new ArithmeticOperatorNode("subtraction",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
		System.out.print("found a relational_operand (-)\n"); 
	}
| term 
	{ 
	 	$$ = $1;
	 	System.out.print("found a relational_operand\n"); 
	}

term : term '*' factor 
	{
		$$ = new ParserVal(new ArithmeticOperatorNode("multiplication",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column));
		System.out.print("found a term (*)\n"); 
	}
| term '/' factor 
	{
		$$ = new ParserVal(new ArithmeticOperatorNode("division",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column)); 
		System.out.print("found a term (/)\n"); 
	}
| term MOD factor 
	{
		$$ = new ParserVal(new ArithmeticOperatorNode("modulus",(ASTNode)$1.obj, (ASTNode)$3.obj, line, column)); 
		System.out.print("found a term (%)\n"); 
	}
| factor 
	{ 
		$$ = $1;
		System.out.print("found a term\n"); 
	}
| '-' factor 
	{ 
		$$ = new ParserVal(new ArithmeticOperatorNode("unary",(ASTNode)$2.obj, line, column));
		System.out.print("found a term (unary -)\n"); 
	}


factor : INTLITERAL { System.out.print("found a factor (int)\n"); $$ = new ParserVal(new LiteralNode("int",(Object) $1.ival, line, column)); }
| FLOATLITERAL { System.out.print("found a factor (float)\n"); $$ = new ParserVal(new LiteralNode("float", (Object) $1.dval, line, column)); }
| CHARLITERAL { System.out.print("found a factor (char)\n"); $$ = new ParserVal(new LiteralNode("char", (Object) $1.ival,line, column)); }
| STRINGLITERAL { System.out.print("found a factor (string)\n"); $$ = new ParserVal(new LiteralNode("string", (Object) $1.sval, line, column));}
| BOOLLITERAL { System.out.print("found a factor (bool)\n"); $$ = new ParserVal(new LiteralNode("boolean", (Object) $1.sval,line, column)); }		// Do checking for true and false ignoreCase
| ID 					{ $$ = new ParserVal(new IDNode($1.sval, false, line, column)); }
| question_literal 		{ $$ = $1; }
| '(' expression ')'	{ $$ = $2; }  	
| function_call			{ $$ = $1; }

function_call : ID '(' optional_factor_list ')' { System.out.print("found a function_call\n"); $$ = new ParserVal(new FunctionCallNode($1.sval, (FactorListNode)$3.obj, line,column)); }

optional_factor_list : factor_list { System.out.print("found an optional_factor_list\n"); $$ = $1; }
| /* empty */ { $$= new ParserVal(null); }


factor_list: factor_list ',' factor { 
					System.out.print("found a factor_list\n"); 
					((ArrayList<FactorListNode>)$1.obj).add((FactorListNode)$3.obj);
					$$ = $1;
				    }
| factor  { 
		System.out.print("found a factor_list\n"); 
		$$ = new ParserVal(new FactorListNode((ASTNode)$1.obj, line, column)); 
	}

%%

/**************************************
* Variables
***************************************/
private Yylex lexer;
public int line = 1, column;
public static boolean DEBUG = true;

/* 
 * symbolsTable: Keys are the identifier names as found in the source code
 * Values are three-element arrays where the first element represents the type of the identifier
 * and the second element represents a generated variable name to be used in the target code
 * and the third element is the line which this symbol was declared first at
 * For more details see DeclarationNode
 */
public static HashMap<String, String[]> symbolsTable = new HashMap<String, String[]>();


public static HashMap<String, FunctionSymbolTableEntry> functionSymbolsTable = new HashMap<String, FunctionSymbolTableEntry>();
public static String currentReturnType = "";



/**************************************
* Error handler					 
***************************************/
public void yyerror(String error)
{
    try{      
      if(stateptr > 0) {
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
  
  return yyl_return;
}

public static void main(String args[]) throws IOException
{

  Parser yyparser;
  boolean createFile = false;

  if (args.length < 1)
  {
    System.out.println("Usage: java Parser <filename.stl>");
    return;
  }
 
  // parse a file
  yyparser = new Parser(new FileReader(args[0]));

  System.out.println("\nCompiling ...\n");
  yyparser.yyparse();
  
  
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


}

