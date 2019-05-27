// import BPlusTree.*;
package org.naivedb;

import org.naivedb.Persistence.PersistenceData;
import org.naivedb.Statement.Conditions;
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
        // a <= 3 && (b < a || c == d)
        Conditions cond1 = new Conditions(3, "a", "3", "NGT");
        Conditions cond2 = new Conditions(2, "b", "a", "LT");
        Conditions cond3 = new Conditions(2, "c", "d", "EQ");
        Conditions cond4 = new Conditions(1, cond2, cond3);
        Conditions cond5 = new Conditions(0, cond1, cond4);
        LinkedList<String> nameList = new LinkedList<String>();
        nameList.add("a");
        nameList.add("b");
        nameList.add("c");
        nameList.add("d");
        LinkedList<Type> typeList = new LinkedList<Type>();
        typeList.add(new Type(2));
        typeList.add(new Type(3));
        typeList.add(new Type(5,4));
        typeList.add(new Type(5,6));
        LinkedList valueList = new LinkedList();
        valueList.add((long)3);
        valueList.add((float)3.2);
        valueList.add("abc");
        valueList.add("abc");
        System.out.println(cond2.satisfied(nameList, typeList, valueList));
        System.out.println(cond5.satisfied(nameList, typeList, valueList));
    }
}
