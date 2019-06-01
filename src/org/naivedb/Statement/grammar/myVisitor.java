package org.naivedb.Statement.grammar;

import javafx.util.Pair;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.naivedb.Statement.*;
import org.naivedb.Type.Type;
import org.naivedb.utils.NDException;

import java.util.ArrayList;
import java.util.LinkedList;

public class myVisitor extends sqlBaseVisitor {

    public myVisitor() {
        super();
    }

    @Override
    public Object visitParse(sqlParser.ParseContext ctx) {
        int n = ctx.getChildCount();
        ArrayList res = new ArrayList<>();
        for (int i = 0; i < n-1; ++i)
            res.addAll((ArrayList)visit(ctx.getChild(i)));
        return res;
    }

    @Override
    public Object visitError(sqlParser.ErrorContext ctx) {
        return null;
    }

    @Override
    public Object visitSql_stmt_list(sqlParser.Sql_stmt_listContext ctx) {
        System.out.println(ctx.getText());
        int n = ctx.getChildCount();
        ArrayList res = new ArrayList();
        for (int i = 0; i < n; i += 2)
            res.add(visit(ctx.getChild(i)));
        return res;
    }

    @Override
    public Object visitSql_stmt(sqlParser.Sql_stmtContext ctx) {
        System.out.println(ctx.getText());
        return visit(ctx.getChild(0));
    }

    @Override
    public Object visitCreate_db_stmt(sqlParser.Create_db_stmtContext ctx) {
        String dbName = (String) visit(ctx.getChild(2));
        return new StatementCreateDatabase(dbName);
    }

    @Override
    public Object visitCreate_table_stmt(sqlParser.Create_table_stmtContext ctx) {
        String tbName = (String) visit(ctx.getChild(2));
        int n = ctx.getChildCount();
        String primaryKey = null;
        ArrayList<Pair<String, Type>> cols = new ArrayList<>();
        ArrayList<Boolean> notNull = new ArrayList<>();
        for (int i = 4; i < n; i += 1) {
            String colName = (String) visit(ctx.getChild(i));
            i += 1;
            if (colName.compareTo("PRIMARY") == 0) {
                primaryKey = ctx.getChild(i+2).getText();
                break;
            }

            Type colType = (Type) visit(ctx.getChild(i));
            i += 1;

            cols.add(new Pair<>(colName, colType));

            if (ctx.getChild(i).getText().toUpperCase().compareTo("NOT") == 0) {
                i += 2;
                notNull.add(true);
            } else
                notNull.add(false);
        }
        return new StatementCreateTable(tbName, cols, notNull, primaryKey);
    }

    @Override
    public Object visitDrop_stmt(sqlParser.Drop_stmtContext ctx) {
        String name = ctx.getChild(2).getText().toUpperCase();
        if (ctx.getChild(1).getText().toUpperCase().compareTo("TABLE") == 0) {
            return new StatementDropTable(name);
        }
        return new StatementDropDatabase(name);
    }

    @Override
    public Object visitInsert_stmt(sqlParser.Insert_stmtContext ctx) {
        return null;
    }

    @Override
    public Object visitDelete_stmt(sqlParser.Delete_stmtContext ctx) {
        return null;
    }

    @Override
    public Object visitUpdate_stmt(sqlParser.Update_stmtContext ctx) {
        return null;
    }

    @Override
    public Object visitSelect_stmt(sqlParser.Select_stmtContext ctx) {
        return null;
    }

    @Override
    public Object visitUse_stmt(sqlParser.Use_stmtContext ctx) {
        return null;
    }

    @Override
    public Object visitShow_stmt(sqlParser.Show_stmtContext ctx) {
        return null;
    }

    @Override
    public Object visitSelect_elements(sqlParser.Select_elementsContext ctx) {
        return null;
    }

    @Override
    public Object visitAsign_clause(sqlParser.Asign_clauseContext ctx) {
        int n = ctx.getChildCount();
        LinkedList<String> colList = new LinkedList<>();
        LinkedList<Expression> exprList = new LinkedList<>();
        for (int i = 0; i < n; i += 4) {
            String colName = (String) visit(ctx.getChild(i));
            Expression expr = (Expression) visit(ctx.getChild(i+2));
            colList.add(colName);
            exprList.add(expr);
        }
        return new Pair<>(colList, exprList);
    }

