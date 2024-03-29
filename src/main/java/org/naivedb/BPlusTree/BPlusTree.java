package org.naivedb.BPlusTree;

import java.io.*;
import java.util.*;

import org.naivedb.utils.Consts;
import org.naivedb.utils.NumberUtils;
import org.naivedb.utils.StreamUtils;
import org.naivedb.utils.NDException;

public class BPlusTree {

    private File headerFile;
    private RandomAccessFile treeFile;
//    private RandomAccessFile dataFile;
    private BPlusTreeNode root;
    private BPlusTreeConfiguration config;
    private HashMap<Long, BPlusTreeNode> cachedNodes;
//    private HashMap<Long, LinkedList> cachedData;
    private long maxPageIndex;
    private LinkedList<Long> pageIndexPool;
//    private long maxRowIndex;
//    private ArrayList<Long> rowIndexPool;
    private long firstLeaf, lastLeaf;

    public void close(boolean needWriteBack)
            throws IOException, NDException {
        if (needWriteBack)
            this.toFile();
        this.treeFile.close();
    }

    public void close()
        throws IOException, NDException {
        this.close(true);
    }

    public BPlusTree(BPlusTreeConfiguration config)
            throws IOException, NDException {
        this.config = config;
        this.root = new BPlusTreeNode(0, -1,
                BPlusTreeNodeType.ROOT_LEAF_NODE, config, -1, -1);
        this.cachedNodes = new HashMap<Long, BPlusTreeNode>();
        this.cachedNodes.put(this.root.getPageIndex(), this.root);
//        this.cachedData = new HashMap<Long, LinkedList>();

        this.lastLeaf = 0;
        this.firstLeaf = 0;
        this.maxPageIndex = 0;
        this.pageIndexPool = new LinkedList<>();
//        this.maxRowIndex = -1;
//        this.rowIndexPool = new ArrayList<Long>();
        this.headerFile = new File(config.getFilename()+".header");
        this.treeFile = new RandomAccessFile(config.getFilename()+".tree", "rw");
//        this.dataFile = new RandomAccessFile(config.getFilename()+".data", "rw");
        this.toFile();
    }

    public BPlusTree(String filename)
            throws IOException, NDException {
        if (new File(filename+".header").exists() &&
            new File(filename+".tree").exists()) {
            fromFile(filename);
        } else
        {
            throw new NDException(filename + ".header or " + filename + ".tree does not exist!");
        }
    }

    /*
        header format:
            pageSize|keyType|columnCnt|[columnTypes]
            |rootPageIndex|first_leaf|last_leaf
            |blankPageCnt|[blankPageIndex]

     */
    private void fromFile(String filename)
        throws IOException, NDException {

        this.headerFile = new File(filename+".header");
        this.treeFile = new RandomAccessFile(filename+".tree", "rw");
//        this.dataFile = new RandomAccessFile(filename+".data", "rw");

        this.fromHeaderFile(filename);

        this.cachedNodes = new HashMap<Long, BPlusTreeNode>();
        this.cachedNodes.put(this.root.getPageIndex(), this.root);
//        this.cachedData = new HashMap<Long, LinkedList>();
    }

    private void toFile()
            throws IOException, NDException{
        byte[] block = new byte[this.config.getPageSize()];
        Arrays.fill(block, (byte)0);
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(this.headerFile));

        int pos = 0;
        int n = this.config.getColumnSize();
        // pageSize
        StreamUtils.writeInt(output, this.config.getPageSize());

        // keyType
        StreamUtils.writeString(output, this.config.getKeyType(), Consts.columnTypeSize);

        // columnCnt
        StreamUtils.writeInt(output, n);

        // [columnTypes]
        for (int i = 0; i < n; ++i) {
            StreamUtils.writeString(output, this.config.getColumnType(i), Consts.columnTypeSize);
        }

        // rootPageIndex
        StreamUtils.writeLong(output, this.root.getPageIndex());

