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
        if (t != 5 || len < 0 || len >= Consts.stringSize) throw new NDException("wrong input type");
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

    public boolean check(Object v) throws NDException{
        switch (this.type) {
            case SQL_INT:
                return v instanceof Integer;
            case SQL_LONG:
                return v instanceof Long;
            case SQL_FLOAT:
                return v instanceof Float;
            case SQL_DOUBLE:
                return v instanceof Double;
            case SQL_STRING:
                if (v instanceof String){
                    String s = (String)v;
                    return s.length() <= this.strLen;
                }
                else return false;
            default:
                throw new NDException("Wrong type met");
        }
    }

    public int getType() {
        return this.type;
    }
}