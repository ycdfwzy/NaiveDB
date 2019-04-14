package BTree;

public class BTree {
    public BTree(int a) {
        data = a;
    }

    public void printData() {
        System.out.println("x = "+data);
    }

    public void setData(int a) {
        data = a;
    }

    private int data;
}
