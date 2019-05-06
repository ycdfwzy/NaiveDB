package BPlusTree;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import sun.awt.image.ImageWatched;
import utils.Consts;
import utils.NumberUtils;
import utils.utilsException;

public class BPlusTree {

    private RandomAccessFile treeFile;
    private RandomAccessFile dataFile;
    private BPlusTreeNode root;
    private BPlusTreeConfiguration config;
    private HashMap<Long, BPlusTreeNode> cachedNodes;
    private HashMap<Long, LinkedList> cachedData;
    private long maxPageIndex;
    private ArrayList<Long> pageIndexPool;
    private long maxRowIndex;
    private ArrayList<Long> rowIndexPool;
    private long firstLeaf, lastLeaf;

    public void close()
        throws Exception {
        this.toFile();
        this.treeFile.close();
        this.dataFile.close();
    }

    public BPlusTree(BPlusTreeConfiguration config)
            throws IOException, BPlusException, utilsException {
        this.config = config;
        this.root = new BPlusTreeNode(1, -1,
                BPlusTreeNodeType.ROOT_LEAF_NODE, config, -1, -1);
        this.cachedNodes = new HashMap<Long, BPlusTreeNode>();
        this.cachedNodes.put(this.root.getPageIndex(), this.root);
        this.cachedData = new HashMap<Long, LinkedList>();

        this.lastLeaf = 1;
        this.firstLeaf = 1;
        this.maxPageIndex = 0;
        this.pageIndexPool = new ArrayList<Long>();
        this.maxRowIndex = -1;
        this.rowIndexPool = new ArrayList<Long>();
        this.treeFile = new RandomAccessFile(config.getFilename()+".tree", "rw");
        this.dataFile = new RandomAccessFile(config.getFilename()+".data", "rw");
        this.toFile();
    }

    public BPlusTree(String filename)
            throws IOException, utilsException, BPlusException {
        if (new File(filename+".tree").exists() &&
            new File(filename+".data").exists()) {
            fromFile(filename);
        } else
        {
            throw new BPlusException(filename + ".tree or " + filename + ".data does not exist!");
        }
    }

    /*
        page#0 format:
            pageSize|columnCnt|[columnNames]|[columnTypes]
            |rootPageIndex|first_leaf|last_leaf

     */
    public void fromFile(String filename)
        throws IOException, utilsException, BPlusException {

        this.treeFile = new RandomAccessFile(filename+".tree", "rw");
        this.dataFile = new RandomAccessFile(filename+".data", "rw");

        this.pageIndexPool = new ArrayList<Long>();
        this.rowIndexPool = new ArrayList<Long>();

        // pageSize
        byte[] tmp = new byte[16];
        treeFile.seek(0);
        treeFile.read(tmp, 0, 16);
        int pageSize = NumberUtils.parseInt(new String(tmp), 0, Consts.intSize);

        String s;
        byte[] block = new byte[pageSize];
        treeFile.seek(0);
        treeFile.read(block, 0, pageSize);
        s = new String(block);

        // columnCnt
        int columnCnt = NumberUtils.parseInt(s, Consts.intSize, Consts.intSize);
        if (columnCnt < 1) {
            throw new BPlusException("Too few columns!");
        }

        int pos = Consts.intSize * 2;
        LinkedList<String> columnNames = new LinkedList<String>();
        LinkedList<String> columnTypes = new LinkedList<String>();
        // [columnNames]
        for (int i = 0; i < columnCnt; ++i) {
            columnNames.add(s.substring(pos, pos+Consts.columnNameSize).trim());
            pos += Consts.columnNameSize;
        }

        // [columnTypes]
        for (int i = 0; i < columnCnt; ++i) {
            columnTypes.add(s.substring(pos, pos+Consts.columnTypeSize).trim());
            pos += Consts.columnTypeSize;
        }
        this.config = new BPlusTreeConfiguration(pageSize, filename,
                                                columnTypes.get(0),
                                                columnNames, columnTypes);

        this.maxRowIndex = dataFile.length()/this.config.getRowSize() - 1;
        this.maxPageIndex = treeFile.length()/this.config.getPageSize() - 1;

        // rootPageIndex
        long rootPageIndex = NumberUtils.parseLong(s, pos, Consts.longSize);
        this.root = new BPlusTreeNode(treeFile, rootPageIndex, this.config);
        this.cachedNodes = new HashMap<Long, BPlusTreeNode>();
        this.cachedNodes.put(rootPageIndex, this.root);
        pos += Consts.longSize;

        // firstLeaf
        this.firstLeaf = NumberUtils.parseLong(s, pos, Consts.longSize);
        pos += Consts.longSize;
        // lastLeaf
        this.lastLeaf = NumberUtils.parseLong(s, pos, Consts.longSize);

        this.cachedData = new HashMap<Long, LinkedList>();
    }

