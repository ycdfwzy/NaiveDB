package org.naivedb.Statement;

import org.naivedb.Database.Database;
import org.naivedb.Table.Table;
import org.naivedb.utils.NDException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class StatementUpdate {
    private String targetTableName;
    private LinkedList<String> colList;
    private LinkedList<Expression> exprList;
    private Conditions cond;


    public StatementUpdate(String targetTableName,
                           LinkedList<String> colList,
                           LinkedList<Expression> exprList) {
        this(targetTableName, colList, exprList, null);
    }

    public StatementUpdate(String targetTableName,
                           LinkedList<String> colList,
                           LinkedList<Expression> exprList,
                           Conditions cond) {
        this.targetTableName = targetTableName;
        this.colList = colList;
        this.exprList = exprList;
        this.cond = cond;
    }

    /*
        execute update operation
        params:
            db: current database
        return:
            the number of updated rows
    */
    public int exec(Database db) throws IOException, NDException {
        Table targetTable = db.getTable(this.targetTableName);
        ArrayList<Long> toUpdate = targetTable.search(cond);
        int succeed = 0;
        for (long row: toUpdate) {
            targetTable.update(row, colList, exprList);
            succeed += 1;
        }
        return succeed;
    }
}
