---
title: 会话技术-session
date: 2021-01-18 10:04:36
tags: [Java,会话技术,后端]
categories: 技术笔记
---

# session概述

* 概念：服务器端会话技术，在一次会话的多次请求间共享数据，将数据保存在服务器端的对象（HTTPSession)中。
* session的特点：
  * session用于存储一次会话的多次请求数据，存在**服务器端**;
  * session可以存储**任意类型，任意大小**的数据。
* session与cookie的区别：
  * session存储在服务器端，cookie存储在客户端;
  * session没有数据大小限制，而cookie有;
  * session数据存储在服务器端相对安全。





# session快速入门

1. 获取HttpSession对象：`HttpSession session = request.getSession();`
2. 使用HttpSession对象：
   1. 获取属性：`Object getAttribute(String name)`
   2. 设置属性：`void setAttribute(String name, String value);`
   3. 删除属性：`void removeAttribute(Strin name)`

# session的基本原理

* 在一次会话范围内多次获取的Session对象是同一个Session对象。
* Session的实现是依赖于Cookie的：
  1.  在第一次请求创建了Session对象之后，服务器的`set-cookie`响应头之中会携带Session的id信息;
  2.  在客户端进行第二次请求时，会自动发送一个cookie头，在这个头中包含有`set-cookie`中携带回来的Session对象的Id信息;
  3.  服务器接受到Session的id信息后，或检查服务器中有无该session对象，如果有，则继续使用。 
* 当客户端关闭后，服务器不关闭，两次获取的session：
  * 默认情况下，不相同;
  * 客户端关闭后，session也能相同方法：
    * 将携带JSESSIONID信息的cookie持久化存储到本地：
      1. 创建cookie;
      2. 键设置为JESSIONID;
      3. 设置最大存活时间;
      4. 将cookie在响应消息中发回客户端。
```java
Cookie c = new Cookie("JESSIONID", session.getId());
c.setMaxAge(60*60);//持久化到磁盘1个小时
response.addCookie(c);
``` 
* 客户端不关闭，服务器关闭后，两次读取的session：
  * 两次获取的session不是同一个，但是要确保数据不丢失。
  * seession的钝化：在服务器正常关闭之前，将session对象序列化到硬盘上;
  * session的活化：在服务器启动之后，将session文件转化为内存中的session对象，实现数据的继续使用。
  * tomcat服务器会自动完成session的钝化和活化，但是IDEA不会。
* session的失效时间：
  1. 服务器关闭时自动销session对象;
  2. session对象调用invalidate()方法进行自毁;
  3. session的默认失效时间（在这段时间内，session没有任何操作或这变化，则被销毁）：
     1. 修改默认失效时间：在服务器的`web.xml`配置文件中找到：
```xml
<session-config>
    <session-timeout>30</session-timeout>
</session-config>
```