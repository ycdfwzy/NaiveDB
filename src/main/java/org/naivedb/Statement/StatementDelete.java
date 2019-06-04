package org.naivedb.Statement;

import org.naivedb.Database.Database;
import org.naivedb.Table.Table;
import org.naivedb.utils.NDException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class StatementDelete {
    private String targetTableName;
    private Conditions cond;

    public StatementDelete(String targetTableName) {
        this(targetTableName, null);
    }

    public StatementDelete(String targetTableName, Conditions cond) {
        this.targetTableName = targetTableName;
        this.cond = cond;
    }

    /*
        execute delete operation
        params:
            db: current database
        return:
            the number of deleted rows
    */
    public ExecResult exec(Database db) throws NDException, IOException {
        Table targetTable = db.getTable(this.targetTableName);
        ArrayList<Table> param = new ArrayList<>();
        param.add(targetTable);
        if (cond != null)
            cond.normalize(param);

        LinkedList<String> tableHeader = new LinkedList<>();
        tableHeader.add("Insert_Count");
        ExecResult execResult = new ExecResult(tableHeader);

        int succeed = 0;
        ArrayList<Long> rowList = targetTable.search(cond);
        for (long row: rowList) {
            targetTable.delete(row);
            succeed += 1;
        }
        targetTable.close();

        LinkedList val = new LinkedList();
        val.add(succeed);
        execResult.insert(val);
        return execResult;
    }
}