    @Override
    public Object visitDb_name(sqlParser.Db_nameContext ctx) {
        return ctx.getText();
    }

    @Override
    public Object visitTable_name(sqlParser.Table_nameContext ctx) {
        return ctx.getText();
    }

    @Override
    public Object visitAttr_name(sqlParser.Attr_nameContext ctx) {
        return ctx.getText();
    }

    @Override
    public Object visitType_name(sqlParser.Type_nameContext ctx) {
        char[] tc = ctx.getText().toLowerCase().toCharArray();
        tc[0] -= 32;
        String t = new String(tc);

        if (t.startsWith("String")) {
            t = "String" + t.substring(7, t.length()-1);
        }

        Type type;
        try {
            type = new Type(t);
        } catch (NDException e)
        {
            throw new ParseCancellationException(e);
        }
        return type;
    }

    @Override
    public Object visitExpr(sqlParser.ExprContext ctx) {
        try {
            Expression expr;
            if (ctx.getChild(0).getText().compareTo("\"") == 0) {
                expr = new Expression(4, ctx.getChild(1).getText());
            } else
            {
                expr = (Expression) visit(ctx.getChild(0));
            }
            return expr.simplify();
        }   catch (NDException e)
        {
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public Object visitNumeric_expr(sqlParser.Numeric_exprContext ctx) {
        try {
            // numeric_value or expr_column
            if (ctx.getChildCount() == 1) {
                return visit(ctx.getChild(0));
            }
            // unary_operator numeric_expr
            if (ctx.getChildCount() == 2) {
                Expression expr = (Expression) visit(ctx.getChild(1));
                return new Expression(2, ctx.getChild(0).getText(), expr);
            }
            // ( numeric_expr )
            if (ctx.getChild(0).getText().compareTo("\"") == 0) {
                return visit(ctx.getChild(1));
            }
            // numeric_expr op numeric_expr
            Expression expr1 = (Expression) visit(ctx.getChild(0));
            Expression expr2 = (Expression) visit(ctx.getChild(2));
            String op = ctx.getChild(1).getText();
            return new Expression(3, op, expr1, expr2);
        } catch (NDException e) {
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public Object visitExpr_column(sqlParser.Expr_columnContext ctx) {
        Expression expr;
        try {
            expr = new Expression(1, ctx.getText().toUpperCase());
        } catch (NDException e) {
            throw new ParseCancellationException(e);
        }

        return expr;
    }

    @Override
    public Object visitUnary_operator(sqlParser.Unary_operatorContext ctx) {
        return ctx.getText().toUpperCase();
    }

    @Override
    public Object visitPred_expr(sqlParser.Pred_exprContext ctx) {
        try {
            String op = ctx.getChild(1).getText().toUpperCase();
            // pred_expr and pred_expr  |  pred_expr or pred_expr
            if (op.compareTo("AND") == 0 || op.compareTo("OR") == 0) {
                Conditions cond1 = (Conditions) visit(ctx.getChild(0));
                Conditions cond2 = (Conditions) visit(ctx.getChild(2));
                // pred_expr and pred_expr
                if (op.compareTo("AND") == 0) {
                    return new Conditions(0, cond1, cond2);
                } else {   // pred_expr or pred_expr
                    return new Conditions(1, cond1, cond2);
                }
            }
            Expression expr1 = (Expression) visit(ctx.getChild(0));
            Expression expr2 = (Expression) visit(ctx.getChild(2));
            switch (op) {
                case "==":
                    return new Conditions(2, "EQ", expr1, expr2);
                case "!=":
                case "<>":
                    return new Conditions(2, "NEQ", expr1, expr2);
                case "<":
                    return new Conditions(2, "LT", expr1, expr2);
                case ">":
                    return new Conditions(2, "GT", expr1, expr2);
                case "<=":
                    return new Conditions(2, "NGT", expr1, expr2);
                case ">=":
                    return new Conditions(2, "NLT", expr1, expr2);
                default:
                    throw new ParseCancellationException("Unknown binary operation '" + op + "'");
            }
        } catch (NDException e) {
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public Object visitNumeric_value(sqlParser.Numeric_valueContext ctx) {
        Expression expr;
        try {
            expr = new Expression(0, ctx.getText());
        } catch (NDException e) {
            throw new ParseCancellationException(e);
        }

        return expr;
    }
}
