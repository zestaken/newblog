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

#绘制灰度图像
plt.subplot(221)
plt.imshow(X_train[0], cmap=plt.get_cmap('gray'))
plt.subplot(222)
plt.imshow(X_train[1], cmap=plt.get_cmap('gray'))
plt.subplot(223)
plt.imshow(X_train[2], cmap=plt.get_cmap('gray'))
plt.subplot(224)
plt.imshow(X_train[3], cmap=plt.get_cmap('gray'))
# show the plot
plt.show()

# 格式化keras训练数据
num_pixels = X_train.shape[1] * X_train.shape[2]
X_train = X_train.reshape(X_train.shape[0], num_pixels).astype('float32')
X_test = X_test.reshape(X_test.shape[0], num_pixels).astype('float32')
X_train = X_train / 255
X_test = X_test / 255
# 再次查看训练数据集和和测试数据集的格式
print(X_train.shape)
print(X_test.shape)

# 创建 MNIST 数据集的噪声版本，并将其作为解码器网络的输入
#定义一个噪声因子，它是一个超参数。噪声因子乘以均值为 0.0 且标准差为 1.0 的随机矩阵。
# 该矩阵将从正态（高斯）分布中抽取样本。在添加噪声时，我们必须记住正态随机数组的形状与你将添加噪声的数据的形状一样。
noise_factor = 0.2
x_train_noisy = X_train + noise_factor * numpy.random.normal(loc=0.0, scale=1.0, size=X_train.shape)
x_test_noisy = X_test + noise_factor * numpy.random.normal(loc=0.0, scale=1.0, size=X_test.shape)
# clip 是numpy的函数，用于剪辑min - max范围之外的值，并用指定的最小或最大值替换它们。
# 可以确保我们最终的图像数组项值在 0 到 1 的范围内
x_train_noisy = numpy.clip(x_train_noisy, 0., 1.)
x_test_noisy = numpy.clip(x_test_noisy, 0., 1.)

# 定义编码器-解码器网络
# 它将有一个 784 个神经元的输入层，因为我们的图像大小为 784，因为存在 28 x 28 个像素（即我们有 784 个神经元的输入维度和输出层）。
model = Sequential()
model.add(Dense(500, input_dim=num_pixels, activation='relu'))
model.add(Dense(300, activation='relu'))
model.add(Dense(100, activation='relu'))
model.add(Dense(300, activation='relu'))
model.add(Dense(500, activation='relu'))
model.add(Dense(784, activation='sigmoid'))

# 编译模型
# 在编译时，我们提供要使用的损失函数、优化器和任何指标。
# 我们将为我们的模型使用Adam 优化器和均方误差。
model.compile(loss='mean_squared_error', optimizer='adam')
# 训练模型
# 们将向网络提供训练数据。此外，我们将指定验证数据，仅在其上验证模型。
model.fit(x_train_noisy, X_train, validation_data=(x_test_noisy, X_test), epochs=100, batch_size=200)

# 在测试数据集上评估我们的训练模型
pred = model.predict(x_test_noisy)
pred.shape
# 图像展示效果
X_test = numpy.reshape(X_test, (10000, 28, 28)) * 255
pred = numpy.reshape(pred, (10000, 28, 28)) * 255
x_test_noisy = numpy.reshape(x_test_noisy, (-1, 28, 28)) * 255
plt.figure(figsize=(20, 4))
print("Test Images")
for i in range(10, 20, 1):
    plt.subplot(2, 10, i + 1)
    plt.imshow(X_test[i, :, :], cmap='gray')
    curr_lbl = y_test[i]
    plt.title("(Label: " + str(curr_lbl) + ")")
plt.show()
plt.figure(figsize=(20, 4))
print("Test Images with Noise")
for i in range(10, 20, 1):
    plt.subplot(2, 10, i + 1)
    plt.imshow(x_test_noisy[i, :, :], cmap='gray')
plt.show()
plt.figure(figsize=(20, 4))
print("Reconstruction of Noisy Test Images")
for i in range(10, 20, 1):
    plt.subplot(2, 10, i + 1)
    plt.imshow(pred[i, :, :], cmap='gray')
plt.show()






