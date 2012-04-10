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
import java.util.HashMap;

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
    0,    1,    1,    2,    4,    4,    6,    6,    7,    5,
    5,    9,    9,    9,    9,    9,    9,    9,    9,    8,
    8,    8,    8,    8,    8,    8,    3,    3,   14,   14,
   13,   13,   12,   15,   16,   16,   17,   10,   10,   10,
   18,   18,   19,   19,   19,   20,   20,   20,   20,   20,
   21,   21,   21,   22,   22,   22,   22,   22,   23,   23,
   23,   23,   23,   23,   23,   23,   23,   11,   24,   24,
   25,   25,
};
final static short yylen[] = {                            2,
    1,    2,    0,    8,    0,    1,    3,    1,    2,    2,
    0,    3,    5,    4,    2,    1,    1,    4,    3,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    0,
    7,   11,    8,    8,    3,    1,    3,    3,    3,    1,
    2,    1,    3,    3,    1,    3,    3,    3,    3,    1,
    3,    3,    1,    3,    3,    3,    1,    2,    1,    1,
    1,    1,    1,    1,    1,    3,    1,    4,    1,    0,
    3,    1,
};
final static short yydefred[] = {                         3,
    0,    0,   20,   21,   25,   26,   22,   23,   28,   24,
    2,    0,   27,    0,    0,    0,    0,    8,    0,    0,
    0,    9,   11,    7,    0,    0,    0,    0,    0,    4,
    0,   10,    0,   16,   17,    0,    0,    0,    0,    0,
    0,    0,   63,   61,   62,   60,   59,    0,    0,    0,
    0,   67,    0,   65,   40,    0,    0,    0,    0,   57,
    0,   15,    0,    0,   72,    0,    0,    0,    0,   41,
    0,    0,   58,    0,    0,   19,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   12,    0,    0,
   18,   68,    0,   14,    0,   66,    0,   38,   39,    0,
    0,    0,    0,    0,    0,    0,    0,   56,   54,   55,
    0,   11,   71,    0,    0,   13,    0,   11,    0,    0,
    0,    0,    0,   36,    0,   33,    0,    0,    0,   11,
    0,   35,   34,    0,   32,
};
final static short yydgoto[] = {                          1,
    2,   11,   12,   16,   25,   17,   18,   31,   32,  122,
   52,   34,   35,   53,   54,  123,  124,   55,   56,   57,
   58,   59,   60,   66,   67,
};
final static short yysindex[] = {                         0,
    0,  276,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -218,    0,  -22, -224,   19,   21,    0, -172,  -34,
 -224,    0,    0,    0,  155,   53,  -39, -184,  -36,    0,
 -159,    0,   42,    0,    0,  -36,  -36,  -24,  -36,   64,
   70,  -36,    0,    0,    0,    0,    0,  -36,  -36,  -24,
 -241,    0,   52,    0,    0, -237, -222,   43,  -40,    0,
   17,    0,  -33,  -48,    0,   61,   68,  -46,  -36,    0,
  -21,  -55,    0,  -36,  -36,    0,  -30,  -30,  -30,  -30,
  -30,  -30,  -30,  -30,  -24,  -24,  -24,    0,  -36,  -10,
    0,    0,  -24,    0,    8,    0,  -36,    0,    0, -222,
 -222,   43,   43,   43,   43,  -40,  -40,    0,    0,    0,
  -19,    0,    0,   -9,  -70,    0,  179,    0,  -36, -142,
  203,  -53,  -27,    0,   -1,    0,  -36,  -36,   87,    0,
 -241,    0,    0,  227,    0,
};
final static short yyrindex[] = {                         0,
    0,  124,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   84,    0,   85,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   69,    0,
    0,    0,    0,    0,    0,    0,    0,   93,    0,    0,
  -16,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   76,    0,    0,    0,    0,  146,  110,   50,   14,    0,
    0,    0,    0,    0,    0,    0,   95,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  122,
  134,   62,   74,   86,   98,   26,   38,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  251,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -25,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,    0,  -38,    0,  116,   33,    0,  486,
   65,    0,    0,    0,    0,    0,   10,  -28,    0,  -45,
  -18,   15,  121,    0,    0,
};
final static int YYTABLESIZE=613;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         49,
   38,   86,   97,   48,  127,   49,   87,   90,   50,   48,
   91,   49,   94,   70,   50,   48,  128,   15,   37,   96,
  119,   39,   74,   75,   64,   64,   64,   64,   64,   77,
   64,  100,  101,   78,   13,    3,    4,    5,    6,  116,
   14,   64,   64,    7,    8,   98,   99,   19,  114,   79,
   80,   81,   82,   19,   53,   10,   53,   53,   53,   20,
  102,  103,  104,  105,   21,  129,   51,   37,   51,   51,
   51,   53,   53,  117,   64,   88,   64,   89,   52,  121,
   52,   52,   52,   51,   51,   83,   22,   84,   23,   33,
   50,  134,   36,   50,   40,   52,   52,  106,  107,   61,
   62,   92,   48,   69,   53,   48,   53,   50,   50,   38,
   76,   93,  112,  118,   49,  125,   51,   49,   51,   48,
   48,  130,  133,    1,    5,    6,   46,   30,   52,   46,
   52,   49,   49,   70,   29,   69,   24,  132,   47,    0,
   50,   47,   50,   46,   46,    0,    0,    0,    0,    0,
   45,    0,   48,   45,   48,   47,   47,    0,   65,    0,
    0,    0,   44,    0,   49,   44,   49,   45,   45,    0,
   73,    0,    0,    0,   43,    0,   46,   43,   46,   44,
   44,   33,    0,    0,    0,   33,   42,    0,   47,   42,
   47,   43,   43,   74,   75,    0,    0,    0,   33,    0,
   45,    0,   45,   42,   42,  108,  109,  110,   74,   75,
   74,   75,   44,  113,   44,   74,   75,   74,   75,    0,
    0,    0,   41,    0,   43,    0,   43,    0,   41,   42,
   74,   75,    0,    0,   41,   85,   42,    0,   42,    0,
   43,   37,   74,   75,   74,   75,   43,   64,   64,    0,
   64,    0,   43,    0,   64,   64,   64,   64,   64,   64,
    0,   44,   45,   46,   47,    0,    0,   44,   45,   46,
   47,   74,   75,   44,   45,   46,   47,   53,   53,   30,
   53,    0,    0,    0,   53,   53,   53,   53,   53,   51,
   51,    0,   51,    0,    0,    0,   51,   51,   51,   51,
   51,   52,   52,  120,   52,    0,    0,    0,   52,   52,
   52,   52,   52,   50,   50,    0,   50,    0,    0,    0,
   50,   50,   50,   50,   50,   48,   48,  126,   48,    0,
    0,    0,   48,   48,   48,   48,   48,   49,   49,    0,
   49,    0,    0,    0,   49,   49,   49,   49,   49,   46,
   46,  135,   46,    0,    0,    0,   46,   46,   46,   46,
   46,   47,   47,    0,   47,    0,    0,    0,   47,   47,
   47,   47,   47,   45,   45,   31,   45,    0,    0,    0,
   45,    0,    0,    0,    0,   44,   44,    0,   44,    0,
    0,    0,   44,    0,    0,    0,    0,   43,   43,    0,
   43,    0,    0,    0,   43,    0,    0,    0,    0,   42,
   42,   26,    0,   27,    3,    4,    5,    6,    0,    0,
    0,    0,    7,    8,    0,    0,    0,    0,    0,    0,
    0,    0,   28,    0,   10,   26,   29,   27,    3,    4,
    5,    6,    0,    0,    0,    0,    7,    8,    0,    0,
    0,    0,    0,    0,    0,    0,   28,    0,   10,   26,
   29,   27,    3,    4,    5,    6,    0,    0,    0,    0,
    7,    8,    0,    0,    0,    0,    0,    0,    0,    0,
   28,    0,   10,   26,   29,   27,    3,    4,    5,    6,
    0,    0,    0,    0,    7,    8,    0,    0,    0,    0,
    0,    0,    0,    0,   28,    0,   10,   31,   29,   31,
   31,   31,   31,   31,   51,    0,    0,    0,   31,   31,
    0,   63,   64,    0,   68,    0,    0,    0,   31,    0,
   31,    0,   31,   71,   72,    3,    4,    5,    6,    0,
    0,    0,    0,    7,    8,    9,    0,    0,    0,    0,
    0,    0,    0,    0,   95,   10,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  111,    0,    0,    0,    0,    0,
    0,    0,  115,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  131,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         36,
   40,   42,   58,   40,   58,   36,   47,   41,   45,   40,
   59,   36,   59,   42,   45,   40,   44,   40,   44,   41,
   91,   61,  264,  265,   41,   42,   43,   44,   45,  267,
   47,   77,   78,  271,    2,  260,  261,  262,  263,   59,
  259,   58,   59,  268,  269,   74,   75,   15,   41,  272,
  273,  274,  275,   21,   41,  280,   43,   44,   45,   41,
   79,   80,   81,   82,   44,   93,   41,   93,   43,   44,
   45,   58,   59,  112,   91,   59,   93,   61,   41,  118,
   43,   44,   45,   58,   59,   43,  259,   45,  123,   25,
   41,  130,   40,   44,  279,   58,   59,   83,   84,  259,
   59,   41,   41,   40,   91,   44,   93,   58,   59,   40,
   59,   44,  123,  123,   41,  258,   91,   44,   93,   58,
   59,  123,   36,    0,   41,   41,   41,   59,   91,   44,
   93,   58,   59,   41,   59,   41,   21,  128,   41,   -1,
   91,   44,   93,   58,   59,   -1,   -1,   -1,   -1,   -1,
   41,   -1,   91,   44,   93,   58,   59,   -1,   38,   -1,
   -1,   -1,   41,   -1,   91,   44,   93,   58,   59,   -1,
   50,   -1,   -1,   -1,   41,   -1,   91,   44,   93,   58,
   59,  117,   -1,   -1,   -1,  121,   41,   -1,   91,   44,
   93,   58,   59,  264,  265,   -1,   -1,   -1,  134,   -1,
   91,   -1,   93,   58,   59,   85,   86,   87,  264,  265,
  264,  265,   91,   93,   93,  264,  265,  264,  265,   -1,
   -1,   -1,  259,   -1,   91,   -1,   93,   -1,  259,  266,
  264,  265,   -1,   -1,  259,  276,   91,   -1,   93,   -1,
  277,  281,  264,  265,  264,  265,  277,  264,  265,   -1,
  267,   -1,  277,   -1,  271,  272,  273,  274,  275,  276,
   -1,  298,  299,  300,  301,   -1,   -1,  298,  299,  300,
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
  260,  261,  262,  263,   29,   -1,   -1,   -1,  268,  269,
   -1,   36,   37,   -1,   39,   -1,   -1,   -1,  278,   -1,
  280,   -1,  282,   48,   49,  260,  261,  262,  263,   -1,
   -1,   -1,   -1,  268,  269,  270,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   69,  280,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   89,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   97,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  127,
};
}
final static short YYFINAL=1;
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
"program : function_list",
"function_list : function_list function",
"function_list :",
"function : return_type ID '(' optional_param_list ')' '{' statements '}'",
"optional_param_list :",
"optional_param_list : param_list",
"param_list : param_list ',' parameter",
"param_list : parameter",
"parameter : type ID",
"statements : statements statement",
"statements :",
"statement : type ID ';'",
"statement : type ID '=' expression ';'",
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

