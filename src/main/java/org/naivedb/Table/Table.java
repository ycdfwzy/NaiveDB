package org.naivedb.Table;

import org.naivedb.Statement.Conditions;
import org.naivedb.Statement.Expression;
import org.naivedb.utils.Consts;
import org.naivedb.utils.NDException;
import org.naivedb.utils.StreamUtils;
import org.naivedb.utils.MyLogger;
import org.naivedb.Type.Type;
import org.naivedb.Persistence.PersistenceData;
import org.naivedb.BPlusTree.BPlusTree;
import org.naivedb.BPlusTree.BPlusTreeConfiguration;
import java.io.*;
import java.util.*;
import javafx.util.Pair;
import java.util.logging.*;

/**
 * This class provides the table management
 * usage: create a table -> new Table()
 *        load a table -> new Table()
 *        
 */
public class Table {
    
// ------------------------------- Private Variables ---------------------------------
    private String tableName;
    private String fileName;
    private ArrayList<Type> colTypes;
    private ArrayList<String> colNames;
    private ArrayList<Boolean> colNotNull;
    private int primaryKey;

    private PersistenceData persistence;
    private BPlusTree index;
    private static Logger logger = MyLogger.getLogger("table");


// ------------------------------------ Interfaces ------------------------------------

    /**
     * create a new table object
     * parameter: 1. table name
     *            2. database dir
     *            3. cols definition
     */
    public Table(String table_name, String db_dir,
                 ArrayList<Pair<String, Type>> cols) throws IOException, NDException {

        // check table name correctness
        this.tableName = new String(table_name);
        this.fileName = new String(db_dir + "/" + this.tableName);
        if (table_name.length() >= Consts.tableNameSize)
            throw new NDException("table name too long");
        if (new File(this.fileName + ".meta").exists() || new File(this.fileName + ".data").exists())
            throw new NDException("table already exists.");

        // read input types
        this.primaryKey = -1;
        this.colTypes = new ArrayList<Type>();
        this.colNames = new ArrayList<String>();
        this.colNotNull = new ArrayList<Boolean>();
        HashSet<String> names = new HashSet<String>();
        for (Pair<String, Type> pair : cols) {
            if (pair.getKey().length() >= Consts.columnNameSize)
                throw new NDException("column name too long.");
            this.colNames.add(pair.getKey());
            names.add(pair.getKey());
            this.colTypes.add(pair.getValue());
            // default: every col can be null
            this.colNotNull.add(false);
        }
        if (cols.size() != names.size()) throw new NDException("input names have duplicate.");

        // create meta info and data
        File metaFile = new File(this.fileName + ".meta");
        File dataFile = new File(this.fileName + ".data");
        metaFile.createNewFile();
        dataFile.createNewFile();

        this.persistence = new PersistenceData(this.fileName, this.colTypes, null);
        this.writeMeta(metaFile);
    }

    /**
     * read a table from file
     * parameter: 1. table name
     *            2. database dir
     */
    public Table(String table_name, String db_dir)
        throws IOException, NDException{
            // initiate table varible
            this.tableName = table_name;
            this.fileName = db_dir + "/" + this.tableName;
            this.primaryKey = -1;
            this.colTypes = new ArrayList<Type>();
            this.colNames = new ArrayList<String>();
            this.colNotNull = new ArrayList<Boolean>();

            // load meta and data
            if (new File(this.fileName + ".meta").exists() &&
                new File(this.fileName + ".data").exists()){
                File metaFile = new File(this.fileName + ".meta");
                this.loadMeta(metaFile);
                if (this.primaryKey != -1) {
                    this.index = new BPlusTree(this.fileName);
                }
            }
            else throw new NDException("data base file not exist.");
    }

    /**
     * close a table, write data back to storage (not meta info currently)
     */
    public void close() throws IOException, NDException {
        this.writeMeta(new File(this.fileName + ".meta"));
        this.persistence.close();
        if (this.index != null)
            this.index.close();
    }

    /**
     * set a primary key
     * input: primary key col name
     */
    public void setPrimary(String col_name) throws IOException, NDException {
        if (this.primaryKey != -1) throw new NDException("primary key already exists.");
        this.primaryKey = this.colNames.indexOf(col_name);
        if (this.primaryKey == -1) throw new NDException("no match col names.");

        String keyType = this.colTypes.get(this.primaryKey).typeName();
        int pageSize = 4096;
        while (pageSize < (Consts.Type2Size(keyType) + Consts.pointSize)*3 + Consts.nodeTypeSize + Consts.parentSize*3) {
            pageSize *= 2;
        }
        LinkedList<String> types = new LinkedList<>();
        for (int i = 0; i < this.colTypes.size(); ++i)
            types.add(this.colTypes.get(i).typeName());
        BPlusTreeConfiguration config = new BPlusTreeConfiguration(pageSize, this.fileName, keyType, types);
        this.index = new BPlusTree(config);
        // primary key should be not null!
        this.colNotNull.set(this.primaryKey, true);
    }
    
