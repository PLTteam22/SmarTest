%{

import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
%}

%token  IF ELSE ID INT FLOAT QUESTION SET AND OR NOT NOTEQUAL CHAR BOOLEAN VOID EQUALEQUAL GE LE LT GT MOD BOOLLITERAL LOOP WHILE STRING INSERT RETURN LPARAN RPARAN LCURLY RCURLY COMMA SEMICOLON EQUAL DOLLAR LSQUARE RSQUARE COLON PLUS MINUS MULTIPLY DIVIDE CHARLITERAL STRINGLITERAL FLOATLITERAL INTLITERAL
 


%%


// ================================= //
// Grammar Definition 				 //
// ================================= //


program : optional_function_list
{
        System.out.print("found a program\n");
        $$ = new ParserVal(new ProgramNode((ArrayList<ASTNode>)$1.obj, yyline, yycolumn));
}

optional_function_list : function_list { System.out.println("found optional_function_list\n"); $$ = new ParserVal((ArrayList<FunctionNode>)$1.obj); }
| /* emtpy */ { System.out.println("found optional_function_list\n"); $$ = new ParserVal(null); }

function_list : function_list function  { System.out.print("found function_list\n"); $$ = new ParserVal(((ArrayList<ASTNode>)$1.obj).add((ASTNode)$2.obj)); }
| function { System.out.print("found function_list\n"); $$ = new ParserVal((new ArrayList<ASTNode>()).add((ASTNode)$1.obj)); }

function : return_type ID '(' optional_param_list ')' '{' statements '}'
{
        System.out.print("found function\n");
        $$ = new ParserVal(new FunctionNode($1.sval, new IDNode($2.sval, yyline, yycolumn), (ArrayList<ASTNode>)$4.obj, (ArrayList<ASTNode>)$7.obj, yyline, yycolumn));
}

optional_param_list : /*empty*/ { System.out.print("found optional_param_list\n"); $$ = new ParserVal(null); }
| param_list { System.out.print("found optional_param_list\n"); $$ = new ParserVal((ASTNode)$1.obj); }

param_list: param_list ',' declaration { System.out.print("found param_list\n"); }
| declaration { System.out.print("found param_list\n"); $$ = new ParserVal((new ArrayList<ASTNode>()).add((ASTNode)$1.obj)); }

statements : statements statement { System.out.print("found statements\n"); ((ArrayList<ASTNode>)$1.obj).add((ASTNode)$2.obj); }
|/*  empty */ { System.out.print("found statements\n"); $$ = new ParserVal(new ArrayList<ASTNode>()); }

/* statement productions go here */

declaration : type ID { $$ = new ParserVal(new DeclarationNode($1.sval, new IDNode($2.sval, yyline, yycolumn), yyline, yycolumn)); }

statement : declaration ';' { System.out.print("found statement (int i;)\n"); $$ = new ParserVal((ASTNode)$1.obj); }
| declaration '=' expression ';' { System.out.print("found statement (int i=5;)\n"); $$ = new ParserVal(new AssignmentOperatorNode((DeclarationNode)$1.obj, (ASTNode)$3.obj, yyline, yycolumn)); }
| ID '=' expression ';' { System.out.print("found statement (i=5;)\n"); $$ = new ParserVal(new AssignmentOperatorNode((IDNode)$1.obj, (ASTNode)$2.obj, yyline, yycolumn)); }
| function_call ';' { System.out.print("found statement (func call)\n"); }
| loop { System.out.print("found statement (loop)\n"); }
| if_statement { System.out.print("found statement (if statement)\n"); }
| ID INSERT expression ';' { System.out.print("found statement (insert ques to set)\n"); $$ = new ParserVal(new InsertOperatorNode((ASTNode)$1.obj, (ASTNode)$3.obj, yyline, yycolumn)); }
| RETURN optional_expression ';' { System.out.print("found statement (return)\n"); }

type : INT { System.out.print("found type (int)\n"); $$ = $1; }
| FLOAT { System.out.print("found type (float)\n"); $$ = $1; }
| CHAR { System.out.print("found type (char)\n"); $$ = $1; }
| BOOLEAN { System.out.print("found type (boolean)\n"); $$ = $1; }
| STRING { System.out.print("found type (string)\n"); $$ = $1; } 
| QUESTION { System.out.print("found type (question)\n"); $$ = $1; }
| SET { System.out.print("found type (set)\n"); $$ = $1; }

