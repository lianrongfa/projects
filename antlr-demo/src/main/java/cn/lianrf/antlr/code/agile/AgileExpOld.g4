/*
  自定义实现函数语法
  有如下代码：
  int g=9;//全局变量
  int fact(int x) { //求阶乘函数
      if x==0 then return 1;
      return x * fact(x-1);
  }
*/
grammar AgileExpOld;

file: simpStat;

simpStat
    :statIfJava END?
    |statIf END?
    |expr END?
    ;

statIfJava: IF '(' comparison ')' (block|expr) ('else' (statIfJava|block|expr))? ;
statIf:IF comparison 'then' (block|expr) ('else' (statIf|block|expr))?;

block: '{' stat* '}';

stat
    : block
    | statIfJava
    | statIf
    | expr END
    ;

expr
    :   primary                       # basic
//  |   '$' '(' expr ')'              # Dot
    |   expr '.' IDENTIFIER           # Attr
    |   '$' '(' expr ')'              # Dot
    |   expr '[' expr ']'             # Index
    |   expr '(' exprList? ')'        # Call
    |   ('+'|'-') expr                # Negate
    |   '!' expr                      # Not
    |   expr '%'                      # Percent
    |   expr ('*'|'/') expr           # MultDiv
    |   expr ('+'|'-') expr           # AddSub
    |   expr COMP_OP expr             # Condition
    ;

primary
       :  '(' expr ')' # Parens
       |   literal     # Literals
       |   IDENTIFIER  # Id
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

IDENTIFIER
         :LETTER (LETTER|JAVA_ID_DIGIT)*
         | '#' IDENTIFIER '#'
         ;

LETTER
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

fragment JAVA_ID_DIGIT
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
IF : 'if';
BLOCK_LEFT:'{';
BLOCK_RIGHT:'}';

ROUND_LEFT:'(';
ROUND_RIGHT:')';
