package Table;

import BPlusTree.BPlusTree;
import Table.Type;
import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Table {

    protected String tableName;
    protected String baseDir;
    protected BPlusTree index;
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
        ArrayList<Pair<String, Type>> cols) throws Exception {
        this.tableName = table_name;
        this.baseDir = db_dir;
        this.primaryKey = -1;
        String prefix = this.baseDir + "/" + this.tableName;
        if (new File(prefix + ".meta").exists() || new File(prefix + ".data").exists())
            throw new Exception("table already exists.");
        
        HashSet<String> names = new HashSet<String>();
        for (Pair<String, Type> pair : cols) {
            this.colNames.add(pair.getKey());
            names.add(pair.getKey());
            this.colTypes.add(pair.getValue());
            this.colNotNull.add(false);
        }
        if (cols.size() != names.size()) throw new Exception("input names have duplicate.");
    }

    /*
      read a table from file
     */
    public Table(String table_name, String db_dir)
        throws IOException, Exception{
            this.tableName = table_name;
            this.baseDir = db_dir;
            this.primaryKey = -1;
            String prefix = this.baseDir + "/" + this.tableName;
            if (new File(prefix + ".meta").exists() &&
                new File(prefix + ".data").exists()){
                fromFile(prefix);
            }
            else throw new Exception("file not exist.");
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

    public void setPrimary(String col_name) throws Exception{
        for (int i = 0; i < this.colNames.size(); i++) {
            if (col_name == this.colNames.get(i)) {
                this.primaryKey = i;
                break;
            }
        }
        if (this.primaryKey == -1) throw new Exception("no match col names.");
    }

    public void setNotNull(ArrayList<Boolean> not_null) throws Exception{
        // interface not decided
    }

}