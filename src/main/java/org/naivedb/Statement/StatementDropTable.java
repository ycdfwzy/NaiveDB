package org.naivedb.Statement;

import org.naivedb.Database.Database;
import org.naivedb.utils.NDException;

import java.io.IOException;
import java.util.LinkedList;

public class StatementDropTable {
    private String tableName;

    public StatementDropTable(String tableName) {
        this.tableName = tableName;
    }

    // return 1 if success
    public ExecResult exec(Database db) throws NDException, IOException {
        db.dropTable(tableName);

        LinkedList<String> tableHeader = new LinkedList<>();
        tableHeader.add("Insert_Count");
        ExecResult execResult = new ExecResult(tableHeader);
        LinkedList val = new LinkedList();
        val.add(1);
        execResult.insert(val);
        return execResult;
    }
}
