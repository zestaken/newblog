---
title: css
tags:
  - CSS
  - 前端
categories:
  - 技术笔记
  - Web前端
abbrlink: 52116
date: 2020-12-12 23:41:38
---

# css概述

* css概念：cascading style sheets,层叠样式表
  * 层叠：多个样式可以作用在一个html元素上，同时生效。
* 作用：
  * 功能较直接用html控制样式更加强大；
  * 将内容展示和样式控制分离
    * 降低耦合度，解耦
    * 让分工协作更容易
    * 提高开发效率
* css的使用：css与html结合的方式
  1. 内联方式：在**标签内**使用style属性指定css代码
     1. 如：`<div style="color:red;">hello css</div>`。
     2. 仅限于修改当前标签的内容样式，范围太小，且没有分离，一般不使用。
  2. 内部样式：在**head标签内**，定义style标签，style标签的标签体内容就是css代码
     1. 仅限于修改当前页面的内容
     2. 如：
   ```html
   <style>
    div{
        color:red;
    }
    </style>
    ```
  3. 外部样式：
    1.  定义css资源文件
    2. 在**head标签**内，定义**link标签**，引入外部的资源文件。  
    3. 如：
```html
<link rel="stylesheet" href="css/a.css">

<!--也可以通过下面方式引入css，但是不常用-->
<style>
    @import "css/a.css";
</style>
```

# css基本语法
[参考手册](https://www.w3school.com.cn/cssref/index.asp)
* 格式：
```
选择器 ｛
    属性名1：属性值1；
    属性名2：属性值2；
    ...
｝
```
* 选择器：筛选具有相似特征的元素
* 每一对属性需要**使用;隔开**，最后一对属性可以不加。

# 选择器

## 基础选择器

* id选择器： 选择具体的id属性值的元素,一个html页面中的id值唯一。
  * 语法：`#id属性值{}`
* 元素选择器：选择具有相同标签名称的元素,标签名可以自定义。
  * 语法：`标签名称{}`
  * 注意：id选择器的优先级高于元素选择器
* 类选择器：选择具有相同class属性值的元素
  * 语法：`.class属性值{}`
  * 注意：类选择器的优先级高于元素选择器的优先级，但是低于id选择器的优先级。**同一级的选择器，放在后面的可以覆盖前面选择器设置的相同的属性**。
* 示例：
```html
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <style>
        #div1{
            color: red;
        }

        p{
          color:green;
        }

        .class1{
          color:blue;
        }
    </style>

</head>
<body>
  <div id="div1">张杰</div>
  <p>张杰2</p>
  <p class="class1">zhangjie</p>
  <m class="class1">张杰</m><!--自定义的标签-->
</body>
```

## 扩展选择器

* 选择所有元素选择器：
  * 语法：`*{}`
* 并集选择器：选择两个选择器的所有内容
  * 语法：`选择器1，选择器2{}`
* 子选择器：筛选选择器1元素下的选择器2元素
  * 语法：`选择器1 选择器2{}`
  * 只有在选择器1元素包裹下的选择器2元素才会被选中。
  * 示例：如果是`div p{}`
```html
<div>
  <p>zhangjie1</p> <!--只有这一行元素才会被选中-->
</div>
<p>zhangjie2</p>
```
* 父选择器：筛选选择器2上的父元素选择器1
  * 语法：`选择器1>选择器2{}`
  * 只有其中包裹了选择器2元素的选择器1元素才会被选中。父选择器改变的是选择器1的样式。
  * 示例：`div>p{}`
```html
<!--只有这个包裹了p元素的div元素会被选中-->
<div>
  <p>zhangjie</p>
</div>

<!--下面这个div就不会被选中-->
<div>zhangjie2</div>
```
* 属性选择器：选择元素名称，属性名=属性值的元素
  * 语法：`元素名称[属性名:"属性值"]{}`
  * 一般用来选择input标签元素。
  * 示例；
```html
<head>
<style>
input[type="text"]{
  border:2px solid;
}
</style>
</head>

<input type="text" name="test">
```
* 伪类选择器：选择一些元素具有的状态
  * 语法：`元素:状态{}`
  * 状态：如`<a>`标签，具有如下状态
    * link:初始化的状态
    * visited:被访问过的状态
    * active：正在被访问的状态
    * hover:鼠标悬浮状态
  * 示例：
```html
<style>
a:link{
  color:pink;
}
a:hover{
  color:red;
}
a:active{
  color:yellow;
}
a:visited{
  color:green;
}
</style>
```
# 属性

## 字体、文本

* color:字体颜色，如：`color:#FF0000;`代表红色。
* font-size:字体大小，单位为px，如；`font-size:30px;`
* text-align；文本对齐方式。如：`text-align:center;`
* line-height:行高，单位也为px，整个文本范围所占的高度。如；`line-align:100px;`
  
## 边框

* boder:设置边框，是一个复合属性。
* 如：`border:1px solid red;`,表示，边框宽是1个像素，是实线，是红色。

## 尺寸

* 设置整个标签内容所占的范围大小。
* height：范围的高度，单位为px；如：`height:200px;`
* width:范围的宽度，单位为px：如:`width:200px;`

## 背景

* background:背景颜色，是一个复合属性。如；`background:url("img/logo.jpg") no-repeat center;`,意思为设置背景图片为logo.jpg 图片不要重复，图片在标签内容范围内居中。

## 盒子模型

* 用来控制布局。
* **margin** :外边距，和父盒子的边框的距离。
  * 可以用margin-left等属性来设置每一条边距，直接设置margin是设置四条边的边距。
* **padding**:内边距，和子盒子的边框的距离。
  * 默认情况下内边距会影响盒子的大小。
  * **box-sizing**:需要设置盒子的属性，让width和height就是盒子的大小。如；`box-sizing:border-box;`
* **float**:浮动属性，让不同盒子在顺序在同一行排列。如；`float:left`:左浮动；`float:right;`；有浮动。


