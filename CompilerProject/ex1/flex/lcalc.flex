

/* --------------------------Usercode Section------------------------ */
package src;
import java_cup.runtime.*;

%%

/* -----------------Options and Declarations Section----------------- */

/*
   The name of the class JFlex will create will be Lexer.
   Will write the code to the file Lexer.java.
*/
%class Lexer

/*
  The current line number can be accessed with the variable yyline
  and the current column number with the variable yycolumn.
*/
%line
%column

/*
   Will switch to a CUP compatibility mode to interface with a CUP
   generated parser.
*/
%cup

/*
  Declarations

  Code between %{ and %}, both of which must be at the beginning of a
  line, will be copied letter to letter into the lexer class source.
  Here you declare member variables and functions that are used inside
  scanner actions.
*/
%{
    /* To create a new java_cup.runtime.Symbol with information about
       the current token, the token will have no value in this
       case. */

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    /* Also creates a new java_cup.runtime.Symbol with information
       about the current token, but this object has a value. */

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}


/*
  Macro Declarations

  These declarations are regular expressions that will be used latter
  in the Lexical Rules Section.
*/

/* A line terminator is a \r (carriage return), \n (line feed), or \r\n. */
LineTerminator = \r|\n|\r\n

/* White space is a line terminator, space, tab, or line feed. */
WhiteSpace     = {LineTerminator} | [ \t\f]

/* A literal integer is is a number beginning with a number between
   one and nine followed by zero or more numbers between zero and nine
   or just a zero.  */
int = 0 | [1-9][0-9]*

/* A literal float */
float = [0-9]+ ("." [0-9]+)?

/* A identifier integer is a word beginning a letter between A and
   Z, a and z, or an underscore followed by zero or more letters
   between A and Z, a and z, zero and nine, or an underscore. */
id = [A-Za-z_][A-Za-z_0-9]*

boolean = true|false


%%
/* ------------------------Lexical Rules Section---------------------- */

/*
   This section contains regular expressions and actions, i.e. Java
   code, that will be executed when the scanner matches the associated
   regular expression. */
    
    /* Print the token found that was declared in the class sym and then return it. */
    "+"        { return symbol(sym.PLUS); }
    "-"        { return symbol(sym.MINUS); }
    "*"        { return symbol(sym.TIMES); }
    "/"        { return symbol(sym.DIVIDE); }
    "("        { return symbol(sym.LPAREN); }
    ")"        { return symbol(sym.RPAREN); }
    "="        { return symbol(sym.EQ); }
    "=="       { return symbol(sym.EQUALTO); }
    "<"        { return symbol(sym.LESSTHAN); }
    ">"        { return symbol(sym.GREATERTHAN); }
    "<="       { return symbol(sym.LESSTHANEQ); }
    ">="       { return symbol(sym.GREATERTHANEQ); }
    "<>"       { return symbol(sym.NOTEQUAL); }
    "&&"       { return symbol(sym.AND); }
    "||"       { return symbol(sym.OR); }
    "!"        { return symbol(sym.NOT); }
    "print"    { return symbol(sym.PRINT); }
    "if"       { return symbol(sym.IF); }
    "then"     { return symbol(sym.THEN); }
    "else"     {return symbol(sym.ELSE);  }
    "endif"    {return symbol(sym.ENDIF);  }
    "{"        { return symbol(sym.BEGIN); }
    "}"        { return symbol(sym.END); }
    "while"    { return symbol(sym.WHILE); }
    "do"       { return symbol(sym.DO); }
    "bool"     { return symbol(sym.BOOL); }
    "int"      { return symbol(sym.INT); }
    "float"    { return symbol(sym.FLOAT); }
    "void"     { return symbol(sym.VOID); }
    "return"   { return symbol(sym.RETURN);}

    {int}      {return symbol(sym.INTEGER, new Integer(yytext()));}
    {float}    {return symbol(sym.FLOATING, new Float(yytext()));}
    {boolean}  {return symbol(sym.BOOLEAN,new Boolean(yytext()));}

    {id}       { return symbol(sym.ID, yytext());}

    /* Return the token SEMI declared in the class sym that was found. */
    ";"        { return symbol(sym.SEMI); }

    /* Don't do anything if whitespace is found */

    {WhiteSpace}       { /* just skip what was found, do nothing */ }


/* No token was found for the input so through an error.  Print out an
   Illegal character message with the illegal character that was found. */

[^]        { throw new Error("Illegal character <"+yytext()+">"); }
