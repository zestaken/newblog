---
title: HTML
date: 2020-12-08 09:05:40
tags: [HTML, 前端]
categories: 技术笔记
---

# 概念

* 最基础的网页开发语言。
* HTML：Hyper Text Markup Language 超文本标记语言。
* 超文本：超文本是用超链接的方法，将不同空间的文字信息组织在一起的网状文本。
* 标记语言：
  * 由标签（<标签内容>）构成的语言，如html，xml
  * 标记语言不是编程语言，没有逻辑性。
* HTML 文档描述网页,HTML 文档包含 HTML 标签和纯文本,HTML 文档也被称为网页


# 快速入门

* html文档的后缀名：html或者htm
* 标签分为：
  * 围堵标签：有开始标签和结束标签。如：`<html></html>`
  * 自闭和标签：开始标签和结束标签在一起。如：`<br/>`
* 标签可以被嵌套：
  * 需要正确嵌套，不能你中有我，我中有你
  * 错误：`<a><b></a></b>`
  * 正确：`<a><b></b></a>`
* 在**开始标签**中可以定义属性。属性是由键值对构成，值需要用引号(单双都可，但是要统一)引起来。
* html的标签不区分大小写，但是建议使用小写
* 示例：
```html
<html>

    <head>
            <title>title</title>
    </head>

    <body>
        <font color='red'>Hello world</font><br/>
        <font color='green'>Hello world</font>
    </body>
</html>
```
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201208092240.jpg)

# 标签

* [参考手册](https://www.w3school.com.cn/tags/index.asp)

## 文件标签

* 构成html最基本的标签。
* html:html文档的根标签,<html> 与 </html> 之间的文本描述网页
* head：头标签。用于指定html文档的一些属性，引入外部的资源。
* title:标题标签；
* body；体标签，放网页显示内容.`<body>` 与 `</body>` 之间的文本是**可见**的页面内容。
* `<!DOCTYPE>`:定义文档类型标签,html5中定义文档类型的方式。如：`<!DOCTYPE html>`

## 文本标签

* 注释：`<!--注释内容 -->`(后面没有感叹号)
* 标题：`<h1>标题内容</h1>`共有6级标题。
* 定义段落：`<p>这是段落内容</p>`,p 元素会自动在其前后创建一些空白。浏览器会自动添加这些空间，您也可以在样式表中规定。
* 换行：`<br>`，如：`白日依山尽，<br>黄河入海流`,注意这是一个**自闭合标签**无需结束标签。（`<br/>`具有相同的效果）。
* 定义水平线：`<hr>`或者`<hr/>`可以定义一条水平线。
* 粗体：`<b>需要粗体内容</b>`。
* 斜体：`<i>需要斜体的内容</i>`.
* 类似打字机或者等宽的文本效果：`<tt>需要等宽显示的内容<tt>`
* 呈现大号字体效果:`<big>需要显示大号字体的内容</big>`
* 呈现小号字体效果:`<small>需要显示小号字体的内容<small>`
* 规定文本的字体、字体尺寸、字体颜色:`<font color="red", size="5",face="楷体"> 我是红色</font>`,face是指的字体。**已经不建议使用，改变样式现在用css**
* 文本居中：`<center>需要居中的内容</center>`,居中是相对于父元素来说的。示例：
```html
<center>
<font color="red", size="5",face="楷体"> 我是红色</font>
</center>
```
* 属性：
  * color:颜色：
    * 英文单词：red,green,blue等
    * rgb(值1，值2，值3)；rgb分别是红，绿，蓝三种颜色的占比。值的范围：0~255（不常用）
    * `#值1值2值3`:值的范围：00~FF之间。效果也是通过三种颜色的占比来配色。如：`#FF00FF`。
  * width:
    * 数值:width='20',数值的单位，默认是像素px(像素)；
    * 数值%：width='50%',相对于父元素的占比。
  * align:对齐方式
    * center:居中
    * left：左对齐
    * right：右对齐

## 图片标签

* `<img />`:图片标签是自闭合标签
* 属性：src
  * src后输入图片的位置；
  * 相对路径：
    * `./...`代表当前目录；如：`./image/1.jpg`
    * `../...`代表上一级目录：如：`../image/2.jpg`
  * 绝对路径也可以使用
  * 直接使用链接也可以
* 示例：
```html
<img src = "https://gitee.com/zhangjie0524/picgo/raw/master/img/20201208092240.jpg">
```

## 列表标签

* 有序列表
  * ol:定义有序列表（order list）
    * 通过type属性来定义顺序表示的方式，如type="A",则用A,B，C。。。表示顺序，type="I",则表示用罗马数字表示顺序
    * 通过start属性来定义从哪里开始计算顺序，如：start="5",则顺序从5，6，7，8开始下去。
  * li：定义**列表的项目**(list)
```html
<ol>
  <li>Coffee</li>
  <li>Tea</li>
  <li>Milk</li>
</ol>
```
效果：
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201211102931.jpg)
* 无序列表：
  * ul:定义无序列表
    * 通过type属性来定义列表项前面的符号样式：有disc，square，circle三种样式。
  * li：定义列表的项目
  * 示例：
