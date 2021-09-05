---
title: java
date: 2020-09-11 16:39:07
tags: Java
categories: 技术笔记
---

# java概述

[官方文档](https://docs.oracle.com/en/java/javase/15/docs/api/index.html)
* java EE：java企业版；java SE：java标准版；java ME：java微型版。
* java**不支持指针**。
* Application：java应用程序；Applet：java小应用程序（嵌入到web页面中，要依赖HTML文件和web浏览器，且没有main()方法）
* Java可以编写Web服务程序。
* Java特性：
  * 平台无关性；
  * 相对C++的简洁性。
  * java提供内存管理机制，会自动收集内存垃圾。
  * 多线程
  * 分布式：适用于网络
  * **面向对象**。
* 封装：将对象内的**数据和代码**联编起来，形成一个对象。
* 多态性：一个接口，有多个内在的实现。
* 继承：某一对象直接使用另一对象的所有属性和方法的过程。
* JDK：（Java Development Kit)
  * Sun免费提供的Java SE，如：JDK1.8
  * GPL许可的Java平台的实现：OpenJDK。
* Java程序基本结构：
  * `.java`:源程序文件
    * 一个包声明package语句；
    * 任意数量的import语句；
    * 类和接口声明
  * `.class`:经编译后的字节代码程序文件。
* Java中的字符使用的是**Unicode标准**，支持汉字在内的多种文字,16位存储，且无符号。
* Java符号：
  1. 关键字；
  2. 标识符：
     1. 必须由字母、下划线或美元符开头；
     2. 由字母，数字，下划线和美元符组成；
     3. 不能与关键字同名
  3. 常量；
  4. 运算符
  5. 分隔符
* 取名习惯：
  * 类和接口名用**名词**，大小写混写，**第一个字母大写**，如：`class WorldTool`
  * 方法名用**动词**，大小写混写，**第一个字母小写**，如：`depositAccount()`
  * 变量用名词或形容词等，大小写混写，**第一个字母小写**，如：`currentCustomer`
  * 常量符号用**全部大写**，并用**下划线将词分隔**，如：`PERSON_COUNT`

# 输入和输出

* 使用`Scanner in = new Scanner(System.in);`可以定义一个可以接受输入的东西`in`（名字是任取的。）。之后要接受输入时，就使用形如`in.nextline()``in.nextint()`的语句。（其中line与int表示接受的输入的数据类型，line是字符串，int是整型）。
* 使用` System.out.println();`可以直接输出字符串并且在每一次输出后都换行，还有`System.out.print()`，它与前者的区别是在每一次输出后不换行。
* `System.out.prinf()`这个输出的用法与c语言中的printf()类似，可以有`System.out.printf("%.2f",i)`这样的类似c语言的格式限制。
* Java的注释和c语言一样，可以用`//`和`/**/`，还有`/** */`用来为注释javadoc标明。
# 变量

