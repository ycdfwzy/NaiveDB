package org.naivedb.Statement;

import javafx.util.Pair;
import org.naivedb.Table.Table;
import org.naivedb.Type.Type;
import org.naivedb.utils.NDException;
import org.naivedb.utils.NumberUtils;

import java.util.ArrayList;
import java.util.LinkedList;

public class Expression {
//    private String exprStr;
    private int type; // 0  numeric value
                       // 1  symbol
                       // 2  unary_op expr
                       // 3  expr op expr
                       // 4  string value
    private String symbolORValue;
    private Type valueType;
    private Expression expr1, expr2;
    private String op; // +, -, *, /, %, ~, not

    public Expression(int type, String symbolORvalue)
            throws NDException  {
        if (type != 0 && type != 1 && type != 4) {
            throw new NDException("Unexpected Expression type!");
        }
        this.type = type;
        if (type == 0) {
            if (!NumberUtils.isNumber(symbolORvalue)) {
                throw new NDException(symbolORvalue + " is Not a Number!");
            }
            this.symbolORValue = symbolORvalue;
            if (NumberUtils.isInteger(symbolORValue)) {
                if (symbolORvalue.compareTo(String.valueOf(Integer.parseInt(symbolORvalue))) == 0)
                    this.valueType = new Type(Type.SQL_INT);
                else
                    this.valueType = new Type(Type.SQL_LONG);
            } else {
                if (symbolORvalue.compareTo(String.valueOf(Float.parseFloat(symbolORvalue))) == 0)
                    this.valueType = new Type(Type.SQL_FLOAT);
                else
                    this.valueType = new Type(Type.SQL_DOUBLE);
            }
        } else
        if (type == 1) {
            this.symbolORValue = symbolORvalue;
            this.valueType = null;
        } else
        {
            this.symbolORValue = symbolORvalue;
            this.valueType = new Type(Type.SQL_STRING, symbolORvalue.length()+1);
        }
    }

    public Expression(int type, String unary_op, Expression expr)
            throws NDException  {
        if (type != 2) {
            throw new NDException("Unexpected Expression type!");
        }
//        if (expr.getType() == 0) {
//            this.type = 0;
//            switch (unary_op) {
//                case "+":
//                    this.value = expr.value;
//                    break;
//                case "-":
//                    this.value = expr.neg();
//                    break;
//                case "~":
//                    this.value = expr.non();
//                    break;
//                case "not":
//                    this.value = expr.not();
//                    break;
//                default:
//                    throw new NDException("Unexpected unary operation '" + unary_op + "' !");
//            }
//        } else
//        {
            this.type = type;
            this.expr1 = expr;
            this.op = unary_op;
            this.valueType = null;
//        }
    }

