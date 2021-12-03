/*
R语言语法规则
*/

grammar R;

prog : (expr_or_assign (';'|NL)
        |NL
        )* EOF;

expr_or_assign
    : expr ('<-'|'='|'<<-') expr_or_assign
    | expr
;

expr: ' ';

operator_expr
    :
;
NL:'\r'?'\n';