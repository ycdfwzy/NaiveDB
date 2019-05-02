package BPlusTree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import utils.Consts;

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

    public BPlusTree(BPlusTreeConfiguration config)
            throws IOException, BPlusException {
        this.config = config;
        this.root = new BPlusTreeNode(1, -1, BPlusTreeNodeType.ROOT_LEAF_NODE, config);
        this.allNodes = new ArrayList<BPlusTreeNode>();
        this.allNodes.add(this.root);

        this.maxPageIndex = 1;
        this.pageIndexPool = new ArrayList<Long>();
        this.maxRowIndex = -1;
        this.rowIndexPool = new ArrayList<Long>();
        this.treeFile = new RandomAccessFile("***.tree", "rw");
        this.dataFile = new RandomAccessFile("***.data", "rw");
    }

    public BPlusTree(String treeFilename,
                     String dataFilename) throws IOException {

        treeFile = new RandomAccessFile(treeFilename, "rw");
        dataFile = new RandomAccessFile(dataFilename, "rw");
        fromFile(treeFile, dataFile);
    }

    /*
        page#0 format:
            pageSize|columnCnt|[columNames]|[columnTypes]

     */
    public void fromFile(RandomAccessFile treeFile,
                         RandomAccessFile dataFile)
        throws IOException {
//        maxRowIndex = dataFile.length()/config.getRowSize();
//        maxPageIndex = treeFile.length()/config.getPageSize();
        this.pageIndexPool = new ArrayList<Long>();
        this.rowIndexPool = new ArrayList<Long>();
        // Todo: read tree from file
    }

    public int getTreeDegree() {return config.getTreeDegree();}

    public String getKeyType() {return config.getKeyType();}

    public void insert(LinkedList values)
            throws BPlusException, IOException {
        if (values.size() != config.getColumnType().length) {
            throw new BPlusException("Too many/few values");
        }

        long rowNum = getNewRowIndex();
        write2Datafile(values, rowNum);

        Object key = (Object) values.getFirst();
        if (root.isEmpty()) {
            root.addKeyAndPtr(key, rowNum);
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
            throws BPlusException, IOException {
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

    public void search() {

    }

    public void delete() {

    }

    public void printTree() {
        System.out.println(config.getKeyType());
    }

    public void write2Datafile(LinkedList values, long rowNum)
        throws IOException, BPlusException {
        dataFile.seek(rowNum*config.getRowSize());
        byte[] rowData = new byte[config.getRowSize()];
        String[] types = config.getColumnType();
        Arrays.fill(rowData, (byte)0);
        int pos = 0;
        for (int i = 0; i < types.length; ++i) {
            pos += copy2row(rowData, pos, values.get(i), types[i]);
        }
    }

    public int copy2row(byte[] rowData, int pos, Object value, String type)
        throws BPlusException {
        int ret = 0;
        byte[] tmp;
        switch (type) {
            case "Int":
                ret = Consts.intSize;
                tmp = Integer.toString((int)value).getBytes();
                break;
            case "Long":
                ret = Consts.longSize;
                tmp = Long.toString((long)value).getBytes();
                break;
            case "String":
                ret = Consts.stringSize;
                tmp = value.toString().getBytes();
                if (tmp.length > Consts.stringSize) {
                    throw new BPlusException("String data is too long!");
                }
                break;
            case "Float":
                ret = Consts.floatSize;
                tmp = Float.toString((float)value).getBytes();
                break;
            case "Double":
                ret = Consts.doubleSize;
                tmp = Double.toString((double)value).getBytes();
                break;
            default:
                throw new BPlusException("Unkonwn Type " + type);
        }
        System.arraycopy(tmp, 0, rowData, pos, tmp.length);
        return ret;
    }

    private BPlusTreeNode getNode(long pageIndex)
            throws BPlusException, IOException{
        if (pageIndex < 0) {
//            new BPlusTreeNode(treeFile, pageIndex);
            throw new BPlusException("Underflow pageIndex!");
        } else
        {
            this.allNodes.ensureCapacity((int)pageIndex+1);
            if (allNodes.get((int)pageIndex) == null) {
                allNodes.set((int)pageIndex, new BPlusTreeNode(treeFile, pageIndex, config));
            }
            return allNodes.get((int)pageIndex);
        }
    }

    private void updateNodes(BPlusTreeNode p, long pageIndex) {
        this.allNodes.ensureCapacity((int)pageIndex+1);
        this.allNodes.set((int)pageIndex, p);
    }

    private void splitNode(BPlusTreeNode p)
        throws BPlusException, IOException {
        if (p.isRoot()) {
            long newRootPageIndex = getNewPageIndex();

            BPlusTreeNode newRoot = new BPlusTreeNode(newRootPageIndex, -1, BPlusTreeNodeType.ROOT_NODE, this.config);
            updateNodes(newRoot, newRootPageIndex);

            p.setParent(newRootPageIndex);
            newRoot.addKeyAndPtr(p.getKey(0), p.getPageIndex());

            return;
        }
        BPlusTreeNodeType nodeType;

        if (p.isLeaf()) {
            nodeType = BPlusTreeNodeType.LEAF_NODE;
            p.setNodeType(BPlusTreeNodeType.LEAF_NODE);
        } else
        if (p.isInternal()) {
            nodeType = BPlusTreeNodeType.INTERNAL_NODE;
            p.setNodeType(BPlusTreeNodeType.INTERNAL_NODE);
        } else
        {
            nodeType = BPlusTreeNodeType.INTERNAL_NODE;
        }

        long newPageIndex = getNewPageIndex();

        BPlusTreeNode q = new BPlusTreeNode(newPageIndex, p.getParent(), nodeType, this.config);
        int m = p.getCurrentSize();

        for (int i = m/2; i < m; ++i) {
            Object key = p.getKey(i);
            long ptr = p.getPtr(i);
            q.addKeyAndPtr(key, ptr);
        }
        for (int i = m/2; i < m; ++i) {
            p.removeKeyAndPtr(m/2);
        }

        BPlusTreeNode parent = getNode(p.getPageIndex());
        parent.addKeyAndPtr(q.getKey(0), newPageIndex);
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
}
