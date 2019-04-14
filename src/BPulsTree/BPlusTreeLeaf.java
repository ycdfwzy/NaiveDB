package BPulsTree;

import java.util.LinkedList;

public class BPlusTreeLeaf extends BPlusTreeNode {

    private LinkedList<LinkedList> valueLists;
    private long nextPage, prevPage;

    public BPlusTreeLeaf(BPlusTree tree) {
        super(tree);
    }
}
