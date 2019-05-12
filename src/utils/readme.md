# utils

## Consts类
存储了一些必要的常量，包括各种数据类型的在文件中存储的默认长度等。
支持的数据类型包括：

* Int：整型，范围$-2^31\sim 2^31-1$
* Long：长整型，范围$-2^-63\sim 2^63-1$
* Float：单精度浮点数
* Double：双精度浮点数
* String×××：定长字符串，表示`×××`字符串长度，如果省略，默认为256。

有一个对外的接口`Type2Size(String)`，接受表示类型的字符串，返回字符串的长度。



## NumberUtils类

仅对外提供一些使用的函数，包括

* `isPureInteger(String)`,`isPositiveInteger(String)`,`isNegativeInteger`,`isInteger`,`isFloat`：判断字符串是否是纯整数（不带正负号），正数（可带正号），负数，整数，小数。
* `parseInt(str,s,len,flag=false)`：将`str[s~s+len]`解析为整型数返回，如果flag为`true`，则允许结果为`null`，否则解析到`null`会抛出异常。类似的函数还有`parseLong`，`parseFloat`，`parseDouble`，`parseString`。
* `readInt(BufferedInputStream)`,`writeInt(BufferedOutputStream, int)`：从文件中读入整型数，或者将整数存储到文件中去。类似的函数有`readLong`,`writeLong`,`readFloat`,`writeFloat`,`readDouble`,`writeDouble`,`readString`,`writeString`,关于String的读写可以提供一个长度参数，指明String的长度，否则长度为String的默认长度。
* `fromBytes(list,str,pos,type,flag)`：从`str`的`pos`位置开始，解析出`type`类型的数据，并加到`list`里。如果flag为`true`，则允许解析结果为`null`，否则解析到`null`会抛出异常。
* `toBytes(byte[] bytes,pos,Object value,type)`：将`value`按`type`类型转化为`byte[]`，并存入bytes的`pos`位置。