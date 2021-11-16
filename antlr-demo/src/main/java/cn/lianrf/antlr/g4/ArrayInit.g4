/***
*通常以为grammar开头，文件名也必须和ArrayInit同名
*/
grammar ArrayInit;

/*规则*/
init : '{' value (',' value)* '}';

value : init
      | INT
      ;
INT : [0-9]+;
WS  : [ \t\r\n]+ -> skip; //丢弃规则