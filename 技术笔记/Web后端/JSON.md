---
title: JSON
tags:
  - JSON
  - 后端
categories:
  - 技术笔记
  - Web后端
abbrlink: 3410
date: 2021-01-25 21:47:27
---
# JSON概述

* JSON：JavaScript 对象表示法（JavaScript Object Notation）。
* 采用完全独立于编程语言的文本格式来存储和表示数据。
* JSON是存储和交换文本信息的语法。类似XML。用于数据的传输。
* JSON比XML更小、更快，更易解析。

# JSON基本规则

* 数据在（名称:值）对中：json数据是由==键值对==构成的。
  * **键**：用==引号（单双都行）引起来，也可以不使用引号==；
  * 值的取值类型：
    1. 数字（整数或者浮点数）,不用加引号
    2. 字符串（在双引号中）
    3. 逻辑值（true或者false）
    4. 数组（在方括号中），如：`{"person":[{name : "zhangsan"},{name:"lisi"}]}`
    5. 对象（在花括号中），如：`{"address":{"province":"陕西","四川"}}`
    6. null
  * 数据由逗号分隔：多个键值对**由逗号**分隔；
  * 花括号保存对象：使用{}定义json格式；
  * 方括号保存数组:`[]`
  * 示例：
```javascript
//基本定义格式
var person = {"name":"zhangjie","age":19, "gender" : true};

//对象中嵌套数组
var persons = {
    "person":[
        {"name":"zhangjie","age":23,"gender":true},
        {"name":"lisi", "age":24,"gender":true},
        {"name":"wangwu","age":25,"gender":false}
    ]
};

//数组中嵌套对象
var ps = [
        {"name":"zhangjie","age":23,"gender":true},
        {"name":"lisi", "age":24,"gender":true},
        {"name":"wangwu","age":25,"gender":false}
    ];
```

# 获取数据

1. `json对象.键名；`
2. `json对象["键名"]`
3. 遍历json对象：使用for...in循环
* 示例：
```javascript
person.name ;
person["name"];
for(var key in person) {
    //获取到的key是一个字符串
    alert(person[key]);//key原本就是字符串，不用引号包裹
}
```

# JSON数据与JavaScript对象的相互转化

* JavaScript转换为JSON数据（就是有特殊格式的字符串）：
```javascript
var user = {
  name : "zhangjie",
  age: 20,
  sex: "男"
};

var json = JSON.stringify(user);
```
* 将JSON数据转换为JavaScript对象：
```javascript
var jsObj = JSON.parse(json);
```

# JSON数据与java对象的相互转换

* JSON的解析器：用于转换json的工具类
  * 常见解析器：Jsonlib,Gson,fastJson,jackson

## Java对象转换JSON

* 此处使用Jackson
* 使用步骤：
  1. 导入jackson的相关jar包；
  2. 创建jackson的核心类对象：ObjectMapper;
  3. 调用ObjectMapper的相关方法进行转换
     1. `writeValue(参数1，obj)`:
        1. 参数1:
            1. `File`：将obj对象转换为**JSON字符串**,并保存到指定的**文件**中；
            2. `Writer`:将obj对象转换为JSON字符串，并将json数据填充到**字符输出流**中；
            3. `OutputStream`:将obj对象转换为JSON字符串，并将json数据填充到**字节输出流**中。
        2. writeValueAsString(obj):将对象转为JSON字符串
     2. 示例：
```java
ObjectMapper mapper = new ObjectMapper();

String json = mapper.writeValueAsString(person);

mapper.writeValue(new File("c:/person.txt"));
```
* 注解：
  * `@JsonIgnore`:排除指定属性，不参与转换。将注解放在对应属性定义位置即可。
  * `@JsonFormat`:将属性值转换为格式化字符串。
    * 如；`@JsonFormat(pattern = "yyyy-MM-dd")`
* List集合转换为JSON是数组格式的，在这个数组中存放JSON对象;
* Map集合转换为JSON，是一个集合对应一个JSON对象，中间的内容是键值对。

## JSON对象转为Java对象

* 此处使用Jackson
* 使用步骤：
  1. 导入jackson的相关jar包；
  2. 创建jackson的核心类对象：ObjectMapper;
  3. 调用ObjectMapper的相关方法进行转换
     1. `readValue(json字符串对象,Class)`，如：`Person person = mapper.readValue(jsonString,Person.class)`.