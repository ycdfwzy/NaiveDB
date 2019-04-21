package BPlusTree;

public class BPlusTreeLeaf extends BPlusTreeNode {

    private long nextPage, prevPage;
    BPlusTreeLeaf(long pageIndex,
                  BPlusTreeNodeType nodeType,
                  BPlusTreeConfiguration conf,
                  long nextPage,
                  long prevPage)
    throws BPlusException {
        super(pageIndex, nodeType, conf);
        if (!isLeaf()) {
            throw new BPlusException("wrong node type for instance of BPlusTreeLeaf");
        }
        if (isRoot() && nextPage > 0) {
            throw new BPlusException("can't have root-leaf node with non-null next poiter");
        }
        this.nextPage = nextPage;
        this.prevPage = prevPage;
    }

    public void setNextPage(long nextPage) {
        this.nextPage = nextPage;
    }

    public long getNextPage() {
        return nextPage;
    }

    public void setPrevPage(long prevPage) {
        this.prevPage = prevPage;
    }

    public long getPrevPage() {
        return prevPage;
    }
}
