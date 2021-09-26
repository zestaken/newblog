---
title: Bootstrap
date: 2020-12-23 19:23:16
tags: [前端, BootStrap]
categories: 技术笔记
---

# Bootstrap概述

[官网](https://www.bootcss.com/)
* 基于HTML，CSS，JavaScript的前端开发框架。
  * **框架**：一个半成品软件，开发人员可以在框架基础上，再进行开发，简化编码。
* 优点：
  * 定义了很多**css样式**和**js插件**。
  * **响应式布局**：同一套页面可以兼容不同分辨率的设备。
* 快速入门：
  1. [下载Bootstrap](https://v3.bootcss.com/getting-started/#download)
  2. 在项目中将三个文件夹复制进来；
  3. 创建html文件，引入必要的资源文件（即下载的文件夹中的文件）
     1. jquery的min.js文件需要手动去[下载](https://www.jq22.com/jquery-info122)并放到js文件夹下
     2. 输入的寻找资源文件的路径要与当前html所在位置相匹配。
```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="js/bootstrap.min.js"></script>

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="dist/html5shiv.min.js"></script>
    <script src="dest/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<h1>你好，世界！</h1>
</body>
</html>
```

# 响应式布局

* 同一套页面可以兼容不同分辨率的设备。
* 实现：依赖于**栅格系统**。
  * 栅格系统：将一行平均分为**12个格子**，可以指定元素占几个格子。
* 步骤：
  * 定义容器。相当于table，通过**class**设置
    * 容器分类：
        1. container:两边有留白，除非设备特别小。
        2. container-fluid：每种设备都是占100%
  * 定义行。相当于tr ，通过class设置为row
  * 定义元素。指定该元素在不同的设备上，所占格子的数目。样式`col-设备代号-格子数目`
    * 设备代号：
        1. xs: 超小屏幕 手机(<768px)
        2. sm: 小屏幕 平板(>=768px)
        3. md: 中等屏幕 桌面显示器(>=992px)
        4. lg: 超大屏幕 大桌面显示器(>=1200px)
* 注意：
  * 一行中如果格子数目超过12，则**超出部分自动换行**
  * 栅格类属性可以**向上兼容**。栅格类适用于与屏幕宽度大于或者等于分界点大小的设备。
  * 栅格类属性**不向下兼容**，如果真实设备宽度**小于**了设置栅格类属性的设备代号的最小值，会**一个元素占满一整行**
* 示例：
```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="js/jquery-3.5.1.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="js/bootstrap.min.js"></script>
    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <![endif]-->
    <style>
        .inner {
            border:1px solid red;
            width: 50px;
            height: 50px;
        }
    </style>

</head>
<body>
<!--定义容器-->
<div class="container-fluid">
    <!--定义行-->
    <div class="row">
        <!--定义元素-->
        <div class="col-lg-1 col-sm-2 inner">栅格</div>
        <div class="col-lg-1 col-sm-2 inner">栅格</div>
        <div class="col-lg-1 col-sm-2 inner">栅格</div>
        <div class="col-lg-1 col-sm-2 inner">栅格</div>
        <div class="col-lg-1 col-sm-2 inner">栅格</div>
        <div class="col-lg-1 col-sm-2 inner">栅格</div>
        <div class="col-lg-1 col-sm-2 inner">栅格</div>
        <div class="col-lg-1 col-sm-2 inner">栅格</div>
        <div class="col-lg-1 col-sm-2 inner">栅格</div>
        <div class="col-lg-1 col-sm-2 inner">栅格</div>
        <div class="col-lg-1 col-sm-2 inner">栅格</div>
        <div class="col-lg-1 col-sm-2 inner">栅格</div>
    </div>
</div>

</body>
</html>
```

# css样式和js插件

## 全局css样式

* 使用时[查阅文档](https://v3.bootcss.com/css/#overview)，通过设置**class**属性实现。
* 按钮：
  * 设置class实现样式，如：`class="btn btn-default"`
* 图片：
  * 占比，如：`class="img-responsive"`
  * 形状等
* 表格
* 表单等。。。

## 组件

* [参考文档](https://v3.bootcss.com/components/),需要在文档中复制一段代码来实现对应的功能。
* 导航条；
* 分页条等。。。

## js插件

* [文档](https://v3.bootcss.com/javascript/),需要在文档中复制一段代码来实现对应的功能。
* 轮播图(Carousel)等。。。