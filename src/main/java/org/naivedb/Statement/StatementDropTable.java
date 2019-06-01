package org.naivedb.Statement;

import org.naivedb.Database.Database;
import org.naivedb.utils.NDException;

import java.io.IOException;

public class StatementDropTable {
    private String tableName;

    public StatementDropTable(String tableName) {
        this.tableName = tableName;
    }

    // return 1 if success
    public int exec(Database db) throws NDException, IOException {
        db.dropTable(tableName);
        return 1;
    }
}