```html
<ul>
  <li>Coffee</li>
  <li>Tea</li>
  <li>Milk</li>
</ul>
```
效果：
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201211103137.jpg)

## 链接标签

* `<a></a>`:定义超链接
* 属性：
  * href：指定访问资源的url
    * 既可以是网页链接
    * 也可以是本地的资源，如：`./5_列表标签.html`。
  * target:
    * "_self",在当前页面打开链接的网页
    * "_blank":在一个空白标签页打开链接的网页
* 示例：
```html
<a href = "https://gitee.com/zhangjie0524/picgo/raw/master/img/20201211103137.jpg" target = "_blank">我是超链接</a>
```

## div和span

* `<span></span>`:没有任何样式，文本信息在**一行展示**，是行内标签，内联标签。
* `<div></div>`:没有任何样式，每一个div占满一整行，是块级标签。
* 这两个是结合css来控制样式的。

## 语义化标签

* html5中为了提高程序的可读性，提供了一些标签
* 如：`<header>`是页眉
* 如：`<footer>`是页脚。

## 表格标签

* html中的表格只有行的概念。所谓的“列”是行中的单元格。
* table；定义表格
  * width:宽度
  * border:边框，会出现两条线，一条是行的边框线，一条是单元格的边框线。数字定义的是边框的宽度（像素为单位）
  * cellpadding；定义内容和单元格的距离
  * cellspaciing:定义单元格之间的距离。如果指定为0，则单元格的线合并为一条。
  * bgcolor:背景色
  * align：对齐方式
* tr：定义行
  * 可以定义每一行的属性，如背景色，对齐方式等。
* td:定义单元格
  * 属性colspan:合并列
  * 属性rowspan；合并行
  * 属性align:也可以设置单元格的对齐方式
* th:定义表头单元格
* 示例：
```html
<table border="1" width="50%" cellpading="0" cellspacing="0" align="center" bgcolor="red">
  <!-- 表头行-->
    <tr>
    <!-- 表头单元格 -->
        <th>编号</th>
        <th>姓名</th>
        <th>成绩</th>
    </tr>
    <tr>
        <td>1</td>
        <td>小龙女</td>
        <td>100</td>
    </tr>
    <tr>
        <td>2</td>
        <td>杨过</td>
        <td>60</td>
    </tr>
</table>
```
* 效果：
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201212111419.jpg)
* caption:表格的标题
* thead,tbody,tfoot；分别定义表格的头部分，体部分和脚部分，与语义标签类似，起到增强代码可读性的作用。


## 表单标签

* 表单概念：用于采集用户输入的数据，用于和服务器进行交互。

### form标签

* form标签：用于定义表单，可以定义一个范围，范围表示采集用户数据的页面范围。
  * action属性：指定数据提交的url
  * method属性：指定提交方式(一共有7种，常用以下两种)
    * get:
      * 请求的参数会在地址栏中显示，封装在请求行中。
      * 请求参数大小有限制
      * 不安全
    * post:
      * 请求参数不会在地址栏中显示，会封装在请求体中。
      * 请求参数的大小没有限制
      * 较为安全。
* 表单项中的数据想要被提交，必须**指明name属性**，且要位于form标签的范围之内。


### 表单项标签

#### input标签

