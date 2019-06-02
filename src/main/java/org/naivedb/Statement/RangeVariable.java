package org.naivedb.Statement;

import javafx.util.Pair;
import org.naivedb.Database.Database;
import org.naivedb.Table.*;
import org.naivedb.utils.NDException;
import org.naivedb.Type.*;

import java.io.IOException;
import java.util.*;

public class RangeVariable {

    private boolean isProduct;
    private boolean isNatural;
    private boolean isOuterJoined;
    private boolean isInnerJoined;
    private boolean isLeft;
    private boolean isRight;
    private boolean isFull;
    private Conditions conditions;
    private RangeVariable[] rangeVariables;
    private Table table;
    private String tableName;
    private ArrayList<Table> tableList;
    private ArrayList<ArrayList<Long>> rowNumLists;
    private ArrayList<String> ignoredColumns;

    // todo: table -> tableName
    public RangeVariable(String tableName, Conditions conditions) {
        isProduct = false;
        isOuterJoined = false;
        isInnerJoined = false;
        isLeft = false;
        isRight = false;
        isFull = false;
        rangeVariables = null;
        tableList = null;
        rowNumLists = null;
        ignoredColumns = null;
        table = null;
        this.tableName = tableName;
        this.conditions = conditions;
    }

    public void setProduct(boolean product) { isProduct = product; }

    public boolean isProduct() { return isProduct; }

    public void setNatural(boolean natural) { isNatural = natural; }

    public boolean isNatural() { return isNatural;}

    public void setOuterJoined(boolean outerJoined) { isOuterJoined = outerJoined; }

    public boolean isOuterJoined() { return isOuterJoined; }

    public void setInnerJoined(boolean innerJoined) { isInnerJoined = innerJoined; }

    public boolean isInnerJoined() { return isInnerJoined; }

    public void setLeft(boolean left) { isLeft = left; }

    public boolean isLeft() { return isLeft; }

    public void setRight(boolean right) { isRight = right; }

    public boolean isRight() { return isRight; }

    public void setFull(boolean full) { isFull = full; }

    public boolean isFull() { return isFull; }

    public void setConditions(Conditions conditions) { this.conditions = conditions; }

    public Conditions getConditions() { return conditions; }

    public void setRowNumLists(ArrayList<ArrayList<Long>> rowNumLists) { this.rowNumLists = rowNumLists; }

    public ArrayList<ArrayList<Long>> getRowNumLists() { return rowNumLists; }

    public ArrayList<String> getIgnoredColumns() { return ignoredColumns; }

    public ArrayList<Table> getTableList() {
        return tableList;
    }

    // todo: exec(Database db)
    // db.getTable(tableName)
    public TempTable exec(Database db) throws NDException, IOException {
        //todo: 交给泽神判断是否是join
//        if (rangeVariables == null) {
////            return table;
//
//        }
        if (rangeVariables == null) {
            throw new NDException("range variable is null");
        }

        combineRows(db);

        ArrayList<String> totalColNames = new ArrayList<>();
        ArrayList<Type> totalColType = new ArrayList<>();

        getTotalColNamesAndTypes(totalColNames, totalColType, tableList);

        ArrayList<Pair<String, Type>> cols = new ArrayList<>();
        for (int i = 0; i < totalColNames.size(); ++i) {
            String colName = totalColNames.get(i);
            Type colType = totalColType.get(i);
            cols.add(new Pair<>(colName, colType));
        }

        TempTable tempTable = new TempTable(cols);

        for (ArrayList<Long> rowNumList : rowNumLists) {

            LinkedList value = new LinkedList(getRowValue(tableList, rowNumList));
            tempTable.insert(value);
        }

        return tempTable;
    }

    //todo:ignoredColumns
    private void combineRows(Database db) throws NDException, IOException {

        if (rangeVariables == null) {
            table = db.getTable(tableName);
            ArrayList<Long> rowNumList = table.getAllRows();
            tableList = new ArrayList<>();
            tableList.add(table);

            rowNumLists = new ArrayList<>();
            for (long rowNum : rowNumList) {
                ArrayList<Long> newNumList = new ArrayList<>();
                newNumList.add(rowNum);
                rowNumLists.add(newNumList);
            }
            return;
        }

        RangeVariable leftRange = rangeVariables[0];
        leftRange.combineRows(db);
        ArrayList<Table> leftTableList = leftRange.getTableList();
        ArrayList<ArrayList<Long>> leftRowNumLists = leftRange.getRowNumLists();

        ArrayList<String> totalColNames = new ArrayList<>();
        ArrayList<Type> totalColTypes = new ArrayList<>();

        getTotalColNamesAndTypes(totalColNames, totalColTypes, leftTableList);

        for (int i = 1; i < rangeVariables.length; ++i) {
            RangeVariable rightRange = rangeVariables[i];
            rightRange.combineRows(db);

            ArrayList<Table> rightTableList = rightRange.getTableList();

            if (rightRange.isNatural()) {
                processNatural(totalColNames, rightRange);
            }

            getTotalColNamesAndTypes(totalColNames, totalColTypes, rightTableList);

            if (rightRange.isProduct()) {
                product(leftRowNumLists, leftTableList, rightRange);
            }
            else if (rightRange.isOuterJoined()) {
                if (rightRange.isLeft()) {
                    leftOuterJoin(leftRowNumLists, leftTableList, rightRange, totalColNames, totalColTypes);
                }
                else if (rightRange.isRight()) {
                    rightOuterJoin(leftRowNumLists, leftTableList, rightRange, totalColNames, totalColTypes);
                }
                else {
                    fullOuterJoin(leftRowNumLists, leftTableList, rightRange, totalColNames, totalColTypes);
                }
            }
            else {
                innerJoin(leftRowNumLists, leftTableList, rightRange, totalColNames, totalColTypes);
            }
        }

        rowNumLists = leftRowNumLists;
        tableList = leftTableList;
    }

