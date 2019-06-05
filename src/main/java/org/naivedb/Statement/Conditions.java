package org.naivedb.Statement;

import org.naivedb.Table.Table;
import org.naivedb.Type.Type;
import org.naivedb.utils.NDException;
import org.naivedb.utils.NumberUtils;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;

public class Conditions {
    private int type;
    private String op;
    private Conditions leftCond;
    private Conditions rightCond;
    private Expression expr1, expr2;

    /*
        type |         0         |         1       |         2
             |  cond1 and cond2  | cond1 or cond2  |  expr1 op expr2
     */
    public Conditions(int type, Conditions leftCond, Conditions rightCond)
            throws NDException {
        if (type != 0 && type != 1) {
            throw new NDException("Unexpected Conditions type");
        }
        this.type = type;
        this.leftCond = leftCond;
        this.rightCond = rightCond;
    }

    public Conditions(int type, String op, Expression expr1, Expression expr2)
            throws NDException {
        if (type != 2) {
            throw new NDException("Unexpected Conditions type");
        }
        this.type = type;
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.op = op;
    }

    /*
        test if this condition is satisfied
        params:
            nameList:   symbols' name
            typeList:   symbols' types
            valueList:  symbols' values
        return:
            true if satisfied, otherwise false
     */
    public boolean satisfied(LinkedList<String> nameList, LinkedList<Type> typeList, LinkedList valueList)
            throws NDException {
        switch (this.type) {
            case 0:
                return leftCond.satisfied(nameList, typeList, valueList) && rightCond.satisfied(nameList, typeList, valueList);
            case 1:
                return leftCond.satisfied(nameList, typeList, valueList) || rightCond.satisfied(nameList, typeList, valueList);
            case 2:
                Pair<Object, Type> tmp1 = expr1.calcValue(nameList, typeList, valueList);
                Pair<Object, Type> tmp2 = expr2.calcValue(nameList, typeList, valueList);
                Type finalType = Type.lift(tmp1.getValue(), tmp2.getValue());
                try {
                    if (finalType.getType() == 5) {
                        return check(tmp1.getKey(), tmp2.getKey(), this.op, finalType);
                    }
                    Object obj1 = Type.convert(tmp1.getKey(), finalType);
                    Object obj2 = Type.convert(tmp2.getKey(), finalType);
                    return check(obj1, obj2, this.op, finalType);
                } catch (ClassCastException e)
                {
                    throw new NDException(e.getMessage());
                }
            default:
                throw new NDException("Unexpected Conditions type");
        }
    }

    /*
        convert column name to table_name.column_name
        params: table list
        return: none
     */
    public void normalize(ArrayList<Table> tables) throws NDException {
        switch (this.type) {
            case 0:
            case 1:
                leftCond.normalize(tables);
                rightCond.normalize(tables);
                break;
            case 2:
                expr1.normalize(tables);
                expr2.normalize(tables);
                break;
        }
    }

    /*
         get the value in "symbol = value"
         params:
            none
        return:
            one Pair<> value p, p's first value is real numeric/string, p's second value is its type
         * Note: You must call isSymbolEqualSomething(symbol) to check the form
     */
    public Pair<Object, Type> getEqualValue() throws NDException {
        if (expr1.isValue())
            return expr1.getDirectValue();
        return expr2.getDirectValue();
    }

    /*
         get the x in "(-∞, x)" or "(-∞, x]" or "(x, +∞)" or "[x, +∞)"
         params:
            none
        return:
            one Pair<> value p, p's first value is real numeric and its type, p's second value is True if open, else closed
         * Note: You must call isUpperBounded(symbol) or isLowerBound(symbol) to check the form
     */
    public Pair<Pair<Object, Type>, Boolean> getBoundValue() throws NDException {
        Pair<Object, Type> obj;
        if (expr1.isValue()) {
            obj = expr1.getDirectValue();
        } else
        {
            obj = expr2.getDirectValue();
        }
        if (op.compareTo("LT") == 0 || op.compareTo("GT") == 0)
            return new Pair<>(obj, true);
        return new Pair<>(obj, false);
    }