//#line 188 "stl.y"

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

//#line 542 "Parser.java"
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
//#line 20 "stl.y"
{ System.out.print("found a program\n"); }
break;
case 2:
//#line 22 "stl.y"
{ System.out.print("found function_list\n"); }
break;
case 3:
//#line 23 "stl.y"
{ System.out.print("found function_list\n"); }
break;
case 4:
//#line 25 "stl.y"
{ System.out.print("found function\n"); }
break;
case 5:
//#line 27 "stl.y"
{ System.out.print("found optional_param_list\n"); }
break;
case 6:
//#line 28 "stl.y"
{ System.out.print("found optional_param_list\n"); }
break;
case 7:
//#line 30 "stl.y"
{ System.out.print("found param_list\n"); }
break;
case 8:
//#line 31 "stl.y"
{ System.out.print("found param_list\n"); }
break;
case 9:
//#line 33 "stl.y"
{ System.out.print("found a parameter\n"); }
break;
case 10:
//#line 35 "stl.y"
{ System.out.print("found statements\n"); }
break;
case 11:
//#line 36 "stl.y"
{ System.out.print("found statements\n"); }
break;
case 12:
//#line 40 "stl.y"
{ System.out.print("found statement (int i;)\n"); yyval = new ParserVal(new DeclarationNode((ASTNode)val_peek(2).obj, (ASTNode)val_peek(1).obj, yyline, yycolumn));  }
break;
case 13:
//#line 41 "stl.y"
{ System.out.print("found statement (int i=5;)\n"); yyval = new ParserVal(new AssignmentOperatorNode(new DeclarationNode((ASTNode)val_peek(4).obj, (ASTNode)val_peek(3).obj, yyline, yycolumn), (ASTNode)val_peek(1).obj, yyline, yycolumn)); }
break;
case 14:
//#line 42 "stl.y"
{ System.out.print("found statement (i=5;)\n"); yyval = new ParserVal(new AssignmentOperatorNode((IDNode)val_peek(3).obj, (ASTNode)val_peek(2).obj, yyline, yycolumn)); }
break;
case 15:
//#line 43 "stl.y"
{ System.out.print("found statement (func call)\n"); }
break;
case 16:
//#line 44 "stl.y"
{ System.out.print("found statement (loop)\n"); }
break;
case 17:
//#line 45 "stl.y"
{ System.out.print("found statement (if statement)\n"); }
break;
case 18:
//#line 46 "stl.y"
{ System.out.print("found statement (insert ques to set)\n"); }
break;
case 19:
//#line 47 "stl.y"
{ System.out.print("found statement (return)\n"); }
break;
case 20:
//#line 49 "stl.y"
{ System.out.print("found type (int)\n"); }
break;
case 21:
//#line 50 "stl.y"
{ System.out.print("found type (float)\n"); }
break;
case 22:
//#line 51 "stl.y"
{ System.out.print("found type (char)\n"); }
break;
case 23:
//#line 52 "stl.y"
{ System.out.print("found type (boolean)\n"); }
break;
case 24:
//#line 53 "stl.y"
{ System.out.print("found type (string)\n"); }
break;
case 25:
//#line 54 "stl.y"
{ System.out.print("found type (question)\n"); }
break;
case 26:
//#line 55 "stl.y"
{ System.out.print("found type (set)\n"); }
break;
case 28:
//#line 57 "stl.y"
{ System.out.print("found return_type\n"); }
break;
case 29:
//#line 59 "stl.y"
{ System.out.print("found optional_expression\n"); }
break;
case 30:
//#line 60 "stl.y"
{ System.out.print("found optional_expression\n"); }
break;
case 31:
//#line 66 "stl.y"
{ 
		yyval = new ParserVal(new IfStatementNode((ASTNode)val_peek(4).obj, (ASTNode)val_peek(1).obj, yyline, yycolumn));
		System.out.print("found if_statement\n"); 
	}
