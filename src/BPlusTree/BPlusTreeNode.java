package BPlusTree;

import java.io.RandomAccessFile;
import java.util.ArrayList;

public class BPlusTreeNode {
    private ArrayList keyList;
    private ArrayList<Long> ptrList;
    private long pageIndex;
    private BPlusTreeNodeType nodeType;
    private BPlusTreeConfiguration conf;

    public BPlusTreeNode(long pageIndex,
                         BPlusTreeNodeType nodeType,
                         BPlusTreeConfiguration conf)
    throws BPlusException {
        this.pageIndex = pageIndex;
        this.nodeType = nodeType;
        this.conf = conf;
        this.keyList = new ArrayList();
        this.ptrList = new ArrayList();
    }

    public boolean isOverflow(BPlusTreeConfiguration conf) {
        return keyList.size() >= conf.getTreeDegree();
    }

    public boolean isToMerge(BPlusTreeConfiguration conf) {
        if (isRoot()) {
            return !isLeaf() && isEmpty();
        }
        else {
            return keyList.size() < conf.getTreeDegree() / 2;
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

    private int binarySearch(Object key, String keyType) {
        int left = 0, right = keyList.size() - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (keyList.get(mid) == key) {
                return mid;
            }
            int compare = 0;
            switch (keyType) {
                case "Int":
                    compare = (int) keyList.get(mid) > (int) key ? 1 : -1;
                    break;
                case "Long":
                    compare = (long) keyList.get(mid) > (long) key ? 1 : -1;
                    break;
                case "Float":
                    compare = (float) keyList.get(mid) > (float) key ? 1 : -1;
                    break;
                case "Double":
                    compare = (double) keyList.get(mid) > (double) key ? 1 : -1;
                    break;
                case "String":
                    compare = keyList.get(mid).toString().compareTo(key.toString());
                    break;
                default:
                    break;
            }
            if (compare == 1) {
                right = mid;
            } else {
                left = mid;
            }
            continue;
        }
        return left;
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
}
