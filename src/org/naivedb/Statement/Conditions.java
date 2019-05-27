package org.naivedb.Statement;

import org.naivedb.Type.Type;
import org.naivedb.utils.NDException;
import org.naivedb.utils.NumberUtils;

import javafx.util.Pair;
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
                    Object obj1 = Type.convert(tmp1.getKey().toString(), finalType);
                    Object obj2 = Type.convert(tmp2.getKey().toString(), finalType);
                    return check(obj1, obj2, this.op, finalType);
                } catch (ClassCastException e)
                {
                    throw new NDException(e.getMessage());
                }
            default:
                throw new NDException("Unexpected Conditions type");
        }
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
                return comp != 0;
            case "LT":
                return comp <= -1;
            case "GT":
                return comp >= 1;
            case "NLT":
                return comp >= 0;
            case "NGT":
                return comp <= 0;
            default:
                throw new NDException("Unknown relation!");
        }
    }

}