    /**
     * set not null
     */
    public void setNotNull(ArrayList<Boolean> not_null) throws NDException{
        // interface not decided
        if (not_null.size() != this.colNotNull.size())
            throw new NDException("not null list size not right");
        for (int i = 0; i < this.colNotNull.size(); i++)
            this.colNotNull.set(i, not_null.get(i));
    }

    /**
     * insert a row
     * param: a linked list of given values
     * return: the new row index
     */
    public long insert(LinkedList values) throws IOException, NDException {
        if (values.size() != this.colTypes.size()) throw new NDException("row value number wrong");
        
        // not null and type check
        int n = values.size();
        for (int i = 0; i < n; ++ i) {
            Object val = values.get(i);
            // val is null
            if (val == null) {
                // col can't be null
                if (this.colNotNull.get(i))
                    throw new NDException(this.colNames.get(i) + " can't be null");
                i++;
            } else
            {
                try {
                    values.set(i, Type.convert(val, this.colTypes.get(i)));
                } catch (NDException e) {
                    throw new NDException("row values type check error!");
                }
            }
        }

        // type check pass
        long rowNum = this.persistence.getNewRowIndexWithoutDeletion();
        if (this.index != null)
            this.index.insert(values.getFirst(), rowNum);
        this.persistence.add(values);
        return rowNum;
    }

    /**
     * search
     * param: conditions
     * return: result of rows
     */
    public ArrayList<Long> search(Conditions cond) throws IOException, NDException {
        if (cond == null) return getAllRows();
        if (this.primaryKey != -1) {
            String primary = this.tableName + "." + this.colNames.get(this.primaryKey);
            // index = x
            if (cond.isSymbolEqualSomething(primary)) {
                Object obj = cond.getEqualValue().getKey();
                Object key = Type.convert(obj, this.colTypes.get(this.primaryKey));
                return new ArrayList<>(this.index.search(key));
            } else
            // index ∈ (-∞, x) / (-∞, x]
            if (cond.isLowerBounded(primary)) {
                Pair<Pair<Object, Type>, Boolean> lower = cond.getBoundValue();
                boolean isOpen = lower.getValue();
                Object obj = lower.getKey().getKey();
                Object key = Type.convert(obj, this.colTypes.get(this.primaryKey));
                return new ArrayList<>(this.index.search(key, isOpen ? "GT" : "NLT"));
            } else
            // index ∈ (x, +∞) / [x, +∞)
            if (cond.isUpperBounded(primary)) {
                Pair<Pair<Object, Type>, Boolean> upper = cond.getBoundValue();
                boolean isOpen = upper.getValue();
                Object obj = upper.getKey().getKey();
                Object key = Type.convert(obj, this.colTypes.get(this.primaryKey));
                return new ArrayList<>(this.index.search(key, isOpen ? "LT" : "NGT"));
            } else
            // index ∈ (/[x, y)/]
            if (cond.isRanged(primary)) {
                Pair<Pair<Pair<Object, Type>, Boolean>, Pair<Pair<Object, Type>, Boolean>> range = cond.getRange();
                Pair<Pair<Object, Type>, Boolean> lower = range.getKey();
                Pair<Pair<Object, Type>, Boolean> upper = range.getValue();
                boolean lowerOpen = lower.getValue();
                boolean upperOpen = upper.getValue();
                Object lowerKey = Type.convert(lower.getKey().getKey(), this.colTypes.get(this.primaryKey));
                Object upperKey = Type.convert(upper.getKey().getKey(), this.colTypes.get(this.primaryKey));
                return new ArrayList<>(this.index.search(lowerKey, !lowerOpen, upperKey, !upperOpen));
            }
        }
        // bad implementation
        ArrayList<Long> res = new ArrayList<>();
        ArrayList<Long> allRow = this.persistence.getAllRowNum();
        LinkedList<String> colNames = this.combineTableColumn();
        for (long row: allRow) {
            if (cond.satisfied(colNames,
                                new LinkedList<Type>(this.colTypes),
                                this.persistence.get(row)))
                res.add(row);
        }
        return res;
    }

