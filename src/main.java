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

        LinkedList values0 = new LinkedList();
        values0.add(0);
        values0.add("Mao");
        tree.insert(values0);

        LinkedList values1 = new LinkedList();
        values1.add(1);
        values1.add("Deng");
        tree.insert(values1);

        LinkedList values2 = new LinkedList();
        values2.add(2);
        values2.add("Jiang");
        tree.insert(values2);

        LinkedList values3 = new LinkedList();
        values3.add(3);
        values3.add("Hu");
        tree.insert(values3);

        LinkedList values4 = new LinkedList();
        values4.add(4);
        values4.add("Xi");
        tree.insert(values4);

        LinkedList values5 = new LinkedList();
        values5.add(-1);
        values5.add("Pu");
        tree.insert(values5);

        tree.delete(-1);
        tree.delete(2);
        tree.delete(1);
        tree.delete(0);
        tree.delete(3);
        tree.delete(4);

        tree.printTree();

        tree.close();
    }
}
