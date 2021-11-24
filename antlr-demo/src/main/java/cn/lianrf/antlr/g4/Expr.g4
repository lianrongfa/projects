/*简单的计算器*/
grammar Expr;
/** 起始规则 语法分析器起点 */
prog:   stat+ ;

stat:   expr
    |   ID '=' expr
    ;

expr:   <assoc=right>expr ('*'|'/') expr
    |   expr ('+'|'-') expr
    |   INT
    |   ID
    |   '(' expr ')'
    |   ID '(' expr ( ',' expr)* ')'
    ;

ID  :   [a-zA-Z]+ ;      // 匹配标识符
INT :   [0-9]+ ;         // 匹配整数
NEWLINE:'\r'? '\n' ;     // 换行符
WS  :   [ \t]+ -> skip ; // 丢弃空白字符