    public Expression(int type, String op, Expression expr1, Expression expr2)
            throws NDException {
        if (type != 3) {
            throw new NDException("Unsupported Expression type!");
        }
        this.type = type;
        this.op = op;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    /*
        calculate this expression's value
        params:
            nameList:   symbols' name
            typeList:   symbols' types
            valueList:  symbols' values
        return:
            one Pair<> value p, p's first value is real numeric/string, p's second value is its type
     */
    public Pair<Object, Type> calcValue(LinkedList<String> nameList, LinkedList<Type> typeList, LinkedList valueList)
            throws NDException {
        switch (type) {
            case 0:
                Object obj = Type.convert(this.symbolORValue, this.valueType);
                return new Pair<>(obj, this.valueType);
            case 1:
                int idx = nameList.indexOf(this.symbolORValue);
                if (idx == -1)
                    throw new NDException("Unknown column name '" + this.symbolORValue + "'!");
                return new Pair<>(valueList.get(idx), typeList.get(idx));
            case 2:
                Pair<Object, Type> tmp = expr1.calcValue(nameList, typeList, valueList);
                switch (this.op) {
                    case "+":
                        return tmp;
                    case "-":
                        return neg(tmp);
                    case "~":
                        return non(tmp);
                    case "NOT":
                        return not(tmp);
                    default:
                        throw new NDException("Unexpected unary operation '" + this.op + "' !");
                }
            case 3:
                Pair<Object, Type> tmp1 = expr1.calcValue(nameList, typeList, valueList);
                Pair<Object, Type> tmp2 = expr2.calcValue(nameList, typeList, valueList);
                Type finalType = Type.lift(tmp1.getValue(), tmp2.getValue());
                Object o1 = Type.convert(tmp1.getKey(), finalType);
                Object o2 = Type.convert(tmp2.getKey(), finalType);
                switch (this.op) {
                    case "+":
                        return plus(o1, o2, finalType);
                    case "-":
                        return minus(o1, o2, finalType);
                    case "*":
                        return multiply(o1, o2, finalType);
                    case "/":
                        return divide(o1, o2, finalType);
                    case "%":
                        return mod(o1, o2, finalType);
                    default:
                        throw new NDException("Unexpected binary operation '" + this.op + "' !");
                }
            case 4:
                return new Pair<>(this.symbolORValue, this.valueType);
            default:
                throw new NDException("Unknown Type!");
        }
    }

    /*
        test if it is a symbol that is equal to "symbol"
        params:
            nameList:   symbols' name
        return:
            true if satisfied, otherwise false
     */
    public boolean isSymbol(String symbol) {
        return (type == 1 && symbolORValue.compareTo(symbol) == 0);
    }

    public boolean isSymbol() {
        return type == 1;
    }

    /*
        test if it is a numeric value
        params:
            none
        return:
            true if satisfied, otherwise false
     */
    public boolean isNumericValue() {
        return type == 0;
    }

    /*
        test if it is a string value
        params:
            none
        return:
            true if satisfied, otherwise false
     */
    public boolean isStringValue() {
        return type == 4;
    }

    /*
        test if it is a string/numeric value
        params:
            none
        return:
            true if satisfied, otherwise false
     */
    public boolean isValue() {
        return isNumericValue() || isStringValue();
    }

    /*
        get string/numeric value directly
        params:
            none
        return:
            one Pair<> value p, p's first value is real numeric/string, p's second value is its type
        * Note: make sure isValue() returns true before you call this function
     */
    public Pair<Object, Type> getDirectValue() throws NDException {
        if (type == 0) {
            Object obj = Type.convert(this.symbolORValue, this.valueType);
            return new Pair<>(obj, this.valueType);
        } else
        if (type == 4) {
            return new Pair<>(this.symbolORValue, this.valueType);
        }
        throw new NDException("Can't get value directly!");
    }

    /*
        simplify this Expression
        params: none
        return: a simplified expression
     */
    public Expression simplify() throws NDException {
        if (type == 0 || type == 1 || type == 4)
            return this;
        if (type == 2) {
            Expression expr = expr1.simplify();
            if (expr.isNumericValue()) {
                switch (this.op) {
                    case "+":
                        return expr;
                    case "-":
                        return new Expression(0, neg(expr.getDirectValue()).getKey().toString());
                    case "~":
                        return new Expression(0, non(expr.getDirectValue()).getKey().toString());
                    case "NOT":
                        return new Expression(0, not(expr.getDirectValue()).getKey().toString());
                    default:
                        throw new NDException("Unexpected unary operation '" + this.op + "' !");
                }
            }
            return new Expression(2, this.op, expr);
        }
        Expression expr1 = this.expr1.simplify();
        Expression expr2 = this.expr2.simplify();
        if (expr1.isNumericValue() && expr2.isNumericValue()) {
            Pair<Object, Type> x = expr1.getDirectValue();
            Pair<Object, Type> y = expr1.getDirectValue();
            Type finalType = Type.lift(x.getValue(), y.getValue());
            Object o1 = Type.convert(x.getKey(), finalType);
            Object o2 = Type.convert(y.getKey(), finalType);
            switch (this.op) {
                case "+":
                    return new Expression(0, plus(o1, o2, finalType).getKey().toString());
                case "-":
                    return new Expression(0, minus(o1, o2, finalType).getKey().toString());
                case "*":
                    return new Expression(0, multiply(o1, o2, finalType).getKey().toString());
                case "/":
                    return new Expression(0, divide(o1, o2, finalType).getKey().toString());
                case "%":
                    return new Expression(0, mod(o1, o2, finalType).getKey().toString());
                default:
                    throw new NDException("Unexpected binary operation '" + this.op + "' !");
            }
        }
        if (this.op.compareTo("+") == 0 && expr1.isStringValue() && expr2.isStringValue()) {
            Pair<Object, Type> x = expr1.getDirectValue();
            Pair<Object, Type> y = expr1.getDirectValue();
            return new Expression(4, x.getKey().toString()+y.getKey().toString());
        }
        return new Expression(3, this.op, expr1, expr2);
    }

    public String getSymbol() throws NDException {
        if (type != 1) {
            throw new NDException("Not a symbol expression!");
        }
        return symbolORValue;
    }

    /*
        convert column name to table_name.column_name
        params: table list
        return: none
     */
    public void normalize(ArrayList<Table> tables) throws NDException {
        switch (this.type) {
            case 1:
                if (this.symbolORValue.indexOf(".") == -1) {
                    String tableName = null;
                    for (Table table : tables) {
                        if (table.getColNames().indexOf(this.symbolORValue) != -1) {
                            if (tableName != null)
                                throw new NDException("Column name '" + this.symbolORValue +"' occurs in Table '" +
                                                        tableName + "' and Table '" + table.getTableName() + "'!");
                            tableName = table.getTableName();
                        }
                    }
                    if (tableName == null)
                        throw new NDException("Unknown column name: '" + this.symbolORValue + "'");
                    this.symbolORValue = tableName + "." + this.symbolORValue;
                }
                break;
            case 2:
                this.expr1.normalize(tables);
                break;
            case 3:
                this.expr1.normalize(tables);
                this.expr2.normalize(tables);
                break;
        }

    }

    public int getType() {return this.type;}
    public Type getValueType() {return this.valueType;}

    public static Pair<Object, Type> neg(Pair<Object, Type> p)
            throws NDException {
        switch (p.getValue().getType()) {
            case Type.SQL_INT:
                return new Pair<>(-(int)p.getKey(), p.getValue());
            case Type.SQL_LONG:
                return new Pair<>(-(long)p.getKey(), p.getValue());
            case Type.SQL_FLOAT:
                return new Pair<>(-(float)p.getKey(), p.getValue());
            case Type.SQL_DOUBLE:
                return new Pair<>(-(double)p.getKey(), p.getValue());
            default:
                throw new NDException("Unsupported NEG operation!");
        }
    }

    public static Pair<Object, Type> non(Pair<Object, Type> p)
            throws NDException {
        switch (p.getValue().getType()) {
            case Type.SQL_INT:
                return new Pair<>(~(int)p.getKey(), p.getValue());
            case Type.SQL_LONG:
                return new Pair<>(~(long)p.getKey(), p.getValue());
            default:
                throw new NDException("Unsupported NON operation!");
        }
    }

    public static Pair<Object, Type> not(Pair<Object, Type> p)
            throws NDException {
        switch (p.getValue().getType()) {
            case Type.SQL_INT:
                return new Pair<>((int)p.getKey() == 0 ? 1 : 0, new Type(Type.SQL_INT));
            case Type.SQL_LONG:
                return new Pair<>((long)p.getKey() == 0 ? 1 : 0, new Type(Type.SQL_INT));
            case Type.SQL_FLOAT:
                return new Pair<>((float)p.getKey() == 0 ? 1 : 0, new Type(Type.SQL_INT));
            case Type.SQL_DOUBLE:
                return new Pair<>((double)p.getKey() == 0 ? 1 : 0, new Type(Type.SQL_INT));
            default:
                throw new NDException("Unsupported NOT operation!");
        }
    }

    public static Pair<Object, Type> plus(Object o1, Object o2, Type finalType)
            throws NDException {
        switch (finalType.getType()) {
            case Type.SQL_INT:
                return new Pair<>((int)o1+(int)o2, finalType);
            case Type.SQL_LONG:
                return new Pair<>((long)o1+(long)o2, finalType);
            case Type.SQL_FLOAT:
                return new Pair<>((float)o1+(float)o2, finalType);
            case Type.SQL_DOUBLE:
                return new Pair<>((double)o1+(double)o2, finalType);
            default:
                throw new NDException("Unsupported PLUS operation!");
        }
    }

    public static Pair<Object, Type> minus(Object o1, Object o2, Type finalType)
            throws NDException {
        switch (finalType.getType()) {
            case Type.SQL_INT:
                return new Pair<>((int)o1-(int)o2, finalType);
            case Type.SQL_LONG:
                return new Pair<>((long)o1-(long)o2, finalType);
            case Type.SQL_FLOAT:
                return new Pair<>((float)o1-(float)o2, finalType);
            case Type.SQL_DOUBLE:
                return new Pair<>((double)o1-(double)o2, finalType);
            default:
                throw new NDException("Unsupported MINUS operation!");
        }
    }

    public static Pair<Object, Type> multiply(Object o1, Object o2, Type finalType)
            throws NDException {
        switch (finalType.getType()) {
            case Type.SQL_INT:
                return new Pair<>((int)o1*(int)o2, finalType);
            case Type.SQL_LONG:
                return new Pair<>((long)o1*(long)o2, finalType);
            case Type.SQL_FLOAT:
                return new Pair<>((float)o1*(float)o2, finalType);
            case Type.SQL_DOUBLE:
                return new Pair<>((double)o1*(double)o2, finalType);
            default:
                throw new NDException("Unsupported MULTIPLY operation!");
        }
    }

    public static Pair<Object, Type> divide(Object o1, Object o2, Type finalType)
            throws NDException {
        switch (finalType.getType()) {
            case Type.SQL_INT:
                return new Pair<>((int)o1/(int)o2, finalType);
            case Type.SQL_LONG:
                return new Pair<>((long)o1/(long)o2, finalType);
            case Type.SQL_FLOAT:
                return new Pair<>((float)o1/(float)o2, finalType);
            case Type.SQL_DOUBLE:
                return new Pair<>((double)o1/(double)o2, finalType);
            default:
                throw new NDException("Unsupported DIVIDE operation!");
        }
    }

    public static Pair<Object, Type> mod(Object o1, Object o2, Type finalType)
            throws NDException {
        switch (finalType.getType()) {
            case Type.SQL_INT:
                return new Pair<>((int)o1%(int)o2, finalType);
            case Type.SQL_LONG:
                return new Pair<>((long)o1%(long)o2, finalType);
            case Type.SQL_FLOAT:
                return new Pair<>((float)o1%(float)o2, finalType);
            case Type.SQL_DOUBLE:
                return new Pair<>((double)o1%(double)o2, finalType);
            default:
                throw new NDException("Unsupported MOD operation!");
        }
    }
}
