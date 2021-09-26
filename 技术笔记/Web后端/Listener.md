---
title: Listener
tags:
  - Java
  - Listener
  - 后端
categories:
  - 技术笔记
  - Web后端
abbrlink: 3986
date: 2021-01-21 11:20:42
---

# Listener概述

* JavaWeb三大组件之一（Servlet,Filter,Listener)
* 事件监听机制：
  * 事件：一件事情，如：点击。
  * 事件源：事件发生的地方。
  * 监听器：一个对象;
  * 注册监听：将事件，事件源，监听器绑定在一起。当事件源上发生某个事件后，执行监听器的代码。

# ServletContextListener

* ServletContextListener:一个接口，用来监听ServletContext对象的创建和销毁。
* 接口方法：
  * `void contextInitalized(ServletContextEvent sce)`:ServletContext对象创建后会调用该方法。
  * `void contextDestroyed(ServletContextEvent sce)`:ServletContext对象被销毁之前会调用该方法。
* 使用步骤：
  1. 定义一个类，**实现ServletContextListener接口**;
  2. 复习方法;
  3. 配置：
     *  web.xml:
        1. 只用配置监听器的类名。
        2.可以指定初始化参数`<context-param>` 
```xml
<listener>
    <listener-class>监听器类的完全限定名</listener-class>
</listener>

<context-param>
    <param-name>参数名</param-name>
    <param-value>参数内容</param-value>
</context-param>
```
  * 注解配置：无需写参数。
    * `@WebListener`