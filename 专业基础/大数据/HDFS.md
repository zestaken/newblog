---
title: HDFS
tags: HDFS
categories:
  - 专业基础
  - 大数据
abbrlink: 59429
date: 2020-12-13 22:00:43
---

# HDFS概述

[hadoop源码镜像](http://mirrors.hust.edu.cn/apache/)

* HDFS，即Hadoop分布式文件系统，是可以运行在通用硬件上，提供流式数据操作，能够处理超大文件的分布式文件管理系统。
  * 分布式文件管理系统：一种系统来管理多台机器上的文件。  
  * HDFS是分布式的，意味着它的功能需要很多服务器联合起来实现。
* HDFS有高度容错，高吞吐量，高可靠性，容易扩展等特性。 
  * 高容错性：
    * 数据自动保存多个副本。
    * 某一个副本丢失后，它可以自动恢复。
* HDFS的使用场景：适合一次写入，多次读出的场景，且不支持文件的修改。适合用来做数据分析。
* HDFS的缺点：
  * 不适合低延时的数据访问；
  * 无法高效对大量**小文件**进行存储；
    * 存储大量小文件，它会占用**NameNode大量的内存**来存储文件目录和块信息。
    * 小文件存储的**寻址时间**会超过读取时间，违反了Hdfs的设计目标。
  * 不支持并发写入，文件随机修改:
    * 一个文件只能有一个写，不允许多个线程同时写；
    * 仅支持数据追加，不支持文件的随机修改。

# HDFS的体系结构

* HDFS是一个主从(Master/Slave)体系结构的分布式系统。
* 体系结构如图；
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201213221011.jpg)
* **NameNode**是HDFS的Master节点，负责管理文件系统的**命名空间(namespace)**,以及**数据块到具体DataNode的映射**等信息。
* **DataNode**一般是一个节点上一个，负责管理它所在节点上的存储。
* 用户与HDFS的交互：
  1. 用户能够通过**HDFS客户端(client)**,发起**读写**HDFS文件的请求，同时还能通过client执行HDFS命名空间的操作，如**打开，关闭，重命名文件或者目录**。
  2. **NameNode会响应客户端的请求**，来更改命名空间的信息以及DataNode和数据块的映射关系；
  3. NameNode会指导**DataNode来处理client的文件读写请求**。

# HDFS基本概念

## 数据块(Block)

* HDFS的文件是以数据块存储的，数据块是HDFS文件处理的**最小单元**。
* HDFS的数据块很大，默认为每块**128MB**。
* HDFS**数据块会以文件**的形式存储在数据节点的磁盘上。
* HDFS中所有文件都会被**切分成若干个数据块**分布在数据节点上存储。
* HDFS会将同一个数据块**冗余备份**到不同的数据节点上(一个数据块默认保存**3份**)。

## 名字节点(NameNode)

* NameNode是HDFSMaster/Slave结构中的**主节点**。
* NameNode管理文件系统**命名空间(NameSpace)**以及**数据块和数据节点之间的对应关系**。
  * NameSpace:
    * 包括**文件系统目录树**，**文件目录信息**，以及**文件的数据块索引**（文件->数据块）；
    * 这些信息以**命名空间镜像文件**和**编辑日志文件**这两个文件的形式**永久的保存在NameNode的本地磁盘上**。
  * 数据块与数据节点之间的对应关系：
    * 并不保存在NameNode的本地磁盘上，而是在NameNode启动时**动态构建**。
* NameNode是HDFS的**单一故障点**，如果NameNode丢失元数据或者损坏，文件系统将出现错误。
  * Hadoop 2.X版本引入**名字节点高可用性(HA)**,来解决这个问题：
* NameNode中要保存数据块与数据节点之间的对应关系，如果文件数量过多，**NameNode的内存将成为限制系统横向扩展的瓶颈**。
  * Hadoop 2.X版本引入**联邦HDFS机制(HDFS Federation)**,来解决这个问题。

## 数据节点(DataNode)

* DataNode是HDFS的**从结点**;
* DataNode会根据Client的请求和NameNode的指令来从本地存储中读取数据块，或者向本地存储中写入数据块。
* DataNode会不断向NameNode发送**数据块汇报，心跳和缓存汇报**。

