grammar test;

expr: expr '+' expr
    | expr '*' expr
    | INT
    ;
INT: [0-9]+;