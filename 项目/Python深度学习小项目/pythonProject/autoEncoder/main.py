# numpy用于优化矩阵乘法
import numpy
# matplotlib用于数据可视化，如绘制图像
import matplotlib.pyplot as plt
# 来自 Keras 的序列模型为我们提供了一个空的架构
# 根据我们的架构，我们将在其中添加几个全连接层，Dense在我们的网络中创建全连接层
# 直接从Keras导入MNIST数据集
from keras.models import Sequential
from keras.layers import Dense
from keras.datasets import mnist

#  MNIST 数据集，这是一个简单的计算机视觉数据集。它由灰度形式的手写数字图像组成。它还包括每个图像的标签，告诉我们它是哪个数字（即每个图像的输出）
#  这意味着我们手中有一个标记数据可以使用。MNIST 数据集中的每个图像都是 28 x 28 像素。
# MNIST数据集中的60000个数据点用于训练，10000个数据点用于测试

# 以Numpy格式加载数据集
(X_train, y_train), (X_test, y_test) = mnist.load_data()
# 查看训练数据集和和测试数据集的格式
print(X_train.shape)
print(X_test.shape)

