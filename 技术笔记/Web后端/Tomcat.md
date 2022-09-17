---
title: Tomcat
tags:
  - 后端
  - Tomcat
categories:
  - 技术笔记
  - Web后端
abbrlink: 55324
date: 2020-10-08 16:42:03
---

# Tomcat简介

* Tomcat 服务器是一个免费的开放源代码的**Web 应用服务器**，属于轻量级应用[服务器](https://baike.baidu.com/item/服务器)，在中小型系统和并发访问用户不是很多的场合下被普遍使用，是开发和调试JSP 程序的首选。Tomcat是一个使别人能够访问我写的页面的一个程序。
* Tomcat与servlet以及数据库的关系：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201008164643.png)

# Tomcat的安装

* Tomcat需要jdk的支持，需要先在电脑上装上jdk，Tomcat会在环境变量中去寻找jdk的支持。
* 去[官网](https://tomcat.apache.org)下载Windows系统的tomcat安装器：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201008165449.jpg)
* 找到Tomcat安装目录的bin目录中的startup.bat文件，打开得到如下界面：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201008170319.jpg)

* 之后再浏览器地址栏输入http://localhost:8080成功显示出如下界面，则证明Tomcat的配置成功了。![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201008170513.jpg)

* 如果要卸载，则删除目录即可。

# Tomcat的基础知识

## 链接URL简介

![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201008173307.png)

## Tomcat的目录结构

![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201008173457.png)

* conf文件：	
	* `server.xml`该文件用于配置server相关的信息，比如tomcat启动的端口号，配置主机(Host)
	* `web.xml`文件配置与web应用（web应用相当于一个web站点）
	* `tomcat-user.xml`配置用户名密码和相关权限.
* work文件：
  * work工作目录：该目录用于存放**jsp被访问后生成对应的server文件和.class文件**

## 启动

* bin/startup.bat:运行该文件
* 访问；浏览器输入：
  * `http://localhost:8080` 访问自己
  * `http://别人的IP地址：8080` 访问别人
  * 8080是Tomcat的默认端口号。
  * 可能遇到的问题：
    * 黑窗口一闪而过：
      * 原因：没有配置好JAVA_HOME
    * 启动报错：
      1. 原因：端口号被占用
      2. 解决方案：
         1. 找到占用的端口号对应的进程，杀死该进程
            1. 找到进程：`netstat -ano`
            2. 杀死进程：`taskkill /pid 进程号 -t -f`
         2. 修改自身的端口号
            1. 修改conf/server.xml下的默认端口号

## 关闭

* 正常关闭
  * bin/shutdown.bat
  * ctrl+c
* 强制关闭
  * 点击启动窗口的x

## 动态项目和静态项目

* 目录结构：
  * java动态项目：
    * 项目名称
      * WEB-INF
        * web.xml:该项目的核心配置文件
        * classes目录：放置了字节码文件
        * lib目录：放置项目依赖的jar包
# 配置

* 部署项目的方式
  1. 直接将项目放到webapps目录下
     1. 创建一个项目目录，在这个目录中放置资源文件，启动这个项目需要使用在端口地址后增加`/项目目录名/资源文件名`，
        1. `/项目目录名`:项目的的访问路径->**虚拟项目**。
     2. 简化部署：将项目打包成一个war包，直接放到webapps目录下，tomcat会自动解压缩这个包。
  2. 配置虚拟目录法

## webapps目录部署项目

* **在webapps中建立了web1目录**，下面放置我们的html文件，jsp文件，图片等等，**则web1就被当做web应用管理起来**。示例：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201009103542.jpg)

  ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201009103533.jpg)

  * 注意路径是严格大小写的，路径出错是无法显示的。

