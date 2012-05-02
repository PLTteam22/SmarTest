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



package smartest;



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
    9,   17,   17,   18,   18,   19,   15,   15,   10,   10,
   10,   10,   10,   10,   10,    4,    4,   16,   16,   14,
   14,   13,   20,   21,   21,   22,   11,   11,   11,   23,
   23,   24,   24,   24,   25,   25,   25,   25,   25,   26,
   26,   26,   27,   27,   27,   27,   27,   28,   28,   28,
   28,   28,   28,   28,   28,   28,   28,   12,   29,   29,
   30,   30,
};
final static short yylen[] = {                            2,
    1,    1,    0,    2,    1,    8,    0,    1,    3,    1,
    2,    0,    2,    2,    4,    4,    2,    1,    1,    2,
    3,    1,    0,    3,    1,    3,    3,    3,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    0,    7,
   11,    8,    8,    3,    1,    3,    3,    3,    1,    2,
    1,    3,    3,    1,    3,    3,    3,    3,    1,    3,
    3,    1,    3,    3,    3,    1,    2,    1,    1,    1,
    1,    1,    1,    1,    1,    3,    1,    4,    1,    0,
    3,    1,
};
final static short yydefred[] = {                         0,
   29,   30,   34,   35,   31,   32,   37,   33,    0,    1,
    0,    5,    0,   36,    4,    0,    0,    0,    0,   10,
    0,    0,    0,   13,   12,    9,    0,    0,    0,    0,
    0,    6,    0,   11,    0,   18,   19,    0,    0,    0,
    0,    0,    0,    0,    0,   72,   70,   71,   69,   68,
    0,    0,    0,    0,    0,   77,    0,   75,   74,   49,
    0,    0,    0,    0,   66,   14,    0,   17,    0,   20,
    0,    0,    0,    0,    0,    0,    0,   50,    0,    0,
    0,    0,    0,   67,    0,    0,   21,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   78,    0,   16,    0,   76,   26,    0,    0,   47,
   48,    0,    0,    0,    0,    0,    0,    0,    0,   65,
   63,   64,   15,   12,    0,    0,    0,    0,    0,   12,
    0,    0,    0,    0,    0,   45,    0,   42,    0,    0,
    0,   12,    0,   44,   43,    0,   41,
};
final static short yydgoto[] = {                          9,
   10,   11,   12,   13,   18,   27,   19,   33,   34,   21,
  134,   56,   36,   37,   38,   57,   81,   82,   58,   59,
  135,  136,   60,   61,   62,   63,   64,   65,   74,   75,
};
final static short yysindex[] = {                       281,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  281,    0, -240,    0,    0,    6,  297,  -20,   10,    0,
 -190,   -8,  297,    0,    0,    0,  158,   56,  -39, -176,
  -36,    0,  -14,    0,   58,    0,    0,  -56,  -36,  -36,
  -36,  -36,   83,   84,  -36,    0,    0,    0,    0,    0,
  -36,  -36,  -36,  -24, -230,    0,   66,    0,    0,    0,
 -234, -223,   32,  -40,    0,    0,  -36,    0,  -36,    0,
  -33, -230, -230,   85,   91,   20,  -36,    0,  -21, -230,
   34,   92,  -45,    0,  -36,  -36,    0,  -30,  -30,  -30,
  -30,  -30,  -30,  -30,  -30,  -24,  -24,  -24,  144, -230,
   16,    0,  -36,    0,  -10,    0,    0,  -36,  -36,    0,
    0, -223, -223,   32,   32,   32,   32,  -40,  -40,    0,
    0,    0,    0,    0, -230,   18, -230,  -68,  182,    0,
  -36, -129,  206,  -19,  -27,    0,   24,    0,  -36,  -36,
  101,    0, -230,    0,    0,  230,    0,
};
final static short yyrindex[] = {                       138,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  148,    0,    0,    0,    0,    0,  108,    0,  109,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   94,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  110,    0,    0,  -15,    0,    0,    0,    0,    0,    0,
    0,   67,    0,    0,  100,    0,    0,    0,    0,    0,
  147,  111,   51,   15,    0,    0,    0,    0,    0,    0,
    0,  -54,   47,    0,  120,    0,    0,    0,    0,   -6,
    0,   69,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -48,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  123,  135,   63,   75,   87,   99,   27,   39,    0,
    0,    0,    0,    0,   61,    0,   -4,    0,    0,    0,
    0,  254,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   -3,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  152,    0,    0, -106,    0,   25,    0,   14,
  487,   54,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   31,    8,    0,   23,  -28,   19,    3,    0,    0,
};
final static int YYTABLESIZE=626;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         53,
   41,   97,   70,   51,   28,   53,   98,  101,   54,   51,
   27,   53,  109,   14,   54,   51,  140,  129,   16,  106,
   22,   42,  131,  133,   14,   73,   73,   73,   73,   73,
  126,   73,   88,   85,   86,  146,   89,   25,  139,   24,
   46,   20,   73,   73,   66,   17,   67,   26,   90,   91,
   92,   93,   78,   23,   52,   62,   84,   62,   62,   62,
   52,  114,  115,  116,  117,  141,   52,   60,   24,   60,
   60,   60,   62,   62,   94,   73,   95,   73,  104,   61,
   35,   61,   61,   61,   60,   60,   25,   82,   24,   46,
   82,   59,  110,  111,   59,   39,   61,   61,  120,  121,
  122,   81,   43,   57,   81,   62,   57,   62,   59,   59,
  112,  113,  118,  119,   25,   58,   68,   60,   58,   60,
   57,   57,   77,   41,   87,  102,  107,   55,  137,   61,
   55,   61,   58,   58,  103,  108,  145,    3,  124,   56,
  130,   59,   56,   59,   55,   55,  142,    2,    7,    8,
   80,   54,   39,   57,   54,   57,   56,   56,   38,   23,
   79,   22,   15,   53,    0,   58,   53,   58,   54,   54,
  144,    0,    0,    0,    0,   52,    0,   55,   52,   55,
   53,   53,   35,    0,    0,    0,   35,   51,    0,   56,
   51,   56,   52,   52,    0,   85,   86,    0,    0,   35,
    0,   54,  123,   54,   51,   51,    0,    0,    0,    0,
    0,    0,    0,   53,    0,   53,    0,    0,   85,   86,
    0,    0,   44,    0,   69,   52,   28,   52,   44,   45,
   85,   86,   27,    0,   44,   96,    0,   51,    0,   51,
   46,   40,   85,   86,   85,   86,   46,    0,   73,   73,
    0,   73,   46,   85,   86,   73,   73,   73,   73,   73,
   73,   47,   48,   49,   50,   73,    0,   47,   48,   49,
   50,    0,    0,   47,   48,   49,   50,    0,   62,   62,
    0,   62,   32,   85,   86,   62,   62,   62,   62,   62,
   60,   60,    0,   60,    0,   62,    0,   60,   60,   60,
   60,   60,   61,   61,    0,   61,  132,   60,    0,   61,
   61,   61,   61,   61,   59,   59,    0,   59,    0,   61,
    0,   59,   59,   59,   59,   59,   57,   57,    0,   57,
  138,   59,    0,   57,   57,   57,   57,   57,   58,   58,
    0,   58,    0,   57,    0,   58,   58,   58,   58,   58,
   55,   55,    0,   55,  147,   58,    0,   55,   55,   55,
   55,   55,   56,   56,    0,   56,    0,   55,    0,   56,
   56,   56,   56,   56,   54,   54,    0,   54,   40,   56,
    0,   54,    0,    0,    0,    0,   53,   53,    0,   53,
    0,   54,    0,   53,    0,    0,    0,    0,   52,   52,
    0,   52,    0,   53,    0,   52,    0,   85,   86,    0,
   51,   51,    0,    0,   28,   52,   29,    1,    2,    3,
    4,    0,    0,    0,    0,    5,    6,   51,    0,    0,
    0,    0,    0,    0,    0,   30,    0,    8,   28,   31,
   29,    1,    2,    3,    4,    0,    0,    0,    0,    5,
    6,    0,    0,    0,    0,    0,    0,    0,    0,   30,
    0,    8,   28,   31,   29,    1,    2,    3,    4,    0,
    0,    0,    0,    5,    6,    0,    0,    0,    0,    0,
    0,    0,    0,   30,    0,    8,   28,   31,   29,    1,
    2,    3,    4,    0,    0,    0,    0,    5,    6,    0,
    0,    0,    0,    0,    0,    0,    0,   30,    0,    8,
   40,   31,   40,   40,   40,   40,   40,   55,    0,    0,
    0,   40,   40,    0,    0,   71,   72,   73,   76,    0,
    0,   40,    0,   40,    0,   40,    0,   79,   80,   83,
    1,    2,    3,    4,    0,    0,    0,    0,    5,    6,
    7,    0,    0,   99,    0,  100,    1,    2,    3,    4,
    8,    0,    0,  105,    5,    6,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    8,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  125,
    0,    0,    0,    0,  127,  128,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  143,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         36,
   40,   42,   59,   40,   59,   36,   47,   41,   45,   40,
   59,   36,   58,    0,   45,   40,   44,  124,  259,   41,
   41,   61,   91,  130,   11,   41,   42,   43,   44,   45,
   41,   47,  267,  264,  265,  142,  271,   44,   58,   44,
   44,   17,   58,   59,   59,   40,   61,   23,  272,  273,
  274,  275,   45,   44,   91,   41,   54,   43,   44,   45,
   91,   90,   91,   92,   93,   93,   91,   41,  259,   43,
   44,   45,   58,   59,   43,   91,   45,   93,   59,   41,
   27,   43,   44,   45,   58,   59,   93,   41,   93,   93,
   44,   41,   85,   86,   44,   40,   58,   59,   96,   97,
   98,   41,  279,   41,   44,   91,   44,   93,   58,   59,
   88,   89,   94,   95,  123,   41,   59,   91,   44,   93,
   58,   59,   40,   40,   59,   41,   93,   41,  258,   91,
   44,   93,   58,   59,   44,   44,   36,    0,  123,   41,
  123,   91,   44,   93,   58,   59,  123,    0,   41,   41,
   41,   41,   59,   91,   44,   93,   58,   59,   59,   93,
   41,   93,   11,   41,   -1,   91,   44,   93,   58,   59,
  140,   -1,   -1,   -1,   -1,   41,   -1,   91,   44,   93,
   58,   59,  129,   -1,   -1,   -1,  133,   41,   -1,   91,
   44,   93,   58,   59,   -1,  264,  265,   -1,   -1,  146,
   -1,   91,   59,   93,   58,   59,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   91,   -1,   93,   -1,   -1,  264,  265,
   -1,   -1,  259,   -1,  281,   91,  281,   93,  259,  266,
  264,  265,  281,   -1,  259,  276,   -1,   91,   -1,   93,
  277,  281,  264,  265,  264,  265,  277,   -1,  264,  265,
   -1,  267,  277,  264,  265,  271,  272,  273,  274,  275,
  276,  298,  299,  300,  301,  281,   -1,  298,  299,  300,
  301,   -1,   -1,  298,  299,  300,  301,   -1,  264,  265,
   -1,  267,  125,  264,  265,  271,  272,  273,  274,  275,
  264,  265,   -1,  267,   -1,  281,   -1,  271,  272,  273,
  274,  275,  264,  265,   -1,  267,  125,  281,   -1,  271,
  272,  273,  274,  275,  264,  265,   -1,  267,   -1,  281,
   -1,  271,  272,  273,  274,  275,  264,  265,   -1,  267,
  125,  281,   -1,  271,  272,  273,  274,  275,  264,  265,
   -1,  267,   -1,  281,   -1,  271,  272,  273,  274,  275,
  264,  265,   -1,  267,  125,  281,   -1,  271,  272,  273,
  274,  275,  264,  265,   -1,  267,   -1,  281,   -1,  271,
  272,  273,  274,  275,  264,  265,   -1,  267,  125,  281,
   -1,  271,   -1,   -1,   -1,   -1,  264,  265,   -1,  267,
   -1,  281,   -1,  271,   -1,   -1,   -1,   -1,  264,  265,
   -1,  267,   -1,  281,   -1,  271,   -1,  264,  265,   -1,
  264,  265,   -1,   -1,  257,  281,  259,  260,  261,  262,
  263,   -1,   -1,   -1,   -1,  268,  269,  281,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  278,   -1,  280,  257,  282,
  259,  260,  261,  262,  263,   -1,   -1,   -1,   -1,  268,
  269,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  278,
   -1,  280,  257,  282,  259,  260,  261,  262,  263,   -1,
   -1,   -1,   -1,  268,  269,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  278,   -1,  280,  257,  282,  259,  260,
  261,  262,  263,   -1,   -1,   -1,   -1,  268,  269,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  278,   -1,  280,
  257,  282,  259,  260,  261,  262,  263,   31,   -1,   -1,
   -1,  268,  269,   -1,   -1,   39,   40,   41,   42,   -1,
   -1,  278,   -1,  280,   -1,  282,   -1,   51,   52,   53,
  260,  261,  262,  263,   -1,   -1,   -1,   -1,  268,  269,
  270,   -1,   -1,   67,   -1,   69,  260,  261,  262,  263,
  280,   -1,   -1,   77,  268,  269,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  280,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  103,
   -1,   -1,   -1,   -1,  108,  109,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  139,
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
"statement : set_insert ';'",
"statement : RETURN optional_expression ';'",
"optional_question_list : question_list",
"optional_question_list :",
"question_list : question_list ',' expression",
"question_list : expression",
"set_literal : '[' optional_question_list ']'",
"set_insert : set_insert INSERT expression",
"set_insert : ID INSERT expression",
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
"factor : set_literal",
"factor : '(' expression ')'",
"factor : function_call",
"function_call : ID '(' optional_factor_list ')'",
"optional_factor_list : factor_list",
"optional_factor_list :",
"factor_list : factor_list ',' expression",
"factor_list : expression",
};

