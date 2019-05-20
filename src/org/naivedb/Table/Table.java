package org.naivedb.Table;

import org.naivedb.Type.Type;
import java.io.*;
import java.util.*;
import javafx.util.Pair;
import org.naivedb.utils.NDException;

public class Table {

    protected String tableName;
    protected String fileName;
    // protected BPlusTree index;
    protected File metaFile;
    protected RandomAccessFile dataFile;
    protected ArrayList<Boolean> colNotNull;
    protected int primaryKey;
    protected ArrayList<Type> colTypes;
    protected ArrayList<String> colNames;

    /*
      create a new table object
     */
    public Table(String table_name, String db_dir, 
        ArrayList<Pair<String, Type>> cols) throws NDException {
        this.tableName = table_name;
        this.fileName = db_dir + "/" + this.tableName;
        this.primaryKey = -1;
        if (new File(this.fileName + ".meta").exists() || new File(this.fileName + ".data").exists())
            throw new NDException("table already exists.");
        
        HashSet<String> names = new HashSet<String>();
        for (Pair<String, Type> pair : cols) {
            this.colNames.add(pair.getKey());
            names.add(pair.getKey());
            this.colTypes.add(pair.getValue());
            this.colNotNull.add(false);
        }
        if (cols.size() != names.size()) throw new NDException("input names have duplicate.");
    }

    /*
      read a table from file
     */
    public Table(String table_name, String db_dir)
        throws IOException, NDException{
            this.tableName = table_name;
            this.fileName = db_dir + "/" + this.tableName;
            this.primaryKey = -1;
            if (new File(this.fileName + ".meta").exists() &&
                new File(this.fileName + ".data").exists()){
                fromFile(this.fileName);
            }
            else throw new NDException("file not exist.");
    }

    /*
      close a table, write thing back to storage
    */
    public void close() {

    }

    private void fromFile(String filename) {

    }
    
    private void toFile() {

    }

    public void setPrimary(String col_name) throws NDException{
        for (int i = 0; i < this.colNames.size(); i++) {
            if (col_name == this.colNames.get(i)) {
                this.primaryKey = i;
                break;
            }
        }
        if (this.primaryKey == -1) throw new NDException("no match col names.");
    }

    public void setNotNull(ArrayList<Boolean> not_null) throws NDException{
        // interface not decided
    }

}