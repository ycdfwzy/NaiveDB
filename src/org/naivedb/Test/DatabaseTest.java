package org.naivedb.Test;

import org.junit.Test;
import org.junit.Assert;
import org.naivedb.Database.DatabaseManager;

public class DatabaseTest {
    // example for test
    @Test
    public void testExample() throws Exception {
        Assert.assertEquals(123, 123);
    }

    // @Test
    // public void testInitial() throws Exception {
    //     DatabaseManager.initial();
    //     Assert.assertEquals(1, 1);
    // }

    @Test
    public void suibianTest() throws Exception {
        DatabaseManager.initial();
        DatabaseManager.create("wangzeyu");
        System.out.println(DatabaseManager.getDatabases());
        // DatabaseManager.drop("wangzeyu");
        DatabaseManager.close();
        Assert.assertEquals(1, 1);
    }

}