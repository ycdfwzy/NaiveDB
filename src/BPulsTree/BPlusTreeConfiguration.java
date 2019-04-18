package BPulsTree;

import java.util.LinkedList;

public class BPlusTreeConfiguration {

    private int pageSize;
    private String indexType;
    private int indexSize;
    private int treeDegree;
    private int rowSize;

    private LinkedList<String> columnType;
    private LinkedList<String> columnName;

    private static int pointSize = 4;


    public BPlusTreeConfiguration(String indexType,
                                  LinkedList<String> columnName,
                                  LinkedList<String> columnType)
            throws BPlusException {
        this(4096, indexType, columnName, columnType);
    }

    public BPlusTreeConfiguration(int pageSize,
                                  String indexType,
                                  LinkedList<String> columnName,
                                  LinkedList<String> columnType)
            throws BPlusException {
        this.pageSize = pageSize;
        this.indexType = indexType;
        this.indexSize = Type2Size(this.indexType);
        this.treeDegree = pageSize / (indexSize + pointSize);
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
                ret = 4;
                break;
            case "Long":
                ret = 8;
                break;
            case "String":
                ret = 128;
                break;
            case "Float":
                ret = 4;
                break;
            case "Double":
                ret = 8;
                break;
            default:
                throw new BPlusException("Unknown index type '" + type + "'");
        }
        return ret;
    }

    public int getTreeDegree() {return treeDegree;}
    public String getIndexType() {return indexType;}
    public int getIndexSize() {return indexSize;}
    public int getPageSize() {return pageSize;}
    public int getRowSize() {return rowSize;}
    public String[] getColumnType() {return columnType.toArray(new String[0]);}
    public String[] getColumnName() {return columnName.toArray(new String[0]);}
}
