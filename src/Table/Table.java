package Table;

import BPlusTree.BPlusTree;
import Table.Type;
import java.io.*;
import java.util.*;
import javafx.util.Pair;
import Database.DatabaseManager;

public class Table {

    protected String tableName;
    protected BPlusTree index;
    protected File metaFile;
    protected RandomAccessFile dataFile;
    protected ArrayList<Boolean> colNotNull;
    protected ArrayList<Boolean> isPrimary;
    protected ArrayList<Type> colTypes;
    protected ArrayList<String> colNames;
    // Database database;

    public Table(String table_name, ArrayList<Pair<String, Type>> cols) throws Exception {
        this.tableName = table_name;
        
        HashSet<String> names = new HashSet<String>();
        for (Pair<String, Type> pair : cols) {
            this.colNames.add(pair.getKey());
            names.add(pair.getKey());
            this.colTypes.add(pair.getValue());
            this.colNotNull.add(false);
            this.isPrimary.add(false);
        }
        if (cols.size() != names.size()) throw new Exception("input names have duplicate.");
    }

    public Table(String filename) 
        throws IOException, Exception{
            String prefix = DatabaseManager.getDatabasePath("test" + filename);
            if (new File(prefix+".meta").exists() &&
                new File(prefix+".data").exists()){
                fromFile(filename);
            }
            else throw new Exception("file not exists.");
    }

    private void fromFile(String filename) {

    }
    
    private void toFile() {

    }

    public void setPrimary(String col_name) {

    }

    public void setNotNull(String col_name) {

    }

}