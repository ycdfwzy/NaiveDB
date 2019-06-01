# DatabaseManager 接口

* DatabaseManager提供所有方法都是static类型，直接使用类名调用即可
* `initial()`，初始化数据库系统，每次启动时必须调用
* `close()`，关闭数据库系统，每次退出时必须调用
* `Database create(String)`，创建一个数据库，输入数据库名，返回创建好的数据库
* `Database get(String)`，获取一个已经创建过的数据库，返回数据库
* `void drop(String)`，删除一个数据库
* `ArrayList<String> getDatabases()`，获取目前所有的数据库

# Database 接口

* 构造函数同样不需要关心，使用DatabaseManager创建即可
* `void close()`，关闭一个数据库，在使用完数据库后调用
* `Table createTable(String table_name, ArrayList<Pair<String, Type>> cols)`，创建一个表，输入表名和列信息，返回一个表对象
* `Table getTable(String table_name)`，获取一个已经创建了的表，返回一个表对象
* `void dropTable(String table_name)`，从数据库中删除某张表
* `ArrayList<String> getTables()`，获取数据库中所有的表