    public void toFile()
            throws IOException, utilsException, BPlusException{
        byte[] block = new byte[this.config.getPageSize()];
        byte[] tmp;
        Arrays.fill(block, (byte)0);

        int pos = 0;
        int n = this.config.getColumnSize();
        // pageSize
        tmp = Integer.toString(this.config.getPageSize()).getBytes();
        System.arraycopy(tmp, 0, block, pos, tmp.length);
        pos += Consts.intSize;
        // columnCnt
        tmp = Integer.toString(n).getBytes();
        System.arraycopy(tmp, 0, block, pos, tmp.length);
        pos += Consts.intSize;

        String[] columnNames = this.config.getColumnName();
        // [columnNames]
        for (int i = 0; i < n; ++i) {
            tmp = columnNames[i].getBytes();
            System.arraycopy(tmp, 0, block, pos, tmp.length);
            pos += Consts.columnNameSize;
        }

        String[] columTypes = this.config.getColumnType();
        // [columnTypes]
        for (int i = 0; i < n; ++i) {
            tmp = columTypes[i].getBytes();
            System.arraycopy(tmp, 0, block, pos, tmp.length);
            pos += Consts.columnTypeSize;
        }

        // rootPageIndex
        tmp = Long.toString(this.root.getPageIndex()).getBytes();
        System.arraycopy(tmp, 0, block, pos, tmp.length);
        pos += Consts.longSize;

        // fistLeaf
        tmp = Long.toString(this.firstLeaf).getBytes();
        System.arraycopy(tmp, 0, block, pos, tmp.length);
        pos += Consts.longSize;

        // lastLeaf
        tmp = Long.toString(this.lastLeaf).getBytes();
        System.arraycopy(tmp, 0, block, pos, tmp.length);

        treeFile.seek(0);
        treeFile.write(block, 0, this.config.getPageSize());

        // node write back
        this.root.toFile(treeFile, this.root.getPageIndex() * config.getPageSize());
        for (Map.Entry<Long, BPlusTreeNode> entry: this.cachedNodes.entrySet())
        if (entry.getValue().getPageIndex() != this.root.getPageIndex()) {
            entry.getValue().toFile(treeFile, entry.getKey() * config.getPageSize());
        }
        // data write back
        for (Map.Entry<Long, LinkedList> entry: this.cachedData.entrySet()) {
            write2Datafile(entry.getValue(), entry.getKey());
        }
    }

    public int getTreeDegree() {return config.getTreeDegree();}

    public String getKeyType() {return config.getKeyType();}

    public void update(LinkedList values)
            throws BPlusException, IOException, utilsException {
        if (values.size() != config.getColumnSize()) {
            throw new BPlusException("Too many/few values");
        }

        Object key = values.getFirst();
        this.update(this.root, key, values);
    }

    private void update(BPlusTreeNode p, Object key, LinkedList values)
            throws BPlusException, IOException, utilsException{
        if (p.isLeaf()) {
            long rowNum = p.getPtrByExactKey(key);
            if (rowNum != -1) {
                getData(rowNum);
                cachedData.put(rowNum, values);
            }
        } else
        {
            long to = p.getPtrByKey(key);
            BPlusTreeNode q = getNode(to);
            update(q, key, values);
        }
    }

