---
title: Python数据处理技术
date: 2021-10-14 17:01:19
tags: [Python, 大数据]
categories: 技术笔记
---
# 合并数据集

## 索引上的合并


```python
import pandas as pd
import numpy as np
```

* 连接的方法：
    * 通过列与索引合并
    * 通过索引和索引合并
    * 复合索引和多个列来合并

### 生成索引的两种方式

* 构建数据表（DataFrame）自动生成索引：使用pandas库的DataFrame对象，花括号中以键值对的形式，给出列名和值，索引会自动按序生成


```python
left1 = pd.DataFrame({'key': ['a', 'b', 'a','a', 'b', 'c'], 'value': range(6)})
left1
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
      <th>key</th>
      <th>value</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>a</td>
      <td>0</td>
    </tr>
    <tr>
      <th>1</th>
      <td>b</td>
      <td>1</td>
    </tr>
    <tr>
      <th>2</th>
      <td>a</td>
      <td>2</td>
    </tr>
    <tr>
      <th>3</th>
      <td>a</td>
      <td>3</td>
    </tr>
    <tr>
      <th>4</th>
      <td>b</td>
      <td>4</td>
    </tr>
    <tr>
      <th>5</th>
      <td>c</td>
      <td>5</td>
    </tr>
  </tbody>
</table>
</div>



* 可以通过设置index参数来指定索引创造DataFrame对象


```python
right1 = pd.DataFrame({'group_val':[3.5, 7]}, index=['a', 'b'])
right1
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
      <th>group_val</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>a</th>
      <td>3.5</td>
    </tr>
    <tr>
      <th>b</th>
      <td>7.0</td>
    </tr>
  </tbody>
</table>
</div>


### 连接的模式

* 根据索引和列来合并两个表，使用`merge()`函数，参数依次为：左表（DataFrame对象)，右表，left_on参数指定左表的某一列用于合并，right_index参数指定使用右表的索引来合并（因为索引是唯一的，所以直接设置True或false即可），同理也有right_on和left_index参数，只是left_on和left_index参数不能同时使用
* 内连接：`merge()`函数的默认方式，求两张表的交集
* 外连接：求两种表的并集，如果有的元素，一个表有而另一个表没有，则在代表另一个表值的列使用`NaN`填充

#### 内连接

* 内连接的使用示例：


```python
merge1 = pd.merge(left1, right1, left_on='key', right_index=True)
merge1
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
      <th>key</th>
      <th>value</th>
      <th>group_val</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>a</td>
      <td>0</td>
      <td>3.5</td>
    </tr>
    <tr>
      <th>2</th>
      <td>a</td>
      <td>2</td>
      <td>3.5</td>
    </tr>
    <tr>
      <th>3</th>
      <td>a</td>
      <td>3</td>
      <td>3.5</td>
    </tr>
    <tr>
      <th>1</th>
      <td>b</td>
      <td>1</td>
      <td>7.0</td>
    </tr>
    <tr>
      <th>4</th>
      <td>b</td>
      <td>4</td>
      <td>7.0</td>
    </tr>
  </tbody>
</table>
</div>

#### 外连接

* 外连接：求两张表的并集,`merge()`函数需要设置`how`参数来启动外连接模式


```python
merge2 = pd.merge(left1, right1, left_on='key', right_index=True,how='outer')
merge2
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
      <th>key</th>
      <th>value</th>
      <th>group_val</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>a</td>
      <td>0</td>
      <td>3.5</td>
    </tr>
    <tr>
      <th>2</th>
      <td>a</td>
      <td>2</td>
      <td>3.5</td>
    </tr>
    <tr>
      <th>3</th>
      <td>a</td>
      <td>3</td>
      <td>3.5</td>
    </tr>
    <tr>
      <th>1</th>
      <td>b</td>
      <td>1</td>
      <td>7.0</td>
    </tr>
    <tr>
      <th>4</th>
      <td>b</td>
      <td>4</td>
      <td>7.0</td>
    </tr>
    <tr>
      <th>5</th>
      <td>c</td>
      <td>5</td>
      <td>NaN</td>
    </tr>
  </tbody>
