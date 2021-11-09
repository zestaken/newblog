---
title: 深度学习五——Softmax回归
date: 2021-11-09 15:39:19
tags: [深度学习]
categories: 技术笔记
---
# softmax回归

* 回归可以用于预测*多少*的问题。比如预测房屋被售出价格，或者棒球队可能获得的胜利数，又或者患者住院的天数。
* 事实上，我们经常对*分类*感兴趣：不是问“多少”，而是问“哪一个”：比如某图像描绘的是驴、狗、猫、还是鸡？
* 通常，机器学习实践者用*分类*这个词来描述两个有微妙差别的问题：（1）我们只对样本的硬性类别感兴趣，即*属于*哪个类别；（2）我们希望得到软性类别，即得到属于每个类别的*概率*。这两者的界限往往很模糊。其中的一个原因是，即使我们只关心硬类别，我们仍然使用软类别的模型。

## 分类问题

* 让我们从一个图像分类问题开始简单尝试一下。每次输入是一个$2\times2$的灰度图像。我们可以用一个标量表示每个像素值，每个图像对应四个特征$x_1, x_2, x_3, x_4$（4个输入）。此外，让我们假设每个图像属于类别“猫”，“鸡”和“狗”中的一个（3个输出）。
* *独热编码*（one-hot encoding）：输出结果并不是数字，需要一种表示分类数据的简单方法，独热编码正是起到这个作用。独热编码是一个向量，它的分量和类别一样多。**类别对应的分量设置为1，其他所有分量设置为0。**在我们的例子中，标签$y$将是一个三维向量，其中$(1, 0, 0)$对应于“猫”、$(0, 1, 0)$对应于“鸡”、$(0, 0, 1)$对应于“狗”：
    $$y \in \{(1, 0, 0), (0, 1, 0), (0, 0, 1)\}.$$

## 网络结构

* 输出：为了估计所有可能类别的条件概率，我们需要一个有多个输出的模型，*每个类别对应一个输出*。
* 仿射函数：为了解决线性模型的分类问题，我们需要和输出一样多的仿射函数（affine function），每一个输出都对应一个自己的仿射函数，不同仿射函数具有不同的参数值。而不是简单线性回归只有一个从特征值到结果（标签）值的函数映射。
* 在我们的例子中，由于我们有4个特征和3个可能的输出类别，我们将需要12个标量来表示权重（带下标的$w$），3个标量来表示偏置（带下标的$b$）。下面我们为每个输入计算三个*未归一化的预测*（logit）：$o_1$、$o_2$和$o_3$。

    $$
    \begin{aligned}
    o_1 &= x_1 w_{11} + x_2 w_{12} + x_3 w_{13} + x_4 w_{14} + b_1,\\
    o_2 &= x_1 w_{21} + x_2 w_{22} + x_3 w_{23} + x_4 w_{24} + b_2,\\
    o_3 &= x_1 w_{31} + x_2 w_{32} + x_3 w_{33} + x_4 w_{34} + b_3.
    \end{aligned}
    $$
* 我们可以用神经网络图来描述这个计算过程。与线性回归一样，softmax回归也是一个单层神经网络。由于计算每个输出$o_1$、$o_2$和$o_3$取决于所有输入$x_1$、$x_2$、$x_3$和$x_4$，所以softmax回归的输出层也是全连接层。（全连接层的每一个结点都与上一层的所有结点相连，用来把前边提取到的特征综合起来。只要每一个输出都与所有特征值相关联就是全连接层)
    ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211106104545.png)
* 通过向量形式表达为$\mathbf{o} = \mathbf{W} \mathbf{x} + \mathbf{b}$，这是一种更适合数学和编写代码的形式。我们已经将所有*权重放到一个$3 \times 4$矩阵中*。对于给定数据样本的特征$\mathbf{x}$，我们的输出是由权重与输入特征进行*矩阵-向量乘法*再加上偏置$\mathbf{b}$得到的。

## softmax运算

* 我们可以将将模型的输出视作为概率。我们将优化参数以最大化观测数据的概率。为了得到预测结果，我们将设置一个阈值，如选择具有最大概率的标签（即所有仿射函数的输出中具有最大概率的那一个对应的类别就是这组特征对应的类别)。
* 例如，在我们的*示例*中，我们让模型的输出$\hat{y}_j$可以视为属于类$j$的概率。然后我们可以选择具有最大输出值的类别$\operatorname*{argmax}_j y_j$作为我们的预测。例如，如果$\hat{y}_1$、$\hat{y}_2$和$\hat{y}_3$分别为0.1、0.8和0.1，那么我们预测的类别是2，在我们的例子中代表“鸡”。
* 将未归一化（输出总和不为1)的预测$o$直接视为概率时存在一些*问题*：一方面，没有限制这些数字的总和为1。另一方面，根据输入的不同，它们可以为负值。这些违反了概率基本公理。
* 要将输出视为概率，我们必须保证在任何数据上的输出都是*非负的且总和为1*。
* **校准（calibration）**：我们需要一个*训练目标*，来鼓励模型精准地估计概率。例如，在分类器输出0.5的所有样本中，我们希望这些样本有一半实际上属于预测的类。这个属性叫做*校准*（calibration）。
* **softmax函数**正是这样做的：为了将未归一化的预测变换为非负并且总和为1，同时要求模型保持可导。
    * 我们首先对每个未归一化的预测*求幂*，这样可以确保输出非负。
    * 为了确保最终输出的总和为1，我们再对每个*求幂后的结果除以它们的总和*。如下式：

    $$\hat{\mathbf{y}} = \mathrm{softmax}(\mathbf{o})\quad \text{其中}\quad \hat{y}_j = \frac{\exp(o_j)}{\sum_k \exp(o_k)}$$