    public void insert(LinkedList values)
            throws BPlusException, IOException, utilsException {
//        System.out.println(values.size());
//        System.out.println(config.getColumnType().length);
        if (values.size() != config.getColumnSize()) {
            throw new BPlusException("Too many/few values");
        }

        long rowNum = getNewRowIndex();
        updateCachedData(rowNum, values);

        Object key = values.getFirst();
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
            throws BPlusException, IOException, utilsException {
        if (p.isLeaf()) {
            p.addKeyAndPtr(key, rowNum);
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

    public LinkedList<LinkedList> searchAll()
        throws Exception{
        LinkedList<LinkedList> ret = new LinkedList<LinkedList>();
        long idx = this.firstLeaf;
        while (idx > 0) {
            BPlusTreeNode p = getNode(idx);
            int n = p.getCurrentSize();
            for (int i = 0; i < n; ++i)
                ret.add(getData(p.getPtr(i)));
            idx = p.getNextPage();
        }

        return ret;
    }

    public void search() {

    }

    public void delete(Object key)
        throws BPlusException, IOException, utilsException {
        delete(this.root, key);
    }

    private void delete(BPlusTreeNode p, Object key)
        throws BPlusException, IOException, utilsException {
        if (p.isLeaf()) {
            long rowNum = p.getPtrByExactKey(key);
            if (rowNum != -1) {
                removeData(rowNum);
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
        LinkedList<LinkedList> res = this.searchAll();
        for (LinkedList row: res) {
            for (Object value: row) {
                System.out.print(value);
                System.out.printf(" ");
            }
            System.out.println();
        }
    }

    public void write2Datafile(LinkedList values, long rowNum)
        throws IOException, BPlusException {

        byte[] rowData = new byte[config.getRowSize()];
        String[] types = config.getColumnType();
        Arrays.fill(rowData, (byte)0);
        int pos = 0;
        for (int i = 0; i < types.length; ++i) {
            pos += NumberUtils.toBytes(rowData, pos, values.get(i), types[i]);
        }

        dataFile.seek(rowNum*config.getRowSize());
        dataFile.write(rowData, 0, config.getRowSize());
    }

    private void updateNodes(BPlusTreeNode p, long pageIndex) {
        this.cachedNodes.put(pageIndex, p);
    }

    private void splitNode(BPlusTreeNode p)
        throws BPlusException, IOException, utilsException {
        if (p.isRoot()) {
            long newRootPageIndex = getNewPageIndex();

            BPlusTreeNode newRoot = new BPlusTreeNode(newRootPageIndex, -1, BPlusTreeNodeType.ROOT_NODE, this.config);
            updateNodes(newRoot, newRootPageIndex);

            if (p.isLeaf())
                p.setNodeType(BPlusTreeNodeType.LEAF_NODE);
            else if (p.isInternal())
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
        throws BPlusException, IOException, utilsException {
        if (p.isRoot()) {
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
        throws IOException, BPlusException, utilsException {
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

    private void removeData(long rowNum)
        throws IOException {
        int rowSize = config.getRowSize();
        byte[] bytes = new byte[rowSize];
        Arrays.fill(bytes, (byte)0);
        this.dataFile.seek(rowNum * rowSize);
        this.dataFile.write(bytes, 0, rowSize);

        this.cachedData.remove(rowNum);
    }

    private long getNewPageIndex() {
        if (pageIndexPool.isEmpty()) {
            maxPageIndex += 1;
            return maxPageIndex;
        } else
        {
            return pageIndexPool.remove(0);
        }
    }

    private long getNewRowIndex() {
        if (rowIndexPool.isEmpty()) {
            maxRowIndex += 1;
            return maxRowIndex;
        } else
        {
            return rowIndexPool.remove(0);
        }
    }

    public BPlusTreeConfiguration getConfig() {
        return config;
    }

    private BPlusTreeNode getNode(long pageIndex)
            throws BPlusException, IOException, utilsException{
        if (pageIndex <= 0) {
            throw new BPlusException("Underflow pageIndex!");
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

    private LinkedList getData(long rowNum)
        throws BPlusException, IOException, utilsException{
        if (rowNum < 0) {
            throw new BPlusException("Underflow row number!");
        }
        if (cachedData.containsKey(rowNum)) {
            return cachedData.get(rowNum);
        }
        LinkedList ret = new LinkedList();
        int rowSize = config.getRowSize();
        long offset = rowSize * rowNum;
        byte[] row = new byte[rowSize];

        dataFile.seek(offset);
        dataFile.read(row, 0, rowSize);

        String s = new String(row);

        int n = config.getColumnSize();
        String[] types = config.getColumnType();
        int pos = 0;
        for (int i = 0; i < n; ++i) {
            pos += NumberUtils.fromBytes(ret, s, pos, types[i], true);
        }
        updateCachedData(rowNum, ret);

        return ret;
    }

    private void updateCachedData(long rowNum, LinkedList value)
        throws IOException, BPlusException{
        if (!this.cachedData.containsKey(rowNum)) {
            // remove some data cache
            while (config.getRowSize() * this.cachedData.size() > Consts.memoryDataLimitation) {
                for (Map.Entry<Long, LinkedList> entry : this.cachedData.entrySet()) {
                    write2Datafile(entry.getValue(), entry.getKey());
                    this.cachedNodes.remove(entry.getKey());
                    break;
                }
            }
        }
        this.cachedData.put(rowNum, value);
    }
}
