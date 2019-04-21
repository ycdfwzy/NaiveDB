import BPlusTree.*;

//import utils.NumberUtils;

import java.io.*;
import java.util.LinkedList;

public class main {

    public static void main(String[] args)
        throws IOException, BPlusException{

//        File f = new File("test.txt");
//        if (!f.exists()) {
//            f.createNewFile();
//        }
//        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
//        String db_name = "testDB";
//        String table_name = "test";
//        double col_num = 2.0/3;
//        byte[] bytes = new byte[16];
//        System.arraycopy(db_name.getBytes(), 0, bytes, 0, db_name.getBytes().length);
//        out.write(bytes);
//
//        Arrays.fill(bytes, (byte)0);
//        System.arraycopy(table_name.getBytes(), 0, bytes, 0, table_name.getBytes().length);
//        out.write(bytes);
//
//        Arrays.fill(bytes, (byte)0);
//        System.arraycopy(Double.toString(col_num).getBytes(), 0, bytes, 0, Integer.min(16,Double.toString(col_num).getBytes().length));
//        out.write(bytes);
//        out.flush();
//        out.close();
//
//        BufferedInputStream in = new BufferedInputStream(new FileInputStream("test.txt"));
//
//        while (in.read(bytes, 0, 16) != -1) {
//            String s = new String(bytes);
//            s = s.trim();
//            if (NumberUtils.isInteger(s)){
//                int x = Integer.parseInt(s);
//                System.out.println("isInt: " + x);
//            } else
//            if (NumberUtils.isFloat(s)) {
//                Double x = Double.parseDouble(s);
//                System.out.println("isDouble: "+ x);
//            } else {
//                System.out.println(s);
//            }
//            System.out.println(s.length());
//        }
//        String column_0 = "ID";
//        String columnType_0 = "Int";
//        LinkedList columnNames = new LinkedList();
//        LinkedList columnTypes = new LinkedList();
//
//        columnNames.add(column_0);
//        columnTypes.add(columnType_0);
//        BPlusTreeConfiguration conf = new BPlusTreeConfiguration((String)columnTypes.getFirst(), columnNames, columnTypes);
//
//        BPlusTreeNode node = new BPlusTreeNode(0, BPlusTreeNodeType.ROOT_LEAF_NODE);
    }
}
