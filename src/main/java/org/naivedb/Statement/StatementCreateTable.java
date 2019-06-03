package org.naivedb.Statement;

import javafx.util.Pair;
import org.naivedb.Database.Database;
import org.naivedb.Table.Table;
import org.naivedb.Type.Type;
import org.naivedb.utils.NDException;

import java.io.IOException;
import java.util.ArrayList;

public class StatementCreateTable {
    private String tableName;
    private ArrayList<Pair<String, Type>> colList;
    private ArrayList<Boolean> notNull;
    private String primaryKey;

    public StatementCreateTable(String tableName,
                                ArrayList<Pair<String, Type>> colList,
                                ArrayList notNull) {
        this(tableName, colList, notNull, null);
    }

    public StatementCreateTable(String tableName,
                                ArrayList<Pair<String, Type>> colList,
                                ArrayList notNull,
                                String primaryKey) {
        this.tableName = tableName;
        this.colList = colList;
        this.notNull = notNull;
        this.primaryKey = primaryKey;
    }

    // return 1 if success
    public int exec(Database db) throws IOException, NDException {
        Table table = db.createTable(tableName, colList);
        table.setNotNull(notNull);
        if (primaryKey != null)
            table.setPrimary(primaryKey);
        table.close();
        return 1;
    }
}
