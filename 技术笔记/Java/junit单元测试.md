---
title: junit单元测试
tags:
  - Junit
  - Java
  - 后端
categories:
  - 技术笔记
  - Java
abbrlink: 39893
date: 2020-09-29 15:42:22
---
# junit简介

* JUnit 是一个 Java 编程语言的单元测试框架。JUnit 在测试驱动的开发方面有很重要的发展，是起源于 JUnit 的一个统称为 xUnit 的单元测试框架之一。

# 在IDEA中配置junit

* 安装JUnit Generator V2.0插件。`File-->settings-->Plguins`.

# junit的基本使用

* 测试方法必须使用 `@Test` 修饰
* 测试方法必须使用` public void `进行修饰，**不能带参数**
* 一般使用单元测试会新建一个**test 目录存放测试代码**，在生产部署的时候只需要将 test 目录下代码删除即可
* 测试单元中的每个方法必须可以**独立测试**，方法间不能有任何依赖
* 测试类一般使用 **Test 作为类名的后缀**
* 测试方法使一般用**test 作为方法名的前缀**
  
  ## 常用注解

* @Test:将一个普通方法修饰成一个**测试方法**.
* @Before：会在每一个测试方法被**运行前**执行一次
* @After：会在每一个测试方法**运行后**被执行一次
* @Ignore：所修饰的测试方法会被测试运行器忽略.

## 基本调用方法

* 在需要测试的类中，使用`alt + insert`可以调用出一个窗口，在这个窗口中点击junit即可。