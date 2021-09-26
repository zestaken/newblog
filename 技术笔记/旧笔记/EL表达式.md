---
title: EL表达式
date: 2021-01-19 12:15:27
tags: [EL表达式, JSP, 后端]
categories: 技术笔记
---

# EL表达式概述

* 概念：Expression Language ,表达式语言
* 作用：替换和简化jsp页面中的代码的编写;
* 语法：`${表达式}`;
* jsp默认支持EL表达式：
  * 如果要忽略EL表达式：
    1. 设置jsp中的page指令：`isELIgnored = "true"`,忽略当前jsp页面中的所有EL表达式;
    2. `\${表达式}`:忽略当前这个EL表达式。
* 用法：
  * 运算，并在页面上显示运算结果;
  * 获取值。

# EL的运算

* 算术运算符：`+ ,-, *, / ,%`,其中`/`可以用`div`代替，`%`可以用`mod`代替。
* 比较运算符：`> < >= == !=`
* 逻辑运算符：`&&(and), ||(or), !(not)`
* 空运算符：`empty`:
  * 功能：用于判断字符串，集合，数组对象是否为null并且长度是否为0;
  * 示例：`${empty list}`,返回结果为boolean型。 一般常用`${not empty list}`:判断不为null或者长度不为0;

# EL获取值

* EL表达式只能从**域对象**中获取值;
* 语法：
  * `${域名称.键名}`:从指定域中获取指定键的值;
  * `${键名}`：表示依次从最小的域中查找是否有该键对应的值，知道找到为止，若没有找到，则返回空字符串。
* 域名称与域对象的对应关系：
  1. pageScope --> pageContext;
  2. requestScopt --> request;
  3. sessionScope --> session;
  4. applicationScope --> application(ServletContext);
* 示例：
  * 在域对象中存储了`name 张三`
  * 获取：`${requestScope.name}`将在页面上打印张三。
* EL获取对象值：
  * `${域名称.键名.属性名}`:本质上回去调用对象的getter方法，所以必须有对应的getXxx方法的属性才能调用（甚至只有getXxx的方法，没有实际对应的属性也可以）
  * 对应的对象必须首先以键值对的形式存入对应的域对象中，如:`request.setAttribute("user",user)`,将user对象存入request域中去。
* EL获取List集合：
  * `${域名称.键名[索引]}`
* EL获取Map集合的值
  * `${域名称.键名.key名称}`
  * `${域名称.键名["key名称"]}` 

# EL的隐式对象

* EL中不用创建可以直接使用的对象（类似jsp中的内置对象）。
* EL表达式中有11个隐式对象：
* pageContext对象：
  * 获取其他八个内置对象。
  * 示例：动态获取虚拟目录：`${pageContext.request.contextPath}`