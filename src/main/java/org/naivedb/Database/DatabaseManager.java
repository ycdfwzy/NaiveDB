/* Problem:
 *  如果添加了多客户端支持，manager如何处理删除、添加数据库等事情
 */

package org.naivedb.Database;
import org.naivedb.utils.Consts;
import org.naivedb.utils.StreamUtils;
import org.naivedb.utils.FileUtils;
import org.naivedb.Database.Database;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import org.naivedb.utils.MyLogger;
import org.naivedb.utils.NDException;

public class DatabaseManager {
    /*
     * this class provides a manager for all databases
     * usage: initial() at first
     *        close() at end
     *        create, drop and get is easy to understand
     */

    private static String baseDir = "data";
    private static String metaName = "db.meta";
    private static HashSet<String> databases = null;
    private static Logger logger = MyLogger.getLogger("database");


    /*
        initiate database system, must call once on session begin, read or create database metainfo
        database meta format: dbCnt|[dbNames]
     */
    public static void initial() throws IOException, NDException {
        databases = new HashSet<String>();

        // check base Dir
        File dbDir = new File(baseDir);
        File meta = new File(baseDir + "/" + metaName);
        if (dbDir.exists() && dbDir.isDirectory() && meta.exists() && meta.isFile()) {
            // load dbs
            loadMeta(meta);
        }
        else if (!dbDir.exists() && !meta.exists()) {
            // frist time: create
            dbDir.mkdir();
            logger.info("data directory created");
            meta.createNewFile();
            logger.info("meta info file created");
        }
        else throw new NDException("database base dir or meta already exists");
    }

    /*
     *  close database manager. write metainfo back.
     */
    public static void close() throws IOException, NDException {
        File meta = new File(baseDir + "/" + metaName);
        writeMeta(meta);
    }

    // create a database
    public static Database create(String db_name) throws IOException, NDException {
        // check name and duplicate
        if (db_name.length() >= Consts.databaseNameSize)
            throw new NDException("database name too long.");
        if (databases.contains(db_name)) throw new NDException("database already exists!");
        
        Database db = new Database(db_name);
        databases.add(db_name);
        return db;
    }

    // get a database
    public static Database get(String db_name) throws IOException, NDException {
        if (!databases.contains(db_name)) throw new NDException("database not exist.");
        return new Database(db_name);
    }

    // drop a database
    public static void drop(String db_name) throws IOException, NDException {
        if (!databases.contains(db_name)) throw new NDException("database does not exist!");

        File db = new File(getDatabasePath(db_name));
        if (!db.exists()) throw new NDException("database dir does not exists!");
        FileUtils.deleteAll(db);

        databases.remove(db_name);
    }

    public static ArrayList<String> getDatabases() {
        ArrayList<String> dbs = new ArrayList<String>();
        for (String name: databases) {
            dbs.add(name);
        }
        return dbs;
    }

    public static String getDatabasePath(String db_name) {
        return baseDir + "/" + db_name;
    }

    public static String getBasePath() {
        return baseDir;
    }

    public static String getMetaPath() {
        return baseDir + "/" + metaName;
    }

// --------------------------------- private method -----------------------------------
    private static void loadMeta(File meta) throws IOException, NDException{
        // read info
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(meta));

        // cnt
        int db_cnt = StreamUtils.readInt(input);
        for (int i = 0; i < db_cnt; i++) {
            String name = StreamUtils.readString(input, Consts.databaseNameSize);
            databases.add(name);
        }
        input.close();
        logger.info("meta info loaded");
    }

    private static void writeMeta(File meta) throws IOException, NDException{
        // write meta info to file
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(meta));

        // db cnt
        StreamUtils.writeInt(output, databases.size());

        // [db names]
        for (String name: databases) {
            StreamUtils.writeString(output, name, Consts.databaseNameSize);
        }
        output.close();
        logger.info("database metainfo write successful.");
    }

}
