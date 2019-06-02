// Generated from D:/gitRepo/NaiveDB/src/main/java/org/naivedb/Statement/grammar\sql.g4 by ANTLR 4.7.2
package org.naivedb.Statement.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link sqlParser}.
 */
public interface sqlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link sqlParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(sqlParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(sqlParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#error}.
	 * @param ctx the parse tree
	 */
	void enterError(sqlParser.ErrorContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#error}.
	 * @param ctx the parse tree
	 */
	void exitError(sqlParser.ErrorContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#sql_stmt_list}.
	 * @param ctx the parse tree
	 */
	void enterSql_stmt_list(sqlParser.Sql_stmt_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#sql_stmt_list}.
	 * @param ctx the parse tree
	 */
	void exitSql_stmt_list(sqlParser.Sql_stmt_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#sql_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSql_stmt(sqlParser.Sql_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#sql_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSql_stmt(sqlParser.Sql_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#create_db_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCreate_db_stmt(sqlParser.Create_db_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#create_db_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCreate_db_stmt(sqlParser.Create_db_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#create_table_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCreate_table_stmt(sqlParser.Create_table_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#create_table_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCreate_table_stmt(sqlParser.Create_table_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#drop_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDrop_stmt(sqlParser.Drop_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#drop_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDrop_stmt(sqlParser.Drop_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#insert_stmt}.
	 * @param ctx the parse tree
	 */
	void enterInsert_stmt(sqlParser.Insert_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#insert_stmt}.
	 * @param ctx the parse tree
	 */
	void exitInsert_stmt(sqlParser.Insert_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#delete_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDelete_stmt(sqlParser.Delete_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#delete_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDelete_stmt(sqlParser.Delete_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#update_stmt}.
	 * @param ctx the parse tree
	 */
	void enterUpdate_stmt(sqlParser.Update_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#update_stmt}.
	 * @param ctx the parse tree
	 */
	void exitUpdate_stmt(sqlParser.Update_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#select_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSelect_stmt(sqlParser.Select_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#select_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSelect_stmt(sqlParser.Select_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#use_stmt}.
	 * @param ctx the parse tree
	 */
	void enterUse_stmt(sqlParser.Use_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#use_stmt}.
	 * @param ctx the parse tree
	 */
	void exitUse_stmt(sqlParser.Use_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#show_stmt}.
	 * @param ctx the parse tree
	 */
	void enterShow_stmt(sqlParser.Show_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#show_stmt}.
	 * @param ctx the parse tree
	 */
	void exitShow_stmt(sqlParser.Show_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#select_elements}.
	 * @param ctx the parse tree
	 */
	void enterSelect_elements(sqlParser.Select_elementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#select_elements}.
	 * @param ctx the parse tree
	 */
	void exitSelect_elements(sqlParser.Select_elementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#asign_clause}.
	 * @param ctx the parse tree
	 */
	void enterAsign_clause(sqlParser.Asign_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#asign_clause}.
	 * @param ctx the parse tree
	 */
	void exitAsign_clause(sqlParser.Asign_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#join_range}.
	 * @param ctx the parse tree
	 */
	void enterJoin_range(sqlParser.Join_rangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#join_range}.
	 * @param ctx the parse tree
	 */
	void exitJoin_range(sqlParser.Join_rangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#join_ranges}.
	 * @param ctx the parse tree
	 */
	void enterJoin_ranges(sqlParser.Join_rangesContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#join_ranges}.
	 * @param ctx the parse tree
	 */
	void exitJoin_ranges(sqlParser.Join_rangesContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#natural_join}.
	 * @param ctx the parse tree
	 */
	void enterNatural_join(sqlParser.Natural_joinContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#natural_join}.
	 * @param ctx the parse tree
	 */
	void exitNatural_join(sqlParser.Natural_joinContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#join_on}.
	 * @param ctx the parse tree
	 */
	void enterJoin_on(sqlParser.Join_onContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#join_on}.
	 * @param ctx the parse tree
	 */
	void exitJoin_on(sqlParser.Join_onContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#outer_join}.
	 * @param ctx the parse tree
	 */
	void enterOuter_join(sqlParser.Outer_joinContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#outer_join}.
	 * @param ctx the parse tree
	 */
	void exitOuter_join(sqlParser.Outer_joinContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#inner_join}.
	 * @param ctx the parse tree
	 */
	void enterInner_join(sqlParser.Inner_joinContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#inner_join}.
	 * @param ctx the parse tree
	 */
	void exitInner_join(sqlParser.Inner_joinContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#db_name}.
	 * @param ctx the parse tree
	 */
	void enterDb_name(sqlParser.Db_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#db_name}.
	 * @param ctx the parse tree
	 */
	void exitDb_name(sqlParser.Db_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#table_name}.
	 * @param ctx the parse tree
	 */
	void enterTable_name(sqlParser.Table_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#table_name}.
	 * @param ctx the parse tree
	 */
	void exitTable_name(sqlParser.Table_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#attr_name}.
	 * @param ctx the parse tree
	 */
	void enterAttr_name(sqlParser.Attr_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#attr_name}.
	 * @param ctx the parse tree
	 */
	void exitAttr_name(sqlParser.Attr_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#type_name}.
	 * @param ctx the parse tree
	 */
	void enterType_name(sqlParser.Type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#type_name}.
	 * @param ctx the parse tree
	 */
	void exitType_name(sqlParser.Type_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(sqlParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(sqlParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#numeric_expr}.
	 * @param ctx the parse tree
	 */
	void enterNumeric_expr(sqlParser.Numeric_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#numeric_expr}.
	 * @param ctx the parse tree
	 */
	void exitNumeric_expr(sqlParser.Numeric_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#expr_column}.
	 * @param ctx the parse tree
	 */
	void enterExpr_column(sqlParser.Expr_columnContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#expr_column}.
	 * @param ctx the parse tree
	 */
	void exitExpr_column(sqlParser.Expr_columnContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#unary_operator}.
	 * @param ctx the parse tree
	 */
	void enterUnary_operator(sqlParser.Unary_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#unary_operator}.
	 * @param ctx the parse tree
	 */
	void exitUnary_operator(sqlParser.Unary_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#pred_expr}.
	 * @param ctx the parse tree
	 */
	void enterPred_expr(sqlParser.Pred_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#pred_expr}.
	 * @param ctx the parse tree
	 */
	void exitPred_expr(sqlParser.Pred_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#numeric_value}.
	 * @param ctx the parse tree
	 */
	void enterNumeric_value(sqlParser.Numeric_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#numeric_value}.
	 * @param ctx the parse tree
	 */
	void exitNumeric_value(sqlParser.Numeric_valueContext ctx);
}