        // fistLeaf
        StreamUtils.writeLong(output, this.firstLeaf);

        // lastLeaf
        StreamUtils.writeLong(output, this.lastLeaf);

        // blankPageCnt
        StreamUtils.writeInt(output, this.pageIndexPool.size());
        for (long pageIndex: this.pageIndexPool) {
            StreamUtils.writeLong(output, pageIndex);
        }
        output.close();

        treeFile.seek(0);
//        treeFile.write(block, 0, this.config.getPageSize());
        // node write back
        this.root.toFile(treeFile, this.root.getPageIndex() * config.getPageSize());
        for (Map.Entry<Long, BPlusTreeNode> entry: this.cachedNodes.entrySet())
        if (entry.getValue().getPageIndex() != this.root.getPageIndex()) {
            entry.getValue().toFile(treeFile, entry.getKey() * config.getPageSize());
        }
        // data write back
//        for (Map.Entry<Long, LinkedList> entry: this.cachedData.entrySet()) {
//            write2Datafile(entry.getValue(), entry.getKey());
//        }
    }

    private void fromHeaderFile(String filename)
            throws IOException, NDException {

        BufferedInputStream input = new BufferedInputStream(new FileInputStream(this.headerFile));

        // pageSize
        int pageSize = StreamUtils.readInt(input);

        // keyType
        String keyType = StreamUtils.readString(input, Consts.columnTypeSize);

        // columnCnt
        int columnCnt = StreamUtils.readInt(input);
        if (columnCnt < 1) {
            throw new NDException("Too few columns!");
        }

        LinkedList<String> columnTypes = new LinkedList<String>();

        // [columnTypes]
        for (int i = 0; i < columnCnt; ++i) {
            String type = StreamUtils.readString(input, Consts.columnTypeSize);
            columnTypes.add(type);
        }
        this.config = new BPlusTreeConfiguration(pageSize, filename,
                                                keyType, columnTypes);

        this.maxPageIndex = treeFile.length()/this.config.getPageSize() - 1;

        // rootPageIndex
        long rootPageIndex = StreamUtils.readLong(input);
        this.root = new BPlusTreeNode(treeFile, rootPageIndex, this.config);

        // firstLeaf
        this.firstLeaf = StreamUtils.readLong(input);
        // lastLeaf
        this.lastLeaf = StreamUtils.readLong(input);

        // blankPageCnt
        int blankPageCnt = StreamUtils.readInt(input);
        this.pageIndexPool = new LinkedList<>();
        // [blankPage]
        for (int i = 0; i < blankPageCnt; ++i) {
            long pageIndex = StreamUtils.readLong(input);
            this.pageIndexPool.add(pageIndex);
        }
        input.close();

//        this.rowIndexPool = new ArrayList<Long>();
    }

    public boolean check() throws IOException, NDException {
        return check(this.root);
    }

    private boolean check(BPlusTreeNode p) throws IOException, NDException {
        if (p.isLeaf()) return true;
        int n = p.getCurrentSize();
        for (int i = 0; i < n; ++i) {
            BPlusTreeNode q = getNode(p.getPtr(i));
            if (q.getParent() != p.getPageIndex() || !check(q))
                return false;
        }
        return true;
    }

    public int getTreeDegree() {return config.getTreeDegree();}

    public String getKeyType() {return config.getKeyType();}

    public void update(Object oldKey, Object newKey, long rowNum)
            throws NDException, IOException, NDException {
        this.delete(oldKey);
        this.insert(newKey, rowNum);
    }