//#line 281 "stl.y"

/**************************************
* Variables
***************************************/
private Yylex lexer;
private static String INPUT_FILE;
public int line = 1, column;
public static boolean DEBUG = false;

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
  paraList6.add("double");
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
  functionSymbolsTable.put("printboolean", new FunctionSymbolTableEntry("printBoolean", "BuiltInFunction.printBoolean" , "void", paraList6));


  // Add 2 more pre-defined functions. 


  yyparser.yyparse();
  
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

//#line 645 "Parser.java"
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
        
        yyval = new ParserVal(new ProgramNode((ArrayList<ASTNode>)val_peek(0).obj, line, column));
        try
        {
        	((ASTNode)yyval.obj).checkSemantics();
/*        	System.out.println("\nCompiled successfully");*/
        }
        catch (Exception e)
        {
        	System.out.println(e.getMessage());
        	e.printStackTrace();
		System.exit(1);
        }
        ((ProgramNode)yyval.obj).generateCode(INPUT_FILE);
}
break;
case 2:
//#line 38 "stl.y"
{  yyval = val_peek(0); }
break;
case 3:
//#line 39 "stl.y"
{  yyval = new ParserVal(null); }
break;
case 4:
//#line 41 "stl.y"
{  ((ArrayList<ASTNode>)val_peek(1).obj).add((ASTNode)val_peek(0).obj); yyval = val_peek(1); }
break;
case 5:
//#line 42 "stl.y"
{  ArrayList<ASTNode> flist = new ArrayList<ASTNode>(); flist.add((ASTNode)val_peek(0).obj); yyval = new ParserVal(flist); }
break;
case 6:
//#line 45 "stl.y"
{
        
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
{  yyval = new ParserVal(null); }
break;
case 8:
//#line 60 "stl.y"
{  yyval = val_peek(0); }
break;
case 9:
//#line 62 "stl.y"
{  ((ArrayList<ASTNode>)val_peek(2).obj).add((ASTNode)val_peek(0).obj); }
break;
case 10:
//#line 63 "stl.y"
{  ArrayList<ASTNode> plist = new ArrayList<ASTNode>(); plist.add((ASTNode)val_peek(0).obj); yyval = new ParserVal(plist); }
break;
case 11:
//#line 65 "stl.y"
{  ((StatementsNode)val_peek(1).obj).addChild((ASTNode)val_peek(0).obj); yyval = val_peek(1);}
break;
case 12:
//#line 66 "stl.y"
{  yyval = new ParserVal(new StatementsNode(line, column)); }
break;
case 13:
//#line 70 "stl.y"
{ yyval = new ParserVal(new DeclarationNode(val_peek(1).sval, new IDNode(val_peek(0).sval, true, line, column), line, column)); }
break;
case 14:
//#line 72 "stl.y"
{  ((DeclarationNode)val_peek(1).obj).setIsStatement(true); yyval = val_peek(1); }
break;
case 15:
//#line 73 "stl.y"
{  yyval = new ParserVal(new AssignmentOperatorNode((DeclarationNode)val_peek(3).obj, (ASTNode)val_peek(1).obj, line, column)); }
break;
case 16:
//#line 74 "stl.y"
{  yyval = new ParserVal(new AssignmentOperatorNode(new IDNode(val_peek(3).sval, false, line, column), (ASTNode)val_peek(1).obj, line, column)); }
break;
case 17:
//#line 75 "stl.y"
{  }
break;
case 18:
//#line 76 "stl.y"
{  }
break;
case 19:
//#line 77 "stl.y"
{  }
break;
case 20:
//#line 79 "stl.y"
{ yyval = val_peek(1); }
break;
case 21:
//#line 80 "stl.y"
{  yyval = new ParserVal(new ReturnNode(currentReturnType, (ASTNode)val_peek(1).obj, line, column)); }
break;
case 22:
//#line 84 "stl.y"
{ yyval = val_peek(0); }
break;
case 23:
//#line 85 "stl.y"
{ QuestionListNode ql = new QuestionListNode(line, column);  yyval = new ParserVal(ql); }
break;
case 24:
//#line 87 "stl.y"
{  ((QuestionListNode)val_peek(2).obj).addChild((ASTNode)val_peek(0).obj);  }
break;
case 25:
//#line 88 "stl.y"
{ QuestionListNode ql = new QuestionListNode(line, column); ql.addChild((ASTNode)val_peek(0).obj); yyval = new ParserVal(ql); }
break;
case 26:
//#line 90 "stl.y"
{ SetLiteralNode sl = new SetLiteralNode((ASTNode)val_peek(1).obj, line, column); yyval = new ParserVal(sl); }
break;
case 27:
//#line 92 "stl.y"
{ ((ASTNode)val_peek(2).obj).addChild((ASTNode)val_peek(0).obj); yyval = val_peek(2); }
break;
case 28:
//#line 93 "stl.y"
{ yyval = new ParserVal(new InsertOperatorNode(new IDNode(val_peek(2).sval, false, line, column), (ASTNode)val_peek(0).obj, line, column)); }
break;
case 29:
//#line 95 "stl.y"
{  yyval = new ParserVal("int"); }
break;
case 30:
//#line 96 "stl.y"
{  yyval = new ParserVal("double"); }
break;
case 31:
//#line 97 "stl.y"
{  yyval = new ParserVal("char"); }
break;
case 32:
//#line 98 "stl.y"
{  yyval = new ParserVal("boolean"); }
break;
case 33:
//#line 99 "stl.y"
{  yyval = new ParserVal("String"); }
break;
case 34:
//#line 100 "stl.y"
{  yyval = new ParserVal("question"); }
break;
case 35:
//#line 101 "stl.y"
{ yyval = new ParserVal("set"); }
break;
case 36:
//#line 104 "stl.y"
{ 
		yyval = new ParserVal(val_peek(0).sval);
		Parser.currentReturnType = val_peek(0).sval;
	}
break;
case 37:
//#line 109 "stl.y"
{ 
		 
		yyval = new ParserVal("void");
		Parser.currentReturnType = "void";
	}
break;
case 38:
//#line 115 "stl.y"
{  yyval = val_peek(0); }
break;
case 39:
//#line 116 "stl.y"
{  yyval = new ParserVal(null);}
break;
case 40:
//#line 122 "stl.y"
{ 
		yyval = new ParserVal(new IfStatementNode((ASTNode)val_peek(4).obj, (ASTNode)val_peek(1).obj, line, column));
		 
	}
break;
case 41:
//#line 127 "stl.y"
{
		yyval = new ParserVal(new IfStatementNode((ASTNode)val_peek(8).obj, (ASTNode)val_peek(5).obj, (ASTNode)val_peek(1).obj, line, column));
	 	
	}
break;
case 42:
//#line 133 "stl.y"
{
	yyval = new ParserVal(new LoopNode((ASTNode)val_peek(4).obj, (ASTNode)val_peek(1).obj, line, column));

}
break;
case 43:
//#line 139 "stl.y"
{  yyval = new ParserVal(new QuestionLiteralNode((ASTNode)val_peek(6).obj, (ASTNode)val_peek(4).obj, (ASTNode)val_peek(2).obj, line, column)); }
break;
case 44:
//#line 141 "stl.y"
{  ((AnswerChoicesListNode)val_peek(2).obj).addAnswer((ASTNode)val_peek(0).obj); yyval = val_peek(2); }
break;
case 45:
//#line 142 "stl.y"
{  yyval = new ParserVal(new AnswerChoicesListNode(line, column)); ((AnswerChoicesListNode)yyval.obj).addAnswer((ASTNode)val_peek(0).obj); }
break;
case 46:
//#line 144 "stl.y"
{  yyval = new ParserVal(new AnswerChoiceNode((ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column)); }
break;
case 47:
//#line 148 "stl.y"
{ 	
		yyval = new ParserVal(new ExpressionNode("and",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column)); 
	}
break;
case 48:
//#line 152 "stl.y"
{ 
		 yyval = new ParserVal(new ExpressionNode("or",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column)); 
	}
break;
case 49:
//#line 156 "stl.y"
{ 
		 yyval = val_peek(0);
	}
break;
case 50:
//#line 161 "stl.y"
{ 
		yyval = new ParserVal(new NotBooleanOperandNode("not",(ASTNode)val_peek(0).obj, line, column));
		
	}
break;
case 51:
//#line 166 "stl.y"
{ 
		/*$$ = new ParserVal(new NotBooleanOperandNode((ASTNode)$1.obj, line, column));*/
		yyval = val_peek(0);		
		
	}
break;
case 52:
//#line 173 "stl.y"
{ 
		yyval = new ParserVal(new BooleanOperandNode("equal",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
		
	}
break;
case 53:
//#line 178 "stl.y"
{
		yyval = new ParserVal(new BooleanOperandNode("not_equal",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
		
	}
break;
case 54:
//#line 183 "stl.y"
{ 	
		yyval = val_peek(0);
		
	}
break;
case 55:
//#line 189 "stl.y"
{
					
					yyval = new ParserVal(new RelationalOperatorNode("LT", (ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
				}
break;
case 56:
//#line 194 "stl.y"
{
					
					yyval = new ParserVal(new RelationalOperatorNode("GT", (ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
				}
break;
case 57:
//#line 199 "stl.y"
{
					
					yyval = new ParserVal(new RelationalOperatorNode("GE", (ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
				}
break;
case 58:
//#line 204 "stl.y"
{
					
					yyval = new ParserVal(new RelationalOperatorNode("LE", (ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
				}
break;
case 59:
//#line 208 "stl.y"
{  yyval = val_peek(0); }
break;
case 60:
//#line 211 "stl.y"
{ 
		yyval = new ParserVal(new ArithmeticOperatorNode("addition",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
		
	}
break;
case 61:
//#line 216 "stl.y"
{ 
		yyval = new ParserVal(new ArithmeticOperatorNode("subtraction",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
		
	}
break;
case 62:
//#line 221 "stl.y"
{ 
	 	yyval = val_peek(0);
	 	
	}
break;
case 63:
//#line 227 "stl.y"
{
		yyval = new ParserVal(new ArithmeticOperatorNode("multiplication",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column));
		
	}
break;
case 64:
//#line 232 "stl.y"
{
		yyval = new ParserVal(new ArithmeticOperatorNode("division",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column)); 
		
	}
break;
case 65:
//#line 237 "stl.y"
{
		yyval = new ParserVal(new ArithmeticOperatorNode("modulus",(ASTNode)val_peek(2).obj, (ASTNode)val_peek(0).obj, line, column)); 
		
	}
break;
case 66:
//#line 242 "stl.y"
{ 
		yyval = val_peek(0);
		
	}
break;
case 67:
//#line 247 "stl.y"
{ 
		yyval = new ParserVal(new ArithmeticOperatorNode("unary",(ASTNode)val_peek(0).obj, line, column));
		
	}
break;
case 68:
//#line 253 "stl.y"
{  yyval = new ParserVal(new LiteralNode("int",(Object) val_peek(0).ival, line, column)); }
break;
case 69:
//#line 254 "stl.y"
{  yyval = new ParserVal(new LiteralNode("double", (Object) val_peek(0).dval, line, column)); }
break;
case 70:
//#line 255 "stl.y"
{  yyval = new ParserVal(new LiteralNode("char", (Object) val_peek(0).ival,line, column)); }
break;
case 71:
//#line 256 "stl.y"
{  yyval = new ParserVal(new LiteralNode("String", (Object) val_peek(0).sval, line, column));}
break;
case 72:
//#line 257 "stl.y"
{  yyval = new ParserVal(new LiteralNode("boolean", (Object) val_peek(0).sval,line, column)); }
break;
case 73:
//#line 258 "stl.y"
{ yyval = new ParserVal(new IDNode(val_peek(0).sval, false, line, column)); }
break;
case 74:
//#line 259 "stl.y"
{ yyval = val_peek(0); }
break;
case 75:
//#line 260 "stl.y"
{ yyval = val_peek(0); }
break;
case 76:
//#line 261 "stl.y"
{ yyval = new ParserVal(new ParenthesesNode((ASTNode)val_peek(1).obj, line, column)); }
break;
case 77:
//#line 262 "stl.y"
{ yyval = val_peek(0); }
break;
case 78:
//#line 264 "stl.y"
{  yyval = new ParserVal(new FunctionCallNode(val_peek(3).sval, (FactorListNode)val_peek(1).obj, line,column)); }
break;
case 79:
//#line 266 "stl.y"
{  yyval = val_peek(0); }
break;
case 80:
//#line 267 "stl.y"
{ yyval= new ParserVal(null); }
break;
case 81:
//#line 270 "stl.y"
{ 
					
                                        ((FactorListNode)val_peek(2).obj).addChild((ASTNode)val_peek(0).obj); 
					yyval = val_peek(2);
				    }
break;
case 82:
//#line 275 "stl.y"
{ 
		
		yyval = new ParserVal(new FactorListNode((ASTNode)val_peek(0).obj, line, column)); 
	}
break;
//#line 1230 "Parser.java"
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
