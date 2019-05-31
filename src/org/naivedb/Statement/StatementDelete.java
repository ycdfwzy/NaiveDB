package org.naivedb.Statement;

import org.naivedb.Table.Table;
import org.naivedb.utils.NDException;

import java.io.IOException;
import java.util.ArrayList;

public class StatementDelete {
    private Table targetTable;
    private Conditions cond;

    public StatementDelete(Table targetTable) {
        this(targetTable, null);
    }

    public StatementDelete(Table targetTable, Conditions cond) {
        this.targetTable = targetTable;
        this.cond = cond;
    }

    /*
        execute delete operation
        params:
            none
        return:
            the number of deleted rows
    */
    public int exec() throws NDException, IOException {
        int succeed = 0;
        ArrayList<Long> rowList = targetTable.search(cond);
        for (long row: rowList) {
            targetTable.delete(row);
            succeed += 1;
        }
        return succeed;
    }
}
