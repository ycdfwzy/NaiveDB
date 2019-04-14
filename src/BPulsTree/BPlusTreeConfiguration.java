package BPulsTree;

import java.util.LinkedList;

public class BPlusTreeConfiguration {

    private int pageSize;
    private int nodeTypeSize;
    private int leafTypeSize;
    private int parentPtrSize;
    private LinkedList<String> columnType;

    private int treeDegree;
    private String indexType;

    public BPlusTreeConfiguration() {
        this.treeDegree = 4;
    }

    public BPlusTreeConfiguration(int treeDegree,
                                  String indexType)
            throws BPlusException {
        this.treeDegree = treeDegree;
        this.indexType = indexType;
        if (!this.indexType.equals("Int") && !this.indexType.equals("Long") &&
            !this.indexType.equals("String") && !this.indexType.equals("float") &&
            !this.indexType.equals("double"))
            throw new BPlusException("Unknown index type '" + this.indexType + "'");
    }

    public int getTreeDegree() {return treeDegree;}
    public String getIndexType() {return indexType;}
}
