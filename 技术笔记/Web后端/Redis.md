---
title: Redis
tags:
  - Redis
  - 后端
categories:
  - 技术笔记
  - Web后端
abbrlink: 27273
date: 2021-01-30 11:09:31
---

# Redis概念

* redis是一款高性能的NOSQL系列的非关系型数据库。
  * Redis（Remote Dictionary Server )，即远程字典服务，是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。
  * Redis支持的键值数据类型：
    1. String:字符串类型;
    2. hash:哈希类型;
    3. list：列表类型;
    4. set：集合类型;
    5. sortedset:有序集合类型。
  * Redis的应用场景：
    1. 数据缓存（数据查询，短链接，新闻内容，商品内容等）
    2. 聊天室的在线好友列表;
    3. 任务队列（秒杀，抢购，12306的高并发等）
    4. 应用排行榜;
    5. 数据过期处理;
    6. 分布式集群架构中的session分离
* NOSQL:Not Only SQL ,泛指非关系型数据库。
  * 为了应对大规模数据的挑战。
* 关系型数据库：
  * 数据之间有关联关系。
  * 数据存储在硬盘的文件中。
  * 数据以表的形式存储。
* 非关系型数据库，
  * 数据之间没有关系;
  * 数据存储在内存中;
  * 存储格式有key:value形式，文档形式，图片形式等。
* 主流NOSQL产品：
  * 键值（key:value)数据库：
    * Tokyo Cabinet/Tyrant， Redis， Voldemort， Oracle BDB
    * 应用场景：内容缓存，主要用于处理大量数据的高访问负载，也用于一些日志系统等等。
    * 数据模型：Key 指向 Value 的键值对，通常用hashtable来实现
  * 列存储数据库：
    * Cassandra， HBase， Riak
    * 应用场景：分布式的文件系统
    * 数据模型：以列簇式存储，将同一列数据存在一起
  * 文档型数据库：
    * CouchDB， MongoDb
    * 应用场景：Web应用（与Key-Value类似，Value是结构化的，不同的是数据库能够了解Value的内容）
    * 数据模型：Key-Value对应的键值对，Value为结构化数据
  * 图形(Graph)数据库：
    * Neo4J， InfoGrid， Infinite Graph
    * 应用场景：社交网络，推荐系统等。专注于构建关系图谱
    * 数据模型：图结构。

# Redis的安装

