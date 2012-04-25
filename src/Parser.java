//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "stl.y"

import java.io.*;
import java.util.*;

//#line 22 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IF=257;
public final static short ELSE=258;
public final static short ID=259;
public final static short INT=260;
public final static short FLOAT=261;
public final static short QUESTION=262;
public final static short SET=263;
public final static short AND=264;
public final static short OR=265;
public final static short NOT=266;
public final static short NOTEQUAL=267;
public final static short CHAR=268;
public final static short BOOLEAN=269;
public final static short VOID=270;
public final static short EQUALEQUAL=271;
public final static short GE=272;
public final static short LE=273;
public final static short LT=274;
public final static short GT=275;
public final static short MOD=276;
public final static short BOOLLITERAL=277;
public final static short LOOP=278;
public final static short WHILE=279;
public final static short STRING=280;
public final static short INSERT=281;
public final static short RETURN=282;
public final static short LPARAN=283;
public final static short RPARAN=284;
public final static short LCURLY=285;
public final static short RCURLY=286;
public final static short COMMA=287;
public final static short SEMICOLON=288;
public final static short EQUAL=289;
public final static short DOLLAR=290;
public final static short LSQUARE=291;
public final static short RSQUARE=292;
public final static short COLON=293;
public final static short PLUS=294;
public final static short MINUS=295;
public final static short MULTIPLY=296;
public final static short DIVIDE=297;
public final static short CHARLITERAL=298;
public final static short STRINGLITERAL=299;
public final static short FLOATLITERAL=300;
public final static short INTLITERAL=301;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    3,    5,    5,    7,    7,
    6,    6,    8,    9,    9,    9,    9,    9,    9,    9,
    9,   10,   10,   10,   10,   10,   10,   10,    4,    4,
   15,   15,   14,   14,   13,   16,   17,   17,   18,   11,
   11,   11,   19,   19,   20,   20,   20,   21,   21,   21,
   21,   21,   22,   22,   22,   23,   23,   23,   23,   23,
   24,   24,   24,   24,   24,   24,   24,   24,   24,   12,
   25,   25,   26,   26,
};
final static short yylen[] = {                            2,
    1,    1,    0,    2,    1,    8,    0,    1,    3,    1,
    2,    0,    2,    2,    4,    4,    2,    1,    1,    4,
    3,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    0,    7,   11,    8,    8,    3,    1,    3,    3,
    3,    1,    2,    1,    3,    3,    1,    3,    3,    3,
    3,    1,    3,    3,    1,    3,    3,    3,    1,    2,
    1,    1,    1,    1,    1,    1,    1,    3,    1,    4,
    1,    0,    3,    1,
};
final static short yydefred[] = {                         0,
   22,   23,   27,   28,   24,   25,   30,   26,    0,    1,
    0,    5,    0,   29,    4,    0,    0,    0,    0,   10,
    0,    0,    0,   13,   12,    9,    0,    0,    0,    0,
    0,    6,    0,   11,    0,   18,   19,    0,    0,    0,
    0,    0,    0,    0,   65,   63,   64,   62,   61,    0,
    0,    0,    0,   69,    0,   67,   42,    0,    0,    0,
    0,   59,   14,    0,   17,    0,    0,   74,    0,    0,
    0,    0,   43,    0,    0,   60,    0,    0,   21,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   20,   70,    0,   16,    0,   68,    0,   40,
   41,    0,    0,    0,    0,    0,    0,    0,    0,   58,
   56,   57,   15,   12,   73,    0,    0,    0,   12,    0,
    0,    0,    0,    0,   38,    0,   35,    0,    0,    0,
   12,    0,   37,   36,    0,   34,
};
final static short yydgoto[] = {                          9,
   10,   11,   12,   13,   18,   27,   19,   33,   34,   21,
  123,   54,   36,   37,   55,   56,  124,  125,   57,   58,
   59,   60,   61,   62,   69,   70,
};
final static short yysindex[] = {                       277,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  277,    0, -245,    0,    0,   -3, -215,   37,   20,    0,
 -218,  -43, -215,    0,    0,    0,  155,   52,  -39, -184,
  -36,    0,   15,    0,   43,    0,    0,  -36,  -36,  -24,
  -36,   64,   72,  -36,    0,    0,    0,    0,    0,  -36,
  -36,  -24, -175,    0,   57,    0,    0, -235, -212,  -10,
  -40,    0,    0,  -36,    0,  -33,  -48,    0,   81,   79,
  -46,  -36,    0,  -21,  -55,    0,  -36,  -36,    0,  -30,
  -30,  -30,  -30,  -30,  -30,  -30,  -30,  -24,  -24,  -24,
  -19,    1,    0,    0,  -24,    0,    8,    0,  -36,    0,
    0, -212, -212,  -10,  -10,  -10,  -10,  -40,  -40,    0,
    0,    0,    0,    0,    0,    2,  -70,  179,    0,  -36,
 -132,  203,  -53,  -27,    0,    5,    0,  -36,  -36,   99,
    0, -175,    0,    0,  227,    0,
};
final static short yyrindex[] = {                       136,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  137,    0,    0,    0,    0,    0,   93,    0,   97,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   87,    0,    0,    0,    0,    0,    0,    0,    0,  106,
    0,    0,  -16,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   89,    0,    0,    0,    0,  146,  110,   50,
   14,    0,    0,    0,    0,    0,    0,    0,    0,  108,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  122,  134,   62,   74,   86,   98,   26,   38,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  251,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -25,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  129,    0,    0,  -75,    0,    7,    0,   23,
  484,  132,    0,    0,    0,    0,    0,   21,  -26,    0,
   30,   16,   27,   -2,    0,    0,
};
final static int YYTABLESIZE=612;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         51,
   40,   89,   99,   50,  128,   51,   90,   92,   52,   50,
   93,   51,   96,   16,   52,   50,  129,   73,   39,   98,
  120,   41,   14,   20,   66,   66,   66,   66,   66,   26,
   66,   80,   86,   14,   87,   81,   17,   68,  118,  113,
   24,   66,   66,  122,    1,    2,    3,    4,  116,   76,
  100,  101,    5,    6,   55,  135,   55,   55,   55,   82,
   83,   84,   85,   23,    8,  130,   53,   39,   53,   53,
   53,   55,   55,   63,   66,   64,   66,   22,   54,   25,
   54,   54,   54,   53,   53,  110,  111,  112,   77,   78,
   52,   38,  115,   52,   42,   54,   54,  104,  105,  106,
  107,   65,   50,   72,   55,   50,   55,   52,   52,  102,
  103,   40,  108,  109,   51,   79,   53,   51,   53,   50,
   50,   94,   95,  114,  119,  126,   48,  131,   54,   48,
   54,   51,   51,    7,  134,    3,    2,    8,   49,   15,
   52,   49,   52,   48,   48,   32,   72,   31,   71,  133,
   47,    0,   50,   47,   50,   49,   49,    0,   35,    0,
    0,    0,   46,    0,   51,   46,   51,   47,   47,    0,
    0,    0,    0,    0,   45,    0,   48,   45,   48,   46,
   46,    0,    0,    0,    0,    0,   44,    0,   49,   44,
   49,   45,   45,   77,   78,    0,    0,    0,    0,    0,
   47,    0,   47,   44,   44,    0,    0,    0,   77,   78,
   77,   78,   46,    0,   46,   77,   78,   77,   78,    0,
    0,    0,   43,    0,   45,    0,   45,    0,   43,   44,
   77,   78,    0,    0,   43,   88,   44,    0,   44,    0,
   45,   39,   77,   78,   77,   78,   45,   66,   66,   35,
   66,    0,   45,   35,   66,   66,   66,   66,   66,   66,
    0,   46,   47,   48,   49,    0,   35,   46,   47,   48,
   49,   77,   78,   46,   47,   48,   49,   55,   55,   32,
   55,    0,    0,    0,   55,   55,   55,   55,   55,   53,
   53,    0,   53,    0,    0,    0,   53,   53,   53,   53,
   53,   54,   54,  121,   54,    0,    0,    0,   54,   54,
   54,   54,   54,   52,   52,    0,   52,    0,    0,    0,
   52,   52,   52,   52,   52,   50,   50,  127,   50,    0,
    0,    0,   50,   50,   50,   50,   50,   51,   51,    0,
   51,    0,    0,    0,   51,   51,   51,   51,   51,   48,
   48,  136,   48,    0,    0,    0,   48,   48,   48,   48,
   48,   49,   49,    0,   49,    0,    0,    0,   49,   49,
   49,   49,   49,   47,   47,   33,   47,    0,    0,    0,
   47,    0,    0,    0,    0,   46,   46,    0,   46,    0,
    0,    0,   46,    0,    0,    0,    0,   45,   45,    0,
   45,    0,    0,    0,   45,    0,    0,    0,    0,   44,
   44,   28,    0,   29,    1,    2,    3,    4,    0,    0,
    0,    0,    5,    6,    0,    0,    0,    0,    0,    0,
    0,    0,   30,    0,    8,   28,   31,   29,    1,    2,
    3,    4,    0,    0,    0,    0,    5,    6,    0,    0,
    0,    0,    0,    0,    0,    0,   30,    0,    8,   28,
   31,   29,    1,    2,    3,    4,    0,    0,    0,    0,
    5,    6,    0,    0,    0,    0,    0,    0,    0,    0,
   30,    0,    8,   28,   31,   29,    1,    2,    3,    4,
    0,    0,    0,    0,    5,    6,    0,    0,    0,    0,
    0,    0,    0,    0,   30,    0,    8,   33,   31,   33,
   33,   33,   33,   33,   53,    0,    0,    0,   33,   33,
    0,   66,   67,    0,   71,    0,    0,    0,   33,    0,
   33,    0,   33,   74,   75,    0,    1,    2,    3,    4,
    0,    0,    0,    0,    5,    6,    7,   91,    0,    0,
    0,    0,    0,    0,    0,   97,    8,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  117,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  132,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         36,
   40,   42,   58,   40,   58,   36,   47,   41,   45,   40,
   59,   36,   59,  259,   45,   40,   44,   44,   44,   41,
   91,   61,    0,   17,   41,   42,   43,   44,   45,   23,
   47,  267,   43,   11,   45,  271,   40,   40,  114,   59,
  259,   58,   59,  119,  260,  261,  262,  263,   41,   52,
   77,   78,  268,  269,   41,  131,   43,   44,   45,  272,
  273,  274,  275,   44,  280,   93,   41,   93,   43,   44,
   45,   58,   59,   59,   91,   61,   93,   41,   41,  123,
   43,   44,   45,   58,   59,   88,   89,   90,  264,  265,
   41,   40,   95,   44,  279,   58,   59,   82,   83,   84,
   85,   59,   41,   40,   91,   44,   93,   58,   59,   80,
   81,   40,   86,   87,   41,   59,   91,   44,   93,   58,
   59,   41,   44,  123,  123,  258,   41,  123,   91,   44,
   93,   58,   59,   41,   36,    0,    0,   41,   41,   11,
   91,   44,   93,   58,   59,   59,   41,   59,   41,  129,
   41,   -1,   91,   44,   93,   58,   59,   -1,   27,   -1,
   -1,   -1,   41,   -1,   91,   44,   93,   58,   59,   -1,
   -1,   -1,   -1,   -1,   41,   -1,   91,   44,   93,   58,
   59,   -1,   -1,   -1,   -1,   -1,   41,   -1,   91,   44,
   93,   58,   59,  264,  265,   -1,   -1,   -1,   -1,   -1,
   91,   -1,   93,   58,   59,   -1,   -1,   -1,  264,  265,
  264,  265,   91,   -1,   93,  264,  265,  264,  265,   -1,
   -1,   -1,  259,   -1,   91,   -1,   93,   -1,  259,  266,
  264,  265,   -1,   -1,  259,  276,   91,   -1,   93,   -1,
  277,  281,  264,  265,  264,  265,  277,  264,  265,  118,
  267,   -1,  277,  122,  271,  272,  273,  274,  275,  276,
   -1,  298,  299,  300,  301,   -1,  135,  298,  299,  300,
  301,  264,  265,  298,  299,  300,  301,  264,  265,  125,
  267,   -1,   -1,   -1,  271,  272,  273,  274,  275,  264,
  265,   -1,  267,   -1,   -1,   -1,  271,  272,  273,  274,
  275,  264,  265,  125,  267,   -1,   -1,   -1,  271,  272,
  273,  274,  275,  264,  265,   -1,  267,   -1,   -1,   -1,
  271,  272,  273,  274,  275,  264,  265,  125,  267,   -1,
   -1,   -1,  271,  272,  273,  274,  275,  264,  265,   -1,
  267,   -1,   -1,   -1,  271,  272,  273,  274,  275,  264,
  265,  125,  267,   -1,   -1,   -1,  271,  272,  273,  274,
  275,  264,  265,   -1,  267,   -1,   -1,   -1,  271,  272,
  273,  274,  275,  264,  265,  125,  267,   -1,   -1,   -1,
  271,   -1,   -1,   -1,   -1,  264,  265,   -1,  267,   -1,
   -1,   -1,  271,   -1,   -1,   -1,   -1,  264,  265,   -1,
  267,   -1,   -1,   -1,  271,   -1,   -1,   -1,   -1,  264,
  265,  257,   -1,  259,  260,  261,  262,  263,   -1,   -1,
   -1,   -1,  268,  269,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  278,   -1,  280,  257,  282,  259,  260,  261,
  262,  263,   -1,   -1,   -1,   -1,  268,  269,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  278,   -1,  280,  257,
  282,  259,  260,  261,  262,  263,   -1,   -1,   -1,   -1,
  268,  269,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  278,   -1,  280,  257,  282,  259,  260,  261,  262,  263,
   -1,   -1,   -1,   -1,  268,  269,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  278,   -1,  280,  257,  282,  259,
  260,  261,  262,  263,   31,   -1,   -1,   -1,  268,  269,
   -1,   38,   39,   -1,   41,   -1,   -1,   -1,  278,   -1,
  280,   -1,  282,   50,   51,   -1,  260,  261,  262,  263,
   -1,   -1,   -1,   -1,  268,  269,  270,   64,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   72,  280,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   99,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  128,
};
}
final static short YYFINAL=9;
final static short YYMAXTOKEN=301;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,"'$'",null,null,null,"'('","')'","'*'","'+'",
"','","'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'",null,"'='",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"IF","ELSE","ID","INT","FLOAT",
"QUESTION","SET","AND","OR","NOT","NOTEQUAL","CHAR","BOOLEAN","VOID",
"EQUALEQUAL","GE","LE","LT","GT","MOD","BOOLLITERAL","LOOP","WHILE","STRING",
"INSERT","RETURN","LPARAN","RPARAN","LCURLY","RCURLY","COMMA","SEMICOLON",
"EQUAL","DOLLAR","LSQUARE","RSQUARE","COLON","PLUS","MINUS","MULTIPLY","DIVIDE",
"CHARLITERAL","STRINGLITERAL","FLOATLITERAL","INTLITERAL",
};
final static String yyrule[] = {
"$accept : program",
"program : optional_function_list",
"optional_function_list : function_list",
"optional_function_list :",
"function_list : function_list function",
"function_list : function",
"function : return_type ID '(' optional_param_list ')' '{' statements '}'",
"optional_param_list :",
"optional_param_list : param_list",
"param_list : param_list ',' declaration",
"param_list : declaration",
"statements : statements statement",
"statements :",
"declaration : type ID",
"statement : declaration ';'",
"statement : declaration '=' expression ';'",
"statement : ID '=' expression ';'",
"statement : function_call ';'",
"statement : loop",
"statement : if_statement",
"statement : ID INSERT expression ';'",
"statement : RETURN optional_expression ';'",
"type : INT",
"type : FLOAT",
"type : CHAR",
"type : BOOLEAN",
"type : STRING",
"type : QUESTION",
"type : SET",
"return_type : type",
"return_type : VOID",
"optional_expression : expression",
"optional_expression :",
"if_statement : IF '(' expression ')' '{' statements '}'",
"if_statement : IF '(' expression ')' '{' statements '}' ELSE '{' statements '}'",
"loop : LOOP WHILE '(' expression ')' '{' statements '}'",
"question_literal : '$' expression ':' expression '[' answer_choices ']' '$'",
"answer_choices : answer_choices ',' answer_choice",
"answer_choices : answer_choice",
"answer_choice : expression ':' expression",
"expression : expression AND not_boolean_operand",
"expression : expression OR not_boolean_operand",
"expression : not_boolean_operand",
"not_boolean_operand : NOT not_boolean_operand",
"not_boolean_operand : boolean_operand",
"boolean_operand : boolean_operand EQUALEQUAL equality_operand",
"boolean_operand : boolean_operand NOTEQUAL equality_operand",
"boolean_operand : equality_operand",
"equality_operand : equality_operand LT relational_operand",
"equality_operand : equality_operand GT relational_operand",
"equality_operand : equality_operand GE relational_operand",
"equality_operand : equality_operand LE relational_operand",
"equality_operand : relational_operand",
"relational_operand : relational_operand '+' term",
"relational_operand : relational_operand '-' term",
"relational_operand : term",
"term : term '*' factor",
"term : term '/' factor",
"term : term MOD factor",
"term : factor",
"term : '-' factor",
"factor : INTLITERAL",
"factor : FLOATLITERAL",
"factor : CHARLITERAL",
"factor : STRINGLITERAL",
"factor : BOOLLITERAL",
"factor : ID",
"factor : question_literal",
"factor : '(' expression ')'",
"factor : function_call",
"function_call : ID '(' optional_factor_list ')'",
"optional_factor_list : factor_list",
"optional_factor_list :",
"factor_list : factor_list ',' factor",
"factor_list : factor",
};

