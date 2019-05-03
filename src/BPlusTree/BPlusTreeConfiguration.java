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

    public BPlusTreeConfiguration(String keyType,
                                  LinkedList<String> columnName,
                                  LinkedList<String> columnType)
            throws BPlusException {
        this(Consts.defaultBlockSize, keyType, columnName, columnType);
    }

    public BPlusTreeConfiguration(int pageSize,
                                  String keyType,
                                  LinkedList<String> columnName,
                                  LinkedList<String> columnType)
            throws BPlusException {
        this.pageSize = pageSize;
        this.keyType = keyType;
        this.keySize = Type2Size(this.keyType);
        // header = nodeType|parent_ptr|next_page|prev_page
        this.treeDegree = (pageSize - Consts.nodeTypeSize - Consts.parentSize*3) / (keySize + Consts.pointSize);
        if (this.treeDegree < 3) {
            throw new BPlusException("Too small page size: " + this.pageSize + "bytes");
        }

        this.columnName = columnName;
        this.columnType = columnType;
        this.rowSize = 0;
        for (int i = 0; i < this.columnType.size(); ++i) {
            this.rowSize += Type2Size(this.columnType.get(i));
        }

        if ((Consts.columnNameSize + Consts.columnTypeSize) * columnName.size()
                + Consts.longSize*3 + Consts.intSize*2 > this.pageSize)
            throw new BPlusException("Too small page size: " + this.pageSize + "bytes");
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
    public int getColumnSize() {return columnName.size();}
    public String[] getColumnType() {return columnType.toArray(new String[0]);}
    public String[] getColumnName() {return columnName.toArray(new String[0]);}
}
