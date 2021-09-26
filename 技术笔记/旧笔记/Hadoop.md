---
title: Hadoop
date: 2020-10-26 18:31:15
tags:
---

# Hadoop简介

* Hadoop是由Apache基金会所开发的**分布式系统基础架构**。
* 主要解决问题：
  1.  海量**数据的存储**；
  2.  海量**数据的分析计算问题**。
* Hadoop生态圈：包含Hadoop框架以及nutch等。
* Hadoop的三大发行版本：
  1. Apache:原始版本，适用于入门学习。
  2. Cloudera：在大型互联网企业中使用。（又叫CDH版）
  3. Hortonworks：文档较好。
* Hadoop的优势：
  1.  高可靠性：Hadoop维护了**多个数据副本**（在多个服务器上），即使Hadoop某个计算元素或存储出现故障，也不会导致数据的丢失。
  2.  高扩展性：在**集群间分配**任务数据，可方便的扩展数以千计的节点（服务器）。
  3.  高效性：基于MapReduce思想，**并行**工作，加快任务处理速度。
  4.  高容错性：能够自动将**失败的任务重新分配**。
* Hadoop组成
  * Hadoop1.x：
    1.  common（辅助工具）：
    2.  HDFS（数据存储）：
    3.  MapReduce（计算+资源调度）：
  * Hadoop2.x：
    1.  common（辅助工具）：
    2.  HDFS（数据存储）：
    3.  Yarn(资源调度)：
    4.  MapReduce（计算）：

# Hadoop组成概述

## HDFS架构概述

1.  NameNode（nn):存储文件的**元数据**，如文件名，文件目录结构，文件属性，以及每个文件的块列表和所在的DataNode等。（相当于一个目录，真正的数据在DataNode中。）
2.  DataNode(dn):在本地文件系统存储**文件块数据**，以及块数据的检验和。
3.  Secondary NameNode（2nn）：用来**监控HDFS状态**的辅助后台程序。

## Yarn架构概述

![IMG_2177](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/IMG_2177.JPG)
* ResourceManger(RM：**整个资源管理**老大)主要作用：
  1.  处理客户端请求
  2.  监控NodeManger
  3.  启动或监控ApplicationMaster(任务管理)
  4.  资源的分配与调度。
* NodeMaster（NM：节点老大）作用：
  1. 管理**单个节点**上的资源。
  2. 处理来自ResourceManger的命令。
  3. 处理来自ApplicationManager命令。
* ApplicationManger（AM：任务老大）作用：
  1. 负责**数据**的切分。
  2. 为应用程序申请资源并分配给内部的任务。
  3. 任务的监控与容错。
* Container
  * Container是yarn中的**资源抽象**，它封装了某个节点（可理解为一台电脑）上的多个维度资源，如内存，cpu，磁盘，网络等。

## MapReduce架构概述

* MapReduce将计算过程分为两个阶段：Map和Reduce（先分后合）：
  1. Map阶段**并行处理**输入数据（将运算任务分给不同的服务器）；
  2. Reduce阶段对Map的阶段的结果进行**汇总**。

## 大数据技术生态
  
* 大数据技术生态图：
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201027094308.png)

# Hadoop运行环境搭建

## Docker配置Hadoop环境

