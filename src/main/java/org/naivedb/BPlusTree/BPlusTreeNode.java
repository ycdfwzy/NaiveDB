package org.naivedb.BPlusTree;

import org.naivedb.utils.Consts;
import org.naivedb.utils.NumberUtils;
import org.naivedb.utils.NDException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

public class BPlusTreeNode {
    private ArrayList keyList;
    private ArrayList<Long> ptrList;
    private long pageIndex;
    private long parent;
    private long nextPage, prevPage;
    private BPlusTreeNodeType nodeType;
    private BPlusTreeConfiguration conf;

    public BPlusTreeNode(long pageIndex,
                         long parent,
                         BPlusTreeNodeType nodeType,
                         BPlusTreeConfiguration conf)
            throws NDException {
        this.pageIndex = pageIndex;
        this.parent = parent;
        this.nodeType = nodeType;
        this.conf = conf;
        this.keyList = new ArrayList();
        this.ptrList = new ArrayList<Long>();
        if (this.isLeaf()) {
            throw new NDException("Leaf should have nextPage or prevPage!");
        }
    }

    public BPlusTreeNode(long pageIndex,
                         long parent,
                         BPlusTreeNodeType nodeType,
                         BPlusTreeConfiguration conf,
                         long nextPage,
                         long prevPage)
            throws NDException {
        this.pageIndex = pageIndex;
        this.parent = parent;
        this.nodeType = nodeType;
        this.conf = conf;
        this.keyList = new ArrayList();
        this.ptrList = new ArrayList<Long>();
        if (!this.isLeaf()) {
            throw new NDException("Leaf shouldn't have nextPage or prevPage!");
        }
        this.nextPage = nextPage;
        this.prevPage = prevPage;
    }

    /*
        node page format
            node_type|parent_ptr|[(key, ptr)]
        leaf page format
            node_type|parent_ptr|next_page|prev_page|[(key, ptr)]
     */
    public BPlusTreeNode(RandomAccessFile input,
                        long pageIndex,
                         BPlusTreeConfiguration conf)
            throws IOException, NDException {
        this.pageIndex = pageIndex;
        this.conf = conf;
        long offset = pageIndex * conf.getPageSize();
        this.fromFile(input, offset);
    }

    public void fromFile(RandomAccessFile input, long offset)
        throws IOException, NDException {
        int pageSize = conf.getPageSize();
        byte[] pageB = new byte[pageSize];
        input.seek(offset);
        input.read(pageB, 0, pageSize);
        String pageS = new String(pageB);
        int pos = 0;
        // node_type | parent_ptr
        this.nodeType = BPlusTreeNodeType.values()[NumberUtils.parseInt(pageS, pos, Consts.nodeTypeSize)];
        this.parent = NumberUtils.parseLong(pageS, Consts.nodeTypeSize, Consts.longSize);
        pos = Consts.nodeTypeSize + Consts.longSize;
        // next_page | prev_page
        if (this.isLeaf()) {
            this.nextPage = NumberUtils.parseLong(pageS, pos, Consts.longSize);
            this.prevPage = NumberUtils.parseLong(pageS, pos + Consts.longSize, Consts.longSize);
            pos += Consts.longSize*2;
        }

        // [(key, ptr)]
        this.keyList = new ArrayList();
        this.ptrList = new ArrayList<Long>();
        int n = this.conf.getTreeDegree();
        for (int i = 0; i < n; ++i) {
            if (pageS.substring(pos).startsWith("\0")) {
                break;
            }
            pos += NumberUtils.fromBytes(this.keyList, pageS, pos, conf.getKeyType(), false);
            long ptr = NumberUtils.parseLong(pageS, pos, Consts.pointSize);
            this.ptrList.add(ptr);
            pos += Consts.pointSize;
        }
        if (!pageS.substring(pos).trim().isEmpty()) {
            throw new NDException("Node page is invalid!");
        }
    }

