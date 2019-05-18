/* Problem:
 *  如果添加了多客户端支持，manager如何处理删除、添加数据库等事情
 */

package Database;
import utils.Consts;
import utils.NumberUtils;
import Database.Database;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import utils.MyLogger;

public class DatabaseManager {

    private static String baseDir = "data";
    private static String metaPath = "data/db.meta";
    private static HashSet<String> databases = null;

    /*
        initiate database system, must call once on session begin, read or create database metainfo
        database meta format: dbCnt|[dbNames]
     */
    public static void initial() throws IOException, Exception {
        Logger log = MyLogger.getLogger("database");

        // check base Dir
        File dbDir = new File(baseDir);
        if (dbDir.exists() && dbDir.isFile()) throw new Exception("please remove file: \'data\'.");
        if (!dbDir.exists()) {
            dbDir.mkdir();
            log.info("data directory created");
        }
        
        // check meta file
        File metaFile = new File(metaPath);
        if (!metaFile.exists()) {
            metaFile.createNewFile();
            databases = new HashSet<String>();
            log.info("meta info file created");
        }
        else {
            // read info
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(metaFile));

            // cnt
            int db_cnt = NumberUtils.readInt(input);
            databases = new HashSet<String>();
            for (int i = 0; i < db_cnt; i++) {
                String name = NumberUtils.readString(input, Consts.databaseNameSize);
                databases.add(name);
            }
            input.close();
            log.info("meta info loaded");
        }
    }

    /*
     *  close database manager. write metainfo back.
     */
    public static void close() throws IOException, Exception {
        // write meta info to file
        File metaFile = new File(metaPath);
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(metaFile));

        // db cnt
        NumberUtils.writeInt(output, databases.size());

        // [db names]
        for (String name: databases) {
            NumberUtils.writeString(output, name, Consts.databaseNameSize);
        }
        output.close();
        MyLogger.getLogger("database").info("database metainfo write successful.");
    }

    // create a database
    public static Database create(String db_name) throws Exception {
        if (db_name.length() >= Consts.databaseNameSize)
            throw new Exception("database name too long.");
        if (databases.contains(db_name)) throw new Exception("database already exists!");
        
        // create dir for this db
        File db = new File(getDatabasePath(db_name));
        if (db.exists()) throw new Exception("database dir exists!");
        db.mkdir();

        databases.add(db_name);
        return new Database(db_name);
    }

    // drop a database
    public static void drop(String db_name) throws IOException, Exception {
        if (!databases.contains(db_name)) throw new Exception("database does not exist!");

        File db = new File(getDatabasePath(db_name));
        if (!db.exists()) throw new Exception("database dir does not exists!");
        db.delete();

        databases.remove(db_name);
    }

    // get a database
    public static Database get(String db_name) throws Exception {
        if (!databases.contains(db_name)) throw new Exception("database not exist.");
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