</table>
</div>

#### 通过复合索引合并

* 通过复合索引和两个列来合并

  * 通过自动生成索引创建左表


```python
lefth = pd.DataFrame({'key1': ['Ohio', 'Ohio', 'Ohio', 'Nevada', 'Nevada'], 'key2': [2000, 2001, 2002, 2001, 2002], 
                      'data': np.arange(5a)})
lefth
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
      <th>key1</th>
      <th>key2</th>
      <th>data</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>Ohio</td>
      <td>2000</td>
      <td>0</td>
    </tr>
    <tr>
      <th>1</th>
      <td>Ohio</td>
      <td>2001</td>
      <td>1</td>
    </tr>
    <tr>
      <th>2</th>
      <td>Ohio</td>
      <td>2002</td>
      <td>2</td>
    </tr>
    <tr>
      <th>3</th>
      <td>Nevada</td>
      <td>2001</td>
      <td>3</td>
    </tr>
    <tr>
      <th>4</th>
      <td>Nevada</td>
      <td>2002</td>
      <td>4</td>
    </tr>
  </tbody>
</table>
</div>



  * ​	指定两个列作为复合索引


```python
righth = pd.DataFrame(np.arange(6*2).reshape(6, 2), index = [['Nevada', 'Nevada', 'Ohio', 'Ohio', 'Ohio', 'Ohio'], 
                                                            [2001, 2000, 2000, 2000, 2001, 2002]],
                     columns=['event1', 'event2'])
righth
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
      <th></th>
      <th>event1</th>
      <th>event2</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th rowspan="2" valign="top">Nevada</th>
      <th>2001</th>
      <td>0</td>
      <td>1</td>
    </tr>
    <tr>
      <th>2000</th>
      <td>2</td>
      <td>3</td>
    </tr>
    <tr>
      <th rowspan="4" valign="top">Ohio</th>
      <th>2000</th>
      <td>4</td>
      <td>5</td>
    </tr>
    <tr>
      <th>2000</th>
      <td>6</td>
      <td>7</td>
    </tr>
    <tr>
      <th>2001</th>
      <td>8</td>
      <td>9</td>
    </tr>
    <tr>
      <th>2002</th>
      <td>10</td>
      <td>11</td>
    </tr>
  </tbody>
</table>
</div>



  * 使用`merge()`函数内连接两个表


```python
merge3 = pd.merge(lefth, righth, left_on=['key1', 'key2'], right_index=True)
merge3
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
      <th>key1</th>
      <th>key2</th>
      <th>data</th>
      <th>event1</th>
      <th>event2</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>Ohio</td>
      <td>2000</td>
      <td>0</td>
      <td>4</td>
      <td>5</td>
    </tr>
    <tr>
      <th>0</th>
      <td>Ohio</td>
      <td>2000</td>
      <td>0</td>
      <td>6</td>
      <td>7</td>
    </tr>
    <tr>
      <th>1</th>
      <td>Ohio</td>
      <td>2001</td>
      <td>1</td>
      <td>8</td>
      <td>9</td>
    </tr>
    <tr>
      <th>2</th>
      <td>Ohio</td>
      <td>2002</td>
      <td>2</td>
      <td>10</td>
      <td>11</td>
    </tr>
    <tr>
      <th>3</th>
      <td>Nevada</td>
      <td>2001</td>
      <td>3</td>
      <td>0</td>
      <td>1</td>
    </tr>
  </tbody>
</table>
</div>



  * 结果分析：因为Nevada 2000这个组合在左表中存在而在右表中不存在，所以在合并表中并没有出现

---

* 让两个表都使用索引来合并


```python
left2 = pd.DataFrame([[1., 2.,], [3., 4.], [5., 6.]], index=['a', 'c', 'e'], 
                     columns=['Ohio', 'Nevada'])
