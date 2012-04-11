/*stl.flex*/
%%

%byaccj

%{
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser)
  {
    this(r);
    this.yyparser = yyparser;
  }
%}
%%

[iI][fF]                          { 
					System.out.println("lex: found if\n"); return (Parser.IF); 
				  }



[eE][lL][sS][eE]       		{System.out.println("lex: found else\n"); return (Parser.ELSE);}



[fF][lL][oO][aA][tT]  		{System.out.println("lex: found float\n"); return (Parser.FLOAT);}


[aA][nN][dD] 			{System.out.println("lex: found and\n"); return (Parser.AND);}	


[oO][rR]			{System.out.println("lex: found or\n"); return (Parser.OR);}


[nN][oO][tT]			{System.out.println("lex: found not\n"); return (Parser.NOT);}


[lL][oO][oO][pP]                {System.out.println("lex: found loop\n"); return (Parser.LOOP);}


[wW][hH][iI][lL][eE]            {System.out.println("lex: found while\n"); return (Parser.WHILE);}


[rR][eE][tT][uU][rR][nN]        {System.out.println("lex: found return\n"); return (Parser.RETURN);}


"!="				{System.out.println("lex: found !=\n"); return (Parser.NOTEQUAL);}


[iI][nN][Tt]                    {System.out.println("lex: found int\n"); return (Parser.INT);} 


[cC][hH][aA][rR]		{System.out.println("lex: found char\n"); return (Parser.CHAR);}


[bB][oO][oO][lL][eE][aA][nN]	{System.out.println("lex: found boolean\n"); return (Parser.BOOLEAN);}


[vV][oO][iI][dD]		{System.out.println("lex: found void\n"); return (Parser.VOID);}


[qQ][uU][eE][sS][tT][iI][oO][nN] {System.out.println("lex: found question\n"); return (Parser.QUESTION);}


[sS][eE][tT]                    {System.out.println("lex: found set\n"); return (Parser.SET);}


"<<"                            {System.out.println("lex: found <<\n"); return (Parser.INSERT);}


"=="				{System.out.println("lex: found ==\n"); return (Parser.EQUALEQUAL);}


">="				{System.out.println("lex: found >=\n"); return (Parser.GE);}


"<="				{System.out.println("lex: found <=\n"); return (Parser.LE);}


"<"				{System.out.println("lex: found <\n"); return (Parser.LT);}


">"		 		{System.out.println("lex: found >\n"); return (Parser.GT);}


"%" 				{System.out.println("lex: found percent\n"); return (Parser.MOD);}


/*
"("				{System.out.println("lex: found percent\n"); return (Parser.LPARAN);}


")"				{System.out.println("lex: found percent\n"); return (Parser.RPARAN);}


"{"				{System.out.println("lex: found percent\n"); return (Parser.LCURLY);}


"}"				{System.out.println("lex: found percent\n"); return (Parser.RCURLY);}


","				{System.out.println("lex: found percent\n"); return (Parser.COMMA);}


";"				{System.out.println("lex: found percent\n"); return (Parser.SEMICOLON);}
	

"="				{System.out.println("lex: found percent\n"); return (Parser.EQUAL);}


"$"				{System.out.println("lex: found percent\n"); return (Parser.DOLLAR);}


"["				{System.out.println("lex: found percent\n"); return (Parser.LSQUARE);}


"]"				{System.out.println("lex: found percent\n"); return (Parser.RSQUARE);}


":"				{System.out.println("lex: found percent\n"); return (Parser.COLON);}


"+"				{System.out.println("lex: found percent\n"); return (Parser.PLUS);}


"-"				{System.out.println("lex: found percent\n"); return (Parser.MINUS);}


"*"				{System.out.println("lex: found percent\n"); return (Parser.MULTIPLY);}


"/"				{System.out.println("lex: found percent\n"); return (Parser.DIVIDE);}

*/






[0-9]+ 				{
					System.out.println("lex: found int_literal\n");
					yyparser.yylval = new ParserVal(Integer.parseInt(yytext())); 
					return (Parser.INTLITERAL);
				}


['][^'][']           		{
					System.out.println("lex: found char_literal\n");
					yyparser.yylval = new ParserVal(yycharat(1)); 
					return (Parser.CHARLITERAL);
				}


[\"][^\"]*[\"]         		{
					System.out.println("lex: found string_literal\n"); 
					yyparser.yylval = new ParserVal(yytext().substring(1,yytext().length()-2));
					return (Parser.STRINGLITERAL);
				}


[0-9]+\.[0-9]+          	{	
					System.out.println("lex: found float_literal\n");
					yyparser.yylval = new ParserVal(Double.parseDouble(yytext())); 
					return (Parser.FLOATLITERAL);
				}


([tT][rR][uU][eE])|([fF][aA][lL][sS][eE])	{
							System.out.println("lex: found bool_literal\n");
							yyparser.yylval = new ParserVal(yytext()); 
							return (Parser.BOOLLITERAL);
						}


[a-zA-Z_][a-zA-Z_0-9]*			
				   {
						System.out.println("lex: found id\n"); 
						yyparser.yylval = new ParserVal($1.sval);
						return (Parser.ID);
					}	


[\n\t ]+                        {System.out.println("lex: found whitespace or newline or tab\n");/* ignore */ } 


[^\n\t ]                        {
					System.out.println("lex: found individual char\n");
					yyparser.yylval = new ParserVal(yytext()); 
					return yycharat(0);		
					//return new ParserVal(yytext()); 
				}


(%%).*$				{/*Ignore*/}


(%%%)(.|\n)*(%%%)		{/*Ignore*/}


