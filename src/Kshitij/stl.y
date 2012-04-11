%{

import java.io.*;
import java.util.HashMap;

%}

%token  IF ELSE ID INT FLOAT QUESTION SET AND OR NOT NOTEQUAL CHAR BOOLEAN VOID EQUALEQUAL GE LE LT GT MOD BOOLLITERAL LOOP WHILE STRING INSERT RETURN LPARAN RPARAN LCURLY RCURLY COMMA SEMICOLON EQUAL DOLLAR LSQUARE RSQUARE COLON PLUS MINUS MULTIPLY DIVIDE CHARLITERAL STRINGLITERAL FLOATLITERAL INTLITERAL
 


%%


// ================================= //
// Grammar Definition 				 //
// ================================= //


program : function_list { System.out.print("found a program\n"); }

function_list : function_list function  { System.out.print("found function_list\n"); }
|/* empty*/ { System.out.print("found function_list\n"); }

function : return_type ID '(' optional_param_list ')' '{' statements '}' { System.out.print("found function\n"); }

optional_param_list : /*empty*/ { System.out.print("found optional_param_list\n"); }
| param_list { System.out.print("found optional_param_list\n"); }

param_list: param_list ',' parameter { System.out.print("found param_list\n"); }
| parameter { System.out.print("found param_list\n"); }

parameter : type ID { System.out.print("found a parameter\n"); }

statements : statements statement { System.out.print("found statements\n"); }
|/*  empty */ { System.out.print("found statements\n"); }

/* statement productions go here */

statement : type ID ';' { System.out.print("found statement (int i;)\n"); $$ = new ParserVal(new DeclarationNode((ASTNode)$1.obj, (ASTNode)$2.obj, yyline, yycolumn));  }
| type ID '=' expression ';' { System.out.print("found statement (int i=5;)\n"); $$ = new ParserVal(new AssignmentOperatorNode(new DeclarationNode((ASTNode)$1.obj, (ASTNode)$2.obj, yyline, yycolumn), (ASTNode)$4.obj, yyline, yycolumn)); }
| ID '=' expression ';' { System.out.print("found statement (i=5;)\n"); $$ = new ParserVal(new AssignmentOperatorNode((IDNode)$1.obj, (ASTNode)$2.obj, yyline, yycolumn)); }
| function_call ';' { System.out.print("found statement (func call)\n"); }
| loop { System.out.print("found statement (loop)\n"); }
| if_statement { System.out.print("found statement (if statement)\n"); }
| ID INSERT expression ';' { System.out.print("found statement (insert ques to set)\n"); }
| RETURN optional_expression ';' { System.out.print("found statement (return)\n"); }

type : INT { System.out.print("found type (int)\n"); }
| FLOAT { System.out.print("found type (float)\n"); }
| CHAR { System.out.print("found type (char)\n"); }
| BOOLEAN { System.out.print("found type (boolean)\n"); }
| STRING { System.out.print("found type (string)\n"); }
| QUESTION { System.out.print("found type (question)\n"); }
| SET { System.out.print("found type (set)\n"); }

return_type : type | VOID { System.out.print("found return_type\n"); }

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


question_literal : '$' expression ':' expression '[' answer_choices ']' '$' { System.out.print("found question_literal\n"); }

answer_choices : answer_choices ',' answer_choice { System.out.print("found answer_choices\n"); }
| answer_choice { System.out.print("found answer_choices\n"); }

answer_choice : expression ':' expression { System.out.print("found an answer_choice\n"); }


expression : expression AND not_boolean_operand { System.out.print("found an expression (and)\n"); }
| expression OR not_boolean_operand { System.out.print("found an expression (or)\n"); }
| not_boolean_operand { System.out.print("found an expression (not boolean)\n"); }

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
		$$ = new ParserVal(new RelationalOperatorNode("addition",(ASTNode)$1.obj, (ASTNode)$3.obj, yyline, yycolumn));
		System.out.print("found a relational_operand (+)\n"); 
	}
| relational_operand '-' term 
	{ 
		$$ = new ParserVal(new RelationalOperatorNode("subtraction",(ASTNode)$1.obj, (ASTNode)$3.obj, yyline, yycolumn));
		System.out.print("found a relational_operand (-)\n"); 
	}
| term 
	{ 
	 	$$ = $1;
	 	System.out.print("found a relational_operand\n"); 
	}

term : term '*' factor { System.out.print("found a term (*)\n"); }
| term '/' factor { System.out.print("found a term (/)\n"); }
| term MOD factor { System.out.print("found a term (%)\n"); }
| factor { System.out.print("found a term\n"); }
| '-' factor { System.out.print("found a term (unary -)\n"); }

factor : INTLITERAL { System.out.print("found a factor (int)\n"); }
| FLOATLITERAL { System.out.print("found a factor (float)\n"); }
| CHARLITERAL { System.out.print("found a factor (char)\n"); }
| STRINGLITERAL { System.out.print("found a factor (string)\n"); }
| BOOLLITERAL { System.out.print("found a factor (bool)\n"); }		// Do checking for true and false ignoreCase
| ID
| question_literal
| '(' expression ')' 
| function_call

function_call : ID '(' optional_factor_list ')' { System.out.print("found a function_call\n"); }
optional_factor_list : factor_list { System.out.print("found an optional_factor_list\n"); }
| /* empty */

factor_list: factor_list ',' factor { 
					System.out.print("found a factor_list\n"); 
					$$ = new ParserVal(((FactorListNode)$1.obj).add($3.obj)); 
				    }
| factor  { 
		System.out.print("found a factor_list\n"); 
		$$ = new ParserVal(new FactorListNode((ASTNode)$1.obj)); 
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
public static HashMap<String, String[]> symbolsTable;


public static HashMap<String, String[]> functionSymbolsTable;



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