    private void getTotalColNamesAndTypes(ArrayList<String> totalColNames,
                                          ArrayList<Type> totalColTypes,
                                          ArrayList<Table> tableList) {
        for (Table table : tableList) {
            ArrayList<String> colNames = table.getColNames();
            ArrayList<Type> colTypes = table.getColTypes();
            for (int i = 0; i < colNames.size(); ++i) {
                totalColNames.add(table.getTableName() + "." + colNames.get(i));
                totalColTypes.add(colTypes.get(i));
            }
        }
    }

    private ArrayList getRowValue(ArrayList<Table> tableList,
                                  ArrayList<Long> rowNumList)
            throws NDException, IOException {
        ArrayList rowValue = new ArrayList();
        for (int i = 0; i < tableList.size(); ++i) {
            long rowNum = rowNumList.get(i);
            Table table = tableList.get(i);
            if (rowNum == -1) {
                for (int j = 0; j < table.getColNames().size(); ++j) {
                    rowValue.add(null);
                }
            } else {
                rowValue.addAll(table.getSingleRowData(rowNum));
            }
        }
        return rowValue;
    }

    private void product(ArrayList<ArrayList<Long>> leftRowNumLists,
                         ArrayList<Table> leftTableList,
                         RangeVariable rightRange)
            throws NDException, IOException {
        ArrayList<ArrayList<Long>> rightRowNumLists = rightRange.getRowNumLists();
        ArrayList<Table> rightTableList = rightRange.getTableList();

        if (leftRowNumLists.size() == 0 || rightRowNumLists.size() == 0) {
            leftRowNumLists.clear();
            return;
        }

        ArrayList<ArrayList<Long>> tempRowNumLists = new ArrayList<>();

        leftTableList.addAll(rightTableList);

        for (ArrayList<Long> leftRowNumList : leftRowNumLists) {
            for (ArrayList<Long> rightRowNumList : rightRowNumLists) {
                ArrayList<Long> tempRowNumList = new ArrayList<>(leftRowNumList);
                tempRowNumList.addAll(rightRowNumList);
                tempRowNumLists.add(tempRowNumList);
            }
        }

        leftRowNumLists.clear();
        leftRowNumLists.addAll(tempRowNumLists);
    }

    private void leftOuterJoin(ArrayList<ArrayList<Long>> leftRowNumLists,
                               ArrayList<Table> leftTableList,
                               RangeVariable rightRange,
                               ArrayList<String> totalColNames,
                               ArrayList<Type> totalColTypes)
            throws NDException, IOException {
        if (leftRowNumLists.size() == 0) {
            leftRowNumLists.clear();
            return;
        }

        ArrayList<ArrayList<Long>> tempRowNumLists = new ArrayList<>();

        ArrayList<ArrayList<Long>> rightRowNumLists = rightRange.getRowNumLists();
        ArrayList<Table> rightTableList = rightRange.getTableList();

        //TODO: change the type of list
        LinkedList<String> linkedTotalColNames = new LinkedList<>(totalColNames);
        LinkedList<Type> linkedTotalColTypes = new LinkedList<>(totalColTypes);

        for (ArrayList<Long> leftRowNumList : leftRowNumLists) {
            ArrayList leftRowValue = getRowValue(leftTableList, leftRowNumList);
            boolean inserted = false;

            for (ArrayList<Long> rightRowNumList : rightRowNumLists) {
                ArrayList rightRowValue = getRowValue(rightTableList, rightRowNumList);
                LinkedList totalValues = new LinkedList(leftRowValue);
                totalValues.addAll(rightRowValue);
                if (rightRange.getConditions().satisfied(linkedTotalColNames, linkedTotalColTypes, totalValues)) {
                    ArrayList<Long> tempRowNumList = new ArrayList<>(leftRowNumList);
                    tempRowNumList.addAll(rightRowNumList);
                    tempRowNumLists.add(tempRowNumList);
                    inserted = true;
                }
            }

            if (!inserted) {
                ArrayList<Long> tempRowNumList = new ArrayList<>(leftRowNumList);
                for (int i = 0; i < rightTableList.size(); ++i) {
                    tempRowNumList.add((long)-1);
                }
                tempRowNumLists.add(tempRowNumList);
            }
        }

        leftTableList.addAll(rightTableList);

        leftRowNumLists.clear();
        leftRowNumLists.addAll(tempRowNumLists);
    }

