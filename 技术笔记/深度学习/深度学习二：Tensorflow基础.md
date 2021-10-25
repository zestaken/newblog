---
title: 深度学习二：Tensorflow基础
date: 2021-10-25 15:47:19
tags: [深度学习, Tensorflow]
categories: 技术笔记
---
# Tensorflow基础数据操作

* 为了能够完成各种操作，我们需要某种方法来存储和操作数据。一般来说，我们需要做两件重要的事情：
    1. 获取数据；
    2. 在将数据读入计算机后对其进行处理。如果没有某种方法来存储数据，那么获取数据是没有意义的。
* 数据存储对象：$n$维数组，也称为*张量*（tensor）。
* 无论使用哪个深度学习框架，它的*张量类*（在PyTorch和TensorFlow中为`Tensor`）都与Numpy的`ndarray`类似，但又比Numpy的`ndarray`多一些重要功能：
    1. 深度学习框架很好地支持GPU加速计算，而NumPy仅支持CPU计算；
    2. 张量类支持自动微分。这些功能使得张量类更适合深度学习。

# Tensorflow数组对象

* 首先，我们导入`tensorflow`。由于名称有点长，我们经常使用短别名`tf`导入它。


```python
import tensorflow as tf
```

* **张量（tensor）**表示由一个数值组成的数组，这个数组可能有多个维度。具有一个轴的张量对应数学上的*向量*（vector）。具有两个轴的张量对应数学上的*矩阵*（matrix）。具有两个轴以上的张量没有特殊的数学名称。
* 可以使用`arange`创建一个行向量`x`。这个行向量包含从0开始的前12个整数，它们被默认创建为浮点数。张量中的每个值都称为张量的*元素*（element）。例如，张量`x`中有12个元素。
    * 除非额外指定，否则新的张量将存储在内存中，并采用基于CPU的计算。


```python
x = tf.range(12)
x
```




    <tf.Tensor: shape=(12,), dtype=int32, numpy=array([ 0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11], dtype=int32)>



* `shape`属性可以通过张量的`shape`属性来访问张量的*形状*（沿每个轴的长度）。


```python
x.shape
```




    TensorShape([12])



* 如果只想知道张量中元素的总数，即形状的所有元素乘积，可以检查它的大小（size）。


```python
tf.size(x)
```




    <tf.Tensor: shape=(), dtype=int32, numpy=12>



* 要改变一个张量的形状而不改变元素数量和元素值，可以调用`reshape`函数。例如，可以把张量`x`从形状为（12,）的行向量转换为形状为（3,4）的矩阵。
    * 这个新的张量包含与转换前相同的值，但是它被看成一个3行4列的矩阵。要重点说明一下，虽然张量的形状发生了改变，但其元素值并没有变。注意，通过改变张量的形状，张量的大小不会改变。


```python
X = tf.reshape(x, (3, 4))
X
```




    <tf.Tensor: shape=(3, 4), dtype=int32, numpy=
    array([[ 0,  1,  2,  3],
           [ 4,  5,  6,  7],
           [ 8,  9, 10, 11]], dtype=int32)>



* 不需要通过手动指定每个维度来改变形状。也就是说，如果我们的目标形状是（高度,宽度），那么在知道宽度后，高度应当会隐式得出，我们不必自己做除法。在上面的例子中，为了获得一个3行的矩阵，我们手动指定了它有3行和4列。
    * 幸运的是，张量在给出其他部分后可以自动计算出一个维度。我们可以通过在希望张量自动推断的维度放置`-1`来调用此功能。在上面的例子中，我们可以用`x.reshape(-1,4)`或`x.reshape(3,-1)`来取代`x.reshape(3,4)`。
* 有时，我们希望[**使用全0、全1、其他常量或者从特定分布中随机采样的数字**]来初始化矩阵。我们可以创建一个形状为（2,3,4）的张量，其中所有元素都设置为0。代码如下：


```python
tf.zeros((2, 3, 4))
```




    <tf.Tensor: shape=(2, 3, 4), dtype=float32, numpy=
    array([[[0., 0., 0., 0.],
            [0., 0., 0., 0.],
            [0., 0., 0., 0.]],
    
           [[0., 0., 0., 0.],
            [0., 0., 0., 0.],
            [0., 0., 0., 0.]]], dtype=float32)>



* 同样，我们可以创建一个形状为`(2,3,4)`的张量，其中所有元素都设置为1。代码如下：