    /**
     * update
     * param: row number, columns list to be updated, expressions list to be assigned to columns
     * return: no return value
     */
    public void update(long row, LinkedList<String> colList, LinkedList<Expression> exprList)
            throws NDException, IOException {
        boolean changePrimaryKey = false;
        if (this.primaryKey != -1) {
            for (String col : colList)
                if (this.colNames.get(this.primaryKey).compareTo(col) == 0) {
                    changePrimaryKey = true;
                }
        }
        LinkedList oldData = this.persistence.get(row);
        LinkedList newData = new LinkedList(oldData);
        LinkedList<String> nameList = this.combineTableColumn();
        LinkedList<Type> typeList = new LinkedList<Type>(this.colTypes);
        int n = colList.size();
        for (int i = 0; i < n; ++i) {
            Pair<Object, Type> val = exprList.get(i).calcValue(nameList, typeList, oldData);
            int idx = this.colNames.indexOf(colList.get(i));
            Object newVal = Type.convert(val.getKey(), this.colTypes.get(idx));
            newData.set(idx, newVal);
        }
        this.persistence.update(row, newData);
        if (changePrimaryKey) {
            Object oldKey = oldData.get(this.primaryKey);
            this.index.update(oldKey, newData.get(this.primaryKey), row);
        }
    }

    /**
     * delete
     * param: row number
     * return: no return value
     */
    public void delete(long row) throws IOException, NDException {
        LinkedList values = this.persistence.remove(row);
        if (this.primaryKey != -1) {
            this.index.delete(values.get(this.primaryKey));
        }
    }

    /**
     * get all row numbers
     * param: none
     * return: an array list of row numbers
     */
    public ArrayList<Long> getAllRows() {
        return this.persistence.getAllRowNum();
    }

    /**
     * get single row number data
     * param: row number
     * return: an linked list of row data in line "row"
     */
    public LinkedList getSingleRowData(long row) throws NDException, IOException {
        return this.persistence.get(row);
    }

    public ArrayList<String> getColNames() { return this.colNames; }
    public ArrayList<Type> getColTypes() { return this.colTypes; }
    public String getFileName() { return this.fileName; }
    public String getTableName() { return this.tableName; }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public LinkedList<String> combineTableColumn() {
        LinkedList<String> list = new LinkedList<>();
        for (String colName: this.colNames)
            list.add(this.tableName + "." + colName);
        return list;
    }

// ----------------------------- Private methods ---------------------------------------

    /**
     * meta info format
     *     columnCnt|[columnTypes]|[columnNames]|[columnNotNull]|primaryKey
     * currently no way to alter table meta, so only write once
     * columnNotNull use 1 as true and 0 as false
     */
    private void loadMeta(File meta) throws IOException, NDException{
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(meta));

        // col cnt
        int col_cnt = StreamUtils.readInt(input);

        // col types
        for (int i = 0; i < col_cnt; i++)
            this.colTypes.add(new Type(StreamUtils.readString(input, Consts.columnTypeSize)));

        // col names
        for (int i = 0; i < col_cnt; i++)
            this.colNames.add(StreamUtils.readString(input, Consts.columnNameSize));

        // col not null
        for (int i = 0; i < col_cnt; i++)
            this.colNotNull.add(StreamUtils.readInt(input) == 1);

        // primary key
        this.primaryKey = StreamUtils.readInt(input);

        // blankRowCnt
        int blankRowCnt = StreamUtils.readInt(input);

        ArrayList<Long> blankRow = new ArrayList<>();
        blankRow.ensureCapacity(blankRowCnt);
        // [blankRow]
        for (int i = 0; i < blankRowCnt; ++i) {
            blankRow.add(StreamUtils.readLong(input));
        }

        input.close();

        this.persistence = new PersistenceData(this.fileName, this.colTypes, blankRow);
        logger.info("table " + this.tableName + " meta info load successful.");
    }
    
    private void writeMeta(File meta) throws IOException, NDException{
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(meta));

        // col cnt
        StreamUtils.writeInt(output, this.colTypes.size());

        // col types
        for (Type type: this.colTypes)
            StreamUtils.writeString(output, type.typeName(), Consts.columnTypeSize);

        // col names
        for (String name: this.colNames)
            StreamUtils.writeString(output, name, Consts.columnNameSize);

        // col not null
        for (Boolean not_null: this.colNotNull) {
            StreamUtils.writeInt(output, not_null ? 1 : 0);
        }

        // primary key
        StreamUtils.writeInt(output, this.primaryKey);

        // blankRowCnt|[blankRow]
        this.persistence.output(output);

        output.close();
        logger.info("table " + this.tableName + " meta info write successful.");
    }

}
