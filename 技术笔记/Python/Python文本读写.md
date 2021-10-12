---
title: Python文件读写
date: 2021-10-12 15:01:19
tags: [Python]
categories: 技术笔记
---
# 读取文本文件

## 读取文本文件的基本函数

* 以只读模式打开文件：r代表读取模式，若文件不存在则会报错。


```python
fp = open("iris.txt", "r")
print(file)
```

    out: <_io.TextIOWrapper name='iris.txt' mode='r' encoding='UTF-8'>


* 一次读入文件的一行，然后“游标"自动转到文件的下一行首


```python
fp.readline() # 读取一行
```


    '150\t4\tsetosa\tversicolor\tvirginica\n'


```python
fp.readline() #再一次调用，则已经读到下一行
```


    '5.1\t3.5\t1.4\t0.2\t0\n'

* 一次读取到文件末尾


```python
fp.readlines()
```


    ['4.9\t3\t1.4\t0.2\t0\n',
     '4.7\t3.2\t1.3\t0.2\t0\n',
     '4.6\t3.1\t1.5\t0.2\t0\n',
    	......
     '6.5\t3\t5.2\t2\t2\n',
     '6.2\t3.4\t5.4\t2.3\t2\n',
     '5.9\t3\t5.1\t1.8\t2\n']

* 将游标跳到文件开始位置


```python
fp.seek(0) # 将游标跳到第一行再读取一行，则又读到了文件的第一行
fp.readline()
```


    '150\t4\tsetosa\tversicolor\tvirginica\n' 

* close关闭文件


```python
fp.close() # 关闭文件后再次读取就会报错：在关闭的文件上进行IO操作
fp.readline()
```


    ---------------------------------------------------------------------------
    
    ValueError                                Traceback (most recent call last)
    
    <ipython-input-9-f83b4d936024> in <module>
          1 fp.close()
    ----> 2 fp.readline()


    ValueError: I/O operation on closed file.

## 三种读取文本文件全部数据的方法

* 1. 打开文件，利用while循环，一行行的readline


```python
fp = open("iris.txt", "r")
line = fp.readline()
while line:
    print(line) #因为是使用print打印输出，\n等转义符号都已转义后输出，不再保持原样
    line = fp.readline()
fp.close()
```

    150	4	setosa	versicolor	virginica
    
    5.1	3.5	1.4	0.2	0
    
    4.9	3	1.4	0.2	0
    
    ......
    
    5.9	3	5.1	1.8	2

* 2. 打开文件，通过for in循环来遍历文件指针来获取文件的每一行

```python
fp = open("iris.txt")
for line2 in fp: # 遍历的是文件指针，能够自动获得每一行的内容
    print(line2)
fp.close()
```

    150	4	setosa	versicolor	virginica
    
    5.1	3.5	1.4	0.2	0
    
    4.9	3	1.4	0.2	0
    
    4.7	3.2	1.3	0.2	0
    
    .......
    
    5.9	3	5.1	1.8	2

* 3. 打开文件，先读取文件的全部内容（readlines）存入一个对象中，然后通过for in循环遍历这个对象，获取每一行的内容


```python
fp = open("iris.txt")
lines = fp.readlines()
for line in lines: #遍历的是存储所有文件内容的对象
    print(line)
fp.close()
```

    150	4	setosa	versicolor	virginica
    
    5.1	3.5	1.4	0.2	0
    
    4.9	3	1.4	0.2	0
    
    .......
    
    5.9	3	5.1	1.8	2



# 读取CSV文件

## 读取CSV文件的基本函数

* 导入Pandas包（需要提前安装好，我是使用Anaconda管理这些包）

```python
import pandas as pd
```

* 采用pandas中的函数将csv表格形式文件直接读取到一个Pyhton的**DataFrame**对象中，常用的函数有read_csv和read_table函数


