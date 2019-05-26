package org.naivedb.Statement;

import org.naivedb.utils.NDException;
import org.naivedb.utils.NumberUtils;

import java.util.LinkedList;

public class Conditions {
    private int type;
    private String leftSymbol;
    private String rightSymbol;
    private Object value;
    private Conditions leftCond;
    private Conditions rightCond;

    /*
        type        0         |         1       |         2            |        3
             cond1 and cond2  | cond1 or cond2  |  symbol1 op symbol2  | symbol op value
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

    public Conditions(int type, String leftSymbol, String symbolORvalue)
            throws NDException {
        if (type != 2 && type != 3) {
            throw new NDException("Unexpected Conditions type");
        }
        this.type = type;
        if (type == 2) {
            if (isValue(symbolORvalue)) {
                throw new NDException("Unexpected Conditions type");
            }
            this.leftSymbol = leftSymbol;
            this.rightSymbol = symbolORvalue;
        } else
        {
            if (!isValue(symbolORvalue)) {
                throw new NDException("Unexpected Conditions type");
            }
            this.leftSymbol = leftSymbol;
            if (NumberUtils.isInteger(symbolORvalue)) {
                this.value = NumberUtils.parseDouble(symbolORvalue, 0, symbolORvalue.length());
            } else
            {
                this.value = symbolORvalue.substring(1, symbolORvalue.length()-1);
            }
        }
    }

    public boolean satisfied(LinkedList<String> nameList, LinkedList<String> typeList, LinkedList valueList)
            throws NDException {
        switch (this.type) {
            case 0:
                return leftCond.satisfied(nameList, typeList, valueList) && rightCond.satisfied(nameList, typeList, valueList);
            case 1:
                return leftCond.satisfied(nameList, typeList, valueList) || rightCond.satisfied(nameList, typeList, valueList);
            case 2:
                return true;
            case 3:
                return true;
            default:
                throw new NDException("Unexpected Conditions type");
        }
    }

    private boolean isValue(String s) {
        return NumberUtils.isInteger(s) || (s.startsWith("\"") && s.endsWith("\""));
    }

}