## 客户端(client)

* HDFS提供了多种客户端接口供**应用程序**和**用户**使用，这些接口包括**命令行接口**，**浏览器接口**和**代码API接口**。

## HDFS通信协议

* HDFS的使用流程需要client，NameNode，DataNode三者之间的配合和相互调用。HDFS将这些节点间的调用抽象成不同的**接口**。
* HDFS节点间的接口主要有两种类型：
  1. Hadoop RPC接口
  2. 流式接口

# Hadoop RPC接口

* Hadoop RPC接口是基于Protobuf实现的，主要定义在`org.apache.hadoop.hdfs.protocol`包和`org.apache.hadoop.hdfs.server.protocol`包中、
* Hadoop RPC接口主要包含以下几个接口：
  1. **ClentProtocol**:这个接口定义了**client和NameNode**之间的接口，clent对文件的所有操作都要通过这个接口。
  2. **ClientDatanodeProtocol**:这是**client和DataNode**之间的接口，其中的方法是在**client获取DataNode的信息时调用**，而不是直接的数据读写交互。
  3. **DatanodeProtocol**:DataNode通过这个接口和**NameNode通信**，同时NameNode通过这个接口中方法的返回值向DataNode下发指令，这个接口是**DataNode和NameNode之间通信的唯一方式**。
  4. **InnerDatanodeProtocol**:这是**DataNode和DataNode之间通信的接口**，这个接口主要用于**数据块的恢复操作，以及同步DataNode上存储的数据块副本的信息**。
  5. **NameNodeProtocol**: **Secondary NameNode和NameNode之间的接口**，如果引入了HA机制，则不使用Secondary NameNode，这个接口作用有限。
  6. 其它接口：如安全相关接口，HA相关接口。

## ClientProtocol

* 是Client发起调用的接口。
### 读数据相关方法

* `getBlockLocations()`:客户端调用该方法获取指定范围内所有**数据块的位置信息**。
  * 参数：HDFS文件的**文件名**和**读取范围**。
  * 返回值：文件指定范围内所有数据块的文件名以及它们的位置信息，用**LocatedBlocks对象**封装。
    * 数据块的位置信息是指所有存储这个数据块副本的DataNode信息，这些DataNode会以与当前客户端的距离远近排序。
  * 定义：`public LocatedBlocks getBLockLocations(String src, long offset,long length);`
  * 使用：client会首先调用`getBlockLocations()`方法获取文件的所有数据块的位置，然后根据这些**位置信息**从DataNode读取数据块。
* `reportBadBlocks()`:客户端调用该方法向NameNode汇报错误的数据块。
  * 参数：LocatedBlock对象数组
  * 定义：`public void reportBadBlocks(LocatedBlock[]  blocks);`
  * 使用： 当client从DataNode**读取数据块并且发现数据块的校验和并不正确时**，调用这个方法向NameNode汇报这个错误数据块的信息。

### 写/追加写数据相关方法

* `creat()`:用于在HDFS文件系统目录树中创建一个新的空文件。
  * 参数：由`String src`参数指定创建文件的路径。
  * 返回值：
  * 定义:`public HDFSFileStatus create(String src,...(此处省略后续参数));`
* `append()`:用于打开一个已有的文件。
  * 如果这个文件的**最后一个数据块没有写满**，则返回这个数据块的位置信息(用LocatedBlock对象封装);
  * 如果这个文件的**最后一个数据块恰好写满**，则**创建一个新数据块**并添加到这个文件中，然后返回这个新添加数据块的位置信息。
  * 定义:`public LocatedBlock append(String src, String clientName);`
* `addBlock()`:向指定文件**添加一个新数据块**，并获取存储这个数据块副本的所有**DataNode的位置信息**。
  * 调用该方法需要传入**上一个数据块的引用**（即previous参数）；
  * excludeNodes参数：是DataNode的黑名单，保存了client**无法连接的一些DataNode**；
  * favoredNodes参数：是client希望保存数据块副本的**DataNode列表**.
  * 定义：`public LocatedBlock addBlock(String src, String clientName, ExtenedNode previous, DataNodeInfo[] excludeNodes, long fileId,  String[] favoredNodes);`
