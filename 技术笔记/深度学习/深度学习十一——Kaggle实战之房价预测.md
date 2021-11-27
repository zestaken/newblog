---
title: 深度学习十一——Kaggle实战之房价预测
date: 2021-11-26 12:02:19
tags: [深度学习]
categories: 技术笔记
---

# Kaggle房价预测比赛简介

* [比赛地址](https://www.kaggle.com/c/house-prices-advanced-regression-techniques)
* 竞赛数据分为训练集和测试集。每条记录都包括房屋的属性值和属性，如街道类型、施工年份、屋顶类型、地下室状况等。这些特征由各种数据类型组成。例如，建筑年份由整数表示，屋顶类型由离散类别表示，其他特征由浮点数表示。这就是现实让事情变得复杂的地方：例如，一些数据完全丢失了，缺失值被简单地标记为“NA”。每套房子的价格只出现在训练集中（毕竟这是一场比赛）。
* 训练模型时划分训练集以创建验证集，来判断模型的效果。
* 最终将将预测结果上传到Kaggle之后，在官方测试集中评估我们的模型。

# 数据获取

* 先从kaggle比赛网址上将数据集下载下来。
* 再将数据导入到程序对象中以供使用。


```python
import pandas as pd
import numpy as np
import tensorflow as tf
```

* 训练数据集包括1460个样本，每个样本80个特征和1个标签，而测试数据包含1459个样本，每个样本80个特征。


```python
train_data = pd.read_csv("./house-prices/train.csv")
test_data = pd.read_csv("./house-prices/test.csv")
train_data.shape,test_data.shape
```




    ((1460, 81), (1459, 80))



# 数据预处理

* concat将训练数据集和测试数据集纵向连接起来[concat用法](https://zhuanlan.zhihu.com/p/69224745)
* 将训练集的id列（第0列）和标签列（最后一列，即房价列）去除再和去除id列的test集合并
* [检查NaN值的方法](https://www.cnblogs.com/songdanzju/p/7497566.html)


```python
all_features = pd.concat((train_data.iloc[:,1:-1], test_data.iloc[:,1:])) # 将测试集和训练集所有特征值合并
all_features.shape
```




    (2919, 79)



* 将所有缺失的值（NaN值）替换为相应特征的平均值。
* 为了将所有特征放在一个共同的尺度上，我们(**通过将特征重新缩放到零均值和单位方差来标准化数据**)：
    $$x \leftarrow \frac{x - \mu}{\sigma}.$$


```python
numeric_features = all_features.dtypes[all_features.dtypes != 'object'].index # 获取值为数值的特征的索引
# 我们通过将特征重新缩放到零均值和单位方差来标准化数据(将所有的数值数据缩至同一范围)
all_features[numeric_features] = all_features[numeric_features].apply(lambda x : (x - x.mean()) / (x.std()))
all_features[numeric_features] = all_features[numeric_features].fillna(0) # 将缺失值填充为0
all_features[numeric_features].isnull().any() # 检查，确定已经没有有NaN值的列（有NaN值列为True）
```




    MSSubClass       False
    LotFrontage      False
    LotArea          False
    OverallQual      False
    OverallCond      False
    YearBuilt        False
    YearRemodAdd     False
    MasVnrArea       False
    BsmtFinSF1       False
    BsmtFinSF2       False
    BsmtUnfSF        False
    TotalBsmtSF      False
    1stFlrSF         False
    2ndFlrSF         False
    LowQualFinSF     False
    GrLivArea        False
    BsmtFullBath     False
    BsmtHalfBath     False
    FullBath         False
    HalfBath         False
    BedroomAbvGr     False
    KitchenAbvGr     False
    TotRmsAbvGrd     False
    Fireplaces       False
    GarageYrBlt      False
    GarageCars       False
    GarageArea       False
    WoodDeckSF       False
    OpenPorchSF      False
    EnclosedPorch    False
    3SsnPorch        False
    ScreenPorch      False
    PoolArea         False
    MiscVal          False
    MoSold           False
    YrSold           False
    dtype: bool



* 将非数值的特征值转换为独热编码。如，“MSZoning”包含值“RL”和“Rm”。将创建两个新的指示器特征“MSZoning_RL”和“MSZoning_RM”，其值为0或1。* pandas自带的函数可以完成这个过程。


```python
# `Dummy_na=True` 将“na”（缺失值）视为有效的特征值，并为其创建指示符特征。
all_features = pd.get_dummies(all_features, dummy_na=True) # 
all_features.shape # 列数增多，即很多离散的非数值特征值被拆分成独热编码导致的
```




    (2919, 331)



* 通过过values属性，我们可以从pandas格式中提取NumPy格式，并将其转换为张量表示用于训练。


```python
n_train = train_data.shape[0] # 训练数据的数量
train_features = tf.constant(all_features[:n_train].values, dtype=tf.float32) # 从全部特征值中取出训练特征值
test_features = tf.constant(all_features[n_train : ].values, dtype=tf.float32)# 从全部特征值中取出测试特征值
train_labels = tf.constant(train_data.SalePrice.values.reshape(-1, 1), dtype=tf.float32) # 从训练数据中取出标签列
```

# 训练

* 我们训练一个带有损失平方的线性模型。
* [L2范数用于权重衰减（简洁实现)](https://www.zestaken.top/post/%E6%8A%80%E6%9C%AF%E7%AC%94%E8%AE%B0%2F%E6%B7%B1%E5%BA%A6%E5%AD%A6%E4%B9%A0%2F%E6%B7%B1%E5%BA%A6%E5%AD%A6%E4%B9%A0%E4%B8%83%E2%80%94%E2%80%94%E6%A8%A1%E5%9E%8B%E9%80%89%E6%8B%A9%E3%80%81%E6%AC%A0%E6%8B%9F%E5%90%88%E5%92%8C%E8%BF%87%E6%8B%9F%E5%90%88)


```python
loss = tf.keras.losses.MeanSquaredError() # 平方损失函数
def get_net(): # 定义获取模型的函数
    net = tf.keras.Sequential() # 序列模型
    net.add(tf.keras.layers.Dense( # 添加一个全连接层
            1, kernel_regularizer = tf.keras.regularizers.l2(weight_decay))) # 添加L2范数惩罚项
    return net
```

* 用价格预测的对数来衡量差异。事实上，这也是比赛中官方用来评价提交质量的误差指标。即将 $\delta$ for $|\log y - \log \hat{y}| \leq \delta$转换为$e^{-\delta} \leq \frac{\hat{y}}{y} \leq e^\delta$。这使得预测价格的对数与真实标签价格的对数之间出现以下均方根误差：
    $$\sqrt{\frac{1}{n}\sum_{i=1}^n\left(\log y_i -\log \hat{y}_i\right)^2}.$$
* 定义使用对数计算相对误差的函数


```python
def log_rmse(y_true, y_pred): # 传入实际标签值，和预测标签值
     # 为了在取对数时进一步稳定该值，将小于1的值设置为1
    clipped_preds = tf.clip_by_value(y_pred, 1, float('inf')) # 处理后的预测标签值
    return tf.sqrt(tf.reduce_mean(
                    loss(tf.math.log(y_true), tf.math.log(clipped_preds))
                    )) # 计算相对误差
```

* 定义训练函数
* 注：[d2l中函数定义](https://www.zestaken.top/post/%E6%8A%80%E6%9C%AF%E7%AC%94%E8%AE%B0%2F%E6%B7%B1%E5%BA%A6%E5%AD%A6%E4%B9%A0%2F%E6%B7%B1%E5%BA%A6%E5%AD%A6%E4%B9%A0%E5%B8%B8%E7%94%A8%E8%87%AA%E5%AE%9A%E4%B9%89%E5%87%BD%E6%95%B0) [Adam优化器](https://zh-v2.d2l.ai/chapter_optimization/adam.html)


```python
from d2l import tensorflow as d2l

def train(net, train_features, train_labels, test_features, test_labels,
         num_epochs, learning_rate, weight_decay, batch_size):
    train_ls = []
    test_ls = [] # 存储计算的相对误差值
    train_iter = d2l.load_array((train_features, train_labels), batch_size) # 将训练特征值和训练标签生成小批量迭代器
    # 定义Adam优化器
    optimizer = tf.keras.optimizers.Adam(learning_rate) 
    # 生成模型
    net.compile(loss=loss, optimizer=optimizer)
    # 迭代训练
    for epoch in range(num_epochs):
        for X, y in train_iter: # 迭代得到小批量特征值和标签
            with tf.GradientTape() as tape:
                y_hat = net(X) # 由模型获取预测值
                l = loss(y, y_hat) # 计算损失值
            params = net.trainable_variables # 获取模型的参数值
            grads = tape.gradient(l, params) # 计算损失值关于模型参数值的梯度
            optimizer.apply_gradients(zip(grads, params)) # 根据梯度使用优化器优化参数
        train_ls.append(log_rmse(train_labels, net(train_features))) # 计算标签值和训练后模型的预测值的相对误差并存储到数组中
        if test_labels is not None:
            test_ls.append(log_rmse(test_labels, net(test_features))) # 计算测试数据的相对误差
    return train_ls, test_ls # 返回相对误差值数组
```

# K折交叉验证优化模型

* [K折交叉验证](https://www.zestaken.top/post/%E6%8A%80%E6%9C%AF%E7%AC%94%E8%AE%B0%2F%E6%B7%B1%E5%BA%A6%E5%AD%A6%E4%B9%A0%2F%E6%B7%B1%E5%BA%A6%E5%AD%A6%E4%B9%A0%E4%B8%83%E2%80%94%E2%80%94%E6%A8%A1%E5%9E%8B%E9%80%89%E6%8B%A9%E3%80%81%E6%AC%A0%E6%8B%9F%E5%90%88%E5%92%8C%E8%BF%87%E6%8B%9F%E5%90%88)用来选择模型，或者选择同一模型的不同超参数
* 我们首先需要一个函数，在 𝐾 折交叉验证过程中返回第 𝑖 折的数据作为验证数据集，剩余k-1折组成训练数据集。
* 注：[slice函数使用](https://www.w3school.com.cn/python/ref_func_slice.asp) [tf.concat使用](https://blog.csdn.net/leviopku/article/details/82380118)


```python
def get_k_fold_data(k, i, X, y):
        assert k > 1
        fold_size = X.shape[0] // k # 定义每一折的数据量（X的数据量除以折数并向下取整）
        X_train, y_train = None, None
        for j in range(k):
            idx = slice(j * fold_size, (j + 1) * fold_size) # 获取一折数量的下标
            X_part  = X[idx, :] # 取一折数量的的特征向量
            y_part = y[idx] # 取对应一折数量的标签量
            if j == i: # 取出第i折的元素作为验证数据集
                X_valid, y_valid = X_part, y_part
            elif X_train is None: # 当训练数据集还为空时，不用拼接而是将当前折赋给训练数据集
                X_train, y_train = X_part, y_part
            else:
                X_train = tf.concat([X_train, X_part], 0) # 拼接当前折的张量到训练数据集，在第0维，即按行拼接
                y_train = tf.concat([y_train, y_part], 0)
        return X_train, y_train, X_valid, y_valid # 返回k-1折合并的训练数据集和其中一折组成的验证数据集
```

* 当我们在 𝐾 折交叉验证中训练 𝐾 次后，返回训练和验证误差的平均值。下面定义运用K折交叉验证的训练函数。
* [函数调用时*运算符的作用](https://blog.csdn.net/cadi2011/article/details/84871401)


```python
def k_fold(k, X_train, y_train, num_epochs, learnging_rate, weight_decay, batch_size):
    train_l_sum, valid_l_sum =0, 0
    for i in range(k): # 进行k次k折训练，每一次训练迭代num_epochs次（即每一个train函数内部会迭代num_epochs次进行参数的优化）
        data = get_k_fold_data(k, i, X_train, y_train) # 获取经过k折处理的数据（训练和验证集）
        net = get_net() # 获取定义的模型
        # 训练数据并获取返回的训练集和验证集的相对误差值数组
        train_ls, valid_ls = train(net, *data, num_epochs, learnging_rate, weight_decay, batch_size) # *data将data数据一一解封
        train_l_sum += train_ls[-1]
        valid_l_sum += valid_ls[-1] # 取训练后相对误差的最后一位，即一次训练结束后的相对误差
#         if i == 0: # 将第一次k折训练的情况绘制成图像展示
#             d2l.plot(list(range(1, num_epochs+1)), [train_ls, valid_ls],
#                     xlabel='epoch', ylabel='rmse', xlim=[1, num_epochs],
#                     legend=['train', 'valid'], yscale='log')
#         print(f'fold {i + 1} train log rmse {float(train_ls[-1]):f},'
#               f'valid log rmse {float(valid_ls[-1]):f}') # 输出每一次k折训练后训练集和验证集的相对误差，:f是限制数据输出的格式保留多少位
    return train_l_sum / k, valid_l_sum / k # 返回k次k折训练后的相对误差平均值
```

* 一次K折交叉验证训练的示例：


```python
# 定义超参数
k, num_epochs, lr, weight_decay, batch_size = 5, 100, 5, 0, 64
# 5次k折训练
train_l, valid_l = k_fold(k, train_features, train_labels, num_epochs, lr, weight_decay, batch_size)
print(f'{k}-折验证: 平均训练log rmse: {float(train_l):f}, '
      f'平均验证log rmse: {float(valid_l):f}')
```

    fold 1 train log rmse 0.170829,valid log rmse 0.157123
    fold 2 train log rmse 0.162197,valid log rmse 0.188655
    fold 3 train log rmse 0.164240,valid log rmse 0.168701
    fold 4 train log rmse 0.168455,valid log rmse 0.154722
    fold 5 train log rmse 0.163307,valid log rmse 0.182933
    5-折验证: 平均训练log rmse: 0.165806, 平均验证log rmse: 0.170427




![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211127121236.svg)
    


* 下面通过k折交叉验证来从12到24选择一个最优的学习率（lr）超参数：


```python
# 其它超参数固定不变
k, num_epochs, weight_decay, batch_size = 5, 100,0, 64
for lr in range(12, 25, 1): # 学习率从12到24取值
    train_l, valid_l = k_fold(k, train_features, train_labels, num_epochs, lr, weight_decay, batch_size)
    print(f'学习率 {lr}: 平均训练log rmse: {float(train_l):f}, '
      f'平均验证log rmse: {float(valid_l):f}')
```

    学习率 20: 平均训练log rmse: 0.130166, 平均验证log rmse: 0.148109
    学习率 21: 平均训练log rmse: 0.129168, 平均验证log rmse: 0.148078
    学习率 22: 平均训练log rmse: 0.128741, 平均验证log rmse: 0.148535
    学习率 23: 平均训练log rmse: 0.128011, 平均验证log rmse: 0.147719
    学习率 24: 平均训练log rmse: 0.127184, 平均验证log rmse: 0.148102
    学习率 25: 平均训练log rmse: 0.127095, 平均验证log rmse: 0.148823
    学习率 26: 平均训练log rmse: 0.126952, 平均验证log rmse: 0.149455
    学习率 27: 平均训练log rmse: 0.126383, 平均验证log rmse: 0.150066
    学习率 28: 平均训练log rmse: 0.125802, 平均验证log rmse: 0.149238
    学习率 29: 平均训练log rmse: 0.125103, 平均验证log rmse: 0.148572
    学习率 30: 平均训练log rmse: 0.125125, 平均验证log rmse: 0.149642


* 从上面可以分析出，在该范围内随着学习率的上升，平均训练误差一直下降；
* 但是我们要注意到，在学习率超过23之后，在平均训练误差下降的同时，平均验证误差却有上升。
    * 这说明我们在学习率超过23之后，我们的平均训练误差的上升，是以过拟合为代价的。
    * 所以，我们应该选择23为学习率。

* 确定我们要选择的最优超参数之后，我们再用所有数据对模型进行训练。
* 然后，我们通过这种方式获得的模型可以应用于测试集。
* 将预测保存在CSV文件中，将结果上传到Kaggle。


```python
# 定义训练和预测函数
def train_and_pred(train_featrues, test_features, train_labels, test_data, 
                  num_epochs, lr, weight_decay, batch_size):
    net = get_net()
    # 验证集相对误差没有用，用一个_接受之后就不用管了
    train_ls, _ = train(net, train_features, train_labels, None, None,
                        num_epochs, lr, weight_decay, batch_size) # 两个None原来是传的验证集，此处不需要
    # 绘制训练集相对误差随着模型训练迭代次数的增加的变化图
    d2l.plot(np.arange(1, num_epochs + 1), [train_ls], xlabel='epoch',
             ylabel='log rmse', xlim=[1, num_epochs], yscale='log')
    # 输出模型训练结束后的训练集相对误差
    print(f'train log rmse {float(train_ls[-1]):f}')
    # 将模型运用于测试集
    preds = net(test_features).numpy()
    # 将其重新格式化以导出到Kaggle
    test_data['SalePrice'] = pd.Series(preds.reshape(1, -1)[0])
    submission = pd.concat([test_data['Id'], test_data['SalePrice']], axis=1)
    submission.to_csv('submission.csv', index=False)
```

* 下面代码在整个训练数据集上进行训练并预测测试集上的数据，将预测结果生成一个名为submission.csv的文件。


```python
# 设置超参数
num_epochs, lr, weight_decay, batch_size = 100, 23, 0, 64
train_and_pred(train_features, test_features, train_labels, test_data,
               num_epochs, lr, weight_decay, batch_size)
```

    train log rmse 0.127216




![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211127121255.svg)
    


# 提交预测数据

* 生成的预测数据样式：
    ![R2uWkc](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/R2uWkc.png)

* 提交到kaggle结果：
    ![BHSK4g](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/BHSK4g.png)
* 其中的score就是代表预测值与实际值的误差，越小越好。（这里的score计算方法，就是我们计算的对数相对误差）
* 顺便看个排名：
    ![dWvv0J](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/dWvv0J.png)

