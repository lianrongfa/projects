//json的g4

grammar JSON;

/*
从json语法手册得知，一个JSON文件可以是一个对象，或者是一个数组，故如下
*/
json: object
    | array
    ;

object: '{' pair (','pair)* '}'
      | '{' '}' //空对象
      ;

pair: STRING ':' value;


array: '['value (',' value)* ']'
     | '[' ']'
;

value: STRING
     | NUMBER
     | object
     | array
     | 'true'
     | 'false'
     | 'null'

;

STRING : '"' (ESC|~["\\])* '"';
NUMBER
      : '-'? INT '.' INT EXP?//小数or科学计数 1.35,1.35E-9,0.3,-4.5
      | '-'? INT EXP         //1e10 ,-3e4
      | '-'? INT
      ;
fragment EXP: [Ee] [+-]? INT;

fragment INT: '0'|[1-9][0-9]*;

//转义符
fragment ESC: '\\' (["\\/bfnrt]|UNICODE);
//unicode
fragment UNICODE : 'u' HEX HEX HEX HEX;
//16进制字符
fragment HEX : [0-9a-fA-F];