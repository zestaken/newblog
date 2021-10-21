---
title: Scikit-Learn工具包
date: 2021-10-21 09:10:19
tags: [Python, 大数据, Scikit-Learn, 机器学习]
categories: 技术笔记
---
# Scikit-Learn概述

* Scikit-Learn是由DavidCournapeau 在2007 年发起的项目，是一种基于python的机器学习模块。
* Scikit-Learn库已经实现了几乎所有常用的机器学习算法

* 通过conda安装Scikit-Learn库

* Scikit-Learn库包含的算法：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211021085145.png)

## Scikit-Learn实现决策树算法

* 决策树是直观运用概率分析的一种图解法。由于这种决策分支画成图形很像一棵树的枝干，故称决策树。决策树代表一类算法，C4.5是其中比较典型的一种算法。C4.5算法采用熵来选择属性，以构成决策分支；并采用后剪枝以抑制不必要的决策分支的生长。

* conda安装pydotplus和python-graphviz两个包

* scikit-learn库包含了可以使用的鸢尾花数据集iris

* 载入支持库


```python
from sklearn import tree
from sklearn.datasets import load_iris
from sklearn.metrics import accuracy_score
from sklearn.model_selection import train_test_split
import pydotplus
```

* 准备数据


```python
iris=load_iris()
# 特征
iris_feature = iris.data
# 分类标签
iris_label = iris.target
# 随机数据集划分，为了验证算法的正确性，需要将数据分成训练数据和测试数据
X_train,X_test,Y_train,Y_test = train_test_split(iris_feature,iris_label,test_size=0.3,random_state=30)
```

* 训练与测试


```python
# 生成决策树
clf=tree.DecisionTreeClassifier()
# 训练
clf=clf.fit(X_train,Y_train)
# 预测
predict=clf.predict(X_test)
```

* 统计结果


```python
# 查看测试数据的预测值与真实值
print(predict)
print(Y_test)
# 获得预测准确率，本例是97.78%
print(accuracy_score(predict,Y_test))
```

    [0 0 0 2 1 1 2 2 1 2 0 2 1 1 0 1 0 0 0 1 2 0 0 0 2 2 2 2 0 1 2 1 2 2 2 2 1
     2 1 2 2 2 0 1 2]
    [0 0 0 2 1 1 2 2 1 2 0 2 1 1 0 1 0 0 0 1 2 0 0 0 2 2 1 2 0 1 2 1 2 2 2 2 1
     2 1 2 2 2 0 1 2]
    0.9777777777777777


*  输出决策树图


```python
# 输出结果图
dot_data = tree.export_graphviz(clf, out_file=None, 
                         feature_names=iris.feature_names,  
                         class_names=iris.target_names,  
                         filled=True, rounded=True,  
                         special_characters=True)
graph = pydotplus.graph_from_dot_data(dot_data)
# 以pdf文件形式输出决策树图
graph.write_pdf("irisresult.pdf")
```




    True



* 决策树图：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211021090412.png)