//#line 278 "stl.y"

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

  ArrayList<String> paraList1 = new ArrayList<String>();
  paraList1.add("string");
  functionSymbolsTable.put("print", new FunctionSymbolTableEntry("print", "_smartestfunction_print" , "void", paraList1));

  ArrayList<String> paraList2 = new ArrayList<String>(); 
  paraList2.add("string");
  functionSymbolsTable.put("printvar", new FunctionSymbolTableEntry("printVar", "_smartestfunction_printVar" , "void", paraList2));

  ArrayList<String> paraList3 = new ArrayList<String>();
  paraList3.add("");
  functionSymbolsTable.put("readline", new FunctionSymbolTableEntry("readLine", "_smartestfunction_readLine" , "string", paraList3));

  ArrayList<String> paraList4 = new ArrayList<String>();
  paraList4.add("string");
  paraList4.add("string");
  paraList4.add("string");
  paraList4.add("string");
  functionSymbolsTable.put("load", new FunctionSymbolTableEntry("load", "_smartestfunction_load" , "set", paraList4));


  // Add 2 more pre-defined functions. 


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

//#line 589 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 21 "stl.y"
{
        System.out.print("found a program\n");
        yyval = new ParserVal(new ProgramNode((ArrayList<ASTNode>)val_peek(0).obj, line, column));
        try
        {
        	((ASTNode)yyval.obj).checkSemantics();
        	System.out.println("\nCompiled successfully");
        }
        catch (Exception e)
        {
        	System.out.println(e.getMessage());
        	e.printStackTrace();
		System.exit(1);
        }
        ((ASTNode)yyval.obj).generateCode();
}
break;
case 2:
//#line 38 "stl.y"
{ System.out.println("found optional_function_list\n"); yyval = val_peek(0); }
break;
case 3:
//#line 39 "stl.y"
{ System.out.println("found optional_function_list\n"); yyval = new ParserVal(null); }
break;
case 4:
//#line 41 "stl.y"
{ System.out.print("found function_list\n"); ((ArrayList<ASTNode>)val_peek(1).obj).add((ASTNode)val_peek(0).obj); yyval = val_peek(1); }
break;
case 5:
//#line 42 "stl.y"
{ System.out.print("found function_list\n"); ArrayList<ASTNode> flist = new ArrayList<ASTNode>(); flist.add((ASTNode)val_peek(0).obj); yyval = new ParserVal(flist); }
break;
case 6:
//#line 45 "stl.y"
{
        System.out.print("found function\n");
	try
	{
	        yyval = new ParserVal(new FunctionNode(val_peek(7).sval, val_peek(6).sval, (ArrayList<ASTNode>)val_peek(4).obj, (ASTNode)val_peek(1).obj, line, column));
	}
	catch (Exception e)
        {
        	System.out.println(e.getMessage());
        	e.printStackTrace();
		System.exit(1);
        }
}
break;
case 7:
//#line 59 "stl.y"
{ System.out.print("found optional_param_list\n"); yyval = new ParserVal(null); }
break;
case 8:
//#line 60 "stl.y"
{ System.out.print("found optional_param_list\n"); yyval = val_peek(0); }
break;
case 9:
//#line 62 "stl.y"
{ System.out.print("found param_list\n"); ((ArrayList<ASTNode>)val_peek(2).obj).add((ASTNode)val_peek(0).obj); }
break;
case 10:
//#line 63 "stl.y"
{ System.out.print("found param_list\n"); ArrayList<ASTNode> plist = new ArrayList<ASTNode>(); plist.add((ASTNode)val_peek(0).obj); yyval = new ParserVal(plist); }
break;
case 11:
//#line 65 "stl.y"
{ System.out.print("found statements\n"); ((StatementsNode)val_peek(1).obj).addChild((ASTNode)val_peek(0).obj); yyval = val_peek(1);}
break;
case 12:
//#line 66 "stl.y"
{ System.out.print("found statements\n"); yyval = new ParserVal(new StatementsNode(line, column)); }
break;
case 13:
//#line 70 "stl.y"
{ yyval = new ParserVal(new DeclarationNode(val_peek(1).sval, new IDNode(val_peek(0).sval, true, line, column), line, column)); }
break;
case 14:
//#line 72 "stl.y"
{ System.out.print("found statement (int i;)\n"); ((DeclarationNode)val_peek(1).obj).setIsStatement(true); yyval = val_peek(1); }
break;
case 15:
//#line 73 "stl.y"
{ System.out.print("found statement (int i=5;)\n"); yyval = new ParserVal(new AssignmentOperatorNode((DeclarationNode)val_peek(3).obj, (ASTNode)val_peek(1).obj, line, column)); }
break;
case 16:
//#line 74 "stl.y"
{ System.out.print("found statement (i=5;)\n"); yyval = new ParserVal(new AssignmentOperatorNode(new IDNode(val_peek(3).sval, false, line, column), (ASTNode)val_peek(1).obj, line, column)); }
break;
case 17:
//#line 75 "stl.y"
{ System.out.print("found statement (func call)\n"); }
break;
case 18:
//#line 76 "stl.y"
{ System.out.print("found statement (loop)\n"); }
break;
case 19:
//#line 77 "stl.y"
{ System.out.print("found statement (if statement)\n"); }
break;
case 20:
//#line 78 "stl.y"
{ System.out.print("found statement (insert ques to set)\n"); yyval = new ParserVal(new InsertOperatorNode(new IDNode(val_peek(3).sval, false, line, column), (ASTNode)val_peek(1).obj, line, column)); }
break;
case 21:
//#line 79 "stl.y"
{ System.out.print("found statement (return)\n"); yyval = new ParserVal(new ReturnNode(currentReturnType, (ASTNode)val_peek(1).obj, line, column)); }
break;
case 22:
//#line 81 "stl.y"
{ System.out.print("found type (int)\n"); yyval = new ParserVal("int"); }
break;
case 23:
//#line 82 "stl.y"
{ System.out.print("found type (float)\n"); yyval = new ParserVal("double"); }
break;
case 24:
//#line 83 "stl.y"
{ System.out.print("found type (char)\n"); yyval = new ParserVal("char"); }
break;
case 25:
//#line 84 "stl.y"
{ System.out.print("found type (boolean)\n"); yyval = new ParserVal("boolean"); }
break;
case 26:
//#line 85 "stl.y"
{ System.out.print("found type (string)\n"); yyval = new ParserVal("string"); }
break;
case 27:
//#line 86 "stl.y"
{ System.out.print("found type (question)\n"); yyval = new ParserVal("question"); }
break;
case 28:
//#line 87 "stl.y"
{ System.out.print("found type (set)\n"); yyval = new ParserVal("set"); }
break;
case 29:
//#line 90 "stl.y"
{ 
		yyval = new ParserVal(val_peek(0).sval);
		Parser.currentReturnType = val_peek(0).sval;
	}
