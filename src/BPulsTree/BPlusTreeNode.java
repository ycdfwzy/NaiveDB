package BPulsTree;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.LinkedList;

public class BPlusTreeNode {
    private BPlusTree tree;
    private BPlusTreeNode parent;
    private Boolean isLeaf;
    private Boolean isInternal;
    private Boolean isRoot;

    private LinkedList indexList;

    public BPlusTreeNode(BPlusTree tree) {
        this.tree = tree;
        String indexType = this.tree.getIndexType();
        indexList = new LinkedList();
//        if (indexType == "Int") {
//            indexList = new LinkedList<Integer>();
//        } else
//        if (indexType == "Long") {
//            indexList = new LinkedList<Long>();
//        } else
//        if (indexType == "Float") {
//            indexList = new LinkedList<Float>();
//        } else
//        if (indexType == "Double") {
//            indexList = new LinkedList<Double>();
//        } else
//        if (indexType == "String") {
//            indexList = new LinkedList<String>();
//        }
    }
}