* 容易看出对于所有的$j$总有$0 \leq \hat{y}_j \leq 1$。因此，$\hat{\mathbf{y}}$可以视为一个正确的概率分布。softmax运算不会改变未归一化的预测$\mathbf{o}$之间的顺序，只会确定分配给每个类别的概率。因此，在预测过程中，我们仍然可以用下式来选择最有可能的类别（即经过softmax函数运算后最大的概率对应的类别就是这组特征值对应的类别)。
    $$
    \operatorname*{argmax}_j \hat y_j = \operatorname*{argmax}_j o_j.
    $$
* 尽管softmax是一个非线性函数，但softmax回归的输出仍然由输入特征的仿射变换决定。因此，softmax回归是一个线性模型。如下图所示，softmax所做的工作就是在模型函数原本的输出上做了一些不改变结果的变形，方便后期对数据的分析。
    ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211106110548.png)

## 小批量样本的矢量化

* 为了提高计算效率并且充分利用GPU，我们通常会针对小批量数据执行矢量计算。假设我们读取了一个批量的样本$\mathbf{X}$，其中特征维度（输入数量）为$d$，批量大小为$n$。此外，假设我们在输出中有$q$个类别。那么小批量特征为$\mathbf{X} \in \mathbb{R}^{n \times d}$，权重为$\mathbf{W} \in \mathbb{R}^{d \times q}$，偏置为$\mathbf{b} \in \mathbb{R}^{1\times q}$。softmax回归的矢量计算表达式为：

    $$ \begin{aligned} \mathbf{O} &= \mathbf{X} \mathbf{W} + \mathbf{b}, \\ \hat{\mathbf{Y}} & = \mathrm{softmax}(\mathbf{O}). \end{aligned} $$
* 相对于一次处理一个样本，小批量样本的矢量化加快了$\mathbf{X}和\mathbf{W}$的*矩阵-向量乘法*。由于$\mathbf{X}$中的每一行代表一个数据样本，所以softmax运算可以*按行*（rowwise）执行：对于$\mathbf{O}$的每一行，我们先对所有项进行幂运算，然后通过求和对它们进行标准化。

## 损失函数

* 我们同样需要一个损失函数来度量预测概率的效果。此处我们将依赖最大似然估计。

### 对数似然

* softmax函数给出了一个向量$\hat{\mathbf{y}}$，我们可以将其视为给定任意输入$\mathbf{x}$的*每个类的估计条件概率*。例如，$\hat{y}_1$=$P(y=\text{猫} \mid \mathbf{x})$。假设整个数据集$\{\mathbf{X}, \mathbf{Y}\}$具有$n$个样本，其中索引$i$的样本由特征向量$\mathbf{x}^{(i)}$和独热标签向量$\mathbf{y}^{(i)}$组成。我们可以将估计值与实际值进行比较：

$$
P(\mathbf{Y} \mid \mathbf{X}) = \prod_{i=1}^n P(\mathbf{y}^{(i)} \mid \mathbf{x}^{(i)}).
$$

* 根据最大似然估计，我们最大化$P(\mathbf{Y} \mid \mathbf{X})$，相当于最小化负对数似然（使用对数变连乘为连加）：

$$
-\log P(\mathbf{Y} \mid \mathbf{X}) = \sum_{i=1}^n -\log P(\mathbf{y}^{(i)} \mid \mathbf{x}^{(i)})
= \sum_{i=1}^n l(\mathbf{y}^{(i)}, \hat{\mathbf{y}}^{(i)}),
$$

* 其中，对于任何标签$\mathbf{y}$和模型预测$\hat{\mathbf{y}}$，损失函数为（怎么推导出来的？）：

$$ l(\mathbf{y}, \hat{\mathbf{y}}) = - \sum_{j=1}^q y_j \log \hat{y}_j. $$

* 上面的损失函数通常被称为*交叉熵损失*（cross-entropy loss）。由于$\mathbf{y}$是一个长度为$q$的独热编码向量，所以除了一个项以外的所有项$j$都消失了。由于所有$\hat{y}_j$都是预测的概率，所以它们的对数永远不会大于$0$。

### softmax及其导数

* 将 softmax函数代入损失函数中，我们得到：

$$
\begin{aligned}
l(\mathbf{y}, \hat{\mathbf{y}}) &=  - \sum_{j=1}^q y_j \log \frac{\exp(o_j)}{\sum_{k=1}^q \exp(o_k)} \\
&= \sum_{j=1}^q y_j \log \sum_{k=1}^q \exp(o_k) - \sum_{j=1}^q y_j o_j\\
&= \log \sum_{k=1}^q \exp(o_k) - \sum_{j=1}^q y_j o_j.
\end{aligned}
$$

为了更好地理解发生了什么，考虑相对于任何未归一化的预测$o_j$的导数。我们得到：

    $$
    \partial_{o_j} l(\mathbf{y}, \hat{\mathbf{y}}) = \frac{\exp(o_j)}{\sum_{k=1}^q \exp(o_k)} - y_j = \mathrm{softmax}(\mathbf{o})_j - y_j.
    $$

* 换句话说，导数是我们模型分配的概率（由softmax得到）与实际发生的情况（由独热标签向量表示）之间的差异。从这个意义上讲，与我们在回归中看到的非常相似，其中梯度是观测值$y$和估计值$\hat{y}$之间的差异。


## 模型预测和评估

* 在训练softmax回归模型后，给出任何样本特征，我们可以预测每个输出类别的概率。通常我们使用预测概率最高的类别作为输出类别。如果预测与实际类别（标签）一致，则预测是正确的。在接下来的实验中，我们将使用*准确率*来评估模型的性能。准确率等于正确预测数与预测的总数之间的比率。

# 图像分类数据集

