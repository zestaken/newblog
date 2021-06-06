---
title: 阿里巴巴MySql规范
date: 2020-11-19 17:19:34
tags: [Mysql, 数据库]
categories: 技术笔记
---

* 表达是与否概念的字段，必须使用 is_xxx的方式命名，数据类型是 unsigned tinyint（ 1表示是，0表示否）;
* 任何字段如果为**非负数，必须是 unsigned**。
* 表名、字段名必须使用**小写字母或数字**,单词之间用下划线分隔；禁止出现数字开头，禁止两个下划线中间只出现数字;
* 表名不使用复数名词;
* 禁用保留字，如 desc、range、match、delayed等，请参考 MySQL官方保留字;
* **小数类型**为 decimal，禁止使用 float和 double。
  * 说明：float和 double在存储的时候，存在精度损失的问题，很可能在值的比较时，得到不正确的结果。如果存储的数据范围超过 decimal的范围，建议将数据拆成整数和小数分开存储。
* 表必备三字段：id, gmt_create, gmt_modified。
  ![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201123111656.png)```
 * 说明：其中 id必为主键，类型为 unsigned bigint、单表时自增、步长为 1。gmt_create,gmt_modified的类型均为 date_time类型。
* gmt_create和gmt——modifed的设置示例：
```sql
-- 创建学院表
create table t_college (
    `id`  bigint unsigned primary key auto_increment comment '学院id', 
	  `name` varchar (60) not null comment '学院名称', 
    `gmt_create` datetime null default current_timestamp comment '记录创建时间', 
    `gmt_modified` datetime null default current_timestamp on update current_timestamp comment '记录最近修改时间' 
    ); 
``` 