break;
case 32:
//#line 71 "stl.y"
{
		yyval = new ParserVal(new IfStatementNode((ASTNode)val_peek(8).obj, (ASTNode)val_peek(5).obj, (ASTNode)val_peek(1).obj, yyline, yycolumn));
	 	System.out.print("found if_statement\n"); 
	}
break;
case 33:
//#line 90 "stl.y"
{
	yyval = new ParserVal(new LoopNode((ASTNode)val_peek(4).obj, (ASTNode)val_peek(1).obj, yyline, yycolumn));
	System.out.print("found loop\n"); 
}
break;
case 34:
//#line 96 "stl.y"
{ System.out.print("found question_literal\n"); }
break;
case 35:
//#line 98 "stl.y"
{ System.out.print("found answer_choices\n"); }
break;
case 36:
//#line 99 "stl.y"
{ System.out.print("found answer_choices\n"); }
break;
case 37:
//#line 101 "stl.y"
{ System.out.print("found an answer_choice\n"); }
break;
case 38:
//#line 104 "stl.y"
{ System.out.print("found an expression (and)\n"); }
break;
case 39:
//#line 105 "stl.y"
{ System.out.print("found an expression (or)\n"); }
break;
case 40:
//#line 106 "stl.y"
{ System.out.print("found an expression (not boolean)\n"); }
break;
case 41:
//#line 109 "stl.y"
{ 
		yyval = new ParserVal(new NotBooleanOperandNode("not",(ASTNode)val_peek(0).obj, yyline, yycolumn));
		System.out.print("found a not_boolean_operand\n"); 
	}