//    private void update(BPlusTreeNode p, Object key, LinkedList values)
//            throws NDException, IOException, NDException{
//        if (p.isLeaf()) {
//            long rowNum = p.getPtrByExactKey(key);
//            if (rowNum != -1) {
//                getData(rowNum);
//                cachedData.put(rowNum, values);
//            } else
//                throw new NDException("Can't find primary key '" + key.toString() + "'!");
//        } else
//        {
//            long to = p.getPtrByKey(key);
//            BPlusTreeNode q = getNode(to);
//            update(q, key, values);
//        }
//    }

    public void insert(Object key, long rowNum)
            throws NDException, IOException {
//        long rowNum = getNewRowIndex();
//        updateCachedData(rowNum, values);

        if (root.isEmpty()) {
            root.addKeyAndPtr(key, rowNum);
            root.toFile(treeFile, config.getPageSize() * root.getPageIndex());
        } else
        {
            insert(root, key, rowNum);
        }
    }

    /*
        insert into non-empty nodes
        --- p         tree node
        --- key       key/index
        --- rowNum    row number in datafile(store in leaf node)
     */
    private void insert(BPlusTreeNode p, Object key, long rowNum)
            throws NDException, IOException {
        if (p.isLeaf()) {
            if (p.getPtrByExactKey(key) == -1) {
                p.addKeyAndPtr(key, rowNum);
            } else
                throw new NDException("Duplicate primary key '" + key.toString() + "' found!");
        } else
        {
            long to = p.getPtrByKey(key);
            BPlusTreeNode q = getNode(to);
            insert(q, key, rowNum);
        }
        // reBalance
        if (BPlusTreeNode.compareKey(p.getKey(0), key, config.getKeyType()) == 1) {
            p.setKey(0, key);
        }
        if (p.isOverflow()) {
            splitNode(p);
        }
    }

    public LinkedList<Long> searchAll()
        throws Exception{
        LinkedList<Long> ret = new LinkedList<Long>();
        long idx = this.firstLeaf;
        while (idx >= 0) {
            BPlusTreeNode p = getNode(idx);
            int n = p.getCurrentSize();
            for (int i = 0; i < n; ++i)
                ret.add(p.getPtr(i));
            idx = p.getNextPage();
        }

        return ret;
    }

    public LinkedList<Long> search(Object low, boolean eql, Object up, boolean eqr)
            throws NDException, IOException {
        if (eql) {
            if (eqr) {
                return searchRange(this.root, low, 0, up, 0);
            } else
            {
                return searchRange(this.root, low, 0, up, -1);
            }
        } else
        {
            if (eqr) {
                return searchRange(this.root, low, 1, up, 0);
            } else
            {
                return searchRange(this.root, low, 1, up, -1);
            }
        }
    }

    public LinkedList<Long> search(Object key, String type)
            throws NDException, IOException {
        switch (type) {
            case "EQ":  // key ==
                return search(this.root, key);
            case "LT": // < key
                return searchRange(this.root, null, 0, key, -1);
            case "GT": // > key
                return searchRange(this.root, key, 1, null, 0);
            case "NLT": // >= key
                return searchRange(this.root, key, 0, null, 0);
            case "NGT": // <= key
                return searchRange(this.root, null, 0, key, 0);
        }
        throw new NDException("Unknown search type '" + type + "'");
    }

    public LinkedList<Long> search(Object key)
            throws NDException, IOException {
        return search(key, "EQ");
    }

    private LinkedList<Long> search(BPlusTreeNode p, Object key)
            throws NDException, IOException {
        if (p.isLeaf()) {
            LinkedList<Long> ret = new LinkedList<Long>();
            long rowNum = p.getPtrByExactKey(key);
            if (rowNum != -1) {
                ret.add(rowNum);
            }
            return ret;
        } else
        {
            long to = p.getPtrByKey(key);
            BPlusTreeNode q = getNode(to);
            return search(q, key);
        }
    }

    private LinkedList<Long> searchRange(BPlusTreeNode p, Object low, int k1, Object up, int k2)
            throws NDException, IOException {
        LinkedList<Long> ret = new LinkedList<Long>();
        int n = p.getCurrentSize();
        if (p.isLeaf()) {
            for (int i = 0; i < n; ++i)
                if (this.inRange(p.getKey(i), low, k1, up, k2)) {
                    ret.add(p.getPtr(i));
                }
            return ret;
        } else
        {
            for (int i = 0; i < n; ++i) {
                // key(i) > up
                if (BPlusTreeNode.compareKey(p.getKey(i), up, this.config.getKeyType()) > k2) {
                    continue;
                }
                // key(i+1) < low
                if (i < n-1 && BPlusTreeNode.compareKey(p.getKey(i+1), low, this.config.getKeyType()) < k1) {
                    continue;
                }
                BPlusTreeNode q = getNode(p.getPtr(i));
                ret.addAll(searchRange(q, low, k1, up, k2));
            }
            return ret;
        }
    }

    public void delete(Object key)
        throws NDException, IOException {
        delete(this.root, key);
    }

    private void delete(BPlusTreeNode p, Object key)
        throws NDException, IOException {
        if (p.isLeaf()) {
            long rowNum = p.getPtrByExactKey(key);
            if (rowNum != -1) {
//                removeData(rowNum);
                p.removeKeyAndPtr(key);
            }
        } else
        {
            long to = p.getPtrByKey(key);
            BPlusTreeNode q = getNode(to);
            delete(q, key);
        }

        // reBalance
        if (p.isToMerge()) {
            afterDelete(p);
        }
    }

    public void printTree()
        throws Exception {
        LinkedList<Long> res = this.searchAll();
        for (Long row: res) {
            System.out.println(row);
        }
    }

