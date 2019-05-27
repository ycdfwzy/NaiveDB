// import BPlusTree.*;
package org.naivedb;

import org.naivedb.Persistence.PersistenceData;
import org.naivedb.Statement.Conditions;
import org.naivedb.Statement.Expression;
import org.naivedb.utils.NumberUtils;
import org.naivedb.utils.MyLogger;
import org.naivedb.Type.Type;
import java.util.logging.*;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import org.naivedb.Database.DatabaseManager;

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
//        Conditions cond1 = new Conditions(3, "a", "3", "NGT");
//        Conditions cond2 = new Conditions(2, "b", "a", "LT");
//        Conditions cond3 = new Conditions(2, "c", "d", "EQ");
//        Conditions cond4 = new Conditions(1, cond2, cond3);
//        Conditions cond5 = new Conditions(0, cond1, cond4);
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
        valueList.add(4);
        valueList.add("abc");
        valueList.add("abc");
//        System.out.println(cond2.satisfied(nameList, typeList, valueList));
//        System.out.println(cond5.satisfied(nameList, typeList, valueList));
        System.out.println(expr8.calcValue(nameList, typeList, valueList).getKey());
        System.out.println(expr8.calcValue(nameList, typeList, valueList).getValue().typeName());
        System.out.println(expr9.calcValue(nameList, typeList, valueList).getKey());
        System.out.println(expr9.calcValue(nameList, typeList, valueList).getValue().typeName());
        System.out.println(expr10.calcValue(nameList, typeList, valueList).getKey());
        System.out.println(expr10.calcValue(nameList, typeList, valueList).getValue().typeName());
    }
}
