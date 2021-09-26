---
title: Mysql
tags:
  - Mysql
  - 数据库
categories:
  - 技术笔记
  - Web后端
abbrlink: 62688
date: 2020-10-23 12:09:42
---

# 数据库简介

[mysql8.0官方文档](https://dev.mysql.com/doc/refman/8.0/en/)
* 特点：
  1.  持久化保存数据；
  2.  可以实现结构化查询，方便管理。
* DB:数据库（database），存储数据的仓库，保存了有组织的数据。
* DBMS:数据库管理系统。数据库是通过DBMS来创建和管理的容器。常见数据库管理系统：MYSQL，Oracle，DB2,SqlServer。
* SQL：结构化查询语言（StructureQuery Language），专门用来与数据库通信。几乎所有DBMS都支持SQL。
* 数据存储数据：
  1.  将数据存入表中，表再放入库中。一个数据库中可以有多个表，每个表有一个名字，表名具有唯一性。
  2.  表具有一些特性，这些特性定义了数据在表中如何存储，类似java中的“类”。
  3.  表由列组成，这些列也称为**字段**，每一列类似java中的属性。
  4.  表中的数据是按行存储的，每一行类似java中的对象。

# MySQL的安装

* MySQL的特点：
  1.  开放源代码；
  2.  性能高
  3.  容易安装和使用。
* DBMS的分类：
  1.  基于共享文件系统的（如：Access）
  2.  基于客户机-服务器的DBMS（如；MySQL，oracle，sqlsever）。
* MySQL下载安装：
  1.  社区版（免费）
  2.  企业版（收费）
  3.  下载地址：https://devmysql.com/downloads/installer/
  4.  跟随安装指导进行配置。

# MySQL在ubuntu上的安装

1. 安装：`sudo apt-get install mysql-server`
2. 配置：`sudo mysql_secure_installation`

# MySQL服务的启动

* 在Windows上：
  1. 搜索服务，点击打开；
  2. 在cmd输入`services.msc`启动；
  3. 在管理员cmd输入`net start <本机mysql的名字>`或者`net stop <本机mysql的名字>`启动或者关闭。(本机mysql服务的名字叫MYSQL)
* 在linux上：
  1. 启动：`sudo service mysql start`
  2. 关闭：`sudo service mysql stop`
  3. 重启：`sudo service mysql restart`
# MySQL的登录

* 登录本地用户：`mysql -uroot -proot`，-u后面接的是用户名，-p后面接的是root用户的密码。`mysql -uroot -p`,会在之后输入密码。(在linux上登录root用户需要使用sudo)
* 登录远程用户：`mysql -h127.0.0.1 -p` -h后面接的是连接目标的ip。
* 登录：`mysql --host=IP --user=用户名 --passward=连接目标的密码`
* Mysql设置远程连接权限，[参考文档](https://blog.csdn.net/mxskymx/article/details/88765072)

# 退出MySQL

* 输入`exit`

# MySQL的目录结构

1.MySQL的安装目录：
  1.  bin：二进制可执行文件，包括很多命令的执行程序。
  2.  data：存放mysql的日志文件，数据文件等。
  3.  include：放置.h头文件；
  4.  lib：所需的jar包；
  5.  share：错误信息存放
2.MySQL的数据目录：（在c盘下的programdata下）
  1.  数据库及其中的数据；
  2.  my.ini是MySQL的配置文件。

# SQLyog图形化界面

* 可以使用sql语句控制，也可以直接使用鼠标控制。

# SQL语句

## SQL的简介

* SQL：结构化查询语言（StructureQuery Language），专门用来与数据库通信。几乎所有DBMS都支持SQL。可以用来操作所有的关系型数据库。

## SQL的通用语法

* SQL可以单行或者多行书写，以分号结尾。
* 使用空格和缩进来规范格式。
* Mysql的SQL语句不区分大小写。，关键字建议使用大写。
* 注释：
  1.  单行注释：`-- `或者`# `之后写注释内容。（--注释符后面有空格。#可以没有空格，是MySQL特有的。）

## SQL的分类

* DDL（data definition language）：操作数据库和表；
* DML（Data Manipulation Language）；增删改表中的数据。
* DQL（Data Query Language）:查询表中的数据。
* DCL（Data control Language）:数据控制语言，控制访问权限和安全级别等。

## DDL

### 操作数据库（CRUD）

1. C（creat）:创建；
   1. 创建数据库：`creat database 数据库名称;`
   2. 判断数据库不存在，再创建：`creat database if not exists 数据库名称;`
   3. 创建数据库，并指定字符集：`creat database 数据库名称 character set 字符集名;`
2. R（Retrieve）：查询；
   1. 查询已经存在的数据库：`show databases;`
   2. 查看某个数据库的字符集：`show creat database 数据库名称;`(同时也是查询数据库的创建语句)
3. U（Update）：修改；
   1. 修改数据库的字符集：`alter database 数据库名称 character set 字符集名称;`
4. D(Delete):删除；
   1. 删除数据库：`drop database 数据库名称;`。
   2. 判断数据库存在，再删除：`drop database if exists 数据库名称;`
5. 使用数据库。
   1. 查询当前正在使用的数据库名称:`select database();`
   2. 使用数据库：`use 数据库名称;`

### 操作表（CRUD）


1. C（creat）:创建；
   1. 创建语法:`creat table 表名（列名1， 数据类型1， 列名2 数据类型2， ... 列名n 数据类型n）;`**创建表时，至少有一个列，否则创建会直接失败**
   2. 复制：`creat table 表名（即将创建） like 表名（被复制的）`
2. R（Retrieve）：查询；
   1. 查询数据库中所有表的名称：`show tables;`
   2. 查询表结构：`desc 表名;`
3. U（Update）：修改；
   1. 修改表名：`alter table 表名 rename to 新表名;`
   2. 修改表的字符集：`alter table 表名 character set 字符集名称;`
   3. 添加一列：`alter table 表名 add 列名 数据类型;`
   4. 修改列名称 类型：`alter table 表名 modify 列名 新数据类型;`（仅修改列的数据类型）或者`alter table 表名 change 列名 新列名 新数据类型;`
   5. 删除列：`alter table 表名 drop 列名;`
4. D(Delete):删除；
   1. 删除数据库中的表;·drop table 表名;`
   2. 判断表存在后再修改:`drop table if exists 表名;`

### 数据类型

* MySQL支持多种类型的SQL数据类型：数值，日期和时间类型，字符串（字符和字节）类型，空间类型和 JSON数据类型等
* 数据类型描述使用以下约定：
  * M表示整数类型的**最大显示宽度**。M表示整数类型的最大显示宽度。对于浮点和定点类型， M是可以存储的总位数（精度）。对于字符串类型， M是最大长度。允许的最大值M取决于数据类型。
  * D适用于浮点和定点类型，并指示**小数点后面的位数**。最大可能值为30，但不应大于 M-2。
  * [ ]表示类型定义的**可选部分**,例如：int[(M)],但是可以不填整型的宽度。
  * [借鉴博客](https://blog.csdn.net/yuzhiqiang_1993/article/details/81453569?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522160592530619724838553623%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=160592530619724838553623&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_click~default-2-81453569.pc_first_rank_v2_rank_v28p&utm_term=mysql%E6%95%B0%E6%8D%AE%E7%B1%BB%E5%9E%8B&spm=1018.2118.3001.4449)
#### 数值类型

##### 整型

* `TINYINT[(M)] [UNSIGNED] [ZEROFILL]`： **范围非常小的整数**，有符号的范围是 -128到127，无符号的范围是0到 255
* `SMALLINT[(M)] [UNSIGNED] [ZEROFILL]` ：范围较小的整数，有符号的范围是 -32768到32767，无符号的范围是0到 65535
* `MEDIUMINT[(M)] [UNSIGNED] [ZEROFILL]`: 中等大小的整数，有符号的范围是 -8388608到8388607，无符号的范围是0到 16777215。
* `INT[(M)] [UNSIGNED] [ZEROFILL]`: 正常大小的整数，有符号的范围是 -2147483648到 2147483647。无符号的范围是 0到4294967295。
* `BIGINT[(M)] [UNSIGNED] [ZEROFILL]`: 大整数，有符号的范围是 -9223372036854775808到 9223372036854775807，无符号的范围是0到 18446744073709551615。

##### 浮点型

* `FLOAT[(M,D)] [UNSIGNED] [ZEROFILL]`：一个小的（单精度）浮点数。允许值是-3.402823466E+38 到-1.175494351E-38， 0以及1.175494351E-38 到3.402823466E+38,M是总位数，D是小数点后面的位数。
* `DOUBLE[(M,D)] [UNSIGNED] [ZEROFILL]`：正常大小（双精度）浮点数。允许值是 -1.7976931348623157E+308到-2.2250738585072014E-308，0以及 2.2250738585072014E-308到 1.7976931348623157E+308。
* **M是总位数，D是小数点后面的位数**，float和double在不指定精度时，默认会按照**实际的精度**来显示。

##### 定点型

* `DECIMAL[(M[,D])] [UNSIGNED] [ZEROFILL]`:常用于存储**精确的小数**，**M是总位数**，**D是小数点后的位数**。小数点和（负数） -符号不计入 M。如果 **D为0，则值没有小数点或小数部分**。最大位数（M）为 65. 最大支持小数（D）为30.**如果D省略，则默认值为0.如果M省略，则默认值为10**,如果二者都省略，则默认为decimal(10,0)。M的范围是1到65。D范围为0到30，且不得大于M。

##### 时间类型

* `TIME`: 范围是'-838:59:59.000000' 到'838:59:59.000000';
  * TIME的完整的显示为 `D HH:MM:SS`:
  * D：表示天数，当指定该值时，存储时小时会先乘以该值
  * HH：表示小时
  * MM：表示分钟
  * SS:表示秒
  * 示例：
```sql
INSERT time_db() VALUES('22:14:16');

--   -2表示间隔了2两天
INSERT time_db() VALUES('-2 22:14:16');

-- 有冒号从小时开始
INSERT time_db() VALUES('14:16');

-- 没有冒号且没有天数则数据从秒开始
INSERT time_db() VALUES('30');

-- 有天数也从小时开始
INSERT time_db() VALUES('3 10');

-- 直接使用数字代替也可以
INSERT time_db() VALUES(253621);
```
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201121142312.png)

* `DATE`: 支持的范围是 '1000-01-01'到 '9999-12-31';
  * 示例：
```sql
INSERT date_db() VALUES(20180813);
INSERT date_db() VALUES(“2018-06-1”);
INSERT date_db() VALUES(“2018-4-1”);
INSERT date_db() VALUES(“2018-04-07”);
```
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201121142549.png)

* `DATETIME`: **日期和时间组合**。支持的范围是 '1000-01-01 00:00:00.000000'到 '9999-12-31 23:59:59.999999';
  * 示例：
```sql
INSERT datetime_db() VALUES(20180102235432);
INSERT datetime_db() VALUES("2015-04-21 21:14:32");
INSERT datetime_db() VALUES("2015-04-23");
```
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201121142737.png)

* `TIMESTAMP`: 时间戳。范围是'1970-01-01 00:00:01.000000'UTC到'2038-01-19 03:14:07.999999'UTC。与datetime类型有差不多，只是范围较小。
  * 示例：
```sql
INSERT timestamp_db() VALUES(20020121);
INSERT timestamp_db() VALUES(20020121142554);
INSERT timestamp_db() VALUES("2015-12-16 21:14:15");
INSERT timestamp_db() VALUES("2015-12-17");
INSERT timestamp_db() VALUES(NULL);
INSERT timestamp_db() VALUES(CURRENT_TIMESTAMP);
INSERT timestamp_db() VALUES();
```
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201121143202.png)
* `YEAR`: 范围是 1901到2155;
  * 示例：
```sql
INSERT year_db() VALUES("1993");
INSERT year_db() VALUES(1993);
```
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201121143340.png)

##### 字符串类型

###### char和varchar

* `CHAR[(M)]`: 一个**固定长度**的字符串，在存储时始终**用空格填充**指定长度。 M表示以字符为单位的列长度。M的范围为0到255.如果**M省略，则长度为1**，存储时占用M个字节
* `VARCHAR(M)`:**可变长度**的字符串，M 表示字符的最大列长度，M的范围是0到65,535，存储时占用L+1（L<=M,L为实际字符的长度）个字节,此处的参数**M是必须指定的**。
```sql
-- 创建表
CREATE TABLE str_db(
a CHAR(4),
b VARCHAR(4)
);

-- 插入数据
INSERT str_db() VALUES("","");
INSERT str_db() VALUES("ab","ab");
INSERT str_db() VALUES("abcd","abcd");
INSERT str_db() VALUES("abcdefg","abcdefg");//在严格模式下，改条数据会插入失败，非严格模式则会对数据进行截取
```
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201123110639.png)
  * char的参数M规定的是字符串必须的长度，不够长度用空格来补充；
  * varchar的参数M规定的是字符串最长的长度，没有超出最长的长度就存储字符串原来的长度。
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201123110718.png)

###### text系列

* `TINYTEXT[(M)]`: 不能有默认值，占用L+1个字节，L<$2^8$;
* `TEXT[(M)]`: 不能有默认值，占用L+2个字节，L<$2^16$;
* `MEDIUMTEXT[(M)]`: 不能有默认值，占用L+3个字节，L<$2^24$;
* `LONGTEXT[(M)]`: 不能有默认值，占用L+4个字节，L<$2^32$;
  * TEXT系列的存储范围比VARCHAR要大，当VARCHAR不满足时可以用TEXT系列中的类型。需要注意的是TEXT系列类型的字段不能有默认值，在检索的时候不存在大小写转换，没有CHAR和VARCHAR的效率高

###### enum枚举类型

* `ENUM('value1','value2',...)`: ENUM是一个字符串对象，其值从允许值列表中选择，它只能有一个值，从值列表中选择,最多可包含65,535个不同的元素;
```sql
CREATE TABLE enum_db (
  gender ENUM("男","女")
);

INSERT enum_db() VALUES("男");
INSERT enum_db() VALUES(1); 也可以使用编号插入值，等同于"男"，序号从1开始
INSERT enum_db() VALUES("女");
INSERT enum_db() VALUES(2);等同于"女"
```

###### set
在ENUM中我们只能从允许值列表中给字段插入一个值，而在SET类型中可以给字段插入多个值
* `SET('value1','value2',...)`: 字符串对象，该对象可以有零个或多个值，最多可包含64个不同的成员;
  *  在ENUM中我们只能从允许值列表中给字段插入一个值，而在SET类型中可以给字段插入多个值.
```sql
CREATE TABLE set_db (
a SET('1','2','3','4','5')
);


INSERT set_db() VALUES('1')
INSERT set_db() VALUES('1,2,3')
```
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201123111656.png)
## DML

1. 添加数据：
   1. 添加语法：`insert into 表名 （列名1， 列名2，... ，列名n）value（值1，值2，...,值n）;`
      1. 列名和值要一一对应；
      2. 如果表名后不给列名，在提供和列数相同个数值的前提下，会默认依次给所有列添加值，否则会报错。
2.删除数据：
   1.  删除语法：`delete from where 条件；`如果没有加条件，就会删除表中所有的数据。示例：`delete from where id=1;`
   2.  删除表，然后再创建一个一模一样的空表：`truncate table 表名;`
3.修改数据：
   1.  修改语法：`update 表名 set 列名1 = 值1，列名2 =值2 ... where 条件;`,示例：`update stu set age = 19， score = 12 where id = 1;`。如果不加任何条件则会将表中所有的数据全部修改。

## DQL

1.排序查询：
   1.  排序基础语法：`order by 子句`
   2.  `order by 排序字段1 排序方式1, 排序字段2 排序方式2, ... 排序字段n 排序方式n;`
   3.  示例：`select * from stu order by id desc;`
   4.  位于后面的排序字段的优先级较低，则当当前面的条件值一样时，才会去判断之后的条件来排序。
   5.  排序方式：
       1.  ASC：升序排列，如果不指定排序方式，默认升序；
       2.  DESC：降序排列；
2.聚合函数：将一列数据作为一个整体，进行**纵向**的计算。聚合函数会默认排除为null的项。
   1.count：计算个数；
       * 示例:`select count(列名) from 表名;`,会忽略为null的项。
       * `select count(ifnull(列名，0)) from 表名;`，会将值为null的项的值转为0，再计数。
       * `select count(*) from 表名;`,会综合所有列来计数（除非有一行都为null）
   2.max：计算最大值；
   3.min;计算最小值；
   4.sum：求和；
   5.avg：计算平均值。  
   * 适用于count的特殊方法也会适用于其他函数（除了`*`）
3.分组查询
   1. 语法：`group by 分组字段`，可以增加条件：`where 条件 group by 分组字段;`（分组前进行限定，如果不满足条件，则不参与分组。where后不可以跟聚合函数进行判断）或者`group by 分组字段 having 条件
   2. 示例：`select sex avg(math), count(id) from student group by sex;`,有条件：`select sex from student where math>70 group by sex;`(math>70 才能有被分组的资格)，`select sex feom student group by sex having count(id) > 2;`(id的个数要大于2，才会参与分组)。
   3. 分组后查询的字段：只能是被查询的字段，或者是聚合函数。
4.分页查询
   1. 分页语法：`limit 分页索引，每页显示的条数`（limit是mysql的方言）
   2. 分页索引的计算：开始的索引 - （当前的页码 -1）* 每页的条数。
   3. 示例：`select * from stu limit 0,3;`(第一页)， `selct  * from stu limit 3,3;`(第二页)

### DQL查询表中的数据

* 查询所有数据：`select * from 表名`(将表中的所有数据查询出来)
* 总语法：`select 字段列表 from 表名列表 where 条件列表 group by 分组字段 having 分组之后的条件限定 order by 排序子句 limit 分页限定`

#### 基础数据查询

* 查询指定列：`select 列名1，列名2 from 表名`.示例：
```sql
select 
      name, -- 姓名
      age,  -- 年龄
from
      student; --学生表
```
* 去除重复的结果集显示某列的内容:`select distinct 需去重的列名1，需去重的列名2 from 表名`（必须去重的所有的列的数据完全一样才会去掉）
* 计算列的的结果再显示：`select 列名1， 列名2，列名1 + 列名2 form 表明;`(如果某一列的数据为null，则计算结果都为null)
  * 一般可以使用四则运算进行运算（只有数值型计算）。
  * ifnull函数：`ifnull(表达式1，表达式2);`表达式1：需要判断是否为null的字段；表达式2；被查询字段为null后替代的值。
* 给列起别名：` select 列名1 as 别名1 form 表名`或者省略as`select 列名1 别名1 form 表名`(列名和别名之间用空格)

#### 条件查询

* where子句后跟条件；
* 运算符：
  * `>, <, >= , <=, =(没有==，=就是等于) , !=, <>(也是不等号）`
  * `between...and...(在两者之间，包含边界0`
  * 逻辑运算符：`and(与&&相同),or(与||相同)，not(与!相同)`
  * `in(数据1，数据2，数据3，...)`数据等于数据1，或者数据2，或者数据3都可以。
  * `is null, is not null`null值不能被直接查询，只能使用这两个特殊语句判断。
  * `like`:模糊查询
    * 占位符:
      * `_`：单个任意字符；
      * `%`:多个任意字符；
    * 示例：
      * `select name from stu where name like '马%';`(name中第一个字是马)
      * `select name from stu where name like '_化_`（name中第二个字是化）
      * `select naem from stu where name like '%德%'(name中含马)
      * `select name from stu where name like '___'`(name为三个字)

## DCL

* 管理用户，授权。对应DBA(数据库管理员)
* 管理用户：
  * 添加用户：
    * 创建用户并设置账户密码：`creat user '用户名'@'主机名' identified by '密码';`
  * 查询用户：
    * 切换到mysql数据库`use mysql;`
    * 查询user表：`select * from user;`,通配符%表示可以在任意主机上使用该用户。`localhost 127.0.0.1 ::1`三者都表示本地机。
  * 删除用户：
    * `drop user '用户名'@'主机名';`
  * 修改用户的密码（在已经登录用户的条件下）：
    * `update user set password = password('新密码') where user = '用户名';
    * `set password for '用户名'@'主机名' = password('新密码');` 
    * 使用password()函数，将密码进行加密之后存储。
* 授权：
  * 查询权限：`show grants for '用户名'@'主机名';`
  * 授予权限：`grant 权限列表 on 数据库名.表名 to '用户名'@'主机名';`
    * 示例：`grant select,delete,update on db3.account to 'zhangsan'@'%';`
    * 授予所有库和表的所有权限：`grant all on *.* to 'zhangsan'@'%';`
  * 撤销权限：`revote 权限列表 on 数据库名.表名 from '用户名'@'主机名';`
## 约束

### 约束概念

* 约束：对表中的数据进行限定，从而保证数据的正确性，有效性和完整性。
* 约束的分类：
  1. 主键约束：primary key
  2. 非空约束：not null
  3. 唯一约束：unique
  4. 外键约束：foreign key

### 非空约束 NOT NULL

* 在创建表时添加约束：
  * 示例：
  ```sql
   CREATE TABLE stu1(
   id INT,
   NAME VARCHAR(20) NOT NULL -- name必须为非空，才能加入表中
   );
   ```
* 创建表完成后添加或删除约束：
  * 示例：
  ```sql
  ALTER TABLE stu1 MODIFY NAME VARCHAR(20); -- 将原来有约束的name改为没有not null的
  ALTER TABLE stu1 MODIFY NAME VARCHAR(20) NOT NULL; -- 给name添加not null约束
  ```

### 唯一约束 UNIQUE

* 创建表时添加约束：
  * 示例：
  ```sql
   CREATE TABLE stu1(
   id INT,
   NAME VARCHAR(20) UNIQUE -- name必须为是唯一没有重复的，才能加入表中
   );
* 创建表完成后添加或者删除约束：
   * 示例：
   ```sql
   ALTER TABLE stu1 DROP INDEX NAME VARCHAR(20); -- 删除创建表时的唯一约束（使用DROP INDEX 而不是MODIFY）
   ALTER TABLE stu1 MODIFY NAME VARCHAR(20) UNIQUE; -- 添加唯一约束，仍然使用MODIFY
   ```
* MySQL中多个null不认为是重复的

### 主键约束：PRIMARY KEY

* 主键约束：非空且唯一，一张表中只能有一个字段为主键，主键就是表中记录的唯一标识。
* 创建时添加主键，示例：
```sql
create table stu (
   id int primary key, -- 给id添加主键约束
   name varchar(20)
)
```
* 删除主键约束，示例：
```sql
alter table stu1  drop primary key;
```
   * 因为一个表中的主键是唯一的，所以无需指定特定的列。
* 创建完表之后，添加主键，示例：
```sql
alter table stu1 modify id int primary key;
```
* **自动增长**：
  * 如果某一列是数值类型的，使用auto_increment可以完成值的自动增长。一般和主键联合在一起使用。
  * 创建表时添加主键约束，并且实现主键的自增长，示例：
  ```sql
  create table stu1 (
     id int primary key auto_increment, -- 给id添加主键约束，并自动增长。
     name varchar(20)
  );
  ```
* 使用自动增长：
```sql
insert into stu1 values(null, 'name1'); -- 每次执行此操作，如果不指定id的值，就会自动根据上一行的数据加一来作为本行的id值
```
* 删除自动增长：
```sql
alter table stu1 modify id int; -- 能够去除自动增长，但是不能这样删除主键
```
* 创建完表后添加自动增长：
```sql
alter table stu modify id int auto_increment;
```

### 外键约束：FOREIGN KEY

* 联系两个表某些列，之后关联的两列的删除添加操作都是相互制约的,从而保证数据的正确性。
* 创建表时添加外键：
```sql
create table 表名 (
   ...
   外键列(和其它表有关系的列)
   constanit  外键名称 foreign key (外键列的名称) references 主表名称(主表的列（一般为主键列）名称)
)

示例：

creat table department (
   id int primary key,
   name varchar(20)
)

create table empolyee (
   id int primary key,
   dep_id int -- 外键对应主表的主键（外键列）
   constraint emp_dept_fk foreign key (dep_id) references department(id)
)
```
* 删除外键：
```sql
alter table employee drop foreign key emp_dept_key;
```
* 创建表之后添加外键：
```sql 
alter table employee add constraint emp_dept_fk foreign key (dep_id) references department(id);
```
* **级联操作**:修改有外键连接的两个表的列中的数据，操作会自动同步给关联的另一个列。
  * 添加级联操作语法：
  ```sql
  ALTER TABLE 表名 ADD CONSTRAINT 外键名称 
         FOREIGN KEY (外键字段名称) REFERENCES 主表名称(主表列名称) ON UPDATE CASCADE ON DELTE CASCADE;
   ```
   * 级联更新：ON UPDATE CASADE;
   * 级联删除：ON DELTE CASCADE;

# 数据库的设计
* [参考blog](https://blog.csdn.net/guangod/article/details/88714091?biz_id=102&utm_term=%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-0-88714091&spm=1018.2118.3001.4449)
## 多表关系

1. 一对一关系（使用较少）
   1. 如：人与身份证
   2. 实现：一对一关系实现，可以在任意一方添加**唯一外键**指向另一方的主键。可以直接合成一张表。
2. 一对多关系
   1. 如：部门和员工
   2. 实现：在“多”的一方建立外键，指向“一”的一方的主键。
3. 多对多关系：
   1. 如：课程与学生
   2. 实现：多对多关系需要借助**中间表**，中间表至少包含两个字段，这两个字段（这两个字段是**联合**的）作为第三张表的外键，分别指向两张表的主键。

## 数据库设计的范式

* 范式概念：设计数据库时需要遵循的一些规范。要遵循后边的范式要求，必须先遵循前边的所有范式要求。
  * 设计关系数据库时，遵从不同的规范要求，设计出合理的关系型数据库，这些不同的规范要求被称为不同的范式，各种范式呈递次规范，越高的范式数据库冗余越小。目前关系数据库有六种范式：第一范式（1NF）、第二范式（2NF）、第三范式（3NF）、巴斯-科德范式（BCNF）、第四范式(4NF）和第五范式（5NF，又称完美范式）。（前三种足矣。）
* 范式分类：
  1. 第一范式（1NF）：每一列都是不可分割的原子数据项。（数据库中创建表的基本要求）
     1. 存在严重的数据冗余问题
     2. 数据添加存在问题
     3. 数据删除存在问题**
  2. 第二范式（2NF）：在1NF的基础上，非码属性必须完全依赖于候选码（在1NF的基础上消除了非主属性对主码的**部分依赖**，解决了数据冗余问题）
     1. 概念：
        1. 函数依赖：A->B，如果通过A属性（属性组）的值，可以确定唯一B属性的值，则称B依赖于A
        2. 完全函数依赖：A->B,如果A是一个属性组，则B的值的确定需要依赖于A属性组中所有的属性值
        3. 部分函数依赖：A->B,如果A是一个属性组，则B属性值的确定只需要依赖于A属性组中某一些属性值即可。
        4. 传递函数依赖：A->B, B->C,如果通过A属性（属性值）的值，可以唯一确定B属性的值，在通过B属性（属性组）的值可以确定唯一的C属性的值，则称C传递函数依赖于A.
        5. 码：如果在一张表中，如果一个属性或者属性组，被其它所有属性完全依赖，则称这个属性（属性组）为该表的码。
        6. 主属性：码属性组中的所有属性。
        7. 非主属性：除了码属性组的属性。
  3. 第三范式（3NF）：在2NF的基础上，任何非主属性不依赖于其它非主属性（在2NF的基础上消除传递依赖，解决了数据添加和删除存在的问题）


## 触发器
.....
# 数据库的备份和还原

* 语法：`mysqldump -u用户名 -p密码 数据库名称 > 备份保存的路径`（保存到以.sql结尾的文件）
* 还原：
    1. 登录数据库；`mysql -u用户名 -p密码;`
    2. 创建一个新数据库；`create database 新数据库名;`
    3. 使用数据库:`use 数据库名;`
    4. 执行备份的文件:`source 文件路径;`

# 多表查询

## 多表查询的基本概念

* 查询基本语法：
```sql
select
   列名列表
from
   表的列表（可以写多个表）
where
   ...(条件)
```
* 示例：`select * from table1, table2;`。不加任何条件查询多个表的结果是：所有表的组合（两个表时是笛卡尔积）。

## 多表查询的分类

1. 内连接查询
2. 外连接查询
3. 子查询

### 内连接查询

1. 隐式内连接：使用where条件来消除多个表组合后的无用信息。
```sql
select 
      * 
from 
      table1, table2 
where 
      table1.`id1` = `table.id2`; -- 组合后不满足的这个条件的行都会被去掉


select 
      table1.name, table2.name -- 要查询两个表里的同名列，需要在列名前面加上表名的限定。
from 
      table1, table2 
where       
      table1.`id1` = table2.`id2`; 


select 
      t1.name, t2.name 
from 
      table1 t1, table2 t2 -- 通过取别名来简化对表名的书写。
where 
      t1.`id1` = t2.`id2`; 
```

2. 显式内连接：
   * 语法：`select 字段列表 from 表名1 inner join 表名2 on 条件;`
   * 同样支持对表取别名来简化书写
```sql
select
      * 
from
      table1
inner join
      table2  -- 两个表是分开放的
on 
      table1.`id1` = table2.`id2`;


select
      * 
from
      table1 t1
join              -- inner可以省略
      table2 t2
on 
      t1.`id1` = t2.`id2`;
```

### 外连接查询

1. 左外连接：
   * 语法：`select 字段列表 from 表1 left outer join 表2 on 条件;`(outer同样是可以省略的)
   * 查询的是左表所有数据以及左表和右表数据交集部分。from后面的是左表，join后面是右表。
2. 右外连接：
   * 语法：`select 字段列表 from 表1 right outer join 表2 on 条件;`(outer同样是可以省略的)
   * 查询的是右表所有数据以及左表和右表数据交集部分。from后面的是左表，join后面是右表。

### 子查询

* 查询中嵌套查询，嵌套的查询称为子查询。
* 示例：
```sql
# 查询工资最高的员工的信息需完成两步操作：一：查询最高的工资；二：查询最高工资对应的员工信息
select
      * 
from 
      table1 -- 外层查询整个表的员工信息
where 
      table1.`salary` = (select max(salary) from table1); -- 嵌套一个子查询语句在条件里，完成最高工资的限定
```

#### 子查询的不同结果

* 子查询结果是**单行单列**的：
  * 子查询结果可以作为条件，使用判断运算符去判断,如：
  ```sql
  table1.`salary` = (select max(salary) from table1);
  ```
* 子查询结果是**多行单列**的：
  * 子查询可以作为条件，使用运算符`in`（是否在这个集合中）去判断，如：
  ```sql
  table.`id` in (select id from table1 where name = '1' or name = '2');
  ```
* 子查询的结果是**多行多列**的：
  * 子查询可以作为一张虚拟表，参与查询，如：
  ```sql
  # 查询员工的入职日期是`2011-11-11`日之后的员工信息和部门信息
  
  -- 子查询方式
   select
         *
   from 
         dept t1,(select * from emp where emp.`join_date` > '2011-11-11') t2   -- 子查询结果作为一张表参与查询
   where
         t1.id = t2.dept_id;

   -- 普通内连接
   select
         * 
   from
         emp t1, dept t2
   where
         t1.`dept_id` = t2.`id` and t1.`join_date` > '2011-11-11';
   ```

# 事务

## 事务的基本介绍

* 事务概念：如果一个包含多个步骤的业务操作，被事务管理，那么这些操作**要么同时成功，要么同时失败**。
* 基本操作；
  1. 开启事务：start transaction；
  2. 回滚：rollback；
  3. 提交：commit。
* 示例：
```sql
# 实现张三给李四的账户上转去500：1.张三的账户减五百 2.李四的账户加500 （如果这两步中有任何一步出错了的话，事务管理机制保证账户信息不被修改）
start transaction; -- 开启事务

update account set money = money - 500 where name = 'zhangsan'; -- 张三的账户减500

update account set money = money +500 where name = 'lisi'; -- 李四的账户加500

-- 如果两步执行完没有异常，则提交，提交后的数据库改变才真正生效
commit;
-- 如果执行出错，则回滚，数据库的状态维持启动事务时的状态
rollback;
```
* 在MySQL中，事务是默认自动提交的
  * 自动提交：
    * 一条DML（增删改）语句会自动提交一次事务
  * 手动提交：
    * 需要先开启事务，再提交
  * 修改事务的默认提交方式：
    * 查看默认提交方式：`select @@autocommit;`,结果为1代表自动提交
    * 设置默认提交方式：`set @@autocommit = 0;`，改为0代表手动提交
* 在oracle中，事务是默认手动提交的。

## 事务的四大特征

1. 原子性：是不可分割的最小单位，要么同时成功，要么同时失败。
2. 持久性：事务一旦提交或者回滚后，数据库会持久化地保存数据。
3. 隔离性：多个事务之间相互独立。
4. 一致性：事务操作前后，数据总量不变。（即如果事务执行到中途出错了，则整个数据库都回滚到事务开始前的状态。）

## 事务的隔离级别

* 概念：理论上多个事务之间是隔离的，相互独立。如果多个事务操作同一批数据，那么这些事务之间就不是隔离的了，会引发一些问题，设置不同的隔离级别来解决这些问题。
* 存在的问题：
  1. 脏读：一个事务，读取到了另一个事务中**没有提交**的数据。
  2. 不可重复读（虚读）：在同一个事务中，两次读到的数据不一样。(原因可能是其它事务在两次查询之间的时间修改了数据库)
  3. 幻读：一个事务操作（DML）数据表中所有记录，另一个事务添加了一条数据，则第一个事务查询不到自己的修改。
* 隔离级别：隔离级别从小到大安全性越来越高，但是效率越来越低。
  1. read uncommited：读未提交
     1. 产生的问题：脏读，不可重复读，幻读。
  2. read commited:读已提交（oracle默认）
     1. 产生的问题：不可重复读，幻读
  3. repeatable read：可重复读（MySQL默认）
     1. 产生的问题：幻读
  4. serializable：串行化（一个事务在操作一个表时，其它事务是不可以操作这个表，类似给这个表“加锁”）
     1. 可以解决所有问题。
* 数据库查询隔离级别：`select @@tx_isolation;`
* 数据库设置隔离级别：`set global transaction isolation level 级别字符串;`

# 易错语法

* 语句括号内部少逗号，最后一行多了逗号:

```sql
-- 创建学生表
create table `student` (
    `id` int primary key auto_increment comment '学生id', -- 学生id
	`name` varchar (30) not null comment '学生姓名', -- 学生姓名
	`phone` varchar (36) not null comment '学生电话', -- 学生电话
	`youth_league_branch_name` varchar (60) not null comment '所属团支部名称', -- 所属团支部名称
	`study_time` datetime not null comment '学习时间'  -- 学习时间 # 少了逗号
    `gmt_create` datetime null default current_timestamp comment '记录创建时间', -- 记录创建时间
    `gmt_modified` datetime null default current_timestamp on update current_timestamp comment '记录最近修改时间', -- 记录最近修改时间
    `youth_league_branch_id` int not null, -- 所属团支部id # 多了逗号
); 


修改：

-- 创建学生表
create table `student` (
    `id` int primary key auto_increment comment '学生id', -- 学生id
	`name` varchar (30) not null comment '学生姓名', -- 学生姓名
	`phone` varchar (36) not null comment '学生电话', -- 学生电话
	`youth_league_branch_name` varchar (60) not null comment '所属团支部名称', -- 所属团支部名称
	`study_time` datetime not null comment '学习时间',  -- 学习时间
    `gmt_create` datetime null default current_timestamp comment '记录创建时间', -- 记录创建时间
    `gmt_modified` datetime null default current_timestamp on update current_timestamp comment '记录最近修改时间', -- 记录最近修改时间
    `youth_league_branch_id` int not null -- 所属团支部id 
); 
```
* 在`student`外面加上引号是为了设置大小写敏感，否则所有的大写都会被自动转换为小写。

 
 JSON parse error: Cannot deserialize value of type `java.util.Date` from String "2020-11-24": expected format "yyyy-MM-dd HH:mm:ss"; nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.util.Date` from String "2020-11-24": expected format "yyyy-MM-dd HH:mm:ss" at [Source: (PushbackInputStream); line: 1, column: 30] (through reference chain: com.zw.admin.server.model.TSchedule["deadline"])