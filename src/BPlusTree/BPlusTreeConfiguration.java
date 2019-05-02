package BPlusTree;

import java.util.LinkedList;
import utils.Consts;

public class BPlusTreeConfiguration {

    private int pageSize;
    private String keyType;
    private int keySize;
    private int treeDegree;
    private int rowSize;

    private LinkedList<String> columnType;
    private LinkedList<String> columnName;

    private static int pointSize = 4;
    private static int nodeTypeSize = 1;
    private static int parentSize = pointSize;

    public BPlusTreeConfiguration(String keyType,
                                  LinkedList<String> columnName,
                                  LinkedList<String> columnType)
            throws BPlusException {
        this(4096, keyType, columnName, columnType);
    }

    public BPlusTreeConfiguration(int pageSize,
                                  String keyType,
                                  LinkedList<String> columnName,
                                  LinkedList<String> columnType)
            throws BPlusException {
        this.pageSize = pageSize;
        this.keyType = keyType;
        this.keySize = Type2Size(this.keyType);
        this.treeDegree = pageSize / (keySize + pointSize + nodeTypeSize + parentSize);
        if (this.treeDegree < 3) {
            throw new BPlusException("Too small page size: " + this.pageSize + "bytes");
        }

        this.columnName = columnName;
        this.columnType = columnType;
        this.rowSize = 0;
        for (int i = 0; i < this.columnType.size(); ++i) {
            this.rowSize += Type2Size(this.columnType.get(i));
        }
    }

    public static int Type2Size(String type)
            throws BPlusException {
        int ret;
        switch (type) {
            case "Int":
                ret = Consts.intSize;
                break;
            case "Long":
                ret = Consts.longSize;
                break;
            case "String":
                ret = Consts.stringSize;
                break;
            case "Float":
                ret = Consts.floatSize;
                break;
            case "Double":
                ret = Consts.doubleSize;
                break;
            default:
                throw new BPlusException("Unknown key type '" + type + "'");
        }
        return ret;
    }

    public int getTreeDegree() {return treeDegree;}
    public String getKeyType() {return keyType;}
    public int getKeySize() {return keySize;}
    public int getPageSize() {return pageSize;}
    public int getRowSize() {return rowSize;}
    public String[] getColumnType() {return columnType.toArray(new String[0]);}
    public String[] getColumnName() {return columnName.toArray(new String[0]);}
}