* 下载：[官网](https://redis.io),[中文网](https://www.redis.net.cn)
* 为linux系统下载的`.tar.gz`文件，通过`tar -xzf`命令解压缩;
* 进入解压缩文件目录下，执行`make`编译该文件 
* 二进制文件保存在src目录下：
  * `src/redis-cli`是客户端的启动文件;
  * `src/redis-serve`是服务端的启动文件。
* `redis.conf`是redis的配置文件。

# Redis的数据结构

* redis存储的都是key:value格式的数据，其中**key都是字符串类型**,而value有五种数据结构。
* value的五种数据结构：
    1. String:字符串类型, 如：zhangjie
    2. hash:哈希类型,还是键值对，理解为套娃。如：name zhangjie
    3. list：列表类型,理解为双向队列，如：zhangsan lisi wangwu zhangsan
    4. set：集合类型,与list类似，只是**不允许重复元素**
    5. sortedset:有序集合类型,在set的基础上，对集合中的元素进行排序。

# Redis的命令操作

[官方文档](https://www.redis.net.cn/tutorial/3501.html)
* 字符串类型：
  * 存储：`set key value`,如：`set username zhangjie`
  * 获取：`get key`,如：`get username`
  * 删除：`del key`,如: `del username`
* 哈希类型：
  * 存储:`hset key field value`,如：`hset usernames username1 zhangjie`
  * 获取:`hget key field`,如：`hget usernames username1`
  * 获取所有的field和value：`hgetall key`,如：`hgetall usernames`
  * 删除：`hdel key field`,如：`hdel usernames username1`
* 列表类型：
  * 可以添加一个元素到列表的头部或者尾部
  * 存储：
    * `lpush key value`:将元素加入列表左边,如：`lpush usernames zhangjie`
    * `rpush key value`:将元素加入列表右边
  * 获取：
    * `lrange key start end`:获取指定范围内的元素，如：`lrange usernames 0 -1`，代表获取列表中的所有元素。
  * 删除：
    * `lpop key`:代表删除列表最左边的元素，并将该元素返回，如:`lpop usernames`
    * `rpop key`:代表删除列表最右边的元素，并将该元素返回。
* 集合类型：不允许重复元素
  * 存储：`sadd key value`,如：`sadd key a`,还可以一次存入多个元素，如：`sadd key b c`
  * 获取：`smembers key`，获取set集合中的所有元素，但是**不保证和存入的顺序一样**。如：`smembers key`
  * 删除：`srem key value`,删除集合中的某个元素，如：`srem key a`
* 有序集合类型：不允许重复元素，且元素有顺序
  * 存储：`zadd key score value`,其中score是value对应的分数，用来作为排序的依据（score小的元素在前），如：`zadd key 80 zhangjie`
  * 获取：
    * `zrange key start end`,获取指定范围内的元素，如：`zrange key 0 -1`,获取集合中所有的元素。
    * `zrange key start end withscores`，或获取指定范围内的元素以及对应的socre。
  * 删除：`zrem key value`
* 通用的命令：
  * `keys *`:获取所有的键（`*`的位置可以换为正则表达式，获取符合指定规则的键）
  * `type key`:获取指定键对应的value的类型;
  * `del key`:删除指定的key value（不止适用于String类型，也适用于所有类型，直接删除指定键）

# 持久化

* Redis的持久化：将Redis内存中的文件存储到硬盘文件中去。
* Redis持久化的两种机制：
  * RDB：默认方式，不需要配置，默认使用这种方式。
    * 在一定的间隔时间中，检测key的变化情况，然后持久化数据。
    * linux操作系统的关于RDB的配置在`redis.conf`文件中。
```conf
# after 900 sec(15 min) if at least 1 key changed
save 900 1
# after 300 sec(5 min) if at least 10 key changed
save 300 10
# after 60 sec(1 min) if at least 10000 key changed
save 60 10000
```
  * AOF：日志记录的方式，可以记录每一条命令的操作。可以在每一条命令操作后，持久化数据
    * 编辑`redis.conf`文件
```conf
# no是指AOF方式关闭，yes是打开
appendonly no
# appendfsync always：每一次操作都进行持久化，如果需要启动则取消注释
appendfsync everysec #每隔一秒进行一次持久化操作
# appendfsync no：不进行持久化操作
```

# Java客户端-Jedis

* Jedis:一款Java操作Redis数据库的工具
* 使用步骤：
  1. 下载并导入相关jar包到工程中：
     1. [common-pool2](https://commons.apache.org/proper/commons-pool/download_pool.cgi)
     2. [Jedis](https://mvnrepository.com/artifact/redis.clients/jedis?__cf_chl_captcha_tk__=d41db408314d60fb331623deabd9a15fe36abc20-1612082289-0-AVlmsRyd0pFMVLysGHAjfOhsUPRBHl6zROFnuHmysV6K7TigdSA1uvSeNmDDWTGalFggBbwRBai91Temzk2O6Jrst1jxpXIFZQib8pQTTtT6QmgsW0hE7HLfbz22RleXBOGwJrTEIHP5OipDQnpaYmvWNWosSJ8HkrWh5MPyB8ypP3Kp9zRONNgpzqY2jeTrRSixucDR7JbxJsCYeu7iRe0z2aC2TCcDS8nYy3XWJbjwVbAu8CedWeWxBWjra0azGe-L1K8uLq7W9SeT-pZbA6d8pbTcj4qsm7WKSZH94RvWgh_UfDzCD9RPOKGrq7taDoO9hM2jk12X7v1jqTkPoTCd8x0Me0tdHsGCSOGtKe_6Fub13BJBQS1xWXIeQT95yjdgRs_eXdUom5d7MIrErcKAuGTJkdGnzJG--LIjrhdKrlvioSKGg4UvToUYkV1Oc1SmjoicGh1vitlE8OT6p5m_Jbu2Bc9zybE7IfgRoBGuPZeZWS3oqJlEKJeM6D6TMWOM09WLhdgZkurzOzKDnwUN1CYnciZFlyoDrvzkHfkVdZl-Z0vhWM42cOWIANBLtpPKOnMFoWfH56jIJGGjrUF7VOee4isrl3_fFwg7zPsDcggPgIlwGrxV-VKXy_6oCQ)
  2. 获取数据库连接：
```java
Jedis jedis = new Jedis("localhost",6379);
//localhost是主机ip地址，因为是本机，所以直接用localhost
//6379是Redis的端口号，Redis服务器启动时会显示端口号，默认是6379
//如果空参则默认是localhost和6379参数
```
   3. 操作数据库,如：`jedis.set("username","zhanjie");`,与命令行操作基本类似
   4. 关闭连接：`jedis.close();`

# Jedis连接池

1. 创建Jedis连接池对象：`JedisPool jedisPool = new JedisPool();`
2. 获取Jedis对象：`Jedis jedis = jedisPool.getResource();`
3. 使用Jedis对象：，如：`jedis.set("username","zhangjie");`
4. 关闭对象，归还到连接池中：`jedis.close();`
* JedisPoolConfig对象，可以作为参数传给JedisPool构造器，配置连接池。如：
```java
JedisPoolConfig config = new JedisPoolConfig();
config.setMaxTotal(50);
config.setMaxIdel(10);

JedisPool jedisPool = new JedisPool(config,"localhost",6379);
```
* 可以类似JDBC写Jedis连接池的工具类，来简化代码的书写。
* 注：使用Jedis的连接池还需要额外导入两个包：
```xml
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.7.9</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.22</version>
        </dependency>
```