* [参考博客](https://blog.csdn.net/sb985/article/details/82722451?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522160652764419195271678757%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=160652764419195271678757&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_click~default-1-82722451.pc_first_rank_v2_rank_v28&utm_term=docker%E9%85%8D%E7%BD%AEHadoop&spm=1018.2118.3001.4449)
  
1. 在Ubuntu上安装docker：`sudo apt-get install docker`;
2. docker去sudo：在Ubuntu下，在执行Docker时，每次都要输入sudo，解决方法就是把当前用户执行权限添加到相应的docker用户组里面
```
sudo groupadd docker；// 添加一个新的docker用户组
sudo gpasswd -a username docker	//添加当前用户到docker用户组里
sudo service docker restart //重启Docker后台监护进程
docker ps 试试，如果没有实现当前运行的容器，则系统重启，则生效
sudo reboot
```
3. 拉取ubuntu镜像并运行容器
```
docker pull ubuntu //拉取Ubuntu镜像
docker run -it ubuntu /bin/bash //交互式运行容器
```

4. 给Ubuntu容器安装java
```
apt-get update
apt install openjdk-11-jdk
```
5. 安装wget,ifconfig,ping,vim工具
```
ubuntu的镜像默认只有最简单的系统，所以这些工具要自己安装，这三个工具后面都要用到．

apt-get install wget			#wget
apt-get install net-tools		#ifconfig
apt-get install iputils-ping    #ping
apt-get install vim			#你可以下载自己喜欢的编辑器，不一定是vim
```
6. 安装Hadoop

1. 首先创建Hadoop安装目录:`mkdir hadoop`
2. 下载Hadoop：`wget http://archive.apache.org/dist/hadoop/common/hadoop-2.8.3/hadoop-2.8.3.tar.gz`
3. 解压到Hadoop目录：`tar -xvzf hadoop-2.8.3.tar.gz`

7. 配置环境变量

* 在home目录下的`.bashrc`文件后添加如下内容（`.bashrc`文件是隐藏文件，使用`ls -al`命令可以查看到）
```
export JAVA_HOME=/usr/lib/jvm/openjdk-11-jdk
export HADOOP_HOME=/root/hadoop/hadoop-2.8.3
export HADOOP_CONFIG_HOME=$HADOOP_HOME/etc/hadoop
export PATH=$PATH:$HADOOP_HOME/bin
export PATH=$PATH:$HADOOP_HOME/sbin
```

8. 配置hadoop

* 在hadoop2.8.3目录下创建三个目录，后续配置的时候会用到：
```
cd /hadoop/hadoop.2.8.3 进入hadoop存储目录下
mkdir tmp：作为Hadoop的临时目录
mkdir namenode：作为NameNode的存放目录
mkdir datanode：作为DataNode的存放目录
```
* 我们开始修改Hadoop的配置文件。主要配置core-site.xml、hdfs-site.xml、mapred-site.xml这三个文件。
1. 在core-site.xml文件后添加如下内容(在etc/hadoop目录下)
```
<configuration>
    <property>
            <name>hadoop.tmp.dir</name>
            <value>/root/hadoop/hadoop-2.8.3/tmp</value>
    </property>

    <property>
            <name>fs.default.name</name>
            <value>hdfs://master:9000</value>
            <final>true</final>
    </property>
</configuration>
```
2. 在hdfs-site.xml文件后添加如下内容（在etc/hadoop目录下）
```
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>2</value>
        <final>true</final>
    </property>

    <property>
        <name>dfs.namenode.name.dir</name>
        <value>/root/hadoop/hadoop-2.8.3/namenode</value>
        <final>true</final>
    </property>

    <property>
        <name>dfs.datanode.data.dir</name>
        <value>/root/hadoop/hadoop-2.8.3/datanode</value>
        <final>true</final>
    </property>
</configuration>
```
   * 我们后续搭建集群环境时，将配置一个Master节点和两个Slave节点。所以dfs.replication配置为2。dfs.namenode.name.dir和hdfs.datanode.data.dir分别配置为之前创建的NameNode和DataNode的目录路径
3. mapred-site.xml配置
   * Hadoop安装文件中提供了一个mapred-site.xml.template，所以我们之前使用了命令cp mapred-site.xml.template mapred-site.xml，创建了一个mapred-site.xml文件。之后在mapred-site.xml文件中添加如下内容：
```
<configuration>
    <property>
        <name>mapred.job.tracker</name>
        <value>master:9001</value>
    </property>
</configuration>
```
   * 这里只有一个配置项mapred.job.tracker，我们指向master节点机器。
4. 修改JAVA_HOME环境变量
   * 在hadoop-env.sh文件中添加如下内容：(也在etc/hadoop目录下)
```
export JAVA_HOME=/usr/lib/jvm/openjdk-11-jdk
```
5. 格式化namenode：
  * 执行命令：`hadoop namenode -format`
# Docker配置单节点hadoop

* 为Ubuntu容器配置基本命令：
* 安装openjdk-8-jdk[参考](https://blog.csdn.net/xiaosaerjt/article/details/106033324?biz_id=102&utm_term=Ubuntu20%E5%AE%89%E8%A3%85jdk&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-0-106033324&spm=1018.2118.3001.4449)
  * 安装在usr/lib/jvm目录下
  * 先用`apt search openjdk`查询可用的版本
  * 再用`sudo apt-get 对应版本`安装
  * 配置openjdk-8-jdk：
```
#配置环境变量
vim ~/.bashrc
#在底部添加：
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export JRE_HOME=${JAVA_HOME}/jre
export PATH=${JAVA_HOME}/bin:$PATH
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib

#如果之前已经安装了其它版本的jdk,则需要修改javadoc
cd /etc/alternatives
rm javadoc
rm javadoc.1.gz
ln /usr/lib/jvm/java-8-openjdk-amd64/bin/javadoc* javadoc
ln /usr/lib/jvm/java-8-openjdk-amd64/man/man1/javadoc.1.gz javadoc.1.gz
```

* 安装ssh
  * 安装ssh；`sudo apt-get install ssh`
  * 安装pdsh:`sudo apt-get install pdsh`
* 保存镜像：
  * `docker commit -m "tools installed" -a "zestaken" c2e83b0f9183 zestaken/ubuntu:tools`

# 搭建Hadoop运行环境

## 一：配置虚拟机环境

1. 安装ubuntu20.04虚拟机；
2. 