return_type : type 
	{ 
		$$ = new ParserVal(new ReturnNode($1.sval,yyline,yycolumn);
	}
| VOID 
	{ 
		System.out.print("found return_type\n"); 
		$$ = new ParserVal(new ReturnNode($1.sval,yyline,yycolumn); 
	}

optional_expression : expression { System.out.print("found optional_expression\n"); }
| /* empty */ { System.out.print("found optional_expression\n"); }


/* begin if productions */

if_statement : IF '(' expression ')' '{' statements '}' 
	{ 
		$$ = new ParserVal(new IfStatementNode((ASTNode)$3.obj, (ASTNode)$6.obj, yyline, yycolumn));
		System.out.print("found if_statement\n"); 
	}
| IF '(' expression ')' '{' statements '}' ELSE '{' statements '}' 
	{
		$$ = new ParserVal(new IfStatementNode((ASTNode)$3.obj, (ASTNode)$6.obj, (ASTNode)$10.obj, yyline, yycolumn));
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
	$$ = new ParserVal(new LoopNode((ASTNode)$4.obj, (ASTNode)$7.obj, yyline, yycolumn));
	System.out.print("found loop\n"); 
}


question_literal : '$' expression ':' expression '[' answer_choices ']' '$' { System.out.print("found question_literal\n"); $$ = new ParserVal(new QuestionLiteralNode((ASTNode)$2.obj, (ASTNode)$4.obj, (ASTNode)$6.obj, yyline, yycolumn)); }

answer_choices : answer_choices ',' answer_choice { System.out.print("found answer_choices\n"); ((AnswerChoicesListNode)$1.obj).addAnswer((ASTNode)$3.obj); $$ = $1; }
| answer_choice { System.out.print("found answer_choices\n"); $$ = new ParserVal(new AnswerChoicesListNode(yyline, yycolumn)); ((AnswerChoicesListNode)$$.obj).addAnswer((ASTNode)$1.obj); }

answer_choice : expression ':' expression { System.out.print("found an answer_choice\n"); $$ = new ParserVal(new AnswerChoiceNode((ASTNode)$1.obj, (ASTNode)$3.obj, yyline, yycolumn)); }


expression : expression AND not_boolean_operand 
	{ 	
		System.out.print("found an expression (and)\n"); $$ = new ParserVal(new ExpressionNode("and",(ASTNode)$1.obj, (ASTNode)$3.obj, yyline, yycolumn)); 
	}
| expression OR not_boolean_operand 
	{ 
		System.out.print("found an expression (or)\n"); $$ = new ParserVal(new ExpressionNode("or",(ASTNode)$1.obj, (ASTNode)$3.obj, yyline, yycolumn)); 
	}
| not_boolean_operand 
	{ 
		System.out.print("found an expression (not boolean)\n"); $$ = $1;
	}

not_boolean_operand: NOT not_boolean_operand 
	{ 
		$$ = new ParserVal(new NotBooleanOperandNode("not",(ASTNode)$2.obj, yyline, yycolumn));
		System.out.print("found a not_boolean_operand\n"); 
	}
| boolean_operand 
	{ 
		//$$ = new ParserVal(new NotBooleanOperandNode((ASTNode)$1.obj, yyline, yycolumn));
		$$ = $1;		
		System.out.print("found a not_boolean_operand\n"); 
	}

boolean_operand : boolean_operand EQUALEQUAL equality_operand 
	{ 
		$$ = new ParserVal(new BooleanOperandNode("equal",(ASTNode)$1.obj, (ASTNode)$3.obj, yyline, yycolumn));
		System.out.print("found a boolean_operand\n"); 
	}
| boolean_operand NOTEQUAL equality_operand 
	{
		$$ = new ParserVal(new BooleanOperandNode("not_equal",(ASTNode)$1.obj, (ASTNode)$3.obj, yyline, yycolumn));
		System.out.print("found a boolean_operand\n"); 
	}
| equality_operand 
	{ 	
		$$ = $1;
		System.out.print("found a boolean_operand\n"); 
	}

equality_operand : equality_operand  LT relational_operand { System.out.print("found a equality_operand (LT)\n"); }
   | equality_operand GT relational_operand { System.out.print("found a equality_operand (GT)\n"); }
   | equality_operand GE relational_operand { System.out.print("found a equality_operand (GE)\n"); }
   | equality_operand LE relational_operand { System.out.print("found a equality_operand (LE)\n"); }
   | relational_operand { System.out.print("found a equality_operand\n"); }

relational_operand : relational_operand '+' term 
	{ 
		$$ = new ParserVal(new ArithmeticOperatorNode("addition",(ASTNode)$1.obj, (ASTNode)$3.obj, yyline, yycolumn));
		System.out.print("found a relational_operand (+)\n"); 
	}
| relational_operand '-' term 
	{ 
		$$ = new ParserVal(new ArithmeticOperatorNode("subtraction",(ASTNode)$1.obj, (ASTNode)$3.obj, yyline, yycolumn));
		System.out.print("found a relational_operand (-)\n"); 
	}
| term 
	{ 
	 	$$ = $1;
	 	System.out.print("found a relational_operand\n"); 
	}

term : term '*' factor 
	{
		$$ = new ParserVal(new ArithmeticOperatorNode("multiplication",(ASTNode)$1.obj, (ASTNode)$3.obj, yyline, yycolumn));
		System.out.print("found a term (*)\n"); 
	}
| term '/' factor 
	{
		$$ = new ParserVal(new ArithmeticOperatorNode("division",(ASTNode)$1.obj, (ASTNode)$3.obj, yyline, yycolumn)); 
		System.out.print("found a term (/)\n"); 
	}
| term MOD factor 
	{
		$$ = new ParserVal(new ArithmeticOperatorNode("modulus",(ASTNode)$1.obj, (ASTNode)$3.obj, yyline, yycolumn)); 
		System.out.print("found a term (%)\n"); 
	}
| factor 
	{ 
		$$ = $1;
		System.out.print("found a term\n"); 
	}
| '-' factor 
	{ 
		$$ = new ParserVal(new ArithmeticOperatorNode("unary",(ASTNode)$2.obj, yyline, yycolumn));
		System.out.print("found a term (unary -)\n"); 
	}

factor : INTLITERAL { System.out.print("found a factor (int)\n"); }
| FLOATLITERAL { System.out.print("found a factor (float)\n"); }
| CHARLITERAL { System.out.print("found a factor (char)\n"); }
| STRINGLITERAL { System.out.print("found a factor (string)\n"); }
| BOOLLITERAL { System.out.print("found a factor (bool)\n"); }		// Do checking for true and false ignoreCase
| ID 					{ $$ = new ParserVal(new IDNode($1.sval, yyline, yycolumn)); }
| question_literal 		{ $$ = $1; }
| '(' expression ')'	{ $$ = $2; }  	
| function_call

function_call : ID '(' optional_factor_list ')' { System.out.print("found a function_call\n"); $$ = new ParserVal(new FunctionCallNode($1.sval, (FactorListNode)$3.obj, yyline,yycolumn); }

optional_factor_list : factor_list { System.out.print("found an optional_factor_list\n"); $$ = $1; }
| /* empty */ { $$= new ParserVal(null); }


factor_list: factor_list ',' factor { 
					System.out.print("found a factor_list\n"); 
					((ArrayList<FactorListNode>)$1.obj).add((FactorListNode)$3.obj);
					$$ = $1;
				    }
| factor  { 
		System.out.print("found a factor_list\n"); 
		$$ = new ParserVal(new FactorListNode((ASTNode)$1.obj, yyline, yycolumn)); 
	}

%%

/**************************************
* Variables
***************************************/
private Yylex lexer;
private static int yyline, yycolumn;

/* 
 * symbolsTable: Keys are the identifier names as found in the source code
 * Values are three-element arrays where the first element represents the type of the identifier
 * and the second element represents a generated variable name to be used in the target code
 * and the third element is the line which this symbol was declared first at
 * For more details see DeclarationNode
 */
public static HashMap<String, String[]> symbolsTable = new HashMap<String, String[]>();


public static HashMap<String, FunctionSymbolTableEntry> functionSymbolsTable = new HashMap<String, FunctionSymbolTableEntry>();



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
}

