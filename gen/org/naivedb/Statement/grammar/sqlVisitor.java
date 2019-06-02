// Generated from /Users/leeda/THU/SixthSem/DB/homework/NaiveDB/src/main/java/org/naivedb/Statement/grammar/sql.g4 by ANTLR 4.7.2
package org.naivedb.Statement.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link sqlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface sqlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link sqlParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(sqlParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitError(sqlParser.ErrorContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#sql_stmt_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql_stmt_list(sqlParser.Sql_stmt_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#sql_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql_stmt(sqlParser.Sql_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#create_db_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_db_stmt(sqlParser.Create_db_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#create_table_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_table_stmt(sqlParser.Create_table_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#drop_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDrop_stmt(sqlParser.Drop_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#insert_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert_stmt(sqlParser.Insert_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#delete_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete_stmt(sqlParser.Delete_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#update_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate_stmt(sqlParser.Update_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#select_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_stmt(sqlParser.Select_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#use_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUse_stmt(sqlParser.Use_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#show_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow_stmt(sqlParser.Show_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#select_elements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_elements(sqlParser.Select_elementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#asign_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsign_clause(sqlParser.Asign_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#join_range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoin_range(sqlParser.Join_rangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#join_ranges}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoin_ranges(sqlParser.Join_rangesContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#natural_join}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNatural_join(sqlParser.Natural_joinContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#join_on}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoin_on(sqlParser.Join_onContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#outer_join}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOuter_join(sqlParser.Outer_joinContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#inner_join}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInner_join(sqlParser.Inner_joinContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#db_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDb_name(sqlParser.Db_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#table_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_name(sqlParser.Table_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#attr_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr_name(sqlParser.Attr_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_name(sqlParser.Type_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(sqlParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#numeric_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric_expr(sqlParser.Numeric_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#expr_column}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_column(sqlParser.Expr_columnContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#unary_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_operator(sqlParser.Unary_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#pred_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPred_expr(sqlParser.Pred_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#numeric_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric_value(sqlParser.Numeric_valueContext ctx);
}