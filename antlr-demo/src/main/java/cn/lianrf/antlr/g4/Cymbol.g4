/*
  自定义实现函数语法
  有如下代码：
  int g=9;//全局变量
  int fact(int x) { //求阶乘函数
      if x==0 then return 1;
      return x * fact(x-1);
  }
*/
grammar Cymbol;


file: (stat|varDecl|functionDecl)+;

functionDecl: type ID '('formalParameters?')' bolok; //void f(int x){...}

bolok: '{' stat* '}';

stat
    : bolok
    | varDecl
    | 'if' expr 'then' stat ('else' stat)?
    | 'return' expr? END_LINE
    | expr ':=' expr END_LINE//赋值
    | expr END_LINE //调用
    ;

formalParameters: formalParameter (',' formalParameter)*;

formalParameter: type ID;

varDecl: type ID ('=' expr)? END_LINE;

expr
    :   ID '(' exprList? ')'        # Call
    |   expr '[' expr ']'           # Index
    |   ('+'|'-') expr              # Negate
    |   '!' expr                    # Not
    |   expr'%'                     # Percent
    |   expr ('*'|'/') expr         # MultDiv
    |   expr ('+'|'-') expr         # AddSub
    |   expr '=' expr               # Equal
    |   ID                          # Var
    |   literal                     # Literals
    |   '(' expr ')'                # Parens
    ;

exprList: expr (','expr)*;//参数列表

type
    : 'float'
    | 'int'
    | 'void'
    ;
literal
       :INT             # Int
       |FLOAT_POINT     # FloatPoint
       ;


ID : [a-zA-Z][a-zA-Z0-9_]*;
INT : ('0'|[1-9][0-9]*);
FLOAT_POINT: INT '.' INT EXP?;
fragment EXP: [Ee] [+-]? INT;
END_LINE: ';'|('\r'?'\n');
WS  :   [ \r\t\u000C\n]+ -> channel(HIDDEN);
