package org.naivedb.Statement;

import org.naivedb.Type.Type;
import org.naivedb.utils.NDException;
import org.naivedb.utils.NumberUtils;

import java.util.ArrayList;
import java.util.LinkedList;

public class Conditions {
    private int type;
    private String leftSymbol;
    private String rightSymbol;
    private String op;
    private Object value;
    private Conditions leftCond;
    private Conditions rightCond;

    /*
        type |         0         |         1       |         2            |        3
             |  cond1 and cond2  | cond1 or cond2  |  symbol1 op symbol2  | symbol op value
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

    public Conditions(int type, String leftSymbol, String symbolORvalue, String op)
            throws NDException {
        if (type != 2 && type != 3) {
            throw new NDException("Unexpected Conditions type");
        }
        this.type = type;
        this.leftSymbol = leftSymbol;
        this.op = op;
        if (type == 2) {
            if (isValue(symbolORvalue)) {
                throw new NDException("Unexpected Conditions type");
            }
            this.rightSymbol = symbolORvalue;
        } else
        {
            if (!isValue(symbolORvalue)) {
                throw new NDException("Unexpected Conditions type");
            }
            this.value = symbolORvalue;
        }
    }

    public boolean satisfied(LinkedList<String> nameList, LinkedList<Type> typeList, LinkedList valueList)
            throws NDException {
        switch (this.type) {
            case 0:
                return leftCond.satisfied(nameList, typeList, valueList) && rightCond.satisfied(nameList, typeList, valueList);
            case 1:
                return leftCond.satisfied(nameList, typeList, valueList) || rightCond.satisfied(nameList, typeList, valueList);
            case 2:
                int idx1 = nameList.indexOf(leftSymbol);
                int idx2 = nameList.indexOf(rightSymbol);
                Type finalType = Type.lift(typeList.get(idx1), typeList.get(idx2));
                try {
                    if (finalType.getType() == 5) {
                        return check(valueList.get(idx1), valueList.get(idx2), this.op, finalType);
                    }
                    Object obj1 = Type.convert(valueList.get(idx1).toString(), finalType);
                    Object obj2 = Type.convert(valueList.get(idx2).toString(), finalType);
                    return check(obj1, obj2, this.op, finalType);
                } catch (ClassCastException e)
                {
                    throw new NDException(e.getMessage());
                }
            case 3:
                int idx = nameList.indexOf(leftSymbol);
                Object convertValue = Type.convert(this.value.toString(), typeList.get(idx));
                try {
                    return check(valueList.get(idx), convertValue, this.op, typeList.get(idx));
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
