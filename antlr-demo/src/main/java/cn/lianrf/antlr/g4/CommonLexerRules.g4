/*提取公共的词法规则*/
lexer grammar CommonLexerRules;

ID  :   [a-zA-Z]+ ;      // 匹配标识符
INT :   [0-9]+ ;         // 匹配整数
NEWLINE:'\r'? '\n' ;     // 换行符
WS  :   [ \t]+ -> skip ; // 丢弃空白字符