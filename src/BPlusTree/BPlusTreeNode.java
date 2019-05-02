package BPlusTree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class BPlusTreeNode {
    private ArrayList keyList;
    private ArrayList<Long> ptrList;
    private long pageIndex;
    private long parent;
    private BPlusTreeNodeType nodeType;
    private BPlusTreeConfiguration conf;

    public BPlusTreeNode(long pageIndex,
                         long parent,
                         BPlusTreeNodeType nodeType,
                         BPlusTreeConfiguration conf)
            throws BPlusException {
        this.pageIndex = pageIndex;
        this.parent = parent;
        this.nodeType = nodeType;
        this.conf = conf;
        this.keyList = new ArrayList();
        this.ptrList = new ArrayList<Long>();
    }

    /*
        node size
     */
    public BPlusTreeNode(RandomAccessFile treeFile,
                        long pageIndex)
            throws IOException, BPlusException {
        this.pageIndex = pageIndex;
        // Todo: read node info from file
    }

    public boolean isOverflow() {
        return keyList.size() >= this.conf.getTreeDegree();
    }

    public boolean isToMerge() {
        if (isRoot()) {
            return !isLeaf() && isEmpty();
        }
        else {
            return keyList.size() < this.conf.getTreeDegree() / 2;
        }
    }

    public boolean isEmpty() {
        return keyList.isEmpty();
    }

    public boolean isRoot() {
        return nodeType == BPlusTreeNodeType.ROOT_NODE
                || nodeType == BPlusTreeNodeType.ROOT_INTERNAL_NODE
                || nodeType == BPlusTreeNodeType.ROOT_LEAF_NODE;
    }

    public boolean isInternal() {
        return nodeType == BPlusTreeNodeType.INTERNAL_NODE
                || nodeType == BPlusTreeNodeType.ROOT_INTERNAL_NODE;
    }

    public boolean isLeaf() {
        return nodeType == BPlusTreeNodeType.LEAF_NODE
                || nodeType == BPlusTreeNodeType.ROOT_LEAF_NODE;
    }


    public int addKey(Object key) {
        if (keyList.isEmpty()) {
            keyList.add(key);
            return 0;
        }
        else {
            int idx = binarySearch(key, conf.getKeyType()) + 1;
            keyList.add(idx, key);
            return idx;
        }
    }

    public long getKeyIndex(Object key) {
        int idx = binarySearch(key, conf.getKeyType());
        if (idx < 0 || keyList.size() > idx)
            return -1;
        return ptrList.get(idx);
    }

    private int binarySearch(Object key, String keyType) {
        int left = 0, right = keyList.size() - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (keyList.get(mid) == key) {
                return mid;
            }
            int compare = compareKey(keyList.get(mid), key, keyType);
            if (compare == 1) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return right;
    }

    public long getParent() {
        return this.parent;
    }

    public void setParent(long parent) {
        this.parent = parent;
    }

    public int getCurrentSize() {
        return keyList.size();
    }

    public void removeKey(Object key) {
        keyList.remove(key);
    }

    public Object getKey(int idx) {
        return keyList.get(idx);
    }

    public void setKey(int idx, Object key) {
        keyList.set(idx, key);
    }

    public void addPtr(int idx, long ptr) {
        ptrList.add(idx, ptr);
    }

    public long getPtr(int idx) {
        return ptrList.get(idx);
    }

    public void setPtr(int idx, long ptr) {
        ptrList.set(idx, ptr);
    }

    public void removePtr(int idx) {
        ptrList.remove(idx);
    }

    public long getPtrByKey(Object key) {
        int idx = binarySearch(key, conf.getKeyType());
        return ptrList.get(idx);
    }

    public void addKeyAndPtr(Object key, long ptr) {
        int idx = addKey(key);
        addPtr(idx, ptr);
    }

    public void removeKeyAndPtr(int idx) {
        keyList.remove(idx);
        ptrList.remove(idx);
    }

    public void removeKeyAndPtr(Object key) {
        int idx = binarySearch(key, conf.getKeyType());
        keyList.remove(idx);
        ptrList.remove(idx);
    }


    public long getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setNodeType(BPlusTreeNodeType nodeType)
    throws BPlusException{
        if (isLeaf() && nodeType == BPlusTreeNodeType.INTERNAL_NODE) {
            throw new BPlusException("Cannot convert Leaf to Internal Node");
        }
        if (isInternal() && nodeType == BPlusTreeNodeType.LEAF_NODE) {
            throw new BPlusException("Cannot convert Internal Node to Leaf");
        }
        this.nodeType = nodeType;
    }

    public void writeNode(RandomAccessFile r, BPlusTreeConfiguration conf) {

    }

    public static int compareKey(Object a, Object b, String keyType) {
        int compare = 0;
        if (a == b)
            return 0;
        switch (keyType) {
            case "Int":
                compare = (int) a > (int) b ? 1 : -1;
                break;
            case "Long":
                compare = (long) a > (long) b ? 1 : -1;
                break;
            case "Float":
                compare = (float) a > (float) b ? 1 : -1;
                break;
            case "Double":
                compare = (double) a > (double) b ? 1 : -1;
                break;
            case "String":
                compare = a.toString().compareTo(b.toString());
                break;
            default:
                break;
        }
        return compare;
    }
}
