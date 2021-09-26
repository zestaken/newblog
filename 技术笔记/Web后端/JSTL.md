---
title: JSTL
tags:
  - JSTL
  - JSP
  - 后端
categories:
  - 技术笔记
  - Web后端
date: 2021-01-19 17:09:49
---

# JSTL概述

* JSTL:JavaServer Pages Tag Library,JSP标准标签库。
* JSTL是由Apache组织提供的开源的免费的JSP标签;
* 作用：用于简化和替换JSP页面上的**java代码**;
* 使用步骤：
  1. 导入JSTL相关的jar包;
  2. 引入标签库：taglib指令：`<%@ taglib %>`
     * 一般将JSTL标签库，将前缀设为`c`。
  3. 使用标签。
* 示例：
```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

# 常用的JSTL标签

## if标签

* if：相当于java代码的if语句
* 属性：
  * test:必须的属性，接受boolean表达式，相当于原来if语句的条件;
    * 如果表达式为true，则显示if标签体内容，如果为false，则不显示标签体内容;
    * 一般情况下，test属性值会结合EL表达式一起使用,如：
```jsp
<c:if test="${not empty list}">
    遍历集合;
</c:if>
```
* if标签没有else情况，要想实现else情况，则可以再定义一个if标签。

## choose

* choose:相当于java代码的switch语句
* 使用choose标签声明：相当于switch声明;
* 使用when标签判断：相当于case;
* 使用otherwise标签做其他情况的声明：相当于default
* 示例：
```jsp
<c:choose>
    <c:when test="${number == 1}"> 1</c:when>
    <c:when test="${number == 2}"> 2</c:when>
    <c:otherwise>默认数字</c:otherwise>
</c:choose>
```

## forEach

* forEach:相当于java的for语句
* 作用：
  1. 完成重复的操作;
  2. 遍历容器。
* 完成重复的操作：相当于`for(int i = 0; i < 10; i++)`
  * 属性： 
    * begin:开始值，相当于i = 0;
    * end:结束值;
    * var：临时变量，相当于i;
    * step:步长，为1时相当于++;
    * varStatus:循环状态对象
      * index属性：容器中的元素的索引，从0开始;
      * count属性：循环次数，从1开始。
* 遍历容器：相当于`for(int i ： numbers)`
  * 属性：
    * items:容器对象;
    * var:容器中元素的临时变量;
    * varStatus:循环状态对象
      * index属性：容器中的元素的索引，从0开始;
      * count属性：循环次数，从1开始。
* 示例：
```jsp
<c:forEach items=“${list}" var="str" varStatus="s">
    ${s.index} ${s.count} ${str}<br>
</c:forEach>
```
