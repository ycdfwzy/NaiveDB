package org.naivedb.Statement.grammar;

import javafx.util.Pair;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.naivedb.Statement.StatementCreateDatabase;
import org.naivedb.Statement.StatementCreateTable;
import org.naivedb.Statement.StatementDropDatabase;
import org.naivedb.Statement.StatementDropTable;
import org.naivedb.Type.Type;
import org.naivedb.utils.NDException;

import java.util.ArrayList;

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
        String dbName = ctx.getChild(2).getText().toUpperCase();
        return new StatementCreateDatabase(dbName);
    }

    @Override
    public Object visitCreate_table_stmt(sqlParser.Create_table_stmtContext ctx) {
        String tbName = ctx.getChild(2).getText().toUpperCase();
        int n = ctx.getChildCount();
        String primaryKey = null;
        ArrayList<Pair<String, Type>> cols = new ArrayList<>();
        ArrayList<Boolean> notNull = new ArrayList<>();
        for (int i = 4; i < n; i += 1) {
            String colName = ctx.getChild(i).getText().toUpperCase();
            i += 1;
            if (colName.compareTo("PRIMARY") == 0) {
                primaryKey = ctx.getChild(i+2).getText();
                break;
            }

            String t = ctx.getChild(i).getText().toLowerCase();
            i += 1;
            char[] tc = t.toCharArray();
            tc[0] -= 32;
            t = new String(tc); // first letter is upper case, others are lower case
            if (t.startsWith("String")) {
                t = "String" + t.substring(7, t.length()-1);
            }

            Type colType;
            try {
                colType = new Type(t);
            } catch (NDException e)
            {
                throw new ParseCancellationException(e);
            }

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
        return null;
    }

    @Override
    public Object visitDb_name(sqlParser.Db_nameContext ctx) {
        return null;
    }

    @Override
    public Object visitTable_name(sqlParser.Table_nameContext ctx) {
        return null;
    }

    @Override
    public Object visitAttr_name(sqlParser.Attr_nameContext ctx) {
        return null;
    }

    @Override
    public Object visitType_name(sqlParser.Type_nameContext ctx) {
        return null;
    }

    @Override
    public Object visitExpr(sqlParser.ExprContext ctx) {
        return null;
    }

    @Override
    public Object visitNumeric_expr(sqlParser.Numeric_exprContext ctx) {
        return null;
    }

    @Override
    public Object visitExpr_column(sqlParser.Expr_columnContext ctx) {
        return null;
    }

    @Override
    public Object visitUnary_operator(sqlParser.Unary_operatorContext ctx) {
        return null;
    }

    @Override
    public Object visitPred_expr(sqlParser.Pred_exprContext ctx) {
        return null;
    }

    @Override
    public Object visitNumeric_value(sqlParser.Numeric_valueContext ctx) {
        return null;
    }
}
