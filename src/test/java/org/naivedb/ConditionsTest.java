package org.naivedb;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.naivedb.Statement.Conditions;
import org.naivedb.Statement.Expression;
import org.naivedb.Type.Type;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class ConditionsTest {

    private LinkedList<String> nameList;
    private LinkedList<Type> typeList;
    private LinkedList valueList;

    @Before
    public void setUp() throws Exception {
        nameList = new LinkedList<String>();
        typeList = new LinkedList<Type>();
        valueList = new LinkedList();
        nameList.add("a");
        nameList.add("b");
        nameList.add("c");
        nameList.add("d");
        typeList.add(new Type(Type.SQL_INT));
        typeList.add(new Type(Type.SQL_LONG));
        typeList.add(new Type(Type.SQL_STRING,4));
        typeList.add(new Type(Type.SQL_STRING,6));
        valueList.add(3);
        valueList.add(-1);
        valueList.add("123");
        valueList.add("abc");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void satisfied() throws Exception {
        // a
        Expression expr1 = new Expression(1, "a");
        // 2
        Expression expr2 = new Expression(0, "2");
        // b
        Expression expr3 = new Expression(1, "b");
        // 3
        Expression expr4 = new Expression(0, "3");
        // b*2
        Expression expr5 = new Expression(3, "*", expr2, expr3);
        // b*2%3
        Expression expr6 = new Expression(3, "%", expr5, expr4);
        // -a
        Expression expr7 = new Expression(2, "-", expr1);
        // -a+b*2%3
        Expression expr8 = new Expression(3, "+", expr7, expr6);
        // "123"
        Expression expr9 = new Expression(4, "123");
        // d
        Expression expr10 = new Expression(1, "d");
        // b+3
        Expression expr11 = new Expression(3, "+", expr3, expr4);
        // c
        Expression expr12 = new Expression(1, "c");

        // a > b + 3
        Conditions cond1 = new Conditions(2, "GT", expr1, expr11);
        // c == d
        Conditions cond2 = new Conditions(2, "EQ", expr12, expr10);
        // c <> "123"
        Conditions cond3 = new Conditions(2, "NEQ", expr12, expr9);
        // c == d || c <> "123"
        Conditions cond4 = new Conditions(1, cond2, cond3);
        // a > b + 3 && (c == d || c <> "123")
        Conditions cond5 = new Conditions(0, cond1, cond4);

        Assert.assertTrue(cond1.satisfied(nameList, typeList, valueList));
        Assert.assertFalse(cond2.satisfied(nameList, typeList, valueList));
        Assert.assertFalse(cond3.satisfied(nameList, typeList, valueList));
        Assert.assertFalse(cond4.satisfied(nameList, typeList, valueList));
        Assert.assertFalse(cond5.satisfied(nameList, typeList, valueList));
    }
}