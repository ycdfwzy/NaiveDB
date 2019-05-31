package org.naivedb.Statement;

import org.naivedb.Database.Database;
import org.naivedb.Database.DatabaseManager;
import org.naivedb.utils.NDException;

import java.io.IOException;

public class StatementCreateDatabase {
    private String dbName;

    public StatementCreateDatabase(String dbName) {
        this.dbName = dbName;
    }

    // return 1 if success
    public int exec() throws NDException, IOException {
        DatabaseManager.create(dbName);
        return 1;
    }
}
