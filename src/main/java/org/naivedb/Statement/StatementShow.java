package org.naivedb.Statement;

import org.naivedb.Database.Database;
import org.naivedb.Database.DatabaseManager;
import org.naivedb.Table.Table;
import org.naivedb.Type.Type;
import org.naivedb.utils.NDException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class StatementShow {
    private String name;
    private String type;

    public StatementShow() {
        this.name = null;
        this.type = null;
    }

    public StatementShow(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public ExecResult exec(Database db) throws NDException, IOException {
        ExecResult execResult;
        ArrayList<String> res;

        if (type == null || type.compareTo("DATABASE") == 0) {
            if (name == null) {
                LinkedList<String> tableHeader = new LinkedList<>();
                tableHeader.add("DATABASES");
                execResult = new ExecResult(tableHeader);
                res = DatabaseManager.getDatabases();
            } else {
                LinkedList<String> tableHeader = new LinkedList<>();
                tableHeader.add("TABLES");
                execResult = new ExecResult(tableHeader);
                res = DatabaseManager.get(name).getTables();
            }
            for (String db_name : res) {
                LinkedList val = new LinkedList();
                val.add(db_name);
                execResult.insert(val);
            }
        } else
        {
            LinkedList<String> tableHeader = new LinkedList<>();
            tableHeader.add("NAME");
            tableHeader.add("TYPE");
            execResult = new ExecResult(tableHeader);
            Table table = db.getTable(name);
            ArrayList<String> colNames = table.getColNames();
            ArrayList<Type> colTypes = table.getColTypes();
            for (int i = 0; i < colNames.size(); ++i) {
                LinkedList val = new LinkedList();
                val.add(colNames.get(i));
                val.add(colTypes.get(i).typeName());
                execResult.insert(val);
            }
        }

        return execResult;
    }
}
