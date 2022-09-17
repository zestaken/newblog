---
title: MVC开发模式与三层架构
date: 2021-01-19 09:03:12
tags: [MVC, 后端]
categories: 技术笔记
---
# 演变历史

1. 早期只有servlet，只能使用response输出标签数据，很麻烦。
2. 后来又有jsp，简化了servlet的开发;
3. 如果过度使用jsp，在jsp中既有大量的java代码，又写html,造成难以维护，难以分工协作。
4. 再后来，java的web开发，借鉴MVC开发模式，使得程序的设计更加的合理性。

# MVC开发模式

* M:Model,模型。
  * 完成具体的业务操作，如：查询数据库，封装对象。
  * 一般使用JavaBean完成。
* V:view,视图。
  * 展示数据;
  * 使用JSP来完成。
* C：Controller,控制器。
  * 获取用户的输入;
  * 调用模型;
  * 将数据交给视图进行展示。
  * 使用Servlet来完成。
* 优点：
  * 耦合性低，方便维护，可以利于分工协作；
  * 重用性高（主要指控制器和模型）
* 缺点：
  * 使得项目的架构便复杂，对开发人员的要求高。

# 三层架构

* 界面层（表示层/web层）
  * 用户看得到的界面。用户可以通过界面上的组件和服务器进行交互。
  * 接收用户参数，封装数据，调用业务逻辑层完成处理，转发jsp页面完成显示。
  * 用SpringMVC框架实现。
* 业务逻辑层(service层)：
  * 处理业务逻辑的代码;
  * 组合DAO层中的简单方法，形成复杂的功能（业务逻辑操作）。
  * 用Spring框架实现。
* 数据访问层(DAO层：Data Access Object)：
  * 操作数据库存储文件。
  * 定义了对于数据库最基本的CRUD操作;、
  * 用MyBatis框架实现。
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20210119193423.PNG)