* 变量的定义：<类型名称> <变量名>;
* 变量的名字(又叫做标识符）只能由字母，数字和下划线组成，且数字不可以出现在第一个位置上。Java的内置的关键字不可以用作变量名。
* Java是一种强类型语言，变量在使用前必须声明，且变量具有确定的类型。
* 所有被声明的成员变量都是有默认值的。例如：数字类型的默认值为0,引用类型的默认值为**null**，布尔值为**false**。不过方法中**局部变量声明后不会进行默认的初始化**,所以必须进行明确初始化。

# 常量

* 类似变量的定义声明，常量只需要在定义时在类型前加上关键词`final`。常量只能在定义时赋值初始化，之后不能再赋值。
* 可以在类中定义final类型的实例字段。如果在定义时没有给final类型的变量赋值，那么就可以在**构造器**（也只有在构造器中，其他方法不行）中为它赋值
* **变量的作用域**：总的来说，Java中变量的作用域就是在它被定义的花括号之内。如，在for循环内部定义的就只在该循环内有效，在函数内部定义的就只在函数内部有效。
* 常量符号命名规范：全部大写，并用下划线将词分隔，如:`PERSON_COUNT`.

# 运算

## 赋值

* 同c语言一样，Java依旧使用`=`来进行赋值。
* 在定义变量的时候可以进行赋值（此时叫做变量的初始化）。
* 赋值运算是自右向左。

### 复合赋值

* 同c语言一样，Java中也有复合赋值，即`+=, -=, *=, /=`。用法与c语言完全一致。`a += b + c`与`a = a + (b + c)`完全相同。

## 四则运算

* 当浮点数和整数放在一起运算时，Java会将整数先转化为浮点数，之后进行浮点数的运算。
* `+，-，*,/ , %`的运算优先级和惯常的优先级一致。特别的，`%`对于小数也可以使用。
* 赋值号`=`的优先级很低，以保持和正常思维一样的运算顺序。
* Java中同样有`i++, ++i`这种运算，用法与c语言完全相同。

## 位运算

* 位运算是用来处理整型数据的。
* 位运算符包括`&(and), |(or), ^(xor), ~(not)，>>, <<, >>>`.
* `&, |, ^, ~`是对每一位进行和，或，异或，非的运算。
* `>>,<<`是将位左移或者右移。`>>`用符号位填充高位。
  * 位运算是将值转换位2进制来进行移位，如：`1 << 1`,表示将1的二进制数左移一位，结果是2。
* `>>>`进行左移，但是用0来填充高位。

## 单目运算符

* 取正`+`和取负`-`,运算优先级高于普通的双目运算符。
* `++`和`--`java也同样使用。
* `~`:按位求补。

## 条件运算符

* `expreBool?expression1 : expression2`：条件为true则执行表达式1，否则执行2.
* 如：`int c = (a > b)? 1 : 2;`

# 数据类型

## 整型

* 整数类型不能表达有小数部分的数。整数与整数的运算结果还是整数。
* Java中所有的数字都是**有符号**的，没有无符号类型。
* `int`型：32位 ，如果整型常量后面没有L，则默认为int型
* `long`型：64位
* `short`型：16位

### 整数型常量

* 三种形式
  1. 十进制整数：是由不以0开头，0~9数字构成的数据；
  2. 八进制整数：是由**以0开头**，0~7数字组成的数据：012（与十进制数字10值相等）；
  3. 十六进制整数：是由**0x或者0X**开头，0~9数字及A~F的字母组成的数据：0x1F（与十进制数字31相等）

## 浮点型

* 浮点数就是通常所说的小数。
* `double`型的浮点数：12.23d/12.23D/12.23（不带字母时默认小数为double类型）  64位存储
* `float`:12.23f/12.23F 32位存储
* 带指数的数：如`2E2`,他的结果是200，即E2代表科学计数法的$10^2$,虽然**结果为整数但是仍然是double类型**。


## 布尔类型

* Java中的布尔类型是用`boolean`定义的，其含义与用法与c语言中的bool类型一致。但是整型与boolean是严格区分的，即将boolean类型变量赋为0或者1是不行的。

## 字符类型

* 单个字符类型是char，其字符字面量是用**单引号**来表示的，如'a','4'等。Java中的字符使用的是Unicode标准，支持汉字在内的多种文字,16位存储，且无符号。
* 类似C语言中的字符类型，可以对字符变量进行加减运算。如`i = 'A' - 'D'`中的i为3，也可以强制将字符类型转换为int类型。
* **逃逸字符**：有些没有办法打印出来的字符，这些字符由一个反斜杠`\`开头，后面跟上一个字符，由这两个字符合起来组成一个字符。常见逃逸字符：

|字符|意义|
|-|-|
|\b|回退一格|
|\t|到下一个表格位|
|\n|换行|
|\r|回车|
|\"|双引号|
|\'|单引号|
|`\\`|反斜杠本身|


## 包裹类型

* 每种基础类型（如，int，double，char）都有对应的包裹类型。
* 包裹类型除了具有和基础类型一样的功能外（如定义变量），还有许多可以实现的功能。如`Integer.MAX_VALUE`,可以得到int类型的最大值。即使用包裹类型可以调用许多java内置的方法。
* 包裹类型的第一个字母是大写的。
* 各种基础类型对应的包裹类型。

|基础类型|包裹类型|
|-|-|
|int|Integer|
|double|Double|
|char|Character|
|boolean|Boolean|

## 引用类型

* 引用类型（数组，class或interface）在声明变量时是**不会为变量（即对象）分配存储空间**的，所以即使声明了也不可直接使用，需要使用**new运算符为引用类型的分配存储空间，完成初始化**之后才能正常使用。

## 字符串变量

* **字符串字面量**：用**双引号**括起来的0或者多个字符为一个字符串字面量。如："hello"

  * 字符串字面量可以直接调用String类型的方法,如：`"hello".toUpperCase();`
  * 字符串字面量就是String类型的一个**匿名对象**。

* **字符串类型**：`String`是字符串类型（第一个字母为大写，是一种包裹类型），String是一个类，String类型的变量是字符串的管理者而非所有者（就和数组变量和数组一样。）

* **字符串变量的创建**：
  * 法一：`String s = new String(字符串字面量)`.用字符串字面量初始化字符串变量。也可以直接初始化一个字面量，如`String s = "hello"`。
  
    * 相同内容的字符串字面量在内存中默认是同一个字符串对象。
  
    * 但是同样的字符串字面量传递给String类构造器之后构造出来的就不是一个对象了。
  
    * 示例：
  
      ```java
      String s1 = "zhangjie";
      String s2 = "zhangjie";
      String s3 = new String("zhangjie");
      s1 == s2;//true
      s1 == s3;//false
      //==用于对象之间，只会判断引用的是否是同一个对象，不会管内容是否相同
      ```
  
      
  
  * 法二：使用`StringBuider <字符串名> = new StringBuilder();`可以创建一个空的字符串(但是这样创建的不是String类型，而是StringBuilder类型的变量了，使用`<字符串名>.append(ch)`或者<字符串名>.append(str)`来给字符串追加内容。(更多方法请查阅java.lang.StringBuilder)                                                              
  
* **字符串的连接**：使用`+`可以将两个字符串连接起来，如果`+`两边的不是字符串类型，它会自动将非字符串类型的转为字符串类型。

* **字符串的读入**：使用`in.nextline()`能直接读入一个字符串，字符串之间以回车为表示。（使用`in.next()`可以读入单词，每个单词之间用空格隔开。）

* 字符串是**只读**的变量，不可以通过赋值来修改。

* **String[]**代表的是**字符串数组**，即一个元素类型为String类型的数组。注意与String类型的区分。

### 字符串的操作

* 字符串的数据是对象，可以通过`.`来调用其方法来实现对字符串的操作。

* **字符串的比较**：`s.compareTo(s1)`;

* **获得字符串的长度**：`s.length()`;

* **访问字符串中的单个字符**：`s.charAt(3)`.(3是字符的索引，类似数组，但不可直接像访问数组中元素一样使用a[i]这样的访问)。

* **判断两个字符串是否相同**：`s.equals(s1)`.

  * `==`用于对象之间，只会判断引用的是否是同一个对象，不会管内容是否相同;
  * 而`equal()`判断是否相同的依据是它们的内容是否相同。

* **获得字符串的子串**：`s.substring(2,4)`或者`s.substring(2)`,括号中是字符的索引，单独的2代表从索引为2以后开始的子串，但是（2，4）代表从索引为2字符以后开始，到索引为4字符之前。

* **寻找字符串中的字符**：`s.indexOf(c)`或者`s.indexOf(c,n)`或者`s.indexOf(s1)`。括号中只有字符c代表找到字符串中c字符所在位置的索引。（c，n）表示从n位置开始寻找c字符，s1为字符串变量，（s1）能找到字符串s1所在的位置。还可以从右边开始找`s.lastIndexOf(同普通的)`.

  

  ## StringBuffer
  
  * **StringBuffer**是线程安全的可变字符序列。每个字符串缓冲区都有一定的容量。只要字符串缓冲区所包含的字符序列的长度没有超出此容量，就无需分配新的内部缓冲区数组。如果内部缓冲区溢出，则此容量自动增大。从 JDK 5 开始，为该类补充了一个单个线程使用的等价类，即 StringBuilder。与该类相比，通常应该优先使用StringBuilder类，因为它支持所有相同的操作，但由于它是异步的，所以速度更快。
## 强制类型转换

* Java同c语言一样，有很多隐式转换。例如整型在某些条件下会自动转为浮点型。
* 但是浮点型不会自动转换为整型（**大不会自动转小**）。我们可以进行强制类型转换。例如：`int a = (int)(32 /5 .0)`。

## var声明局部变量

* 当变量的类型可以**从它的初始值中推导出来**时，可以使用var关键字来声明，而无需指明其类型。如：`Employee harry = new Employee();`可以写作`var harry = new Employee();`。
* var关键字只能用于方法中的局部变量，**参数和实例字段**的类型必须明确声明。

# 关系运算

* 关系运算符：`==, >, <, >=, <=, !=`与C语言一致。
* 关系运算符的优先级比算术运算符低，但是比赋值运算高。例如：`7 > 3 + 2`。
* 判等和判不等`==, !=`的优先级比`<, <=, >, >=`低.例如：`5 > 3 == 6 > 4`是可行的（但是和c语言一样，尽量不要连续使用关系运算。）
* `<, >, >=, <=`是不能连续使用的，例如：`a > b > c`是和你的想象完全不同的。

## 浮点数的比较

* 整型是可以与浮点数进行比较的。
* 浮点数的运算有误差。所以判断两个浮点数是否相等是用`Math.abs(a -b) < 1e-6`.先用`Math.abs(a - b)`算出两个浮点数之间的差值，在让这个差值和一个很小的数比较。`1e-6`表示的是1的负6次方。


# 控制语句

## 条件语句

### if语句  

* 与c语言类似，只有一个语句时可以不用花括号（但是建议不管多少语句都用花括号），但是有多条语句时必须括起来。
* 类似c语言，Java中的if语句同样可以与else连用，进行级联条件判断。

### swich语句

* Java中同样有switcl-case语句。用法也与c语言相同。
* 示例：
```java
switch (控制表达式) {
    case 常量：
        语句
        ...
        break;
    case 常量：
        语句
        ...
        break；
    ...
    default:
        语句
        ...
}
```
* 控制表达式，只能是**整型**或者与整型相容（如byte short char）的结果，布尔型也不行；
* 每个case后面都要有**break**语句。
* 如果所有的case都不匹配，就执行default后面的语句；如果没有设置default语句，就什么也不做。(default后面直接接冒号，**没有条件**。default后面的语句**不用**放break来终止。）

## 循环语句

### while

* 与c语言中的while基本一致。 while是**当条件满足时**执行循环内的语句。
* 与c语言一样，Java中也有`do-while`循环，这个循环至少执行一次。do-while循环是一直执行直到while语句中的条件**不满足**。（while判断之后要接`;`）

### for循环

* Java中也有for循环，用法与c语言基本相似。
* 但是Java可以在循环条件语句中定义变量，并且在for循环中定义的变量只能在for循环中使用，在循环体外并没有那个变量的定义。例如：`for(int i =  1; i <= n; i = i + 1 ){}`。（for循环适合有明确结束条件的循环。）
* `break`语句在Java中与c语言中一致，用于跳出循环。
* `continue`语句也是与c语言一样，用于到达循环尾。(但是continue语句**不会跳过for循环的步进语句**，例如：`for(int i = 0; i <= n; i++){continue;}`， 每次continue语句之后还是要执行i++之后再开启下一轮循环。)。
* **循环的标号**：可以在循环前设置一个标号（例如;`out:`）来标示循环。使用`break  标号`和`continue 标号`可以使break和continue对标号标示的循环起作用。示例：
```java
OUt:
for(int i = 0; i <= n1; i++){
    for(int j = 0; j <= n2; j++){
        break OUT;
    }
}
```
OUT标示的循环是最外层的循环，使用`break OUT`能直接跳出最外层的循环。
* **for-each**循环：形如:`for (int i : numbers){}`,意思是每一次循环，一次将数组numbers中的元素值赋给变量i。常用于遍历数组（foreach循环不能用于遍历字符串，即java中字符串与数组没有c语言中那么亲密的关系）。**接收数组中元素的变量必须是在for中定义的**。 示例：
```java
int[] numbers = new int[100];

// 依次遍历数组中每个元素，并在数组遍历完之后停止循环。
for (int i : numbers) {
    if (i == 23) {
        System.out.println("numbers数组中有23这个数")；
        break；
    }
}

//错误使用

int i;
for(i : numbers) {
    System.out.println(i);
}

```

# 逻辑类型

* 关系运算的结果是一个逻辑值，true或者false。这个值保存在一个对应的逻辑类型的变量中，这个类型是boolean。与c语言的布尔类型一样，Java中的布尔类型也只有true和false这两种值。

## 逻辑运算

* **!**:逻辑非。
* **&&**：逻辑与。
* **||**：逻辑或。三者都与c语言中的逻辑运算的用法一致。

# 数组

* 数组定义：形如`int[] numbers = new int[100]`。**动态初始化**格式范式：`<类型>[] <名字> = new <类型>[元素个数]`（同c语言一样，数组的下标是从0开始的，后面方括号中是数组中元素的个数(可以为变量，但是必须有）。但是实际只有numbers[99], 而没有numbers[100].)
* 数组一旦创建，**不能被改变大小**。
* 数组本身是一个**引用数据类型**。
* 数组中的所有元素是**同一类型**。
* java中的每个数组有一个内部成员length,这个变量中存储了数组中元素的个数（即创建数组时确定的元素个数）。使用示例：
```java
int[] numbers = new int[100];

for(int i = 0; i < numbers.length; i++) {
    ...
}
```
* 数组的**直接（静态）初始化**：形如：`int[] numbers = new int[] {1, 2, 3, 4, 5};`也可以省略书写`int[] numbers = {1, 2,3};`
* 初始化的语句可以拆分为两个步骤：`int[] array; array = new int[5]`或者`int[] array; array = new int[] {1, 2, 3};`声明不能创建数组对象本身，只能创建一个引用。数组对象由new语句创建。
* 数组变量之间可以做赋值。例如：`int[] numbers = {1, 2, 3, 4, 5}; int[] b = numbers;`.数组变量的实质与c语言类似，是指向实际存储空间的“指针”，但是Java中不使用指针的概念，可以把数组变量看作是实际存储空间的管理者。所以数组变量之间的赋值，其实质是将对实际存储空间的管理权共享了出去，而不是把整个数组复制过去。因此，如果改变了b数组中，某个元素的值，numbers数组中相应的元素也会跟着改变。如`b [2] = 3;`之后会有`numbers[2] == 3`.
* **拷贝**数组元素到另一个数组中：`var a = new X[list.size]; list.toArray(a)`。将list数组的元素拷贝到a数组中去。（适用于各种类型的数组，包括泛型数组列表）
* 对数组对象的操作需要使用**Arrays**类中的方法。
* main函数必须带的参数`String[] args`是main函数接收命令行参数的字符串类型的数组。
* 数组变量之间的比较，是在比较两个数组变量是否管理同一个数组，而不是比较两个数组是否是每个元素都对应相等。
* 刚创建的int类型数组，数组中的元素**默认**都是0。double类型为0.0,boolean类型为false等。
* **二维数组**：定义形如`int[][] numbers = new int[3][5];`;与c语言类似，通常理解为矩阵。二维数组初始化：
```java

int[][] numbers = {
    {1, 2, 3, 4}, //每一行用逗号，分隔。
    {5, 6, 7}, //第二行比第一行少了一个元素，编译器会自动补上0；
};//别忘了最后的分号。
```
* 二维数组中仍然有length变量表示数组的长度。`numbers.length`表示数组的的行数。而每一行有多少个元素需要用`numbers[i].length`。

* 多维数组的实质是元素类型为数组的一维数组。

  * 必须对前面的维的数组（即低维数组）初始化，才能对它后面的维依次进行初始化。

  * 可以每一维元素分步初始化，来创建非矩形的数组。

  * 示例：

    ```java
    int[][] numbers = new int[2][];
    numbers[0] = new int[5];
    numbers[1] = new int[8];
    ```

    


# Math类

* Math类提供的很多数学操作，如取绝对值等。
* `abs`提供取绝对值操作，`.round`提供四舍五入的操作，`.random`提供取随机数（0到1之间）的操作，`.pow`可以进行幂运算。
* 使用示例：
```java

Math.abs(-12); //结果为12
Math.round(10.2343); //结果为10
Math.random(); 
Math.pow(2, 3); //结果为2的3次方：8
```

# 方法

## 方法定义

* 方法可以接收0个或者多个参数，做一件事，并返回0个或者一个值。
* 方法定义：函数头（函数名，返回类型，参数表），函数体。函数定义示例：
```java
public static boolean sum(int a, int b) 
{
    ...
    return true;  
}
```
## 方法调用

* 方法单独调用格式：`方法名（参数）`,注意，即使方法不需要参数，也需要括号。
* 方法可能会有返回值，此时需要有对应的变量来接收返回值。void类型的方法是没有返回值的。
* 传递给方法的参数类型要匹配，如果类型不匹配，则在某些情况下方法会自动转换。（条件是方法需要的类型比实际传进来的参数类型“宽”，如double类型比int类型宽。）。
* 传递给方法的参数，实际是传的**值**，即没有传进去变量本身，而只是变量值的一个副本。在方法内部是无法修改传进来的参数的值的。
  * **基本类型**：在方法调用时，实际参数将其存储单元的数据赋值给形式参数；
  * **引用类型**：在方法调用，引用类型虽然也是将其复制给形式参数，但是实际引用的对象却并没有发生改变，所以形式变量对引用中的对象值改变**会影响**到实际参数引用的对象值。
* 方法调用流程：
  1.  找到方法定义；
  2.  参数传递；
  3.  执行方法体；
  4.  带着返回值回到方法的调用处。

## 方法的本地变量

* 同c语言一样，Java方法内部定义的变量在方法外部是不可见的。方法内部定义的本地变量，只在方法内部有效。

* 
# 类与对象

## 类的初始化



## 类与对象的基本定义

* **类**：定义同一类事物，Java中所有的代码都位于某个类里。（类的第一个字母要大写）
  * 类包括三个部分：类声明，类成员，类的构造器（方法）。
* 类定义示例：
```java
public class Hero {
     
    String name; //姓名
     
    float hp; //血量
     
    float armor; //护甲
     
    int moveSpeed; //移动速度
}
```
* **对象**：类就像是一个模板，根据这个模板可以创建很多同一类的东西，这些根据类创建出来的东西就是对象。由类构造对象的过程叫做**实例化**。对象创建示例:
```java
public class Hero {
     
    String name; //姓名
     
    float hp; //血量
     
    float armor; //护甲
     
    int moveSpeed; //移动速度
     
    public static void main(String[] args) {
        Hero garen =  new Hero(); //对象的创建类似于数组或者String类型的变量的创建。
        garen.name = "盖伦";
        garen.hp = 616.28f;
        garen.armor = 27.536f;
        garen.moveSpeed = 350;
         
        Hero teemo =  new Hero();
        teemo.name = "提莫";
        teemo.hp = 383f;
        teemo.armor = 14f;
        teemo.moveSpeed = 330;
    }  
     
} //英雄是一个大类，而具体的每个英雄则是一个对象
```
* **属性（成员变量）**：类中定义的变量就是类的属性(类中的数据又叫做**实例字段**），类中的属性就像一种东西的各种属性。属性可以是基本类型（如int等）也可以是**类类型**（如String等）.属性名称一般是小写，但是如果由两个及以上单词组成，则从第二个单词开始，首字母大写。
  * 类中的实例字段只能由自己类中的方法来直接访问。换句话说，成员变量的作用域在类中是**全局**的，能被类中所有的方法所访问。
  * 所有对象的实例字段描述了对象当前状况的信息，这就是对象的**状态**。
*  **方法**：类中的东西不仅具有属性，它还会做事情，能做的事情就是类中的方法。（方法的实质是在类中定义的函数）.方法的命名方法与属性一样。方法与属性的调用方法都是通过在对象后使用`.`。
   *  通常的类没有main方法，却有自己的实例字段和方法。要想构建一个完成的程序，会使用多个类，但是**只有一个类有main方法**。
*  示例：
```java
public class Hero {
    String name; //姓名
      
    float hp; //血量
      
    float armor; //护甲
      
    int moveSpeed; //移动速度
 
    //Hero方法:坑队友
    void keng(){
        System.out.println("坑队友！");
    }
}
```

## 对象变量

* 不是基本类型而是类类型的变量，又叫做**对象变量**，对象变量可以用来引用该类的对象。
* 对象的生命周期：
  1. 创建：声明，实例化；
  2. 使用：通过对象名对成员的访问；
  3. 销毁：实例开销的回收，由JVM自动完成，对象在被作为垃圾收集前自动调用从Object继承的`finalize()`方法来清楚自己所占用的资源。
* 对象的创建时用`new Hero()`,但是创建的对象需要一个变量来接收才能使用，于是`Hero h = new Hero()`，此时的`=`不再是对于基本类型的赋值的意思，而是**指向**的意思。这个Hero类型的变量h就又叫做引用。（用c语言指针的概念来理解：就是h指向刚创建的Hero对象，但是Java中没有指针这个概念，所以只是一种理解）
* 对象变量可以**赋值**，如`Date deadline = birthday`(birthday也是一个对象变量，现在这两个对象变量引用同一个对象)。对象变量可以赋值为**null**，代表这个对象变量当前没有引用任何对象。
* 对象变量的值除了用new操作符，使用构造器获得外，还可以调用返回对象的引用的方法来赋值。如`DayOfWeek weekday = date.getDayOfWeek();
* 根据引用的概念，对象的创建又可以写为：
```java
public class Hero {
      
    String name; //姓名
      
    float hp; //血量
      
    float armor; //护甲
      
    int moveSpeed; //移动速度
      
    public static void main(String[] args) {
        //创建一个对象,但是没有引用。
        new Hero();
         
        //使用一个引用来指向这个对象
        Hero h = new Hero();
         
    }  
      
}
```
* （类比c语言的指针），可以有多个引用指向同一个对象，但是一个引用不能同时指向多个对象。

## 继承

* 如果另一类东西有已经定义的某一类东西全部属性，则可以通过继承来避免属性的重复定义。
* 继承的特性：
  1. **单一继承性**：子类只能有**一个超类**。
  2. 子类**不能继承超类的构造器**，只能通过`super()`来调用超类的构造器。
  3. 子类的构造器**首先要调用超类的构造器**。
  4. 子类的成员**隐藏和覆盖超类中相同的成员**。（多态性）
  5. 超类的对象可以对子类的实例进行引用。（多态性）
  6. 由**abstract修饰的类必须被继承**。
  7. 由**final修饰的类不能被继承**。
* 继承是一种`is-a`关系，即子类包含于父类中，子类的特性（字段和方法）**更多**。例如：人类是一个父类，而男人可以作为人类的一个子类。
* 父类又称为：超类、基类；子类又称为：派生类、孩子类。
* 子类从父类那里继承了**方法**和**字段**：
  * 子类只可以访问从超类继承下来的三种访问权限设定的成员：**public,protected以及缺省的**。
  * 如果是在父类中用private修饰的**私有字段**和**私有方法**（大多数时候是私有字段），虽然被子类继承，但是子类却**无法访问**。
* 根据对象的多态性，可以将**子类对象赋给父类引用变量**，但是反之不行。因为如果将父类对象赋给子类的引用变量，如果这个变量调用子类特有的方法就会出错。但是父类中的方法，子类对象都具有，所以不会出错。
  * 超类引用子类的的实例，只能调用超类中定义了的方法；
  * 对于被覆盖的方法，java运行时是根据实例的类型来选择调用哪个方法。即如果用超类引用子类的实例，调用被子类覆盖了的方法，那么执行的是子类中重新定义的方法。
* 继承是通过关键字`extends`来实现的。示例：
```java
public class Item {
    String name;
    int price;
}

// 非继承的写法，武器是一种物品。
public class Weapon{
    String name;
    int price;
    int damage; //攻击力
 
}
//继承的写法

public class Weapon extends Item{ // 通过extends来实现继承
    int damage; //攻击力
     
    public static void main(String[] args) {
        Weapon infinityEdge = new Weapon();
        infinityEdge.damage = 65; //damage属性在类Weapon中新设计的
         
        infinityEdge.name = "无尽之刃";//name属性，是从Item中继承来的，就不需要重复设计了
        infinityEdge.price = 3600;
         
    }
     
}
```

### 方法覆盖（重写）

* 父类中的某些方法可能对子类不适用，此时就需要在子类中重新写一个**名字相同（方法名，参数，返回数据类型都相同）但是实现不同**的方法。
* 注：方法过载是在一个类中具有相同方法名不同参数的方法。

### super

* super关键字可以在子类中调用父类的方法。
* super关键字可以在子类中调用父类的构造器。如`super(name,salry)`。具体调用父类的哪个构造器是由参数的数目和类型决定的。
* 另外，经常是在子类的构造器中调用父类的构造器来节省部分代码，但是**super调用构造器的语句只能作为子类构造器的第一个语句**出现。

### 继承层次

* 由一个公共父类派生出来的所有类的集合称为**继承层次**。
* 在继承层次中从某个特定的类到其祖先的路径称为该类的**继承链**。

### 阻止继承：final类与方法

* 被声明为final的类，是不能被继承的。声明格式`public final class Hello {...}`。
* 方法也可以被声明为final，这代表这个方法不能在子类中被**覆盖（重写）**。final类中的所有方法都被自动声明为final类型的方法（但是不会将字段自动变为final类型）。
* 字段被声明为final类型就与继承没有关系。final字段是指，该字段在对象构造出来初始化之后就不能被修改了。

### 对象引用的强制类型转换

* 在继承层次内才能进行对象引用的强制类型转换。
* 只能将父类强制类型转换为子类，并且应该使用instanceof检查要转换的两个类型之间是否为父子类关系。
* 示例：`boss = (Manger) staff[1]`。

### 对象包装器与自动装箱

* 所有的基本类型都有一个与之对应的类。（如：Integer类对应基本类型int）。这些类称为**包装器**。
* 包装器有：`Integer, Long, Float, Double, Short, Byte, Character, Boolean`。
* 包装器是**不可变**的类，包装器被构造之后，就不允许更改包装在其中的值。包装器是**final**类型的类，不能派生子类。
* 包装器的用途：将基本类型如int转换为对象。如：ArrayList<Integer>`。因为尖括号中必须是普通的类，所以不能使用int，此时可以用Integer达到相同的效果。
* **自动装箱**：在声明为包装器类的地方使用对应的基本类型，会自动将该基本类型的元素转换为对应的包装器类，这种特性叫做自动装箱。如：`list.add(3)`将自动转换成`list.add(Integer.valueOf(3))`。
* **自动拆箱**：在声明为基本类型的地方使用对应的包装器类，会自动将该包装器类的元素转换为对应的基本类型。
* 包装器中还有很多基本静态方法。如`int x = Integer.parseInt(s)`可以将s字符串转换为整型数值。 

### 参数数量可变的方法

* 示例：`public PrintStream printf(String fmt, Object...args)`。其中`...`是java代码的一部分。，表明这个方法可以接收任意数量的对象。实际上这个方法接收两个参数，一个是格式字符串fmt，一个是`Object[]`数组。`Object[]`数组中保存着其它所有参数。

### 反射

* 暂时略。

## 方法重载(方法过载)

* 方法重载指的是**方法名一样，但是参数不一样**。每次调用该名字的方法时，会自动根据对应的参数类型以及数量来调用对应的方法。
* **多态性**的表现。
* 参数的名称，数量以及类型在重载中都是可以随便修改的。
* 但是方法重载不能修改方法的**返回值类型**。
* 示例：
```java
public class ADHero extends Hero {
    public void attack() {
        System.out.println(name + " 进行了一次攻击 ，但是不确定打中谁了");
    }
 
    public void attack(Hero h1) {
        System.out.println(name + "对" + h1.name + "进行了一次攻击 ");
    }
 
    public void attack(Hero h1, Hero h2) {
        System.out.println(name + "同时对" + h1.name + "和" + h2.name + "进行了攻击 ");
    }
 //可以给类似的方法设置同样的名字。
    public static void main(String[] args) {
        ADHero bh = new ADHero();
        bh.name = "赏金猎人";
 
        Hero h1 = new Hero();
        h1.name = "盖伦";
        Hero h2 = new Hero();
        h2.name = "提莫";
 
        bh.attack(h1);
        bh.attack(h1, h2);
    }
 
}
```
* 方法重载还可以通过设置**可变数量的参数**来实现。可变数量的参数通过数组来实现。示例：
```java
public class ADHero extends Hero {
 
    public void attack() {
        System.out.println(name + " 进行了一次攻击 ，但是不确定打中谁了");
    }
 
    // 可变数量的参数
    public void attack(Hero... heros) { //Hero是类型，方法的形参需要设置...<数组名>来表示这个函数是接受可变参数的
        for (int i = 0; i < heros.length; i++) {
            System.out.println(name + " 攻击了 " + heros[i].name);//数组设置的参数调用方法与普通数组一样。
 
        }
    }
 
    public static void main(String[] args) {
        ADHero bh = new ADHero();
        bh.name = "赏金猎人";
 
        Hero h1 = new Hero();
        h1.name = "盖伦";
        Hero h2 = new Hero();
        h2.name = "提莫";
 
        bh.attack(h1);
        bh.attack(h1, h2);
 
    }
 
}
```
## 构造方法

* 实例化：通过一个类创建一个对象的过程叫做实例化。
* **构造方法（构造器）**：实例化是通过调用构造方法来实现的。构造方法同其他方法一样具有一样具有参数和语句体，但是**没有返回类型**。构造方法不是成员方法，不能用对象来调用它。**构造方法的名字与类的名字一样**，包括大小写也一样。
* 每个类可以有**一个以上**的构造器（构造器的方法过载）。
* **不要**在构造方法（类中的其它方法也一样）中使用与实例字段同名的局部变量。
* 构造方法之间可以**通过`this()`来相互调用**。
* 示例：
```java
public class Hero {
 
    String name;
 
    float hp;
 
    float armor;
 
    int moveSpeed;
 
    // 方法名和类名一样（包括大小写）
    // 没有返回类型
    public Hero() {
        System.out.println("实例化一个对象的时候，必然调用构造方法");
    }
     
    public static void main(String[] args) {
        //实例化一个对象的时候，必然调用构造方法
        Hero h = new Hero();//new 后面跟的是构造方法，只是构造方法的名字与类的名字是相同的。
    }
}
```
* 如果在定义类中没有对构造方法的定义，则编译器在编译时会自动给类加上一个无参的，语句体为空的构造方法。但如果已经手动定义了一个有参的构造器，就**不会**再自动生成这个默认无参的构造器，此时再使用无参的构造器构造对象就是非法的。
* 构造方法总是通过`new`来调用的，如果使用的是没有参数的构造方法，则直接是`new <类名>()`来调用，如果有参数则需加上参数列表`new <类名>(参数列表)`，另外，构造方法与普通方法一样，是可以进行方法重载的。示例：
```java
public class Hero {
       
    String name; //姓名
       
    float hp; //血量
       
    float armor; //护甲
       
    int moveSpeed; //移动速度
       
    //带一个参数的构造方法
    public Hero(String heroname){ 
        name = heroname;
    }
     
    //带两个参数的构造方法
    public Hero(String heroname,float herohp){ 
        name = heroname;
        hp = herohp;
    }
       
    public static void main(String[] args) {
        Hero garen =  new Hero("盖伦"); //调用构造方法创建对象时需要加上参数了。
        Hero teemo =  new Hero("提莫",383);
    }
     
}
```

### 静态代码块、初始化块

* 静态代码块：类中用static关键修饰的块（又名**类初始化块**）：

```java
public class 类名称 {
    static {
        //静态代码块的内容
    }
}
```

* 静态代码块特性：

  * 当**第一次**用到本类时，静态代码块执行**唯一的一次**；再次使用该类时，不会再执行静态代码块。
  * 静态内容总是优先于非静态，所以**静态代码块比构造方法先执行**。
  * 一个类中可以有多个静态初始化块；
  * 静态初始化块的执行要早于普通初始化块；
  * 用途：用来一次性地对静态成员变量进行赋值，比如需要通过计算来初始化static变量时可以声明一个static块。

* 初始化块：没有static修饰的普通块

  ```java
  
  class InitDemo1{
  	{
  		System.out.println("我是普通初始化块");
  	}
  }
  ```

* 初始化块特性：

  * 初始化块没有名字，不能被调用，它在对象创建时隐式执行，每次创建该类的对象都会执行初始化块。
  * 一个类里可以有多个初始化块，多个初始化块之间有顺序：在前面的初始化块先执行，后边的初始化块后执行。
  * 初始化块先于构造器先执行。

* 在父子类中，执行顺序是：

   爷爷类的静态初始化(静态属性初始化) >  父类静态初始化块(静态属性初始化）> 子类静态初始化块（静态属性初始化）> 爷爷类普通初始化块(普通属性初始化)>爷爷类构造器>
   父类普通初始化块(普通属性初始化)>父类构造器>
   子类普通初始化块(普通属性初始化)>子类构造器
   
* 初始化块常用于动态给对象的属性赋值（有些对象属性的赋值不是固定的，需要通过一些算法现场运算得到）

## this

* this可以顾名思义，this这个关键字代表当前对象，就相当于当前对象的名字，实际使用于类定义的内部。示例：
```java
public class Hero {
     
    String name; //姓名
     
    float hp; //血量
     
    float armor; //护甲
     
    int moveSpeed; //移动速度
 
    //打印内存中的虚拟地址
    public void showAddressInMemory(){
        System.out.println("打印this看到的虚拟地址："+this); //this在类内部使用，因为此时类没有名字，如果要用其中的属性很不方便，所以设置this来表示当前：“对象”，相当于类的一个虚拟名字。
    }
     
    public static void main(String[] args) {
        Hero garen =  new Hero();
        garen.name = "盖伦";
        //直接打印对象，会显示该对象在内存中的虚拟地址
        //格式：Hero@c17164 c17164即虚拟地址，每次执行，得到的地址不一定一样
 
        System.out.println("打印对象看到的虚拟地址："+garen);
        //调用showAddressInMemory，打印该对象的this，显示相同的虚拟地址->表示this起始和对象名garen指向的是同一个东西
        garen.showAddressInMemory();
    }
}
```
* 使用this给对象的属性赋值。示例：
```java
public class Hero {
     
    String name; //姓名
     
    //参数名和属性名一样
    //在方法体中，只能访问到参数name
    public void setName1(String name){
        name = name;
    }
     
    //为了避免setName1中的问题，参数名不得不使用其他变量名
    public void setName2(String heroName){
        name = heroName;
    }
     
    //通过this访问属性
    public void setName3(String name){
        //name代表的是参数name
        //this.name代表的是属性name
        this.name = name;
    }
     
    public static void main(String[] args) {
        Hero  h =new Hero();
         
        h.setName1("teemo");
        System.out.println(h.name); //结果为null，即参数的值传不到对象里的属性上
         
        h.setName2("garen");
        System.out.println(h.name); //结果为garen，成功，即使用this可以访问到当前对象的属性   
         
        h.setName3("死歌");
        System.out.println(h.name); //结果为死歌，成功，即使用非属性名的形参，还可以将值传给属性。    
    }
     
}
```
* this还可以用来在**一个构造器内部调用该类中的另一个构造器**。调用的形式为`this(要调用的构造器的参数)`。在一个构造器中调用另一个构造器的的语句只能放在该构造器的第一条语句。
  
## 隐式参数与显式参数

* 隐式参数是出现在方法之前的实例字段，在方法中被直接调用。而显式参数就是普通的位于方法名后面括号中的参数。
* 隐式参数常用this来指示，以便和局部变量区分开来。
* 示例：
```java

public class Employee {
    double salary;

    public void raiseSalary (double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}
```
* 在这个例子中，salary为隐式参数，而byPercent为显式参数。
* 可以用this指示隐式参数：
```java

public class Employee {
    double salary;

    public void raiseSalary (double byPercent) {
        double raise = this.salary * byPercent / 100;
        this.salary += raise;
    }
}
```

## 参数的传递

* 参数与变量一样，分为基本类型和类类型。基本类型参数与c语言基本一样，而类类型的参数有一点类似c语言中的指针参数。
* 类类型参数可以修改实际的类类型的变量指向的对象，实际类类型变量是对应对象的引用，是对象的一个地址。示例：
```java
public class Hero {
        
    String name; //姓名
        
    float hp; //血量
        
    float armor; //护甲
        
    int moveSpeed; //移动速度
     
    public Hero(){
         
    }
     
    public Hero(String name,float hp){
        this.name = name;
        this.hp = hp;
    }
 
    //复活
    public void revive(Hero h){ //传入的参数是Hero类型的引用
        h = new Hero("提莫",383);
    }
 
    public static void main(String[] args) {
        Hero teemo =  new Hero("提莫",383);
         
        //受到400伤害，挂了
        teemo.hp = teemo.hp - 400;
         
        teemo.revive(teemo); // teemo中hp的值变为了383，即通过类类型传递的是可以在函数内部修改的。
         
    
         
    }
      
}
```
* 在给参数命名时，可以在名字面前加上前缀a以与实例字段区别。如`name = aName`.

## 包

* 包：类和接口的集合，即为类库。
* 把具有某种关系的类放在一个包里。包必须在**类最开始的地方声明**。若是缺省该语句，则默认将类指定为无名包下。
* 借助包名可以确保类名的唯一性。包名常使用因特网域名的逆序形式，一般都是用小写字母。如域名为`horstman.com`则将包名设为`com.horstman`.包名之后还可以追加一个工程名之后再写上类名。如`com.horstman.corejava.Employee`，这个也被称为该类的**完全限定名**.其中corejava为工程名，Employee为类名。
* 示例：
```java
package charactor; //在最开始的地方声明该类所处于的包名
public class Hero {
        
    String name; //姓名
        
    float hp; //血量
        
    float armor; //护甲
        
    int moveSpeed; //移动速度
     
}
```
* 包与类的关系示例：
![java](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/java.jpg)

### 类的导入

* **一个类可以使用所属包中的所有类**，以及其他包里的公共类。但是**其他包里的公共类必须使用`import`来导入**。（也可以在使用其他包里的公共类时使用其**完全限定名**）
* 使用`import 包名.*`引入语句，只表示了源程序所需要的类会在包中找到并引入，但是对包中其它的类或它下面的包中的类并不引入。（但是只能使用`*`导入一个包，不能导入一个包里嵌套的所有包。）
* 示例：
```java
package charactor;
 
//Weapon类在其他包里，使用必须进行import
import property.Weapon;//格式为 import 类的完全限定名;
 
public class Hero {
        
    String name; //姓名
        
    float hp; //血量
        
    float armor; //护甲
        
    int moveSpeed; //移动速度
     
    //装备一把武器
    public void equip(Weapon w){
         
    }
        
}
```

### 静态导入

* 在import关键字之后加上static修饰符可以导入某个类中的静态方法和静态字段。如：`import static java.lang.System.*`可以导入System类中的所有静态方法和静态字段。如果这样导入，`System.out.print()`就可以用`out.print()`代替（可以省略类名）。当然，也可以导入特定的方法和字段。如`import static java.lang.System.out`.

### 在包中增加类

* 要将类放入包中需要将包的名字放在该类源文件的开头。如`package com.horstman.corejava`.如果在源文件开头没有防止package语句，则这个类就默认属于**无名包**（没有名字，但是实际存在的的包）。还要注意**类的源文件必须放到与完整包名匹配的目录中**。
* 编译和运行类必须切换到基目录。

### 类路径

* 类的路径必须与包名相匹配。
* 通过设置`classpath`来设置类路径，以使多个程序共享类。

### JAR文件

* **JAR文件**:java的压缩包文件（使用zip格式）。在一个JAR文件中可以包含多个压缩形式的类文件和子目录，也可以包含诸如图像、声音等类型的文件。在程序中使用第三方的库文件时，需要得到相应的JAR文件。
* **创建JAR文件**：使用`jar`工具来制作JAR文件。类似Unix系统中的`tar`指令。[jar程序选项](https://www.cnblogs.com/chenjfblog/p/10164967.html
* **清单文件**：每个JAR文件都包含一个清单文件（manifest），用于描述JAR文件的某些特性。[清单文件具体使用](https://blog.csdn.net/weixin_33801856/article/details/86247641).
* **可执行JAR文件**：需要为JAR包装的程序指定入口点，即在需要调用java程序启动器时指定的类。可以使用`jar`命令的e选项，来指定入口点。


## 成员变量的修饰符

### 类之间的关系

* **依赖**："use-a"关系，即一个类的方法使用或者操纵另一个类的对象，就是一个类依赖于另一个类。
* **聚合**："has-a"关系，即一个类中的对象包含另一个类的一些对象。
* **继承**："is-a"关系，表示一个更特殊的类与更一般的类之间的关系。，这个更特殊的类中不但包含了原来的类中的所有对象和方法，还增加一些额外的功能。
* **UML**:unified Modeling Language:统一建模语言，可以用来绘制类图。
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20200922073144.jpg)

### private

* 使用private修饰的变量，只有**这种类的对象**才可以访问，子类不可以继承，其它的类就更不能访问了。

### 缺省

* 没有修饰符的成员变量，自身的对象可以访问，同包的类可以访问，同包子类可以继承，不同包子类也可以继承，但是不同包的类不可以访问。

### proteced

* 使用protected修饰的成员字段或者方法，**同包类可以访问和继承**，不同包的类不可以访问和继承。


### public

* 任何地方，都可以访问和继承。
* 在一个源文件中（.java）中只能有**一个public类**（这个类就是文件名），但可以有任意数目的非公共类。
* 由一个源文件编译而成的类文件（.class），将包含main方法的类名提供给解释器以启动这个程序。

## 类属性（static）

* 当类中的属性被static修饰时，这个属性就叫做**类属性**，又叫做**静态属性**或**静态字段**。如果某个属性被声明为类属性，那么该类所有的对象都共享这个值,不管有没有实例化得到的对象，这个属性都存在，不管有多少个对象（甚至从未创建过该类的对象），都共用一个静态字段，静态字段只属于类，不属于任何单个的对象。静态属性中常用的是**静态常量**。类属性事实上提供了**全局变量和全局方法**。
* 与类属性相对的（类中没有static修饰的属性）叫做**对象属性**，又叫做**非静态属性**，**实例属性**。示例：
```java
package charactor;
 
public class Hero {
    public String name; //实例属性，对象属性，非静态属性
    protected float hp;
    static String copyright;//类属性,静态属性
     
    public static void main(String[] args) {
           Hero garen =  new Hero();
           garen.name = "盖伦";
            
           Hero.copyright = "版权由Riot Games公司所有"; //对类属性的赋值
            
           System.out.println(garen.name);
           System.out.println(garen.copyright);
            
           Hero teemo =  new Hero();
           teemo.name = "提莫";
           System.out.println(teemo.name);    
           System.out.println(teemo.copyright);
         
    }
     
}
```
* 对类属性的访问通过**直接使用类名**来完成`Hero.copyright = `.也可以通过实际的对象来调用，不过一般还是使用类来直接调用，这样更符合类对象的概念。

## 类方法（静态方法）

* **类方法**，又叫静态方法。同类对象相似，类方法也是通过在方法名前面加上`static`关键字来实现的。
  * static方法仅能调用其他的static方法；
  * static方法只能访问static数据；
  * static方法不能以任何方式引用this或者super。
* 静态方法不使用对象中的实例字段（也可以理解为静态方法中没有this参数，它所需要的所有参数都通过显示参数提供），计算结果与实际对象无关。
* 与类方法相对的是**对象方法**，对象方法又叫实例方法，非静态方法。示例：
```java
package charactor;
 
public class Hero {
    public String name;
    protected float hp;
 
    //实例方法,对象方法，非静态方法
    //必须有对象才能够调用
    public void die(){
        hp = 0;
    }
     
    //类方法，静态方法
    //通过类就可以直接调用
    public static void battleWin(){
        System.out.println("battle win");
    }
     
    public static void main(String[] args) {
           Hero garen =  new Hero();
           garen.name = "盖伦";
           //必须有一个对象才能调用
           garen.die();
            
           Hero teemo =  new Hero();
           teemo.name = "提莫";
            
           //无需对象，直接通过类调用
           Hero.battleWin();
         
    }
}
```
* **对象方法必须用实际创建的对象才能调用**，而类方法可以使用类来直接调用，也可以使用对象来调用。（同类对象的情况一致）。
* 当某个方法没有涉及到具体对象的属性时，设计为类方法，而当涉及具体对象的属性时，则一般设计为对象方法。示例：
```java
    public String getName(){
    	return name;
    }

    public static void printGameDuration(){
        System.out.println("已经玩了10分50秒");
    }
```

### 工厂方法

* 静态方法的另一个用途就是静态工厂方法，工厂方法实际上是构造器方法的延伸。使用工厂方法可以生成不同风格的对象。

### main方法

* main方法启动时还没有任何对象，所以main方法不对任何对象进行操作，main方法**必须声明为static**方法。
* 每个类都可以有一个main方法，用于对每个类进行单元测试。
* 包含main方法的类**声明为public类**。
* main方法是**程序运行的入口**。
## 属性初始化

* 对象属性初始化。
  1. 在声明时初始化；
  2. 在块中初始化，初始化块的前面还是可以加上修饰符的；
  3. 在构造方法中初始化。
  4. **初始化的顺序**：如果三种初始化方式同时出现，则不管构造方法中初始化的相对位置在哪里，它的初始化都是最后执行的。
  5. 示例：
```java
public class Hero {
    public String name = "some hero"; //声明该属性的时候初始化
    protected float hp;
    float maxHP;
     
    {
        maxHP = 200; //初始化块,单独用花括号给出一个用来初始化属性的块。
    }  
     
    public Hero(){
        hp = 100; //构造方法中初始化
         
    }
     
}
```

* 类属性初始化
  1. 声明时初始化
  2. 静态初始化块:在普通花括号形成的块前面加上关键字`static`形成静态初始化块。
  3. 示例：
```java
public class Hero {
    public String name;
    protected float hp;
    float maxHP;
     
    //物品栏的容量
    public static int itemCapacity = 8; //声明的时候 初始化
     
    static{
        itemCapacity = 6;//静态初始化块 初始化
    }
     
    public Hero(){
         
    }
     
    public static void main(String[] args) {
        System.out.println(Hero.itemCapacity);
    }
     
}
```

## 单例模式

* **单例模式**又叫**Singleton**模式，表示的是一个类，在一个JVM（Java虚拟机）中只有一个实例存在。

### 饿汉式单例模式

* 饿汉式单例模式通过使用private使构造方法无法在外部通过new来创建对象，并创建一个新的方法指定在类中定义的对象，每次使用该方法都只能产生一个唯一的对象从而实现单例模式。
```java
public class GiantDragon {
 
    //私有化构造方法使得该类无法在外部通过new 进行实例化
    private GiantDragon(){
         
    }
 
    //准备一个类属性，指向一个实例化对象。 因为是类属性，所以只有一个
 
    private static GiantDragon instance = new GiantDragon();
     
    //public static 方法，提供给调用者获取前面定义的instance对象
    public static GiantDragon getInstance(){
        return instance;
    }
     
}
```
### 懒汉式单例模式

* 懒汉式与饿汉式的区别在于懒汉式只有在调用创建对象的方法时才第一次创建对象。
```java
public class GiantDragon {
  
    //私有化构造方法使得该类无法在外部通过new 进行实例化
    private GiantDragon(){       
    }
  
    //准备一个类属性，用于指向一个实例化对象，但是暂时指向null
    private static GiantDragon instance;
      
    //public static 方法，返回实例对象
    public static GiantDragon getInstance(){
        //第一次访问的时候，发现instance没有指向任何对象，这时实例化一个对象
        if(null==instance){
            instance = new GiantDragon();
        }
        //返回 instance指向的对象
        return instance;
    }
      
}
```

## 枚举类型

* 枚举类型包括有限个命名的值。例如：
```java

enum Size{SMALL, MEDIUM, LARGE, EXTRA_LARGE};//定义枚举类型Size。
Size s = Size.MEDIUM;//声明枚举类型的变量s并进行初始化。
```
* 枚举类型的变量中只能存储这个类型的声明中给定的**某个枚举值**，或者null（表示这个变量没有设置任何值）。

# 接口与继承

## 接口（Interface）

* 接口不是类，而是对希望符合这个接口的类的一组需求（应该做什么）。接口是抽象的，它的所有成员方法都是抽象方法（abstract）（语句体都是空的。
* 接口的所有成员变量都默认是`public static final`类型，创建成员变量时可以省略这些修饰符。即接口中的所有成员变量都可以直接用接口名来访问。
* 但是在接口中声明的成员方法都默认是`public abstract`方法，也可以省略这两个修饰符。
* 接口可以作为一个引用类型来使用：
  * 任何实现了该接口的类的实例都可以存储在该接口类型的变量中；
  * 通过接口类型的变量可以访问类中所实现的该接口的方法。
* 接口不能包含**实例字段**，但可以包含**常量**。
* 接口的创建：
```java
package charactor;
 
public interface AD { //接口的创建使用关键字interface。
        //物理伤害
    public void physicAttack(); //接口的成员函数都是抽象函数，只有其形，而实际里面什么也没有。
}
```
* 接口的使用：（接口在类中具体实现时的public不能省略）
```java
package charactor;
 
public class ADHero extends Hero implements AD{ //ADHero是从Hero继承而来的的子类，并且使用关键字implements加入了AD接口

    @Override
    public void physicAttack() { //接口在实际使用的时候再定义具体操作的实现。这是方法的重写，需要有Override注解
        System.out.println("进行物理攻击");
    }
 
}
```
  * 接口使用关键字`implements`来加入，并且可以**同时加入多个接口**，如`...implements AD,AP`.

### 接口的属性

* 接口**不是类**，不能使用new来实例化一个接口。
* 可以声明**接口类型的变量**，这个接口类型的变量必须引用实现了这个的类对象。
* 可以使用`instanceof`来判断一个对象是否实现了某个特定的接口。如：`if(anObject instanceof Comparable) {...}`。
* 接口与类一样，也可以进行扩展，形成**接口链**，从通用性较高的接口扩展到专用性较高的接口。对接口进行扩展同样使用`extends`关键字，基本扩展规则与类的扩展相似。**接口可以实现多重继承，即一个接口同时继承其它多个接口**。

### 接口与抽象类

* 接口与抽象类的最大区别是：每个类**只能扩展一个类**。但是每个类却可以实现多个接口。

### 默认方法

* 接口中允许对一些方法进行实现，以使用这个接口的类不用在类中在对该方法进行实现，这种接口中实现了的方法称为**默认方法**。
* 默认方法必须再前面加上`default`关键字。

### 标记接口

* 普通接口的作用是确保一个类实现一些方法。而标记接口中不包含任何方法，它唯一的作用就是允许在类型查询中使用`instanceof`。如：Cloneable就是一个标记接口。

### lambda表达式

* lambda表达式是用来实现对一些代码块的复用，避免每次使用一些方法就得构造一个对象。（类似于模拟c语言中普通的函数）。
*  Lambda 表达式可以对某些接口进行简单的实现，但并不是所有的接口都可以使用 Lambda 表达式来实现。**Lambda 规定接口中只能有一个需要被实现的方法**，不是规定接口中只能有一个方法。jdk8中有另一个新特性：default， 被 default 修饰的方法会有默认实现，不是必须被实现的方法，所以不影响 Lambda 表达式的使用。
* lambda表达式的形式：(parameters) -> expression或(parameters) ->{ statements; }。
  * `()`:接口中抽象方法的参数列表，没有参数就空着；
  * `->`:传递的意思，把参数传递给方法体。
  * `{}`:重写接口的抽象方法的方法体。
* lambda表达式的重点是使代码延迟执行。这种延迟执行可以用于：
  1.  在一个单独的线程中运行代码；
  2.  多次运行代码；
  3.  在算法的适当位置执行代码等。
```java

//多个参数
(String first, String second) -> {
    if (first.length() < second.length())
        return -1;
    else if (first.length() > second.length())
        return 1;
    else 
        return 0;
}

//没有参数
() -> ｛for（intI= 100; i >= 0; ; i--) System.out.println(); ｝

//一个参数
event -> {
    System.out.println(event);
}

//只有一个表达式
(String first, String second) -> first.length - second.length();
```

#### 函数式接口

* 对于**只有一个抽象方法的接口**，需要这种接口的对象时，就可以提供一个lambda表达式，这种接口称为**函数式接口**。
* 可以将lambda表达式转换为函数式接口。

#### lambda的方法引用

* 当lambda表达式只调用一个方法而不做其他操作时，可以把lambda表达式重写为**方法引用**。
* 方法引用有三种情况：
  1. `对象 :: 普通方法` ：等价于向方法传递参数的lambda表达式，如`System.out::println`等价于`x -> System.out.println(x)`.
  2. `类 :: 普通方法` ：在这种情况下第一个参数会称为方法的隐式参数，如： `String::compareToTgnoreCase`等价于`(x, y) -> x.compareToIgnoreCase(y)`.
  3. `类 :: 静态方法` ：所有参数都传递到静态方法，如`Math::pow`等价于`(x, y) -> Math.pow(x, y)`。
* 还可以在方法引用中使用`this`和`super`.如：`this::equals`等价于`x -> this.equals(x)`.

#### 构造器引用

* 构造器引用与方法引用类似，只不过将方法名统一为new。如`int[]::new`等价于`x -> new int[x]`.

### 服务加载器

。。。

### 代理

。。。

## 对象转型

### 引用类型和对象类型

* 在`Hero a = new Hero()`中，a是引用，a的类型即是引用类型（此例中为a前面的Hero类类型），而`new Hero()`创建的就是对象，对象类型就是Hero类类型。

### 子类转父类（向上转型）

* 类型转换发生在引用类型和对象类型不一致的时候。（类似基本类型中`=`两边的类型不一致时。）类型转换不一定会成功。（就像基本类型转换也会有失败的时候，就像int不能转换为double）。子类是可以转换为父类的（就像基本类型中，长的可以转换为短的，如double可以转换为int）。

### 父类转子类（向下转型）

* 父类转子类需要进行强制类型转换，如`a  = (ADHero)h`.

### 没有继承关系的类

* 没有继承关系的类之间转换一定会失败，即使用强制类型转换也会出现异常报错。

### 实现类转换为接口(向上转型)

### 接口转换为实现类（向下转型）

## instanceof语句

* instanceof语句用来判断一个引用所指向的对象是否是某种类的实例化或者其子类的实例化。使用示例：`h instanceof Hero`判断引用h指向的对象是否是Hero类或者Hero子类的，如果是则结果为true，否则为false。

## 重写

* 子类可以继承父类的对象方法，如果子类对从父类继承过来的对象方法进行了修改（即在子类中对相同名字的对象方法在写一遍，故称为重写），这就叫做方法的重写。示例：
```java
package property;
 
//父类
public class Item {
    String name;
    
    public void effect() {
        System.out.println("物品使用后，可以有效果");
    }
 
}

//从Item中继承的子类
public class LifePotion extends Item{
     
     //对Item中的effect方法进行了重写
    public void effect(){
        System.out.println("血瓶使用后，可以回血");
    }
     
}
```

## 多态

### 操作符的多态

* 操作符的多态是指同一个操作符在不同的情景下的作用不同。如`+`，在算术运算中是加法的作用，而在字符串中是连接字符串的作用。

### 类的多态

* 同一个类的同一个方法，输出不同的结果是类的一种多态现象。示例：
```java
package property;
 
public class Item {
    String name;
    int price;
 
    public void buy(){
        System.out.println("购买");
    }
    public void effect() {
        System.out.println("物品使用后，可以有效果 ");
    }
     
    public static void main(String[] args) {
        Item i1= new LifePotion(); //LifePotion和MagicPotion是类Item的两个子类。而且这两个子类中都有方法的重写。
        Item i2 = new MagicPotion(); //引用是父类类型的。
        System.out.print("i1  是Item类型，执行effect打印:"); //由于两个子类的重写方法不同，导致调用同一个方法会有不同的输出
        i1.effect();
        System.out.print("i2也是Item类型，执行effect打印:");
        i2.effect();
    }
 
}
```

* 实现类的多态的条件:
  1. 父类（接口）指向子类的对象；
  2. 调用的方法被重写。

### 对象变量的多态

* 一个对象变量可以指示多种实际类型的现象称为多态。
* 这种情况常出现在父类和子类的对象中。如,父类为Father，子类为Kid。
```java

var people = new Father[2];

Kid person1 = new Kid();
Father person2 = new Father();

for(Father e : people) {
    e.age++;
}
```
  * 在这个例子中，对象变量e既有可能指向Father类型，也有可能指向Kid类型。
## 隐藏

* 与重写类似。重写是子类对父类的**对象方法**的覆盖，而隐藏是对父类的**类方法的覆盖**。示例：
```java
package charactor;
  
public class Hero {
    public String name;
    protected float hp;
  
    //类方法，静态方法
    //通过类就可以直接调用
    public static void battleWin(){
        System.out.println("hero battle win");
    }
      
}

public class ADHero extends Hero {
  
    //隐藏父类的battleWin方法
    public static void battleWin(){
        System.out.println("ad hero battle win");
    }   
     
    public static void main(String[] args) {
        Hero.battleWin(); //输出的是hero battle win
        ADHero.battleWin(); //输出的是ad hero battle win
    }
  
}
```

## super关键字

* super是在子类中调用父类被隐藏的属性，方法的关键字。
    1. 显式调用父类构造方法；如`super(参数)`
    2. 调用父类的属性：如`super.name`
    3. 调用父类的普通方法:如`super.useItem()`.
* `super()`**必须是子类构造函数的第一个执行语句**。

## Object类

* Object默认是**所有类的父类**。（全名：java.lang.Object:java.lang这个包是默认导入到每一个类中的）
* Object类中提供的方法所有类都默认含有。
* Objiect类中有很多方法，如`toString(), finalize(),equals()...`。

### Object类型的变量

* 可以使用Object类型的变量引用所有类型的**对象**。如：`Object obj = new Employee();`。（注意：除了基本类型（int，char，boolean...)不是对象，其他所有类型都是对象。） 
* 所有的数组类型，不管是基本类型的数组还是对象数组都是Object类的扩展。如
```java
Employee[] staff = new Employee[10];
obj = staff; //ok
obj = int[10]; //ok
```

### equals方法

* Object中equals的实现：
```java
    //第一种
 public boolean equals(Object obj) {
   return (this == obj);
   //第二种
```
* Objects类中equals的实现：
```java

   static boolean equals(Object a, Object b) {
       ...
   }
```

* equals方法是用来检测一个对象是否等于另一个对象的(Object中的equals方法适用于两个参数都不为null的情况，Objects类中的equals方法两个参数都为null时返回true，一个为null是返回false，两个都不为null时**返回a.equals(b)的结果**，即进行正常比较。不过object类中直接判断两个对象的引用是否相等来判断对象是否想等的方法有时会不够，所以equals方法经常会在子类中被**重写**。如：
```java
public class Employee{
    ...
    @override //用@override标记来表明这是对超类方法的覆盖，以避免出现参数类型不一致等情况（会报错）
    public boolean equals(Object otherObject){//参数类型必须为Object才能覆盖Object类中的equals方法
        //快速检查对象是否相同
        if(this==otherObject) return true;
        
        //如果EcPLID参数为空，则必须返回false
        if(otherObject==null) return false;
        
        //如果类不匹配，它们就不能相等。
        if(getClass()!=otherObject.getClass())
            return false;
        
        //现在我们知道另一个对象是非空雇员，将其强制类型转换为对应的类型
        Employee other =(Employee)otherObject;
        
        //测试字段是否具有相同的值
        return Object.equals(other.name);
        && salary==other.salary
        && hireDay.equals(other.hireDay);
    }
}
```
* equals方法的设计一般要遵循**自反性**，**对称性**，**传递性**，**一致性**，**非空性**。

### hashCode方法

* 散列码是由对象导出的一个整型值。没有规律，两个不同的对象的散列码基本不会相同。Object类的散列码是由对象的存储地址导出的。
* hashCode方法：`int hashCode()`。返回对象的散列码。

### toString方法

* toString方法会返回表示对象值的一个字符串：类名[字段值]。
* Object类中的toString方法会打印对象的类名和散列码。toString 方法经常会在子类中被重写。如：
```java
public String toString() {
    return getClass().getName() + "name=" + name +"salary=" + salary;
}
```
* 使用getClass和getName方法确保子类也可以调用该方法。
* 只要一个对象与一个字符串通过`+`相连，java编译器就会自动地调用toString方法来或得这个对象的字符串描述并与另一个字符串相连。


## final修饰词

* final在修饰类、方法、基本类型变量、引用是分别有不同的意思。

### final修饰类

* final修饰类时表示该类不能被继承。使用示例：
```java
package charactor;
 
public final class Hero extends Object {
        
    String name; //姓名
        
    float hp; //血量
        
}
```

### final修饰方法

* 如果类中的方法被final修饰，则代表该方法不能在子类中被重写。使用示例：
```java
public class Hero {
        
    String name; //姓名
        
   
     
    public final void useItem(){
        System.out.println("hero use item");
    }   
     
  
    public static void main(String[] args) {
        new Hero();
    }
      
}
```

### final修饰基本类型变量

* final修饰基本类型变量表示该类型只有一次赋值机会（即变量在第一次被手动初始化后便是只读的变量，再不可修改了），如：`final int hp = 2;`

### final修饰引用

* 被final修饰的引用代表该引用只有一次被赋予指向对象的机会（类似基本类型变量只有一次赋值机会）。

## 抽象类

### 抽象方法

* 没有实现体的方法，是抽象方法。抽象方法使用`abstract`关键字标识。示例：`public abstract void attack();`

### 抽象类

* **包含抽象方法的类必须声明为抽象类**，使用关键字`abstract`。
* 没有包含抽象方法的类也可以声明为抽象类。
* 抽象类不可以被直接实例化。即不可以使用`new`来创建抽象类的对象。但是**抽象类类型的引用变量**却是可以声明的。如果Person是一个抽象类，`new Person()`是错误的，但是`Person p;`却是可行的。
* **构造方法，类方法（static修饰的方法），私有方法**不可以作为抽象方法定义。
* **抽象类与接口区别**：一个子类只可以继承一个抽象类，但是可以实现多个接口。抽象类和接口都是为子类的具体实现提供了一个框架。
* 抽象类与接口中都可以包含实体的方法，这些实体方法被叫做**默认方法**。
* 示例：
```java
public abstract class Hero { //声明为抽象类
    String name;
             
    public static void main(String[] args) {
        //虽然没有抽象方法，但是一旦被声明为了抽象类，就不能够直接被实例化
        Hero h= new Hero();
    }
          
}
```

## 内部类

* 在一个类内部声明的类就是**内部类**，相应的外面的类为**外部类**。
* 内部类**对同一个包中的其他类隐藏**；
* 内部类方法可以访问定义这个类的类的作用域中的成员变量和成员方法，包括原本的**私有变量和方法**。
* 内部类**不可以声明类变量和类方法**（即内部类的成员变量和方法**不可以用static来修饰**）

### 非静态内部类

* 直接在类内部定义(类前面没有任何修饰词），只有在外部类存在的时候才有意义。实例化的语法：`new <外部类>().new <内部类>`或者`<外部类引用>.new <内部类>`.示例：
```java
package charactor;
 
public class Hero {
    private String name; // 姓名
 
    // 非静态内部类，只有一个外部类对象存在的时候，才有意义
    // 战斗成绩只有在一个英雄对象存在的时候才有意义
    class BattleScore { //直接在内部定义，前面没有修饰词
        int kill;
        int die;
        int assit;
 
        public void legendary() {
            if (kill >= 8)
                System.out.println(name + "超神！");
            else
                System.out.println(name + "尚未超神！");
        }
    }
 
    public static void main(String[] args) {
        Hero garen = new Hero();
        garen.name = "盖伦";
        // 实例化内部类
        // BattleScore对象只有在一个英雄对象存在的时候才有意义
        // 所以其实例化必须建立在一个外部类对象的基础之上
        BattleScore score = garen.new BattleScore();
        score.kill = 9;
        score.legendary();
    }
 
}
```

### 静态内部类

* 静态内部类在外部类里面定义时加上了`static`修饰，此时的内部类与普通类相比，除了内部类可以访问外部类的私有静态成员外，没有任何区别。
* 静态内部类不依赖于外部类的对象，所以可以直接实例化。如`h = new Hero.EnemyCrystal()`.
* 示例：
```java
package charactor;
  
public class Hero {
    public String name;
    protected float hp;
  
    private static void battleWin(){
        System.out.println("battle win");
    }
     
    //敌方的水晶
    static class EnemyCrystal{
        int hp=5000;
         
        //如果水晶的血量为0，则宣布胜利
        public void checkIfVictory(){
            if(hp==0){
                Hero.battleWin();
                 
                //静态内部类不能直接访问外部类的对象属性（非静态）
                System.out.println(name + " win this game");
            }
        }
    }
     
    public static void main(String[] args) {
        //实例化静态内部类
        Hero.EnemyCrystal crystal = new Hero.EnemyCrystal();
        crystal.checkIfVictory();
    }
  
}
```
### 匿名类

* 直接使用一个类的子类的类体创建一个子类对象，该类体被认为是创建该类的类的子类。如：`new People() {子类（匿名类）的类体}`
* 也可以直接使用接口名和一个类体创建一个匿名对象，此类体被认为是实现了该接口的类。如：`new Computable() {匿名类类体}`
* 对于抽象方法，本来应该在子类中对其实现后，再实例化。但是**匿名类**是在实际使用这个抽象方法的时候直接对其现场实现，省去了在子类中实现的步骤，因而没有子类的名字，叫做匿名。但是，实际上系统是自动为这个你未曾创建的子类分配了一个名字。
* 示例：
```java
package charactor;
   
public abstract class Hero {
    String name; //姓名
             
    public abstract void attack(); //抽象方法
      
    public static void main(String[] args) {
          
        ADHero adh=new ADHero();
        //通过打印adh，可以看到adh这个对象属于ADHero类
        adh.attack();
        System.out.println(adh);
          
        Hero h = new Hero(){ //对抽象类在实例化时直接实现抽象类，省去了在子类中实现的步骤，从而没有类名，故为匿名类。
            //当场实现attack方法
            public void attack() {
                System.out.println("新的进攻手段");
            }
        };
        h.attack();
        //通过打印h，可以看到h这个对象属于Hero$1这么一个系统自动分配的类名
          
        System.out.println(h);
    }
      
}
```

### 本地类

* 本地类可以看做有名字的匿名类，都是在实际使用的代码块里对抽象方法进行实现，只是本地类还顺手创建了子类的名字。示例：
```java
package charactor;
   
public abstract class Hero {
    String name; //姓名
      
    public abstract void attack();
      
    public static void main(String[] args) {
          
        //与匿名类的区别在于，本地类有了自定义的类名
        class SomeHero extends Hero{ //s使用时创建了Hero的子类SomeHero
            public void attack() {
                System.out.println( name+ " 新的进攻手段");
            }
        }
         
        SomeHero h  =new SomeHero();
        h.name ="地卜师";
        h.attack();
    }
      
}
```

## 默认方法

* 在接口中除了抽象方法，还可以实现具体的方法，这个实现了的方法就是默认方法。默认方法前使用`default`来修饰。示例：
```java 
package charactor;
 
public interface Mortal {
    public void die(); //抽象方法
 
    default public void revive() { //默认方法
        System.out.println("本英雄复活了");
    }
}
```

# 注释

1. `//`方式：其注释内容从//开始到本行末尾。
2. `/* */`方式：其注释内容从`/*`开始到`*/`结束。
3. 文档注释：从`/**`开始到`*/`结束。

## 文档注释

* 文档注释借助于javadoc工具实现，它可以由源文件生成一个HTML文档。
* 在注释中包含**标记**和自由格式的文本(在其中可以使用HTML修饰符)。每个标记以`@`开始，如`@return`.

### 类注释

* 类注释放在`import`语句之后，类定义之前。

### 方法注释

* 方法注释放在所描述的方法之前。通常会使用标记。
* `@param`：用以描述当前方法的参数(parameters),可以占据多行。
* `@return`:用以描述当前方法的返回信息，同样可以占据多行。
* `@throws`：表示这个方法有可能抛出异常。

### 字段注释

* 对公共字段（通常为静态常量）建立文档。

### 通用注释

* `@since`：建立一个描述这个特性的版本的文本。
* `@author`:描述作者，可以有多个这个标记，每个标记对应一个作者。
* `@version`:描述当前版本。
* `@see`和`@link`：可以在之后放指向其它类或方法，以及URL的超链接。

### 包注释

* 在每个包的目录下创建一个名为package-info.java（包含一个Javadoc文档，在文档后面是一个package语句）或者package.html的文件（使用普通的html即可，javadoc会抽取`<body>...</bode>`的部分）.

### 文档注释的抽取

* 使用[javadoc](https://www.cnblogs.com/fjhh/p/5370642.html).

# 异常、断言和日志

## 异常

* 异常就是程序执行过程中出现的不正常现象，如非预期情况，错误的参数，网络故障等。
* 任何一个程序都可能出现异常，java使用**异常对象**表示打开的文件不存在，内存不够，数组访问超界等非预期情况。

### 异常概念

* **异常处理的任务**：将控制权从产生错误的地方转移到能够处理这种情况的错误处理器。
* **程序中可能会出现的错误**：
  * 用户输入错误；
  * 设备错误；
  * 物理限制（存储空间）；
  * 代码错误。
* **异常处理过程**： 如果某个方法不能够正常的完成，可以通过**抛出**一个封装了错误信息的异常对象来退出方法。需要退出的方法并不返回任何值，而是**直接退出**（中断处理），之后异常处理机制开始搜索能够处理这种异常的异常处理器。

### 异常分类

* **异常对象**：是派生于Throwable类的类实例。
* **异常的层次结构**：
  ![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201015184846.jpg)
    * 所有的异常类都是由**Throwable类**继承而来，Throwable类属于java.lang类，不需要import即可使用。
    * **Error**类层次结构：描述了java运行时的系统内部错误和资源耗尽错误，不能人工处理的，一般也不要求处理。
    * **Exception**类层次结构：是设计程序时主要关注的异常。
    * **RuntimeException**:由**编程错误**导致的异常（如果出现RuntimeException异常，一定是程序员的问题），又称运行期异常，一般也不要求处理，只要求提示。具体包含：
      * 错误的强制类型转换；
      * 数组访问越界；
      * 访问null指针。
    * **IOException**:包含除RuntimeException的其他异常，例如：
      * 试图超越文件末尾继续读取数据；
      * 试图打开一个不存在的文件；
      * 试图根据给定的字符串查找并不存在的Class对象。
* **非检查型异常与检查型异常**：所有派生于**Error或者RuntimeException类的异常称为非检查型异常**。其余异常为检查型异常。

### 异常处理的第一种方式：声明检查型异常

* 在方法首法使用`throws`关键字声明该方法可能抛出的**检查型异常**：如： `public FileInputStream(String name) throws FileNotFoundException`。如果会抛出多个异常，可以在首部声明所有的检查型异常类,用逗号分隔，如：`public Image loadImage(String s) throws FileNotFoundException, EoFException`
* 一个方法必须声明所有可能抛出的**检查型异常**。而非检查型异常是我们无法控制的（Error），或者是我们应该极力在编程时避免的（RuntimeException）。
* **子类**方法中声明的检查型异常是**父类**方法中的声明的异常相同的，或者是该异常的子类。如果父类没有声明异常，则子类也不可以声明异常。
* 如果方法中抛出的多个异常都是一个异常的子类，那么只需要声明这写异常的父类异常即可。
* 声明的检查型异常会抛出给方法的调用者处理，最终交给JVM处理。
* 调用一个声明了抛出异常的方法，必须处理声明的异常，有以下两种方法：
  1.  在调用这个方法的方法里继续使用throws声明抛出异常，交给jvm来处理，但是jvm处理异常是通过中断来处理的，在抛出异常对象的语句之后的代码不会再被执行。
  2.  在该方法里使用`try...catch`语句，自己处理该异常。

### 抛出异常

* 抛出指定异常的方法：
  1. 找到一个合适的异常类；
  2. 创建这个类的**对象**：如：`var e = new EOFException`
  3. 将对象抛出：如`throw e`。
* 2、3步可以合并为一步：`throw new EOFException`
* 每个异常类都带有一个字符串参数的构造器，可以用来描述异常的具体信息。如：
```java
String gripe = "Content-length: " + len +", Recived: " + n;
throw new EOFException(gripe);
```
* throws 出来的RuntimeException或者其子类异常，就可以直接抛出交给jvm内置的异常处理器来处理。
### 创建异常类

* 如果标准的异常类不能描述清楚你程序的问题，那么就需要创建自己的异常类。
* 命名以Exception结尾。
* 自定义的异常类需要派生于Exception类或者Exception的子类（**所有异常类都是Throwable类的子类**）。并且这个类中应该包含两个构造器：一个默认的构造器（无参数），一个包含**描述异常信息的字符串参数的构造器**。示例：
```java
class FileFormatException extends IOException {
    public FileFormatException() {} //默认的构造器
    public FileFormatException (String gripe) {
        super(gripe);//父类构造器中有含这个参数的构造器，直接借用即可
    }
}
```

### 异常的第二种处理方法：捕获异常

* 抛出异常是将发生的异常交给异常处理器取处理。而与之相对的**捕获异常**就是将异常交给用户指定的异常处理器去处理。
* 在调用可能会产生异常的方法时使用`try/catch`来捕获异常，来替代直接throws声明异常将异常继续抛出交给jvm来处理。
* 捕获异常需要使用`try/catch`语句块，要捕获异常的方法就不再使用`throws`声明抛出异常。示例：
```java
    public void read(String filename) {
        try {
            //放置可能产生异常的代码
            var in = new FileInputStream(filename);
            int b;
            while (b = in.read() != -1) {
                process input
            }
        }
        catch (IOException exception) { //异常类名 异常对象名， 发挥了声明异常中throw new 异常的作用
            exception.printStackTrace(); //调用对应异常的处理器方法
        }
    }
```
* **捕获多个异常**：在一个try语句块中可以捕获多个异常类型，并对不同的异常类型做出不同的处理。例如：
    ```java
    try {
        //code that might throw exceptions
    }
    catch (FileNotFoundException e) {
        //emergency action for missing files
    } 
    catch (IOException e) { //父类异常必须放在子类异常后面，否则子类异常就失去作用了
        //emergency action for all other I/O problems
    }
    ​```java
        * 还可以用一个catch语句捕获多个异常类型，前提是这些异常类型彼此之间不存在子类关系。如：
    ​```java
    
    try {
        //code that might throw exceptions
    }
    catch (FileNotFoundException e | UnknownHostException e) { //同时捕获多个异常
        //emergency action for missing files or unknown hosts
    } 
    catch (IOException e) {
        //emergency action for I/O problems
    }
    ```
* **再次抛出异常**：可以在catch语句中仅记录一个异常（即*不对该异常对象做修改*），之后再将这个异常重新抛出。此时可以在方法上重新加上`throws`关键字，指明抛出的异常类型。示例：
```java
try {
    //access thr database
}
catch (Exception) {
    logger.log(level, message, e);//仅仅是记录该异常的信息
    throw e;
}
```

### finally子句

* 代码抛出一个异常时，会停止处理这个方法中的剩余的代码，并退出这个方法。但此时可能会有这个方法以及获得的资源没有清理。而finally子句正是用来清理这这些资源的。
* **finally不能单独使用，必须和try一起使用**。
* **无论该方法有没有抛出异常，finally子句中的代码都会在前面代码按照普通流程结束后执行**（没有异常则顺序执行到finally子句，捕获了异常则在catch中处理了异常再执行finall子句，抛出了异常则抛出异常后执行finally子句），示例：
```java
var in = new FileInputStream;
try {
    //code
}
catch (IOException) {
    //...
}
finally {
    in.close();
}
```
* 还可以将两个try嵌套，内层try确保关闭资源，外层try语句确保报告错误。示例：
```java
InputStream in = ...;
try {
    try {
        //code that might throw exception
    }
    finally {
        in.close();
    }
}
catch (IOException) {
    //...
}
```

### try-with-Resources语句

* try-with-Resources语句是和finally子句实现同样功能的。可以在try后面加上执行完代码后需要关闭的资源，这样构成try-with-Resources语句,无论什么时候退出try语块，都会自动将资源关闭。示例：
```java
try (var in = new Scanner(new FileInputStream("/usr/share/dict/words"), StandardCharests.UTF_8)) {
    while (in.hasNext()) {
        System.out.println(in.next());
    }
}
```
* try-with-Resources语句也可以带上catch子句和finally子句。

### 分析堆栈轨迹元素

* 堆栈轨迹（stack trace）是程序执行过程中某个特定点上所有挂起的方法调用的一个列表。
* 可以通过Throwable类或者StackWalker类来显示堆栈轨迹。

## 断言

* **断言**：断言机制允许在测试代码时插入一些检查。使用关键字`assert`来使用断言。一般格式: 
  * `assert condition;`:这个语句会计算condition中的条件，如果结果为false，就会抛出一个AssertionError的异常。
  * `assert condition:expression;`:在前一个语句的基础上增加了expression异常描述消息字符串，这个语句会将这个expression描述传入AssertionError的对象构造器中。
* 示例：
```java
//断言x是一个非负数。

assert x >= 0;
//或者
assert x >= 0 : x;
```
* 断言的使用场景：
  * 断言失败应该是失败的、不可恢复的错误；
  * 断言检查只是用在开发和测试调试阶段，是程序员用来自我检查的工具。

## 日志

* 日志用来记录程序中需要记录的问题。可以用java自带的API（如：java.util.logging）实现，也可以用第三方平台的API实现。


# 并发

## 线程与进程

* 每个进程(processor)都拥有自己的一套变量，而线程(thread)之间共享数据。线程比进程更加的轻量级。
* 线程是在进程内部同时做的事情。
* 多线程即在同一时间，可以做多件事情。
* 线程由3部分组成：
  1.  虚拟的cpu：封装在Java.lang.Thread类中。
  2.  cpu所执行的代码：传递给Thread类。
  3.  cpu所处理的数据：传递给Thread类。



## 创建多线程

### 继承线程类创建多线程

* 可以通过继承`Thread`类来实现创建线程，如：
```java
public class KillThread extends Thread{ //KillThread是继承自Thread的子类
     
    private Hero h1;
    private Hero h2;
 
    //线程的构造器
    public KillThread(Hero h1, Hero h2){
        this.h1 = h1;
        this.h2 = h2;
    }
 
    public void run(){ //覆盖Thread中的run方法（必须），以实现启动线程后的具体操作。
        while(!h2.isDead()){
            h1.attackHero(h2);
        }
    }
}
```
* 无论使用什么方法实现线程，线程启动都需要使用Thread类中的**start方法**。线程启动示例：
```java
    KillThread killThread1 = new KillThread(gareen,teemo); //新建一个线程
    killThread1.start(); //启动一个创建的线程
    KillThread killThread2 = new KillThread(bh,leesin);
    killThread2.start();
```

### 实现Runable接口来创建多线程

* Runable接口中有一个run方法，实现这个接口的时候必须实现run方法（run方法中是这个线程需要做的事）。
* 实现Runable接口示例：
```java

public class Battle implements Runnable{ //实现Runable接口的类
     
    private Hero h1;
    private Hero h2;
 
    public Battle(Hero h1, Hero h2){
        this.h1 = h1;
        this.h2 = h2;
    }
 
    public void run(){ //实现run方法
        while(!h2.isDead()){
            h1.attackHero(h2);
        }
    }
}
```
* 无论使用什么方法都需要**实现run方法**。
* 线程启动示例：
```java
    Battle battle1 = new Battle(gareen,teemo); //启动的时候，首先创建一个Battle对象，然后再根据该battle对象创建一个线程对象，并启动 
    new Thread(battle1).start(); //把Battle对象作为Thread对象的构造器的参数来新建一个Thread对象，并直接调用start方法启动这个线程。
 
    Battle battle2 = new Battle(bh,leesin);
    new Thread(battle2).start();
```

### 使用匿名类创建多线程
。。。

## 线程的状态

* 线程有4个状态：
  1.  **新生态**：线程生成后立即进入新生态，此时该线程已被初始化（分配存储空间，初始化数据）。此时的线程可以使用`start()`方法调度。
  2.  **可执行态**：，新生态使用`start()`方法被调度后，进入可执行态，随时可以被执行，可细分为两种状态：
      1.  执行状态：已获得cpu，正在执行。
      2.  就绪状态：只等待处理器资源。这两个子状态的过渡由执行调度器来控制。
  3.  **阻塞态**：由于某种原因引起线程暂停执行的状态。
  4.  **停止态**：线程执行完毕或者另一线程调用`stop（）`使该线程停止，进入停止态，它表示线程已经退出执行状态，并且不再进入可执行状态。
* 一个线程被创建后就有了生命周期。线程在创建后到销毁前总处于这四种状态中。

## 线程优先级

* 优先级是线程获得cpu而执行的优先程度。java把优先级划为10级，用1到10整数表示，数值越大，优先级越高。

* Tread类中定义了三个优先级常量：`MIN_PRIORITY`,`MAX_PRIORITY`, `NORM_PRIORITY`分别对应1，10，5.默认线程优先级是`NORM_PRIORITY`。

* Thread类中的`setPriority(int a)`方法可以修改线程的优先级。

* java采用**抢占式调度方式**，高优先级线程具有剥夺低优先级线程执行的权利。正在执行的低优先级线程，遇到高优先级线程只能停止，让高优先级线程立即执行。相同优先级的线程采取先来先执行。

* 调用**`sleep()`**方法能使线程暂时进入“睡眠状态”，让出cpu，使与其具有相同优先级线程或者低优先级的线程有执行的机会。

* **`yield()`**方法能让线程放弃cpu，使与其具有相同优先级的线程具有执行的机会。

  
## 线程同步

* 多线程同步控制机制：保证同一时刻只有**一个线程访问数据资源**。
* Tread类定义控制线程执行的方法：
  1. **`start()`**:用于调用`run()`方法使线程开始执行，进入可执行态；
  2. **`stop()`**:立即停止线程执行，其内部状态清零，放弃占用资源，进入停止态；
  3. **`wait()`**:使线程处于等待状态，进入阻塞态；
  4. **`notify()`**:使线程脱离阻塞状态,进入可执行态；
  5. **`sleep()`**:调整线程执行时间，参数指定睡眠时间，睡眠时间内，进入阻塞态；
  6. **`yield`**:暂停调度线程并将其放在等待队列的末尾，等待下一轮执行。
* **同步锁**：java使用锁标志（lock flag），对被访问数据进行同步限制（上锁），从而实现对数据的保护。线程必须取得锁标志才能访问被保护（上锁）的资源。
* 使用`synchronized`修饰符来为保护资源上锁。但是`synchronzied`只能用来说明**方法**和**代码段**，不能用来说明类和成员变量。被`synchronized`修饰的方法或者代码段同一时刻只能被一个线程执行，其他想用的线程必须等待。
* `wait()`可使一个线程进入阻塞状态，等待其他线程用`notify`唤醒。`notifyAll()`可以唤醒其它所有线程。二者配合实现同步机制。

# 流

* 流(stream)是指在计算机的输入和输出之间运动的数据序列。
* 流通过java的输入输出系统与物理设备链接。
* **字节流**：java把处理二进制数据的流称为字节流，字节流每次处理一个字节的数据。字节流的命名以**Stream**结尾。
* **字符流**：把处理某种格式的特定数据称为字符流，字符流每次处理一个字符的数据。字符流命名以**Reader /Writer**结尾。
* **节点流**：node stream是指直接从指定的位置（如磁盘文件或内存区域）读或写。
* **过滤器**：非节点流的流称为过滤器（filters）。过滤器输入流往往是将其它输入流作为它的输入流，经过过滤或处理后再以新的输入流的形式提供给用户；过滤输出流也类似。

## java.io.File类

* File类是**文件及文件目录路径名的抽象表示形式**。java可以使用File类对文件和目录进行创建，删除，获取，判断，遍历等操作。
* File类是与系统无关的类，任何操作系统都可以使用这个类中的方法和字段（它会自适应各种操作系统）。

### File类的成员变量

* 成员变量（都为静态）：
  * `static String pathSeparator;`路径分隔符：Windows中为分号`;`,linux系统为冒号`:`.
  * `static String separator`:文件名分隔符：Windows中为反斜杠`\`,linux中为正斜杠`/`。
  * `static char pathSeparetorChar`:内容一样的字符表示形式。
  * `static char separatorChar`：内容一样的字符表示形式。
* 路径：
  * 绝对路径：完整路径，以盘符`C:\\`开始，如`C:\02Permanent\Blog\source\_posts`
  * 相对路径：相对于当前项目的根目录，可以省略当前项目的根目录。若当前路径`C:\02Permanent\Blog\source\_posts\java.md`,在当前目录下可以简写为`java.md`。
  * 路径不区分大小写；
  * Windows的文件名分隔符原本为`\`,但是反斜杠又有转义的作用，所以需要使用两个反斜杠`\\`来表示分隔符。

### File类的构造方法

* 构造方法：
  * `File(String pathname)`:通过将给定路径名字符串转换为抽象路径名来创建一个File对象。
    * 参数：路径名称的字符串为构造的参数。
      * 既可以是文件结尾，也可以是文件名结尾。
      * 既可以是绝对路径，也可以是相对路径。
      * 路径可以存在的，也可以是不存在的。
  * `File(String parent, String child)`:根据parent路径名字符串和child路径名字符串创建一个File对象。
    * 参数：将路径分为了父路径和子路径两个部分，父路径和子路径都可以单独变化，使用起来很灵活。
    * 示例：`File f = new File("c:\\", "java.md");`f的结果为`c:\java.md`。
  * `File(File parent, String chile)`:根据parent**抽象**路径名和child路径名字符串创建一个File对象。
    * 参数：把路径分为了抽象路径和路径名字符串两部分。可以使用File类的方法对parent路径进行操作，再使用该路径创建File对象。
    * 示例：
    ```java
    File parent = new File("c:\\");
    File file = new File(parent, "hello.java");
    ```
  * `File(URL url)`：暂略

### File类获取文件信息的方法

* `public String getAbsolutePath()`：返回此File的**绝对路径**名字符串；（返回构造File时的传入的路径）
* `public String getPath()`:返回此File的路径名字符串（既可以是绝对路径，也可以是相对）
* `public String getName()`:返回此File表示的**文件或者目录的名称**（即构造时传入的路径的结尾部分）
* `public String getParent()`:返回File对象的父目录名称。
* `public long lenght()`:返回此File表示的文件的长度；
  * 长度是以字节为单位计算的；
  * 目录是没有大小的，只能得到具体文件的长度大小。

### File类判断属性或状态的方法

* `public boolean exists();`:此File对象表示的文件或者目录是否真实存在。如果路径本身就不存在，则直接返回false
* `public boolean isDirectory();`此File对象表示的是否为目录。如果路径本身就不存在，则直接返回false
* `public boolean isFile();`此File对象表示的是否为文件。如果路径本身就不存在，则直接返回false
* 还有`canWrite(), canRead(), isAbsoulte()`等分别表示是否写保护，是否读保护，是否使用绝对路径。

### File类创建和删除的方法

* `public boolean createNewFile();`:当且仅当具有该名称的**文件**尚不存在的时候，创建一个新的空文件。
  * 创建文件的路径和名称在构造方法中给出
  * 文件不存在，创建文件，返回true
  * 文件存在，返回false
  * 如果路径不存在，则会抛出**IOException异常**;所以调用这个方法要么throws异常，要么使用try-catch捕获异常。
  * 只能创建文件，不能创建文件夹（目录）
* `public boolean mkdir();`:创建由此File表示的目录（只能创建单级空文件夹）
  * 创建文件夹的路径和名称在构造方法中给出
  * 文件夹不存在，创建文件，返回true
  * 文件夹存在，返回false
  * 如果路径不存在，则还是返回false
  * 只能创建文件夹，不能创建文件。
* `public boolean mkdirs();`:创建由此File表示的目录（能够创建多级文件夹，如`File//111//222//333`）
  * 创建文件夹的路径和名称在构造方法中给出
  * 文件夹不存在，创建文件，返回true
  * 文件夹存在，返回false
  * 如果路径不存在，则还是返回false
  * 只能创建文件夹，不能创建文件。
* `public boolean delete();`删除由此File表示的**文件或目录**
  * 文件或者目录删除成功，返回true
  * 文件夹中有内容，不会删除返回false；
  * 构造方法中的路径不存在返回false；
  * 该方法直接在硬盘删除文件或者文件夹，不走回收站。

### File的目录遍历功能

* `public String[] list();`:返回一个String数组，表示该File目录中所有的子文件或目录。遍历构造方法中给出的目录，会获取目录中**所有文件/文件夹的名称**，把获取到的多个名称存储到一个String类型的数组。（打印出来就是文件或者文件夹名）
* `public File[] listFiles();`:返回一个File数组，表示该File目录中所有的子文件或目录。遍历构造方法中的目录，或获取目录中所有的文件或文件夹，把文件或者文件夹封装为File对象，多个File对象，存储到File数组中。（打印出来的是文件名和它的完整存储路径）
* 这两种方法遍历的是构造方法中给出的目录，如果给出的目录路径不存在，会抛出空指针异常，如果给出的路径不是一个目录，也会抛出一个空指针异常。
* 示例：递归打印多级目录
```java
import java.io.File;

public class Recurison {

    public static void main(String[] args) {
        File file = new File("c://...");
        getAllFile(file);
    }

    public  static void getAllFile(File dir) {
        File[] files = dir.listFiles();
        System.out.println(dir);

        for(File f : files) {
            if(f.isDirectory()) {
                getAllFile(f);
            } else {
                System.out.println(f);
            }
        }
    }
}
```
* 示例：遍历目录，只需以.java结尾的文件
```java
import java.io.File;

public class Recurison {

    public static void main(String[] args) {
        getAllFile(file);
    }

    public  static void getAllFile(File dir) {
        File[] files = dir.listFiles();
        System.out.println(dir);

        for(File f : files) {
            if(f.isDirectory()) {
                getAllFile(f);
            } else {
                /*
                * 三选一
                */
                //String name = f.getName();
                //String path = f.getPath();
                String s = f.toString();
                
                //把字符串转换为小写
                s = s.tolowerCase();


                boolean b = s.endWith(".java");
                if(b) {
                    System.out.println(f);
                }
            }
        }
    }
}
```
### FileFilter和FilenameFilter过滤器

* FileFilter是一个接口，用于抽象路径名（File对象）的过滤；
* FileFilter内有过滤文件的抽象方法：
```java
boolean accept (File pathname) //测试指定抽象路径名是否应该包含在某个路径名列表中
```
    * File pathname:使用ListFiles方法遍历目录，得到的每一个文件对象；
* FilenameFilter也是一个接口，用于过滤文件名称。
* FileFilter内过滤的抽象方法：
```java
boolean accept (File dir, String name) 
```
    * File dir:构造方法中传递的被遍历的目录；
    * String name：使用ListFiles方法遍历目录，获取的每一个文件/目录的名称
* 两个过滤器接口是没有实现类的，需要我们自己写实现类，重写过滤器的方法accept，在方法找那个自己定义过滤的规则
* 示例：遍历目录，过滤掉不是以.java结尾的文件。使用接口实现类。
```java
import java.io.File;

public class Recurison {

    public static void main(String[] args) {
        getAllFile(file);
    }

    public  static void getAllFile(File dir) {

        /*
        * 1.listFiles方法会对目录中传递的目录进行遍历，获取目录中的每一个文件、文件夹--》封装为File对象
        * 2.listFiles方法会调用参数传递的过滤器的accept方法
        * 3.listFiles方法会把遍历得到的每一个File对象，传递给accept方法的参数pathname
        * 4.accept的返回值为true则将传递进去的File对象存入File数组，为false则不保存。
        */
        File[] files = dir.listFiles(new FileFilterimpl());//传递过滤器对象,将过滤的规则传递给listFiles方法，最后返回过滤后的数组
        System.out.println(dir);

        for(File f : files) {
            if(f.isDirectory()) {
                getAllFile(f);
            } else {
                    System.out.println(f);
                }
            }
        }
    }
}
```
```java

public class FileFiterImpl implements FileFilter {
    @overide
    public boolean accept (File pathname) {

        if(pathname.isDirectory()) {
            return true; //如果是文件夹，则仍存在File数组中。
        }
        return pathname.getName().toLowerCase().endWith(".java");
    }
}
```
* 使用匿名内部类实现过滤器
```java
import java.io.File;

public class Recurison {

    public static void main(String[] args) {
        getAllFile(file);
    }

    public  static void getAllFile(File dir) {

        /*
        * 1.listFiles方法会对目录中传递的目录进行遍历，获取目录中的每一个文件、文件夹--》封装为File对象
        * 2.listFiles方法会调用参数传递的过滤器的accept方法
        * 3.listFiles方法会把遍历得到的每一个File对象，传递给accept方法的参数pathname
        * 4.accept的返回值为true则将传递进去的File对象存入File数组，为false则不保存。
        */
        File[] files = dir.listFiles(new FileFilterimpl() {
            @override
            public boolean accept(File pathname) {
            return pathname.getName().toLowerCase().endWith(".java") || pathname.isDirectory();
            }
        });//传递过滤器对象,将过滤的规则传递给listFiles方法，最后返回过滤后的数组
        System.out.println(dir);

        for(File f : files) {
            if(f.isDirectory()) {
                getAllFile(f);
            } else {
                    System.out.println(f);
                }
            }
        }
    }
}
```
* 使用lambda表达式（接口中只有一个方法）
```java
import java.io.File;

public class Recurison {

    public static void main(String[] args) {
        getAllFile(file);
    }

    public  static void getAllFile(File dir) {

        /*
        * 1.listFiles方法会对目录中传递的目录进行遍历，获取目录中的每一个文件、文件夹--》封装为File对象
        * 2.listFiles方法会调用参数传递的过滤器的accept方法
        * 3.listFiles方法会把遍历得到的每一个File对象，传递给accept方法的参数pathname
        * 4.accept的返回值为true则将传递进去的File对象存入File数组，为false则不保存。
        */
        File[] files = dir.listFiles((File pathname) -> {
            return pathname.getName().toLowerCase().endWith(".java") || pathname.isDirectory();
        }) //传递过滤器对象,将过滤的规则传递给listFiles方法，最后返回过滤后的数组
        System.out.println(dir);

        for(File f : files) {
            if(f.isDirectory()) {
                getAllFile(f);
            } else {
                    System.out.println(f);
                }
            }
        }
    }
}
```

## IO流概述

* I(Input)O(Output),流：数据（字符，字节）1个字符 = 2个字节，1个字节 = 8个二进制位。
* I输入：把硬盘中的数据读取到内存中。O输出：把内存的数据读到硬盘中。
* 字节流：以字节为单位，可以输入输出任意文件。
* 字符流：以字符为单位。
* 输入流：输入数据。
* 输出流：输出数据。
* **一切皆为字节**：一切文件数据都是以二进制数字的形式保存的，就是以一个一个字节的形式来存储的。

## 字节流

* 所有流都是在`java.io`包中实现的。

### 字节输出流

* 在`java.io.OutputStream`定义了OutputStream**抽象类**，表示所有输出字节流的超类。
* 成员方法：
  * `void close();`:关闭此输出流并释放与此流有关的所有系统资源。
  * `void flush();`:刷新此输出流并强制写出所有缓冲的输出字节。
  * `void write(byte[] b);`:将b.length个字节从指定的byte数组写入此输入流。每次写入数据都会覆盖文件中的所有的数据。
  * `void write(byte[] b, int off, int len);`:将指定byte数组中从偏移量off开始的len个字节写入此输出流。
  * `void write(int b);`:将指定的字节写入此输出流。
#### FileOutStream类

* OutStream类的子类，叫做文件字节输出流，把内存中的数据写入到硬盘的文件中。
* 构造方法：参数为写入数据的目的地。会有FileNotFoundException异常（是IOException异常的子类）。需要抛出或者捕获。
  * `FileOutputStream(String name)`:创建一个向指定名称的文件中写入数据的输出文件流。
  * `FileOutputStream(File file)`:创建一个向指定File对象表示的文件中写入数据的文件输出流。
  * `FileOutputStream(String name, boolean append);`：创建一个向具有指定name的文件中写入数据的的输出文件流。
    * String name：写入数据的目的地；
    * boolean append：**追加写**的开关。true:追加写，false：关闭追加写。
  * `FileOutputStream(File file, boolean append);`:
  * 构造方法的作用：
    * 创建一个FileOutputStream对象
    * 会根据构造方法中传递的文件/文件路径，创建一个新的空文件
    * 会把FileOutputStream对象指向创建好的文件。
* 字节输出流的使用步骤：
  1.  **创建**一个FileOutputStream对象，构造方法中传递写入数据的目的地；
  2.  调用FilePOutputStream对象中的**write方法**，把数据写入文件中；
  3.  释放资源(调用**close方法**），因为流在使用过程中会占用系统资源。
* 示例：
```java

public class Test {
    public static void main(String[] args) throws IOException {
    // **创建**一个FileOutputStream对象，构造方法中传递写入数据的目的地,在路径不对时会抛出IO异常的子异常（FileNotFoundException）。
    FileOutputStream fos = new FileOutputStream("路径");
    //调用FilePOutputStream对象中的**write方法**，把数据写入文件中；
    fos.write(数据);
    /*
    fos.write('b');
    byte[] b = {'1','a','-'};
    fos.write(b);
    fos.write(b, 1,  2);
    */
    //释放资源
    fos.close();
    }
}
```
* 写入字符串：
```java
byte[] b = "hello world".getBytes();
fos.write(b);
```
* 换行
    * 不同系统的换行符号；
      * Windows：`\r\n`
      * linux:`\n`
      * mac:`\r`
    * 示例：
    ```java
    FileOutputStream fos = new FileOutputStream("a.txt",true);
            byte[] c = "hello".getBytes();
            for(int i = 0; i < 10; i++) {
                fos.write(c);
                fos.write("\r\n".getBytes());
            }
            fos.close();
    ```


### 文件存储的原理与文本编辑器

* 写到硬盘中的文件是以二进制形式保存的，如果直接以int的形式输入数据，则会转换为二进制形式。如：`fos.write(97)`,实际写数据的时候会将97转化为二进制数1100001。
* 任意的文本编辑器（如记事本）在打开文件的时候都会查询编码表，把字节转换为字符表示，如果就是以字节形式输入`'a','1'`则会按照原样输出。
  * 0~127之间的数查询ASCII表；
  * 其他值：查询系统默认码表（如中文系统的GBK编码）。
* 示例：`fos.write(97);`将97写入a.txt文件中，最终用记事本打开看见的结果为字符`a`

### 字节输出流

* `java.io.InputStream`是字节输入流所有类的超类，是抽象类，定义了所有子类共性的方法
* `int read();`：从输入流中读取数据的下一个字节，返回值会转换为int类型。读到**文件末尾会返回-1**（因为系统会在每个文件末尾设置一个看不见的结束标记）.如果路径不存在，会有IOExcption异常。每次读取完之后，会自动“指向”后面紧接着的未被读的数据。
* `int read(byte[] b);`: 从输入流中读取一定数量的字节，并将其存储在缓冲区数组b中。数组b的长度是每次从文件中读取字节的数目。返回值是从文件中成功读取的字节数,如果一个数据都没有读到，则返回-1.
* `void close();`:关闭此输入流并释放与该流关联的所有系统资源。

#### FileInputStream类

* 继承了`InputStream`类。将硬盘文件中的数据，读取到内存中使用。
* 构造方法：会有FileNotFoundException异常（是IOException异常的子类）。需要抛出或者捕获。
  * `FileInputStream(String name)`:String name是要读取的文件的路径。
  * `FileInputStream(File, file)`：File file是要读取的文件。
  * 作用：创建一个FileInputStream对象，并将此对象指定构造方法中要读取的文件。
* 输入字节流的使用步骤：
  1.  创建一个FileInputStream对象，构造方法中绑定要读取的数据源。
  2.  使用FileInputStream对象中的方法read，读取文件。、
  3.  释放资源（close方法）
* 一次读取一个字节的示例：
```java
import java.io.FileInputStream;
import java.io.IOException;

public class InputStreamTest {
    public static void main(String[] args) throws IOException { //抛出IOException异常可以同时解决FileNotFound和IoException两个异常
        FileInputStream fis = new FileInputStream("a.txt");
        int len = 0;
        while((len = fis.read() )!= -1) {
            System.out.print((char)len); //要想显示字符信息，需要强制转换为char类型
        }
    }
}
```
* 一次读取多个字节的示例：
```java
public class InputStreamTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("a.txt");
        byte[] b = new byte[1000];
        int len = fis.read(b);
        System.out.println(len);
        System.out.println(new String(b));//利用byte数组作为参数构造String对象可以按照文件中原有的格式输出数据。
        fis.close();
        }
    }
```
* 文件复制基本操作：
  1.  创建一个字节输入流对象绑定源数据地址；创建一个字节输出流对象绑定目的数据地址。
  2.  read读入字节，使用write写入目的地址。
  3.  关闭字节输出流对象，关闭字节输入流对象。

## 字符流

* 一个中文在GBK中占用2个字节，在utf-8占用3个字节。所以一个字节一个字节的读入文件不能得到正确的中文字符。使用字符流可以一次读取一个字符（中文，英文，符号等），解决了这个问题。

### java.io.Reader类

* 字符输入流：是**字符输入最顶层的父类**，定义了一些共性的成员方法，是一个抽象类。
* 成员方法：
  * `int read();`:读取单个字符并返回；
  * `int read(char[] cbuf)`:一次读取多个字符，将字符读入数组；
  * `void close();`关闭该字符流并释放与之相关的所有资源。

#### java.io.FileReader类

* 继承了`InputStreamReader`和`Reader`类。
* 作用：把硬盘文件中的数据以字符的方式读取到内存中。
* 构造方法：会有FileNotFoundException异常（是IOException异常的子类）。需要抛出或者捕获。
  * `FileReader(String fileName)`
  * `FileReader(File file)`
  * 参数：
    * String fileName:文件的**路径**；
    * File file：一个文件。
  * 作用：
    * 创建一个FileReader对象；
    * 会把FileReader对象指向我们要读取的文件。
* 字符输入流的使用步骤：
  1.  创建FileReader对象，构造方法中绑定要读取的数据源；
  2.  使用FileReader对象中的read()方法读取文件；
  3.  释放资源（close）。
* 示例：
```java

//一个字符读入
import java.io.FileReader;
import java.io.IOException;

public class ReaderTest {
    public static void main(String[] args) throws IOException {
        //创建对象
        FileReader fr = new FileReader("a.txt");
        //使用read读取文件
        int len = 0;
        while ((len = fr.read()) != -1) {
            System.out.print((char)len);
        }
        //释放资源
        fr.close();
    }
}

//使用字符数组，一次读入多个字符进入数组
import java.io.FileReader;
import java.io.IOException;

public class ReaderTest {
    public static void main(String[] args) throws IOException {

        //创建对象
        FileReader fr = new FileReader("a.txt");
        char[] cs = new char[1024];
        int len = 0; //记录每次读取的有效字符个数
        while ((len = fr.read(cs)) != -1) {
            System.out.println(new String(cs, 0,len));//把字符数组的一部分转换为字符串
        }
    }
}
```

### java.io.Writer类

* 字符输出流：所有字符输出流最顶层的父类，是一个抽象类；
* 部分成员方法：
  * `void write(int c);`写入单个字符；
  * `void write(char[] cbuf);`写入字符数组；
  * `void write(String str);`：写入字符串；
  * `void flush()`：刷新该流的缓冲；
  * `void close()`:关闭此流，但要先刷新它。

### java.io.FileWriter类

* 继承了OutputStream和Writer类，把内存中的字符数据写入文件中。
* 构造方法：会有FileNotFoundException异常（是IOException异常的子类）。需要抛出或者捕获。
  * `FileWriter(File file);`:根据指定的File对象构造一个FileWriter对象。
  * `FileWriter(String filename);`:根据指定的文件名构造一个FileWriter对象。
  * 参数：写入数据的目的地
    * String filename：文件的路径
    * File file：文件
  * 作用：
    1. 创建一个FileWriter对象
    2. 根据构造方法中传递的文件/文件的路径创建文件（该文件不存在的情况下）
    3. 会把FileWriter对象指向创建好的文件。
* 字符输出流的使用步骤：
  1. 创建FileWriter对象，构造方法中绑定要写入数据的目的地；
  2. 使用FileWriter对象中的write()方法，把数据写到**内存数据缓冲区**（字符转换为字节）
  3. 使用FileWriter对象中的**flush方法**，把内存中的数据，刷新到文件中。
  4. 释放资源（close():会先把内存缓冲区的文件刷新到文件中，再释放资源）
* 示例：
```java
import java.io.FileWriter;
import java.io.IOException;

public class WriterTest {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("a.txt");

        fileWriter.write("你好!");

        fileWriter.flush();

        fileWriter.close()
    }
}
```
* 续写：使用append开关：
  * `FileWriter(String fileName, boolean append);`
  * `FileWriter(File file, boolean append);`
  * append是续写的开关，为true则**不会创建新的文件覆盖源文件**，为false则创建新的文件覆盖源文件。
* 换行：使用换行符号
  * windows：`\r\n`
  * linux:`/n`
  * mac:`/r`
* 续写和换行示例：
```java
import java.io.FileWriter;
import java.io.IOException;

public class WriterTest {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("a.txt",true);

        fileWriter.write("你好"+ "\r\n");
        fileWriter.write("你好"+ "\r\n");

        fileWriter.flush();

        fileWriter.close();
    }
}
```

## 流中的异常处理

# 网络编程

* java具有支持Internet和WWW的完整软件包。java.net包支持Internet。

## 网络通信协议

* 同一个网络中的计算机在进行连接和通信时需要遵守一定的**规则**，这些规则被称为网络通信协议，它对数据的传输格式，速率，步骤等做了统一规定。
* TCP/IP协议：传输控制协议/因特网互联协议（Transmission Control Protocol/Internet Protocol）是Internet最基本和广泛的协议，它定义了计算机如何炼乳因特网以及数据在它们之间传输的标准。它包含了一系列协议，并且采用了**4层分层模型**，每一层都呼叫它的下一层所提供的协议来完成自己的需求。
  ![20180930155137505](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/20180930155137505.jpeg)
  * 链路层：用于定义物理传输通道，通常是对某些网络连接设备的驱动协议。
  * 网络层：网络层是协议的核心，它主要用于将传输的数据进行分组并将分组后的数据放松到目标计算机或者网络。
  * 运输层：主要用于网络程序之间的通信（可以用TCP协议，也可以使用UDP协议）。
  * 应用层：主要负责应用程序的协议，如HTTP协议，FTP协议。
* **UDP协议**：用户数据报协议（User Datagram Protocol）。
  * UDP是无连接通信协议，即在数据传输时，数据的发送端和接收端不建立逻辑连接。
  * 不能保证数据的完整性.会有丢包现象出现。
  * 消耗资源少，通信效率高，通常用于音频，视频和普通数据等的传输。
  * 数据限制在64kb之类，超出这个范围就不能传输了。
  * **数据报（Datagram）**：网络传输的基本单位。
* **TCP协议**：传输控制协议（Transmission control Protocol）。
  * TCP协议是面向连接的通信协议，即传输数据之前，在发送端和接受端建立逻辑连接，然后再传输数据。
  * TCP协议提供了两台计算机之间**可靠无差错**的数据传输。
  * TCP连接必须明确客户端和服务器端，**由客户端像服务器端发送请求**，每次连接的创建都需要经过**三次握手**：
  * 三次握手：在发送数据的准备阶段，客户端和服务器端之间的三次交互，以保证连接的可靠：
    * 第一次握手：客户端向服务器端发出连接**请求**，等待服务器的确认；
    * 第二次握手：服务器端向客户端回送一个**响应**，通知客户端收到了连接请求。
    * 第三次握手：客户端再次向服务器端发送确认信息，确认连接。
  * 传输安全，应用广泛，例如下载文件，浏览网页等。



## IP地址

* **IP地址**：互联网协议地址（Internet Protocol Address），IP地址用来给一个网络中的计算机设备做唯一的编号。
* IP地址分类：
  * IPV4:32位的二进制数，通常被分为4个字节（每个自己的表示范围位0～255的十进制整数），表示成`a.b.c.d`格式，如：`192.168.65.100`.
  * IPV6:为了扩大地址空间，通过IPV6来重新定义地址空间，采用128位，每16个字节一组，分为8组16进制数，表示成`ABCD:EF01:2345:6789:ABCD:EF01:2345:6789`/
* 查看本机的IP地址，在控制台输入：`ipconfig`
* 检查网络是否连通,在控制台输入`ping IP地址`

## 端口号

* IP地址唯一标示网络中的设备，端口号唯一标识设备中的进程（应用程序）。
* 打开网络软件时，操作系统会为该网络软件分配端口号。
* 端口号：用两个字节表示的整数，范围（0～65535）。
  * 1024之前的端口号，已经被系统分配给了已知的网络软件，不能再使用（一台计算机上的端口号是不能够重复的）。
  * 常用端口号：
    * 80端口：网络端口。如`www.baidu.com:80`
    * Mysql默认端口号：3306
    * Tomcat默认端口号：8080
* 利用`协议+IP地址+端口号`可以标识网络中的进程。

## 套接字（socket）

* 网络上的两个程序通过一个双向的通信连接实现数据的交换，这个连接的一端称为一个socket。
* Socket的英文原义是（电力系统的）“插座”，在网络中用于描述IP地址和端口。在Internet上的主机一般同时提供几种服务，每种服务都绑定到一个端口上。客户软件将插头插到不同编号的插座（ip和端口），就可以得到不同的服务。
* 如果一个进程要通过网络向另一个进程发送数据，只需简单地写入与socket相关联的输出流。一个进程通过从与socket相关联的输入流读来读取另一个进程所写的数据。

## 客户端/服务器模型

* 即Client/Server(客户机/服务器)结构，通过任务合理分配到Client端和Server端，降低了系统通讯开销，可充分利用两端硬件环境优势。
* C/S的优点是能充分发挥客户端ＰＣ的处理能力，很多工作可以在客户端处理后再提交给服务器。

## TCP通信编程

### java.net.Socket类：客户端

* Socket类实现**客户端**的套接字，套接字是两台计算机之间通信的端点。
  * 客户端和网络端进行交互必须使用Socket中提供的网络流，不能使用自己创建的流对象。
  * 当我们创建客户端对象Socket的时候，就会去请求服务器，和服务器经过3次握手建立连接通信。
* 构造方法：`Socket(String host, int port)`,创建一个流套接字并将其连接到指定主机的指定端口号；
  * String host：服务器的主机地址/服务器的IP地址
  * int port：服务器的端口号；
* `OutputStream getOutputStream()`:返回此套接字的输出流
* `InputStream getInputStream()`:返回此套接字的输入流
* `void close()`：关闭此套接字；

### java.net.ServerSocket类：服务器端

* ServerSocket类表示服务器端的套接字。
* 构造方法；`ServerSocket(int port)`:创建指定端口号的ServerSocket对象；
* `Socket accept()`；侦听并接受请求此ServerSocket对象的Socket对象。
* `void close()`：关闭此套接字。

### Internet寻址(InetAddress类和URL类)

* InetAddress类提供有关从域名地址查询IP地址的方法，类中没有构造器。TCP/IP面向连接服务类(Socket类和ServerSocket类)。
* getAddress() ：返回4个字节的IP地址，是4个整数，有可能是负数，需要进行转换处理，才能正确显示。
* getHostName()：返回被查询**主机域名地址**，即生成InetAddress对象时使用的字符串参数。

## TCP通信程序

### 概述

* TCP通信能够实现两台计算机之间的数据交互，通信的两端要严格区分为**客户端（client）与服务器端（server）**。
* TCP通信步骤：
  1.  服务器端先启动：不会主动请求客户端；
  2.  客户端请求服务器端，建立逻辑连接；
* 客户端和服务器端的逻辑连接中包含一个IO对象（字节流类型对象），客户端和服务器端使用IO对象进行通信。
* **多个客户端**可以同时与**一个服务器**进行交互。在服务器端通过**accept方法**来获取发送请求的客户端对象。**服务器端没有IO流**，服务器获取客户端Socket对象，使用每个客户端Socket对象中提供的IO流和客户端进行交互。即服务器端不会新建IO流，而是直接借用客户端的IO流来与客户端交互。
* 服务器端与客户端交互模型：
![IMG_2180](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/IMG_2180.JPG)

### TCP通信程序实现

# 集合

## 集合概述

* 集合是java提供的一种容器，可以用来存储多个数据。
* 集合与数组的区别：
  * 集合的长度是可变的；
  * 集合存储的都是**对象**，而且对象的类型可以不一致。

## 单列集合的框架

![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201204142553.png) 
* List集合:有存储顺序，可以存储重复元素，有索引；
  * ArrayList：底层是**数组**实现的，查询快，增删慢；
  * LinkedList:底层是**链表**实现的，查询慢，增删快。
* Set集合：无索引，不可存储重复元素，存取无序。
  * HashSet:底层是**哈希表+红黑树**实现的，无索引，不可以存储重复元素，存取无序。
  * LinkedList：底层是**哈希表+链表**实现的，无索引，不可以存储重复元素，但可以包装存储**顺序**。
  * TreeSet:底层是**二叉树**实现，一般用于**排序**。

## Collection接口

* 全称：`java.util.Collection`,是所有单列集合最顶层的接口，定义了所有单列集合共性的方法。
* 常利用**多态**创建Collection对象,如：`Collection<String> coll = new ArrayList<>();`,即上层接口对象指向下层实现类。
* 直接打印Collection对象，会打印出集合中所有的元素。如：`System.out.println(coll);`空集会打印一个`[]`,一般会打印`[张三, 李四]`
* `public boolean add(E e);`：把给定的对象添加到当前集合中去,添加成功返回true。
```java
coll.add("张三");
```
* `public boolean remove(E e);`:把给定的对象在当前集合中删除，如果要删除的元素存在则返回true。
```java
coll.remove("张三");
```
* `public boolean contains(E e);`:判断当前集合中是否包含指定的对象，包含则返回true。
```java
coll.contains("张三");
```
* `public boolean isEmpty();`:判断当前集合是否为空，为空则返回true。
```java
coll.isEmpty();
```
* `public int size();`:返回集合中的元素个数
```java
int size = coll.size();
```
* `public Object[] toArray();`:把集合中的元素存储到**数组**中。注意返回值是**Object**类型的数组。
```java
Object[] arr = coll.toArray();
for(Object i : arr) {
    System.out.println(i);
}
```
* `public void clear();`:清空集合中所有的元素，此时集合变回和刚创建时一样。
```java
coll.clear();
```
## Iterator接口

* 迭代：获取Collection集合中元素的通用方法。
* 全称:`java.util.Iterator`接口，即实现迭代的迭代器。
* `boolean hasNext()`:判断集合中还有没有下一个元素，有则返回true。
* `E next()`；取出集合中的下一个元素。
* 获取Iterator接口的实现类对象方法：
  * 实现Collection接口的实现类对象调用`iterator()`方法，这个方法返回值就是Iterator接口的实现类对象。
  * `Iterator<E> iterator();`：迭代器的泛型跟随Collection对象的泛型。
* 迭代器的使用步骤：
  1. 使用集合中的iterator方法获取迭代器的实现类对象，使用Iterator接口对象来接收（同Collection对象一样利用多态）；
  2. 使用Iterator中的hasNext方法来判断是否还有下一个元素
  3. 使用Iterator中的next方法取出集合中的下一个元素。
* 迭代器理解：
  * 可以将集合想象为一个表，迭代器中有一个游标指向当前行，开始时这个游标是指向表头的。每取一次下一个元素，这个游标都会移向下一行（即取出了元素这一行）。
  * 如果试图取出没有元素的行的值，会出现NoSuchElementException异常。
```java
Collection<String> coll = new ArrayList<>();
coll.add("张三");
coll.add("李四");
Iterator<String> it = coll.iterator(); //多态的使用
while(it.hasNext()) {
    System.out.println(it.next());
}
```
* 增强for循环：又称for-each循环，是jdk1.5以后，java提供的利用迭代器原理的新特性。用来遍历数组或者集合。(只可以用来遍历，不能用来修改集合或者数组)。
```java
for(Object i : coll) {
    System.out.println(i);
}
```

# Java的API
---
实在是太多了，可以在需要时查看API的[官方文档](https://docs.oracle.com/en/java/javase/15/docs/api/index.html)或者[在线中文文档](https://tool.oschina.net/apidocs/apidoc?api=jdk-zh)

## java.lang.Object

* `Class getClass()`:返回包含对象信息的类对象。
* `boolean equals(Object otherObject)`:比较两个对象是否相等。
* `String toString()`:返回表示该对象值的字符串。

## java.lang.Class

* `String getName()`:返回这个类的名字；
* `Class getSuperclass()`:以Class对象的的形式返回这个类的超类。
* `public static Class<T> forName(String className) throws ClassNotFoundException`:返回与带有给定字符串名的类或接口相关联的 Class 对象。调用此方法等效于：`Class.forName(className, true, currentLoader)`其中 currentLoader 表示当前类的定义类加载器。
  * 例如，以下代码片段返回命名为 java.lang.Thread 的类的运行时 Class 描述符。`Class t = Class.forName("java.lang.Thread")`。参数：className - 所需类的完全限定名。返回：具有指定名的类的 Class 对象。抛出：LinkageError - 如果链接失败;ExceptionInInitializerError - 如果此方法所激发的初始化失败;ClassNotFoundException - 如果无法定位该类。
* `public String toString()`:将对象转换为字符串。字符串的表示形式为字符串 "class" 或 "interface" 后面紧跟一个空格，然后是该类的完全限定名，它具有 getName 返回的那种格式。如果此 Class 对象表示一个基本类型，则此方法返回该基本类型的名称。如果该 Class 对象表示 void，则此方法返回 "void"。覆盖：类 Object 中的 toString;返回：表示此 class 对象的字符串。


## java.util.Objects

* 可以看做是Object类针对多个对象进行处理的类似的类。
* 判断两对象是否相等的方法：`static boolean equals(Object a, Object b)`。
* 提供散列码的方法（hashcode）:
  * `static int hash(Object...objects)`，返回一个散列码，由提供的所有对象的散列码组合得到。
  * `static int hashCOde(Object a)`如果a为null返回0，否则返回a.hashCode()的结果（即Object类中的hashCode方法增加了对null的处理）
* 判断对象是否为空并抛出异常的方法：
```java
public static <T> T requireNonNull(T obj,String message) {
    if (obj == null) {
        throw new NullPointerException(message);
    }
    return obj;
}
```

## java.util.List

## Math

## java.time.LocalDate

## java.util.Random

* `Random()`返回一个随机数生成器（对应的类的对象）
* `int nextInt(int n)`，返回一个0~n-1之间的随机数的方法。

## java.util.ArrayList<E> （泛型数组列表）

* ArrayList类类似于数组，但是它能在添加或者删除元素时，**自动调整数组的容量**。
* ArrayList是有**类型参数**的**泛型类**（所以又被称为**泛型数组列表**），泛型类是可以在使用时再声明具体类型的。使用时为了指明数组保存的元素的类型，可以使用一对尖括号`<>`来将具体的类名追加到ArrayList后面。如`Array<Employee> staff = new ArrayList<Employee>();`.在这种方式中可以去掉后一个尖括号中的类型`Array<Employee> staff = new ArrayList<>(可以在这里填数组列表的容量,也可以不写。即使写了容量，这个容量仍是可以在使用时动态改变的);`,这种使用空的尖括号的方式又被称为**菱形语法**。
* 使用`boolean add(E obj)`方法可以将元素**加到数组列表**中，如`staff.add(i)`.`void add(int index, E obj)`。可以在数组内部指定位置插入元素，并使后面的元素后移，同时数组长度加一。
* `int size()`方法返回数组列表中现在的元素个数。如`length = staff.size()`.
* 不能使用普通的`[下标]`来访问数组列表中的元素。使用`get()`方法来访问数组列表中第i个元素，如`value = staff.get(i)`。使用`set()`方法来对第i个元素进行赋值。如`staff.set(i, value)`.
* `remove()`方法删除指定位置的元素，并将之后的所有元素前移，返回所删除的元素。如`staff.remove(i)`.
* `void ensureCapacity(int capacity)`方法可以给数组分配一个有具体数量的数组空间如：`staff.ensure(100)`。与直接在创建数组列表时声明大小的效果一样。
* 遍历：泛型数组列表同样可以用`for each`循环来遍历每个元素。


## java.util.Stack<E>

* 这是java用于实现栈功能的类。
* 初始化一个空栈：`Stack<Interger> stack1 = new Stack<Interger>();`,因为栈也是使用泛型的，所以初始化的时候需要**指定类型**。
* 判断栈是否为空:`stack1.empty()`,方法`boolean empty()`
* 查看栈顶部的元素但不取出：`stack1.peek()`,方法`Object peek()`，Object是指返回值的类型是由初始化的时候指定的，具体为哪种并不确定。
* 进栈：`stack1.push(Object element);`，方法`Object push(Object element)`
* 出栈：`stack1.pop();`,方法`Object pop()`.
* 返回某个值在堆栈中的位置从，位置是从1开始的：方法`int search(Object element)`.

## java.util.Arrays

* 这是一个主要用来对数组进行处理的类。
* 判断两个数组是否相等的静态方法：`static boolean equals(xxx[] a, xxx[] b)`.如果两个数组长度相同且对应位置的元素相等，则返回true，否则返回false。
* 打印数组:`Arrays.toString(数组名)`。结果是一个形如"[1, 2, 3, 4]"的字符串。打印多维数组需要调用`Arrays.deepToString(数组名)`。
* 拷贝数组`数组名1.toArray(数组名2)`。将数组1的数组元素拷贝到数组2中去。
* `static void sort(Object[] a)`:对数组a中的元素进行排序，要求数组中的元素必须实现了Comparable接口的类，并且元素之间必须是可以比较的。

## java.lang.Integer

* `int intValue()`:将该Integer的对象的值作为一个int返回。
* `static String toString(int i)`:返回一个String对象，表示指定数值的十进制表示。
* `static String toString(int i, int radix)`：返回数值i基于radix参数指定进制的表示。
* `static int parseInt(String s)`：将字符串转换为十进制整型返回。
* `static int pardeInt(String s, int radix)：将字符串转换为radix进制整型返回。
* `static Integer valueOf(String s)`:返回一个Integer对象，用s表示的十进制整数初始化。
* `static Integer valueOf(String s, int radix)`：返回一个Integer对象，用s表示的radix进制数初始化。
* `static int compare(int x, int y)`:对两个整数进行比较，如果x < y,返回负整数，如果x=y返回0， x > y返回一个正整数。


## java.lang.Enum<E>

* `static Enum valueOf(Class enumClass, String name)`：返回给定类中指定名字的枚举常量。
* `String toString()`:返回枚举常量名。如`Size.SMALL.toString()`返回字符串"SMALL".
* `int ordinal()`：返回枚举常量在enum中声明的位置，位置从0开始计数。
* `int compareTO(E other)`:如果枚举常量出现在other之前，返回一个负整数；如果this==other，返回0； 其他情况返回一个正整数。

## java.lang.Comparable<T>

* 这是一个接口。其中要求了`int comparable(T other)`方法。这个方法要求对象小于other返回一个负整数，相等返回0，大于返回正整数。

## java.lang.Throwable(异常)

* `Thorwable()`:构造一个Throwable对象但是没有详细的描述信息
* `Throwable(String message)`: 构造一个Throwable对象，带有指定的详细描述信息。
* `String getMessage()`:获得Throwable对象的简短描述信息。
* `vois PrintStackTrace()`:jvm打印堆栈轨迹（异常的信息），是最全面的异常信息（包括类名，方法名及所在程序的行数等）。
* `String toString();`返回此异常的描述消息字符串。


## java.uitl.Properties(配置文件)

* Properties类继承自Hashtable类并且实现了Map接口。该类主要用于**读取Java**的配置文件，不同的编程语言有自己所支持的配置文件，配置文件中很多变量是经常改变的，为了方便用户的配置，能让用户够脱离程序本身去修改相关的变量设置。就像在Java中，其配置文件常为`.properties`文件，是以键值对的形式进行参数配置的。
* 层次关系：
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201206084813.jpg)
* 字段：`protected Properties defaults`,一个属性列表，包含属性列表中所有**未找到值**的键的默认值。
* 构造方法：
  * `public Properties()`:创建一个无默认值的空属性列表。
  * `public Properties(Properties defaults)`:创建一个带有指定默认值的空属性列表。参数：defaults - 默认值。
* 方法：
  * `public String getProperty(String key)`:用指定的键在此属性列表中搜索属性。如果在此属性列表中未找到该键，则接着递归检查默认属性列表及其默认值。如果未找到属性，则此方法返回 null。参数：key - 属性键。
  * `public String getProperty(String key,String defaultValue)`:用指定的键在属性列表中搜索属性。如果在属性列表中未找到该键，则接着递归检查默认属性列表及其默认值。如果未找到属性，则此方法返回**默认值变量**。参数：key - 哈希表defaultValue - 默认值。返回：属性列表中具有指定键值的值。
  * `public void load(Reader reader)throws IOException`:按简单的面向行的格式从输入字符流中读取属性列表（键和元素对）此方法返回后，指定的**流仍保持打开状态**。参数：reader - 输入字符流(Reader是FileReader的上两级父类）。抛出：IOException - 如果从输入流读取时发生错误。IllegalArgumentException - 如果输入中出现了错误的 Unicode 转义。。

## java.net.URL

* 类 URL 代表一个统一资源定位符，它是指向互联网“资源”的指针。资源可以是简单的文件或目录，也可以是对更为复杂的对象的引用，例如对数据库或搜索引擎的查询。
*` http://www.socs.uts.edu.au:80/MosaicDocs-old/url-primer.html`
* 通常，URL 可分成几个部分。上面的 URL 示例指示使用的**协议**为 http （超文本传输协议）并且该信息驻留在一台名为 www.socs.uts.edu.au 的**主机**上。主机上的信息名称为 /MosaicDocs-old/url-primer.html。主机上此名称的准确含义取决于协议和主机。该信息一般存储在文件中，但可以随时生成。该 URL 的这一部分称为**路径**部分。URL 可选择指定一个“**端口**”，它是用于建立到远程主机 TCP 连接的端口号。如果未指定该端口号，则使用协议默认的端口。
* `public String getPath()`:获取此 URL 的路径部分。返回：此 URL 的路径部分，如果没有路径，则返回一个空字符串









