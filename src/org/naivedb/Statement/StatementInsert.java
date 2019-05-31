package org.naivedb.Statement;

import org.naivedb.Table.Table;
import org.naivedb.utils.NDException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class StatementInsert {
    private LinkedList<LinkedList> valueList;
    private Table targetTable;

    public StatementInsert(Table targetTable, LinkedList<LinkedList> valueList) {
        this.valueList = valueList;
        this.targetTable = targetTable;
    }

    public StatementInsert(Table targetTable, LinkedList<String> attrList, LinkedList<LinkedList> valueList)
            throws NDException {
        this.targetTable = targetTable;
        this.valueList = new LinkedList<LinkedList>();
        ArrayList<String> colNames = targetTable.getColNames();
        for (int i = 0; i < valueList.size(); ++i) {
            LinkedList newCol = new LinkedList();
            for (int j = 0; j < colNames.size(); ++j)
                newCol.add(null);
            this.valueList.add(newCol);
        }

        for (String attr: attrList) {
            int idx = colNames.indexOf(attr);
            if (idx >= 0) {
                for (int i = 0; i < valueList.size(); ++i) {
                    LinkedList values = this.valueList.get(i);
                    values.set(idx, valueList.get(i).get(idx));
                }
            } else
            {
                throw new NDException("Unknown column name '" + attr + "'!");
            }
        }
    }

    /*
        execute insert operation
        params:
            none
        return:
            the number of inserted rows
     */
    public int exec() throws IOException, NDException {
        int succeed = 0;
        for (LinkedList value: this.valueList) {
            targetTable.insert(value);
            succeed += 1;
        }
        return succeed;
    }
}
