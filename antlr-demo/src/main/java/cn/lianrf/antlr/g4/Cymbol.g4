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


file: (simpStat|varDecl|functionDecl)+;

functionDecl: type ID '('formalParameters?')' bolok; //void f(int x){...}

bolok: '{' stat* '}';

stat
    : bolok
    | varDecl
    | 'if' expr 'then' stat ('else' stat)?
    | 'return' expr? END
    | expr ':=' expr END//赋值
    | expr END //调用
    ;
simpStat
    :'if' expr 'then' simpStat ('else' simpStat)? #simpIf
    | expr END?                                   #simpExpr
    | expr ':=' expr END?                         #simpAss
    ;


formalParameters: formalParameter (',' formalParameter)*;

formalParameter: type ID;

varDecl: type ID ('=' expr)? END;

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

//IGNORE_NEWLINE: '\r'?'\n' {nesting==1}? -> skip;
END: ';'
   //|//'\n'
;
//NEWLINE: '\r'?'\n';
WS  :   [ \t\r\n]+ -> skip;


