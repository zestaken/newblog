---
title: MarkdownLearn
tags:
  - 杂技术
  - Markdown
categories:
  - 技术笔记
  - 杂技术
abbrlink: 24427
date: 2020-06-19 11:58:46
---

# MarkdownLearn

## 一、Markdown简介

>Markdown是一种可以使用普通文本编辑器编写的标记语言，通过简单的标记语法，它可以使普通文本内容具有一定的格式。
Markdown具有一系列衍生版本，用于扩展Markdown的功能（如表格、脚注、内嵌HTML等等），这些功能原初的Markdown尚不具备，它们能让Markdown转换成更多的格式，例如LaTeX，Docbook。Markdown增强版中比较有名的有Markdown Extra、MultiMarkdown、 Maruku等。这些衍生版本要么基于工具，如Pandoc；要么基于网站，如GitHub和Wikipedia，在语法上基本兼容，但在一些语法和渲染效果上有改动。

## 二、 编辑Markdown的工具

1.vscode
  * 需要安装插件
    * Markdown All in One -> 各种优化一股脑装上  
    * Markdown Preview GitHub styling -> 使预览格式与GitHub pages中一样  
    * Markdown preview ehenced -> 快捷键：英文输入状态下，同时按下ctrl,k 然后同时放开再按v可唤出预览窗口。
    * Markdownlint ->  markdown语法检查纠正器
    * Markdown PDF -> 将.md文件转换成pdf,jpeg,png,html格式

2.typora

* 专业Markdown编辑器，可去[官网](https://www.typora.io/)下载。
* 界面简约美观，专用于写Markdown文件，功能强劲。

## 三、文本编辑语法  

### 标题

```
 # 一级标题
 ## 二级标题
 ### 三级标题
 #### 四级标题
 ##### 五级标题
 ###### 六级标题

```

 标题一般支持六级。注意： # 与文字之间有一个空格

### 缩进，换行，空行及对齐方式

#### 首行缩进(使用占位符)

&emsp;空四位  

&ensp;空两位  
&nbsp;空一位

```
 &emsp;空四位  
 &ensp;空两位
 &nbsp;空一位

```

 注意：占位符后的 ； 不可少

#### 换行

 在一行字后面加了两个空格（space）再回车

#### 空行

 在一行中只有空格（space)或回车则为空行，但无论空了多少行，最终在预览中都只显示一行空格。

#### 对齐方式

<center>行中心对齐</center>

 <p align="left">行左对齐</p>  
 <p align="right">行右对齐</p>

```

 <center> 行中心对齐</center> //后面的</center>可省略
 <p align="left">行左对齐</p>  //后面的</p>可省略
 <p align="right">行右对齐</p>

```

### 斜体，粗体，删除线，下划线,==高亮==

 *斜体*  
 **粗体**  
 ***加粗斜体***  
 ~~删除线~~   
 <u>下划线</u> 
 ==高亮== (只有一些预览才支持)
 
```

 *斜体*  
 **粗体**  
 ***加粗斜体***  
 ~~删除线~~   
 <u>下划线</u>
 ==高亮==

```

## 四、链接

### 行内式（普通超链接）

 GitHub欢迎你[github](www.github.com)

```

 GitHub欢迎你[github](www.github.com)   
 []方括号内放链接名字，（）圆括号内放链接地址
```

### 锚点（页内超链接）