```python
tf.ones((2, 3, 4))
```




    <tf.Tensor: shape=(2, 3, 4), dtype=float32, numpy=
    array([[[1., 1., 1., 1.],
            [1., 1., 1., 1.],
            [1., 1., 1., 1.]],
    
           [[1., 1., 1., 1.],
            [1., 1., 1., 1.],
            [1., 1., 1., 1.]]], dtype=float32)>



* 有时我们想通过**从某个特定的概率分布中随机采样来得到张量中每个元素的值**。例如，当我们构造数组来作为神经网络中的参数时，我们通常会随机初始化参数的值。以下代码创建一个形状为（3,4）的张量。其中的每个元素都从均值为0、标准差为1的标准高斯（正态）分布中随机采样。


```python
tf.random.normal(shape=[3, 4])
```




    <tf.Tensor: shape=(3, 4), dtype=float32, numpy=
    array([[ 1.3451171 ,  1.8505315 , -0.36672395, -0.5327307 ],
           [-1.9183167 , -1.1994293 ,  0.37904397,  0.8061889 ],
           [-0.3106889 ,  0.22559305, -0.96131563, -0.30276218]],
          dtype=float32)>



* 我们还可以[**通过提供包含数值的Python列表（或嵌套列表）来为所需张量中的每个元素赋予确定值**]。在这里，最外层的列表对应于轴0，内层的列表对应于轴1。


```python
tf.constant([[2, 1, 4, 3], [1, 2, 3, 4], [4, 3, 2, 1]])
```




    <tf.Tensor: shape=(3, 4), dtype=int32, numpy=
    array([[2, 1, 4, 3],
           [1, 2, 3, 4],
           [4, 3, 2, 1]], dtype=int32)>



# Tensorflow的数组基本运算
* 对数组一些最简单且最有用的操作是*按元素*（elementwise）操作。它们将标准标量运算符应用于数组的每个元素。
* 对于将两个数组作为输入的函数，按元素运算将二元运算符应用于两个数组中的*每对位置对应*的元素。我们可以基于任何从标量到标量的函数来创建按元素函数。
* 对于任意具有相同形状的张量，[**常见的标准算术运算符（`+`、`-`、`*`、`/`和`**`）都可以被升级为按元素运算**]。我们可以在同一形状的任意两个张量上调用按元素操作。


```python
x = tf.constant([1.0, 2, 4, 8])
y = tf.constant([2.0, 2, 2, 2])
x + y, x - y, x * y, x / y, x ** y  # **运算符是求幂运算
```




    (<tf.Tensor: shape=(4,), dtype=float32, numpy=array([ 3.,  4.,  6., 10.], dtype=float32)>,
     <tf.Tensor: shape=(4,), dtype=float32, numpy=array([-1.,  0.,  2.,  6.], dtype=float32)>,
     <tf.Tensor: shape=(4,), dtype=float32, numpy=array([ 2.,  4.,  8., 16.], dtype=float32)>,
     <tf.Tensor: shape=(4,), dtype=float32, numpy=array([0.5, 1. , 2. , 4. ], dtype=float32)>,
     <tf.Tensor: shape=(4,), dtype=float32, numpy=array([ 1.,  4., 16., 64.], dtype=float32)>)



* 可以(**按按元素方式应用更多的计算**)，包括像求指数幂这样的一元运算符。


```python
tf.exp(x)
```




    <tf.Tensor: shape=(4,), dtype=float32, numpy=
    array([2.7182817e+00, 7.3890562e+00, 5.4598148e+01, 2.9809580e+03],
          dtype=float32)>



* 除了按元素计算外，我们还可以执行线性代数运算，包括向量点积和矩阵乘法。
* [**我们也可以把多个张量*连结*（concatenate）在一起**]，把它们端对端地叠起来形成一个更大的张量。我们只需要提供张量列表，并给出沿哪个轴连结。
* 下面的例子分别演示了当我们沿行（轴-0，形状的第一个元素）和按列（轴-1，形状的第二个元素）连结两个矩阵时会发生什么情况。我们可以看到，第一个输出张量的轴-0长度（$6$）是两个输入张量轴-0长度的总和（$3 + 3$）；第二个输出张量的轴-1长度（$8$）是两个输入张量轴-1长度的总和（$4 + 4$）。