    public void toFile(RandomAccessFile output, long offset)
        throws IOException, NDException {
        int pageSize = conf.getPageSize();
        byte[] pageB = new byte[pageSize];
        byte[] tmp;
        Arrays.fill(pageB, (byte)0);

        // node_type
        tmp = Integer.toString(this.nodeType.ordinal()).getBytes();
        System.arraycopy(tmp, 0, pageB, 0, tmp.length);
        // parent_ptr
        tmp = Long.toString(this.parent).getBytes();
        System.arraycopy(tmp, 0, pageB, Consts.nodeTypeSize, tmp.length);
        int pos = Consts.nodeTypeSize + Consts.longSize;

        // next_page | prev_page
        if (this.isLeaf()) {
            tmp = Long.toString(this.nextPage).getBytes();
            System.arraycopy(tmp, 0, pageB, pos, tmp.length);
            tmp = Long.toString(this.prevPage).getBytes();
            System.arraycopy(tmp, 0, pageB, pos+Consts.longSize, tmp.length);
            pos += Consts.longSize*2;
        }

        // [(key, ptr)]
        for (int i = 0; i < keyList.size(); ++i) {
            pos += NumberUtils.toBytes(pageB, pos, keyList.get(i), conf.getKeyType());
            tmp = Long.toString(ptrList.get(i)).getBytes();
            System.arraycopy(tmp, 0, pageB, pos, tmp.length);
            pos += Consts.pointSize;
        }

        output.seek(offset);
        output.write(pageB, 0, pageSize);
    }

    public int getHeaderSize() {
        return Consts.nodeTypeSize + Consts.parentSize + (this.isLeaf() ? Consts.pointSize*2 : 0);
    }

    public boolean isOverflow() {
        return keyList.size() > this.conf.getTreeDegree();
    }

    public boolean isToMerge() {
        if (isRoot()) {
            return !isLeaf() && keyList.size() < 2;
        } else
        {
            return keyList.size() < (this.conf.getTreeDegree()+1) / 2;
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
//            int idx = lowerbound(key, conf.getKeyType()) + 1;
            int idx = lowerbound(key, conf.getKeyType());
            keyList.add(idx, key);
            return idx;
        }
    }

    public long getPtrByKey(Object key) {
        if (key == null) return -1;
        int idx = Integer.max(0, lowerbound(key, conf.getKeyType()));
        if (keyList.size() <= idx)
            return -1;

        if (idx > 0 && compareKey(keyList.get(idx), key, conf.getKeyType()) == 1)
            idx -= 1;
        return ptrList.get(idx);
    }

    public long getPtrByExactKey(Object key) {
        if (key == null) return -1;
        int idx = lowerbound(key, conf.getKeyType());
        if (idx < 0 || keyList.size() <= idx)
            return -1;
        if (compareKey(keyList.get(idx), key, conf.getKeyType()) != 0)
            return -1;
        return ptrList.get(idx);
    }

    public int getPtrIndex(long ptr) {
        for (int i = 0; i < ptrList.size(); ++i)
            if (ptr == ptrList.get(i))
                return i;
        return -1;
    }

    private int lowerbound(Object key, String keyType) {
        int n = keyList.size();
        for (int i = 0; i < n; ++i)
        if (compareKey(keyList.get(i), key, keyType) >= 0)
            return i;
        return n;
//        int left = 0, right = keyList.size() - 1;
//        while (left < right) {
//            int mid = (left + right) / 2;
//            int compare = compareKey(keyList.get(mid), key, keyType);
//            if (compare == 0) {
//                return mid;
//            }
//            if (compare == 1) {
//                right = mid-1;
//            } else {
//                left = mid+1;
//            }
//        }
//        return right;
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

    public void addKeyAndPtr(Object key, long ptr) {
        int idx = addKey(key);
        addPtr(idx, ptr);
    }

    public void removeKeyAndPtr(int idx) {
        keyList.remove(idx);
        ptrList.remove(idx);
    }

    public void removeKeyAndPtr(Object key) {
        if (key == null) return;
        int idx = lowerbound(key, conf.getKeyType());
        if (compareKey(key, keyList.get(idx), conf.getKeyType()) == 0) {
            keyList.remove(idx);
            ptrList.remove(idx);
        }
    }


    public long getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setNodeType(BPlusTreeNodeType nodeType)
    throws NDException{
        if (isLeaf() && nodeType == BPlusTreeNodeType.INTERNAL_NODE) {
            throw new NDException("Cannot convert Leaf to Internal Node");
        }
        if (isInternal() && nodeType == BPlusTreeNodeType.LEAF_NODE) {
            throw new NDException("Cannot convert Internal Node to Leaf");
        }
        this.nodeType = nodeType;
        if (!this.isLeaf()) {
            this.nextPage = this.prevPage = -1;
        }
    }

    public static int compareKey(Object a, Object b, String type) {
        int compare = 0;
        if (a == null || b == null)
            return 0;
        if (a.equals(b))
            return 0;
        switch (type) {
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
            default:    // String***
                compare = a.toString().compareTo(b.toString());
                break;
        }
        return compare;
    }
}
