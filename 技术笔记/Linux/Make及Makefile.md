---
title: Make及Makefile
tags:
  - MakeFile
  - Linux
  - C
categories:
  - 技术笔记
  - Linux
abbrlink: 33094
date: 2020-07-31 22:28:31
---

# Make及Makefile

## Make
* 代码变成可执行文件，叫做编译（compile）；先编译这个，还是先编译那个（即编译的安排），叫做构建（build）。
* Make是最常用的构建工具，诞生于1977年，主要用于C语言的项目。但是实际上 ，任何只要某个文件有变化，就要重新构建的项目，都可以用Make构建。
* Make这个词，英语的意思是"制作"。Make命令直接用了这个意思，就是要做出某个文件。make是一个根据指定的Shell命令进行构建的工具。
* make命令执行的规则，都写在一个叫做Makefile的文件中，Make命令依赖这个文件进行构建。Makefile文件也可以写为makefile， 或者用命令行参数(`make -f <指定的文件名>`或者` make --file=<指定的文件名>`)指定为其他文件名。


## Makefile

### Makefile的基本概念

#### Makefile是什么？
* Makefile 可以简单的认为是一个工程文件的**编译规则**，描述了整个工程的编译和链接等规则。其中包含了那些文件需要编译，那些文件不需要编译，那些文件需要先编译，那些文件需要后编译，那些文件需要重建等等。编译整个工程需要涉及到的，在 Makefile 中都可以进行描述。换句话说，Makefile 可以使得我们的项目工程的**编译变得自动化**，不需要每次都手动输入一堆源文件和参数。
* 编写Makefile的作用：
  1. 简化输入的编译命令：在 Makefile 中，制定相应的规则和对应的链接顺序。这样只需要执行 make 命令，工程就会自动编译。每次想要编译工程的时候就执行 make ，省略掉手动编译中的参数选项和命令。
  2. 节省编译大工程的时间：Makefile 支持多线程并发操作(就像我们使用的`make -j8`)，会极大的缩短我们的编译时间，并且当我们修改了源文件之后，编译整个工程的时候，make 命令只会编译我们修改过的文件，没有修改的文件不用重新编译，也极大的解决了我们耗费时间的问题。

#### Makefile中的基本规则
* Makefile 描述的是文件编译的相关规则，它的规则主要是两个部分组成，分别是**依赖的关系**和**执行的命令**
* Makefile文件由一系列规则（rules）构成。每条规则的形式如下:
    ```shell
    <target> : <prerequisites> 
    [tab]  <commands>
    ```
    1. targets：规则的目标，可以是 Object File（一般称它为中间文件），也可以是可执行文件，还可以是一个标签
    2. prerequisites：规则的条件，是我们的依赖文件，要生成 targets 需要的文件或者是目标。可以是多个，也可以是没有；
    3. command：make 需要执行的命令（任意的 shell 命令）。可以有多条命令，每一条命令占一行。
    4. 注意：**我们的目标和依赖文件之间要使用*冒号*分隔开，命令的开始一定要使用*Tab*键**

* Makefile文件规则概要：
  1. 显式规则：
    显式规则说明了，如何生成一个或多的的**目标文件**。这是由 Makefile 的书写者明显指出，要生成的文件，文件的依赖文件，生成的命令。
  2. 隐晦规则
    由于我们的 make 命名有**自动推导**的功能，所以隐晦的规则可以让我们比较粗糙地简略地书写 Makefile，这是由 make 命令所支持的。 
  3.  变量的定义
    在 Makefile 中我们要定义一系列的变量，变量一般都是字符串，这个有点像C语言中的**宏**，当 Makefile 被执行时，其中的变量都会被扩展到相应的引用位置上。
  4. 文件指示
    其包括了三个部分，一个是在一个 Makefile 中**引用**另一个 Makefile，就像C语言中的 include 一样；另一个是指根据某些情况指定 Makefile 中的**有效部分**，就像C语言中的预编译 #if 一样；还有就是定义一个多行的命令。
  5. 注释
    Makefile 中**只有行注释**，和 UNIX 的 Shell 脚本一样，其注释是**用“#”字符**，这个就像 C/C++ 中的“//”一样。如果你要在你的 Makefile 中使用“#”字符，可以用反斜框进行转义，如：`\#`。