//    public void write2Datafile(LinkedList values, long rowNum)
//        throws IOException, NDException {
//
//        byte[] rowData = new byte[config.getRowSize()];
//        Arrays.fill(rowData, (byte)0);
//        int pos = 0;
//        int n = this.config.getColumnSize();
//        for (int i = 0; i < n; ++i) {
//            pos += NumberUtils.toBytes(rowData, pos, values.get(i), this.config.getColumnType(i));
//        }
//
//        dataFile.seek(rowNum*config.getRowSize());
//        dataFile.write(rowData, 0, config.getRowSize());
//    }

    private void updateNodes(BPlusTreeNode p, long pageIndex) {
        this.cachedNodes.put(pageIndex, p);
    }

    private void splitNode(BPlusTreeNode p)
        throws NDException, IOException {
        if (p.isRoot()) {
            long newRootPageIndex = getNewPageIndex();

            BPlusTreeNode newRoot = new BPlusTreeNode(newRootPageIndex, -1, BPlusTreeNodeType.ROOT_NODE, this.config);
            updateNodes(newRoot, newRootPageIndex);

            if (p.isLeaf())
                p.setNodeType(BPlusTreeNodeType.LEAF_NODE);
            else// if (p.isInternal())
                p.setNodeType(BPlusTreeNodeType.INTERNAL_NODE);
            p.setParent(newRootPageIndex);
            newRoot.addKeyAndPtr(p.getKey(0), p.getPageIndex());
            this.root = newRoot;
        }

        BPlusTreeNode q;
        BPlusTreeNodeType nodeType;
        long newPageIndex = getNewPageIndex();
        if (p.isLeaf()) {
            nodeType = BPlusTreeNodeType.LEAF_NODE;
            p.setNodeType(BPlusTreeNodeType.LEAF_NODE);
            // insert into Node
            q = new BPlusTreeNode(newPageIndex, p.getParent(), nodeType, this.config, p.getNextPage(), p.getPageIndex());

            if (p.getNextPage() > 0)
                getNode(p.getNextPage()).setPrevPage(newPageIndex);
            p.setNextPage(newPageIndex);
        } else
        if (p.isInternal()) {
            nodeType = BPlusTreeNodeType.INTERNAL_NODE;
            p.setNodeType(BPlusTreeNodeType.INTERNAL_NODE);
            p.setNextPage(0);
            p.setPrevPage(0);
            q = new BPlusTreeNode(newPageIndex, p.getParent(), nodeType, this.config);
        } else
        {
            nodeType = BPlusTreeNodeType.INTERNAL_NODE;
            q = new BPlusTreeNode(newPageIndex, p.getParent(), nodeType, this.config);
        }
        updateNodes(q, newPageIndex);

        int m = p.getCurrentSize();
        for (int i = m/2; i < m; ++i) {
            Object key = p.getKey(i);
            long ptr = p.getPtr(i);
            q.addKeyAndPtr(key, ptr);
            if (!p.isLeaf()) {
                getNode(ptr).setParent(newPageIndex);
            }
        }
        for (int i = m/2; i < m; ++i) {
            p.removeKeyAndPtr(m/2);
        }

        BPlusTreeNode parent = getNode(p.getParent());
        parent.addKeyAndPtr(q.getKey(0), newPageIndex);

        if (this.lastLeaf == p.getPageIndex())
            this.lastLeaf = newPageIndex;
    }

    private void afterDelete(BPlusTreeNode p)
        throws NDException, IOException {
        if (p.isRoot()) {
            if (p.isLeaf()) return;
            this.root = getNode(p.getPtr(0));
            if (this.root.isLeaf()) {
                this.root.setNodeType(BPlusTreeNodeType.ROOT_LEAF_NODE);
            } else
            if (this.root.isInternal()) {
                this.root.setNodeType(BPlusTreeNodeType.ROOT_NODE);
            }
        } else
        {
            BPlusTreeNode parent = getNode(p.getParent());
            int idx = parent.getPtrIndex(p.getPageIndex());
            if (idx == 0) { // no previous siblings
                BPlusTreeNode siblings = getNode(parent.getPtr(idx+1));
                if (siblings.getCurrentSize() + p.getCurrentSize() <= config.getTreeDegree()) {
                    // merge
                    merge(parent, p, siblings, idx+1);
                } else
                {
                    Object key = siblings.getKey(0);
                    long ptr = siblings.getPtr(0);
                    p.addKeyAndPtr(key, ptr);
                    if (!p.isLeaf())
                        getNode(ptr).setParent(p.getPageIndex());
                    siblings.removeKeyAndPtr(0);
                    parent.setKey(idx+1, siblings.getKey(0));
                }
            } else
            {   // otherwise
                BPlusTreeNode siblings = getNode(parent.getPtr(idx-1));
                if (siblings.getCurrentSize() + p.getCurrentSize() <= config.getTreeDegree()) {
                    // merge
                    merge(parent, siblings, p, idx);
                } else
                {
                    int n = siblings.getCurrentSize();
                    Object key = siblings.getKey(n-1);
                    long ptr = siblings.getPtr(n-1);
                    p.addKeyAndPtr(key, ptr);
                    if (!p.isLeaf())
                        getNode(ptr).setParent(p.getPageIndex());
                    siblings.removeKeyAndPtr(n-1);
                    parent.setKey(idx, p.getKey(0));
                }
            }
        }
    }

    private void merge(BPlusTreeNode parent, BPlusTreeNode p, BPlusTreeNode q, int qIndex)
        throws IOException, NDException {
        int n = q.getCurrentSize();
        for (int i = 0; i < n; ++i) {
            Object key = q.getKey(i);
            long ptr = q.getPtr(i);
            p.addKeyAndPtr(key, ptr);
            if (!p.isLeaf())
                getNode(ptr).setParent(p.getPageIndex());
        }
        if (q.isLeaf()) {
            p.setNextPage(q.getNextPage());
            if (q.getNextPage() > 0) {
                getNode(q.getNextPage()).setPrevPage(p.getPageIndex());
            }
            if (q.getPageIndex() == this.lastLeaf) {
                this.lastLeaf = p.getPageIndex();
            }
        }
        parent.removeKeyAndPtr(qIndex);
        removeNode(q.getPageIndex());
    }

    private void removeNode(long pageIndex)
        throws IOException {
        int pageSize = config.getPageSize();
        byte[] bytes = new byte[pageSize];
        Arrays.fill(bytes, (byte)0);
        this.treeFile.seek(pageIndex * pageSize);
        this.treeFile.write(bytes, 0, pageSize);

        this.cachedNodes.remove(pageIndex);
    }