    private void rightOuterJoin(ArrayList<ArrayList<Long>> leftRowNumLists,
                               ArrayList<Table> leftTableList,
                               RangeVariable rightRange,
                               ArrayList<String> totalColNames,
                               ArrayList<Type> totalColTypes)
            throws NDException, IOException {
        ArrayList<ArrayList<Long>> rightRowNumLists = rightRange.getRowNumLists();
        ArrayList<Table> rightTableList = rightRange.getTableList();

        if (rightRowNumLists.size() == 0) {
            leftRowNumLists.clear();
            return;
        }

        ArrayList<ArrayList<Long>> tempRowNumLists = new ArrayList<>();

        //TODO: change the type of list
        LinkedList<String> linkedTotalColNames = new LinkedList<>(totalColNames);
        LinkedList<Type> linkedTotalColTypes = new LinkedList<>(totalColTypes);

        for (ArrayList<Long> rightRowNumList : rightRowNumLists) {
            ArrayList rightRowValue = getRowValue(rightTableList, rightRowNumList);
            boolean inserted = false;

            for (ArrayList<Long> leftRowNumList : leftRowNumLists) {
                ArrayList leftRowValue = getRowValue(leftTableList, leftRowNumList);
                LinkedList totalValues = new LinkedList(leftRowValue);
                totalValues.addAll(rightRowValue);
                if (rightRange.getConditions().satisfied(linkedTotalColNames, linkedTotalColTypes, totalValues)) {
                    ArrayList<Long> tempRowNumList = new ArrayList<>(leftRowNumList);
                    tempRowNumList.addAll(rightRowNumList);
                    tempRowNumLists.add(tempRowNumList);
                    inserted = true;
                }
            }

            if (!inserted) {
                ArrayList<Long> tempRowNumList = new ArrayList<>();
                for (int i = 0; i < leftTableList.size(); ++i) {
                    tempRowNumList.add((long)-1);
                }
                tempRowNumList.addAll(rightRowNumList);
                tempRowNumLists.add(tempRowNumList);
            }
        }

        leftTableList.addAll(rightTableList);

        leftRowNumLists.clear();
        leftRowNumLists.addAll(tempRowNumLists);
    }


