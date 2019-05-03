package BPlusTree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import utils.Consts;
import utils.NumberUtils;
import utils.utilsException;

public class BPlusTree {

    private RandomAccessFile treeFile;
    private RandomAccessFile dataFile;
    private BPlusTreeNode root;
    private BPlusTreeConfiguration config;
    private ArrayList<BPlusTreeNode> allNodes;
    private long maxPageIndex;
    private ArrayList<Long> pageIndexPool;
    private long maxRowIndex;
    private ArrayList<Long> rowIndexPool;
    private long firstLeaf, lastLeaf;

    public void close()
        throws Exception {
        this.toFile();
        System.out.println(treeFile.length());
        this.treeFile.close();
        this.dataFile.close();
    }

    public BPlusTree(BPlusTreeConfiguration config)
            throws IOException, BPlusException, utilsException {
        this.config = config;
        this.root = new BPlusTreeNode(1, -1,
                BPlusTreeNodeType.ROOT_LEAF_NODE, config, -1, -1);
        this.allNodes = new ArrayList<BPlusTreeNode>();
        this.allNodes.add(this.root);

        this.lastLeaf = 1;
        this.firstLeaf = 1;
        this.maxPageIndex = 0;
        this.pageIndexPool = new ArrayList<Long>();
        this.maxRowIndex = -1;
        this.rowIndexPool = new ArrayList<Long>();
        this.treeFile = new RandomAccessFile("test.tree", "rw");
        this.dataFile = new RandomAccessFile("test.data", "rw");
        this.toFile();
    }

    public BPlusTree(String treeFilename,
                     String dataFilename)
            throws IOException, utilsException, BPlusException {
        treeFile = new RandomAccessFile(treeFilename, "rw");
        dataFile = new RandomAccessFile(dataFilename, "rw");
//        System.out.println(dataFile.length());
        fromFile(treeFile, dataFile);
    }

    /*
        page#0 format:
            pageSize|columnCnt|[columnNames]|[columnTypes]
            |rootPageIndex|first_leaf|last_leaf

     */
    public void fromFile(RandomAccessFile treeFile,
                         RandomAccessFile dataFile)
        throws IOException, utilsException, BPlusException {

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
        this.config = new BPlusTreeConfiguration(pageSize, columnTypes.get(0),
                                                columnNames, columnTypes);

        // Todo: maybe some bugs
        this.maxRowIndex = dataFile.length()/this.config.getRowSize() - 1;
        this.maxPageIndex = treeFile.length()/this.config.getPageSize() - 1;

        // rootPageIndex
        long rootPageIndex = NumberUtils.parseLong(s, pos, Consts.longSize);
        this.root = new BPlusTreeNode(treeFile, rootPageIndex, this.config);
        this.allNodes = new ArrayList<BPlusTreeNode>();
        extendCapacity(this.allNodes, (int)rootPageIndex+1);
        this.allNodes.set((int)rootPageIndex, this.root);
        pos += Consts.longSize;

        // firstLeaf
        this.firstLeaf = NumberUtils.parseLong(s, pos, Consts.longSize);
        pos += Consts.longSize;
        // lastLeaf
        this.lastLeaf = NumberUtils.parseLong(s, pos, Consts.longSize);
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

        for (BPlusTreeNode node: allNodes)
        if (node != null) {
            node.toFile(treeFile, node.getPageIndex() * config.getPageSize());
        }
    }

    public int getTreeDegree() {return config.getTreeDegree();}

    public String getKeyType() {return config.getKeyType();}

    public void insert(LinkedList values)
            throws BPlusException, IOException, utilsException {
//        System.out.println(values.size());
//        System.out.println(config.getColumnType().length);
        if (values.size() != config.getColumnType().length) {
            throw new BPlusException("Too many/few values");
        }

        long rowNum = getNewRowIndex();
        write2Datafile(values, rowNum);

        Object key = (Object) values.getFirst();
        if (root.isEmpty()) {
            root.addKeyAndPtr(key, rowNum);
//            System.out.println("Before" + treeFile.length());
            root.toFile(treeFile, config.getPageSize() * root.getPageIndex());
//            System.out.println("After" + treeFile.length());
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
            long to = p.getKeyIndex(key);
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
                ret.add(getData(dataFile, p.getPtr(i)));
            idx = p.getNextPage();
        }

        return ret;
    }

    public void search() {

    }

    public void delete(Object key) {
        // Todo: delete single key
    }

    public void printTree() {
        System.out.println(config.getKeyType());
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

    private BPlusTreeNode getNode(long pageIndex)
            throws BPlusException, IOException, utilsException{
        if (pageIndex <= 0) {
            throw new BPlusException("Underflow pageIndex!");
        } else
        {
            extendCapacity(this.allNodes, (int)pageIndex+1);
            if (allNodes.get((int)pageIndex) == null) {
                allNodes.set((int)pageIndex, new BPlusTreeNode(treeFile, pageIndex, config));
            }
            return allNodes.get((int)pageIndex);
        }
    }

    private void updateNodes(BPlusTreeNode p, long pageIndex) {
        extendCapacity(this.allNodes, (int)pageIndex+1);
        this.allNodes.set((int)pageIndex, p);
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

    private void extendCapacity(ArrayList arr, int minSize) {
        arr.ensureCapacity(minSize);
        int n = arr.size();
        for (int i = n; i < minSize; ++i)
            arr.add(null);
    }

    public BPlusTreeConfiguration getConfig() {
        return config;
    }

    private LinkedList getData(RandomAccessFile dataFile, long rowNum)
        throws Exception{
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
            pos += NumberUtils.fromBytes(ret, s, pos, types[i]);
        }

        return ret;
    }
}
