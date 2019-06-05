package org.naivedb.Statement;

import org.naivedb.Database.Database;
import org.naivedb.Table.Table;
import org.naivedb.utils.NDException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class StatementInsert {
    private String targetTableName;
    private LinkedList<LinkedList> valueList;
    private LinkedList<String> attrList;
//    private Table targetTable;

    public StatementInsert(String targetTableName,
                           LinkedList<LinkedList> valueList) {
        this.valueList = valueList;
        this.targetTableName = targetTableName;
        this.attrList = null;
    }

    public StatementInsert(String targetTableName,
                           LinkedList<String> attrList,
                           LinkedList<LinkedList> valueList) {
        this.targetTableName = targetTableName;
        this.attrList = attrList;
        this.valueList = valueList;
    }

    /*
        execute insert operation
        params:
            db: current database
        return:
            the number of inserted rows
     */
    public ExecResult exec(Database db) throws IOException, NDException {
        if (db == null) throw new NDException("not using any database");
        Table targetTable = db.getTable(targetTableName);
        if (this.attrList != null) {
            LinkedList<LinkedList> valueList = this.valueList;
            this.valueList = new LinkedList<>();
            ArrayList<String> colNames = targetTable.getColNames();
            for (int i = 0; i < valueList.size(); ++i) {
                LinkedList newCol = new LinkedList();
                for (int j = 0; j < colNames.size(); ++j)
                    newCol.add(null);
                this.valueList.add(newCol);
            }


            for (int j = 0; j < this.attrList.size(); ++j) {
                String attr = this.attrList.get(j);
                int idx = colNames.indexOf(attr);
                if (idx >= 0) {
                    for (int i = 0; i < valueList.size(); ++i) {
                        LinkedList values = this.valueList.get(i);
                        values.set(idx, valueList.get(i).get(j));
                    }
                } else {
                    throw new NDException("Unknown column name '" + attr + "'!");
                }
            }
        }

        LinkedList<String> tableHeader = new LinkedList<>();
        tableHeader.add("Update_Count");
        ExecResult execResult = new ExecResult(tableHeader);
        int succeed = 0;
        for (LinkedList value: this.valueList) {
            targetTable.insert(value);
            succeed += 1;
        }
        targetTable.close();

        LinkedList val = new LinkedList();
        val.add(succeed);
        execResult.insert(val);
        return execResult;
    }
}
