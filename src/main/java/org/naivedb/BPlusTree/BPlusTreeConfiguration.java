package org.naivedb.BPlusTree;

import java.util.LinkedList;
import org.naivedb.utils.Consts;
import org.naivedb.utils.NDException;

public class BPlusTreeConfiguration {

    private String filename;
    private int pageSize;
    private String keyType;
    private int keySize;
    private int treeDegree;
    private int rowSize;

    private LinkedList<String> columnType;

    public BPlusTreeConfiguration(String filename,
                                  String keyType,
                                  LinkedList<String> columnType)
            throws NDException {
        this(Consts.defaultBlockSize, filename, keyType, columnType);
    }

    public BPlusTreeConfiguration(int pageSize,
                                  String filename,
                                  String keyType,
                                  LinkedList<String> columnType)
            throws NDException {
        this.pageSize = pageSize;
        this.filename = filename;
        this.keyType = keyType;
        this.keySize = Consts.Type2Size(this.keyType);
        // header = nodeType|parent_ptr|next_page|prev_page
        this.treeDegree = (pageSize - Consts.nodeTypeSize - Consts.parentSize*3) / (keySize + Consts.pointSize);
        if (this.treeDegree < 3) {
            throw new NDException("Too small page size: " + this.pageSize + "bytes");
        }

//        if (columnType.isEmpty() || !keyType.equals(columnType.get(0))) {
//            throw new NDException("key-type is not equal to first column-type!");
//        }

        this.columnType = columnType;
        this.rowSize = 0;
        for (int i = 0; i < this.columnType.size(); ++i) {
            this.rowSize += Consts.Type2Size(this.columnType.get(i));
        }

//        if ((Consts.columnNameSize + Consts.columnTypeSize) * columnType.size()
//                + Consts.longSize*3 + Consts.intSize*2 > this.pageSize)
//            throw new BPlusException("Too small page size: " + this.pageSize + "bytes");
    }

    public int getTreeDegree() {return treeDegree;}
    public String getKeyType() {return keyType;}
    public int getKeySize() {return keySize;}
    public int getPageSize() {return pageSize;}
    public int getRowSize() {return rowSize;}
    public int getColumnSize() {return columnType.size();}
    public String getColumnType(int i) {return columnType.get(i);}
//    public String[] getColumnName() {return columnName.toArray(new String[0]);}
    public String getFilename() {return filename;}
}
