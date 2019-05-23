package org.naivedb.Test;

import java.io.*;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import org.naivedb.Database.DatabaseManager;
import org.naivedb.Database.Database;
import org.naivedb.utils.FileUtils;
import org.naivedb.utils.NDException;

/**
 * Test for database and some usage for database
 */
public class DatabaseTest {
    // @BeforeClass
    // public static void beforeClass() {
    //     System.out.println("lala");
    // }

    @AfterClass
    public static void afterClass() throws IOException, NDException {
        FileUtils.deleteAll(new File(DatabaseManager.getBasePath()));
    }

    // @After
    // public void after() {}

    @Before
    public void before() throws IOException, NDException {
        FileUtils.deleteAll(new File(DatabaseManager.getBasePath()));
        DatabaseManager.initial();
    }

    // able to create base dir
    @Test
    public void test_initial_create() throws IOException, NDException {
        ArrayList<String> dbs = DatabaseManager.getDatabases();
        DatabaseManager.close();
        File data = new File(DatabaseManager.getBasePath());
        File meta = new File(DatabaseManager.getMetaPath());
        Assert.assertTrue(data.exists() && data.isDirectory());
        Assert.assertTrue(meta.exists() && meta.isFile());
        Assert.assertEquals(0, dbs.size());
    }

    // able to load dir
    @Test
    public void test_initial_load() throws IOException, NDException {
        DatabaseManager.create("new_db");
        DatabaseManager.close();
        DatabaseManager.initial();
        ArrayList<String> dbs =  DatabaseManager.getDatabases();
        Assert.assertEquals(1, dbs.size());
        Assert.assertEquals("new_db", dbs.get(0));
        DatabaseManager.close();
    }

    // able to create a database
    @Test
    public void test_create_db_succ() throws IOException, NDException {
        DatabaseManager.create("new_db");
        ArrayList<String> dbs = DatabaseManager.getDatabases();
        File db = new File(DatabaseManager.getDatabasePath("new_db"));
        Assert.assertEquals(1, dbs.size());
        Assert.assertEquals("new_db", dbs.get(0));
        DatabaseManager.close();
        Assert.assertTrue(db.exists() && db.isDirectory());
    }

    @Test
    public void test_create_already_exists() throws IOException, NDException {
        DatabaseManager.create("new_db");
        DatabaseManager.close();
        DatabaseManager.initial();
        Assert.assertThrows(NDException.class, () -> {
            DatabaseManager.create("new_db");
        });
        DatabaseManager.close();
    }

    @Test
    public void test_drop_db_succ() throws IOException, NDException {
        File db = new File(DatabaseManager.getDatabasePath("new_db"));

        DatabaseManager.create("new_db");
        DatabaseManager.close();
        Assert.assertTrue(db.exists() && db.isDirectory());

        DatabaseManager.initial();
        DatabaseManager.drop("new_db");
        DatabaseManager.close();
        Assert.assertFalse(db.exists());
    }

    @Test
    public void test_drop_db_fail() throws IOException, NDException {
        Assert.assertThrows(NDException.class, () -> {
            DatabaseManager.drop("new_db");
        });
        DatabaseManager.close();
    }

    @Test
    public void test_get_db_succ() throws IOException, NDException {
        Database db = DatabaseManager.create("new_db");
        Assert.assertEquals("new_db", db.getName());
        db.close();
        DatabaseManager.close();
        
        DatabaseManager.initial();
        Database db2 = DatabaseManager.get("new_db");
        Assert.assertEquals("new_db", db2.getName());
        db2.close();
        DatabaseManager.close();
    }

    @Test
    public void test_get_db_fail() throws IOException, NDException {
        Assert.assertThrows(NDException.class, () -> {
            DatabaseManager.get("new_db");
        });
    }

}