break;
case 42:
//#line 114 "stl.y"
{ 
		/*$$ = new ParserVal(new NotBooleanOperandNode((ASTNode)$1.obj, yyline, yycolumn));*/
		yyval = val_peek(0);		
		System.out.print("found a not_boolean_operand\n"); 
	}
break;
case 43:
//#line 121 "stl.y"
{ 
		yyval = new ParserVal(new BooleanOperandNode("equal",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, yyline, yycolumn));
		System.out.print("found a boolean_operand\n"); 
	}
break;
case 44:
//#line 126 "stl.y"
{
		yyval = new ParserVal(new BooleanOperandNode("not_equal",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, yyline, yycolumn));
		System.out.print("found a boolean_operand\n"); 
	}
break;
case 45:
//#line 131 "stl.y"
{ 	
		yyval = val_peek(0);
		System.out.print("found a boolean_operand\n"); 
	}
break;
case 46:
//#line 136 "stl.y"
{ System.out.print("found a equality_operand (LT)\n"); }
break;
case 47:
//#line 137 "stl.y"
{ System.out.print("found a equality_operand (GT)\n"); }
break;
case 48:
//#line 138 "stl.y"
{ System.out.print("found a equality_operand (GE)\n"); }
break;
case 49:
//#line 139 "stl.y"
{ System.out.print("found a equality_operand (LE)\n"); }
break;
case 50:
//#line 140 "stl.y"
{ System.out.print("found a equality_operand\n"); }
break;
case 51:
//#line 143 "stl.y"
{ 
		yyval = new ParserVal(new RelationalOperatorNode("addition",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, yyline, yycolumn));
		System.out.print("found a relational_operand (+)\n"); 
	}
