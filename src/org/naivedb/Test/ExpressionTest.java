package org.naivedb.Test;

import javafx.util.Pair;
import org.junit.*;
import org.naivedb.Statement.Expression;
import org.naivedb.Type.Type;
import org.naivedb.utils.NDException;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class ExpressionTest {

    private LinkedList<String> nameList;
    private LinkedList<Type> typeList;
    private LinkedList valueList;

    @Before
    public void setUp() throws Exception {
        nameList = new LinkedList<String>();
        nameList.add("a");
        nameList.add("b");
        nameList.add("c");
        nameList.add("d");
        typeList = new LinkedList<Type>();
        typeList.add(new Type(Type.SQL_INT));
        typeList.add(new Type(Type.SQL_LONG));
        typeList.add(new Type(Type.SQL_STRING,4));
        typeList.add(new Type(Type.SQL_STRING,6));
        valueList = new LinkedList();
        valueList.add(3);
        valueList.add(-1);
        valueList.add("123");
        valueList.add("abc");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws Exception {
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
        // d+2
        Expression expr11 = new Expression(3, "+", expr10, expr2);

        Assert.assertEquals(new Pair<>(3, new Type(Type.SQL_INT)), expr1.calcValue(nameList, typeList, valueList));
        Assert.assertEquals(new Pair<>((long)-5, new Type(Type.SQL_LONG)), expr8.calcValue(nameList, typeList, valueList));
        Assert.assertEquals(new Pair<>("123", new Type(Type.SQL_STRING)), expr9.calcValue(nameList, typeList, valueList));
        Assert.assertEquals(new Pair<>("abc", new Type(Type.SQL_STRING, 6)), expr10.calcValue(nameList, typeList, valueList));
        Assert.assertThrows(NDException.class, () -> expr11.calcValue(nameList, typeList, valueList));
    }
}