    private void fullOuterJoin(ArrayList<ArrayList<Long>> leftRowNumLists,
                                ArrayList<Table> leftTableList,
                                RangeVariable rightRange,
                                ArrayList<String> totalColNames,
                                ArrayList<Type> totalColTypes)
            throws NDException, IOException {
        ArrayList<ArrayList<Long>> rightRowNumLists = rightRange.getRowNumLists();
        ArrayList<Table> rightTableList = rightRange.getTableList();

        if (leftRowNumLists.size() == 0 && rightRowNumLists.size() == 0) {
            leftRowNumLists.clear();
            return;
        }

        ArrayList<ArrayList<Long>> tempRowNumLists = new ArrayList<>();

        LinkedList<String> linkedTotalColNames = new LinkedList<>(totalColNames);
        LinkedList<Type> linkedTotalColTypes = new LinkedList<>(totalColTypes);

        HashSet<ArrayList<Long>> rightRowNumSet = new HashSet<>();

        for (ArrayList<Long> leftRowNumList : leftRowNumLists) {
            ArrayList leftRowValue = getRowValue(leftTableList, leftRowNumList);
            boolean inserted = false;

            for (ArrayList<Long> rightRowNumList : rightRowNumLists) {
                ArrayList rightRowValue = getRowValue(rightTableList, rightRowNumList);
                LinkedList totalValues = new LinkedList(leftRowValue);
                totalValues.addAll(rightRowValue);
                if (rightRange.getConditions().satisfied(linkedTotalColNames, linkedTotalColTypes, totalValues)) {
                    ArrayList<Long> tempRowNumList = new ArrayList<>(leftRowNumList);
                    tempRowNumList.addAll(rightRowNumList);
                    tempRowNumLists.add(tempRowNumList);
                    inserted = true;
                    rightRowNumSet.add(rightRowNumList);
                }
            }

            if (!inserted) {
                ArrayList<Long> tempRowNumList = new ArrayList<>(leftRowNumList);
                for (int i = 0; i < rightTableList.size(); ++i) {
                    tempRowNumList.add((long)-1);
                }
                tempRowNumLists.add(tempRowNumList);
            }
        }

        for (ArrayList<Long> rightRowNumList : rightRowNumLists) {
            if (!rightRowNumSet.contains(rightRowNumList)) {
                ArrayList<Long> tempRowNumList = new ArrayList<>();
                for (int i = 0; i < leftTableList.size(); ++i) {
                    tempRowNumList.add((long)-1);
                }
                tempRowNumList.addAll(rightRowNumList);
                tempRowNumLists.add(tempRowNumList);
            }
        }

        leftTableList.addAll(rightTableList);

        leftRowNumLists.clear();
        leftRowNumLists.addAll(tempRowNumLists);
    }
    private void innerJoin(ArrayList<ArrayList<Long>> leftRowNumLists,
                                ArrayList<Table> leftTableList,
                                RangeVariable rightRange,
                                ArrayList<String> totalColNames,
                                ArrayList<Type> totalColTypes)
            throws NDException, IOException {
        ArrayList<ArrayList<Long>> rightRowNumLists = rightRange.getRowNumLists();
        ArrayList<Table> rightTableList = rightRange.getTableList();

        if (leftRowNumLists.size() == 0) {
            leftRowNumLists.clear();
            return;
        }

        ArrayList<ArrayList<Long>> tempRowNumLists = new ArrayList<>();

        //TODO: change the type of list
        LinkedList<String> newTotalColNames = new LinkedList<>(totalColNames);
        LinkedList<Type> newTotalColTypes = new LinkedList<>(totalColTypes);

        for (ArrayList<Long> leftRowNumList : leftRowNumLists) {
            ArrayList leftRowValue = getRowValue(leftTableList, leftRowNumList);

            for (ArrayList<Long> rightRowNumList : rightRowNumLists) {
                ArrayList rightRowValue = getRowValue(rightTableList, rightRowNumList);
                LinkedList totalValues = new LinkedList(leftRowValue);
                totalValues.addAll(rightRowValue);
                if (rightRange.getConditions().satisfied(newTotalColNames, newTotalColTypes, totalValues)) {
                    ArrayList<Long> tempRowNumList = new ArrayList<>(leftRowNumList);
                    tempRowNumList.addAll(rightRowNumList);
                    tempRowNumLists.add(tempRowNumList);
                }
            }
        }

        leftTableList.addAll(rightTableList);

        leftRowNumLists.clear();
        leftRowNumLists.addAll(tempRowNumLists);
    }

    private void processNatural(ArrayList<String> leftColNames, RangeVariable rightRange) throws NDException {
        ArrayList<Table> rightTableList = rightRange.getTableList();
        ArrayList<String> rightColNames = new ArrayList<>();
        for (Table rightTable : rightTableList) {
            ArrayList<String> colNames = rightTable.getColNames();
            rightColNames.addAll(colNames);
        }
        leftColNames = new ArrayList<>(leftColNames);
        leftColNames.removeAll(ignoredColumns);
        rightColNames.removeAll(rightRange.getIgnoredColumns());

        checkDuplicateColName(leftColNames);
        checkDuplicateColName(rightColNames);

        Conditions naturalCond = null;

        for (String leftColName : leftColNames) {
            String exactLeftColName = getExactColName(leftColName);
            for (String rightColName : rightColNames) {
                String exactRightColName = getExactColName(rightColName);
                if (exactLeftColName.equals(exactRightColName)) {
                    Expression leftExpr = new Expression(1, leftColName);
                    Expression rightExpr = new Expression(1, rightColName);
                    Conditions cond = new Conditions(2, "EQ", leftExpr, rightExpr);
                    if (naturalCond == null) {
                        naturalCond = cond;
                    } else {
                        naturalCond = new Conditions(0, naturalCond, cond);
                    }
                    ignoredColumns.add(rightColName);
                }
            }
        }
        if (naturalCond == null) {
            rightRange.setProduct(true);
            rightRange.setOuterJoined(false);
            rightRange.setInnerJoined(false);
        }
        else {
            rightRange.setConditions(naturalCond);
        }
    }

    private String getExactColName(String colName) {
        int sepPos = colName.indexOf('.');
        if (sepPos == -1) {
            return colName;
        }
        return colName.substring(sepPos + 1);
    }

    private void checkDuplicateColName(ArrayList<String> colNameList) throws NDException {
        HashSet<String> colNameSet = new HashSet<>();
        for (String colName : colNameList) {
            String exactColName = getExactColName(colName);
            if (colNameSet.contains(exactColName)) {
                throw new NDException("Duplicate column name: " + "\"" + exactColName + "\"" + " in derived table");
            }
            colNameSet.add(exactColName);
        }
    }

}