#### Makefile的基本工作流程
* 当我们在执行 make 条命令的时候，make 就会去**当前文件**下找要执行的编译规则，也就是 Makefile 文件。我们编写 Makefile 的时可以使用的文件的名称"GNUmakefile" 、"makefile" 、"Makefile" ，make 执行时回去寻找 Makefile 文件，找文件的顺序也是这样的。我们推荐使用**Makefile**（一般在工程中都这么写，大写的会比较的规范）。如果文件不存在，make 就会给我们报错，提示：
`make：*** 没有明确目标并且找不到 makefile。停止`
* 工作流程简介：
```shell
main:main.o test1.o test2.o
  gcc main.o test1.o test2.o -o main
main.o:main.c test.h
  gcc -c main.c -o main.o
test1.o:test1.c test.h
  gcc -c test1.c -o test1.o
test2.o:test2.c test.h
  gcc -c test2.c -o test2.o
```
   * **工作流程**：当在 shell 提示符下输入 make 命令以后。 make 读取当前目录下的 Makefile 文件，并将 Makefile 文件中的**第一个目标作为其执行的“终极目标”**，开始处理第一个规则（终极目标所在的规则）。在我们的例子中，第一个规则就是目标 "main" 所在的规则。规则描述了 "main" 的**依赖关系**，并定义了**链接 ".o" 文件生成目标 "main" 的命令**；make 在执行这个规则所定义的命令之前，**首先处理目标 "main" 的所有的依赖文件**（例子中的那些 ".o" 文件）的更新规则（以这些 ".o" 文件为目标的规则）。
   * 对这些 ".o" 文件为目标的**规则处理**有下列三种情况：
      * 目标 ".o" 文件**不存在**，使用其描述规则创建它；
      * 目标 ".o" 文件存在，目标 ".o" 文件所**依赖**的 ".c" 源文件 ".h" 文件中的任何一个比目标 ".o" 文件“更新”（在上一次 make 之后被修改）。则根据规则重新编译生成它；
      * 目标 ".o" 文件存在，目标 ".o" 文件比它的任何一个依赖文件（".c" 源文件、".h" 文件）“更新”（它的依赖文件在上一次 make 之后**没有被修改**），则什么也不做。
  * 我们执行 make 命令时，**只有修改过的源文件或者是不存在的目标文件**会进行重建，而那些没有改变的文件不用重新编译，这样在很大程度上节省时间，提高编程效率。
  * 具体命令解析
    1. `main: main.o test1.o test2.o`:表示最终目标文件是生成main,而main的生成依赖于main.o test1.o test2.o 这三个汇编文件。
    2. `gcc mian.o test1.o test2.o -o main`:指定了生成main所需要执行的shell命令，即将三个依赖的汇编文件链接输出为main。
    3. `main.o: main.c test.h`:表示main.o文件依赖于main.c和test.h文件，为了生成最终文件main，make会先执行能生成main的依赖文件的命令。
    4. `gcc -c main.c -o main.o`:指定了如何通过依赖文件生成main.o的shell命令，即将main.c文件编译汇编为main.o文件（注意：因为gcc会自动找到头文件及所需的链接库，所以汇编时不用加上头文件test.h，但是在**描述依赖关系时还是要将test.h描述进去**）
   * **清除工作过程中的过程文件**:我们在使用的时候会产生中间文件会让整个文件看起来很乱，所以在编写 Makefile 文件的时候会在末尾加上这样的规则语句：
  ```
    .PHONY:clean
    clean:
    rm -rf *.o 
  ```
   其中 "*.o" 是执行过程中产生的中间文件，"test" 是最终生成的执行文件。(具体要删除什么文件，可以自行指定);可以在命令行中指定Makefile中的一个文件作为目标，那么这条make命令就只更新这个指定的目标(如：`make clean`，如果没有指定目标则make默认更新Makefile中的第一条规则的目标。
### Makefile中的通配符

* 由于Makefile是使用shell命令的，所以shell命令中的通配符在Makefile中也是适用的。

|通配符|作用|
|-|-|
|`*`|匹配任意个字符,如`*.o`代表在当前文件夹下所有以`.o`结尾的文件|
|`?`|匹配一个字符|
|`[]`|匹配指定的字符，指定的字符放在`[]`中，如`[zhangjie].o`|
|`~`|类似于shell中打开目录时的`~`，后面为空或者接分隔符`/`或者接路径名或者文件名的时候，代表根目录。如`~/bin`代表`/home/usrname/bin`,`~zhangjie/bin`代表`/home/zhangjie/bin`|


### Makefile变量的定义和使用

#### 变量的定义

* 变量的定义：
  * 变量是用来存储一个值或者使用来进行运算操作，可以用来表示多处出现而内容又可能发生变化的内容。
  * Makefile中的变量不像c语言中一样,没有数据类型。
  * 变量的名称可以由字母，阿拉伯数字和下划线组成。
  * 变量的定义语句：`变量 = 值列表`(其中值列表既可以是零项，又可以是一项或者多项),例如：`value = 1 2 3`,一个变量的定义从`=`后面的第一个非空白字符开始，包括后面所有的字符，直到注释或者换行之前结束。 
* 变量的调用：
  * 调用变量可以使用`$(value)`,`${value}`.
  * 示例：
      ```shell
      OBJ = mian.o test.o 
      test: $(OBJ)
        gcc ${OBJ} -o test
      ```
#### 变量的定义方法

* 简单赋值：`:=`,编程中常见的变量赋值，只对当前语句有赋值效果,即遇到这种赋值就立即展开。

```shell
x := foo
y := $(x)b
x := new
test:
  @echo "y => $(y)"
  @echo "x => $(x)"
```
结果：
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20200829225226.jpg)

