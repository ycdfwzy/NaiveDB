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
//        System.out.println(ctx.getText());
        int n = ctx.getChildCount();
        ArrayList res = new ArrayList();
        for (int i = 0; i < n; i += 2)
            res.add(visit(ctx.getChild(i)));
        return res;
    }

    @Override
    public Object visitSql_stmt(sqlParser.Sql_stmtContext ctx) {
//        System.out.println(ctx.getText());
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
            if (ctx.getChild(i).getText().toUpperCase().compareTo("PRIMARY") == 0) {
                primaryKey = (String) visit(ctx.getChild(i+3));
                break;
            }

            String colName = (String) visit(ctx.getChild(i));
            i += 1;

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
        String tbName = (String) visit(ctx.getChild(2));
        int n = ctx.getChildCount();
        LinkedList<String> attrList = null;
        LinkedList<LinkedList> valueList;
        int i = 3;
        if (ctx.getChild(i).getText().toUpperCase().compareTo("VALUES") != 0) {
            attrList = new LinkedList<>();
            i += 1;
            while (true) {
                String attrName = (String) visit(ctx.getChild(i));
                attrList.add(attrName);
                if (ctx.getChild(i+1).getText().compareTo(")") == 0)
                    break;
                i += 2;
            }
            i += 2;
        }

        i += 1;
        valueList = new LinkedList<>();
        while (i < n) {
            LinkedList values = new LinkedList();
            i += 1;
            while (true) {
                Expression expr = (Expression) visit(ctx.getChild(i));
                if (!expr.isValue())
                    throw new ParseCancellationException("Unknown '" + ctx.getChild(i) + "'!");

                Pair<Object, Type> v;
                try {
                    v = expr.getDirectValue();
                } catch (NDException e) {
                    throw new ParseCancellationException(e);
                }

                values.add(v.getKey());

                i += 2;
                if (i >= n || ctx.getChild(i).getText().compareTo(",") == 0)
                    break;
            }

            valueList.add(values);
            i += 1;
        }
        if (attrList != null)
            return new StatementInsert(tbName, attrList, valueList);
        else
            return new StatementInsert(tbName, valueList);
    }

    @Override
    public Object visitDelete_stmt(sqlParser.Delete_stmtContext ctx) {
        String tbName = (String) visit(ctx.getChild(2));
        if (ctx.getChildCount() >= 5) {
            Conditions cond = (Conditions) visit(ctx.getChild(4));
            return new StatementDelete(tbName, cond);
        }
        return new StatementDelete(tbName);
    }

    @Override
    public Object visitUpdate_stmt(sqlParser.Update_stmtContext ctx) {
        String tbName = (String) visit(ctx.getChild(1));
        Pair<LinkedList<String>, LinkedList<Expression>> res = (Pair<LinkedList<String>, LinkedList<Expression>>) visit(ctx.getChild(3));
        LinkedList<String> colList = res.getKey();
        LinkedList<Expression> exprList = res.getValue();
        if (ctx.getChildCount() >= 6) {
            Conditions cond = (Conditions) visit(ctx.getChild(5));
            return new StatementUpdate(tbName, colList, exprList, cond);
        }
        return new StatementUpdate(tbName, colList, exprList);
    }

    @Override
    public Object visitSelect_stmt(sqlParser.Select_stmtContext ctx) {
        LinkedList<String> selectElements = (LinkedList<String>) visit(ctx.getChild(1));
        RangeVariable rv = (RangeVariable) visit(ctx.getChild(3));

        if (ctx.getChildCount() > 4) {
            Conditions cond = (Conditions) visit(ctx.getChild(5));
            return new StatementSelect(selectElements, rv, cond);
        }
        return new StatementSelect(selectElements, rv, null);
    }

    @Override
    public Object visitUse_stmt(sqlParser.Use_stmtContext ctx) {
        String dbName = (String) visit(ctx.getChild(1));
        return new StatementUse(dbName);
    }

    @Override
    public Object visitShow_stmt(sqlParser.Show_stmtContext ctx) {
        if (ctx.getChildCount() == 2) {
            return new StatementShow();
        }
        String dbName = (String) visit(ctx.getChild(2));
        return new StatementShow(dbName);
    }

    @Override
    public Object visitSelect_elements(sqlParser.Select_elementsContext ctx) {
        try {
            if (ctx.getText().compareTo("*") == 0) {
                LinkedList<String> ret = new LinkedList<>();
                ret.add("*");
                return ret;
            }
            int n = ctx.getChildCount();
            ArrayList<String> colList = new ArrayList<>();
            colList.ensureCapacity((n + 1) / 2);
            for (int i = 0; i < n; i += 2) {
                Expression expr = (Expression) visit(ctx.getChild(i));
                if (expr.isSymbol()) {
                    colList.add(expr.getSymbol());
                }
            }
            return new LinkedList<>(colList);
        } catch (NDException e) {
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public Object visitAsign_clause(sqlParser.Asign_clauseContext ctx) {
        try {
            int n = ctx.getChildCount();
            LinkedList<String> colList = new LinkedList<>();
            LinkedList<Expression> exprList = new LinkedList<>();
            for (int i = 0; i < n; i += 4) {
                String colName = ((Expression) visit(ctx.getChild(i))).getSymbol();
                Expression expr = (Expression) visit(ctx.getChild(i + 2));
                colList.add(colName);
                exprList.add(expr);
            }
            return new Pair<>(colList, exprList);
        } catch (NDException e) {
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public Object visitDb_name(sqlParser.Db_nameContext ctx) {
        return ctx.getText().toUpperCase();
    }

    @Override
    public Object visitTable_name(sqlParser.Table_nameContext ctx) {
        return ctx.getText().toUpperCase();
    }

    @Override
    public Object visitAttr_name(sqlParser.Attr_nameContext ctx) {
        return ctx.getText().toUpperCase();
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
    public Object visitJoin_range(sqlParser.Join_rangeContext ctx) {
        Object o = visit(ctx.getChild(0));
        if (o instanceof String) {
            return new RangeVariable( (String) o, null);
        } else
        {   // join_ranges
            ArrayList<RangeVariable> rvs = (ArrayList<RangeVariable>) o;
            RangeVariable rv = new RangeVariable(null, null);
            rv.setRangeVariables(rvs);
            return rv;
        }
    }

    @Override
    public Object visitJoin_ranges(sqlParser.Join_rangesContext ctx) {
        ArrayList<RangeVariable> rvs = new ArrayList<>();
        rvs.add((RangeVariable) visit(ctx.getChild(0)));
        int n = ctx.getChildCount();
        for (int i = 1; i < n; ++i) {
            rvs.add((RangeVariable) visit(ctx.getChild(i)));
        }
        return rvs;
    }

    @Override
    public Object visitNatural_join(sqlParser.Natural_joinContext ctx) {
        RangeVariable rv = (RangeVariable) visit(ctx.getChild(1));
        rv.setNatural(true);
        return rv;
    }

    @Override
    public Object visitJoin_on(sqlParser.Join_onContext ctx) {
        RangeVariable rv = (RangeVariable) visit(ctx.getChild(0));
        Conditions cond = (Conditions) visit(ctx.getChild(2));
        rv.setConditions(cond);
        return rv;
    }

    @Override
    public Object visitOuter_join(sqlParser.Outer_joinContext ctx) {
        RangeVariable rv = (RangeVariable) visit(ctx.getChild(3));
        rv.setOuterJoined(true);
        switch (ctx.getChild(0).getText().toUpperCase()) {
            case "LEFT":
                rv.setLeft(true);
                break;
            case "RIGHT":
                rv.setRight(true);
                break;
            case "FULL":
                rv.setFull(true);
            default:
                throw new ParseCancellationException("Unsupported token '" + ctx.getChild(0).getText().toUpperCase() + "'");
        }
        return rv;
    }

    @Override
    public Object visitInner_join(sqlParser.Inner_joinContext ctx) {
        RangeVariable rv;
        // join single_range
        if (ctx.getChildCount() == 2) {
            rv = (RangeVariable) visit(ctx.getChild(1));
        } else
        {   // inner join single_range
            rv = (RangeVariable) visit(ctx.getChild(2));
        }
        rv.setInnerJoined(true);
        return rv;
    }

    @Override
    public Object visitSingle_range(sqlParser.Single_rangeContext ctx) {
        // (join_range)
        if (ctx.getChildCount() > 1) {
            return visit(ctx.getChild(1));
        } else
        { // table_name
            String tbName = (String) visit(ctx.getChild(0));
            return new RangeVariable(tbName, null);
        }
    }

    @Override
    public Object visitExpr(sqlParser.ExprContext ctx) {
        try {
            Expression expr;
            if (ctx.getChild(0).getText().startsWith("\"")) {
                expr = new Expression(4, ctx.getText().substring(1, ctx.getText().length()-1));
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