* input:可以通过**type**属性值，改变展现元素的格式。
  * type属性：
    * text:文本输入框，是**默认**的type属性值。
      * placeholder；指定输入框的提示信息，当输入框的内容发生变化之后，会自动清空提示信息；
    * password:密码输入框
      * 效果是输入的密码都变为密文，不可见。
    * radio；单选框
       * 要想让多个单选框实现单选的效果，则多个单选框的**name属性值**必须一样。
       * 一般会给每一个单选框提供**value属性值**，指定其被选中后提交的值。
       * **checked属性值**可以指定默认值，即没有选择的时候会默认选择的值。设置方式：`checked="checked"`或者直接写一个`checked`
    * checkbox复选框：
       * 一般会给每一个单选框提供**value属性值**，指定其被选中后提交的值。
       * **checked属性值**可以指定默认值，即没有选择的时候会默认选择的值。设置方式：`checked="checked"`或者直接写一个`checked`
     * file；文件选择框
     * hidden:隐藏域，用于提交一些信息。
     * 按钮：
       * submit:提交按钮，可以提交表单,用**value属性**定义按钮显示的内容。
       * button:普通按钮，**没有提交表单的功能**。
       * image:图片提交按钮，通过**src**属性指定图片。
     * color：取色器（html5新增的属性）
     * date:定义date控件，包括年月日
     * datetime-local：定义date和time控件，包括年月日时分。
     * email：定义用于输入邮箱地址的输入框，如果输入的数据不符合邮箱格式规范，则会报错无法提交。
     * number：定义用于输入数字的输入框，不能输入其它数据类型。
  * label属性：指定输入项的文字描述信息
    * label的**for属性**值与input的**id属性**值相对应，如果对应了，则点击label区域，会让input输入框获取焦点。
  * 示例：
```html
<form action="#", method="post">
    <label for="username">用户名：</label><input type="text" name = "用户名" id = "username" placeholder="请输入用户名"> <br>
    <label for="password">密码：</label><input type="password" name = "password" id="password" placeholder="请输入密码"><br>
    性别：
        <input type="radio" name="gender" value="male" checked>男
        <input type="radio" name="gender" value="female"  >女<br>
    爱好：
        <input type="checkbox" name="hobby" value="java" checked>java
        <input type="checkbox" name="hobby" value="c" >c<br>
    <input type="submit" value="登录">
</form>
```
  * 效果：
 ![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201212175934.jpg)

#### select标签

* select:下拉列表
  * 子元素：option，指定列表项
  * 属性selected可以指定默认选项,如果不指定默认选项，则显示的是第一个列表项。
* 示例：
```html
  省份：<select name="province">
            <option value="">--请选择--</option>
            <option value="1" >北京</option>
            <option value="2" selected>上海</option>
        </select><br>
```
#### textarea标签

* textarea:文本域标签
  * cols：指定文本框的列数，每一行有少个字符；
  * rows：指定文本框的默认行数，行数如果不够会自动扩充。

# 示例

* 代码：
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<body>
    <!--定义表单-->
    <form action="#" method="post">
            <table border="1" align="center" width="500">
                    <tr>
                        <td><label for="username">用户名</label></td>
                        <td><input type="text" name="username" id="username"></td>
                    </tr>
                    <tr>
                        <td><label for="password">密码</label></td>
                        <td><input type="password" name="password" id="password"></td>
                    </tr>
                    <tr>
                        <td><label for="email">Email</label></td>
                        <td><input type="email" name="email" id="email"></td>
                    </tr>
                    <tr>
                        <td><label for="name">姓名</label></td>
                        <td><input type="text" name="name" id="name"></td>
                    </tr>
                    <tr>
                        <td><label for="phonenumber">手机号</label></td>
                        <td><input type="text" name="phonenumber" id="phonenumber"></td>
                    </tr>
                    <tr>
                        <td><label >性别</label></td>
                        <td><input type="radio" name="gender" value="male">男
                            <input type="radio" name="gender" value="female">女
                        </td>
                    </tr>
                    <tr>
                        <td><label for="date">出生日期</label></td>
                        <td><input type="date" name="date" id="date"></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="注册"></td>
                    </tr>
            </table>
    </form>
</body>
</html>
```
* 效果
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201212201738.jpg)