break;
case 52:
//#line 148 "stl.y"
{ 
		yyval = new ParserVal(new RelationalOperatorNode("subtraction",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, yyline, yycolumn));
		System.out.print("found a relational_operand (-)\n"); 
	}
break;
case 53:
//#line 153 "stl.y"
{ 
	 	yyval = val_peek(0);
	 	System.out.print("found a relational_operand\n"); 
	}
break;
case 54:
//#line 158 "stl.y"
{ System.out.print("found a term (*)\n"); }
break;
case 55:
//#line 159 "stl.y"
{ System.out.print("found a term (/)\n"); }
break;
case 56:
//#line 160 "stl.y"
{ System.out.print("found a term (%)\n"); }
break;
case 57:
//#line 161 "stl.y"
{ System.out.print("found a term\n"); }
break;
case 58:
//#line 162 "stl.y"
{ System.out.print("found a term (unary -)\n"); }
break;
case 59:
//#line 164 "stl.y"
{ System.out.print("found a factor (int)\n"); }
break;
case 60:
//#line 165 "stl.y"
{ System.out.print("found a factor (float)\n"); }
break;
case 61:
//#line 166 "stl.y"
{ System.out.print("found a factor (char)\n"); }
break;
case 62:
//#line 167 "stl.y"
{ System.out.print("found a factor (string)\n"); }
break;
case 63:
//#line 168 "stl.y"
{ System.out.print("found a factor (bool)\n"); }
break;
case 68:
//#line 174 "stl.y"
{ System.out.print("found a function_call\n"); }
break;
case 69:
//#line 175 "stl.y"
{ System.out.print("found an optional_factor_list\n"); }
break;
case 71:
//#line 178 "stl.y"
{ 
					System.out.print("found a factor_list\n"); 
					yyval = new ParserVal(((FactorListNode)val_peek(2).obj).add(val_peek(0).obj)); 
				    }
break;
case 72:
//#line 182 "stl.y"
{ 
		System.out.print("found a factor_list\n"); 
		yyval = new ParserVal(new FactorListNode((ASTNode)val_peek(0).obj)); 
	}
break;
//#line 995 "Parser.java"
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
