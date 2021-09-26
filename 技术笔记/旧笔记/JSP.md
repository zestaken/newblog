---
title: JSP
date: 2021-01-13 23:48:38
tags: [JSP, 后端]
---
# JSP概念

* JSP:Java Server Pages:java服务器端页面
* 一个特殊的页面，其中既**可以指定定义html标签，又可以定义java代码**，用于简化书写。
* JSP的注释：
  * html注释： `<!-- -->`，只能用来注释html代码片段;
  * JSP注释：`<%-- --%>`,可以注释所有，推荐使用。

# JSP原理

* JSP文件本质上是一个Servlet类。
* 服务器请求JSP资源之后，服务器会先将JSP文件转换为一个java文件，然后再编译成.class文件，由.class文件提供服务。

# JSP脚本

* JSP脚本：JSP定义java代码的方式。
* `<% code %>`:定义的java代码，在servlce方法中。service方法中可以定义什么，该脚本中就可以定义什么。
* `<%! code %>`:定义的java代码,在JSP转换后的java类的成员位置。（定义该类的全局变量）
* `<%= code %>`:定义的java代码，会输出到页面上。输出语句中可以定义什么，该脚本中就可以定义什么。

# JSP的内置对象

* 在JSP页面中**不需要获取和创建**，可以直接使用的对象（其本质是JSP内部已经提前创建好了）
* JSP一共有9个内置对象：
  * pageContext:
    * 对应java真实类型：PageContext
    * 作用：域对象，当前页面共享数据，还可以获取其他八个内置对象;
  * request:
    * 对应java真实类型：HttpServletRequest
    * 作用：域对象，一次请求的多个资源（转发）
  * session:
    * 真实类型：HttpSession
    * 作用：域对象，一次会话间的多个请求间。
  * application:
    * 真实类型：ServletContext
    * 作用：域对象，所有用户间共享数据。
  * response:
    * 真实类型：HttpServletResponse
    * 作用：响应对象
  * page:
    * 真实类型：Object
    * 作用：当前页面（Servlet)的对象，即`this`。
  * out:字符输出流对象。可以将数据输出到页面上，和`response.getWriter()`类似。
    * 真实类型：JspWriter
    * 作用：输出流对象，将数据输出到页面上。
    * `response.getWriter()`和`out.write()`的区别：
      * 在他tomcat服务器真正给客户端做出响应之前，会先找response的缓冲区数据，再找out缓冲区数据。
      * `response.getWriter()`数据输出永远在`out.write()`之前。
  * config:
    * 真实类型：ServletConfig
    * 作用：Servlet的配置对象
  * exception:
    * 真实类型：Throwable
    * 作用：异常对象，只有指令`isErrorPage`标识为true的jsp页面才可以用。

# JSP的指令

* 作用：用于配置JSP页面，导入资源文件;
* 格式：`<%@ 指令名称 属性名1=属性值1 属性名2=属性值2 %>`
* 分类：
  * page：配置jsp页面的指令;
    * `content-type`:等同于`response.setContentType()`;
      * 设置响应体的MIME类型以及字符集;
      * 设置当前页面的编码（只能是高级的IDE），如果是低级工具，需要设置`pageEncoding`属性设置当前页面的字符集。
    * `language`:设置jsp页面中使用的代码，如java;
    * `buffer`:设置缓冲区的大小;
    * `import`:倒入java代码中需要使用的包;
    * `errorPage`:当前页面发生异常后，会自动跳转到指定的错误页面，如：`errorPage = "500.jsp"`;
    * `isErrorPage`:标识当前页面是否是错误页面：
      * true:是，可以使用**内置对象exception**;
      * false:否，默认值，不可以使用内置对象exception。
  * include：描述页面直接包含关系的指令，倒入页面的资源文件。
    * 如：`<%@ include file = "top.jsp">`
  * taglib:用于导入资源，一般是标签库;
    * 如：`<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%>`
    * prefix:自定义的前缀，使用标签库中的标签，前面必须带上的标识。