left2
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
      <th>Ohio</th>
      <th>Nevada</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>a</th>
      <td>1.0</td>
      <td>2.0</td>
    </tr>
    <tr>
      <th>c</th>
      <td>3.0</td>
      <td>4.0</td>
    </tr>
    <tr>
      <th>e</th>
      <td>5.0</td>
      <td>6.0</td>
    </tr>
  </tbody>
</table>
</div>




```python
right2 = pd.DataFrame([[7., 8.], [9., 10.], [11., 12.], [13., 14.]], index=['b', 'c', 'd', 'e'], 
                      columns=['Missouri', 'Alabama'])
right2
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
      <th>Missouri</th>
      <th>Alabama</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>b</th>
      <td>7.0</td>
      <td>8.0</td>
    </tr>
    <tr>
      <th>c</th>
      <td>9.0</td>
      <td>10.0</td>
    </tr>
    <tr>
      <th>d</th>
      <td>11.0</td>
      <td>12.0</td>
    </tr>
    <tr>
      <th>e</th>
      <td>13.0</td>
      <td>14.0</td>
    </tr>
  </tbody>
</table>
</div>




```python
merge4 = pd.merge(left2, right2, left_index = True, right_index = True)
merge4
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
      <th>Ohio</th>
      <th>Nevada</th>
      <th>Missouri</th>
      <th>Alabama</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>c</th>
      <td>3.0</td>
      <td>4.0</td>
      <td>9.0</td>
      <td>10.0</td>
    </tr>
    <tr>
      <th>e</th>
      <td>5.0</td>
      <td>6.0</td>
      <td>13.0</td>
      <td>14.0</td>
    </tr>
  </tbody>
</table>
</div>

---

#### 通过join函数连接

* 通过`join`函数来合并：
    * 有主表：左表->所以默认左连接（左表的都保留，右表和左表相同索引的才保留，右表不存在的用`NaN`填充）
    * 同样可以通过`how`参数来设置连接方式


```python
merge5 = left2.join(right2)
merge5
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
      <th>Ohio</th>
      <th>Nevada</th>
      <th>Missouri</th>
      <th>Alabama</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>a</th>
      <td>1.0</td>
      <td>2.0</td>
      <td>NaN</td>
      <td>NaN</td>
    </tr>
    <tr>
      <th>c</th>
      <td>3.0</td>
      <td>4.0</td>
      <td>9.0</td>
      <td>10.0</td>
    </tr>
    <tr>
      <th>e</th>
      <td>5.0</td>
      <td>6.0</td>
      <td>13.0</td>
      <td>14.0</td>
    </tr>
  </tbody>
</table>
</div>



  * 可以通过`on`参数来使用左表的特定列来合并


```python
merge6 = lefth.join(righth, on=['key1', 'key2'])
merge6
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
      <th>key1</th>
      <th>key2</th>
      <th>data</th>
      <th>event1</th>
      <th>event2</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>Ohio</td>
      <td>2000</td>
      <td>0</td>
      <td>4.0</td>
      <td>5.0</td>
    </tr>
    <tr>
      <th>0</th>
      <td>Ohio</td>
      <td>2000</td>
      <td>0</td>
      <td>6.0</td>
      <td>7.0</td>
    </tr>
    <tr>
      <th>1</th>
      <td>Ohio</td>
      <td>2001</td>
      <td>1</td>
      <td>8.0</td>
      <td>9.0</td>
    </tr>
    <tr>
      <th>2</th>
      <td>Ohio</td>
      <td>2002</td>
      <td>2</td>
      <td>10.0</td>
      <td>11.0</td>
    </tr>
    <tr>
      <th>3</th>
      <td>Nevada</td>
      <td>2001</td>
      <td>3</td>
      <td>0.0</td>
      <td>1.0</td>
    </tr>
    <tr>
      <th>4</th>
      <td>Nevada</td>
      <td>2002</td>
      <td>4</td>
      <td>NaN</td>
      <td>NaN</td>
    </tr>
  </tbody>
</table>
</div>

---

#### 轴向连接