```python
df = pd.read_csv('iris.csv') # 结果存储在DataFrame对象中
df # 直接查看DataFrame对象中内容
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
      <th>150</th>
      <th>4</th>
      <th>setosa</th>
      <th>versicolor</th>
      <th>virginica</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>5.1</td>
      <td>3.5</td>
      <td>1.4</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>1</th>
      <td>4.9</td>
      <td>3.0</td>
      <td>1.4</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>2</th>
      <td>4.7</td>
      <td>3.2</td>
      <td>1.3</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>3</th>
      <td>4.6</td>
      <td>3.1</td>
      <td>1.5</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>4</th>
      <td>5.0</td>
      <td>3.6</td>
      <td>1.4</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>...</th>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
    </tr>
    <tr>
      <th>145</th>
      <td>6.7</td>
      <td>3.0</td>
      <td>5.2</td>
      <td>2.3</td>
      <td>2</td>
    </tr>
    <tr>
      <th>146</th>
      <td>6.3</td>
      <td>2.5</td>
      <td>5.0</td>
      <td>1.9</td>
      <td>2</td>
    </tr>
    <tr>
      <th>147</th>
      <td>6.5</td>
      <td>3.0</td>
      <td>5.2</td>
      <td>2.0</td>
      <td>2</td>
    </tr>
    <tr>
      <th>148</th>
      <td>6.2</td>
      <td>3.4</td>
      <td>5.4</td>
      <td>2.3</td>
      <td>2</td>
    </tr>
    <tr>
      <th>149</th>
      <td>5.9</td>
      <td>3.0</td>
      <td>5.1</td>
      <td>1.8</td>
      <td>2</td>
    </tr>
  </tbody>
</table>
<p>150 rows × 5 columns</p>
</div>

* 最左列为自动生成的索引
* DataFrame对象非常适合处理表格数据

* `read_table`不仅可以读取csv文件（以,分隔的表格型文件),还可以读取以其它方式分隔的表格，所以需要设置分隔符


```python
df = pd.read_table("iris.csv", sep = ',') # 设置分隔符为,，才能处理csv文件
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
      <th>150</th>
      <th>4</th>
      <th>setosa</th>
      <th>versicolor</th>
      <th>virginica</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>5.1</td>
      <td>3.5</td>
      <td>1.4</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>1</th>
      <td>4.9</td>
      <td>3.0</td>
      <td>1.4</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>2</th>
      <td>4.7</td>
      <td>3.2</td>
      <td>1.3</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>3</th>
      <td>4.6</td>
      <td>3.1</td>
      <td>1.5</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>4</th>
      <td>5.0</td>
      <td>3.6</td>
      <td>1.4</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>...</th>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
    </tr>
    <tr>
      <th>145</th>
      <td>6.7</td>
      <td>3.0</td>
      <td>5.2</td>
      <td>2.3</td>
      <td>2</td>
    </tr>
    <tr>
      <th>146</th>
      <td>6.3</td>
      <td>2.5</td>
      <td>5.0</td>
      <td>1.9</td>
      <td>2</td>
    </tr>
    <tr>
      <th>147</th>
      <td>6.5</td>
      <td>3.0</td>
      <td>5.2</td>
      <td>2.0</td>
      <td>2</td>
    </tr>
    <tr>
      <th>148</th>
      <td>6.2</td>
      <td>3.4</td>
      <td>5.4</td>
      <td>2.3</td>
      <td>2</td>
    </tr>
    <tr>
      <th>149</th>
      <td>5.9</td>
      <td>3.0</td>
      <td>5.1</td>
      <td>1.8</td>
      <td>2</td>
    </tr>
  </tbody>
</table>
<p>150 rows × 5 columns</p>
</div>



## 逐块的读取文件（一次读取其中几行):

* 逐块读取文件的两种方法
  * 设置nrows参数
  * 或者chunksize参数,但是将文件按块存储在一个TextParser对象中
* `read_table`设置nrows参数


