---
title: Numpy工具包
date: 2021-10-19 10:29:19
tags: [Python, 大数据, Numpy]
categories: 技术笔记
---
# 主要对象与其属性

* NumPy的主要对象`ndarray`是同种元素的多维数组。在多维数组中，所有的元素都是一种类型的元素表格，且通过一个正整数下标进行索引。

* 创建一个ndarray对象，查看各个属性


```python
from numpy import *
a = arange(15).reshape(3, 5)
a
```




    array([[ 0,  1,  2,  3,  4],
           [ 5,  6,  7,  8,  9],
           [10, 11, 12, 13, 14]])



* `ndarray.ndim`：该属性表示数组轴的个数。而在python语言中，轴的个数被称作秩。


```python
a.ndim #有横纵两个轴，所以ndim属性为2
```




    2



* `ndarray.shape`：该属性表示数组的维度，用来表示一个数组中各个维度上的大小。对于一个n行m列的矩阵，该属性的值为(n,m)。


```python
a.shape # 3行5列的数组，结果应为(3, 5)
```




    (3, 5)



* `ndarray.size`：该属性表示数组元素的总个数，等于属性中每个维度上元素个数的乘积。


```python
a.size #总共有3*5=15个元素
```




    15



* `ndarray.dtype`：该属性表示数组中的元素类型，可以通过dtype来指定使用哪一种Python类型。


```python
a.dtype # 该数组中元素的类型为int型
```




    dtype('int32')




```python
a.dtype.name # 直接显示类型
```




    'int32'



* `ndarray.itemsize`：该属性表示数组每个元素的字节大小。例如，当一个元素的类型为float64时，数组itemsize的属性值即为8。


```python
a.itemsize # 元素为int64型，itemsize属性值应为8
```




    4



# 创建数组

* 通过`array`函数创建数组


```python
a = array([2, 3, 4])
a
```




    array([2, 3, 4])




```python
a.dtype
```




    dtype('int32')



* 除此之外，我们还可以在创建数组类型时，可以将元素设置为特定的类型。例如，下面的例子中，数组可以设置为复数格式：


```python
a = array([[1, 2], [3, 4]], dtype=complex)
a
```




    array([[1.+0.j, 2.+0.j],
           [3.+0.j, 4.+0.j]])




```python
a.dtype
```




    dtype('complex128')



* 用`zeros()`函数创建一个全0的数组


```python
z = zeros((3, 4))
z
```




    array([[0., 0., 0., 0.],
           [0., 0., 0., 0.],
           [0., 0., 0., 0.]])



* 用函数`ones()`创建全1的数组


```python
o = ones((1, 2, 3), dtype=int16)
o
```




    array([[[1, 1, 1],
            [1, 1, 1]]], dtype=int16)



* 用`empty()`函数创建一个内容随机的数组


```python
e = empty((1, 2))
e
```




    array([[-2.74665012e-188,  1.34073553e-078]])



