package org.naivedb.Table;

import org.naivedb.utils.Consts;
import org.naivedb.utils.NDException;
import org.naivedb.utils.StreamUtils;
import org.naivedb.utils.MyLogger;
import org.naivedb.Type.Type;
import org.naivedb.Persistence.PersistenceData;
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
    // private BPlusTree index;
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
            this.colNotNull.add(false);
        }
        if (cols.size() != names.size()) throw new NDException("input names have duplicate.");

        // create meta info and data
        File metaFile = new File(this.fileName + ".meta");
        File dataFile = new File(this.fileName + ".data");
        metaFile.createNewFile();
        dataFile.createNewFile();

        this.writeMeta(metaFile);
        this.persistence = new PersistenceData(this.fileName + ".data", this.colTypes);
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
                this.persistence = new PersistenceData(this.fileName + ".data", this.colTypes);
            }
            else throw new NDException("data base file not exist.");
    }

    /**
     * close a table, write data back to storage (not meta info)
     */
    public void close() {
        this.persistence.close();
    }

    /*
     * drop a table (a static method)
     * para: 1. table name
     *       2. database dir
     */
    public static void drop(String table_name, String db_dir) 
        throws IOException, NDException{
        String file_name = db_dir + "/" + table_name;
        File meta = new File(file_name + ".meta");
        File data = new File(file_name + ".data");
        if (!meta.exists() || !data.exists())
            throw new NDException("database meta or data file not exist.");
        if (meta.delete() && data.delete()) return;
        else throw new NDException("delete meta or data file failed");
    }

    /**
     * set a primary key
     * input: primary key col name
     */
    public void setPrimary(String col_name) throws NDException{
        if (this.primaryKey != -1) throw new NDException("primary key already exists.");
        for (int i = 0; i < this.colNames.size(); i++) {
            if (col_name == this.colNames.get(i)) {
                this.primaryKey = i;
                break;
            }
        }
        if (this.primaryKey == -1) throw new NDException("no match col names.");
    }
    
    /**
     * set not null
     */
    public void setNotNull(ArrayList<Boolean> not_null) throws NDException{
        // interface not decided
    }

    public ArrayList<String> getColNames() { return this.colNames; }
    public ArrayList<Type> getColTypes() { return this.colTypes; }
    public String getFileName() { return this.fileName; }

// ----------------------------- Private methods ---------------------------------------

    /**
     * meta info format
     *     columnCnt|[columnTypes]|[columnNames]
     * currently no way to alter table meta, so only write once
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
        
        input.close();
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
        output.close();
        logger.info("table " + this.tableName + " meta info write successful.");
    }

}
