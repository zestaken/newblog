---
title: 6种编程语言实现helloworld
date: 2020-09-19 10:21:20
tags:
---
# 总述

## 解释型语言与编译型语言

* 编译型语言是使用特定的编译器，一次性将程序源代码编译为能被平台硬件所执行的机器码的语言。
* 解释型语言是使用特定的解释器，在程序被执行时将源码逐行翻译为能被平台机器执行的机器码并立即执行的语言。


## C语言

## Python语言

## Java语言

## JavaScript语言

## PHP语言

* PHP（全称：PHP：Hypertext Preprocessor，即"PHP：超文本预处理器"）是一种通用开源脚本语言。PHP 脚本在服务器上执行

## Ruby语言

* Ruby 的特性与 Smalltalk、Perl 和 Python 类似。Perl、Python 和 Smalltalk 是脚本语言。Smalltalk 是一个真正的面向对象语言。Ruby，与 Smalltalk 一样，是一个完美的面向对象语言。


# c语言

* 环境：在linux上安装gcc工具。(具体见博客[Gcc基础](https://z-d-j.github.io/2020/07/28/GCC%E5%9F%BA%E7%A1%80/))
* 程序源代码：
```c
#include <stdio.h>

int main() {

    printf("Hello World! I'm 20191091615004 张杰\n");

    return 0;
}
```
* 实现截图：
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20200919104340.jpg)

# Python

## 环境：
* 在linux上编译安装Python3。（半年前装的，不好搞截图了。。。）
* 从官网下载Python3的压缩包。
* 解压缩到指定目录；
* 进入到该目录下，使用命令` ./configure --prefix=/usr/local/python3`;
* 之后`make`一下（make是运行Python3的包里的Makefile文件，自动进行编译安装）,记忆中这个过程耗时有一丢丢的长。
* 最后`make install`，完成安装。
* 在命令行输入`python3 --version`来查看安装的版本。
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20200919105429.jpg)

## helloworld实现

* 源代码（Python就一句。。。）
```python
print("hello world! I'm 2019091615004 张杰")
```
* 实现截图：
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20200919110141.jpg)

# Java

## 环境

* Java的环境在Windows上需要去官网下载jdk并安装，然后修改环境变量。当然还可以直接下载安装idea这种IDE，什么都不用做了，直接编程就完事儿。在linux上就更简单啦，直接一波`sudo apt-get install openjdk-14-jdk`就搞定（openjdk对于我这种菜鸡来说应该完全够了/哈哈），然后使用`java --version`查看安装的版本以及是否安装成功。
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20200919110845.jpg)

## helloworld实现

* 程序源代码
```java

public class Hello {

    public static void main(String[] args) {
        System.out.println("hello world! I'm 2019091615004 张杰");
    }
}
```

* 实现截图：
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20200919112209.jpg)
（不过y1s1,除了打helloworld，其它程序还是用IDEA香，Java补全不行是真的难受）

# JavaScript

## 环境

* JS是一门脚本语言，编程是通过js解释器来逐行解释。js解释器在浏览器中就自带有。

## helloworld实现

* 程序源代码：
```javascript
<script language = "javascript">

    function sayHello() {
        document.write("hello world! I'm 2019091615004 张杰");
    }
    sayHello();
</script>
```
* 新建一个.html类型的文件（因为JavaScript是嵌入到HTML中使用的），之后将代码写入，最后用浏览器（我用的是edge）打开即可。
* 实现截图：
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20200919130507.jpg)

# PHP

## 环境

* 在windows上安装PHP：
    * 去[官网](http://php.net/downloads.php)下载PHP的zip文件；
    * 解压缩到任意路径。
* 在Windows上安装PHPstorm（使用学生邮箱白嫖）。
* 在PHPstorm中导入下载的PHP的`php.exe`解释器。

## helloworld实现

* 程序源代码
```php
<?php
    echo "hello world!";
?>
```
* 实现截图：
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201001204857.jpg)


# Ruby

## 环境

* 去[官网](https://rubyinstaller.org/downloads/)下载Ruby安装器。跟随安装向导即可一步步安好。
* 安装好后打开cmd，输入`ruby -v`即可查看是否安装好了。
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201001210503.jpg)

## helloworld实现

* 程序代码：（在一个名为hello.rb的文件中）
```ruby
puts("hello world！I'm 2019091615004 张杰");
```
* 实现截图：
      
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201001211814.jpg)