* `complete()`:当客户端**完成了整个文件的写入操作后**，会调用该方法**通知NameNode**。
  * 该方法会**提交所有新写入的数据块信息**；
  * 当该文件的**所有数据块至少都有一个副本**时，该方法会返回true,这时NameNode中文件的状态也会从**构建中状态转换为正常状态**；
  * 否则，该方法返回false，**client需要反复调用`complete()`方法,直到返回ture。
  * 定义：`public boolean complete(String src, String clientName, ExtendedBlock last, long fileId);`
* 正常写新文件流程：
  1. client调用`create`方法在文件系统目录树上**创建一个空文件**；
  2. client调用`addBlock`方法获取存储文件的**数据块的位置信息**；
  3. client根据数据块的位置信息构建**数据流管道，向DataNode中写入数据**；
  4. client完成数据的写入，调用`complete`方法**通知NameNode**。
* 正常追写数据流程：
  1. client调用`append`方法**获取最后一个可写数据块的位置信息**；
  2. client根据该数据块的位置信息构建**数据流管道，向这个未写满的DataNode中写入数据**；
  3. client如果将原文件的最后一个数据块写满了，则调用`addBlock`方法获取存储文件的**数据块的位置信息**；
  4. client根据数据块的位置信息构建**数据流管道，向DataNode中写入数据**；
  5. client完成数据的写入，调用`complete`方法**通知NameNode**。  

### 对命名空间的管理相关方法

* HDFS对NameNode信息的修改的相关方法与FileSystem类抽象的所有方法。
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201216103550.jpg)

### 系统问题与管理操作

* ClientProtocol支持**DFSAdmin工具（供HDFS管理员管理HDFS集群的命令行工具）**的接口方法。
* 典型的dfsadmin命令：`hdfs dfsadmin[参数]`;
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201216135353.jpg)
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201216135358.jpg)
* **安全模式**：
  * 安全模式是NameNode的一种状态，处于安全模式的NameNode不接受client对NameSpace的修改操作，NameSpace处于**只读状态**。同时，NameNode也不会**向DataNode下发任何数据块的复制，删除操作**。
  * 刚刚启动的NameNode会**自动进入安全模式**。

### 快照操作

* 快照保存了HDFS在一个时间点某个路径中所有数据的拷贝；
* 快照可以将集群回滚到之间的一个正常的时间点上。
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201217151155.jpg)

### 缓存操作

* 集中式缓存管理功能：用户可以指定一些经常使用的数据或者高优先级任务的数据，使它们常驻缓存而不被淘汰到磁盘上。
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201217151806.jpg)

### 其它操作

* 如安全相关或者XAtrr相关命令

## ClientDatanodeProtocol

* ClientDatanodeProtocol定义了client和Datonode之间的接口，由client发起调用。
* `getRepublicaVisibleLength()`:用于从某个DataNode获取某个数据块副本真实的数据长度。
* `getBlockLocalPathInfo();`:client调用该方法获取指定数据块文件以及数据块校验文件在当前DataNode（和client一个物理机）上的本地路径。
* `refreshNamenodes()`:用于触发指定的DataNode重新加载配置文件，停止服务那些已经从配置文件中删除的块池，开始服务新添加的块池。
* `deleteBlockPool();`:用于从指定的DataNode删除指定的块池。
* `getHdfsBlocksMetadata();`:获取是存储在指定的DataNode的哪个卷上的。
* `shutdownDatanode();`:用于关闭一个数据节点。
* `getDatanodeInfo();`:获取指定的DataNode的信息：DataNode运行和配置的HDFS版本，以及DataNode的启动时间等信息。
* `startReconfiguration();`:触发DataNode异步地从磁盘加载并应用配置。
* `getReconfigurationStatus();`:用于查询上一次触发的重新加载配置操作的运行情况。

## DatanodeProtocol

