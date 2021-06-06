---
title: AJAX
date: 2021-01-25 08:02:19
tags: [JS, AJAX, 前端]
categories: 技术笔记
---

# AJAX概述

* 概念：ASynchronous JavaScript And XML,异步的JavaScript和XML
* 异步和同步：
  * 异步：客户端向服务器端发送请求后，**无需等待服务器端的响应**。在服务器处理请求的过程中，客户端可以进行其他操作。
  * 同步：客户端向服务器端发送请求后，**必须等待服务器端的响应**。在等待的期间，客户端不能做其他操作。
* 作用：
  * Ajax是一种在无需加载整个网页的情况下，能够**更新部分网页**的技术。
  * 通过在后台与服务器进行的少量数据交换，Ajax可以使网页实现异步更新，即可以在不重新加载整个网页的情况下，对网页的某部分进行更新。传统的网页（不使用Ajax)如果需要更新内容，必须重载整个页面。

# AJAX的实现方式

1. 原生的JS实现方式
2. JQuery实现方式


## 原生的JS实现方式

1. 定义一个用于异步请求的方法：
   1. `<input type="button" value="发送异步请求" onclick="fun();">`
2. 创建发送异步请求的对象：
```javascript
var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
```
3. 建立连接：
    1. `xmlhttp.open("GET","test1.txt",true)`，参数：
       1. 请求方式：GET，POST
          1. GET方式：请求参数在URL后面拼接，send方法为空参,如：`xmlhttp.open("GET","ajaxServlet?username=zhangjie",true)`
          2. POST方式：请求参数在send方法中定义，如：`xmlhttp.send("username=zhangjie")`
       2. 请求的URL:文件在服务器上的位置
       3. 同步或者异步请求：true（异步）或者false（同步）
4. 发送请求：
   1. `xmlhttp.send(string)`:string参数仅用于POST请求。
5. 接受并处理来在服务器的响应结果：
   1. 获取方式：`xmlhttp.responseText`或者`xmlhttp.responseXML`
      1. responseText属性：获得字符串形式的响应数据。
      2. responseXML属性：获得XML形式的响应数据。
    2. 获取时间：当XMLHttpTRequest对象的就绪状态(readyState属性)改变时，触发事件onreadystatechange
      * onreadystatechange	存储函数（或函数名），每当 readyState 属性改变时，就会调用该函数。
      * readyState:存有XMLHttpRequest 的状态。从 0 到 4 发生变化。
        * 0: 请求未初始化
        * 1: 服务器连接已建立
        * 2: 请求已接收
        * 3: 请求处理中
        * 4: 请求已完成，且响应已就绪
       * status:XMLHttpState对象的状态	
        * 200: "OK"
        * 404: 未找到页面
    3. 获取实现：
```javascript
xmlhttp.onreadystatechange=function()
  {
      //判断readyState的状态是否为4,判断status响应状态码是否为200
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
        //获取服务器的响应结果
    document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
    }
  }
```
## JQuery实现方式

1. `$.ajax()`:
   1. 语法：`$.ajax({键值对});`
   2. 示例：
```javascript
$.ajax({
    url:"ajaxServlet", //请求路径
    type:"POST", //请求方式
    data:("username":"jack","age":23),
    success:function(data){
        alert(data+"成功啦");
    }, //响应成功后的回调函数
    error:function() {
        alert("出错啦");
    }, //表示如果请求响应如果出现错误，会执行的回调函数

    dataType:"text" //设置接受到的响应数据格式
})
```
2. `$.get()`:发送get请求
   1. 语法：`$.get(url,[data], [callbacke],[type])`
      1. url:请求路径，必须有;
      2. data:请求参数;
      3. callback:回调函数;
      4. type:响应结果类型。
3. `$.post()`:发送post请求
   1. 语法：同get类似。
   2. 示例：
```javascript
$.post("ajaxServlet",(username:"zhangjie"), function(data){
    alert(data);
},"text");
```
* `serialize()`:通过序列化**表单**值，创建URL编码文本字符串。您可以选择一个或多个表单元素（比如input及/或文本框），或者 form 元素本身。序列化的值可在生成 AJAX 请求时用于 URL 查询字符串中(即作为data参数）。