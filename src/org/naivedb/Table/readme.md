# Table 接口

* 构造函数有两种，无需关心，创建和读取都只需要使用数据库提供的接口即可
* `void close()`，关闭table，将数据写回磁盘，使用完table后调用
* `void setPrimary(String)`，设置某列为主键，只能设置一次，参数为列名
* `void setNotNull(ArrayList<Boolean>)`，设置非空表，参数为一张布尔的表
* `ArrayList<String> getColNames()`，获取所有列名
* `ArrayList<Type> getColTypes()`，获取所有列类型
* `String getFileName()`，获取table的文件名前缀
* `long insert(LinkedList values)`，将`values`查入表格
* `ArrayList<Long> search(Conditions cond)`，按照`cond`搜索表数据，返回符合条件的行号列表。
* `void update(long row, LinkedList<String> colList, LinkedList<Expression> exprList)`，修改第`row`行数据。
* `void delete(long row)`，删除第`row`行数据
* `ArrayList<Long> getAllRows()`，获得所有行的行号。
* `LinkedList getSingleRowData(long row)`，获得第`row`行的数据。

