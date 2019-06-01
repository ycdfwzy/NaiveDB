# PersistenceData 接口

* 构造函数`PersistenceData(String filename, LinkedList<String> types)`，接受文件名和类型列表。
* `long add(LinkedList value)` 增加一条数据，并返回其行号
* `LinkedList get(long rowNum)`获取行号为`rowNum`的数据
* `remove(long rowNum)`返回行号为`rowNum`的数据
* `update(long rowNum, LinkedList value)`跟新一条数据，注意：旧数据的主键和新数据的主键必须一致。
* `close()`关闭数据库前务必调用此函数以将数据改变写回文件。