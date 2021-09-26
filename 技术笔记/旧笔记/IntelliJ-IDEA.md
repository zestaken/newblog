---
title: IntelliJ IDEA
date: 2020-10-24 08:41:54
tags: 杂技术
categories: 技术笔记
---

# 项目结构

* 项目（project）->模块（module）-> 包（package） ->代码（.java）（module和project均需要在头部选项`File`中创建）
* src文件夹存放源代码。
* .idea文件夹和.iml文件是IDEA对项目的配置文件。与项目内容无关。
* 对包的命名遵循域名颠倒过来(全是小写字母）。如`com.zhangjie`，这个命名会生成两个文件夹，一个是com文件夹，com文件夹下包含zhangjie文件夹。`.`起到分开不同包文件夹的作用。
* IDEA是实时保存的，写到哪保存到哪。
* IDEA编译java文件生成的.class文件是默认保存在out文件夹的，但是这个文件夹默认是隐藏的。
* External Libraries下面是jar包。

# IDEA配置

* 字体：File->settings->Editor->Font
* 自动补全：File->settings->keymap(齿轮下选择Duplicate不改变原来的快捷键，即复制一遍)
# 快捷操作

* 使用缩写再加上enter可以生成一些常用代码段。如`psvm`可以快捷生成`public static void main(String[] args){}`.
* `ALT + Enter`可以自动修复代码。
* `crtl + y`删除当前行，`ctrl + d`复制当前行。
* `ctrl + alt + l`格式化当前代码的排版。
* `ctrl + /`可以对光标所在行（也可以人为选中）前面加上//注释符。`ctrl + shift + /`生成/**/多行注释符。
* `alt + insert`可以自动添加方法。
* `alt + shift + 上下箭头`可以移动光标所在行。
* `ctrl + shit + F10`可以运行代码，`ctrl + shift + F9`调试代码。
* `shift + F6`可以对选中的文件进行重命名,所有用到该文件（如变量）的地方都会同时进行重命名。
* `ctrl + o`添加override的方法。
* `ctrl`按住ctrl再点击调用的方法名，可以直接去看该方法定义的源代码。

# idea报错

* 同一个包内的类不能使用：参见[blog](https://www.cnblogs.com/wanghongsen/p/12574807.html)