//    private void removeData(long rowNum)
//        throws IOException {
//        int rowSize = config.getRowSize();
//        byte[] bytes = new byte[rowSize];
//        Arrays.fill(bytes, (byte)0);
//        this.dataFile.seek(rowNum * rowSize);
//        this.dataFile.write(bytes, 0, rowSize);
//
//        this.cachedData.remove(rowNum);
//    }

    private long getNewPageIndex() {
        if (pageIndexPool.isEmpty()) {
            maxPageIndex += 1;
            return maxPageIndex;
        } else
        {
            return pageIndexPool.remove(0);
        }
    }

//    private long getNewRowIndex() {
//        if (rowIndexPool.isEmpty()) {
//            maxRowIndex += 1;
//            return maxRowIndex;
//        } else
//        {
//            return rowIndexPool.remove(0);
//        }
//    }

    public BPlusTreeConfiguration getConfig() {
        return config;
    }

    private BPlusTreeNode getNode(long pageIndex)
            throws NDException, IOException{
        if (pageIndex < 0) {
            throw new NDException("Underflow pageIndex!");
        } else
        {
            if (!this.cachedNodes.containsKey(pageIndex)) {
                // remove some node cache
                while (this.cachedNodes.size() * config.getPageSize() > Consts.memoryNodeLimitation) {
                    for (Map.Entry<Long, BPlusTreeNode> entry: this.cachedNodes.entrySet()) {
                        entry.getValue().toFile(treeFile, entry.getKey() * config.getPageSize());
                        this.cachedNodes.remove(entry.getKey());
                        break;
                    }
                }
                this.cachedNodes.put(pageIndex, new BPlusTreeNode(treeFile, pageIndex, config));
            }
            return cachedNodes.get(pageIndex);
        }
    }

