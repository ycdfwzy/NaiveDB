package org.naivedb.Statement;

import org.naivedb.Database.Database;
import org.naivedb.Database.DatabaseManager;
import org.naivedb.utils.NDException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class StatementShow {
    private String dbName;

    public StatementShow() {
        this.dbName = null;
    }

    public StatementShow(String dbName) {
        this.dbName = dbName;
    }

    public ExecResult exec() throws NDException, IOException {
        ExecResult execResult;
        ArrayList<String> res;

        if (dbName == null) {
            LinkedList<String> tableHeader = new LinkedList<>();
            tableHeader.add("DATABASES");
            execResult = new ExecResult(tableHeader);
            res = DatabaseManager.getDatabases();
        } else {
            LinkedList<String> tableHeader = new LinkedList<>();
            tableHeader.add("TABLES");
            execResult = new ExecResult(tableHeader);
            res = DatabaseManager.get(dbName).getTables();
        }
        for (String db_name: res) {
            LinkedList val = new LinkedList();
            val.add(db_name);
            execResult.insert(val);
        }

        return execResult;
    }
}