* Datanode使用这个接口与NameNode进行握手，注册，发送心跳，进行数据块汇报。
* NameNode会**在心跳响应中携带名字节点指令**，NameNode向DataNode下发名字节点指令只能通过这个接口进行。
* DataNode**启动**方法：
  1. 首先调用`versionRequest();`与NameNode进行握手操作，在这个过程中会判断DataNode的HDFS版本与NameNode的HDFS版本能否协同工作，如果不能，则抛出异常；
  2. 然后调用`registerDatanode();`向NameNode注册当前的DataNode。
  3. 接着DataNode调用`blockReport();`方法向NameNode汇报它所管理的所有数据块信息，NameNode根据上报的数据库信息，建立数据块与DataNode之间的对应关系，并且在对`blockReport()`的响应中携带名字节点指令。
  4. 最后调用`cacheReport()`上报DataNode缓存的所有数据块信息。
* **心跳**相关方法：
  * 心跳用来维护节点的健康状态，DataNode会调用用`sengHeartbeat()`定期向NameNode发送心跳,如果NameNode长时间没有收到该DataNode的心跳，会认为该节点失效。
  * NameNode在对心跳的响应中会包含名字节点指令。
* **数据块读写**相关方法:
  * `reportBadBlocks()`:用于汇报损坏的数据块；
  * `blockReceivedAndDeleted()`：汇报DataNode新接受或者删除的数据块。
  * 。。。
* `errorReport()`:用于汇报运行过程中发生的一些状况。
* DatanodeCommand类描述NameNode向DataNode发送的名字节点指令。

## InterDatanodeProtocol

* 是DataNode和DataNode之间的接口，用于同步数据块的状态。

## NamenodeProtocol

* 是NameNode和Secondary NameNode之间的接口。

# 流式接口

* 流式接口是HDFS中基于TCP或者HTTP实现的的接口。
* 包括：
  1. 基于TCP的DataTransferProtocol;
  2. 基于HTTP的HA架构中Active NameNode和Standby之间的接口。

## DataTransferProtocol

* 描述写入或者读出DataNode上的数据。
* client和DataNode，以及DataNode和DataNode之间的数据传输都是基于这个接口的。
* 主要方法：
  1. `readBlock();`:从当前DateNode读取指定的数据块。
  2. `writeBlock();`:将指定的数据块写入数据流管道中（pipeLine）。
* 主要类：
  * `Sender类`:发送DataTransferProtocol请求；
  * `Receiver类`:接收DataTransferProtocol请求；
  * 调用示例：
  ![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201217190221.jpg)

## Active Namenode和Standby之间的HTTP接口

* 未采用HA的NameNode会定期将**命名空间**保存到**fsimage**中，但是NameNode会先将**对命名空间的修改操作**保存到editlog文件，然后再定期合并fsimage和editlog文件。
  * 合并fsimage和editlog文件十分耗费资源，引进Secondary NameNode来专门负责合并。
* 采用了HA的HDFS，会让Standby NameNode不断将读入的editlog文件写到自己的fsimage中，始终维持一个最新版本的命名空间。
  * Standby NameNode需要**定期将自己的命名空间写入一个新的fsimage**，并通过**HTTP协议**将这个fsimage文件传回Active NameNode。这就是二者之间的HTTP接口的作用。

# HDFS的主要流程

## 客户端读流程

1. 打开HDFS文件：
   1. 首先调用：`DistributedFileSystem.open()`方法打开HDFS文件，该方法在底层调用`ClientProtocol.open()`方法；
   2. 返回得到一个HdfsDataInputStream对象用于读取数据块。
2. 从NameNode获取DataNode地址：
   1. 调用`ClientProtocol.getBlockLocations()`方法向NameNode获取该HDFS文件起始数据块的位置。
   2. 按照距离Client的距离远近来选取最优的DataNode。
3. 连接到DataNode读取数据块：
   1. Client通过`DFSInputStream.read()`方法连接到最优的DataNode来读取数据块；
   2. 数据会以**数据包(packet)**为单位，通过**流式接口**从DataNode传到Client。
   3. 一个数据块读取到末尾时DFSInputStream对象会再调用`ClientProtocol.getBlockLocations()`方法获取下一个数据块的地址。
4. 关闭输入流：
   1. 完成文件读取后，通过`HdfsDataInputStream()`方法，关闭输入流。
5. 如果数据出现损坏DFSStream会尝试从存有该数据块副本的其它DataNode读取数据。
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201218100559.jpg)

## 客户端写流程