```python
X = tf.reshape(tf.range(12, dtype=tf.float32), (3, 4))
Y = tf.constant([[2.0, 1, 4, 3], [1, 2, 3, 4], [4, 3, 2, 1]])
tf.concat([X, Y], axis=0), tf.concat([X, Y], axis=1)
```




    (<tf.Tensor: shape=(6, 4), dtype=float32, numpy=
     array([[ 0.,  1.,  2.,  3.],
            [ 4.,  5.,  6.,  7.],
            [ 8.,  9., 10., 11.],
            [ 2.,  1.,  4.,  3.],
            [ 1.,  2.,  3.,  4.],
            [ 4.,  3.,  2.,  1.]], dtype=float32)>,
     <tf.Tensor: shape=(3, 8), dtype=float32, numpy=
     array([[ 0.,  1.,  2.,  3.,  2.,  1.,  4.,  3.],
            [ 4.,  5.,  6.,  7.,  1.,  2.,  3.,  4.],
            [ 8.,  9., 10., 11.,  4.,  3.,  2.,  1.]], dtype=float32)>)



* [**通过*逻辑运算符*构建二元张量**]。以`X == Y`为例子。对于每个位置，如果`X`和`Y`在该位置相等，则新张量中相应项的值为1，这意味着逻辑语句`X == Y`在该位置处为真，否则该位置为0。


```python
X == Y
```




    <tf.Tensor: shape=(3, 4), dtype=bool, numpy=
    array([[False,  True, False,  True],
           [False, False, False, False],
           [False, False, False, False]])>



* [**对张量中的所有元素进行求和会产生一个只有一个元素的张量。**]


```python
tf.reduce_sum(X)
```




    <tf.Tensor: shape=(), dtype=float32, numpy=66.0>



## 广播机制

* 在上面的部分中，我们看到了如何在相同形状的两个张量上执行按元素操作。在某些情况下，[**即使形状不同，我们仍然可以通过调用*广播机制*（broadcasting mechanism）来执行按元素操作**]。
* 这是Tensorflow的自动机制，这种机制的工作方式如下：首先，通过适当复制元素来扩展一个或两个数组，以便在转换之后，两个张量具有相同的形状。其次，对生成的数组执行按元素操作。在大多数情况下，我们将沿着数组中长度为1的轴进行广播，如下例子：


```python
a = tf.reshape(tf.range(3), (3, 1))
b = tf.reshape(tf.range(2), (1, 2))
a, b
```




    (<tf.Tensor: shape=(3, 1), dtype=int32, numpy=
     array([[0],
            [1],
            [2]], dtype=int32)>,
     <tf.Tensor: shape=(1, 2), dtype=int32, numpy=array([[0, 1]], dtype=int32)>)



* 由于`a`和`b`分别是$3\times1$和$1\times2$矩阵，如果我们让它们相加，它们的形状不匹配。我们将两个矩阵*广播*为一个更大的$3\times2$矩阵，如下所示：矩阵`a`将复制列，矩阵`b`将复制行，然后再按元素相加。


```python
a + b
```




    <tf.Tensor: shape=(3, 2), dtype=int32, numpy=
    array([[0, 1],
           [1, 2],
           [2, 3]], dtype=int32)>



## 索引和切片

* 就像在任何其他Python数组中一样，张量中的元素可以通过索引访问。与任何Python数组一样：第一个元素的索引是0；可以指定范围以包含第一个元素和最后一个之前的元素。与标准Python列表一样，我们可以通过*使用负索引根据元素到列表尾部的相对位置访问元素*。
* 因此，我们[可以用`[-1]`选择最后一个元素，可以用`[1:3]`选择第二个和第三个元素（从1， 2 ,索引3是右开边界）]，如下所示：


```python
X[-1], X[1:3]
```




    (<tf.Tensor: shape=(4,), dtype=float32, numpy=array([ 8.,  9., 10., 11.], dtype=float32)>,
     <tf.Tensor: shape=(2, 4), dtype=float32, numpy=
     array([[ 4.,  5.,  6.,  7.],
            [ 8.,  9., 10., 11.]], dtype=float32)>)



* TensorFlow中的`Tensors`对象是不可变的，也不能被赋值。TensorFlow中的`Variables`是支持赋值的可变容器。请记住，TensorFlow中的梯度不会通过`Variable`反向传播。
* 除了为整个`Variable`分配一个值之外，我们还可以通过索引来写入`Variable`的元素。


```python
X_var = tf.Variable(X)
X_var[1, 2].assign(-100) # 将索引为(1, 2)的元素修改为-100
X_var
```




    <tf.Variable 'Variable:0' shape=(3, 4) dtype=float32, numpy=
    array([[   0.,    1.,    2.,    3.],
           [   4.,    5., -100.,    7.],
           [   8.,    9.,   10.,   11.]], dtype=float32)>



* 如果我们想[**为多个元素赋值相同的值，我们只需要索引所有元素，然后为它们赋值。**]。例如，`[0:2, :]`访问第1行和第2行，其中“:”代表沿轴1（列）的所有元素。虽然我们讨论的是矩阵的索引，但这也适用于向量和超过2个维度的张量。


