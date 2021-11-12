grammar Expr;
/** 起始规则 语法分析器起点 */
prog:	stat+ ;

stat:   expr        # printExpr
    |   ID '=' expr # assign
    ;

expr:	expr op=('*'|'/') expr # MulDiv
    |	expr op=('+'|'-') expr # AddSub
    |	INT                 # int
    |   ID                  # id
    |	'(' expr ')'        # parens
    ;

ID      : [a-zA-Z]+ ;   // 匹配标识符
INT     : [0-9]+ ;      // 匹配整数
WS      : [ \r\n\t]+ -> skip ; // 丢弃空白字符

MUL     : '*' ;
DIV     : '/' ;
Add     : '+' ;
SUB     : '-' ;