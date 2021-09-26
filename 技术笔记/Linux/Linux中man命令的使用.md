---
title: Linux中man命令的使用
tags: Linux
categories:
  - 技术笔记
  - Linux
abbrlink: 29245
date: 2021-06-06 08:02:19
---

# man命令介绍

* Linux提供了丰富的帮助手册，当你需要查看某个命令的参数时不必到处上网查找，只要`man`一下即可。
* 可以使用`man man`查看man的使用方法。
* man命令分为以下几个章节（按真实顺序排列）：
  1. 标准用户命令（Executable programs or shell commands）
  2. 系统调用（System calls）functions provided by the kernel
  3. 库调用（Library call）functions within program libraries
  4. 特殊文件（设备文件）的访问入口（/dev）Special files (usually found in /dev)
  5. 文件格式（配置文件的语法），指定程序的运行特性 File formats and conventions 
  6. 游戏（Games）
  7. 杂项（Miscellaneous）including macro packages and conventions
  8. 管理命令 System administration commands
  9. 跟kernel有关的文件 Kernel routines

# man命令的常用参数

* `man -f`:与`whatis`命令功能相同，可以简洁地显示命令的功能，以及它所属的章节。
![](https://gitee.com/zhangjie0524/picgo/raw/master/20210606220020.jpg)
* `man -k`:与`apropos`命令功能相同，可以在所有命令的manual页面中的Description部分中查找命令后接的关键词，然后对查找到关键词的页面简洁地返回一行描述，以及有该命令完整的名字和所在的页面。
![](https://gitee.com/zhangjie0524/picgo/raw/master/20210606220638.png)
* 可以在查命令时，指定对应的章节，如：`man 3 sleep`只查第三节的sleep：
![](https://gitee.com/zhangjie0524/picgo/raw/master/20210606220936.png)
   * 如果不指定对应的章节如：`man sleep`，则默认查最小的章节，对sleep来说，就是Sleep（1）

# man的基本使用方法

* 翻页：
  * 向后翻一屏：space(空格键) 
  * 向前翻一屏：b
  * 向后翻一行：Enter(回车键) 或者j
  * 向前翻一行：k
* 查找：
  *  `/KEYWORD`: 向后查找     
  * `?KEYWORD`:  向前查找    
  * 在进入查找模式之后：
    * N：前一个
    * n：下一个
* 退出：q


