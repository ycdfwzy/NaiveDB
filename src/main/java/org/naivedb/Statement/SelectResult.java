package org.naivedb.Statement;

import org.naivedb.Table.Table;
import org.naivedb.utils.NDException;

import java.util.ArrayList;
import java.util.LinkedList;

public class SelectResult {
    private LinkedList<String> colNames;
    private LinkedList<LinkedList> dataList;

    public SelectResult() {
        this.colNames = new LinkedList<>();
        this.dataList = new LinkedList<>();
    }

    public SelectResult(LinkedList<String> colNames) {
        this.colNames = new LinkedList<>(colNames);
        this.dataList = new LinkedList<>();
    }

    public SelectResult(LinkedList<String> colNames, LinkedList<String> ignoreNames) {
        this.colNames = new LinkedList<>(colNames);
        for (String ignoreName: ignoreNames) {
            this.colNames.remove(ignoreName);
        }
        this.dataList = new LinkedList<>();
    }

    public void insert(LinkedList<String> allNames, LinkedList curData, LinkedList<String> ignoreNames) throws NDException {
        LinkedList data = new LinkedList<>();
        int n = allNames.size();
        for (int i = 0; i < n; ++i) {
            if (ignoreNames.indexOf(allNames.get(i)) < 0)
                data.add(curData.get(i));
        }
        dataList.add(data);
    }

    public void insert(LinkedList<String> curNames, LinkedList curData, ArrayList<Table> tableList) throws NDException {
        LinkedList data = new LinkedList();
        for (String colName: this.colNames) {
            Expression tmp = new Expression(1, colName);
            tmp.normalize(tableList);
            String tableCol = tmp.getSymbol();
            int idx = curNames.indexOf(tableCol);
            if (idx < 0) {
                throw new NDException("Unknown column name: '" + colName + "'");
            }
            data.add(curData.get(idx));
        }
        insert(data);
    }

    public void insert(LinkedList data) {
        dataList.add(data);
    }

    public void show() {
        for (String colName: colNames) {
            System.out.printf("%10s ", colName);
        }
        System.out.println();
        for (LinkedList line: dataList) {
            for (Object o: line) {
                if (o == null) {
                    System.out.printf("      null ");
                } else
                    System.out.printf("%10s ", o.toString());
            }
            System.out.println();
        }
    }
}