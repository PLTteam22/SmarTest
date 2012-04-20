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

NL    = \n | \r | \r\n

%%

[iI][fF]           { 
						yyparser.column += yytext().length();
						System.out.println("lex: found if\n"); return (Parser.IF); 
				  }



[eE][lL][sS][eE]       		{ yyparser.column += yytext().length(); System.out.println("lex: found else\n"); return (Parser.ELSE);}



[fF][lL][oO][aA][tT]  		{ yyparser.column += yytext().length(); System.out.println("lex: found float\n"); return (Parser.FLOAT);}


[aA][nN][dD] 			{ yyparser.column += yytext().length(); System.out.println("lex: found and\n"); return (Parser.AND);}	


[oO][rR]			{ yyparser.column += yytext().length(); System.out.println("lex: found or\n"); return (Parser.OR);}


[nN][oO][tT]			{ yyparser.column += yytext().length(); System.out.println("lex: found not\n"); return (Parser.NOT);}


[lL][oO][oO][pP]                { yyparser.column += yytext().length(); System.out.println("lex: found loop\n"); return (Parser.LOOP);}


[wW][hH][iI][lL][eE]            { yyparser.column += yytext().length(); System.out.println("lex: found while\n"); return (Parser.WHILE);}


[rR][eE][tT][uU][rR][nN]        { yyparser.column += yytext().length(); System.out.println("lex: found return\n"); return (Parser.RETURN);}


"!="				{ yyparser.column += yytext().length(); System.out.println("lex: found !=\n"); return (Parser.NOTEQUAL);}


[iI][nN][Tt]                    { yyparser.column += yytext().length(); System.out.println("lex: found int\n"); return (Parser.INT);} 


[cC][hH][aA][rR]		{ yyparser.column += yytext().length(); System.out.println("lex: found char\n"); return (Parser.CHAR);}


[bB][oO][oO][lL][eE][aA][nN]	{ yyparser.column += yytext().length(); System.out.println("lex: found boolean\n"); return (Parser.BOOLEAN);}


[vV][oO][iI][dD]		{ yyparser.column += yytext().length(); System.out.println("lex: found void\n"); return (Parser.VOID);}


[qQ][uU][eE][sS][tT][iI][oO][nN] { yyparser.column += yytext().length(); System.out.println("lex: found question\n"); return (Parser.QUESTION);}


[sS][eE][tT]                    { yyparser.column += yytext().length(); System.out.println("lex: found set\n"); return (Parser.SET);}

[sS][tT][rR][iI][nN][gG]                    { yyparser.column += yytext().length(); System.out.println("lex: found string\n"); return (Parser.STRING);}


"<<"                            { yyparser.column += yytext().length(); System.out.println("lex: found <<\n"); return (Parser.INSERT);}


"=="				{ yyparser.column += yytext().length(); System.out.println("lex: found ==\n"); return (Parser.EQUALEQUAL);}


">="				{ yyparser.column += yytext().length(); System.out.println("lex: found >=\n"); return (Parser.GE);}


"<="				{ yyparser.column += yytext().length(); System.out.println("lex: found <=\n"); return (Parser.LE);}


"<"				{ yyparser.column += yytext().length(); System.out.println("lex: found <\n"); return (Parser.LT);}


">"		 		{ yyparser.column += yytext().length(); System.out.println("lex: found >\n"); return (Parser.GT);}


"%" 				{ yyparser.column += yytext().length(); System.out.println("lex: found percent\n"); return (Parser.MOD);}


/*
"("				{ yyparser.column += yytext().length(); System.out.println("lex: found percent\n"); return (Parser.LPARAN);}


")"				{ yyparser.column += yytext().length(); System.out.println("lex: found percent\n"); return (Parser.RPARAN);}


"{"				{ yyparser.column += yytext().length(); System.out.println("lex: found percent\n"); return (Parser.LCURLY);}


"}"				{ yyparser.column += yytext().length(); System.out.println("lex: found percent\n"); return (Parser.RCURLY);}


","				{ yyparser.column += yytext().length(); System.out.println("lex: found percent\n"); return (Parser.COMMA);}


";"				{ yyparser.column += yytext().length(); System.out.println("lex: found percent\n"); return (Parser.SEMICOLON);}
	

"="				{ yyparser.column += yytext().length(); System.out.println("lex: found percent\n"); return (Parser.EQUAL);}


"$"				{ yyparser.column += yytext().length(); System.out.println("lex: found percent\n"); return (Parser.DOLLAR);}


"["				{ yyparser.column += yytext().length(); System.out.println("lex: found percent\n"); return (Parser.LSQUARE);}


"]"				{ yyparser.column += yytext().length(); System.out.println("lex: found percent\n"); return (Parser.RSQUARE);}


":"				{ yyparser.column += yytext().length(); System.out.println("lex: found percent\n"); return (Parser.COLON);}


"+"				{ yyparser.column += yytext().length(); System.out.println("lex: found percent\n"); return (Parser.PLUS);}


"-"				{ yyparser.column += yytext().length(); System.out.println("lex: found percent\n"); return (Parser.MINUS);}


"*"				{ yyparser.column += yytext().length(); System.out.println("lex: found percent\n"); return (Parser.MULTIPLY);}


"/"				{ yyparser.column += yytext().length(); System.out.println("lex: found percent\n"); return (Parser.DIVIDE);}

*/






[0-9]+ 				{
					System.out.println("lex: found int_literal\n");
					yyparser.yylval = new ParserVal(Integer.parseInt(yytext())); 
					return (Parser.INTLITERAL);
				}


['][^'][']           		{
					System.out.println("lex: found char_literal\n");
					yyparser.yylval = new ParserVal(Integer.parseInt(yytext())); 
					return (Parser.CHARLITERAL);
				}

[\"]([^\"\\]|([\\][btnfr'\"])|([\\][\\]))*[\"]        		{
					System.out.println("lex: found string_literal\n"); 
					yyparser.yylval = new ParserVal(yytext().substring(1,yytext().length()-1));
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
						yyparser.yylval = new ParserVal(yytext());
						return (Parser.ID);
					}	


{NL}              {
                    yyparser.column = 0;
                    yyparser.line++;
                  }
                  
[\t ]+                        { yyparser.column += yytext().length(); System.out.println("lex: found whitespace or newline or tab\n");/* ignore */ } 


[^\n\t ]                        {
					yyparser.column = 0;
					System.out.println("lex: found individual char: " + yytext() + "\n");
					yyparser.yylval = new ParserVal(yytext()); 
					return yycharat(0);		
					//return new ParserVal(yytext()); 
				}


(%%)[^%\n]*$				{/*Ignore*/}


(%%%)(.|{NL})*(%%%)		{/*Ignore*/}


