/*
  自定义实现函数语法
  有如下代码：
  int g=9;//全局变量
  int fact(int x) { //求阶乘函数
      if x==0 then return 1;
      return x * fact(x-1);
  }
*/
grammar Agile;

file: (simpStat|varDecl|functionDecl)+;

functionDecl: 'func' Identifier '('formalParameters?')' bolok; //void f(int x){...}

bolok: '{' stat* '}';

simpStat
    :statIfJava END?
    |statIf END?
    |expr END?
    ;

stat
    : bolok
    | varDecl
    | statIfJava|statIf
    | 'return' expr? END
    | expr ':=' expr END//赋值
    | expr END //调用
    ;

statIfJava: 'if' '(' comparison ')' (bolok|expr) ('else' (statIfJava|bolok|expr))? ;
statIf:'if' comparison 'then' (bolok|expr) ('else' (statIf|bolok|expr))?;


formalParameters : formalParameter (',' formalParameter)*;

formalParameter: Identifier;

varDecl: Identifier (':=' expr)? END;

expr
    :Identifier '(' exprList? ')'  # Call
    |expr '[' expr ']'             # Index
    |('+'|'-') expr                # Negate
    |'!' expr                      # Not
    |expr '%'                      # Percent
    |expr ('*'|'/') expr           # MultDiv
    |expr ('+'|'-') expr           # AddSub
    |expr COMP_OP expr             # Condition
    |Identifier                    # Id
    |literal                       # Literals
    |'(' expr ')'                  # Parens
    ;

comparison:expr (COMP_OP expr)?;

exprList: expr (','expr)*;//参数列表

literal:number          # Num
       |STRING          # Str
       ;
number:
    INT             # Int
    |FLOAT_POINT    # FloatPoint
    ;

INT : ('0'|[1-9][0-9]*);
FLOAT_POINT: INT '.' INT EXP?;
COMP_OP: '<'|'>'|'='|'>='|'<='|'!='|'<>';

fragment EXP: [Ee] [+-]? INT;

//IGNORE_NEWLINE: '\r'?'\n' {nesting==1}? -> skip;
END: ';';
//NEWLINE: '\r'?'\n';
WS  :   [ \t\r\n]+ -> skip;

STRING : '"' (ESC|~["\\])* '"'
       | '\'' (ESC|~['\\])* '\''
       ;

Identifier
         :Letter (Letter|JavaIDDigit)*
         | '#' Identifier '#'
         ;

Letter
    :  '\u0024' |
       '\u0041'..'\u005a' |
       '\u005f' |
       '\u0061'..'\u007a' |
       '\u00c0'..'\u00d6' |
       '\u00d8'..'\u00f6' |
       '\u00f8'..'\u00ff' |
       '\u0100'..'\u1fff' |
       '\u3040'..'\u318f' |
       '\u3300'..'\u337f' |
       '\u3400'..'\u3d2d' |
       '\u4e00'..'\u9fff' |
       '\uf900'..'\ufaff'
    ;
//转义符
fragment ESC: '\\' (['"\\/bfnrt]|UNICODE);
//unicode
fragment UNICODE : 'u' HEX HEX HEX HEX;
//16进制字符
fragment HEX : [0-9a-fA-F];

fragment JavaIDDigit
    :  '\u0030'..'\u0039' |
       '\u0660'..'\u0669' |
       '\u06f0'..'\u06f9' |
       '\u0966'..'\u096f' |
       '\u09e6'..'\u09ef' |
       '\u0a66'..'\u0a6f' |
       '\u0ae6'..'\u0aef' |
       '\u0b66'..'\u0b6f' |
       '\u0be7'..'\u0bef' |
       '\u0c66'..'\u0c6f' |
       '\u0ce6'..'\u0cef' |
       '\u0d66'..'\u0d6f' |
       '\u0e50'..'\u0e59' |
       '\u0ed0'..'\u0ed9' |
       '\u1040'..'\u1049'
   ;