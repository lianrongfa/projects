grammar Test;

pro:exp+;

exp
   : '{'INT (','INT)* '}'
   | exp '*' exp
   | exp '/' exp
   | exp '+' exp
   | exp '-' exp
   | exp op='%' exp
   | ID
   | INT
    ;

ID: [a-zA-Z];
INT: [0-9];
END: ';';