* 递归赋值：`=`,又叫做迟滞展开，每次使用的变量都是用的最新的定义，所以这种方式甚至能做到等式右边的式子中的变量在之后才被定义,即遇到递归赋值，会在使用整个变量时才将它展开。

```shell
x := foo
y = $(x)b
x := new
test:
  @echo "y => $(y)"
  @echo "x => $(x)"
```
结果：
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20200829230330.jpg)
* 与简单赋值不同的是，递归赋值y中的x使用的是在最后赋给x的new,而不是开始就赋给x的foo(实际上递归赋值相当于最后在使用y变量的时候，才将y的赋值式代入，此时它前面已经又有了对x的新的赋值，所以最后呈现出来的是x最后的赋值,也因此把对x的第二个赋值语句改为简单赋值也是同样的输出)。

* 条件赋值：`?=`如果当前变量从未被赋值过，则相当于`=`;但如果当前变量已将被赋值过，则什么也不做，不会再重新赋值。
```shell
x := foo
y := $(x)b
x ?= new
test:
  @echo "y => $(y)"
  @echo "x => $(x)"
```
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20200830190810.jpg)
* 最后给x的条件赋值实际没有任何效果。

* 追加赋值：`+=`,追加赋值类似于c语言中的`+=`,可理解为变量本身再在后面加上`+=`后面的值，但是不同定义得到的变量它的`+=`的理解会有不同。比如说，如果变量object使用`:=`定义的，那么`object += foo`,与`object := $(object) foo`,注意最后合成的object的两部分的变量间会自动生成一个空格。
```shell
x := foo
y := $(x)b
x += $(y)
test:
  @echo "y => $(y)"
  @echo "x => $(x)"
```
结果：
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20200830192748.jpg)

#### 自动化变量

* `$@`:表示规则中的目标
* `$<`:表示规则中的第一个条件（即第一个依赖文件）
* `$?`:表示规则中所有比目标新的条件，组成一个列表，以空格分隔
* `$^`:表示规则中的所有条件(即所有依赖文件)

### Makefile的隐含规则

* 在Makefile中有内置的隐含规则可以使我们省略对一些规则的命令的编写。

### Makefile的条件判断

* 在Makefile编译文件时可能会遇到需要分条件使用不同命令的情况，这时可以使用Makefile的条件编译语句。
* 常用条件判断的关键字：

|关键字|作用|
|-|-|
|ifeq|判断参数是否相等，相等为true，不相等为false|
|ifneq|判断参数是否不相等，不相等为true，相等为false|
|ifef|判断是否有值，有值为true，无值为false|
|ifnef|判断是否无值，无值为true，有值为false|

### Makefile的伪目标

* 伪目标：这个目标没有任何依赖文件，但是有命令，它最终的结果并不会生成任何目标文件，而只是执行该规则下的命令。
* 伪目标的创建：`.PHONY:变量名`
* 伪目标的应用：
  * 执行一些没有文件产生的命令，如删除
```shell
.PHONY:clean
clean:
  rm -rf *.o 
```
  * 一个make命令产生多可执行文件，提升make的效率。
```shell
.PHONY:all
all:test1, test2, test3

test1: test1.o
  gcc $^ -o $@
test2: test2.o
  gcc $^ -o $@
test3: test3.o
  gcc $^ -o $@
```
*如果只想生成一个test1文件，可以用`make test1`。

### Makefile的基础语法

#### 注释

* Makefile类似Python，在每一行后以`#`来开始注释。

#### 回声

* 默认情况下，make会打印它执行的命令行，这叫做“回声”。
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20200830213731.jpg)
* 在每行命令前加上`@`符号就可以关闭回声，例如经常使用的`@echo`

#### Makefile中的函数

Makefile中有许多[内置函数](https://www.gnu.org/software/make/manual/html_node/Functions.html)可以调用。

---
参考资料：
[阮一峰的博客](http://www.ruanyifeng.com/blog/2015/02/make.html)
[C语言中文网](http://c.biancheng.net/makefile/)
[GNU make](https://www.gnu.org/software/make/)



