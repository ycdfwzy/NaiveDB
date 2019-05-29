package org.naivedb.Statement;

import javafx.util.Pair;
import org.naivedb.Type.Type;
import org.naivedb.utils.NDException;
import org.naivedb.utils.NumberUtils;

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
            this.valueType = new Type(Type.SQL_STRING);
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

    public Pair<Object, Type> calcValue(LinkedList<String> nameList, LinkedList<Type> typeList, LinkedList valueList)
            throws NDException {
        switch (type) {
            case 0:
                Object obj = Type.convert(this.symbolORValue, this.valueType);
                return new Pair<>(obj, this.valueType);
            case 1:
                int idx = nameList.indexOf(this.symbolORValue);
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
                    case "not":
                        return not(tmp);
                    default:
                        throw new NDException("Unexpected unary operation '" + this.op + "' !");
                }
            case 3:
                Pair<Object, Type> tmp1 = expr1.calcValue(nameList, typeList, valueList);
                Pair<Object, Type> tmp2 = expr2.calcValue(nameList, typeList, valueList);
                Type finalType = Type.lift(tmp1.getValue(), tmp2.getValue());
                Object o1 = Type.convert(tmp1.getKey().toString(), finalType);
                Object o2 = Type.convert(tmp2.getKey().toString(), finalType);
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
                return new Pair<>(this.symbolORValue, new Type(Type.SQL_STRING));
            default:
                throw new NDException("Unknown Type!");
        }
    }

    public int getType() {return this.type;}
    public Type getValueType() {return this.valueType;}

    private Pair<Object, Type> neg(Pair<Object, Type> p)
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

    private Pair<Object, Type> non(Pair<Object, Type> p)
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

    private Pair<Object, Type> not(Pair<Object, Type> p)
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

    private Pair<Object, Type> plus(Object o1, Object o2, Type finalType)
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

    private Pair<Object, Type> minus(Object o1, Object o2, Type finalType)
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

    private Pair<Object, Type> multiply(Object o1, Object o2, Type finalType)
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

    private Pair<Object, Type> divide(Object o1, Object o2, Type finalType)
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

    private Pair<Object, Type> mod(Object o1, Object o2, Type finalType)
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
