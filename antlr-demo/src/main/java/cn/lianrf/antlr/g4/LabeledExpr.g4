/*简单的计算器*/
grammar LabeledExpr;
import CommonLexerRules;
/** 起始规则 语法分析器起点 */
prog:   stat+ ;

stat:   expr ';'? NEWLINE            #printExpr
    |   ID '=' expr NEWLINE     #assign
    |   NEWLINE                 #blank
    ;

expr:   expr ('*'|'/') expr     #MulDiv
    |   expr ('+'|'-') expr     #AddSub
    |   INT                     #int
    |   ID                      #id
    |   '(' expr ')'            #parens
    ;

EQUAL : '=' ;
MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;

