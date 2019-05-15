package Table;

import BPlusTree.BPlustree;

public enum colType {
    Int("Int"), Long("Long"), Float("Float"), Double("Double"), String("String");
}

public class Table {

    protected String tableName;
    protected BPlusTree bptree;
    boolean[] colNotNull;
    colType[] colTypes;


}