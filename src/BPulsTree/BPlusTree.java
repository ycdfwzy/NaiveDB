package BPulsTree;

import java.util.LinkedList;

public class BPlusTree {

    private BPlusTreeNode root;
    private BPlusTreeConfiguration config;

    public BPlusTree(BPlusTreeConfiguration config) {
        this.config = config;
        root = new BPlusTreeLeaf(this);
    }

    public BPlusTree(String filename) {
        fromFile(filename);
    }

    public void fromFile(String filename) {

    }

    public int getTreeDegree() {return config.getTreeDegree();}

    public String getIndexType() {return config.getIndexType();}

    public void insert() {

    }

    public void search() {

    }

    public void delete() {

    }

    public void printTree() {
        System.out.println(config.getIndexType());
    }
}
