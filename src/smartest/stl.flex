/*stl.flex*/
package smartest;

%%

%byaccj

%{
    private Parser yyparser;

    public Yylex(java.io.Reader r, Parser yyparser) {
        this(r);
        this.yyparser = yyparser;
    }
%}

NL    = \n | \r | \r\n

%%

[iI][fF] { 
    yyparser.column += yytext().length();
    return (Parser.IF); 
}

[eE][lL][sS][eE] {
    yyparser.column += yytext().length();
    return (Parser.ELSE);
}

[fF][lL][oO][aA][tT] {
    yyparser.column += yytext().length();
    return (Parser.FLOAT);
}

[aA][nN][dD] {
    yyparser.column += yytext().length();
    return (Parser.AND);
}	

[oO][rR] {
    yyparser.column += yytext().length();
    return (Parser.OR);
}

[nN][oO][tT] {
    yyparser.column += yytext().length();
    return (Parser.NOT);
}

[lL][oO][oO][pP] {
    yyparser.column += yytext().length();
    return (Parser.LOOP);
}

[wW][hH][iI][lL][eE] {
    yyparser.column += yytext().length();
    return (Parser.WHILE);
}

[rR][eE][tT][uU][rR][nN] {
    yyparser.column += yytext().length();
    return (Parser.RETURN);
}

"!=" {
    yyparser.column += yytext().length();
    return (Parser.NOTEQUAL);
}

[iI][nN][Tt] {
    yyparser.column += yytext().length();
    return (Parser.INT);
}

[cC][hH][aA][rR] {
    yyparser.column += yytext().length();
    return (Parser.CHAR);
}

[bB][oO][oO][lL][eE][aA][nN] {
    yyparser.column += yytext().length();
    return (Parser.BOOLEAN);
}

[vV][oO][iI][dD] {
    yyparser.column += yytext().length();
    return (Parser.VOID);
}

[qQ][uU][eE][sS][tT][iI][oO][nN] {
    yyparser.column += yytext().length();
    return (Parser.QUESTION);
}

[sS][eE][tT] {
    yyparser.column += yytext().length();
    return (Parser.SET);
}

[sS][tT][rR][iI][nN][gG] {
    yyparser.column += yytext().length();
    return (Parser.STRING);
}

[mM][oO][dD] {
    yyparser.column += yytext().length();
    return (Parser.MOD);
}

"<<" {
    yyparser.column += yytext().length();
    return (Parser.INSERT);
}

"==" {
    yyparser.column += yytext().length();
    return (Parser.EQUALEQUAL);
}

">=" {
    yyparser.column += yytext().length();
    return (Parser.GE);
}

"<=" {
    yyparser.column += yytext().length();
    return (Parser.LE);
}

"<"	{
    yyparser.column += yytext().length();
    return (Parser.LT);
}

">"	{
    yyparser.column += yytext().length();
    return (Parser.GT);
}

// int literal
[0-9]+ {
    yyparser.yylval = new ParserVal(0);
    try {
        yyparser.yylval = new ParserVal(Integer.parseInt(yytext())); 
    } catch (NumberFormatException e) {
        throw new Error("Invalid int literal at line: " + yyparser.line);
    }
	return (Parser.INTLITERAL);
}

// char literal
['][^']['] {
    yyparser.yylval = new ParserVal((int)yytext().charAt(1)); 
    return (Parser.CHARLITERAL);
}

// string literal
[\"]([^\"\\]|([\\][btnfr'\"])|([\\][\\]))*[\"] {
    yyparser.yylval = new ParserVal(yytext().substring(1,yytext().length()-1));
    return (Parser.STRINGLITERAL);
}

// float literal
[0-9]+\.[0-9]+ {	
    yyparser.yylval = new ParserVal(0.0);
    try {			
        yyparser.yylval = new ParserVal(Double.parseDouble(yytext()));
    } catch (NumberFormatException e) {
        throw new Error("Invalid float literal at line: " + yyparser.line);
    }
    return (Parser.FLOATLITERAL);
}

([tT][rR][uU][eE])|([fF][aA][lL][sS][eE]) {
    yyparser.yylval = new ParserVal(yytext()); 
    return (Parser.BOOLLITERAL);
}

// Identifier
[a-zA-Z_][a-zA-Z_0-9]* {
    yyparser.yylval = new ParserVal(yytext());
    return (Parser.ID);
}	

{NL} {
    yyparser.column = 0;
    yyparser.line++;
}
                  
[\t ]+ {
    yyparser.column += yytext().length(); /* ignore */
} 

[^\n\t ] {
    yyparser.column = 0;				
    yyparser.yylval = new ParserVal(yytext()); 
    return yycharat(0);		
}

// Multi-line comments
(%%%)(.|{NL})*(%%%)	{
    /*Ignore*/
}

// Single-line comments
(%%)(.*){NL} {
    /*Ignore*/
}