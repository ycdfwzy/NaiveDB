# B+树接口



## BPlusTreeConfiguration

`BPlusTreeConfiguration`类中存储了一棵B+树所必需的参数，可以用于构造B+树。

成员变量列表：

* `filename`：数据表文件名，例如`filename="test"`，那么B+树结构存储在`test.tree`，叶子结点的数据存储在`test.data`
* `pageSize`：每个树结点在磁盘上存储的大小，单位`byte`。默认值为`4096`（可能不够，请构造数据时自行设计好页面大小）。
* `columnType`：LinkedList，表格中每个属性的类型
* `keyType`：主键的类型
* `keySize`：主键的大小（byte），由`keyType`算出
* `treeDegree`：树的度数，根据`pageSize`等其他常量算出
* `rowSize`：一行数据的大小（byte），由`columnType`算出

构造函数需要提供的参数有`pageSize`，`filename`，`keyType`，`columnName`，`columnType`，其中`pageSize`是可选参数。



## BPlusTree

`BPlusTree`实现了一个简单的B+树。

部分成员变量如下：

* `config`是这个B+存储的必要参数，具体参见上一小节。
* ……剩下的懒得写了，使用者直接看后面的就行接口就行

对外提供的接口有

* 构造函数，提供两种构造方式：提供`config`或提供`filename`。用`config`构造适用于创建一个新的表格，而用`filename`是从文件中载入一个已有的表格
* `close()`：**请在销毁BPlusTree实例前调用该函数**，它会将数据全部写回磁盘。
* `insert(Object key, long rowNum)`：向树中插入一条索引，数据存在第`rowNum`行。
* `delete(Object key)`：从树中删除主键为`key`的索引。
* `update(Object oldKey, Object newKey, long rowNum)`：将主键为`oldkey`的索引改为`newkey`，新的数据在第`rowNum`行。
* `search(Object key)`：查找主键为`key`的数据的行号
* `search(Object key, String type)`：根据type的不同（`EQ,LT,GT,NLT,NGT`），分别查找主键等于/小于/大于/大于等于/小于等于`key`的数据的行号。
* `search(Object low, boolean eql, Object up, boolean eqr)`，查找主键在`low~up`之间的数据的行号，`eql=True`表示包含`key=low`的数据，否则不包含。`eqr`类似。



