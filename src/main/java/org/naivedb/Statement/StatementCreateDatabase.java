package org.naivedb.Statement;

import org.naivedb.Database.Database;
import org.naivedb.Database.DatabaseManager;
import org.naivedb.utils.NDException;

import java.io.IOException;
import java.util.LinkedList;

public class StatementCreateDatabase {
    private String dbName;

    public StatementCreateDatabase(String dbName) {
        this.dbName = dbName;
    }

    // return 1 if success
    public ExecResult exec() throws NDException, IOException {
        DatabaseManager.create(dbName);

        LinkedList<String> tableHeader = new LinkedList<>();
        tableHeader.add("Update_Count");
        ExecResult execResult = new ExecResult(tableHeader);
        LinkedList val = new LinkedList();
        val.add(1);
        execResult.insert(val);
        return execResult;
    }
}
