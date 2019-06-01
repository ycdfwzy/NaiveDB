## Expression

`Expression`一共分为5类：纯数值、变量（符号）、一元操作符+`Expression`、`Expression`+二元操作符+`Expression`、纯字符串。在类中用`type`成员变量区分，对应值分别为`0~4`。

构造函数分为以下几种：

* `Expression(int type, String symbolORvalue)`：`type`必须是`0,1,4`中的一种，对应`Expression`的类型为纯数值、变量（符号）或者是纯字符串中的一种。**注意：纯数字也存为字符串类型**
* `Expression(int type, String unary_op, Expression expr)`：`type=2`，对应表示`Expression`的类型是   一元操作符+`Expression`，支持的一元操作符包括`+`，`-`，`~`（位非），`not`（非）
* `Expression(int type, String op, Expression expr1, Expression expr2)`：`type=3`，对应表示`Expression`的类型是  `Expression`+二元操作符+`Expression`，支持的二元操作符包括`+`，`-`，`*`，`/`，`%`

接口：

* `Pair<Object, Type> calcValue(LinkedList<String> nameList, LinkedList<Type> typeList, LinkedList valueList)`：传入变量名`nameList`，变量类型`typeList`和变量值`valueList`，返回该`Expression`的值（`Object`）和类型（`Type`）

* `boolean isSymbol(String symbol)`：判断该`Expression`是否是关于`symbol`的纯变量。
* `boolean isNumericValue()`：判断该`Expression`是否是纯数值。
* `boolean isStringValue()`：判断该`Expression`是否是纯字符串。
* `boolean isValue()`：判断该`Expression`是否是纯数值/纯字符串。
* `Pair<Object, Type> getDirectValue()`：获取该`Expression`的直接数值，**请在调用该函数前确保`isValue()`的值为`True`   **。



## Conditions

`Conditions`一共分为3类：`Conditions and Conditions`，`Conditions or Conditions`，`Expression op Expression`，分别表示逻辑合取、逻辑析取、单个逻辑表达式。在类中用`type`成员变量区分，对应值分别为`0~2`。

构造函数分为以下两种：

* `Conditions(int type, Conditions leftCond, Conditions rightCond)`：`type=0,1`，分别表示该`Conditions`的类型为逻辑合取、逻辑析取
* `Conditions(int type, String op, Expression expr1, Expression expr2)`：`type=2`，表示这是单个的逻辑表达式，支持的`op`包括相等`EQ`、不等`NEQ`、大于`GT`、小于`LT`、不大于`NGT`、不小于`NLT`。

接口：

* `boolean satisfied(LinkedList<String> nameList, LinkedList<Type> typeList, LinkedList valueList)`：传入变量名`nameList`，变量类型`typeList`和变量值`valueList`，判断该逻辑表达式的值为`True/False`。
* `boolean isSymbolEqualSomething(String symbol)`：判断该`Conditions`是否是`symbol = x`的形式。
* `boolean isUpperBounded(String symbol)`：判断该`Conditions`是否是`symbol∈(-∞, x)`或者`symbol∈(-∞, x]`
* `boolean isLowerBounded(String symbol)`：判断该`Conditions`是否是`symbol∈(x, +∞)`或者`symbol∈[x, +∞)`
* `boolean isRanged(String symbol)`：判断该`Conditions`是否是`symbol∈(x, y)`或者`symbol∈[x, y)`或者`symbol∈[x, y)`或者`symbol∈[x, y]`
* `Pair<Object, Type> getEqualValue()`：如果该`Conditions`是`symbol = x`的形式，那么取出`x`
* `Pair<Pair<Object, Type>, Boolean> getBoundValue()`：如果该`Conditions`是`symbol∈(-∞, x)`或者`symbol∈(-∞, x]`或者`symbol∈(x, +∞)`或者`symbol∈[x, +∞)`的形式，那么取出`x`，以及区间的开闭性。
* `Pair<Pair<Pair<Object, Type>, Boolean>, Pair<Pair<Object, Type>, Boolean>> getRange()`：如果是`symbol∈(x, y)`或者`symbol∈[x, y)`或者`symbol∈[x, y)`或者`symbol∈[x, y]`的形式，取出`x,y`，以及区间的开闭性。

