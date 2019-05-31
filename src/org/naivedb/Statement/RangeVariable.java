package org.naivedb.Statement;

import org.naivedb.Table.Table;
import org.naivedb.utils.NDException;
import org.naivedb.Type.*;
import org.w3c.dom.ranges.Range;

import java.util.*;

public class RangeVariable {

    private boolean isNatural;
    private boolean isOuterJoined;
    private boolean isInnerJoined;
    private boolean isLeft;
    private boolean isRight;
    private boolean isFull;
    private Conditions conditions;
    private RangeVariable[] rangeVariables;
    private Table table;
    private LinkedList<Table> tableList;
    private LinkedList<LinkedList<Integer>> rowNumLists;
    private LinkedList<String> ignoredColumns;

    public RangeVariable(Table table, Conditions conditions) {
        isOuterJoined = false;
        isInnerJoined = false;
        isLeft = false;
        isRight = false;
        isFull = false;
        rangeVariables = null;
        this.table = table;
        this.conditions = conditions;
    }

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

    public Table exec() {
        if (rangeVariables == null) {
            return table;
        }



        return null;
    }

    private void combineRows() {
        if (rangeVariables == null) {
            tableList = new LinkedList<Table>();
            tableList.add(table);
            rowNumLists = new LinkedList<LinkedList<Integer>>();
            int rowNum = table.getRowNum();
            for (int i = 0; i < rowNum; ++i) {
                LinkedList<Integer> rowNumList = new LinkedList<Integer>();
                rowNumList.add(i);
                rowNumLists.add(rowNumList);
            }
            return;
        }

        RangeVariable leftRange = rangeVariables[0];
        leftRange.combineRows();
        LinkedList<Table> leftTableList = leftRange.tableList;
        LinkedList<LinkedList<Integer>> leftRowNumLists = leftRange.rowNumLists;

        LinkedList<String> leftColNames = new LinkedList<String>();
        LinkedList<Type> leftColTypes = new LinkedList<Type>();

        for (Table leftTable : leftTableList) {
            ArrayList<String> colNames = leftTable.getColNames();
            ArrayList<Type> colTypes = leftTable.getColTypes();
            for (int i = 0; i < colNames.size(); ++i) {
                leftColNames.add(leftTable.getTableName() + "." + colNames.get(i));
                leftColTypes.add(colTypes.get(i));
            }
        }

        for (int i = 1; i < rangeVariables.length; ++i) {
            RangeVariable rightRange = rangeVariables[i];
            rightRange.combineRows();
            LinkedList<Table> rightTableList = rightRange.tableList;
            LinkedList<LinkedList<Integer>> rightRowNumLists = rightRange.rowNumLists;

            LinkedList<String> rightColNames = new LinkedList<String>();
            LinkedList<Type> rightColTypes = new LinkedList<Type>();

            for (Table rightTable : rightTableList) {
                ArrayList<String> colNames = rightTable.getColNames();
                ArrayList<Type> colTypes = rightTable.getColTypes();
                for (int j = 0; j < colNames.size(); ++i) {
                    rightColNames.add(rightTable.getTableName() + "." + colNames.get(j));
                    rightColTypes.add(colTypes.get(i));
                }
            }

            for (LinkedList<Integer> leftRowNumList : leftRowNumLists) {
                LinkedList leftRow = null;
                for (int j = 0; j < leftRowNumList.size(); ++j) {
                    LinkedList tempRow = leftTableList.get(j).get(leftRowNumList.get(j));
                    if (leftRow == null) {
                        leftRow = tempRow;
                    } else {
                        leftRow.addAll(tempRow);
                    }
                }

                for (LinkedList<Integer> rightRowNumList : rightRowNumLists) {
                    LinkedList rightRow = null;
                    for (int j = 0; j < rightRowNumList.size(); ++j) {
                        LinkedList tempRow = rightTableList.get(j).get(rightRowNumList.get(j));
                        if (rightRow == null) {
                            rightRow = tempRow;
                        } else {
                            rightRow.addAll(tempRow);
                        }
                    }

                    if ()
                }
            }
        }

    }
}