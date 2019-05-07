# B+树接口



## BPlusTreeConfiguration

`BPlusTreeConfiguration`类中存储了一棵B+树所必需的参数，可以用于构造B+树。

成员变量列表：

* `filename`：数据表文件名，例如`filename="test"`，那么B+树结构存储在`test.tree`，叶子结点的数据存储在`test.data`
* `pageSize`：每个树结点在磁盘上存储的大小，单位`byte`。默认值为`4096`（可能不够，请构造数据时自行设计好页面大小）。
* `columnType`：LinkedList，表格中每个属性的类型
* `columnName`：LinkedList，表格中每个属性的名字
* `keyType`：主键的类型，一定和`columnType`的第一个值一样
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
* `insert(LinkedList values)`：向树中插入一条数据，请确保`values`中值的顺序和`config`里存储`columnType`的顺序相同。
* `delete(Object key)`：从树中删除主键为`key`的数据。
* `update(LinkedList values)`：主键`key=values[0]`，将主键为`key`的数据更新为`values`。
* `search`：待定。。。



