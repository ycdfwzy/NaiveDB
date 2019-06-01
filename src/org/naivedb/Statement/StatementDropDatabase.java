package org.naivedb.Statement;

import org.naivedb.Database.DatabaseManager;
import org.naivedb.utils.NDException;

import java.io.IOException;

public class StatementDropDatabase {
    private String dbName;

    public StatementDropDatabase(String dbName) {
        this.dbName = dbName;
    }

    // return 1 if success
    public int exec() throws NDException, IOException {
        DatabaseManager.drop(dbName);
        return 1;
    }
}
