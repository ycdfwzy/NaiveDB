package org.naivedb.Type;

import org.naivedb.utils.Consts;
import org.naivedb.utils.NDException;
import org.naivedb.utils.NumberUtils;

public class Type {
    public static final int SQL_INT = 1;
    public static final int SQL_LONG = 2;
    public static final int SQL_FLOAT = 3;
    public static final int SQL_DOUBLE = 4;
    public static final int SQL_STRING = 5;

    private int type;
    private int strLen;

    public Type(int t) throws NDException {
        if (t < 1 || t > 5) throw new NDException("wrong input type");
        this.type = t;
        if (t == 5) this.strLen = Consts.stringSize;
    }

    public Type(int t, int len) throws NDException {
        if (t != 5 || len < 0) throw new NDException("wrong input type");
        this.type = t;
        this.strLen = len;
    }

    public Type(String type_name) throws NDException {
        switch (type_name) {
            case "Int":
                this.type = SQL_INT;
                break;
            case "Long":
                this.type = SQL_LONG;
                break;
            case "Float":
                this.type = SQL_FLOAT;
                break;
            case "Double":
                this.type = SQL_DOUBLE;
                break;
            default:
                if (type_name.startsWith("String") && 
                    NumberUtils.isPureInteger(type_name.substring(6))){
                    this.type = SQL_STRING;
                    this.strLen = NumberUtils.parseInt(type_name.substring(6), 0, type_name.substring(6).length());
                }
                else throw new NDException("input type name error");
                break;
        }
    }

    public String typeName() throws NDException {
        switch (this.type) {
            case SQL_INT:
                return "Int";
            case SQL_LONG:
                return "Long";
            case SQL_FLOAT:
                return "Float";
            case SQL_DOUBLE:
                return "Double";
            case SQL_STRING:
                return "String" + Integer.toString(this.strLen);
            default:
                throw new NDException("Wrong type met");
        }
    }

    public int typeSize() throws NDException {
        switch (this.type) {
            case SQL_INT:
                return Consts.intSize;
            case SQL_LONG:
                return Consts.longSize;
            case SQL_FLOAT:
                return Consts.floatSize;
            case SQL_DOUBLE:
                return Consts.doubleSize;
            case SQL_STRING:
                return this.strLen + 1;
            default:
                throw new NDException("Wrong type met");
        }
    }

    public boolean isNumber() {
        return type != SQL_STRING;
    }

    public static int compare(Object obj1, Object obj2, Type type) {
        // null value
        if (obj1.equals(obj2))
            return 0;
        switch (type.getType()) {
            case SQL_INT:
                return (int) obj1 < (int) obj2 ? -1 : 1;
            case SQL_LONG:
                return (long) obj1 < (long) obj2 ? -1 : 1;
            case SQL_FLOAT:
                return (float) obj1 < (float) obj2 ? -1 : 1;
            case SQL_DOUBLE:
                return (double) obj1 < (double) obj2 ? -1 : 1;
            default:
                return obj1.toString().compareTo(obj2.toString());
        }
    }

    public static Object convert(String str, Type type)
            throws NDException {
        if (str.startsWith("\"") && str.endsWith("\"") && type.getType() == Type.SQL_STRING) {
            return str.substring(1, str.length()-1);
        }
        switch (type.getType()) {
            case Type.SQL_INT:
                return NumberUtils.parseInt(str, 0, str.length());
            case Type.SQL_LONG:
                return NumberUtils.parseLong(str, 0, str.length());
            case Type.SQL_FLOAT:
                return NumberUtils.parseFloat(str, 0, str.length());
            case Type.SQL_DOUBLE:
                return NumberUtils.parseDouble(str, 0, str.length());
            default:
                throw new NDException("Can't convert " + str + " to " + type.typeName());
        }
    }

    public static Type lift(Type t1, Type t2)
            throws NDException {
        if ((t1.isNumber() && !t2.isNumber()) || (!t1.isNumber() && t2.isNumber()))
            throw new NDException("Can't lift!");
        if (!t1.isNumber() && !t2.isNumber())
            return t1.strLen > t2.strLen ? t1 : t2;
        if (t1.getType() == SQL_DOUBLE || t2.getType() == SQL_DOUBLE)
            return new Type(SQL_DOUBLE);
        if (t1.getType() == SQL_FLOAT || t2.getType() == SQL_FLOAT)
            return new Type(SQL_FLOAT);
        if (t1.getType() == SQL_LONG || t2.getType() == SQL_LONG)
            return new Type(SQL_LONG);
        return new Type(SQL_INT);
    }

    public int getType() {
        return this.type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Type) {
            Type t = (Type) obj;
            if (this.type != t.getType())
                return false;
            return this.type != Type.SQL_STRING || this.strLen == t.strLen;
        }
        return false;
    }
}