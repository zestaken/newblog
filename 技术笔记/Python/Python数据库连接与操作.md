---
title: Python数据库连接与操作
date: 2021-10-12 17:01:19
tags: [Python]
categories: 技术笔记
---

# 数据库的连接

* 引入数据库连接库pymysql
* python3以后只能使用pymysql库
* python3以前使用MySQLdb库连接数据库


```python
import pymysql
```

* 连接数据库, 连接时需要提供主机地址，用户名，数据库管理系统的密码，数据库名，端口


```python
db = pymysql.connect(host="localhost", user="root", password="root", database="mirror", port=3306)
```

# SQL语句的执行

* 使用cursor()方法创建一个游标对象


```python
cursor = db.cursor()
```

* 查询的表的数据内容：![j05IIX](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/uPic/j05IIX.png)

* cursor对象通过execute()方法执行sql语句，sql语句以字符串的形式传入


```python
sql = "select * from user"
cursor.execute(sql) #查询语句的返回值是该语句影响的行数
```




    5



* 通过执行查询语句后，游标已经移到我们查询的表上来，之后的操作都是对于我们之前查询过的表
* 痛殴`cursor`对象的`fetchone()`函数可以获取对应数据库表的一行的数据，获取这一行数据之后，相当于游标已经移动到了下一行，再次调用fetch系列函数都是从下一行开始获取数据。


```python
data = cursor.fetchone()
print(data)
```

    (18, 'zestaken', 'zestaken')


* 通过`fetchmany(count)`函数可以获取从当前行开始传入参数行的数据


```python
datas1 = cursor.fetchmany(2)
for data in datas1:
    print(data)
```

    (19, 'admin', 'admin')
    (20, 'admin1', 'admin1')


* 通过`fetchall()`函数可以获取从当前行开始所有行的数据


```python
datas2 = cursor.fetchall()
for data in datas2:
    print(data)
```

    (21, '123456', '123456')
    (22, '12345', '12345')


* 通过cursor对象的rowcount参数可以知道当前游标指向的数据库表的数据行的总数


```python
print(cursor.rowcount)
```

    5


* db对象close函数可以关闭连接的数据库


```python
db.close()
```

* 一种规范的访问数据库并操作的方式：通过try except块来保证sql正常执行提交事务，异常则回滚事务
* 提交和回滚通过数据库对象db的`commit()`方法和`rollback()`方法来实现
* 没有提交的事务（操作）是没有实际对数据库生效的，特别是进行对数据库数据修改的操作时要注意


```python
import pymysql
db = pymysql.connect(host="localhost", user="root", password="root", database="mirror", port=3306)
cursor = db.cursor()
sql = "select * from user"
# 使用try except块来使sql语句执行出错的时候不提交操作，而回滚
try:
    cursor.execute(sql)
    datas = cursor.fetchall()
    for data in datas:
        print(data)
    db.commit()
except:
    db.rollback()
db.close()
```

    (18, 'zestaken', 'zestaken')
    (19, 'admin', 'admin')
    (20, 'admin1', 'admin1')
    (21, '123456', '123456')
    (22, '12345', '12345')


# 数据的插入与更新

* 静态插入（没有参数传递)


```python
db = pymysql.connect(host="localhost", user="root", password="root", database="mirror", port=3306)
cursor = db.cursor()
sql1= "insert into user values(30, 'test1', 'test1')"
cursor.execute(sql1)
db.commit()
```

* 动态插入（有参数传递）：通过占位符实现


```python
sql2 = "insert into user values(31, '%s', '%s')"
cursor.execute(sql2%('test2', 'test2'))
db.commit()
```

* 查询数据库表看数据是否成功插入


```python
cursor.execute("select * from user");
datas = cursor.fetchall()
for data in datas:
    print(data)
```

    (18, 'zestaken', 'zestaken')
    (19, 'admin', 'admin')
    (20, 'admin1', 'admin1')
    (21, '123456', '123456')
    (22, '12345', '12345')
    (30, 'test1', 'test1')
    (31, 'test2', 'test2')


* 更新（update）数据库的操作，也同样有静态和动态更新，动态更新和动态插入类似，都通过占位符来实现


```python
sql3 = "update user set username = '%s' where id = '%d'"
try:
    cursor.execute(sql3%('test2-update', 31))
    db.commit()
except:
    db.rollback()
```

* 查询修改是否成功


```python
cursor.execute("select * from user");
datas = cursor.fetchall()
for data in datas:
    print(data)
```

    (18, 'zestaken', 'zestaken')
    (19, 'admin', 'admin')
    (20, 'admin1', 'admin1')
    (21, '123456', '123456')
    (22, '12345', '12345')
    (30, 'test1', 'test1')
    (31, 'test2-update', 'test2')


