---
title: Filter
tags:
  - Java
  - Filter
  - 后端
categories:
  - 技术笔记
  - Web后端
abbrlink: 37028
date: 2021-01-20 21:48:09
---
# Filter概述

* JavaWeb的三大组件（servlet，Filter，Listener)之一。
* web中的过滤器：当访问服务器的资源时，过滤器可以将每次请求拦截下来，完成一些特殊的功能;
* 作用：
  * 一般用于完成**通用的操作**,如：登录验证，统一编码处理，敏感字符过滤。

# Filter快速入门

* 步骤：
  1. 定义一个类，实现**接口Filter**,注意这个Filter接口是java.servlet包下的（因为java.util包下也有Filter接口）;
  2. 复写方法，（主要起作用的方法是doFilter)
  3. 配置拦截路径：
     1. 在web.xml文件中去配置;
     2. 用注解`@WebFilter`注解，如：`@WebFilter("/demo1")`代表访问/demo1目录下的资源的时候执行拦截。
* 示例：
```java

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class FilterDemo1 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("I'm Filter!");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }
}
```

# web.xml配置方式

* 配置filter;
* 配置filter的拦截路径。
* 示例：
```xml
<filter>
    <filter-name>过滤器的名字</filter-name>
    <filter-class>过滤器的完全限定类名</filter-class>
</filter>

<filter-mapping>
    <filter-name>过滤器的名字</filter-name>
    <url-pattern>拦截的路径</url-pattern>
</filter-mapping>
```

# 过滤器的执行流程和生命周期方法

* 执行流程：
  1. 执行过滤器，拦截请求，对request对象请求执行增强操作;
  2. 放行请求,执行放行后对应的资源;
  3. 执行放行后的语句，对response对象响应执行增强操作。
* 生命周期方法：
  1. `init()`:
    * 在**服务器启动**后，会创建Filter对象，然后调用init方法。 
    * 只执行一次，用于**加载资源**。
  2. `doFilter()`:
     * 每一次请求被拦截的资源时，会执行。
     * 会执行多次。
  3. `destroy()`:在**服务器关闭后**,Filter被销毁。
     1. 如果服务器是正常关闭，则会执行destroy方法;
     2. 只执行一次，用于**释放资源**。
      
# 过滤器配置

* 过滤器拦截路径配置：
  1. 具体资源路径：`/index.jsp`,只有访问index.jsp资源时，过滤器才会被执行;
  2. 拦截目录：`/user/*`,访问/user下的所有资源时，过滤器都会被执行;
  3. 后缀名拦截：`*.jsp`，访问所有后缀名为jsp的资源时，过滤器都会被执行。
  4. 拦截所有资源：`/*`，访问所有资源时，过滤器都会被执行。
* 拦截方式配置：
  * 注解配置：设置dispatcherTypes属性：
    1. REQUEST:默认值，浏览器直接请求资源时拦截;
    2. FORWARD：转发访问资源时拦截;
    3. INCLUDE：包含访问资源时拦截;
    4. ERROR：错误跳转资源时拦截;
    5. ASYNC:异步访问资源时拦截。
  * web.xml配置：设置`<dispathcer></dispathcer>`标签。
  * 示例：
    * `@WebFilter(value = "/index.jsp",dispatcherTypes = DispatcherType.REQUEST)`：拦截直接访问index.jsp资源的请求;
    * `@WebFilter(value = "/index.jsp",dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD})`:拦截直接请求index.jsp或者转发访问index.jsp资源。
    * web.xml配置：拦截直接请求。
```xml
<filter>
    <filter-name>过滤器的名字</filter-name>
    <filter-class>过滤器的完全限定类名</filter-class>
</filter>

<filter-mapping>
    <filter-name>过滤器的名字</filter-name>
    <url-pattern>拦截的路径</url-pattern>
    <dispatcher>REQUEST</dispatcher>
</filter-mapping>
```

# 过滤器链

* 过滤器的先后顺序：
  * 注解配置：按照**类名的字符串比较规则比较，值小的先执行
    * 如： AFilter和BFilter，AFilter先执行;
    * 如：FilterDemo6和FilterDemo17,FilterDemo17先执行。
  * web.xml配置：`<filter-mapping>`标签谁定义在上面，谁先执行。 