package org.naivedb.Statement;

import org.naivedb.Table.Table;
import org.naivedb.utils.NDException;
import org.naivedb.Type.*;

import java.util.*;

public class RangeVariable {

//    private boolean isOuterJoined;
//    private boolean isInnerJoined;
//    private boolean isLeft;
//    private boolean isRight;
//    private Conditions conditions;
//    private Table table;
//
//    public RangeVariable(Table table) {
//        this.table = table;
//        isOuterJoined = false;
//        isInnerJoined = false;
//        isLeft = false;
//        isRight = false;
//        conditions = null;
//    }
//
//    public void setNatural (RangeVariable leftRange) throws NDException {
//        ArrayList<String> leftNameList = leftRange.getTable().getColNames();
//        ArrayList<String> rightNameList = table.getColNames();
//        HashSet leftNameSet = new HashSet(leftNameList);
//        for (int i = 0; i < rightNameList.size(); ++i) {
//            if (leftNameSet.contains(rightNameList.get(i))) {
//                Expression leftExpr = new Expression(1, leftRange.getTable().getTableName() + "." + rightNameList.get(i));
//                Expression rightExpr = new Expression(1, table.getTableName() + "." + rightNameList.get(i));
//                Conditions conditions = new Conditions(2, "EQ", leftExpr, rightExpr);
//                if (this.conditions == null) {
//                    this.conditions = conditions;
//                } else {
//                    this.conditions = new Conditions(0, this.conditions, conditions);
//                }
//            }
//        }
//    }
//
//    public Table getTable() {
//        return table;
//    }


}