* 轴向连接：将多个同样的表合并为一个表（增加行数）concatennation
    * 使用Numpy的函数`concatenate()`


```python
arr = np.arange(12).reshape(3, 4)
arr
```




    array([[ 0,  1,  2,  3],
           [ 4,  5,  6,  7],
           [ 8,  9, 10, 11]])



  * 使用`concatenate()`函数横向或纵向合并两个arr
  * 通过`axis`参数来控制合并的方向


```python
arr1 = np.concatenate([arr, arr], axis=1) # 横向合并
arr1
```




    array([[ 0,  1,  2,  3,  0,  1,  2,  3],
           [ 4,  5,  6,  7,  4,  5,  6,  7],
           [ 8,  9, 10, 11,  8,  9, 10, 11]])




```python
arr2 = np.concatenate([arr, arr], axis=0) # 纵向合并
arr2
```




    array([[ 0,  1,  2,  3],
           [ 4,  5,  6,  7],
           [ 8,  9, 10, 11],
           [ 0,  1,  2,  3],
           [ 4,  5,  6,  7],
           [ 8,  9, 10, 11]])



### 合并重叠数据

* 创建两个列一致，部分数据一致的一位序列Series


```python
a = pd.Series([np.nan, 2.5, np.nan, 3.5, 4.5, np.nan], index=['f', 'e', 'd', 'c', 'b', 'a'])
a
```




    f    NaN
    e    2.5
    d    NaN
    c    3.5
    b    4.5
    a    NaN
    dtype: float64




```python
b = pd.Series(np.arange(len(a), dtype=np.float64), index=['f', 'e', 'd', 'c', 'b', 'a'])
b[-1] = np.nan #-1下标指代序列的最后一位元素
b
```




    f    0.0
    e    1.0
    d    2.0
    c    3.0
    b    4.0
    a    NaN
    dtype: float64



* 合并两个数据集，以a表为标准，a表中存在的数据保持原样，a表中不存在（NaN）的数据从b中合并过来


```python
c = np.where(pd.isnull(a), b, a) # 当a为空时取b的值，否则取a的值
c
```




    array([0. , 2.5, 2. , 3.5, 4.5, nan])



# 数据转换

## 移除重复数据


```python
data = pd.DataFrame({'k1':['one']*3+['two']*4, 'k2':[1,1,2,3,3,4,4]})
data
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
      <th>k1</th>
      <th>k2</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>one</td>
      <td>1</td>
    </tr>
    <tr>
      <th>1</th>
      <td>one</td>
      <td>1</td>
    </tr>
    <tr>
      <th>2</th>
      <td>one</td>
      <td>2</td>
    </tr>
    <tr>
      <th>3</th>
      <td>two</td>
      <td>3</td>
    </tr>
    <tr>
      <th>4</th>
      <td>two</td>
      <td>3</td>
    </tr>
    <tr>
      <th>5</th>
      <td>two</td>
      <td>4</td>
    </tr>
    <tr>
      <th>6</th>
      <td>two</td>
      <td>4</td>
    </tr>
  </tbody>
</table>
</div>



* 使用DataFrame对象的`duplicated()`函数检测是否重复行（是为true），返回一个Series对象


```python
dup = data.duplicated()
dup
```




    0    False
    1     True
    2    False
    3    False
    4     True
    5    False
    6     True
    dtype: bool



* `drop_duplicates()`方法去重, 可以指定用于比较是否相同的某一(或多）列（不一定完全重复),默认保留index最小的行，可以通过设置`keep='last'`参数保留index最大的数据


```python
nodup = data.drop_duplicates(keep='last')
nodup
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
      <th>k1</th>
      <th>k2</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>1</th>
      <td>one</td>
      <td>1</td>
    </tr>
    <tr>
      <th>2</th>
      <td>one</td>
      <td>2</td>
    </tr>
    <tr>
      <th>4</th>
      <td>two</td>
      <td>3</td>
    </tr>
    <tr>
      <th>6</th>
      <td>two</td>
      <td>4</td>
    </tr>
  </tbody>
