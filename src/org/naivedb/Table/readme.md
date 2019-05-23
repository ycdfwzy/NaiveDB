# Table 接口

* 构造函数有两种，无需关心，创建和读取都只需要使用数据库提供的接口即可
* `void close()`，关闭table，将数据写回磁盘，使用完table后调用
* `void setPrimary(String)`，设置某列为主键，只能设置一次，参数为列名
* `void setNotNull(ArrayList<Boolean>)`，设置非空表，参数为一张布尔的表
* `ArrayList<String> getColNames()`，获取所有列名
* `ArrayList<Type> getColTypes()`，获取所有列类型
* `String getFileName()`，获取table的文件名前缀
* 还有很多没有写。。。