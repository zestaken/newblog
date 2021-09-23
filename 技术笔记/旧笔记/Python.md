---
title: Python
date: 2020-09-05 20:45:19
tags: Python
categories: 技术笔记
---
# python概述

1. Python是一门解释性语言，无需编译和链接。
2. Python解释器是可以交互的，因此Python甚至可以用作桌面计算器。
3. Python是可扩展的，可以为Python的解释器添加内置函数和模块。
4. 本文学习的是Python3.


## 解释器

* trackback:程序无法成功地运行时，解释器会提供一个traceback。traceback是一条记录，指出了解释器尝试运行代码时， 在什么地方陷入了困境。

## Python编写规范

[PEP8编写规范](https://python.freelycode.com/contribution/detail/47)
* **python之禅**：Python社区的理念都包含在TimPeters撰写的“Python之禅”中。要获悉这些有关编写优秀Python代码的指导原则，只需在解释器中执行命令`import this`。
* **Python改进提案（Python Enhancement Proposal，PEP）**。PEP 8是最古老的PEP之一，它向Python程序员提供了代码格式设置指南。PEP 8的篇幅很长，但大都与复杂的编码结构相关。
* **缩进**：每级缩进都使用四个空格;
  * 如果你混合使用了制表符和空格，可将文件中所有的**制表符转换为空格**
* **空格**：
  * 在诸如== 、>= 和<= 等**比较运算符**两边各添加一个空格，例如，`if age < 4:` 要比`if age<4:`好。
## python的注释

* Python中使用`#`做注释符，即使是在解释器的交互编程中也可以使用`#`注释符。

# 变量和数据类型(结构)

## 变量

* 变量名只能包含**字母、数字和下划线**。变量名可以以字母或下划线打头，但不能以数字打头，例如，可将变量命名为message_1，但不能将其命名为1_message。
*  变量名不能包含空格，但可使用下划线来分隔其中的单词。例如，变量名greeting_message可行，但变量名greeting message会引发错误。 
* 不要将Python关键字和函数名用作变量名，即不要使用Python保留用于特殊用途的单词，如print。
*  变量名应既简短又具有描述性。例如，name比n好，student_name比s_n好，name_length比length_of_persons_name好。 慎用小写字母l和大写字母O，因为它们可能被人错看成数字1和0。
* 就目前而言，应使用小写的Python变量名。在变量名中使用大写字母虽然不会导致错误，但避免使用大写字母是个不错的主意。

## 一：数字

* 常用的数据类型有int、float，但是Python还有其它很多数据类型。例如复数类型（complex）,这种类型中使用j或者J代表复数部分，如`5+2j`.
* 基本运算
  * Python支持基本的`+`,`-`,`*`以及`/`，`%`（求模/余）的运算，但是和c语言不同的是，`/`默认的结果是浮点型。使用`//`可以做返回值为int型的除法运算（类似c语言的普通除法运算）.
  * Python还支持**幂方运算**`**`,`5**2`表示$5^2$；
  * python仍旧使用`=`为变量赋值，同c语言一样，Python中的变量在使用前必须先进行赋值。（但是在交互式编程中，Python内置了一个变量`_`,存储最近一个表达式的运算结果，这个变量无需赋值，便可直接使用，事实上，`_`是一个只读变量，对它赋值是没有意义的。）
  * python的赋值可以进行多重赋值，例如：`a, b = 0, 1`相当于`a = 0, b = 1`
  * 在字符串中使用**数值类型时**，需要显式地指出你希望Python将这个整数用作字符串。为此，可调用函数`str()` ， 它让Python将非字符串值表示为字符串,如：`print("age:"+str(19))`

## 二：字符串

* 字符串可以用单引号`''`或者双引号`""`表示;
    * 灵活地使用单引号和双引号来使字符串中可以**嵌套引号和撇号**，如：
      * `'I told my friend, "Python is my favorite language!"'`
      * `"The language 'Python' is named after Monty Python, not the snake." `
      * `"One of Python's strengths is its diverse and supportive community."`
* 引号可以用`\`来转义。
* 字符串可以用`+`连接，如`'zhang'+'jie'`合为`'zhangjie'`.
* 字符串可以用`*`重复输出。`'zj'*3`即为`'zjzjzj'`。
* 字符串可以被索引，进行类似c语言的取出字符串的中字母的操作。如：`word = 'python`, `word[0] == 'p'`;
  * **索引可以为负数**，这会从右边开始索引。如：`word = 'python'`,`word[-1] = 'n'`(负索引是从-1开始，而不是0)。
* 字符串可以被切片。如`word = 'python'`,则`word[0:2] = 'py'`,包含起始的字符，不包含末尾的字符。切片可以省略，省略左边，默认为从0开始，省略右边，则默认到末尾。如`word[:3] = 'pyt'`,`word[1:] = 'ython'`。
* 字符串**是不可变的**，不可以被修改。如果需要修改，只能创建一个新的字符串，并在创建的过程中，将修改加进去。
* 制表符`\t`:添加四个空格，如：`print("\tPython")`
* 换行符`\n`:换到下一行。


### 字符串对象方法

* `title()`:以首字母大写的方式显示每个单词，即将每个单词的首字母都改为大写。其中以空格区分单词。
* `upper()`:将字符串改为全部大写
* `lower()`:将字符串改为全部小写,存储数据时，方法lower() 很有用。很多时候，你无法依靠用户来提供正确的大小写，因此需要将字符串先转换为小写，再存储它们。以后需要显示这些信息时，再将其转换为 最合适的大小写方式。
* 删除空白的方法：
  * `rstrip()`:删除字符串末尾的空白,只是输出的结果中没有了空白，要想将字符串变量中的空白永久删除，需要将结果重新赋值给字符串变量。
  * `lstrip()`:删除字符串开头的空白。
  * `strip()`:同时删除字符串两端的空白。
* 

## 三：列表

### 列表的基本性质

* 列表由一系列按**特定顺序排列的元素组成**。你可以创建包含字母表中所有字母、数字0~9或所有家庭成员姓名的列表；也可以将任何东西加入列表中，其中的元素之间可以没有任何关系。鉴于列表通常包含多个元素，给列表指定一个表示复数的名称（如letters 、digits 或names ）是个不错的主意。
* 用方括号`[]`来表示列表，并用逗号来分隔其中的元素。
* 列表类似c语言的数组。但是列表中的元素不需要是同一类型。
* 列表的赋值：如`list1 = [1, 2, 3, 4, 5]`， `list2 = [1, 'a', 2, 'b', 3,[1, 2,"zhangjie"]]`,`list3 = []`。
* 访问列表元素：类似数组，通过**索引**来访问列表中的元素。如：`list1[0]`访问的元素是1。
  * 最后一个列表元素可以通过索引-1来访问，如：`list1[-1]`访问的是5。
  * 用负数索引，是从-1开始从右往左依次索引。
* 同字符串一样，列表也可以被切片和索引。
  * 可以**通过切片来复制一个列表**，如：`list2 = list1[:]`,如果直接赋值，引用还是同一个列表：`list2 = list1`，两个列表名其实是一个列表的两个不同名字。
* 同字符串一样，列表也支持使用`+`进行连接,使用`*`进行重复。但是连接和重复的对象都必须是列表，返回的结果也是列表。
* 与字符串不同的是，列表中的元素是**可以修改**的。可以利用赋值操作对列表中某个元素进行修改。
* 可以使用append（）方法，在列表后面加元素。(括号中放需要加的元素。)
* 列表中可以嵌套列表。
* 使用函数`len()`，可以得到列表或者字符串的长度。如；`len(list1)`的结果为5.
* 可以使用函数`enumerate(序列)`来同时得到元素的值和索引值。

### 列表中常用的方法


* `list.append（x）`在列表的后面加上一个元素x。
* `list.extend(L)`将一个给定列表（L）中的元素全部都加到另一个列表里（list）.
* `list.insert(i, x)`在列表的指定元素之前插入元素。其中第一个参数是指定列表中元素的索引，第二个参数是要插入的值。如，`list.insert(0, 5)`是在整个列表前插入一个5.
* `list.remove(x)`删除列表中值为x的第一个元素，如果没有这样的元素，则会返回错误。
* `list.pop([i])`类似栈里面的弹出，会删除列表里指定元素并返回它的值。如果**没有指定参数**则默认对列表的最后一个元素进行操作。（另外，参数用方括号[]括起来是表示，这个参数是可以有也可以没有的）。结合`append`方法和`pop`方法可以将列表当做一个栈使用。
* `list.clear()`删除列表中的所有元素，此时打印列表只剩下一对空的方括号。
* `list.index(x)`返回列表中第一个值为x的元素的索引，如果不存在这样的元素，则返回一个错误。
* `list.count(x)`返回值x在列表中出现的次数。
* `list.sort()`会对列表中的元素进行永久性正向排序（即按字母顺序从小到大）;
  * 传递参数`reverse=True`可以对元素进行永久性反向排序（按字母顺序从大到小），如：`list.sort(reverse=True)`
* `sorted(list)`对列表中的元素进行临时正向排序，排序规则与sort方法相同，如：`print(sorted(list))`
* `list.reverse()`会对列表中的元素进行逆序排列，不是指按与字母顺序相反的顺序排列列表元素，而只是反转列表元素的排列顺序。
* `list.copy()`会返回一个列表的拷贝。

### 列表的应用

* 当作栈使用。
* 当作队列使用。需要引入collections.deque方法。

### 列表解析

* 列表可以由公式推出其中的元素。列表推导式由包含一个表达式的方括号组成，**表达式用于存储要存入列表的结果**，表达式后面跟随一个 for 子句，之后可以有零或多个 for 或 if 子句。结果是一个列表，由表达式依据其后面的 for 和 if 子句上下文计算而来的结果构成。
* 例如： `[(x, y) for x in [1,2,3] for y in [3,1,4] if x != y]`,它的结果为`[(1, 3), (1, 4), (2, 3), (2, 1), (2, 4), (3, 1), (3, 4)]`。

### del语句

* del语句可以**根据索引删除列表中的元素。del语句也可以删除列表中的切片**。例如：`del list[1]`以及`del list[2:4]`,`del list[:]`。
* del语句还可以删除整个变量，`del list`.在del删除整个变量后，再次调用该变量会报错，除非再次这个变量赋值。

### range()函数

* range()会生成一个指定长度的链表，例如：`range(10),会生成一个**从0到9**的序列，即参数10是指定生成的链表的长度，默认情况下，这个等差链表是从0开始的。
* 可以指定range生成的链表的起始和结束，例如：`range(5, 10)`会生成一个从5到9的链表。
* 可以指定链表的步长（即公差，可以为负数）。例如：`range(0, 10, 3)中的第一个参数是起始的数，结束的数为10（永远不会包含指定的结束数），3为步长`,结果为0， 3， 6， 9。可以把步长设置为负数，形成从大到小的链表。例如`range(-10, -100, -30)`,结果-10，-40，-70。

## 四：元组

* 元组与列表有很多地方类似的，但是元组是用**圆括号来定义**的。
* 元组和字符串一样是**不可变**的，一般是通过索引来访问其中的元素值。
  * 虽然不能修改元组的元素，但可以给存储元组的变量赋值。如：
  ```python
  dimensions=(1,2)
  dimensions=(3,4)
  ```
* 元组中的元素可以是**不同类型**的，但是列表中的元素必须是**同一类型**的。
* 元组的定义可以省掉括号，但最好带上。
* 元组的常见形式， `t = 12345, 'hello'`，`t = (1, 3 ,"zhangjie")`

## 五：集合（set）

* 集合同数学中的定义一样，是一个无序不重复的集。集合中的元素可以是不同的类型的。如果在给集合定义或者复制时，有重复的元素，并不会报错，但是Python会自动删除集合中重复的元素，最终只留下一个元素。
* 可以使用花括号`{}`或者`set()`函数来创建集合。例如：`a = {1, 'hello'}`或者`a = set('abcdefg')`。使用set必须有单引号`''`或者双引号`""`，并且set会把被引号包围的任何东西拆分，并按照随机顺序存入集合中。（定义空集合只能使用set(),而不能使用花括号｛｝）
* 集合也可类似列表使用推导式来定义其内的元素。
* 集合中可以使用`&并，^交，-, |`等数学集合运算。

## 六：字典

* 字典与列表不同，它是根据事先存好的的关键字来索引具体元素的。就像真实的字典一样,在Python中，字典是一系列**键—值对** 。每个键都与一个值相关联,形如`key:value`，你可以使用键来访问与之相关联的值。与键相关联的值可以是数字、字符串、列表乃至字典。事实上，可将任何Python对象用作字典中的值。
* 使用**花括号**来创建字典。例如；`dict = {'key1':1, 'key2': 2}`。一对空的花括号可以创建一个空的字典。
* 可以直接给字典**增加关键字和对应的值**:`dict['key3'] = 3`.要**修改字典中的值**，可依次指定字典名、用方括号括起的键以及与该键相关联的新值。
* 键—值对是两个相关联的值。指定键时，Python将返回与之相关联的值。键和值之间用**冒号**分隔，而键—值对之间用**逗号**分隔。在字典中，你想存储多少个键—值对都可以。
* 要获取与键相关联的值，可依次指定字典名和放在方括号内的键,如：`print(dict['key1'])`。
* 注意，键—值对的**排列顺序与添加顺序不同**。Python不关心键—值对的添加顺序，而只关心键和值之间的关联关系。
* 对于字典中不再需要的信息，可使用**del 语句将相应的键—值对彻底删除**。使用del 语句时，必须指定字典名和要删除的键。如：`del dict['key1']`.
* 使用关键字`in`可以确认某个关键字是否在字典中。例如；`'key1' in dict`,如果'key1'存在的话返回True，否则返回False。（类似的还有`'key1' not in dict`的写法）、
* 可以使用方法`字典名.items()`**同时读出字典中的关键字和值**（常用在循环中遍历字典）。
* 使用方法 `字典名.keys()`可以返回一个字典中的**全部关键字**。
* 可使用方法`values() `，它**返回一个值列表，而不包含任何键**。
* 遍历循环示例：
```python
# 遍历键值对
for key, value in dict.items():
    print("key = " + key)
    print("value = " + value)

# 只遍历键
for key in dict.keys():
    print("key = "  + key)
```
* 可使用函数`sorted()`来获得按特定顺序排列(特定顺序是指按字母的大小写顺序等默认的顺序)的键列表的副本,如：`for name in sorted(dict.keys()):`
* dic()函数可以从值对列表中创建字典。
* 通过对包含重复元素的列表调用`set()`方法 ，可让Python找出列表中**独一无二的元素，并使用这些元素来创建一个集合**。如：`for value in set(dict.values()):`
* 字典推导式也是同样可以使用的。
* 我们将较大的字典放在多行中:
```python
favorite_languages = {
    'jen': 'python',
    'sarah': 'c',
    'edward': 'ruby',
    'phil': 'python',
    }
```
* 字典和列表等可以相互嵌套，如：
```python
# 列表中嵌套字典
alien_0 = {'color': 'green', 'points': 5}
alien_1 = {'color': 'yellow', 'points': 10}
alien_2 = {'color': 'red', 'points': 15}
aliens = [alien_0, alien_1, alien_2]

# 在字典中嵌套列表
pizza = {
'crust': 'thick',
'toppings': ['mushrooms', 'extra cheese'],
}

# 在字典中嵌套字典
users = {
'aeinstein': {
'first': 'albert',
'last': 'einstein',
'location': 'princeton',
},
'mcurie': {
'first': 'marie',
'last': 'curie',
'location': 'paris',
},
}
```


# python的流程控制

## if语句

* 类似c语言中的if语句，但是python**省去了条件外面的括号**，而是**用冒号`:`来表示**（就像c语言不能忘记括号一样，python也不能忘记冒号），**并将`else if`合成了一个`elif`关键字**（但是还是有单独的`else`)。如：
```python
 x = (int)(input()) #input读取键盘的输入，但是默认读入的类型是字符串型，需要强制转换为数字整型
 
 if x < 0:
         x = 0
         print(x)
 elif x == 0:
         x = -1
         print(x)
 else:
         x = 1
        print(x)
```

* python条件语句内部的语句不是用花括号`{}`来括起来，而是直接**用缩进来表示包含关系**。所以这种带有控制语句的代码最好不直接在解释器中写。
* **条件测试（布尔表达式）**：每条if 语句的核心都是一个值为True 或False 的表达式，这种表达式被称为条件测试。
  * 条件表达式(布尔表达式)的结果要么为True ，要么为False 。
* if语句判断列表是否为空：`if list:`,如果列表是空的，相当于条件为false，如果为非空，则相当于条件为true。


## for语句

* 与c语言的for循环不同的是，python的for循环没有控制条件，它自动停止的。例如
```python 
words = ['zhang', 'jie']

for word in words:
  print(word)
```
* for循环常用来遍历列表或者字符串等序列，循环会在序列尾部自动结束。而在示例程序中的`word`变量是定义的列表序列中的单个元素。注意`for...in...`的格式。

* for语句的末尾有一个**冒号**。

* 循环中的语句依靠**缩进**来识别。

* python 循环高级用法 `[expression for x in X [if condition] for y in Y [if condition] ... for n in N [if condition] ]`按照从左至右的顺序，分别是外层循环到内层循环:

  * `expression`是满足循环到满足if条件时返回的值,可以使用当前遍历得到的值，也可以使用完全的表达式，如常值等。
  * 我们可以在 `for` 语句后面跟上一个 `if` 判断语句，用于过滤掉那些不满足条件的结果项。

  ```python
  L = [x for x in L if x % 2 != 0]
  [1, 3, 5]
  ```

  

  * 在复杂一点的列表推导式中，可以嵌套有多个 `for` 语句。按照从左至右的顺序，分别是外层循环到内层循环。

  ```python
  [x + y for x in 'ab' for y in 'jk']
  ['aj', 'ak', 'bj', 'bk']
  ```

  * 列表推导式可以带任意数量的嵌套 `for` 循环，并且每一个 `for` 循环后面都有可选的 `if` 语句。

  ```python
  [ expression for x in X [if condition]
               for y in Y [if condition]
               ...
               for n in N [if condition] ]
  ```

  

## while循环

* for 循环用于针对**集合中的每个元素**，而while 循环不断地运行，直到**指定的条件不满足**为止。示例：
```python
current_number = 1
while current_number <= 5:
    print(current_number)
    current_number += 1
```

## break和continue语句

* break 语句用于控制程序流程，可使用它来控制哪些代码行将执行，哪些代码行不执行，从而让程序按你的要求执行你要执行的代码。
* 示例：
```python
>>> for n in range(2, 10):
...     for x in range(2, n):#第一次循环，range（2,2)是没有值的,解释器不会输出任何内容
...         if n % x == 0:
...             print(n, 'equals', x, '*', n//x)
...             break
...     else:
...         # loop fell through without finding a factor
...         print(n, 'is a prime number')
...
2 is a prime number
3 is a prime number
4 equals 2 * 2
5 is a prime number
6 equals 2 * 3
7 is a prime number
8 equals 2 * 4
9 equals 3 * 3
```
* 要**返回到循环开头**，并根据条件测试结果决定是否继续执行循环，可使用continue 语句，它不像break 语句那样不再执行余下的代码并退出整个循环。continue语句与c语言中基本一样，它表示跳过continue语句中后面所有的当前循环的语句（到循环尾），从而直接进入下一次迭代。示例：
```python
>>> for num in range(2, 10):
...     if num % 2 == 0:
...         print("Found an even number", num)
...         continue
...     print("Found a number", num)
Found an even number 2
Found a number 3
Found an even number 4
Found a number 5
Found an even number 6
Found a number 7
Found an even number 8
Found a number 9
```

## pass语句

* pass语句就像它的名字一样，读到它时，什么也不做，直接过去就行。

## 条件控制

### in 和 not in

* in和not in用来判断某个值是否在某个区间内（如列表）。
* **检查特定值是否在列表中**:要判断特定的值是否已包含在列表中，可使用**关键字in**,如：`if user in users:`,也可以判断特定值不再列表中，如：`if user not in users:`
### is 和 not is

* is和not is用来判断两个比较对象是否相同。

### 大于等于以及判等

* python中支持连续比较，即像普通比较一样可以连续的比较多个值的关系。例如：`a < b == c`可以判断b是否大于a的同时等于c。
### 逻辑操作符

* python中的逻辑操作符包括`and, not, or`。
* **or**的示例：`x or y`,如果x为真则返回x，否则返回y。
* **and**的示例：`x and y`,如果x的值为假，则不再看y的值，返回（false），如果x的值为真，则返回y的值。（and和or都是从左向右解析，and返回值的条件是遇见假的变量或或者到末尾， 而or返回的条件是遇见真或者到末尾。
* **not**的示例：`not x`,not只有True和False两种返回值。如果x为真，则not使其取反，结果为False，反之亦然。

### 序列的比较

* 条件比较`>, < ,==`可以用于比较序列（如列表，字符串，字典）。
* 比较规则：首先比较前两个元素，如果不同，就决定了比较的结果；如果相同，就比较后两个元素，依此类推，直到所有序列都完成比较。如果两个元素本身就是同样类 型的序列，就递归字典序比较。如果两个序列的所有子项都相等，就认为序列相等。如果一个序列是另一个序列的初始子序列，较短的一个序列就小于另一个。字符 串的字典序按照单字符的 ASCII 顺序。

# 函数

* 函数是带名字的代码块，用于完成具体的工作。
* 重构：将代码划分为一系列完成具体工作的函数。这样的过程被称为**重构**。重构让代码更清晰、更易于理解、更容易扩展。
* 函数编写规范：
  * 应给函数指定描述性名称，且只在其中使用**小写字母和下划线**;
  * 每个函数都应包含简要地阐述其功能的注释，该注释应紧跟在函数定义后面，并采用文档字符串格式；
  * 给形参指定默认值时以及函数调用中的关键字实参，**等号两边不要有空格**；
  * 如果程序或模块包含多个函数，可使用**两个空行将相邻的函数分开**，这样将更容易知道前一个函数在什么地方结束，下一个函数从什么地方开始。
  * 所有的**import 语句都应放在文件开头**，唯一例外的情形是，在文件开头使用了注释来描述整个程序。
  * 如果形参很多，导致函数定义的长度超过了79字符，可在函数定义中输入左括号后按回车键，并在下一行按两次Tab键，从而将形参列表和只缩进一层的函数体区分开来。如：
  ```python
  def function_name(
        parameter_0, parameter_1, parameter_2,
        parameter_3, parameter_4, parameter_5):
    function body...
  ```
## 函数定义

* 使用`def`定义函数，`def`后接函数的名字，以及她的形参列表，最后就像大多数功能语句一样，以`:`开启函数体。
* 函数体的书写必须必须**全部相对定义缩进**。示例：

```python
>>> def fib(n):    # write Fibonacci series up to n
...     """Print a Fibonacci series up to n."""
...     a, b = 0, 1
...     while a < n:
...         print(a, end=' ')
...         a, b = b, a+b
...     print()
...
>>> # Now call the function we just defined:
... fib(2000)
0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597
```
* 函数第一行处的文本是被称为**文档字符串 （docstring）**的注释，描述了函数是做什么的。文档字符串用**三引号**括
起，Python使用它们来生成有关程序中函数的文档。。

## 函数调用

* 直接输入函数的名字并给他传递实参即可，例如`fib(10)`,即是对前文函数的调用。
* 函数名可以直接赋值给其它变量。如
```python 
f = fib

f(10)
```
与直接`fib(10)`效果一样。

## 函数的参数

* 鉴于函数定义中可能包含**多个形参**，因此函数调用中也可能包含多个实参。向函数传递实参的方式很多，可使用**位置实参** ，这要求实参的顺序与形参的顺序相同；也可使用**关键字实参** ，其中每个实参都由变量名和值组成；还可使用列表和字典。
* **位置实参**：要求实参的顺序与形参的顺序相同，如：
```python
def describe_pet(animal_type, pet_name):
    """显示宠物的信息"""
    print("\nI have a " + animal_type + ".")
    print("My " + animal_type + "'s name is " + pet_name.title() + ".")

describe_pet('hamster', 'harry')
describe_pet('dog', 'willie')
```
* **关键字实参**：每个实参都由变量名和值组成；还可使用列表和字典，如：
```python
def describe_pet(animal_type, pet_name):
    """显示宠物的信息"""
    print("\nI have a " + animal_type + ".")
    print("My " + animal_type + "'s name is " + pet_name.title() + ".")

describe_pet(animal_type='hamster', pet_name='harry')
```
* **形参的默认值**：编写函数时，可给每个形参指定默认值 。在调用函数中给形参提供了实参时，Python将使用指定的实参值；否则，将使用形参的默认值。因此，给形参指定默认值后，可在函数调用中省略相应的实参。使用默认值可简化函数调用，还可清楚地指出函数的典型用法。
```python
def describe_pet(pet_name, animal_type='dog'):
    """显示宠物的信息"""
    print("\nI have a " + animal_type + ".")
    print("My " + animal_type + "'s name is " + pet_name.title() + ".")
```
* 如果传递的参数是列表等复杂类型，**函数中对列表等的修改是永久生效的**（类似传递给该函数的是对该列表的引用）
  * 若想要仅仅**传一个副本给函数**，可使用切片的方法。如：`function_name(list_name[:])`。
* 传递**任意数量的实参**：在形参的前面加上`*`可以生成一个元组，将所有接收到的元组都封装到这个元组中，使用参数时便在这个元组中去取。示例：
```python
def make_pizza(*toppings):
    """概述要制作的比萨"""
    print("\nMaking a pizza with the following toppings:")
    for topping in toppings:
      print("- " + topping)

make_pizza('pepperoni')
make_pizza('mushrooms', 'green peppers', 'extra cheese')
```
* 结合使用**位置形参和任意数量的实参**：如果要让函数接受不同类型的实参，必须在函数定义中将接纳任意数量实参的形参放在最后。Python**先匹配位置实参和关键字实参**，再将余下的实参都收集到最后一个形参中。
* **任意数量的关键字实参**：有时候，需要接受任意数量的实参，但预先不知道传递给函数的会是什么样的信息。在这种情况下，可将函数编写成能够接受任意数量的**键—值对**——调用语句提供了多少就接受多少。
  * 使用`**`能够将接收到的关键字实参封装为一个字典。示例：
```python
def build_profile(first, last, **user_info):
    """创建一个字典，其中包含我们知道的有关用户的一切"""
    profile = {}
    profile['first_name'] = first
    profile['last_name'] = last
    for key, value in user_info.items():
      profile[key] = value
    return profile

user_profile = build_profile('albert', 'einstein',
                              location='princeton',
                              field='physics')
print(user_profile) 
```
## 函数的返回值

* 在函数中，可使用return 语句将值返回到调用函数的代码行。

* 函数可返回任何类型的值，包括列表和字典等较复杂的数据结构。

* ` ->`常常出现在python函数定义的函数名后面，为函数添加`元数据`,描述函数的返回类型，从而方便开发人员使用。如：

  ```java
  Class ListNode:
      def _init_(self, x):
          self.val = x
          self.next = None
  
  Class Solution:
      def reverseList(self, head: ListNode) -> ListNode:
  # 表示该函数的返回值类型为ListNode
  ```

  

## 模块
### 模块的概念

* 模块是一个在其中**封装了函数的Python程序**。模块可以比作一整盒拼装玩具，而函数就是其中的一块块积木。
* 模块是包含了函数的定义的Python文件。这个文件的名称是模块名.py。
* 模块中也可以导入其它模块。

### 模块的导入与调用

* `import 模块名`导入模块名。这种方法导入的模块名，使用名模块内的函数时，必须再加上模块名。即类似`模块名.函数名`的调用方法。示例：
模块(fibo.py)内容：
```python
def fib(n):    # write Fibonacci series up to n
    a, b = 0, 1
    while b < n:
        print(b, end=' ')
        a, b = b, a+b
    print()

def fib2(n): # return Fibonacci series up to n
    result = []
    a, b = 0, 1
    while b < n:
        result.append(b)
        a, b = b, a+b
    return result
```
模块调用：
```python
>>> import fibo
>>> fibo.fib(1000)
1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987
>>> fibo.fib2(100)
[1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89]
>>> fib = fibo.fib #还可以将调用的函数赋给本地定义的变量，以方便频繁调用。
>>> fib(500)
1 1 2 3 5 8 13 21 34 55 89 144 233 377
```
* `from 模块名 import 函数名`导入函数,这样不会导入模块名，但是导入的函数可以不依赖模块而直接调用。
  * 通过用逗号分隔函数名，可根据需要从模块中导入任意数量的函数：`from module_name import function_0, function_1, function_2`
* 还可以使用`from 模块名 import *`来导入模块中所有除了以下划线`_`开头的定义。示例：
```python
>>> from fibo import fib, fib2 #导入
>>> fib(500) #直接调用函数
1 1 2 3 5 8 13 21 34 55 89 144 233 377
>>> from fibo import * #导入模块中所有的定义
>>> fib(500)
1 1 2 3 5 8 13 21 34 55 89 144 233 377
```
* 使用`as`**给导入的函数指定别名**：如果要导入的函数的名称可能与程序中现有的名称冲突，或者函数的名称太长，可指定简短而独一无二的别名 ——函数的另一个名称，类似于外号。如：`from module_name import function_name as fn`.
* 使用`as`给**模块指定别名**：通过给模块指定简短的别名（如给模块pizza 指定别名p ），让你能够更轻松地调用模块中的函数。如：`import module_name as mn`
* 最佳的做法是，要么只导入你需要使用的函数，要么导入整个模块并使用句点表示法。这能让代码更清晰，更容易阅读和理解。

### 模块的搜索路径

* 在导入一个模块时，解释器会从**当前目录**， **环境变量PYTHONPATH**指定的目录列表以及**Python的默认安装路径中**搜索模块。
* 解释器的实际搜索路径是由变量`sys.path`指定的。

### 标准模块

* Python默认内置了很多标准模块，可以直接调用和配置。

### dir（）函数

* dir（）函数用来按模块名搜索模块的定义。

### 包（模块集）

* 包是模块的上层，调用包中的模块与调用模块中的函数大体是相同的。可以直接`import 包名.模块名.函数名`,也可以用`from 包名 import 模块名/函数、变量名`或者`from 包名.模块名 import 子模块名/函数、变量名`。当然，类似模块的导入，包也同样有`from 包名 import *`的导入方式。
* 同样的，包内也可以引用其他包。
* 包文件的创建；包文件中必须包含`_init_.py`文件，这个文件可以让Python把该文件识别为包。它可以为空，也可以包含初始化的一些配置。示例：
```python
sound/                          Top-level package
      __init__.py               Initialize the sound package
      formats/                  Subpackage for file format conversions
              __init__.py
              wavread.py
              wavwrite.py
              aiffread.py
              aiffwrite.py
              auread.py
              auwrite.py
              ...
      effects/                  Subpackage for sound effects
              __init__.py
              echo.py
              surround.py
              reverse.py
              ...
      filters/                  Subpackage for filters
              __init__.py
              equalizer.py
              vocoder.py
              karaoke.py
              ...
```

# 输入和输出

## 格式化输出-print()

* 使用`print()`函数。print()中有类似c语言的用%以及相应参数限制输出格式的方法。
  * Python 3中的print 是一个函数，因此**括号必不可少**。
  * 每一个`print()`语句结束后，对内容会自动进行换行。
  * 下面示例演示了如何将较长的print 语句分成多行。单词print 比大多数字典名都短，因此让输出的第一部分紧跟在左括号后面是合理的（见❶）。请选择在合适的地方拆分要打印的内容，并在第一行末尾（见❷）加上一个拼接运算符（+ ）。按回车键进入print 语句的后续各行，并使用Tab键将它们对齐并缩进一级。指定要打印的所有内容后，在print 语句的最后一行末尾加上右括号（见❸）。
```python
❶ print("Sarah's favorite language is " +
❷     favorite_languages['sarah'].title() +
❸     ".")
```
* 使用标准string模块，对字符串进行格式操作；例如`字符串.format()`方法。
* 使用Template方法；
* 使用str（）和repr（）函数。str（）将值转化为适合人阅读的形式，repr将值转化为适合解释器读取的形式。示例：
```python
>>> s = 'Hello, world.'
>>> str(s)
'Hello, world.'
>>> repr(s)
"'Hello, world.'"
```

## 格式化输入

### 字符串输入-input()

* 函数input() 让程序暂停运行，等待用户输入一些文本。获取用户输入后，Python将其存储在一个变量中，以方便你使用。
* 函数input() 接受一个参数：即要向用户显示的提示 或说明，让用户知道该如何做。示例：
* 使用函数input() 时，Python将用户输入解读为**字符串**。
```python
name = input("Please enter your name: ")
print("Hello, " + name + "!")
```

### 将字符串转换为数字

* 因为input()函数只能读取进来字符串，所以在需要读取数值的时候，需要对字符串类型进行转换。
* `int()`函数可以将符合整数的规定的**字符串转换成int型的**
* `float(str)`函数将符合浮点型的规定的**字符串转换成float型的**
* `str(num)`将**整数、浮点型转换成字符串型的**  

## 文件读写

### 文件的打开与关闭


* 要以任何方式使用文件——哪怕仅仅是打印其内容，都得先**打开文件**，这样才能访问它。
  * **函数`open()`**接受一个参数：要打开的文件的名称。Python在当前执行的文件所在的目录中查找指定的文件。
  * 使用函数`open()`会**返回文件对象**。open常用的模式是`open('filename', 'mode')`.
    * 第一个参数是一个含有要打开的文件的文件名（或文件路径）的字符串。
      * **文件路径**：你将类似pi_digits.txt这样的简单文件名传递给函数open() 时，Python将在**当前执行的文件（即.py程序文件）所在的目录**中查找文件,但仅向open()传递位于当前目录下的文件夹下的文件的名称也不可行。要让Python打开不与程序文件位于同一个目录中的文件，需要提供**文件路径** ，它让Python到系统的特定位置去查找。
      * **相对路径**：如：Linux/OS:`with open('text_files/filename.txt') as file_object:`,在Windows系统中，在文件路径中使用**反斜杠（`\ `）而不是斜杠（`/ `）**：
      * **绝对路径**：绝对路径通常比相对路径更长，因此将其存储在一个变量中，再将该变量传递给open() 会有所帮助。
      ```python
      # Linux/OS
      file_path = '/home/ehmatthes/other_files/text_files/filename.txt' 
      with open(file_path) as file_object:
      
      # Windows
      file_path = 'C:\Users\ehmatthes\other_files\text_files\filename.txt' 
      with open(file_path) as file_object:
      ```
    * 第二个参数也是一个字符串，含有描述如何使用该文件的几个字符。
      * mode 为 'r' 时表示只是读取文件；'w' 表示只是写入文件（已经存在的同名文件将被删掉）；'a' 表示打开文件进行追加，写入到文件中的任何数据将自动添加到末尾。 'r+' 表示打开文件进行读取和写入。
      * mode 参数是可选的，默认为 'r'。示例：`f = open('workfile', 'w')`。
      * 只用写入的模式(w,a)，如果输入的文件不存在，会在当前目录下**创建该文件**。
* 文件打开之后需要正常的**关闭**：
  * 未妥善地关闭文件可能会导致数据丢失或受损；
  * 可以`close()`语句来关闭文件。
  * 也可以是用关键字`with`来关闭文件：关键字with 在不再需要访问文件后将其关闭。如：`with open('pi_digits.txt') as file_object:`

### 读取文件内容

* `文件对象.read(size)`方法：该方法读取若干数量的数据并以字符串形式返回其内容，size 是可选的数值，指定字符串长度。如果没有指定 size 或者指定为负数，就会**读取并返回整个文件**。如果到了文件末尾，f.read() 会返回一个空字符串（''）
* **逐行读取文件**：要以每次一行的方式检查文件，可对文件对象使用**for 循环**(注意：每行的末尾都有一个看不见的换行符,可以使用`rstrip()`方法去掉)：
  ```python
   filename = 'pi_digits.txt' 
   with open(filename) as file_object:  
     # 每一次在循环中只读取文件中的一行
     for line in file_object: 
       print(line.rstrip())
  ```
* `文件对象.readline()`从文件中读取单独一行，字符串结尾会自动加上一个换行符（ \n ）。如果 f.readline() 返回一个空字符串，那就表示到达了文件末尾。

### 写入文件

* 要将文本写入文件，你在调用open() 时需要提供**另一个实参**（因为默认是r模式），告诉Python你要写入打开的文件。
* 使用文件对象的方法`write()`**将一个字符串写入文件**;
  * 函数write() **不会在你写入的文本末尾添加换行符**，因此如果你写入多行时没有指定换行符，文件看起来可能不是你希望的那样：
  * 要让每个字符串都单独占一行，需要在write() 语句中**包含换行符**,如：`file_object.write("I love programming.\n")`
  * 像显示到终端的输出一样，还可以**使用空格、制表符和空行来设置这些输出的格式**。
* Python**只能将字符串写入文本文件**。要将数值数据存储到文本文件中，必须先使用函数`str()` 将其转换为字符串格式。


# 异常

* Python使用**被称为异常的特殊对象来管理程序执行期间发生的错误**。
  * 语法正确的情况下发生的错误为异常错误。
  * 错误信息的最后一行指出发生了什么异常类型。异常也有不同的类型，异常类型做为错误信息的一部分显示出来：如零除错误（ ZeroDivisionError ） ，命名错误（ NameError） 和 类型错误（ TypeError ）等。
* 每当发生让Python不知所措的错误时，它都会创建一个异常对象。如果你编写了处理该异常的代码，程序将继 续运行；如果你未对异常进行处理，程序将停止，并显示一个traceback(在异常没有被处理的情况下才会有traceback)，其中包含有关异常的报告。
* 异常是使用**try-except 代码块处理**的。try-except 代码块让Python执行指定的操作，同时告诉Python发生异常时怎么办。使用了try-except 代码块时，**即便出现异常， 程序也将继续运行：显示你编写的友好的错误消息，而不是令用户迷惑的traceback并让程序终止**。

## 语法错误（SyntaxError）

* 语法分析器指出错误行，并且在检测到错误的位置前面显示一个小“箭头”。 错误是由箭头 前面 的标记引起的（或者至少是这么检测的）。
* 错误会输出文件名和行号，所以如果是从脚本输入的你就知道去哪里检查错误了。
## 异常的处理

* 使用`try...except`语句来处理异常。
  * 如果try 代码块中的代码运行起来没有问题，Python将跳过except 代码块；如果try 代码块中的代码导致了 错误，Python将查找这样的except 代码块，并运行其中的代码，即其中指定的错误与引发的错误相同。 可以在except代码块中放入**pass语句，告诉程序捕获到异常后什么都不需要做**。
  * 在出现异常的情况下，如果**try-except 代码块后面还有其他代码，程序将接着运行**，因为已经告诉了Python如何处理这种错误。
  * 如：
```python
try:
  print(5/0) 
except ZeroDivisionError: 
  print("You can't divide by zero!")
```
* `try...except...else`代码块：依赖于try 代码块成功执行的代码都应放到else 代码块中：
  * try-except-else 代码块的工作原理大致如下：Python尝试执行try 代码块中的代码；只有可能引发异常的代码才需要放在try 语句中。有时候，**有一些仅在try 代码块成功 执行时才需要运行的代码；这些代码应放在else 代码块中**。except 代码块告诉Python，如果它尝试运行try 代码块中的代码时引发了指定的异常，该怎么办。
  * 示例：
  ```python
   try:
     answer = int(first_number) / int(second_number) 
   except ZeroDivisionError: 
     print("You can't divide by 0!") 
   else:
     print(answer)
  ```
* （`try...finall`）可以用来定义清理行为。

## 异常的抛出

* 使用`raise`语句可以抛出异常。

## 自定义异常

* 用户可以自定义异常类型来创建自己的异常名。

## 预定义清理行为、

* `with`语句打开文件，使用完毕之后会自动关闭文件。这可以称作一种预定义的清理行为。示例；
```python
with open("myfile.txt") as f:
    for line in f:
        print(line)
```

# 类

## 类的定义

* 类与函数一样，在使用前需要先定义。类的定义示例如下：
```python
class ClassName():
    <statement-1>
    .
    .
    .
    <statement-N>
```
* **方法**：类中的函数叫做方法。
* **属性**：可通过实例访问的变量称为属性 。
* 可将类视为有关如何创建实例的说明。
  
## `_init_()`-类构造方法

* `__init__()`是一个特殊的方法，每当你根据类创建新实例时，**Python都会自动运行它**。在这个方法的名称中，**开头和末尾各有两个下划线**，这是一种约定，旨在避免Python默认方法与普通方法发生名称冲突。
* 在`_init_()`这个方法的定义中，**形参self 必不可少，还必须位于其他形参的前面**。
  * Python调用这个`__init__() `方法来创建实例时，将**自动传入实参self** 。每个**与类相关联的方法调用都自动传递实参self ，它是一个指向实例本身的引用，让实例能够访问类中的属性和方法。**每当我们根据类创建实例时，都只需给除开self的形参提供值。
  * 以self 为前缀的变量都可供类中的所有方法使用，我们还可以通过类的任何实例来访问这些变量。如：`self.name = name` 获取存储在形参name 中的值，并将其存储到变量name 中，然后**该变量被关联到当前创建的实例**。
* 类的属性不用单独定义，**在`_init()`中通过self定义并初始化。
* 示例：
```python
class Dog():
    """一次模拟小狗的简单尝试"""

    def __init__(self, name, age):
      """初始化属性name和age"""
      self.name = name
      self.age = age

    def sit(self):
      """模拟小狗被命令时蹲下"""
     print(self.name.title() + " is now sitting.")

    def roll_over(self):
      """模拟小狗被命令时打滚"""
      print(self.name.title() + " rolled over!")
```

## 类的实例化

* 类的实例化就就是**将类看做一个函数进行调用**。例如：`x = Myclass()`。
* 我们通常可以认为首字母大写的名称（如Dog）指的是类，而小写的名称（如my_dog ）指的是根据类创建的实例。
* 属性与方法的调用：使用句点表示法获取实例的属性和调用其方法。如：`my_dog.name`,`my_dog.roll_over()`
  * `__doc__` 也是一个有效的属性，返回类的文档字符串

## 类的编码风格

* 类名应采用**驼峰命名法**，即将**类名中的每个单词的首字母都大写**，而不使用下划线。实例名和模块名都采用**小写格式，并在单词之间加上下划线**。
* 对于每个类，都应紧跟在类定义后面包含一个**文档字符串**。这种文档字符串简要地描述类的功能，并遵循编写函数的文档字符串时采用的格式约定。每个模块也都应包含一个文 档字符串，对其中的类可用于做什么进行描述。
* 可使用空行来组织代码，但不要滥用。在类中，可使用**一个空行来分隔方法**；而在模块中，可使用**两个空行来分隔类**
* 需要同时导入标准库中的模块和你编写的模块时，**先编写导入标准库模块的import 语句，再添加一个空行**，然后编写导入你自己编写的模块的import 语句。


## 类的继承

* 编写类时，并非总是要从空白开始。如果你要编写的类是另一个现成类的特殊版本，可使用继承 。一个类继承另一个类时，它将自动获得另一个类的所有属性和方法；原有的类称为父类 ，而新类称为子类 。**子类继承了其父类的所有属性和方法，同时还可以定义自己的属性和方法**。
* 创建子类时，**父类必须包含在当前文件中，且位于子类前面**。
* 定义子类时，必须**在括号内指定父类的名称**。
* 子类的方法`__init__()`
  * 创建子类的实例时，Python首先需要完成的任务是给父类的所有属性赋值。为此，**子类的方法__init__() 需要父类施以援手**。
  * `super()` 是一个特殊函数，帮助Python将父类和子类关联起来。使用这个函数让Python调用父类的方法`__init__()` ，让该子类包含父类的所有属性。
* 让一个类继承另一个类后，可**添加区分子类和父类所需的新属性和方法**,属性在调用`super()`函数后之后添加，方法可以在类中任意定义。
* **重写父类的方法**：
  * 对于**父类的方法**，只要它不符合子类模拟的实物的行为，都可对其进行重写。
  * 重写父类的方法只需要在**子类中定义一个同名的方法**，就会自动覆盖掉父类的该方法。
* **将实例用作属性**：类中属性可以是**引用类型**。如：`self.battery = Battery()`,其中`Battery()`是Battery类的构造方法。
* 示例：
```python
# 父类
class Car():
   """一次模拟汽车的简单尝试""" 
   def __init__(self, make, model, year): 
     self.make = make 
     self.model = model 
     self.year = year 
     self.odometer_reading = 0 

   def get_descriptive_name(self): 
     long_name = str(self.year) + ' ' + self.make + ' ' + self.model
     return long_name.title() 

   def read_odometer(self): 
     print("This car has " + str(self.odometer_reading) + " miles on it.") 

   def update_odometer(self, mileage): 
     if mileage >= self.odometer_reading: 
       self.odometer_reading = mileage else:print("You can't roll back an odometer!") 
       
   def increment_odometer(self, miles): 
     self.odometer_reading += miles  
    
   def gas_gank():
     print("油箱信息")
# 子类     
class ElectricCar(Car): 
  """电动汽车的独特之处""" 
   def __init__(self, make, model, year): 
     """初始化父类的属性""" 
      super().__init__(make, model, year) 
      self.battery_size = 70 
      
   def describe_battery(self): 
     """打印一条描述电瓶容量的消息""" 
     print("This car has a " + str(self.battery_size) + "-kWh battery.")
   
   # 重写父类方法
   def gas_gank():
    print("电动车没有油箱")
```

## 导入类

* 类和函数一样都可以**封装在模块中**，进行导入。
* 在模块文件的首部写**个模块级文档字符串，对该模块的内容做简要的描述**。
* 从模块中导入类的语法和导入函数一致。
* 一个模块中可以存储一个或者多个类。
* 模块示例：
```python
"""一组可用于表示电动汽车的类"""  
from car import Car

class Battery(): 
    ...... 

class ElectricCar(Car): 
    .......
```

## Python标准库

* Python标准库是一组模块，安装的Python都包含它。
* 可使用标准库中的任何函数和类，为此，只需在程序开头包含一条简单的import 语句。
* 注：你还可以从其他地方下载外部模块。


# JSON存储数据

* **JSON（JavaScript Object Notation）**格式最初是为JavaScript开发的，但随后成了一种常见格式，被包括Python在内的众多语言采用。
* 模块json 让你能够将简单的Python**数据结构转储到文件**中，并在程序再次运行时加载该文件中的数据。
* 可以使用json在Python程序之间分享数据。
* JSON数据格式并非Python专用的，这让你能够将以**JSON格式存储的数据与使用其他编程语言**的人分享。
* JSON在Python中是一个**模块**，使用JSON格式时需要先导入json模块。
* `json.dump()`:这个函数能够将数据结构转存到文件中。
* `json.load()`:将以json方式存储到文件的数据结构读取到内存中去。
* 示例：
```python
import json 
# 如果以前存储了用户名，就加载它 
# 否则，就提示用户输入用户名并存储它 
filename = 'username.json' 
try:  
  with open(filename) as f_obj:  
    username = json.load(f_obj) 
  except FileNotFoundError: 
     username = input("What is your name? ")  
     with open(filename, 'w') as f_obj: 
       json.dump(username, f_obj) 
       print("We'll remember you when you come back, " + username + "!") 
  else:
    print("Welcome back, " + username + "!")
```

# Python测试

* Python标准库中的**模块unittest**提供了代码测试工具。 
* **单元测试**用于核实函数的某个方面没有问题。
* **测试用例**是一组单元测试，这些单元测试一起核实函数在各种情形下的行为都符合要求。
* **全覆盖测试用例**包含一整套单元测试，涵盖了各种可能的函数使用方式。
* 示例：
```python
import unittest 
from name_function import get_formatted_name 

class NamesTestCase(unittest.TestCase): 
  """测试name_function.py """ 
  def test_first_last_name(self): 
    """能够正确地处理像Janis Joplin这样的姓名吗？""" 
    formatted_name = get_formatted_name('janis', 'joplin') 
    self.assertEqual(formatted_name, 'Janis Joplin') 

  def test_first_last_middle_name(self): 
    """能够正确地处理像Wolfgang Amadeus Mozart这样的姓名吗？""" 
     formatted_name = get_formatted_name( 'wolfgang', 'mozart', 'amadeus') 
     self.assertEqual(formatted_name, 'Wolfgang Amadeus Mozart') 
     
unittest.main()
```
  * 可先导入**模块unittest**以及要**测试的函数**;
  * 再创建一个**继承unittest.TestCase 的类**，在这个类中编写一系列**方法**对函数行为的不同方面进行测试。
    
    * 你可随便给这个类命名，但最好让它看起来与要测试的函数相关，并包含字样Test。
  * 在类中用于测试函数不同方面的方法名称**必须以test打头，我们运行该测试程序的时候，所有以test打头的方法都将自动运行**。
  * 最后需要**调用`unittest.main()`方法，用于运行测试程序**。
  * **断言**：断言方法用来核实得到的**结果是否与期望的结果一致**。
    * 在python中断言的一种实现方式是使用通过`unint.TestCase`类中的`assertEqual()`方法,如：`self.assertEqual(formatted_name, 'Wolfgang Amadeus Mozart') `,前面的参数是测试的结果，后面的参数是预期的结果。
    * `assertEqual(a, b)`: 核实a == b 
    * `assertNotEqual(a, b)`: 核实a != b 
    * `assertTrue(x)`: 核实x 为True 
    * `assertFalse(x)`: 核实x 为False 
    * `assertIn(item , list )` 核实 item 在 list 中 
    * `assertNotIn(item , list )`: 核实 item 不在 list 中
* 和函数的测试相似的，也可以对类进行测试：
  * 类的测试就是将原来测试函数中调用函数的地方，修改为调用类中的方法。
  * **使用`setUp()`方法**将测试类初始化：
    * `unittest.TestCase`类包含方法`setUp()` ，让我们**只需创建这些对象一次，并在每个测试方法中使用它们**。如果你在TestCase 类中包含了方法`setUp()` ，**Python将先运行它，再运行各个以test_打头的方法**。这样，在你编写的每个测试方法中都可使用在方法`setUp()` 中创建的对象了。
  * 类测试示例：
  ```python
  import unittest 
  from survey import AnonymousSurvey 
  
  class TestAnonymousSurvey(unittest.TestCase): 
    """针对AnonymousSurvey类的测试""" 
    def setUp(self): 
      """ 创建一个调查对象和一组答案，供使用的测试方法使用 """ 
      question = "What language did you first learn to speak?" 
      self.my_survey = AnonymousSurvey(question) 
      self.responses = ['English', 'Spanish', 'Mandarin'] 
      
    def test_store_single_response(self): 
      """测试单个答案会被妥善地存储""" 
      self.my_survey.store_response(self.responses[0]) 
      self.assertIn(self.responses[0], self.my_survey.responses) 
      
    def test_store_three_responses(self): 
      """测试三个答案会被妥善地存储""" 
      for response in self.responses: 
        self.my_survey.store_response(response) 
      for response in self.responses: 
        self.assertIn(response, self.my_survey.responses) 
  
  unittest.main()
  ```
```













```