* 目前广泛使用的图像分类数据集之一是MNIST数据集。虽然它是很不错的基准数据集，但按今天的标准，即使是简单的模型也能达到95%以上的分类准确率，因此不适合区分强模型和弱模型。如今，MNIST更像是一个健全检查，而不是一个基准。
* 为了提高难度，我们将在接下来的章节中讨论在2017年发布的性质相似但相对复杂的*Fashion-MNIST数据集*。

## 读取数据集

* 我们可以[**通过框架中的内置函数将Fashion-MNIST数据集下载并读取到内存中**]。


```python
import tensorflow as tf

mnist_train, mnist_test = tf.keras.datasets.fashion_mnist.load_data()
```

    Downloading data from https://storage.googleapis.com/tensorflow/tf-keras-datasets/train-labels-idx1-ubyte.gz
    32768/29515 [=================================] - 0s 3us/step
    40960/29515 [=========================================] - 0s 2us/step
    Downloading data from https://storage.googleapis.com/tensorflow/tf-keras-datasets/train-images-idx3-ubyte.gz
    26427392/26421880 [==============================] - 11s 0us/step
    26435584/26421880 [==============================] - 11s 0us/step
    Downloading data from https://storage.googleapis.com/tensorflow/tf-keras-datasets/t10k-labels-idx1-ubyte.gz
    16384/5148 [===============================================================================================] - 0s 0us/step
    Downloading data from https://storage.googleapis.com/tensorflow/tf-keras-datasets/t10k-images-idx3-ubyte.gz
    4423680/4422102 [==============================] - 2s 0us/step
    4431872/4422102 [==============================] - 2s 0us/step


* Fashion-MNIST由10个类别的图像组成，每个类别由训练数据集中的6000张图像和测试数据集中的1000张图像组成。
* *测试数据集*（test dataset）不会用于训练，只用于评估模型性能。训练集和测试集分别包含60000和10000张图像。


```python
len(mnist_train[0]), len(mnist_test[0])
```




    (60000, 10000)



* 每个输入图像的高度和宽度均为28像素。数据集由灰度图像组成，其通道数为1。为了简洁起见，在这本书中，我们将高度$h$像素，宽度$w$像素图像的形状记为$h \times w$或（$h$,$w$）。


```python
mnist_train[0][0].shape
```




    (28, 28)



* Fashion-MNIST中包含的10个类别分别为t-shirt（T恤）、trouser（裤子）、pullover（套衫）、dress（连衣裙）、coat（外套）、sandal（凉鞋）、shirt（衬衫）、sneaker（运动鞋）、bag（包）和ankle boot（短靴）。以下函数用于在数字标签索引及其文本名称之间进行转换。


```python
def get_fashion_mnist_labels(labels):  # 根据数字代码获取对应的名字
    """返回Fashion-MNIST数据集的文本标签。"""
    text_labels = ['t-shirt', 'trouser', 'pullover', 'dress', 'coat',
                   'sandal', 'shirt', 'sneaker', 'bag', 'ankle boot']
    return [text_labels[int(i)] for i in labels]
```

我们现在可以创建一个函数来可视化这些样本。



```python
import matplotlib.pyplot as plt 

def show_images(imgs, num_rows, num_cols, titles=None, scale=3):  # scale设置图像显示的大小 
    """绘制图像列表。"""
    figsize = (num_cols * scale, num_rows * scale)
    _, axes = plt.subplots(num_rows, num_cols, figsize=figsize)
    axes = axes.flatten()
    for i, (ax, img) in enumerate(zip(axes, imgs)):
        ax.imshow(img.numpy())
        ax.axes.get_xaxis().set_visible(False)
        ax.axes.get_yaxis().set_visible(False)
        if titles:
            ax.set_title(titles[i]) # 从title参数数组中取出对应图像的名称
    return axes
```

* 以下是训练数据集中前[**几个样本的图像及其相应的标签**]（文本形式）。


```python
X = tf.constant(mnist_train[0][:18])
y = tf.constant(mnist_train[1][:18]) # 取出图像对应的名称代号
show_images(X, 2, 9, titles=get_fashion_mnist_labels(y))
```




    array([<AxesSubplot:title={'center':'ankle boot'}>,
           <AxesSubplot:title={'center':'t-shirt'}>,
           <AxesSubplot:title={'center':'t-shirt'}>,
           <AxesSubplot:title={'center':'dress'}>,
           <AxesSubplot:title={'center':'t-shirt'}>,
           <AxesSubplot:title={'center':'pullover'}>,
           <AxesSubplot:title={'center':'sneaker'}>,
           <AxesSubplot:title={'center':'pullover'}>,
           <AxesSubplot:title={'center':'sandal'}>,
           <AxesSubplot:title={'center':'sandal'}>,
           <AxesSubplot:title={'center':'t-shirt'}>,
           <AxesSubplot:title={'center':'ankle boot'}>,
           <AxesSubplot:title={'center':'sandal'}>,
           <AxesSubplot:title={'center':'sandal'}>,
           <AxesSubplot:title={'center':'sneaker'}>,
           <AxesSubplot:title={'center':'ankle boot'}>,
           <AxesSubplot:title={'center':'trouser'}>,
           <AxesSubplot:title={'center':'t-shirt'}>], dtype=object)


  ![output_12_1](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/output_12_1.png)
    


## 读取小批量

* 为了使我们在读取训练集和测试集时更容易，我们使用*内置的数据迭代器*，而不是从零开始创建一个。
* 在每次迭代中，数据加载器每次都会[**读取一小批量数据，大小为`batch_size`**]。我们在训练数据迭代器中还随机打乱了所有样本。


```python
batch_size = 256
train_iter = tf.data.Dataset.from_tensor_slices(
    mnist_train).batch(batch_size).shuffle(len(mnist_train[0])) # 根据内置数据迭代器定义一个打乱获得小批量数据的迭代器
```

让我们看一下读取训练数据所需的时间。