* 用`arange()`函数可以创建按照一定规则排列的数组，前两个参数是左闭右开的范围区间，第三个参数是步频(如果不设置则默认为1）,如果只有一个参数，则以这个参数为右边界，从0开始以1为步频。


```python
a = arange(10, 30, 5)
a
```




    array([10, 15, 20, 25])




```python
a = arange(10, 15)
a
```




    array([10, 11, 12, 13, 14])



# 打印数组

* 在打印一个数组时，NumPy的展示形式类似于嵌套列表，但呈现出以下特点的布局：
    * 从左到右打印最后的轴
    * 从顶向下打印次后的轴
    * 从顶向下打印剩下的轴，每个切片通过一个空行与下一个切片隔开
* 一维数组以行的形式打印出来，二维数组以矩阵的形式打印出来，三维数以矩阵列表的形式打印出来。

* 一维数组


```python
a = arange(6)
print(a)
```

    [0 1 2 3 4 5]


* 二维数组


```python
a = arange(6).reshape(2, 3)
print(a)
```

    [[0 1 2]
     [3 4 5]]


* 三维数组


```python
a = arange(24).reshape(2, 3, 4) # 最后一个轴有4个元素，从左到右打印；次后的轴上有3个元素，从顶向下打印；之后的轴有2个元素，从顶向下打印
print(a)
```

    [[[ 0  1  2  3]
      [ 4  5  6  7]
      [ 8  9 10 11]]
    
     [[12 13 14 15]
      [16 17 18 19]
      [20 21 22 23]]]


# 数组的操作

## 数组的基本运算

* 数组可以直接进行加减乘除等操作，但是数组的元素是元素之间一一对应进行运算的，所以进行运算的数组的形状必须是一样的。
* 一般运算并不改变数组本身，而是创建了一个新的数组


```python
a = array([1, 2, 3, 4, 5])
b = arange(5)
c = a - b
print(a)
print(b)
print(c)
```

    [1 2 3 4 5]
    [0 1 2 3 4]
    [1 1 1 1 1]


* 假如数组形状不一样进行运算，会直接报错


```python
a = array([1, 2])
b = arange(4)
c = a - b
```


    ---------------------------------------------------------------------------
    
    ValueError                                Traceback (most recent call last)
    
    <ipython-input-21-8e1e5c30d648> in <module>
          1 a = array([1, 2])
          2 b = arange(4)
    ----> 3 c = a - b


    ValueError: operands could not be broadcast together with shapes (2,) (4,) 


* 进行乘法运算


```python
a = arange(3)
b = a * 2
print(a)
print(b)
```

    [0 1 2]
    [0 2 4]


* 进行三角运算


```python
a = arange(2)
b = 10 * sin(a)
print(a)
print(b)
```

    [0 1]
    [0.         8.41470985]


* 进行比较运算


```python
a = arange(5, 20, 5)
b = a < 15
print(a)
print(b)
```

    [ 5 10 15]
    [ True  True False]


* 矩阵乘积：因为数组之间的乘积是通过元素对应相乘完成的，所以矩阵乘积需要使用特殊的函数`dot()`来实现


```python
a = array([[1, 1], [0, 1]])
b = array([[2, 0], [3, 4]])
c = dot(a, b)
print(a)
print(b)
print(c)
```

    [[1 1]
     [0 1]]
    [[2 0]
     [3 4]]
    [[5 4]
     [3 4]]


* 当多种类型数组进行计算时，结果得到的数组通常采用更精确的值，这种行为叫做upcast。


```python
a = ones(3, dtype=int32)
b = arange(0, 0.3, 0.1)
c = a - b
print(a)
print(b)
print(c)
```

    [1 1 1]
    [0.  0.1 0.2]
    [1.  0.9 0.8]


* 可以改变数组自身的运算，如`+=`、`*=`等


```python
a = ones((2, 3))
print(a)
```

    [[1. 1. 1.]
     [1. 1. 1.]]



```python
a *= 3
print(a)
```

    [[3. 3. 3.]
     [3. 3. 3.]]


## 数组的复制和视图

* 数组的复制有三种情况：
    * 完全不拷贝：简单的对数组对象进行赋值，就像引用赋值一样
    * 视图（浅复制）：视图对象使用数组对象的数据（不拷贝数据），可以对数据的表现形式进行组织，如果通过视图修改数据，则原数组的数据也会改变（数据还是使用的原来的， 没有拷贝），通过数组对象ndarray的`view()`方法生成一个数组的视图
    * 深复制：完全复制一个数组的数据，创建一个相同数据的数组、通过数组对象ndarray的`copy()`方法来完全拷贝一个数组的数据生成一个新的数组对象

* 完全不拷贝示例：


```python
a = arange(12)
b = a # 没有创建新的数组对象而直接赋值
b is a # a b就是相同数组对象的两个名字，是完全等价的
```




    True




```python
b.shape=3, 4 # 对b进行形状改变，a的形状也会改变，因为二者指向同一个对象
a
```




    array([[ 0,  1,  2,  3],
           [ 4,  5,  6,  7],
           [ 8,  9, 10, 11]])



* 视图（浅复制）示例：


```python
a = arange(12)
c = a.view() # 创建a数组的视图对象
c is a # c 与 a 不是同一个对象的两个名字
```




    False




```python
c.base is a # 通过base参数来获知c是a的一个视图
```




    True




```python
c.shape = 3, 4 # 修改的c的形状不会影响到原数组a
a
```




    array([ 0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11])




```python
c[0,1] = -1 # 修改c的数据会影响原数组的a的数据
a
```




    array([ 0, -1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11])



* 深复制示例：


```python
a = arange(4)
d = a.copy()
d is a
```




    False




```python
d.shape = 2, 2 # a和d是完全不相关的两个对象，修改d的形状或者数据都对a毫无影响
d[0,0] = -1
print("d:")
print(d)
print("a:")
print(a)
```

    d:
    [[-1  1]
     [ 2  3]]
    a:
    [0 1 2 3]

