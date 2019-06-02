package org.naivedb.Statement;

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.naivedb.Database.Database;
import org.naivedb.Table.Table;
import org.naivedb.Table.TempTable;
import org.naivedb.Type.Type;
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

    public SelectResult exec(Database db) throws IOException, NDException {
        SelectResult res;
        Object t = rv.exec(db);
        if (t instanceof Table) {
            Table table = (Table) t;
            LinkedList<String> colNames = new LinkedList<>(table.getColNames());
//            LinkedList<Type> colTypes = new LinkedList<>(table.getColTypes());
            if (!targetList.isEmpty()) {
                if (targetList.getFirst().compareTo("*") == 0)
                    res = new SelectResult(colNames);
                else
                    res = new SelectResult(targetList);
            } else
            {
                res = new SelectResult();
            }
            ArrayList<Long> rowList = table.search(cond);
            for (long row: rowList) {
                LinkedList data = table.getSingleRowData(row);

                if (!targetList.isEmpty()) {
                    if (targetList.getFirst().compareTo("*") == 0) {
                        res.insert(data);
                    } else
                    {
                        res.insert(targetList, data);
                    }
                } else
                {
                    res.insert(new LinkedList());
                }
            }
            return res;
        } else
        {
            TempTable tempTable = (TempTable) t;
            LinkedList<String> colNames = new LinkedList<>(tempTable.getColNames());
//            LinkedList<Type> colTypes = new LinkedList<>(tempTable.getColTypes());
            LinkedList<String> ignoreColumns = new LinkedList<>(rv.getIgnoredColumns());
            if (!targetList.isEmpty()) {
                if (targetList.getFirst().compareTo("*") == 0)
                    res = new SelectResult(colNames, ignoreColumns);
                else
                    res = new SelectResult(targetList);
            } else
            {
                res = new SelectResult();
            }

            ArrayList<Long> rowList = tempTable.search(cond);
            for (long row: rowList) {
                LinkedList data = tempTable.getSingleRowData(row);

                if (!targetList.isEmpty()) {
                    if (targetList.getFirst().compareTo("*") == 0) {
                        res.insert(data, ignoreColumns);
                    } else
                    {
                        res.insert(targetList, data);
                    }
                } else
                {
                    res.insert(new LinkedList());
                }
            }
            return res;
        }
    }
}