//    private LinkedList getData(long rowNum)
//        throws NDException, IOException, NDException{
//        if (rowNum < 0) {
//            throw new NDException("Underflow row number!");
//        }
//        if (cachedData.containsKey(rowNum)) {
//            return cachedData.get(rowNum);
//        }
//        LinkedList ret = new LinkedList();
//        int rowSize = config.getRowSize();
//        long offset = rowSize * rowNum;
//        byte[] row = new byte[rowSize];
//
//        dataFile.seek(offset);
//        dataFile.read(row, 0, rowSize);
//
//        String s = new String(row);
//
//        int n = config.getColumnSize();
//        int pos = 0;
//        for (int i = 0; i < n; ++i) {
//            pos += NumberUtils.fromBytes(ret, s, pos, this.config.getColumnType(i), true);
//        }
//        updateCachedData(rowNum, ret);
//
//        return ret;
//    }

//    private void updateCachedData(long rowNum, LinkedList value)
//        throws IOException, NDException {
//        if (!this.cachedData.containsKey(rowNum)) {
//            // remove some data cache
//            while (config.getRowSize() * this.cachedData.size() > Consts.memoryDataLimitation) {
//                for (Map.Entry<Long, LinkedList> entry : this.cachedData.entrySet()) {
//                    write2Datafile(entry.getValue(), entry.getKey());
//                    this.cachedData.remove(entry.getKey());
//                    break;
//                }
//            }
//        }
//        this.cachedData.put(rowNum, value);
//    }

    private boolean inRange(Object value, Object low, int k1, Object up, int k2) {
        return (BPlusTreeNode.compareKey(value, low, config.getKeyType()) >= k1 &&
            BPlusTreeNode.compareKey(value, up, config.getKeyType()) <= k2);
    }
}