break;
case 30:
//#line 95 "stl.y"
{ 
		System.out.print("found return_type\n"); 
		yyval = new ParserVal("void");
		Parser.currentReturnType = "void";
	}
break;
case 31:
//#line 101 "stl.y"
{ System.out.print("found optional_expression\n"); yyval = val_peek(0); }
break;
case 32:
//#line 102 "stl.y"
{ System.out.print("found optional_expression\n"); yyval = new ParserVal(null);}
break;
case 33:
//#line 108 "stl.y"
{ 
		yyval = new ParserVal(new IfStatementNode((ASTNode)val_peek(4).obj, (ASTNode)val_peek(1).obj, line, column));
		System.out.print("found if_statement\n"); 
	}
break;
case 34:
//#line 113 "stl.y"
{
		yyval = new ParserVal(new IfStatementNode((ASTNode)val_peek(8).obj, (ASTNode)val_peek(5).obj, (ASTNode)val_peek(1).obj, line, column));
	 	System.out.print("found if_statement\n"); 
	}
break;
case 35:
//#line 131 "stl.y"
{
	yyval = new ParserVal(new LoopNode((ASTNode)val_peek(4).obj, (ASTNode)val_peek(1).obj, line, column));
	System.out.print("found loop\n"); 
}
break;
case 36:
//#line 137 "stl.y"
{ System.out.print("found question_literal\n"); yyval = new ParserVal(new QuestionLiteralNode((ASTNode)val_peek(6).obj, (ASTNode)val_peek(4).obj, (ASTNode)val_peek(2).obj, line, column)); }
break;
case 37:
//#line 139 "stl.y"
{ System.out.print("found answer_choices\n"); ((AnswerChoicesListNode)val_peek(2).obj).addAnswer((ASTNode)val_peek(0).obj); yyval = val_peek(2); }
break;
case 38:
//#line 140 "stl.y"
{ System.out.print("found answer_choices\n"); yyval = new ParserVal(new AnswerChoicesListNode(line, column)); ((AnswerChoicesListNode)yyval.obj).addAnswer((ASTNode)val_peek(0).obj); }
break;
case 39:
//#line 142 "stl.y"
{ System.out.print("found an answer_choice\n"); yyval = new ParserVal(new AnswerChoiceNode((ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column)); }
break;
case 40:
//#line 146 "stl.y"
{ 	
		System.out.print("found an expression (and)\n"); yyval = new ParserVal(new ExpressionNode("and",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column)); 
	}
break;
case 41:
//#line 150 "stl.y"
{ 
		System.out.print("found an expression (or)\n"); yyval = new ParserVal(new ExpressionNode("or",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column)); 
	}
break;
case 42:
//#line 154 "stl.y"
{ 
		System.out.print("found an expression (not boolean)\n"); yyval = val_peek(0);
	}
break;
case 43:
//#line 159 "stl.y"
{ 
		yyval = new ParserVal(new NotBooleanOperandNode("not",(ASTNode)val_peek(0).obj, line, column));
		System.out.print("found a not_boolean_operand\n"); 
	}
break;
case 44:
//#line 164 "stl.y"
{ 
		/*$$ = new ParserVal(new NotBooleanOperandNode((ASTNode)$1.obj, line, column));*/
		yyval = val_peek(0);		
		System.out.print("found a not_boolean_operand\n"); 
	}
break;
case 45:
//#line 171 "stl.y"
{ 
		yyval = new ParserVal(new BooleanOperandNode("equal",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
		System.out.print("found a boolean_operand\n"); 
	}
break;
case 46:
//#line 176 "stl.y"
{
		yyval = new ParserVal(new BooleanOperandNode("not_equal",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
		System.out.print("found a boolean_operand\n"); 
	}
break;
case 47:
//#line 181 "stl.y"
{ 	
		yyval = val_peek(0);
		System.out.print("found a boolean_operand\n"); 
	}
break;
case 48:
//#line 187 "stl.y"
{
					System.out.print("found a equality_operand (LT)\n");
					yyval = new ParserVal(new RelationalOperatorNode("LT", (ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
				}
break;
case 49:
//#line 192 "stl.y"
{
					System.out.print("found a equality_operand (GT)\n");
					yyval = new ParserVal(new RelationalOperatorNode("GT", (ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
				}
break;
case 50:
//#line 197 "stl.y"
{
					System.out.print("found a equality_operand (GE)\n");
					yyval = new ParserVal(new RelationalOperatorNode("GE", (ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
				}
break;
case 51:
//#line 202 "stl.y"
{
					System.out.print("found a equality_operand (LE)\n");
					yyval = new ParserVal(new RelationalOperatorNode("LE", (ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
				}
break;
case 52:
//#line 206 "stl.y"
{ System.out.print("found a equality_operand\n"); yyval = val_peek(0); }
break;
case 53:
//#line 209 "stl.y"
{ 
		yyval = new ParserVal(new ArithmeticOperatorNode("addition",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
		System.out.print("found a relational_operand (+)\n"); 
	}
break;
case 54:
//#line 214 "stl.y"
{ 
		yyval = new ParserVal(new ArithmeticOperatorNode("subtraction",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
		System.out.print("found a relational_operand (-)\n"); 
	}
break;
case 55:
//#line 219 "stl.y"
{ 
	 	yyval = val_peek(0);
	 	System.out.print("found a relational_operand\n"); 
	}
break;
case 56:
//#line 225 "stl.y"
{
		yyval = new ParserVal(new ArithmeticOperatorNode("multiplication",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
		System.out.print("found a term (*)\n"); 
	}
break;
case 57:
//#line 230 "stl.y"
{
		yyval = new ParserVal(new ArithmeticOperatorNode("division",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column)); 
		System.out.print("found a term (/)\n"); 
	}
break;
case 58:
//#line 235 "stl.y"
{
		yyval = new ParserVal(new ArithmeticOperatorNode("modulus",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column)); 
		System.out.print("found a term (%)\n"); 
	}
break;
case 59:
//#line 240 "stl.y"
{ 
		yyval = val_peek(0);
		System.out.print("found a term\n"); 
	}
break;
case 60:
//#line 245 "stl.y"
{ 
		yyval = new ParserVal(new ArithmeticOperatorNode("unary",(ASTNode)val_peek(0).obj, line, column));
		System.out.print("found a term (unary -)\n"); 
	}
break;
case 61:
//#line 251 "stl.y"
{ System.out.print("found a factor (int)\n"); yyval = new ParserVal(new LiteralNode("int",(Object) val_peek(0).ival, line, column)); }
break;
case 62:
//#line 252 "stl.y"
{ System.out.print("found a factor (float)\n"); yyval = new ParserVal(new LiteralNode("double", (Object) val_peek(0).dval, line, column)); }
break;
case 63:
//#line 253 "stl.y"
{ System.out.print("found a factor (char)\n"); yyval = new ParserVal(new LiteralNode("char", (Object) val_peek(0).ival,line, column)); }
break;
case 64:
//#line 254 "stl.y"
{ System.out.print("found a factor (string)\n"); yyval = new ParserVal(new LiteralNode("string", (Object) val_peek(0).sval, line, column));}
break;
case 65:
//#line 255 "stl.y"
{ System.out.print("found a factor (bool)\n"); yyval = new ParserVal(new LiteralNode("boolean", (Object) val_peek(0).sval,line, column)); }
break;
case 66:
//#line 256 "stl.y"
{ yyval = new ParserVal(new IDNode(val_peek(0).sval, false, line, column)); }
break;
case 67:
//#line 257 "stl.y"
{ yyval = val_peek(0); }
break;
case 68:
//#line 258 "stl.y"
{ yyval = val_peek(1); }
break;
case 69:
//#line 259 "stl.y"
{ yyval = val_peek(0); }
break;
case 70:
//#line 261 "stl.y"
{ System.out.print("found a function_call\n"); yyval = new ParserVal(new FunctionCallNode(val_peek(3).sval, (FactorListNode)val_peek(1).obj, line,column)); }
break;
case 71:
//#line 263 "stl.y"
{ System.out.print("found an optional_factor_list\n"); yyval = val_peek(0); }
break;
case 72:
//#line 264 "stl.y"
{ yyval= new ParserVal(null); }
break;
case 73:
//#line 267 "stl.y"
{ 
					System.out.print("found a factor_list\n");
                                        ((FactorListNode)val_peek(2).obj).addChild((ASTNode)val_peek(0).obj); 
					yyval = val_peek(2);
				    }
break;
case 74:
//#line 272 "stl.y"
{ 
		System.out.print("found a factor_list\n"); 
		yyval = new ParserVal(new FactorListNode((ASTNode)val_peek(0).obj, line, column)); 
	}
break;
//#line 1142 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
