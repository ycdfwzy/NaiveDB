package org.naivedb.Type;

import org.naivedb.utils.Consts;
import org.naivedb.utils.NDException;

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
        if (t != 5 || len < 0 || len >= 256) throw new NDException("wrong input type");
        this.type = t;
        this.strLen = len;
    }

    public String typeName() throws NDException {
        switch (this.type) {
            case 1:
                return "Int";
            case 2:
                return "Long";
            case 3:
                return "Float";
            case 4:
                return "Double";
            case 5:
                return "String" + Integer.toString(this.strLen);
            default:
                throw new NDException("Wrong type met");
        }
    }

    public int typeSize() throws NDException {
        switch (this.type) {
            case 1:
                return Consts.intSize;
            case 2:
                return Consts.longSize;
            case 3:
                return Consts.floatSize;
            case 4:
                return Consts.doubleSize;
            case 5:
                return this.strLen + 1;
            default:
                throw new NDException("Wrong type met");
        }
    }

    public int getType() {
        return this.type;
    }
}