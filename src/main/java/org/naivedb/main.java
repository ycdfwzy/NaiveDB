// import BPlusTree.*;
package org.naivedb;

import org.naivedb.Persistence.PersistenceData;
import org.naivedb.Statement.Conditions;
import org.naivedb.Statement.Expression;
import org.naivedb.utils.NumberUtils;
import org.naivedb.utils.MyLogger;
import org.naivedb.Type.Type;
import java.util.logging.*;
import org.naivedb.Table.TempTable;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import org.naivedb.Database.DatabaseManager;
import javafx.util.Pair;

public class main {

    public static void main(String[] args)
        throws Exception {
        // -a+2*b%3
        Expression expr1 = new Expression(1, "a");
        Expression expr2 = new Expression(0, "2");
        Expression expr3 = new Expression(1, "b");
        Expression expr4 = new Expression(0, "3");
        Expression expr5 = new Expression(3, "*", expr2, expr3);
        Expression expr6 = new Expression(3, "%", expr5, expr4);
        Expression expr7 = new Expression(2, "-", expr1);
        Expression expr8 = new Expression(3, "+", expr7, expr6);
        Expression expr9 = new Expression(4, "123");
        Expression expr10 = new Expression(1, "d");
        Expression expr11 = new Expression(3, "+", expr3, expr4);
        Expression expr12 = new Expression(1, "c");
        // a > b + 3 && (c == d || c <> "123")
        Conditions cond1 = new Conditions(2, "GT", expr1, expr11);
        Conditions cond2 = new Conditions(2, "EQ", expr12, expr10);
        Conditions cond3 = new Conditions(2, "NEQ", expr12, expr9);
        Conditions cond4 = new Conditions(1, cond2, cond3);
        Conditions cond5 = new Conditions(0, cond1, cond4);
        LinkedList<String> nameList = new LinkedList<String>();
        nameList.add("a");
        nameList.add("b");
        nameList.add("c");
        nameList.add("d");
        LinkedList<Type> typeList = new LinkedList<Type>();
        typeList.add(new Type(Type.SQL_INT));
        typeList.add(new Type(Type.SQL_INT));
        typeList.add(new Type(Type.SQL_STRING,4));
        typeList.add(new Type(Type.SQL_STRING,6));
        LinkedList valueList = new LinkedList();
        valueList.add(3);
        valueList.add(-1);
        valueList.add("123");
        valueList.add("abc");
        System.out.println(cond2.satisfied(nameList, typeList, valueList));
        System.out.println(cond5.satisfied(nameList, typeList, valueList));
        System.out.println(expr8.calcValue(nameList, typeList, valueList).getKey());
        System.out.println(expr8.calcValue(nameList, typeList, valueList).getValue().typeName());
        System.out.println(expr9.calcValue(nameList, typeList, valueList).getKey());
        System.out.println(expr9.calcValue(nameList, typeList, valueList).getValue().typeName());
        System.out.println(expr10.calcValue(nameList, typeList, valueList).getKey());
        System.out.println(expr10.calcValue(nameList, typeList, valueList).getValue().typeName());
        
        // ArrayList<Pair<String, Type>> types = new ArrayList<Pair<String, Type>>();
        // types.add(new Pair<String,Type>("name", new Type(Type.SQL_STRING, 10)));
        // types.add(new Pair<String,Type>("age", new Type(Type.SQL_INT)));
        // types.add(new Pair<String,Type>("money", new Type(Type.SQL_DOUBLE)));
        // TempTable tb = new TempTable(types);
        // LinkedList ls1 = new LinkedList<>();
        // ls1.add("naxin");ls1.add(21);ls1.add(200d);
        // LinkedList ls2 = new LinkedList<>();
        // ls2.add("lishuai");ls2.add(21);ls2.add(200d);
        // LinkedList ls3 = new LinkedList<>();
        // ls3.add("zeshen");ls3.add(21);ls3.add(200d);
        // tb.insert(ls1);tb.insert(ls2);tb.insert(ls3);
        // ArrayList<Long> s = tb.getAllRows();
        // System.out.println(s);
        // for (long i: s){
        //     System.out.println(tb.getSingleRowData(i));
        // }
        // tb.close();

//        LinkedList<String> list1 = new LinkedList<>(nameList);
//        for (String str: list1) {
//            System.out.println(str);
//        }
//        nameList.set(1, "abc");
//        for (String str: list1) {
//            System.out.println(str);
//        }
//        for (String str: nameList) {
//            System.out.println(str);
//        }
    }
}
