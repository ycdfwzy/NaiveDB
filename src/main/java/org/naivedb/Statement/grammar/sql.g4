grammar sql;

parse
 : ( sql_stmt_list | error )* EOF
 ;

error
  : UNEXPECTED_CHAR
    {
      throw new RuntimeException("UNEXPECTED_CHAR=" + $UNEXPECTED_CHAR.text );
    }
  ;

sql_stmt_list
  : sql_stmt ( ';'+ sql_stmt )* ';'
  ;

sql_stmt
  : create_db_stmt
  | create_table_stmt
  | drop_stmt
  | insert_stmt
  | delete_stmt
  | update_stmt
  | select_stmt
  | use_stmt
  | show_stmt
  ;

create_db_stmt
  : K_CREATE K_DATABASE db_name
  ;

create_table_stmt
  : K_CREATE K_TABLE
    table_name
    ( '(' attr_name type_name (K_NOT K_NULL)? ( ',' attr_name type_name (K_NOT K_NULL)? )* ( ',' K_PRIMARY K_KEY '(' attr_name ')' )? ')'
    )
  ;

drop_stmt
  : K_DROP ( K_TABLE | K_DATABASE ) ( table_name | db_name )
  ;

insert_stmt
  : K_INSERT K_INTO table_name ( '(' attr_name ( ',' attr_name )* ')' )?
    ( K_VALUES '(' expr ( ',' expr )* ')' ( ',' '(' expr ( ',' expr )* ')' )*
    )
  ;

delete_stmt
  : K_DELETE K_FROM table_name ( K_WHERE pred_expr )?
  ;

update_stmt
  : K_UPDATE table_name K_SET asign_clause ( K_WHERE pred_expr )?
  ;

select_stmt
  : K_SELECT select_elements K_FROM range ( K_WHERE pred_expr )?
  ;

use_stmt
  : K_USE db_name
  ;

show_stmt
  : K_SHOW K_DATABASES
  | K_SHOW K_DATABASE db_name
  | K_SHOW K_TABLE table_name
  ;

select_elements
  : '*'
  | expr_column ( ',' expr_column )*
  ;

asign_clause
  : expr_column '=' expr (',' expr_column '=' expr)*
  ;

range
  : table_name
  | join_range
  | product_range
  ;

//join_range // rangeVariable
//  : table_name
//  | join_ranges // rangeVariable[]
//  ;

//
//join_ranges // rangeVariable[]
//  : single_range ( natural_join | join_on )+
//  ;

join_range // rangeVariable[]
  : single_range ( natural_join | join_on )+
  ;

product_range
  : ( table_name | join_range | '(' product_range ')' ) ( ',' range )+
  ;

//product_range
//  : ',' join_range
//  ;

natural_join // rangeVariable
  : K_NATURAL ( outer_join | inner_join )
  ;

join_on
  : ( outer_join | inner_join ) K_ON pred_expr
  ;

outer_join
  : ( K_LEFT | K_RIGHT | K_FULL ) K_OUTER K_JOIN single_range
  ;

inner_join
  : ( K_INNER )? K_JOIN single_range
  ;

single_range
  : table_name | '(' range ')'
  ;

db_name
  : IDENTIFIER;

table_name
  : IDENTIFIER;

attr_name
  : IDENTIFIER;

type_name
  : K_INT
  | K_LONG
  | K_FLOAT
  | K_DOUBLE
  | K_STRING '(' INTEGER_LITERAL ')'
  ;

expr
  : numeric_expr
  | TEXT_STRING
  ;

numeric_expr
  : numeric_value
  | expr_column
  | unary_operator numeric_expr
  | numeric_expr ( '*' | '/' | '%' ) numeric_expr
  | numeric_expr ( '+' | '-' ) numeric_expr
  | '(' numeric_expr ')'
  ;

expr_column
  :  ( table_name '.' )? attr_name
  ;

unary_operator
  : '-'
  | '+'
  | '~'
  | K_NOT
  ;

pred_expr
  : expr ( '<' | '<=' | '>' | '>=' | '<>' | '==' | '=' | '!=' ) expr
  | pred_expr ( K_AND | K_OR ) pred_expr
  ;

numeric_value
  : INTEGER_LITERAL
  | FLOAT_LITERAL
  ;

SPACES
  : [ \u000B\t\r\n] -> channel(HIDDEN)
  ;

TEXT_STRING
  : '"' ( ~'"' | '\\"' )* '"'
  | '\'' ( ~'\'' | '\\\'' )* '\''
  ;

K_CREATE : C R E A T E;
K_DATABASE : D A T A B A S E;
K_DATABASES : D A T A B A S E S;
K_TABLE : T A B L E;
K_TABLES : T A B L E S;
K_NOT : N O T;
K_NULL : N U L L;
K_PRIMARY : P R I M A R Y;
K_KEY : K E Y;
K_DROP : D R O P;
K_INSERT : I N S E R T;
K_INTO : I N T O;
K_VALUES : V A L U E S;
K_DELETE : D E L E T E;
K_FROM : F R O M;
K_WHERE : W H E R E;
K_UPDATE : U P D A T E;
K_SET : S E T;
K_SELECT : S E L E C T;
K_NATURAL : N A T U R A L;
K_LEFT : L E F T;
K_RIGHT : R I G H T;
K_FULL : F U L L;
K_OUTER : O U T E R;
K_INNER : I N N E R;
K_JOIN : J O I N;
K_ON : O N;
K_USE : U S E;
K_SHOW : S H O W;
K_AND : A N D;
K_OR : O R;
K_INT : I N T;
K_LONG : L O N G;
K_FLOAT : F L O A T;
K_DOUBLE : D O U B L E;
K_STRING : S T R I N G;

IDENTIFIER
  : [a-zA-Z_] ([a-zA-Z_0-9])*
  ;

INTEGER_LITERAL
  : DIGIT+
  ;

FLOAT_LITERAL
  : DIGIT+ ( '.' DIGIT* )? ( E [-+]? DIGIT+ )?
  | '.' DIGIT+ ( E [-+]? DIGIT+ )?
  ;

UNEXPECTED_CHAR
  : .
  ;

fragment DIGIT : [0-9];

fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];