</table>
</div>



* 给data添加一列


```python
data['v1'] = range(7)
data
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
      <th>k1</th>
      <th>k2</th>
      <th>v1</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>one</td>
      <td>1</td>
      <td>0</td>
    </tr>
    <tr>
      <th>1</th>
      <td>one</td>
      <td>1</td>
      <td>1</td>
    </tr>
    <tr>
      <th>2</th>
      <td>one</td>
      <td>2</td>
      <td>2</td>
    </tr>
    <tr>
      <th>3</th>
      <td>two</td>
      <td>3</td>
      <td>3</td>
    </tr>
    <tr>
      <th>4</th>
      <td>two</td>
      <td>3</td>
      <td>4</td>
    </tr>
    <tr>
      <th>5</th>
      <td>two</td>
      <td>4</td>
      <td>5</td>
    </tr>
    <tr>
      <th>6</th>
      <td>two</td>
      <td>4</td>
      <td>6</td>
    </tr>
  </tbody>
</table>
</div>



* 可以指定通过具体哪些列来判断是否重复


```python
nodup2 = data.drop_duplicates(['k1', 'k2'], keep='last')
nodup2
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
      <th>k1</th>
      <th>k2</th>
      <th>v1</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>1</th>
      <td>one</td>
      <td>1</td>
      <td>1</td>
    </tr>
    <tr>
      <th>2</th>
      <td>one</td>
      <td>2</td>
      <td>2</td>
    </tr>
    <tr>
      <th>4</th>
      <td>two</td>
      <td>3</td>
      <td>4</td>
    </tr>
    <tr>
      <th>6</th>
      <td>two</td>
      <td>4</td>
      <td>6</td>
    </tr>
  </tbody>
</table>
</div>



## 数据替换

* 定义一个series


```python
data = pd.Series([1., -999, 2., -1000, 3.])
data
```




    0       1.0
    1    -999.0
    2       2.0
    3   -1000.0
    4       3.0
    dtype: float64



* 通过`replace()`方法将-999替换为NaN


```python
data.replace(-999, np.nan)
```




    0       1.0
    1       NaN
    2       2.0
    3   -1000.0
    4       3.0
    dtype: float64



* 可以同时替换多个值，例如将-999和-1000同时替换为NaN


```python
data.replace([-999, -1000], np.nan)
```




    0    1.0
    1    NaN
    2    2.0
    3    NaN
    4    3.0
    dtype: float64



* 可以实现不同的值对应替换替换为不同的值,例如将-999替换为NaN，-1000替换为0


```python
data.replace([-999, -1000], [np.nan, 0])
```




    0    1.0
    1    NaN
    2    2.0
    3    0.0
    4    3.0
    dtype: float64



## 检测异常值

* 创建一个含有正态分布数据的DataFrame, 通过DataFrame对象的`describe()`方法来获取统计特征值


