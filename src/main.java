// import BPlusTree.*;

import persistence.PersistenceData;
import utils.NumberUtils;
import utils.MyLogger;
import java.util.logging.*;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import Database.DatabaseManager;

public class main {

    public static void main(String[] args)
        throws Exception {

        File file = new File("test.tree");
        if (file.exists() && file.isFile())
            file.delete();
        file = new File("test.data");
        if (file.exists() && file.isFile())
            file.delete();
        file = new File("test.header");
        if (file.exists() && file.isFile())
            file.delete();

        LinkedList<String> type = new LinkedList<String>();

        type.add("Int");
        type.add("String8");

        BPlusTreeConfiguration conf = new BPlusTreeConfiguration(256,"test","Int", type);
        BPlusTree tree = new BPlusTree(conf);
        tree.close();

        tree = new BPlusTree("test");
        int n = tree.getConfig().getColumnSize();
        for (int i = 0; i < n; ++i) {
            System.out.printf("%s\n", tree.getConfig().getColumnType(i));
        }

        PersistenceData data = new PersistenceData("test", type);

        LinkedList values0 = new LinkedList();
        values0.add(0);
        values0.add("Mao");
        tree.insert(values0.getFirst(), data.add(values0));

        LinkedList values1 = new LinkedList();
        values1.add(1);
        values1.add("Deng");
        tree.insert(values1.getFirst(), data.add(values1));

        LinkedList values2 = new LinkedList();
        values2.add(2);
        values2.add("Jiang");
        tree.insert(values2.getFirst(), data.add(values2));

        LinkedList values3 = new LinkedList();
        values3.add(3);
        values3.add("Hu");
        tree.insert(values3.getFirst(), data.add(values3));

        LinkedList values4 = new LinkedList();
        values4.add(4);
        values4.add("Xi");
        tree.insert(values4.getFirst(), data.add(values4));

        LinkedList values5 = new LinkedList();
        values5.add(-1);
        values5.add("Pu");
        tree.insert(values5.getFirst(), data.add(values5));

//        tree.delete(-1);
        data.remove(tree.search(2).getFirst());
        tree.delete(2);
//        tree.delete(1);
//        tree.delete(0);
//        tree.delete(3);
//        tree.delete(4);

//        tree.printTree();

        LinkedList<Long> res = tree.search(1, true, 4, false);
        System.out.println(res.size());
        for (Long k: res) {
            LinkedList list = data.get(k);
            for (Object value: list) {
                System.out.print(value);
                System.out.print(" ");
            }
            System.out.println();
        }

        tree.close();
        data.close();
    }
}
