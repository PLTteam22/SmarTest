/*stl.l*/
%%



%byaccj

%{


%}

%%

[iI][fF]                          { System.out.println("lex: found if\n");  }
[eE][lL][sS][eE]       		{System.out.println("lex: found else\n"); return (ELSE);}
[fF][lL][oO][aA][tT]  		{System.out.println("lex: found float\n"); return (FLOAT);}
[aA][nN][dD] 			{System.out.println("lex: found and\n"); return (AND);}	
[oO][rR]			{System.out.println("lex: found or\n"); return (OR);}
[nN][oO][tT]			{System.out.println("lex: found not\n"); return (NOT);}
[lL][oO][oO][pP]                {System.out.println("lex: found loop\n"); return (LOOP);}
[wW][hH][iI][lL][eE]            {System.out.println("lex: found while\n"); return (WHILE);}
[rR][eE][tT][uU][rR][nN]        {System.out.println("lex: found return\n"); return (RETURN);}
"!="				{System.out.println("lex: found !=\n"); return (NOTEQUAL);}
[iI][nN][Tt]                    {System.out.println("lex: found int\n"); return (INT);} 
[cC][hH][aA][rR]		{System.out.println("lex: found char\n"); return (CHAR);}
[bB][oO][oO][lL][eE][aA][nN]	{System.out.println("lex: found boolean\n"); return (BOOLEAN);}
[vV][oO][iI][dD]		{System.out.println("lex: found void\n"); return (VOID);}
[qQ][uU][eE][sS][tT][iI][oO][nN] {System.out.println("lex: found question\n"); return (QUESTION);}
[sS][eE][tT]                    {System.out.println("lex: found set\n"); return (SET);}
"<<"                            {System.out.println("lex: found <<\n"); return (INSERT);}
"=="				{System.out.println("lex: found ==\n"); return (EQUALEQUAL);}
">="				{System.out.println("lex: found >=\n"); return (GE);}
"<="				{System.out.println("lex: found <=\n"); return (LE);}
"<"				{System.out.println("lex: found <\n"); return (LT);}
">"		 		{System.out.println("lex: found >\n"); return (GT);}
"%" 				{System.out.println("lex: found percent\n"); return (MOD);}
[0-9]+ 				{System.out.println("lex: found int_literal\n"); return (INTLITERAL);}
['][^'][']           	{System.out.println("lex: found char_literal\n"); return (CHARLITERAL);}
[\"][^\"]*[\"]          	{System.out.println("lex: found string_literal\n"); return (STRINGLITERAL);}
[0-9]+\.[0-9]+              	{System.out.println("lex: found float_literal\n"); return (FLOATLITERAL);}
([tT][rR][uU][eE])|([fF][aA][lL][sS][eE])	{System.out.println("lex: found bool_literal\n"); return (BOOLLITERAL);}
[a-zA-Z_][a-zA-Z_0-9]*			{System.out.println("lex: found id\n"); return (ID);}	
[\n\t ]+                        {System.out.println("lex: found whitespace\n");/* ignore */ } 
[^\n\t ]                        {System.out.println("lex: found individual char\n"); return yytext(); }


