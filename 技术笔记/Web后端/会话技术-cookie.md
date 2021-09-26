---
title: 会话技术-cookie
tags:
  - Java
  - 会话技术
  - 后端
categories:
  - 技术笔记
  - Web后端
abbrlink: 60901
date: 2021-01-13 15:52:25
---

# 会话技术

* 会话：一次会话中包含多次请求和响应。
  * 一次会话：浏览器第一次给服务器资源发送请求，会话建立，直到有一方断开为止;
* 功能：在一次会话的多次请求间，**共享数据**。
* 方式：
  1. 客户端会话技术：Cookie
  2. 服务器端会话技术：Session

# Cookie概念

* 客户端会话技术，将数据保存到客户端，来实现多次请求数据共享。
* Cookie实现原理:基于响应头`set-cookie`和请求头`cookie`实现。
* 一次发送**多个Cookie**:
  * 可以同时创建多个Cookie对象，使用reponse调用多次addCookie方法发送cookie即可。
* cookie的特点：
  1. cookie存储数据在客户端浏览器（不安全）
  2. 浏览器对于单个**cookie的大小**有限制（一般为4kb);
  3. 浏览器对同一个域名下的**总cookie数量**也有限制(一般为20个)。 
* cookie的作用：
  1. cookie一般用于存储少量不太敏感的数据;
  2. 在**不登录**的情况下，完成服务器对客户端的身份识别。

# 快速入门

1. 创建cookie对象，绑定数据:`new Cookie(String name, String value);`
   1. 同名的cookie的对象的数据会被覆盖掉。
2. 让response对象发送Cookie对象：`response.addCookie(Cookie cookie);`
3. request对象会自动从客户端携带Cookie对象,我们从request对象获取Cookie:`Cookie[] request.getCookies()`


# cookie在浏览器中保存的时间

* 默认情况下，**当浏览器关闭后**，Cookie数据被销毁。
* 持久化存储：
  * 使用Cookie对象中的setMaxAge对象。
  * `setMaxAge(int seconds)`
    * 正数：将Cookie数据写到**硬盘**中，来持久化存储。参数值指定cookie在磁盘中的存活时间，到时间删除硬盘中的cookie文件。
    * 负数:默认值，当浏览器被关闭后删除cookie。
    * 零：删除当前的cookie信息。

# Cookie的共享范围

* 在一个服务器的不同web项目间：
  * 默认情况下cookie不能在他们之间共享;
  * cookie对象的`setPath(String path)`方法：设置cookie的获取范围，默认情况下，设置的是当前项目的虚拟路径;
  * 如果需要**在一个服务器的多个项目间共享cookie**，可以将path设置为`/`,代表当前服务器的根目录。
* 在不同的tomcat服务器间共享cookie：
  * 通过cookie对象的`setDomain(String path)`方法。
  * 如果设置的一级域名相同，那么多个服务器之间cookie也可以共享，如：
    * `setDomain(".baidu.com")`，则`tieba.baidu.com`和`news.baidu.com`中的cookie可以共享。



    