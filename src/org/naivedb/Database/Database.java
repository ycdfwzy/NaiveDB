/* Problem:
 *    Database这边提供的创建表的接口
 */

package org.naivedb.Database;

import org.naivedb.utils.Consts;
import org.naivedb.utils.NumberUtils;
import org.naivedb.utils.NDException;
import org.naivedb.utils.MyLogger;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import org.naivedb.Table.Table;
import org.naivedb.Type.Type;
import javafx.util.Pair;

public class Database {

    private String dbName;
    private String dbPath;
    public Logger logger;

    public Database(String db_name) throws NDException {
        this.dbName = db_name;
        this.dbPath = DatabaseManager.getDatabasePath(db_name);
        this.logger = MyLogger.getLogger(db_name);
    }

    public String getName() { return this.dbName; }
    public String getPath() { return this.dbPath; }

    public Table getTable(String table_name) throws IOException, NDException {
        return new Table(table_name, this.dbPath);
    }

    public Table createTable(String table_name, ArrayList<Pair<String, Type>> cols) 
        throws IOException, NDException{
        return new Table(table_name, this.dbPath, cols);
    }

    public void dropTable(String table_name) throws IOException, NDException {
        Table.drop(table_name, this.dbPath);
    }
}
