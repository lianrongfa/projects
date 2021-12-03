//识别CSV的g4

grammar CSV;

file :hdr row+ ;
hdr : row ;

row : field ( ',' field )* '\r'? '\n';

field
    :   TEXT
    |   STRING
    |           //null及对应文法中的空
    ;

TEXT : ~[,\n\r"]+; //非,\n\r"等字符的其他字符
STRING : '"' ('""'|~'"')* '"' ; // 两个双引号是对双引号的转义，即允许双引号中出现双引号 eg: """","123"