package org.naivedb.Statement;

import org.naivedb.Table.Table;
import org.naivedb.utils.NDException;

import java.io.IOException;
import java.util.LinkedList;

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

    public int exec() throws NDException, IOException {
        int succeed = 0;
        LinkedList<Long> rowList = targetTable.search(cond);
        for (long row: rowList) {
            targetTable.delete(row);
            succeed += 1;
        }
        return succeed;
    }
}
