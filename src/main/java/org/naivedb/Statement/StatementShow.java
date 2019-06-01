package org.naivedb.Statement;

import org.naivedb.Database.Database;
import org.naivedb.Database.DatabaseManager;
import org.naivedb.utils.NDException;

import java.io.IOException;
import java.util.ArrayList;

public class StatementShow {
    private String dbName;

    public StatementShow() {
        this.dbName = null;
    }

    public StatementShow(String dbName) {
        this.dbName = dbName;
    }

    public ArrayList<String> exec() throws NDException, IOException {
        if (dbName == null)
            return DatabaseManager.getDatabases();
        return DatabaseManager.get(dbName).getTables();
    }
}
