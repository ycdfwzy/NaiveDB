import BPlusTree.*;

import utils.NumberUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

public class main {

    public static void main(String[] args)
        throws Exception {

        File file = new File("test.tree");
        if (file.exists() && file.isFile())
            file.delete();
        file = new File("test.data");
        if (file.exists() && file.isFile())
            file.delete();


        LinkedList<String> name = new LinkedList<String>();
        LinkedList<String> type = new LinkedList<String>();
        name.add("id");
        name.add("name");

        type.add("Int");
        type.add("String");

        BPlusTreeConfiguration conf = new BPlusTreeConfiguration(256,"Int", name, type);
        BPlusTree tree = new BPlusTree(conf);
        tree.close();

        tree = new BPlusTree("test.tree", "test.data");
        String[] names = tree.getConfig().getColumnName();
        String[] types = tree.getConfig().getColumnType();
        for (int i = 0; i < names.length; ++i) {
            System.out.printf("%s: %s\n", names[i], types[i]);
        }

        LinkedList values = new LinkedList();
        values.add(0);
        values.add("Mao");
        tree.insert(values);

        values.set(0, 1);
        values.set(1, "Deng");
        tree.insert(values);

        values.set(0, 2);
        values.set(1, "Jiang");
        tree.insert(values);

        values.set(0, 3);
        values.set(1, "Hu");
        tree.insert(values);

        values.set(0, 4);
        values.set(1, "Xi");
        tree.insert(values);

        values.set(0, -1);
        values.set(1, "Pu");
        tree.insert(values);

        LinkedList<LinkedList> res = tree.searchAll();
        for (LinkedList row: res) {
            for (Object value: row) {
                System.out.print(value);
                System.out.printf(" ");
            }
            System.out.println();
        }

        tree.close();
    }
}