```python
import time
class Timer:  
    """记录多次运行时间。"""
    def __init__(self):
        self.times = []
        self.start()

    def start(self):
        """启动计时器。"""
        self.tik = time.time()

    def stop(self):
        """停止计时器并将时间记录在列表中。"""
        self.times.append(time.time() - self.tik)
        return self.times[-1]

    def avg(self):
        """返回平均时间。"""
        return sum(self.times) / len(self.times)

    def sum(self):
        """返回时间总和。"""
        return sum(self.times)

    def cumsum(self):
        """返回累计时间。"""
        return np.array(self.times).cumsum().tolist()

timer = Timer()
for X, y in train_iter:
    continue
f'{timer.stop():.2f} sec'
```




    '0.39 sec'



## 整合所有组件

* 现在我们[**定义`load_data_fashion_mnist`函数**]，用于获取和读取Fashion-MNIST数据集。它返回训练集和验证集的*数据迭代器*。此外，它还接受一个可选参数，用来将图像大小调整为另一种形状。


```python
def load_data_fashion_mnist(batch_size, resize=None):   
    """下载Fashion-MNIST数据集，然后将其加载到内存中。"""
    mnist_train, mnist_test = tf.keras.datasets.fashion_mnist.load_data()
    # 将所有数字除以255，使所有像素值介于0和1之间，在最后添加一个批处理维度，
    # 并将标签转换为int32。
    process = lambda X, y: (tf.expand_dims(X, axis=3) / 255,
                            tf.cast(y, dtype='int32'))
    resize_fn = lambda X, y: (
        tf.image.resize_with_pad(X, resize, resize) if resize else X, y)
    return (
        tf.data.Dataset.from_tensor_slices(process(*mnist_train)).batch(
            batch_size).shuffle(len(mnist_train[0])).map(resize_fn),
        tf.data.Dataset.from_tensor_slices(process(*mnist_test)).batch(
            batch_size).map(resize_fn))
```

下面，我们通过指定`resize`参数来测试`load_data_fashion_mnist`函数的图像大小调整功能。



```python
train_iter, test_iter = load_data_fashion_mnist(32, resize=64)
for X, y in train_iter:
    print(X.shape, X.dtype, y.shape, y.dtype)
    break
```

    (32, 64, 64, 1) <dtype: 'float32'> (32,) <dtype: 'int32'>

# softmax回归的从零开始实现

* softmax回归也是重要的基础，因此(**你应该知道实现softmax的细节**)。我们使用Fashion-MNIST数据集，并设置数据迭代器的批量大小为256。


```python
import tensorflow as tf
from IPython import display
import matplotlib.pyplot as plts
```


```python
batch_size = 256
# 加载数据
def load_data_fashion_mnist(batch_size, resize=None):   
    """下载Fashion-MNIST数据集，然后将其加载到内存中。"""
    mnist_train, mnist_test = tf.keras.datasets.fashion_mnist.load_data()
    # 将所有数字除以255，使所有像素值介于0和1之间，在最后添加一个批处理维度，
    # 并将标签转换为int32。
    process = lambda X, y: (tf.expand_dims(X, axis=3) / 255,
                            tf.cast(y, dtype='int32'))
    resize_fn = lambda X, y: (
        tf.image.resize_with_pad(X, resize, resize) if resize else X, y)
    return (
        tf.data.Dataset.from_tensor_slices(process(*mnist_train)).batch(
            batch_size).shuffle(len(mnist_train[0])).map(resize_fn),
        tf.data.Dataset.from_tensor_slices(process(*mnist_test)).batch(
            batch_size).map(resize_fn))
# 加载训练集和测试集
train_iter, test_iter = load_data_fashion_mnist(batch_size)
```

## 初始化模型参数

