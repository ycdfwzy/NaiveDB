package org.naivedb.Statement;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.LinkedList;
import org.naivedb.Table.Table;
import org.naivedb.utils.NDException;

public class StatementSelect {
    private LinkedList<String> targetList;
    private RangeVariable rv;
    private Conditions cond;

    public StatementSelect(LinkedList<String> targetList,
                           RangeVariable rv,
                           Conditions cond) {
        this.targetList = targetList;
        this.rv = rv;
        this.cond = cond;
    }

    public Result exec() throws NDException {
        Result res = new Result(targetList);
        TempTable tempTable = rv.exec();
        LinkedList<Table> tables = tempTable.getTable();
        LinkedList<String> ignoreColumns = tempTable.getIgnoreColumns();
        ArrayList<String> allColumnNames = new ArrayList<String>();

        int n = temp.getRowCnt();
        for (int i = 0 ; i < n; ++i) {
            LinkedList<Integer> t = tempTable.getRowData(i);
            LinkedList rowData = new LinkedList();
            int k = t.size();
            for (int j = 0; j < k; ++j) {
                Table table = tables.get(j);
                allColumnNames.addAll(table.getColNames());
                rowData.addAll(table.getRowData(t.get(j)));
            }

            LinkedList newRow;
            if (!targetList.isEmpty()) {
                if (targetList.getFirst().compareTo("*") == 0) {
                    for (String ignoreColumn: ignoreColumns) {
                        int idx = allColumnNames.indexOf(ignoreColumn);
                        if (idx >= 0) {
                            rowData.remove(idx);
                        }
                    }
                    newRow = rowData;
                } else
                {
                    newRow = new LinkedList();
                    for (String target: targetList) {
                        int idx = allColumnNames.indexOf(target);
                        if (idx >= 0) {
                            newRow.add(rowData.get(idx));
                        }
                    }
                }
            } else
            {
                newRow = new LinkedList();
            }
            res.addRow(newRow);
        }
        return res;
    }
}