```python
X_var = tf.Variable(X)
X_var[0:2, :].assign(tf.ones(X_var[0:2,:].shape, dtype = tf.float32) * 12) # 将第一行和第二行元素全部修改为12
X_var
```




    <tf.Variable 'Variable:0' shape=(3, 4) dtype=float32, numpy=
    array([[12., 12., 12., 12.],
           [12., 12., 12., 12.],
           [ 8.,  9., 10., 11.]], dtype=float32)>



## 节省内存

* [**运行一些操作可能会导致为新结果分配内存**]。例如，如果我们用`Y = X + Y`，我们将取消引用`Y`指向的张量，而是指向新分配的内存处的张量。
* 在下面的例子中，我们用Python的`id()`函数演示了这一点，它给我们提供了内存中引用对象的确切地址。运行`Y = Y + X`后，我们会发现`id(Y)`指向另一个位置。这是因为Python首先计算`Y + X`，为结果分配新的内存，然后使`Y`指向内存中的这个新位置。


```python
before = id(Y)
Y = Y + X
id(Y) == before
```




    False



* 在机器学习中，我们可能有数百兆的参数，并且在一秒内多次更新所有参数。通常情况下，我们希望原地执行这些更新。其次，我们可能通过多个变量指向相同参数。如果我们不原地更新，其他引用仍然会指向旧的内存位置，这样我们的某些代码可能会无意中引用旧的参数。

* `Variables`是TensorFlow中的可变容器。它们提供了一种存储模型参数的方法。我们可以通过`assign`将一个操作的结果分配给一个`Variable`。为了说明这一点，我们创建了一个与另一个张量`Y`相同的形状的`Z`，使用`zeros_like`来分配一个全$0$的块。


```python
Z = tf.Variable(tf.zeros_like(Y))
print('id(Z):', id(Z))
Z.assign(X + Y)
print('id(Z):', id(Z))
```

    id(Z): 140470903098192
    id(Z): 140470903098192


* 即使你将状态持久存储在`Variable`中，你也可能希望避免为不是模型参数的张量过度分配内存，从而进一步减少内存使用量。由于TensorFlow的`Tensors`是不可变的，而且梯度不会通过`Variable`流动，因此TensorFlow没有提供一种明确的方式来原地运行单个操作。
    * 但是，TensorFlow提供了`tf.function`修饰符，将计算封装在TensorFlow图中，该图在运行前经过编译和优化。这允许TensorFlow删除未使用的值，并复用先前分配的且不再需要的值。这样可以最大限度地减少TensorFlow计算的内存开销。


```python
@tf.function
def computation(X, Y):
    Z = tf.zeros_like(Y)  # 这个未使用的值将被删除
    A = X + Y  # 当不再需要时，分配将被复用
    B = A + Y
    C = B + Y
    return C + Y

computation(X, Y)
```




    <tf.Tensor: shape=(3, 4), dtype=float32, numpy=
    array([[ 8.,  9., 26., 27.],
           [24., 33., 42., 51.],
           [56., 57., 58., 59.]], dtype=float32)>



## 与其他 Python 对象转化

* `tensor`对象[**转换为NumPy张量**]很容易，反之也很容易。
    * 转换后的结果不共享内存。这个小的不便实际上是非常重要的：当你在CPU或GPU上执行操作的时候，如果Python的NumPy包也希望使用相同的内存块执行其他操作，你不希望停下计算来等它。


```python
A = X.numpy()
B = tf.constant(A)
type(A), type(B)
```




    (numpy.ndarray, tensorflow.python.framework.ops.EagerTensor)



* 要(**将大小为1的张量转换为Python标量**)，我们可以调用`item`函数或Python的内置函数。


```python
a = tf.constant([3.5]).numpy()
a, a.item(), float(a), int(a)
```




    (array([3.5], dtype=float32), 3.5, 3.5, 3)



* 将Pandas对象转化为Tensorflow的tensor张量对象。


```python
import pandas as pd

inputs = pd.DataFrame(range(5))
inputs
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
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>0</td>
    </tr>
    <tr>
      <th>1</th>
      <td>1</td>
    </tr>
    <tr>
      <th>2</th>
      <td>2</td>
    </tr>
    <tr>
      <th>3</th>
      <td>3</td>
    </tr>
    <tr>
      <th>4</th>
      <td>4</td>
    </tr>
  </tbody>
</table>
</div>




```python
tensor = tf.constant(inputs.values)
tensor
```




    <tf.Tensor: shape=(5, 1), dtype=int64, numpy=
    array([[0],
           [1],
           [2],
           [3],
           [4]])>