```python
data = pd.DataFrame(np.random.randn(1000, 4))
data.describe()
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
      <th>0</th>
      <th>1</th>
      <th>2</th>
      <th>3</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>count</th>
      <td>1000.000000</td>
      <td>1000.000000</td>
      <td>1000.000000</td>
      <td>1000.000000</td>
    </tr>
    <tr>
      <th>mean</th>
      <td>0.013166</td>
      <td>-0.000481</td>
      <td>-0.030189</td>
      <td>-0.009518</td>
    </tr>
    <tr>
      <th>std</th>
      <td>0.976413</td>
      <td>1.016713</td>
      <td>0.997048</td>
      <td>1.008174</td>
    </tr>
    <tr>
      <th>min</th>
      <td>-3.016676</td>
      <td>-3.162513</td>
      <td>-3.282324</td>
      <td>-3.535175</td>
    </tr>
    <tr>
      <th>25%</th>
      <td>-0.649836</td>
      <td>-0.688303</td>
      <td>-0.703782</td>
      <td>-0.698088</td>
    </tr>
    <tr>
      <th>50%</th>
      <td>0.004212</td>
      <td>0.011474</td>
      <td>0.009120</td>
      <td>-0.001872</td>
    </tr>
    <tr>
      <th>75%</th>
      <td>0.695183</td>
      <td>0.679623</td>
      <td>0.639148</td>
      <td>0.649286</td>
    </tr>
    <tr>
      <th>max</th>
      <td>4.407439</td>
      <td>4.139005</td>
      <td>2.763371</td>
      <td>3.721676</td>
    </tr>
  </tbody>
</table>
</div>



* 选出含有大于3或者小于-3的值的行


```python
data[(np.abs(data) >3).any(1)]
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
      <th>0</th>
      <th>1</th>
      <th>2</th>
      <th>3</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>38</th>
      <td>-0.795664</td>
      <td>-0.330065</td>
      <td>-3.016717</td>
      <td>2.120561</td>
    </tr>
    <tr>
      <th>50</th>
      <td>-0.629000</td>
      <td>0.860827</td>
      <td>-0.125433</td>
      <td>-3.535175</td>
    </tr>
    <tr>
      <th>79</th>
      <td>-0.544495</td>
      <td>-1.288971</td>
      <td>-3.029938</td>
      <td>0.279044</td>
    </tr>
    <tr>
      <th>113</th>
      <td>-3.016676</td>
      <td>0.869205</td>
      <td>-1.010988</td>
      <td>-0.573853</td>
    </tr>
    <tr>
      <th>254</th>
      <td>4.407439</td>
      <td>1.526187</td>
      <td>-1.255663</td>
      <td>-0.069301</td>
    </tr>
    <tr>
      <th>276</th>
      <td>-0.646509</td>
      <td>-2.390895</td>
      <td>1.456119</td>
      <td>-3.337455</td>
    </tr>
    <tr>
      <th>308</th>
      <td>1.139149</td>
      <td>4.139005</td>
      <td>-1.131373</td>
      <td>0.575723</td>
    </tr>
    <tr>
      <th>493</th>
      <td>3.189728</td>
      <td>0.442730</td>
      <td>-1.561426</td>
      <td>0.402213</td>
    </tr>
    <tr>
      <th>509</th>
      <td>0.267529</td>
      <td>-3.162513</td>
      <td>1.107551</td>
      <td>-1.028598</td>
    </tr>
    <tr>
      <th>535</th>
      <td>-0.679389</td>
      <td>3.022345</td>
      <td>-0.983833</td>
      <td>0.965855</td>
    </tr>
    <tr>
      <th>758</th>
      <td>-0.182049</td>
      <td>0.552248</td>
      <td>1.448889</td>
      <td>3.721676</td>
    </tr>
    <tr>
      <th>841</th>
      <td>1.724263</td>
      <td>1.075039</td>
      <td>-3.282324</td>
      <td>1.537926</td>
    </tr>
    <tr>
      <th>895</th>
      <td>-1.313186</td>
      <td>3.063947</td>
      <td>0.154505</td>
      <td>1.813358</td>
    </tr>
    <tr>
      <th>931</th>
      <td>1.180028</td>
      <td>3.300766</td>
      <td>-0.809880</td>
      <td>-1.084533</td>
    </tr>
    <tr>
      <th>933</th>
      <td>-0.037565</td>
      <td>0.175728</td>
      <td>-3.255756</td>
      <td>1.189677</td>
    </tr>
  </tbody>
</table>
</div>



* 将选出的行的异常值用3或者-3替换


