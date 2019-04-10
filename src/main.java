import BTree.BTree;

public class main {
    public static void main(String[] args) {
//        System.out.println("Hello World!");
        BTree a = new BTree(100);
        a.printData();
        a.setData(200);
        a.printData();
    }
}