* 和之前线性回归的例子一样，这里的每个样本都将用固定长度的向量表示。原始数据集中的每个样本都是$28 \times 28$的图像。在本节中，我们[**将展平每个图像，把它们看作长度为784的向量。**。现在我们暂时只把每个像素位置看作一个特征。
* 回想一下，在softmax回归中，我们的输出与类别一样多。(**因为我们的数据集有10个类别，所以网络输出维度为10**)。因此，*权重将构成一个$784 \times 10$的矩阵*，偏置将构成一个$1 \times 10$的行向量。与线性回归一样，我们将使用正态分布初始化我们的权重`W`，偏置初始化为0。


```python
num_inputs = 784
num_outputs = 10

W = tf.Variable(tf.random.normal(shape=(num_inputs, num_outputs),
                                 mean=0, stddev=0.01))
b = tf.Variable(tf.zeros(num_outputs)) # 偏移量初始化为0，10种类别有十个方程，10个偏移量
```

## 定义softmax操作

* 在实现softmax回归模型之前，让我们简要地回顾一下`sum`运算符如何沿着张量中的特定维度工作：[**给定一个矩阵`X`，我们可以对所有元素求和**]（默认情况下），也可以只求同一个轴上的元素，即同一列（轴0）或同一行（轴1）。如果`X`是一个形状为`(2, 3)`的张量，我们对列进行求和，则结果将是一个具有形状`(3,)`的向量。当调用sum运算符时，我们可以指定保持在原始张量的轴数，而不折叠求和的维度。这将产生一个具有形状`(1, 3)`的二维张量。
* `sum`运算符的使用示例：


```python
X = tf.constant([[1.0, 2.0, 3.0], [4.0, 5.0, 6.0]])
tf.reduce_sum(X, 0, keepdims=True), tf.reduce_sum(X, 1, keepdims=True) # 分别按照第0轴（纵轴）和第1轴（横轴）求和合并
```




    (<tf.Tensor: shape=(1, 3), dtype=float32, numpy=array([[5., 7., 9.]], dtype=float32)>,
     <tf.Tensor: shape=(2, 1), dtype=float32, numpy=
     array([[ 6.],
            [15.]], dtype=float32)>)



* softmax由三个步骤组成：

  1. 对每个项求幂（使用`exp`）；
  2. 对每一行求和（小批量中每个样本是一行），得到每个样本的归一化常数；
  3. 将每一行除以其归一化常数，确保结果的和为1。

* 表达式：

  $$
  \mathrm{softmax}(\mathbf{X})_{ij} = \frac{\exp(\mathbf{X}_{ij})}{\sum_k \exp(\mathbf{X}_{ik})}.
  $$


```python
def softmax(X):
    X_exp = tf.exp(X)
    partition = tf.reduce_sum(X_exp, 1, keepdims=True) # 按横轴求和
    return X_exp / partition  # 这里应用了广播机制
```

* 正如你所看到的，对于任何随机输入，[**我们将每个元素变成一个非负数。此外，依据概率原理，每行总和为1**]。示例：


```python
X = tf.random.normal((2, 5), 0, 1) 
X_prob = softmax(X) #测试softmax效果
X_prob, tf.reduce_sum(X_prob, 1)
```




    (<tf.Tensor: shape=(2, 5), dtype=float32, numpy=
     array([[0.02834685, 0.04244769, 0.55426127, 0.2997507 , 0.07519355],
            [0.07591152, 0.0270348 , 0.34433454, 0.52310216, 0.02961694]],
           dtype=float32)>,
     <tf.Tensor: shape=(2,), dtype=float32, numpy=array([1., 1.], dtype=float32)>)



## 定义模型

* 现在我们已经定义了softmax操作，我们可以[**实现softmax回归模型**]。下面的代码定义了输入如何通过网络映射到输出。注意，在将数据传递到我们的模型之前，我们使用`reshape`函数将每张原始图像展平为向量。


```python
def net(X):
    # y = XW + b 模型公式
    return softmax(tf.matmul(tf.reshape(X, (-1, W.shape[0])), W) + b) 
```

## 定义损失函数

* 我们需要实现交叉熵损失函数。这可能是深度学习中最常见的损失函数，因为目前分类问题的数量远远超过回归问题。
* 交叉熵采用真实标签的预测概率的负对数似然。我们不需要使用Python的for循环迭代预测（这往往是低效的）。我们可以通过一个运算符选择所有元素。
* 下面，我们[**创建一个数据`y_hat`，其中包含2个样本在3个类别的预测概率，**]它们对应的标签`y`。有了`y`，我们知道在第一个样本中，第一类是正确的预测，而在第二个样本中，第三类是正确的预测。然后(**使用`y`作为`y_hat`中概率的索引**)，我们选择第一个样本中第一个类的概率和第二个样本中第三个类的概率。


```python
y_hat = tf.constant([[0.1, 0.3, 0.6], [0.3, 0.2, 0.5]])
y = tf.constant([0, 2])
# 将y_hat的第一行的0位，第二行的2位设为1，其余位设为0
tf.boolean_mask(y_hat, tf.one_hot(y, depth=y_hat.shape[-1])) 
```




    <tf.Tensor: shape=(2,), dtype=float32, numpy=array([0.1, 0.5], dtype=float32)>



* 现在我们只需一行代码就可以[**实现交叉熵损失函数**]。


```python
def cross_entropy(y_hat, y):
    return -tf.math.log(tf.boolean_mask(
        y_hat, tf.one_hot(y, depth=y_hat.shape[-1])))

cross_entropy(y_hat, y)
```




    <tf.Tensor: shape=(2,), dtype=float32, numpy=array([2.3025851, 0.6931472], dtype=float32)>



## 分类准确率

* 给定预测概率分布`y_hat`，当我们必须输出硬预测（hard prediction）时，我们通常选择预测概率最高的类。许多应用都要求我们做出选择。如Gmail必须将电子邮件分为“Primary（主要）”、“Social（社交）”、“Updates（更新）”或“Forums（论坛）”。它可能在内部估计概率，但最终它必须在类中选择一个。
* 当预测与标签分类`y`一致时，它们是正确的。*分类准确率即正确预测数量与总预测数量之比*。
* 为了计算准确率，我们执行以下操作。首先，如果`y_hat`是矩阵，那么假定第二个维度存储每个类的预测分数。我们使用`argmax`获得每行中*最大元素的索引来获得预测类别*。然后我们[**将预测类别与真实`y`元素进行比较**]。由于等式运算符“`==`”对数据类型很敏感，因此我们将`y_hat`的数据类型转换为与`y`的数据类型一致。结果是一个包含0（错）和1（对）的张量。进行求和会得到正确预测的数量。


```python
def accuracy(y_hat, y):  
    """计算预测正确的数量。"""
    if len(y_hat.shape) > 1 and y_hat.shape[1] > 1:
        y_hat = tf.argmax(y_hat, axis=1) # 获取矩阵中每一行的最大元素的索引值
    cmp = tf.cast(y_hat, y.dtype) == y # 类型转换后再比较
    # 比较后矩阵中再求和，一位代表一行，如果该行匹配，则值为1
    return float(tf.reduce_sum(tf.cast(cmp, y.dtype))) 
```

* 我们将继续使用之前定义的变量`y_hat`和`y`分别作为预测的概率分布和标签。我们可以看到，第一个样本的预测类别是2（该行的最大元素为0.6，索引为2），这与实际标签0不一致。第二个样本的预测类别是2（该行的最大元素为0.5，索引为2），这与实际标签2一致。因此，这两个样本的分类准确率率为0.5。


```python
accuracy(y_hat, y) / len(y) # 实质是匹配数除以总数
```




    0.5



* 同样，对于任意数据迭代器`data_iter`可访问的数据集，[**我们可以评估在任意模型`net`的准确率**]。这个函数的作用是扩大accrracy函数的使用范围，并没有比accrracy函数多多少功能。注：[Zip函数使用教程](https://www.runoob.com/python/python-func-zip.html)


```python
class Accumulator:  
    """在`n`个变量上累加。"""
    def __init__(self, n):
        self.data = [0.0] * n # 乘法可以将向量延展n倍，如n=2则变为[0.0, 0.0]

    def add(self, *args):
        self.data = [a + float(b) for a, b in zip(self.data, args)] # 合并成一个新元组如[[0,a],[1, b]]再遍历

    def reset(self):
        self.data = [0.0] * len(self.data)

    def __getitem__(self, idx):
        return self.data[idx]

def evaluate_accuracy(net, data_iter):  
    """计算在指定数据集上模型的精度。"""
    # 参数2是指，一个有两个元素的向量，即：第0位为模型预测数，第1位为预测的数据总数
    metric = Accumulator(2)  
    for X, y in data_iter:
        metric.add(accuracy(net(X), y), len(y))
    return metric[0] / metric[1]    # 第一个是准确数，第二个是预测总数  
```

* 这里`Accumulator`是一个实用程序类，用于对多个变量进行累加。
  在上面的`evaluate_accuracy`函数中，我们在(**`Accumulator`实例中创建了2个变量，用于分别存储正确预测的数量和预测的总数量**)。当我们遍历数据集时，两者都将随着时间的推移而累加。

* evaluate_accuracy函数使用示例：由于我们使用随机权重初始化`net`模型，因此该模型的准确率应接近于随机猜测。例如在有10个类别情况下的准确率为0.1。


```python
evaluate_accuracy(net, test_iter)
```




    0.0987



## 训练

* 在这里，我们重构训练过程的实现以使其可重复使用。首先，我们定义一个函数来训练一个迭代周期。请注意，`updater`是更新模型参数的常用函数，它接受批量大小作为参数。注：[isinstance函数解析](https://www.runoob.com/python/python-func-isinstance.html)


```python
def train_epoch_ch3(net, train_iter, loss, updater):  
    """训练模型一个迭代周期"""
    # 训练损失总和、训练准确度总和、样本数
    metric = Accumulator(3)
    for X, y in train_iter:
        # 计算梯度并更新参数
        with tf.GradientTape() as tape:
            y_hat = net(X)
            # Keras内置的损失接受的是（标签，预测），这不同于用户在本书中的实现。
            # 本书的实现接受（预测，标签），例如我们上面实现的“交叉熵”
            if isinstance(loss, tf.keras.losses.Loss): # 检测是否是指定的函数类型
                l = loss(y, y_hat)
            else:
                l = loss(y_hat, y)
        if isinstance(updater, tf.keras.optimizers.Optimizer):
            params = net.trainable_variables
            grads = tape.gradient(l, params)
            updater.apply_gradients(zip(grads, params))
        else:
            updater(X.shape[0], tape.gradient(l, updater.params))
        # Keras的`loss`默认返回一个批量的平均损失
        l_sum = l * float(tf.size(y)) if isinstance(
            loss, tf.keras.losses.Loss) else tf.reduce_sum(l)
        metric.add(l_sum, accuracy(y_hat, y), tf.size(y))
    # 返回训练损失和训练准确率
    return metric[0] / metric[2], metric[1] / metric[2]
```

* 接下来我们实现一个[**训练函数**]，它会在`train_iter`访问到的训练数据集上训练一个模型`net`。该训练函数将会运行多个迭代周期（由`num_epochs`指定）。在每个迭代周期结束时，利用`test_iter`访问到的测试数据集对模型进行评估。我们将利用`Animator`类来可视化训练进度。


```python
def train_ch3(net, train_iter, test_iter, loss, num_epochs, updater):  
    """训练模型"""
    for epoch in range(num_epochs):
        train_metrics = train_epoch_ch3(net, train_iter, loss, updater)
        test_acc = evaluate_accuracy(net, test_iter)
    train_loss, train_acc = train_metrics
    assert train_loss < 0.5, train_loss
    assert train_acc <= 1 and train_acc > 0.7, train_acc
    assert test_acc <= 1 and test_acc > 0.7, test_acc
```

* 作为一个从零开始的实现，我们使用[**小批量随机梯度下降来优化模型的损失函数**]，设置学习率为0.1。


```python
 # params是元素类型为tf.Varible的列表，grads使参数对应的损失函数导数，lr是学习率
def sgd(params, grads, lr, batch_size): 
    """小批量随机梯度下降。"""
    for param, grad in zip(params, grads): # zip函数将可迭代对象打包成一个个元组用于遍历
        #tensorflow的assign_sub函数能够将param减去括号中参数的值再赋给param，实现更新参数
        param.assign_sub(lr*grad/batch_size) 

class Updater():  #@save
    """用小批量随机梯度下降法更新参数。"""
    def __init__(self, params, lr):
        self.params = params
        self.lr = lr

    def __call__(self, batch_size, grads):
        sgd(self.params, grads, self.lr, batch_size)

updater = Updater([W, b], lr=0.1)
```

* 现在，我们[**训练模型10个迭代周期**]。请注意，迭代周期（`num_epochs`）和学习率（`lr`）都是可调节的超参数。通过更改它们的值，我们可以提高模型的分类准确率。


```python
num_epochs = 10
train_ch3(net, train_iter, test_iter, cross_entropy, num_epochs, updater)
```

## 预测

* 现在训练已经完成，我们的模型已经准备好[**对图像进行分类预测**]。给定一系列图像，我们将比较它们的实际标签（文本输出的第一行）和模型预测（文本输出的第二行）。


```python
def get_fashion_mnist_labels(labels):  # 根据数字代码获取对应的名字
    """返回Fashion-MNIST数据集的文本标签。"""
    text_labels = ['t-shirt', 'trouser', 'pullover', 'dress', 'coat',
                   'sandal', 'shirt', 'sneaker', 'bag', 'ankle boot']
    return [text_labels[int(i)] for i in labels]

def show_images(imgs, num_rows, num_cols, titles=None, scale=3):  # scale设置图像显示的大小 
    """绘制图像列表。"""
    figsize = (num_cols * scale, num_rows * scale)
    _, axes = plt.subplots(num_rows, num_cols, figsize=figsize)
    axes = axes.flatten()
    for i, (ax, img) in enumerate(zip(axes, imgs)):
        ax.imshow(img.numpy())
        ax.axes.get_xaxis().set_visible(False)
        ax.axes.get_yaxis().set_visible(False)
        if titles:
            ax.set_title(titles[i]) # 从title参数数组中取出对应图像的名称
    return axes

def predict_ch3(net, test_iter, n=6):  
    """预测标签"""
    for X, y in test_iter:
        break
    trues = get_fashion_mnist_labels(y)
    preds = get_fashion_mnist_labels(tf.argmax(net(X), axis=1))
    titles = [true +'\n' + pred for true, pred in zip(trues, preds)]
    show_images(
        tf.reshape(X[0:n], (n, 28, 28)), 1, n, titles=titles[0:n])

predict_ch3(net, test_iter)
```

![output_35_0](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/output_35_0.png)  

# softmax回归的简洁实现

* 我们可以发现(**通过深度学习框架的高级API能够使实现**)线性(**回归变得更加容易**)。同样地，通过深度学习框架的高级API也能更方便地实现分类模型。让我们继续使用Fashion-MNIST数据集，并保持批量大小为256。


```python
import tensorflow as tf
```

## 加载数据


```python
batch_size = 256
# 加载数据
def load_data_fashion_mnist(batch_size, resize=None):   
    """下载Fashion-MNIST数据集，然后将其加载到内存中。"""
    mnist_train, mnist_test = tf.keras.datasets.fashion_mnist.load_data()
    # 将所有数字除以255，使所有像素值介于0和1之间，在最后添加一个批处理维度，
    # 并将标签转换为int32。
    process = lambda X, y: (tf.expand_dims(X, axis=3) / 255,
                            tf.cast(y, dtype='int32'))
    resize_fn = lambda X, y: (
        tf.image.resize_with_pad(X, resize, resize) if resize else X, y)
    return (
        tf.data.Dataset.from_tensor_slices(process(*mnist_train)).batch(
            batch_size).shuffle(len(mnist_train[0])).map(resize_fn),
        tf.data.Dataset.from_tensor_slices(process(*mnist_test)).batch(
            batch_size).map(resize_fn))
# 加载训练集和测试集
train_iter, test_iter = load_data_fashion_mnist(batch_size)
```

## 初始化模型参数

* 如前所述，[**softmax回归的输出层是一个全连接层**]。因此，为了实现我们的模型，我们只需在`Sequential`中添加一个带有10个输出的全连接层。同样，在这里，`Sequential`并不是必要的，但我们可能会形成这种习惯。因为在实现深度模型时，`Sequential`将无处不在。我们仍然以均值0和标准差0.01随机初始化权重。


```python
net = tf.keras.models.Sequential()
net.add(tf.keras.layers.Flatten(input_shape=(28, 28))) # 构建一层
weight_initializer = tf.keras.initializers.RandomNormal(mean=0.0, stddev=0.01) # 权重参数初始化
net.add(tf.keras.layers.Dense(10, kernel_initializer=weight_initializer))
```

## 重新审视Softmax的实现

* 在之前的例子中，我们计算了模型的输出，然后将此输出送入交叉熵损失。从数学上讲，这是一件完全合理的事情。然而，从计算角度来看，指数可能会造成数值稳定性问题。

* softmax函数$\hat y_j = \frac{\exp(o_j)}{\sum_k \exp(o_k)}$，其中$\hat y_j$是预测的概率分布。$o_j$是未归一化的预测$\mathbf{o}$的第$j$个元素。如果$o_k$中的一些数值非常大，那么$\exp(o_k)$可能大于数据类型容许的最大数字（即*上溢*（overflow））。这将使分母或分子变为`inf`（无穷大），我们最后遇到的是0、`inf`或`nan`（不是数字）的$\hat y_j$。在这些情况下，我们不能得到一个明确定义的交叉熵的返回值。

* 解决这个问题的一个技巧是，在继续softmax计算之前，先从所有$o_k$中减去$\max(o_k)$。你可以证明每个$o_k$按常数进行的移动不会改变softmax的返回值。在减法和归一化步骤之后，可能有些$o_j$具有较大的负值。由于精度受限，$\exp(o_j)$将有接近零的值，即*下溢*（underflow）。这些值可能会四舍五入为零，使$\hat y_j$为零，并且使得$\log(\hat y_j)$的值为`-inf`。反向传播几步后，我们可能会发现自己面对一屏幕可怕的`nan`结果。

* 尽管我们要计算指数函数，但我们最终在计算交叉熵损失时会取它们的对数。通过将softmax和交叉熵结合在一起，可以避免反向传播过程中可能会困扰我们的数值稳定性问题。如下面的等式所示，我们避免计算$\exp(o_j)$，而可以直接使用$o_j$。因为$\log(\exp(\cdot))$被抵消了。
  $$
  \begin{aligned}
  \log{(\hat y_j)} & = \log\left( \frac{\exp(o_j)}{\sum_k \exp(o_k)}\right) \\
  & = \log{(\exp(o_j))}-\log{\left( \sum_k \exp(o_k) \right)} \\
  & = o_j -\log{\left( \sum_k \exp(o_k) \right)}.
  \end{aligned}
  $$

* 我们也希望保留传统的softmax函数，以备我们需要评估通过模型输出的概率。但是，我们没有将softmax概率传递到损失函数中，而是[**在交叉熵损失函数中传递未归一化的预测，并同时计算softmax及其对数**]，这是一件聪明的事情["LogSumExp技巧"](https://en.wikipedia.org/wiki/LogSumExp)。


```python
loss = tf.keras.losses.SparseCategoricalCrossentropy(from_logits=True)
```

## 优化算法

* 在这里，我们(**使用学习率为0.1的小批量随机梯度下降作为优化算法**)。这与我们在线性回归例子中的相同，这说明了优化器的普适性。


```python
trainer = tf.keras.optimizers.SGD(learning_rate=.1)
```

## 训练

* 接下来我们[**调用**]之前定义的训练函数来训练模型。


```python
num_epochs = 10

def accuracy(y_hat, y):  
    """计算预测正确的数量。"""
    if len(y_hat.shape) > 1 and y_hat.shape[1] > 1:
        y_hat = tf.argmax(y_hat, axis=1) # 获取矩阵中每一行的最大元素的索引值
    cmp = tf.cast(y_hat, y.dtype) == y # 类型转换后再比较
    # 比较后矩阵中再求和，一位代表一行，如果该行匹配，则值为1
    return float(tf.reduce_sum(tf.cast(cmp, y.dtype))) 

def evaluate_accuracy(net, data_iter):  
    """计算在指定数据集上模型的精度。"""
    # 参数2是指，一个有两个元素的向量，即：第0位为模型预测数，第1位为预测的数据总数
    metric = Accumulator(2)  
    for X, y in data_iter:
        metric.add(accuracy(net(X), y), len(y))
    return metric[0] / metric[1]    # 第一个是准确数，第二个是预测总数  

class Accumulator:  
    """在`n`个变量上累加。"""
    def __init__(self, n):
        self.data = [0.0] * n # 乘法可以将向量延展n倍，如n=2则变为[0.0, 0.0]

    def add(self, *args):
        self.data = [a + float(b) for a, b in zip(self.data, args)] # 合并成一个新元组如[[0,a],[1, b]]再遍历

    def reset(self):
        self.data = [0.0] * len(self.data)

    def __getitem__(self, idx):
        return self.data[idx]

def train_epoch_ch3(net, train_iter, loss, updater):  
    """训练模型一个迭代周期"""
    # 训练损失总和、训练准确度总和、样本数
    metric = Accumulator(3)
    for X, y in train_iter:
        # 计算梯度并更新参数
        with tf.GradientTape() as tape:
            y_hat = net(X)
            # Keras内置的损失接受的是（标签，预测），这不同于用户在本书中的实现。
            # 本书的实现接受（预测，标签），例如我们上面实现的“交叉熵”
            if isinstance(loss, tf.keras.losses.Loss): # 检测是否是指定的函数类型
                l = loss(y, y_hat)
            else:
                l = loss(y_hat, y)
        if isinstance(updater, tf.keras.optimizers.Optimizer):
            params = net.trainable_variables
            grads = tape.gradient(l, params)
            updater.apply_gradients(zip(grads, params))
        else:
            updater(X.shape[0], tape.gradient(l, updater.params))
        # Keras的`loss`默认返回一个批量的平均损失
        l_sum = l * float(tf.size(y)) if isinstance(
            loss, tf.keras.losses.Loss) else tf.reduce_sum(l)
        metric.add(l_sum, accuracy(y_hat, y), tf.size(y))
    # 返回训练损失和训练准确率
    return metric[0] / metric[2], metric[1] / metric[2]

def train_ch3(net, train_iter, test_iter, loss, num_epochs, updater):  
    """训练模型"""
    for epoch in range(num_epochs):
        train_metrics = train_epoch_ch3(net, train_iter, loss, updater)
        test_acc = evaluate_accuracy(net, test_iter)
    train_loss, train_acc = train_metrics
    assert train_loss < 0.5, train_loss
    assert train_acc <= 1 and train_acc > 0.7, train_acc
    assert test_acc <= 1 and test_acc > 0.7, test_acc

train_ch3(net, train_iter, test_iter, loss, num_epochs, trainer)
```

## 测试

* 现在训练已经完成，我们的模型已经准备好[对图像进行分类预测]。给定一系列图像，我们将比较它们的实际标签（文本输出的第一行）和模型预测（文本输出的第二行）。


```python
import matplotlib.pyplot as plt

def get_fashion_mnist_labels(labels):  # 根据数字代码获取对应的名字
    """返回Fashion-MNIST数据集的文本标签。"""
    text_labels = ['t-shirt', 'trouser', 'pullover', 'dress', 'coat',
                   'sandal', 'shirt', 'sneaker', 'bag', 'ankle boot']
    return [text_labels[int(i)] for i in labels]

def show_images(imgs, num_rows, num_cols, titles=None, scale=3):  # scale设置图像显示的大小 
    """绘制图像列表。"""
    figsize = (num_cols * scale, num_rows * scale)
    _, axes = plt.subplots(num_rows, num_cols, figsize=figsize)
    axes = axes.flatten()
    for i, (ax, img) in enumerate(zip(axes, imgs)):
        ax.imshow(img.numpy())
        ax.axes.get_xaxis().set_visible(False)
        ax.axes.get_yaxis().set_visible(False)
        if titles:
            ax.set_title(titles[i]) # 从title参数数组中取出对应图像的名称
    return axes

def predict_ch3(net, test_iter, n=6):  
    """预测标签"""
    for X, y in test_iter:
        break
    trues = get_fashion_mnist_labels(y)
    preds = get_fashion_mnist_labels(tf.argmax(net(X), axis=1))
    titles = [true +'\n' + pred for true, pred in zip(trues, preds)]
    show_images(
        tf.reshape(X[0:n], (n, 28, 28)), 1, n, titles=titles[0:n])

predict_ch3(net, test_iter)
```


   ![output_14_0](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/output_14_0.png)
    

​    