```python
data[np.abs(data) > 3] = np.sign(data) * 3
data.describe()
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
      <th>0</th>
      <th>1</th>
      <th>2</th>
      <th>3</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>count</th>
      <td>1000.000000</td>
      <td>1000.000000</td>
      <td>1000.000000</td>
      <td>1000.000000</td>
    </tr>
    <tr>
      <th>mean</th>
      <td>0.011585</td>
      <td>-0.001845</td>
      <td>-0.029604</td>
      <td>-0.009367</td>
    </tr>
    <tr>
      <th>std</th>
      <td>0.970417</td>
      <td>1.011010</td>
      <td>0.995229</td>
      <td>1.002956</td>
    </tr>
    <tr>
      <th>min</th>
      <td>-3.000000</td>
      <td>-3.000000</td>
      <td>-3.000000</td>
      <td>-3.000000</td>
    </tr>
    <tr>
      <th>25%</th>
      <td>-0.649836</td>
      <td>-0.688303</td>
      <td>-0.703782</td>
      <td>-0.698088</td>
    </tr>
    <tr>
      <th>50%</th>
      <td>0.004212</td>
      <td>0.011474</td>
      <td>0.009120</td>
      <td>-0.001872</td>
    </tr>
    <tr>
      <th>75%</th>
      <td>0.695183</td>
      <td>0.679623</td>
      <td>0.639148</td>
      <td>0.649286</td>
    </tr>
    <tr>
      <th>max</th>
      <td>3.000000</td>
      <td>3.000000</td>
      <td>2.763371</td>
      <td>3.000000</td>
    </tr>
  </tbody>
</table>
</div>



# 排列和随机采样

* 使用`permutation()`函数可以实现对series或者DataFreame对象的随机排列工作。

* 先生成一个五行4列的矩阵


```python
df = pd.DataFrame(np.arange(5 * 4).reshape(5, 4))
df
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
      <th>0</th>
      <th>1</th>
      <th>2</th>
      <th>3</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>0</td>
      <td>1</td>
      <td>2</td>
      <td>3</td>
    </tr>
    <tr>
      <th>1</th>
      <td>4</td>
      <td>5</td>
      <td>6</td>
      <td>7</td>
    </tr>
    <tr>
      <th>2</th>
      <td>8</td>
      <td>9</td>
      <td>10</td>
      <td>11</td>
    </tr>
    <tr>
      <th>3</th>
      <td>12</td>
      <td>13</td>
      <td>14</td>
      <td>15</td>
    </tr>
    <tr>
      <th>4</th>
      <td>16</td>
      <td>17</td>
      <td>18</td>
      <td>19</td>
    </tr>
  </tbody>
</table>
</div>



* 再生成一个随机排列的5位数组，用于按这个顺序打乱矩阵


```python
sampler = np.random.permutation(5)
sampler
```




    array([3, 2, 0, 4, 1])



* 按照sampler排列的顺序排列矩阵，因为sampler是随机排列的，所以实现了打乱矩阵，随机采样的效果
* `take()`函数可以实现按照特定序列实现矩阵的行调换的功能


```python
df.take(sampler)
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
      <th>0</th>
      <th>1</th>
      <th>2</th>
      <th>3</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>3</th>
      <td>12</td>
      <td>13</td>
      <td>14</td>
      <td>15</td>
    </tr>
    <tr>
      <th>2</th>
      <td>8</td>
      <td>9</td>
      <td>10</td>
      <td>11</td>
    </tr>
    <tr>
      <th>0</th>
      <td>0</td>
      <td>1</td>
      <td>2</td>
      <td>3</td>
    </tr>
    <tr>
      <th>4</th>
      <td>16</td>
      <td>17</td>
      <td>18</td>
      <td>19</td>
    </tr>
    <tr>
      <th>1</th>
      <td>4</td>
      <td>5</td>
      <td>6</td>
      <td>7</td>
    </tr>
  </tbody>
</table>
</div>



* 合并实现，并只取出3行数据


```python
df.take(np.random.permutation(len(df))[:3])
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
      <th>0</th>
      <th>1</th>
      <th>2</th>
      <th>3</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>0</td>
      <td>1</td>
      <td>2</td>
      <td>3</td>
    </tr>
    <tr>
      <th>3</th>
      <td>12</td>
      <td>13</td>
      <td>14</td>
      <td>15</td>
    </tr>
    <tr>
      <th>4</th>
      <td>16</td>
      <td>17</td>
      <td>18</td>
      <td>19</td>
    </tr>
  </tbody>
</table>
</div>