    /*
         get the x, y in "(/[x, y)/]"
         params:
            none
        return:
            one Pair<> value p, p's first value is left real numeric and its type and openness,
                                p's second value is right real numeric and its type and openness
         * Note: You must call isRanged(symbol) to check the form
     */
    public Pair<Pair<Pair<Object, Type>, Boolean>, Pair<Pair<Object, Type>, Boolean>> getRange() throws NDException {
        Pair<Pair<Object, Type>, Boolean> bound1 = leftCond.getBoundValue();
        Pair<Pair<Object, Type>, Boolean> bound2 = rightCond.getBoundValue();
        String symbol;
        if (leftCond.expr1.getType() == 1) {
            symbol = leftCond.expr1.getSymbol();
        } else
        {
            symbol = leftCond.expr2.getSymbol();
        }
        if (leftCond.isLowerBounded(symbol)) {
            return new Pair<>(bound1, bound2);
        }
        return new Pair<>(bound2, bound1);
    }

    /*
        if this condition is in the form "symbol = value"
        params:
            symbol:   symbol's name
        return:
            true if is "symbol = value", otherwise false
     */
    public boolean isSymbolEqualSomething(String symbol) {
        if (type != 2) return false;
        if (op.compareTo("EQ") != 0) return false;
        return ((expr1.isSymbol(symbol) && expr2.isValue()) ||
                (expr2.isSymbol(symbol) && expr1.isValue()));
    }

    /*
        if this condition is in the form "symbol ∈ (-∞, x)” or "symbol ∈ (-∞, x]"
        params:
            symbol:   symbol's name
        return:
            true if is "symbol ∈ (-∞, x)” or "symbol ∈ (-∞, x]", otherwise false
     */
    public boolean isUpperBounded(String symbol) {
        if (type != 2) return false;
        if (op.compareTo("LT") == 0 || op.compareTo("NGT") == 0) {
            return expr1.isSymbol(symbol) && expr2.isNumericValue();
        }
        if (op.compareTo("GT") == 0 || op.compareTo("NLT") == 0) {
            return expr2.isSymbol(symbol) && expr1.isNumericValue();
        }
        return false;
    }

    /*
        if this condition is in the form "symbol ∈ (x, +∞)” or "symbol ∈ [x, +∞)"
        params:
            symbol:   symbol's name
        return:
            true if is "symbol ∈ (x, +∞)” or "symbol ∈ [x, +∞)", otherwise false
     */
    public boolean isLowerBounded(String symbol) {
        if (type != 2) return false;
        if (op.compareTo("GT") == 0 || op.compareTo("NLT") == 0) {
            return expr1.isSymbol(symbol) && expr2.isNumericValue();
        }
        if (op.compareTo("LT") == 0 || op.compareTo("NGT") == 0) {
            return expr2.isSymbol(symbol) && expr1.isNumericValue();
        }
        return false;
    }

    /*
        if this condition is in the form "symbol ∈ (/[x, y)/]"
        params:
            symbol:   symbol's name
        return:
            true if is "symbol ∈ (/[x, y)/]", otherwise false
     */
    public boolean isRanged(String symbol) {
        if (type != 0) return false;
        return (leftCond.isLowerBounded(symbol) && rightCond.isUpperBounded(symbol)) ||
                (rightCond.isLowerBounded(symbol) && leftCond.isUpperBounded(symbol));
    }

    private boolean isValue(String s) {
        return NumberUtils.isNumber(s) || (s.startsWith("\"") && s.endsWith("\""));
    }

    private boolean check(Object obj1, Object obj2, String expectedRelation, Type type)
            throws NDException {
        int comp = Type.compare(obj1, obj2, type);
        switch (expectedRelation) {
            case "EQ":
                return comp == 0;
            case "NEQ":
                return comp == -1 || comp == 1;
            case "LT":
                return comp == -1;
            case "GT":
                return comp == 1;
            case "NLT":
                return comp == 0 || comp == 1;
            case "NGT":
                return comp == 0 || comp == -1;
            default:
                throw new NDException("Unknown relation!");
        }
    }

}
