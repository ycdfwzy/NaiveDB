package org.naivedb.Database;

import org.naivedb.utils.Consts;
import org.naivedb.utils.NumberUtils;
import org.naivedb.utils.StreamUtils;
import org.naivedb.utils.NDException;
import org.naivedb.utils.MyLogger;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import org.naivedb.Table.Table;
import org.naivedb.Type.Type;
import javafx.util.Pair;

public class Database {
    /*
     * this class provides a manager for a database
     * usage: get or create db through dbmanager
     *        close() at end
     *        create, drop and get is easy to understand
     */

    private static String metaName = "dbmeta";

    private String dbName;
    private String dbPath;
    HashSet<String> tables;
    private Logger logger;

    /**
     * initial a db, if not exist then create
     */
    public Database(String db_name) throws IOException, NDException {
        this.dbName = db_name;
        this.dbPath = DatabaseManager.getDatabasePath(db_name);
        this.tables = new HashSet<String>();
        this.logger = MyLogger.getLogger(db_name);

        File db = new File(this.dbPath);
        File dbmeta = new File(this.dbPath + "/" + metaName);
        if (db.exists() && db.isDirectory() && dbmeta.exists() && dbmeta.isFile()) {
            // load a db
            this.loadMeta(dbmeta);
        }
        else if (!db.exists() && !dbmeta.exists()){
            // create a db
            db.mkdir();
            dbmeta.createNewFile();
            this.tables = new HashSet<String>();
            writeMeta(dbmeta);
        }
        else throw new NDException("database dir or meta already exists!");
    }

    /**
     * close a db, write meta info back
     */
    public void close() throws IOException, NDException {
        File dbmeta = new File(this.dbPath + "/" + metaName);
        this.writeMeta(dbmeta);
    }

    // create a table
    public Table createTable(String table_name, ArrayList<Pair<String, Type>> cols) 
        throws IOException, NDException {
        // check name and duplicate
        if (table_name.length() >= Consts.tableNameSize)
            throw new NDException("table name too long.");
        if (this.tables.contains(table_name)) throw new NDException("table already exists!");

        Table tb = new Table(table_name, this.dbPath, cols);
        this.tables.add(table_name);
        return tb;
    }

    // get a table
    public Table getTable(String table_name) throws IOException, NDException {
        if (!this.tables.contains(table_name)) throw new NDException("table does not exist");
        return new Table(table_name, this.dbPath);
    }

    // drop a table
    public void dropTable(String table_name) throws IOException, NDException {
        if (!this.tables.contains(table_name)) throw new NDException("table not exists!");
        File root = new File(this.dbPath);
        File[] files = root.listFiles(new FilenameFilter(){
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith(table_name);
            }
        });
        boolean succ = true;
        for (File file: files)
            succ &= file.delete();
        if (!succ) throw new NDException("delete meta or data file failed");
        this.tables.remove(table_name);
    }

    public ArrayList<String> getTables() throws IOException, NDException {
        ArrayList<String> tables = new ArrayList<String>();
        for (String tb_name: this.tables)
            tables.add(tb_name);
        return tables;
    }

    public String getName() { return this.dbName; }
    public String getPath() { return this.dbPath; }


// ---------------------------- private methods ----------------------------------
    private void loadMeta(File meta) throws IOException, NDException {
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(meta));

        int tb_cnt = StreamUtils.readInt(input);

        for (int i = 0; i < tb_cnt; i++) {
            this.tables.add(StreamUtils.readString(input, Consts.tableNameSize));
        }
        
        input.close();
        logger.fine("table names loaded");
    }

    private void writeMeta(File meta) throws IOException, NDException {
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(meta));

        StreamUtils.writeInt(output, this.tables.size());

        for (String name: this.tables)
            StreamUtils.writeString(output, name, Consts.tableNameSize);

        output.close();
        logger.fine("table names written");
    }
}
