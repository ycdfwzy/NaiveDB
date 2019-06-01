package org.naivedb.Statement;

import org.naivedb.Table.Table;
import org.naivedb.utils.NDException;

import java.io.IOException;
import java.util.LinkedList;

public class StatementUpdate {
    private Table targetTable;
    private LinkedList<String> colList;
    private LinkedList<Expression> exprList;
    private Conditions cond;


    public StatementUpdate(Table targetTable,
                           LinkedList<String> colList,
                           LinkedList<Expression> exprList) {
        this(targetTable, colList, exprList, null);
    }

    public StatementUpdate(Table targetTable,
                           LinkedList<String> colList,
                           LinkedList<Expression> exprList,
                           Conditions cond) {
        this.targetTable = targetTable;
        this.colList = colList;
        this.exprList = exprList;
        this.cond = cond;
    }

    public int exec() throws IOException, NDException {
        LinkedList<Long> toUpdate = targetTable.search(cond);
        int succeed = 0;
        for (long row: toUpdate) {
            targetTable.update(row, colList, exprList);
            succeed += 1;
        }
        return succeed;
    }
}
