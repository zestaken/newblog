---
title: Leetcode-字符串
date: 2021-06-11 19:49:16
tags: [LeetCode, 数据结构]
categories: 技术笔记
---
# 总结 

## String类的charAt()与toCharArray()实现原理

* 例题：[ip-地址无效化-1108](#1-ip-地址无效化-1108)

## StringBuilder的append()方法的参数

* 例题：[ip-地址无效化-1108](#1-ip-地址无效化-1108)

## StringBuilder的insert()方法使用

## foreach循环的应用场景

## 














---

# 1. IP 地址无效化 1108 

* 题目：
```
给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。
所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."

示例 1：

输入：address = "1.1.1.1"
输出："1[.]1[.]1[.]1"
示例 2：

输入：address = "255.100.50.0"
输出："255[.]100[.]50[.]0"
 

提示：
给出的 address 是一个有效的 IPv4 地址
```

## java解法

* 法一：
  * 遍历整个字符串，同时新建一个空的StringBuilder，遇到`.`的时候，加上`[]`使用`append()`添加进入StringBuilder中去。
  * 评测结果：
![](https://gitee.com/zhangjie0524/picgo/raw/master/20210611201714.png)
```java
//解题代码
    public String deFangIpaddr1 (String address) {
        StringBuilder newAddress = new StringBuilder();
        for(int i = 0; i < address.length(); i++) {
            char temp = address.charAt(i);
            if(temp == '.') {
                newAddress.append('[');
                newAddress.append('.');
                newAddress.append(']');
            } else {
                newAddress.append(temp);
            }
        }

        return newAddress.toString();
    }

//JUnit测试用例
    @Test
    public void testDeFangIpaddr1() {
        DefangIPaddr test = new DefangIPaddr();
        String res = test.deFangIpaddr1("127.0.0.1");
        String expRes = "127[.]0[.]0[.]1";
        assert res.equals(expRes) : res;
    }
```
* 法二
  * 调用String的`replace()`方法，直接将`.`替换为`[.]`
  * 评测结果：
![](https://gitee.com/zhangjie0524/picgo/raw/master/20210611202515.png)
```java
//解题代码
    public String deFangIpaddr2(String address) {
        return address.replace(".", "[.]");
    }

//测试用例
    @Test
    public void testDeFangIpaddr2() {
        DefangIPaddr test = new DefangIPaddr();
        String res = test.deFangIpaddr2("127.0.0.1");
        String expRes = "127[.]0[.]0[.]1";
        assert res.equals(expRes) : res;
    }
```
* 法三：
  * 创建StringBuilder对象，并把原字符串赋给它，调用其`insert`方法，在`.`的前后插入`[]`。
  * 评测结果：
![](https://gitee.com/zhangjie0524/picgo/raw/master/20210611204856.png)
```java
//解题代码
    //StringBuilder的insert
    public String deFangIpaddr3 (String address) {
        StringBuilder newAddress = new StringBuilder(address);
        for(int i = 0; i < newAddress.length(); i++) {
            if(newAddress.charAt(i) == '.') {
                //insert是当前计数位变为指定字符，原来的字符到下一位
                newAddress.insert(i + 1, ']'); //先插后面的括号，这样.的位置还未改变，方便添加前面的括号
                newAddress.insert(i, '[');
                i += 3;
                continue;
            }
        }
        return newAddress.toString();
    }
//测试用例
    @Test
    public void testDeFangIpaddr3() {
        DefangIPaddr test = new DefangIPaddr();
        String res = test.deFangIpaddr3("127.0.0.1");
        String expRes = "127[.]0[.]0[.]1";
        assert res.equals(expRes) : res;
    }
```
* 法四：
  * 同一一样，但是append方法可以直接添加字符串。。。
  * 评测结果：
![](https://gitee.com/zhangjie0524/picgo/raw/master/20210611205634.png)
```java
//解题代码
    //StringBuilder的append可以直接添加字符串。。。
    public String deFangIpaddr4 (String address) {
        StringBuilder newAddress = new StringBuilder();
        for(int i = 0; i < address.length(); i++) {
            char temp = address.charAt(i);
            if(temp == '.') {
//                newAddress.append('[');
//                newAddress.append('.');
//                newAddress.append(']');
                newAddress.append("[.]");
            } else {
                newAddress.append(temp);
            }
        }

        return newAddress.toString();
    }
//测试用例
    @Test
    public void testDeFangIpaddr4() {
        DefangIPaddr test = new DefangIPaddr();
        String res = test.deFangIpaddr4("127.0.0.1");
        String expRes = "127[.]0[.]0[.]1";
        assert res.equals(expRes) : res;
    }
```
* 法五：
  * 将String转为字符数组，利用foreach循环遍历
  * 评测结果：
![](https://gitee.com/zhangjie0524/picgo/raw/master/20210611211051.png)
```java
//解题代码
    //将String转为字符数组，利用foreach循环遍历
    public String deFangIpaddr5 (String address) {
        StringBuilder newAddress = new StringBuilder();
        for(char temp : address.toCharArray()) {
            if(temp == '.') {
                newAddress.append("[.]");
            } else {
                newAddress.append(temp);
            }
        }
        return newAddress.toString();
    }
//测试用例
    @Test
    public void testDeFangIpaddr5() {
        DefangIPaddr test = new DefangIPaddr();
        String res = test.deFangIpaddr5("127.0.0.1");
        String expRes = "127[.]0[.]0[.]1";
        assert res.equals(expRes) : res;
    }
```



