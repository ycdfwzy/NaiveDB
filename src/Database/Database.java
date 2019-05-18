package Database;
import utils.Consts;
import utils.NumberUtils;
import utils.MyLogger;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class Database {

    private String dbName;
    private String dbPath;
    public Logger logger;

    // public static void drop()

    public Database(String db_name) throws Exception {
        this.dbName = db_name;
        this.dbPath = DatabaseManager.getDatabasePath(db_name);
        this.logger = MyLogger.getLogger(db_name);
    }

}