package org.naivedb.Test;

import static org.junit.Assert.assertEquals;

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
import org.naivedb.Table.Table;
import org.naivedb.Type.Type;
import org.naivedb.utils.FileUtils;
import org.naivedb.utils.NDException;

import javafx.util.Pair;

/**
 * Test for table and some usage for tables
 */
public class TableTest {
    // @BeforeClass
    // public static void beforeClass() {
    //     System.out.println("lala");
    // }

    @AfterClass
    public static void afterClass() throws IOException, NDException {
        FileUtils.deleteAll(new File(DatabaseManager.getBasePath()));
    }

    @After
    public void after() throws IOException, NDException {
        DatabaseManager.close();
    }

    @Before
    public void before() throws IOException, NDException {
        FileUtils.deleteAll(new File(DatabaseManager.getBasePath()));
        DatabaseManager.initial();
        DatabaseManager.create("new_db");
    }

    @Test
    public void test_create_table_succ() throws IOException, NDException {
        // create meta
        Database db = DatabaseManager.get("new_db");
        ArrayList<Pair<String, Type>> types = new ArrayList<Pair<String, Type>>();
        types.add(new Pair<String,Type>("name", new Type(Type.SQL_STRING, 10)));
        types.add(new Pair<String,Type>("age", new Type(Type.SQL_INT)));
        types.add(new Pair<String,Type>("money", new Type(Type.SQL_DOUBLE)));
        Table tb = db.createTable("person", types);
        Assert.assertTrue(this.table_exists(tb.getFileName()));
        tb.close();

        Table tb2 = db.getTable("person");
        ArrayList<String> col_names = tb2.getColNames();
        ArrayList<Type> col_types = tb2.getColTypes();
        Assert.assertEquals(3, col_names.size());
        Assert.assertEquals(3, col_types.size());
        for (int i = 0; i < 3; i++) {
            Assert.assertEquals(col_names.get(i), types.get(i).getKey());
            Assert.assertEquals(col_types.get(i).typeName(), types.get(i).getValue().typeName());
        }
        tb2.close();
    }

    private boolean table_exists(String table_path) throws IOException, NDException {
        File meta = new File(table_path + ".meta");
        File data = new File(table_path + ".data");
        return (meta.exists() && meta.isFile()) && (data.exists() && data.isFile());
    }
}