* web站点的目录规范：
  ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201009104107.png)
  * 作用：我有多个html文件，想把其中的一个html文件作为我web站点的首页。如果没有WEB-INF目录下的web.xml文件支持，是无法解决我的需求的。这个规范是约定熟成的。

  * web.xml使helloworld2.html做首页示例：

    * 新建一个WEB-INF目录![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201009151532.jpg)
    * 在WEB-INF目录下创建一个web.xml
    * web.xml我们不可能会写，所以可以**在webapps目录下其他的站点中抄一份过来**【复制ROOT/WEB-INF/web.xml的文件到自己的站点中】
    * 在web.xml中添加以下代码

    ```xml
          <welcome-file-list>
                <welcome-file>helloworld2.html</welcome-file>
          </welcome-file-list>
    ```

    * 最终的web.xml如图：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201009151441.jpg)
    * 在浏览器输入`localhost:8080/web1/`得：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201009151224.jpg)
      * 因为已经规定了首页为helloworld2.html所以无需指明具体的html文件。

## 配置虚拟目录

* 虚拟目录的作用：

  * 如果把所有web站点的目录都放在webapps下，可能导致**磁盘空间不够用**，也**不利于对web站点目录的管理**【如果存在非常多的web站点目录】
  * 把**web站点的目录分散到其他磁盘管理就需要配置虚拟目录【默认情况下，只有webapps下的目录才能被Tomcat自动管理成一个web站点】**
  * 把web应用所在目录交给web服务器管理，这个过程称之为虚拟目录的映射。

* 虚拟目录的配置方法一（不推荐使用）：

  * 在其他地方创建一个web站点目录，并创建WEB-INF目录和一个html文件。![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201009152314.jpg)
  * 找到Tomcat目录下**/conf/server.xml**文件
  * 在server.xml中的<Host>节点下添加如下代码。**path表示的是访问时输入的web项目名，docBase表示的是站点目录的绝对路径**` <Context path="/web" docBase="C:\03Temporary\web"/>`
  * 最后访问配置好的站点:`localhost:8080/web/helloworld.html`.
    * 注：需要重启tomcat，配置文件才能生效。

* 虚拟目录的配置方法二（最推荐部署项目的方法）：

  * 进入到conf/Catalina/localhost文件下，创建一个xml文件，**该文件的名字就是站点的名字。**（此处名为`hello3.xml`）

  * xml文件中的内容：

    ```xml
    <Context docBase="C:\03Temporary\web1" />  
    ```
    
  * 输入`localhost:8080/hello3/helloworld.html`来访问页面。结果如图：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201009181521.jpg)
  * 修改文件后就会**立即生效**，不需要重启服务器，是一种热部署的方式。 

# 将Tomcat集成到IDEA

* 实际操作界面和教程完全不同，如何解决？

# 配置临时域名

访问Tomcat服务器有好几种方式：

- 使用localhost域名访问【localhost代表本机】

- 使用ip地址127.0.0.1访问【该ip地址也是本机】

- 使用机器名称访问【只限用于本机上或者局域网】

- 使用本机IP地址访问【**在cmd中输入ipconfig可以查询到本机IP地址**】

- 还可以为机器配置临时域名。

- 示例：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201009183154.jpg)

  

配置临时域名的步骤：

* 打开到C:Windows/System32/drivers/etc下，找到hosts文件，在其中添加`127.0.0.1 localhost`和`127.0.0.1 zhangjie`两行。![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201009182837.jpg)

* 在浏览器中输入`zhangjie:8080/hello3/helloworld.html`访问失败d是因为没有保存修改后的hosts文件，保存后可以正常运行。
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201010074530.jpg)

# 配置虚拟主机

### 什么是虚拟主机？

* **多个不同域名的网站共存于一个Tomcat中**

### 为什么需要用到虚拟主机？

* 例子：我现在开发了4个网站，有4个域名。如果我不配置虚拟主机，一个Tomcat服务器运行一个网站，我就需要4台电脑才能把4个网站运行起来。

配置虚拟主机的步骤：

* 在tomcat的server.xml文件中添加主机名，添加内容如下：

  ```xml
  </Host>
  
        <Host name="zhang" appBase="C:\03Temporary\web1">
                      <Context path="/web1" docBase="C:\03Temporary\web1"/>
        </Host>
  ```
* 之后在浏览器输入`zhang:8080/hello3/helloworld.html`来访问。结果失败。。。



# Tomcat的结构体系

![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201009184720.png)



# 浏览器访问WEB资源的流程图

![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201009184857.png)

