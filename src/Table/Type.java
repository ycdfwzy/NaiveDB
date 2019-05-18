package Table;

public class Type {
    public static final int SQL_INT = 1;
    public static final int SQL_LONG = 2;
    public static final int SQL_FLOAT = 3;
    public static final int SQL_DOUBLE = 4;
    public static final int SQL_STRING = 5;

    private int type;
    private int strLen;

    public Type(int t) throws Exception {
        if (t < 1 || t > 4) throw new Exception("wrong input type");
        this.type = t;
    }

    public Type(int t, int len) throws Exception {
        if (t != 5 || len < 0 || len >= 256) throw new Exception("wrong input type");
        this.type = t;
        this.strLen = len;
    }

    public String typeName() throws Exception {
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
                return "String";
            default:
                throw new Exception("Wrong type met");
        }
    }

    public int getType() {
        return this.type;
    }
}