1. 创建文件：
   1. 首先调用`DistributedFileSystem.create()`方法在HDFS文件系统中创建一个新的空文件。这个方法在底层通过调用`ClientProtocol.create()`方法通知NameNode创建新文件。
   2. 之后，该方法会返回一个HdfsDataOutputStream对象，这个对象在底层封装了DFSOutputStream对象。
2. 建立数据流管道：
   1. DFSOutputStream对象首先调用`ClientProtocol.addBlock()`方法向NameNode申请一个空数据块，这个方法返回一个LocatedBlock对象,这个对象包含了存储这个数据块的所有DataNode的位置信息。
   2. 根据返回的DataNode的位置信息，通过`DFSOutputStream.write()`方法建立数据流管道。
3. 通过数据流管道写入数据：
   1. 写入DFSOutputSteam的数据首先被缓存在数据流中；
   2. 之后这些数据被切分成一个个数据包通过数据流管道发送到DataNode。
   3. 每个数据包中都含有一个确认包，它会逆序通过数据流管道回到输入流；
   4. 在确认所有DataNode都已经写入这个数据包后，就会从缓存队列删除这个数据包；
   5. 当一个数据块被写满之后，就会再调用`addBlock()`方法申请新的数据块，重复上述操作。
4. 关闭输入流并提交文件：
   1. 完成对整个文件的写入之后，就会调用`close()`方法关闭输入流，并调用`ClientProtocol.complete()`通知NameNode提交这个文件中的所有数据块。
5. DataNode汇报
   1. 当DataNode成功接收一个数据块时，会调用`Datanode.blockRecievedAndDeleted()`向NameNode汇报；
   2. 之后，NameNode会更新内存中数据块与DataNode之间的对应关系。
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201218113317.jpg)

## 客户端追加写流程

1. 打开已有的HDFS文件：
   1. 调用`DistributedFileSystem.append();`方法打开一个**已有的HDFS文件**
   2. append方法会首先调用`ClientProtocol.append();`方法获取最后一个数据块的位置信息，如果最后一个数据快已满则返回null。
   3. 之后创建到这个数据块的DFSOutputStream输出流对象。
2. 建立数据流管道：
   1. DFSOutputStream类判断最后一个数据块是否已经写满；
   2. 如果没有写满，根据第一步返回的数据块位置信息，建立到该数据块的数据流管道；
   3. 如果已经写满了，则向NameNode新申请一个数据块再建立输出流管道。
3. 通过数据流管道写入数据；类似
4. 关闭数据流并提交文件：类似

## DataNode启动，心跳以及执行名字节点指令流程

* Datanode启动流程：
  1. **握手**：Datanode首先通过`DatanodeProtocol.versionRequese()`方法获取NameNode的版本号等信息，与自身的软件、版本号等进行比较，确保它们是一致的。
  2. **注册**：Datanode通过`DatanodeProtocol.register()`方法向NameNode注册；
  3. **汇报**：注册成功后，DataNode会将本地存储的所有数据块和缓存的数据块信息上传到NameNode，NameNode利用这些信息建立数据块到DataNode的对应关系。
* DataNode启动之后会定期向NameNode发送心跳。
* DataNode成功的添加或者删除了一个数据块时会向NameNode汇报，NameNode根据这些汇报的信息，更新数据块和Datanode对应关系。

# Hadoop RPC

* RPC（Remote Peocedure CallProtocol 远程过程调用协议），是基于IPC(Inter-Process Communications 进程间通信),模型实现的通信框架。
* 工作模式:客户端/服务器模式，请求程序是一个客户端，服务提供程序就是一个服务器。
  1. 客户端首先会发送一个有参数调用的请求到服务器端；
  2. 服务器端会在睡眠状态等待调用请求到达，之后执行调用请求，向客户端发送响应信息。
  3. 客户端接收响应信息，远程调用结束。
* 框架结构：
  * 通信模块：基于网络通信协议的网络通信模块。
  * 客户端Stub程序：客户端和服务器端都具有Stub程序，主要用于发起和响应请求。
  * 服务器端Stub程序：接收调用请求，触发对应的服务程序，并返回响应信息。
  * 请求程序：调用客户端Stub程序，然后接收服务器Stub程序返回的响应信息。
  * 服务程序：接收来自服务器端的调用请求，执行对应的操作，并返回结果。
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201218180708.jpg)