```python
df5 = pd.read_table('iris.csv', sep = ',', nrows=5)
df5
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
      <th>150</th>
      <th>4</th>
      <th>setosa</th>
      <th>versicolor</th>
      <th>virginica</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>5.1</td>
      <td>3.5</td>
      <td>1.4</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>1</th>
      <td>4.9</td>
      <td>3.0</td>
      <td>1.4</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>2</th>
      <td>4.7</td>
      <td>3.2</td>
      <td>1.3</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>3</th>
      <td>4.6</td>
      <td>3.1</td>
      <td>1.5</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>4</th>
      <td>5.0</td>
      <td>3.6</td>
      <td>1.4</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
  </tbody>
</table>
</div>

* `read-_csv`设置nrows参数


```python
df5 = pd.read_csv("iris.csv", nrows=5)
df5
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
      <th>150</th>
      <th>4</th>
      <th>setosa</th>
      <th>versicolor</th>
      <th>virginica</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>5.1</td>
      <td>3.5</td>
      <td>1.4</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>1</th>
      <td>4.9</td>
      <td>3.0</td>
      <td>1.4</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>2</th>
      <td>4.7</td>
      <td>3.2</td>
      <td>1.3</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>3</th>
      <td>4.6</td>
      <td>3.1</td>
      <td>1.5</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
    <tr>
      <th>4</th>
      <td>5.0</td>
      <td>3.6</td>
      <td>1.4</td>
      <td>0.2</td>
      <td>0</td>
    </tr>
  </tbody>
</table>
</div>

* `read_csv`函数设置chunksize参数


```python
chunk = pd.read_csv("iris.csv", chunksize=5)
chunk
```




    <pandas.io.parsers.TextFileReader at 0x7f824f359cd0>



* 使用设置chuncksize参数生成的textparser对象对文件进行逐块迭代


```python
tot = 0
for piece in chunk:
    tot = tot + 1
    print(piece)
    print("-------")
print(tot) # 总共有150行，每5行一块有30块，存储在这些块存储在chunk对象中
```

       150    4  setosa  versicolor  virginica
    0  5.1  3.5     1.4         0.2          0
    1  4.9  3.0     1.4         0.2          0
    2  4.7  3.2     1.3         0.2          0
    3  4.6  3.1     1.5         0.2          0
    4  5.0  3.6     1.4         0.2          0
    -------
       150    4  setosa  versicolor  virginica
    5  5.4  3.9     1.7         0.4          0
    6  4.6  3.4     1.4         0.3          0
    7  5.0  3.4     1.5         0.2          0
    8  4.4  2.9     1.4         0.2          0
    9  4.9  3.1     1.5         0.1          0
    -------
    
    .......
    
         150    4  setosa  versicolor  virginica
    145  6.7  3.0     5.2         2.3          2
    146  6.3  2.5     5.0         1.9          2
    147  6.5  3.0     5.2         2.0          2
    148  6.2  3.4     5.4         2.3          2
    149  5.9  3.0     5.1         1.8          2
    -------
    30

# 写入文本文件

* 创建文件对象，使用w模式写入,w模式是覆盖式写入，一旦用w模式打开文件，文件中之前的内容全都删除了，若文件不存在则会先创建再写入。


```python
myData = ['Date', 'Time'] #创建测试的数据
```


```python
myfile = open("test.txt",'w') #用w模式打开文件
for line in myData:
    myfile.write(line+'\n')
myfile.close()
```
* 以r模式打开文件，检查写入文件是否成功

```python
myfile = open("test.txt", 'r')
print(myfile.read())
```


    Date
    Time

* 使用a模式可以追加写


```python
print("写之前输出：")
myfile1 = open("test.txt", 'r')
print(myfile1.read())

myfile2 = open("test.txt", 'a')
myfile2.write("a")

print("写之后输出：")
myfile3 = open("test.txt", 'r')
print(myfile3.read())
```

    写之前输出：
    Date
    Time
    
    写之后输出：
    Date
    Time
    a