[跳转到markdown简介](#markdown简介)
```
[跳转到markdown简介](#markdown简介)
[]方括号中放显示的链接文字，（）圆括号中放要跳转的标题。  
```

注意：锚点仅支持跳转到标题，且不管是几级的标题，（）圆括号中都只放一个#

## 五、列表

### 无序列表

* 无序列表项一
* 无序列表项二
  * 无序列表五
    * 无序列表六
  
+ 无序列表项三

- 无序列表项四

```

* 无序列表项一
* 无序列表项二
  * 无序列表五
    * 无序列表六
  
+ 无序列表项三
- 无序列表项四
```

注意：  

* `*`,`-`,`+`三种符号任意一个都可创建无序列表
* 无序列表每一行之后回车会自动新建一个空无序列表行
* 符号`* ，-, +`与字符之间要有空格
* 利用tab对`*、+、-`缩进，可以形成层级列表

### 有序列表

1. 有序列表项一
2. 有序列表项二
3. 有序列表项三  

注意：

1. 数字加英文句点之后空格再加字符创建有序列表
2. 每行之后回车会自动创建新的一行并且序号加一
3. 结束有序列表需要在最后加空行（连按两次回车，或空格两次再回车）

## 六、图像插入

### 插入本地图片

#### 相对路径法

![loading](./MarkdownLearn/back.jpg)

```
![loading](./MarkdownLearn/back.jpg)
[]方括号中放入当图片加载不出来时显示的文字
（）圆括号中放入图片对.md文件的相对路径
```

注意：

1. 需要再.md文件的同级目录下建立储存图片的文件夹
2. 在vscode中需要打开包含.md和对应储存文件夹的文件夹
3. 注意相对路径的格式
4. 由于未知原因，在githubpages中不能显示

### 插入网络图片

#### 建立图床

利用picgo和gitee建立图床

#### 将图片上传至图床，生成链接

![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20200619184526.jpg)
```
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20200619184526.jpg)
圆括号内放图片链接，其余一样
```

## 七、引用

>我是周树人
>>你找鲁迅  
>>>和我周树人有什么关系  

```
>我是周树人
>>你找鲁迅
>>>和我周树人有什么关系  
要终止引用，同结束列表一样，需要在引用之后空一行
```

## 八、字符设置

### 转义字符

* \*, \\, \\\

```
* \*, \\, \\\
转义字符为'\',对于特殊字符如'*'等，需要在其前面加入'\'才能显示其原本的字符（类似c语言）
```

### 字体、字号、颜色

<font face="黑体">我是黑体字</font>
<font face="微软雅黑">我是微软雅黑</font>
<font face="黑体" size=12 color=red>我是12号红色黑体</font>

```
<font face="黑体">我是黑体字</font>
<font face="微软雅黑">我是微软雅黑</font>
<font face="黑体" size=12 color=red>我是12号红色黑体</font>
欲要解锁更多字体，请自行查电脑的字体库，找到字体的名字
```

## 九、内容目录

* 支持[TOC]的编辑器上，直接在文前输入[TOC]
* 用hexo搭建的GitHubPages会自动生成目录
* vscode安上markdown preview enhenced 之后会自动生成目录

## 十、代码块

### 行内式

c语言的`scanf()`怎么用？
```
c语言的`scanf()`怎么用？
用"`"符号包裹代码
```

### 缩进式多行代码

    #include <stdio.h>

    int main()
    {
      printf("hello world!");
      return 0;
    }

缩进4个空格则自动成为代码块，会一直持续到没有缩进的一行。（个人认为适用于不是任何编程语言但需要原封不动显示字符的地方）

### 包裹式多行代码

```c
#include <stdio.h>

    int main()
    {
      printf("hello world!");
      return 0;
    }
```

* 代码开始前输入\```,之后加入语言类型以实现语法高亮，如\```c
* 代码结束后输入\```,之后代码块结束

## 十一、流程图

```flow
st=>start: 开始
e=>end: 结束
op1=>operation: 新品开发流程
op2=>operation: 产品需求提出
op3=>operation: 产品试用 负责人：吴xx
op4=>operation: 包装
op5=>parallel: 继续讨论
op6=>operation: 讨论
cond=>condition: 确认？

st->op1->op2->op3->cond
cond(yes)->op4->e
cond(no)->op6->e
```

```
st=>start: 开始
e=>end: 结束
op1=>operation: 新品开发流程
op2=>operation: 产品需求提出
op3=>operation: 产品试用 负责人：吴xx
op4=>operation: 包装
op5=>parallel: 继续讨论
op6=>operation: 讨论
cond=>condition: 确认？

st->op1->op2->op3->cond
cond(yes)->op4->e
cond(no)->op6->e
```

1. 通过三个\`+flow进入流程图的代码块,再用三个\`结束
2. 编写流程图中的变量：变量=>操作块: 备注名(注意：冒号为英文的冒号，冒号与备注名之间有一个空格)
3. 构建变量之间的流程图关系
4. 格式一旦有一点差错，流程图无法显示

注意：生成流程图，有很多版本，如mermaid,flowchart,此处用flowchart示范

## 十二、表格

|序号|姓名|学号|
|-|-|-|
|1|张杰|2233|

|序号|姓名|学号|
|-:|:-|:-:|
|1|杰|2|

```
|序号|姓名|学号|
|-|-|-|
|1|张杰|2233|

|序号|姓名|学号|
|-:|:-|:-:|
|1|杰|2|
```

1. 第一行为表头
2. 第二行分隔表头和主体部分（还可以为不同的列指定对齐方向：默认为左对齐，:-为左对齐，-:为右对齐，:-为居中对齐，其中，冒号为英文冒号）
3. 从第三行起每一行为一个表格行
4. 列与列之间用管道符“|”隔开

## 十三、Latex公式

### 行内公式

质能守恒方程可以用$E = m c^2$表示

```
质能守恒方程可以用$E = m c^2$表示
```

### 整行公式

$$E = m c^2 $$

```
$$E = m c^2$$
```

### 块级公式

```math
E =m c^2  
```
\```math
E =m c^2  
\```

注意：块级公式，需要特定支持

* 一块只能写一个公式，换行没有意义（行内公式，整行公式也一样，一个包裹内只能写一个公式）

## 十四、分隔线

***

* * *

***

---

--------------

- - -

___

_ _ _

__________

```
***

* * *

***

---

--------------

- - -

___

_ _ _

__________

```

* 三个及以上\*,\-,\_(星号，减号，底线)，即可形成分隔线（中间有空格也可以），但是同一行不能出现其它字符。

## 十五、HTML语法

可用HTML的语法编辑markdown
