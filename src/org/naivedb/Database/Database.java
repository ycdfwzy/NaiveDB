package org.naivedb.Database;
import org.naivedb.utils.Consts;
import org.naivedb.utils.NumberUtils;
import org.naivedb.utils.MyLogger;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import org.naivedb.Table.Table;
import org.naivedb.utils.NDException;

public class Database {

    private String dbName;
    private String dbPath;
    public Logger logger;

    public Database(String db_name) throws NDException {
        this.dbName = db_name;
        this.dbPath = DatabaseManager.getDatabasePath(db_name);
        this.logger = MyLogger.getLogger(db_name);
    }

    public String getName(){ return this.dbName; }

    // public Table getTable(String tb_name) throws NDException{
    //     return new Table("lalaa", null);

    // }

    // public Table createTable(String table_name, ArrayList<Pair<String, Type>> cols)

}