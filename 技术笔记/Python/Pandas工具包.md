---
title: Pandas工具包
date: 2021-10-20 10:29:19
tags: [Python, 大数据, Pandas]
categories: 技术笔记
---
# Pandas基本概念

* Pandas工具包的数据结构可以按轴自动地或显式地对齐数据。

* 导入Pandas工具包。通常来说，但我们在一段代码中看到pd这一关键字时，就要考虑使用了Pandas这个工具包。


```python
import pandas as pd
```

* pandas主要的两个数据结构`Series`和`DataFrame`

# Series

* Series类似于一维数组，它由一组数据以及对应的数据标签（即索引）组成。

* Series的字符串由两部分组成：左边是字符串的索引，右边是字符串的值。如果我们没有指定数据索引，Series就会自动地创建一个从0到N-1（N为数据的长度）的整型索引。


* 创建Series对象


```python
series = pd.Series([1, 2, 3, 4])
series
```




    0    1
    1    2
    2    3
    3    4
    dtype: int64



* 左边的自动生成的索引，右边是值，可以分别查看Series对象的值和索引


```python
series.values # 查看值
```




    array([1, 2, 3, 4])




```python
series.index # 查看索引
```




    RangeIndex(start=0, stop=4, step=1)



* 可以手动设置索引


```python
series2 = pd.Series([1, 2, 3, 4], index=['a', 'b', 'c', 'd'])
series2
```




    a    1
    b    2
    c    3
    d    4
    dtype: int64



* 获取或者某个索引的对应的值


```python
print("a: ")
print(series2['a'])
series2['b'] = -1
print("b: ")
print(series2['b'])
```

    a: 
    1
    b: 
    -1


# DataFrame

* DataFrame是一种表格类型的数据结构，它含有一组有序的列。
* **每一列可以是不同类型的值**（例如数值、字符串、布尔值等）。
* DataFrame既可以按行索引，也可以按列索引，因而可以被视为由Series组成的字典。与其他数据结构相比，DataFrame中对行操作和对列操作基本上是平衡的。
* DataFrame可以自动加上索引（跟Series一样），且全部的列都会进行有序地排列

* 传入一个字典（元素是键值对）构建DataFrame


```python
data = {'state':['Zhang', 'Jie'], 'year':[2000, 2001], 'pop':[1.1, 2.3]}
dataFrame = pd.DataFrame(data)
dataFrame
```




<div>
<style scoped>
    .dataframe tbody tr th:only-of-type {
        vertical-align: middle;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }

    .dataframe thead th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>state</th>
      <th>year</th>
      <th>pop</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>Zhang</td>
      <td>2000</td>
      <td>1.1</td>
    </tr>
    <tr>
      <th>1</th>
      <td>Jie</td>
      <td>2001</td>
      <td>2.3</td>
    </tr>
  </tbody>
</table>
</div>



* 可以通过columns参数指定列的顺序


```python
dataFrame2 = pd.DataFrame(data, columns=['pop', 'state', 'year'])
dataFrame2
```




<div>
<style scoped>
    .dataframe tbody tr th:only-of-type {
        vertical-align: middle;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }

    .dataframe thead th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>pop</th>
      <th>state</th>
      <th>year</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>1.1</td>
      <td>Zhang</td>
      <td>2000</td>
    </tr>
    <tr>
      <th>1</th>
      <td>2.3</td>
      <td>Jie</td>
      <td>2001</td>
    </tr>
  </tbody>
</table>
</div>

