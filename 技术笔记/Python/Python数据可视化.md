---
title: Python数据可视化
date: 2021-11-02 14:39:19
tags: [Python, 大数据]
categories: 技术笔记
---

# Matplotlib

* Matplotlib来自于由John Hunter在2002年启动的一个用于创建图表的绘图项目，其目的是为Python构建一个与Matlab之间进行交互的绘图接口。是最著名Python绘图库， 主要用于二维绘图。具有画图质量高、方便快捷的绘图模块等特点。

## Matplotlib入门

* 绘图API：pyplot模块
* 集成库：pylab模块（包含NumPy和pyplot中的常用函数）

* 导入Matplotlib绘图包
* pyplot对象相当于画笔，`plot()`函数在设置画笔绘制的对象，其它的类似函数在设置画笔的其它性质，最后通过`show()`函数使用画笔绘制出图像


```python
import matplotlib.pyplot as plt
import numpy as np
```


```python
t = np.arange(0., 4.0, 0.1) # 从0到4，步频为0.1生成t数字序列
plt.plot(t, t) # 前面一位是横坐标，后面对应的是纵坐标
plt.show()
t
```


    
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103090642.png)
    





    array([0. , 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1. , 1.1, 1.2,
           1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2. , 2.1, 2.2, 2.3, 2.4, 2.5,
           2.6, 2.7, 2.8, 2.9, 3. , 3.1, 3.2, 3.3, 3.4, 3.5, 3.6, 3.7, 3.8,
           3.9])




```python
plt.plot(t, t**2)
plt.show()
```


    
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103090658.png)
    


* 可以将两个曲线绘制在一个图里


```python
plt.plot(t, t, t, t**2)
plt.show()
```


    
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103090714.png)
    


* 通过`plot()`函数的`o`参数，将图像变为散点图


```python
plt.plot(t, t**2, 'o')
plt.show()
```


    
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103090726.png)
    


* 使用`bar()`函数，绘制柱状图


```python
t = np.arange(0, 10, 1)
plt.bar(t, t**2)
plt.show()
```


    
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103090742.png)
    


## Matplotlib图像属性控制

### 线条属性

* 色彩和样式通过在绘图函数如`plot()`中添加参数来控制，常用参数：![MF7g00](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/uPic/MF7g00.png)

* 颜色修改示例：


```python
t = np.arange(0, 4, 0.1)
plt.plot(t, t**2, 'g') # 设为绿色
plt.show()
```


    
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103090755.png)
    


* 修改线型示例：


```python
plt.plot(t, t**2,'--') # 设为虚线
plt.show()
```


    
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103090806.png)
    


* 设置点的形状示例：


```python
t = np.arange(0, 10, 1)
plt.plot(t, t**2, 'v') #设置点的形状为三角形
plt.show()
```


    
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103090818.png)
    


* 多个参数同时使用,需要**指明参数的名称**才能同时生效：
    * 颜色：`color`
    * 样式：`linestyle`
    * 点形状：`marker`
    * 线条宽度：`linewidth`
    * 线条含义标识：`label`


```python
t1 = np.arange(0, 4, 0.1)
t2 = np.arange(0, 4, 0.5)
# 先设置参数，再显示图像，才能生效（plt可以理解为画笔）
plt.plot(t1, t1**2, linestyle='--', color='r',linewidth='5', label='Line1') 
plt.plot(t2, t2+2, color='g', linewidth='3', marker='o', label='Line2')
plt.legend(loc='upper left') # 设置线条含义标识显示位置（不设置显示不出来）
plt.show()
```


    
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103090829.png)
    


### 添加说明文字

* 可以设置整个图的名称、坐标轴的名称

* 设置图的名称


```python
t = np.arange(0, 4, 0.1)
plt.plot(t, t**2) # 设为绿色

plt.title("Hello!")
plt.show()
```


    
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103090848.png)
    


* 设置横纵坐标


```python
plt.plot(t, t**2) # 设为绿色
# 先设置参数，再显示图像，才能生效（plt可以理解为画笔）
plt.title("Hello!")
plt.xlabel("NumberX")
plt.ylabel("NumberY")
plt.show()
```


    
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103090900.png)
    


## 子图

* 子图是指在一次绘制过程中绘制的多个坐标图，每一个被包含的的坐标图都是子图。
* 子图可以通过pyplot对象的`subplot()`函数来绘制
* 子图也可以通过设置坐标轴的长短和坐标原点的`axes()`方法来创建。

* 通过`subplot()`函数在一个图中绘制四个子图示例：
* 参数的意义：如221(也可写成2，2，1）的意思就是创建2行2列4个子图，当前子图在第一个位置


```python
# t1 = np.arange(0, 4, 0.1)
# t2 = np.arange(0, 4, 0.5)
plt.subplot(221)
plt.subplot(222)
plt.subplot(223)
plt.subplot(224)
```




    <AxesSubplot:>




    
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103090917.png)
    


* 在子图中绘制图像


```python
t1 = np.arange(0, 4, 0.1)
t2 = np.arange(0, 4, 0.5)
#先创建子图
plt.subplot(1,2,1)
#再绘制图像
plt.plot(t1, t1**2)
plt.subplot(1,2,2)
plt.plot(t2, 2*t2+3)
plt.show()
```


    
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103091023.png)
    


* 使用`axes()`函数设置坐标轴来创建2个子图


```python
plt.axes([.1,.1,0.8,0.8]) #创建原点在（0.1，0.1），两个坐标轴的长度都为0.8的子图
plt.plot(t1, t1**2) # 在上面创建的子图上绘制曲线
plt.axes([.3,.5,0.3,0.3])
plt.plot(t2, 2*t2+3)
plt.show()
```


    
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103091038.png)
    


# Pandas绘图

* Pandas 对 Matplotlib 绘图软件包的基础上单独封装了一个plot()接口，通过调用该接口可以实现常用的绘图操作。
* Pandas 之所以能够实现了数据可视化，主要利用了 Matplotlib 库的 plot() 方法，它对 plot() 方法做了简单的封装，因此您可以直接调用该接口。

* 示例：


```python
import pandas as pd
import numpy as np
df = pd.DataFrame(np.random.randn(8, 4), index = pd.date_range('11/2/2021', periods=8), columns=list('ABCD')) #randn使数据具有正态分布
df.plot(linestyle='--') #调用plot（）方法绘图
```




    <AxesSubplot:>




    
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103091048.png)
    


* 除了默认的线形图，Pandas也可以绘制其它图形：
    * 柱状图：bar() 或 barh()
    * 直方图：hist()
    * 箱状箱：box()
    * 区域图：area()
    * 散点图：scatter()
* 通过关键字参数kind可以把上述方法传递给 plot()。

* 绘制柱状图：


```python
df.plot.bar()
```




    <AxesSubplot:>




    
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103091100.png)
    


* 绘制水平柱状图


```python
df.plot(kind='barh')
```




    <AxesSubplot:>




    
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103091111.png)
    


* 绘制柱状堆叠图（参数stacked设为True）


```python
df.plot(kind='bar', stacked='True')
```




    <AxesSubplot:>




![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211103091121.png)
    