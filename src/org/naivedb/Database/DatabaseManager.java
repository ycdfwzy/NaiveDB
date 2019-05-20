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

    private static String baseDir = "data";
    private static String metaPath = "data/db.meta";
    private static HashSet<String> databases = null;
    private static Logger logger = MyLogger.getLogger("database");

    /*
        initiate database system, must call once on session begin, read or create database metainfo
        database meta format: dbCnt|[dbNames]
     */
    public static void initial() throws IOException, NDException {

        // check base Dir
        File dbDir = new File(baseDir);
        if (dbDir.exists() && dbDir.isFile()) throw new NDException("please remove file: \'data\'.");
        if (!dbDir.exists()) {
            dbDir.mkdir();
            logger.info("data directory created");
        }
        
        // check meta file
        File metaFile = new File(metaPath);
        if (!metaFile.exists()) {
            metaFile.createNewFile();
            databases = new HashSet<String>();
            logger.info("meta info file created");
        }
        else {
            // read info
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(metaFile));

            // cnt
            int db_cnt = StreamUtils.readInt(input);
            databases = new HashSet<String>();
            for (int i = 0; i < db_cnt; i++) {
                String name = StreamUtils.readString(input, Consts.databaseNameSize);
                databases.add(name);
            }
            input.close();
            logger.info("meta info loaded");
        }
    }

    /*
     *  close database manager. write metainfo back.
     */
    public static void close() throws IOException, NDException {
        // write meta info to file
        File metaFile = new File(metaPath);
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(metaFile));

        // db cnt
        StreamUtils.writeInt(output, databases.size());

        // [db names]
        for (String name: databases) {
            StreamUtils.writeString(output, name, Consts.databaseNameSize);
        }
        output.close();
        logger.info("database metainfo write successful.");
    }

    // create a database
    public static Database create(String db_name) throws NDException {
        if (db_name.length() >= Consts.databaseNameSize)
            throw new NDException("database name too long.");
        if (databases.contains(db_name)) throw new NDException("database already exists!");
        
        // create dir for this db
        File db = new File(getDatabasePath(db_name));
        if (db.exists()) throw new NDException("database dir exists!");
        db.mkdir();

        databases.add(db_name);
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

    // get a database
    public static Database get(String db_name) throws NDException {
        if (!databases.contains(db_name)) throw new NDException("database not exist.");
        return new Database(db_name);
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

}