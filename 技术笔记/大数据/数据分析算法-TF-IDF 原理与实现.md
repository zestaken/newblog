---
title: 数据分析算法-TF-IDF原理与实现
date: 2021-09-18 11:20:59
tags: [大数据, 机器学习算法, TF-IDF]
categories: 技术笔记
---

# TF-IDF简述

* **TF-IDF**(Term Frequency-Inverse Document Frequency, 词频-逆文件频率)是一种用于资讯检索与资讯探勘的常用加权技术。
* TF-IDF是一种统计方法，用以评估一字词对于一个文件集或一个语料库中的其中一份文件的重要程度。字词的重要性随着它在文件中出现的次数成正比增加，但同时会随着它在语料库中出现的频率成反比下降。即：一个词语在一篇文章中出现次数越多, 同时在所有文档中出现次数越少, 越能够代表该文章。
* **TF（Term Frequency）**：TF(Term Frequency, 词频)表示词条在文本中出现的频率，这个数字通常会被归一化(一般是词频除以文章总词数), 以防止它偏向长的文件（同一个词语在长文件里可能会比短文件有更高的词频，而不管该词语重要与否）。
* **IDF(Inverse Document Frequency)**: IDF表示关键词的普遍程度。如果包含词条的文档越少，IDF越大，则说明该词条具有很好的类别区分能力。某一特定词语的IDF，可以由总文件数目除以包含该词语之文件的数目，再将得到的商取对数得到
* 某一特定文件内的高词语频率，以及该词语在整个文件集合中的低文件频率，可以产生出高权重的TF-IDF。因此，TF-IDF倾向于过滤掉常见的词语，保留重要的词语。

# Python手动实现TF-IDF

[github地址](https://github.com/zestaken/newblog/tree/master/%E9%A1%B9%E7%9B%AE/pythonProject/TF-IDF)

1. 导入相应包


```python
from collections import Counter
import math
```

2. 创建语料库


```python
corpus = ['this is the first document',
          'this is the second second document',
          'and the third one',
          'is this the first document']
```

3. 使用列表将语料库拆为单词存储


```python
# 创建一个空列表与words_list = []等效
words_list = list()
# len(corpus)计算语料库容器的元素数量，即有4个字符串
# range生成一个连续的链表，如此处len(corpus)=4,就生成从0到3的连续链表，实质是使计数变量i从0到3递增变化
for i in range(len(corpus)):
    # spilt函数用指定的分隔符将字符串拆分为一个列表，将这个列表作为一个元素存入words_list列表
    words_list.append(corpus[i].split(' '))
print(words_list)
```

    [['this', 'is', 'the', 'first', 'document'], ['this', 'is', 'the', 'second', 'second', 'document'], ['and', 'the', 'third', 'one'], ['is', 'this', 'the', 'first', 'document']]


4. 统计每个词语在文档中出现次数


```python
# 统计词语数量
count_list = list()
for i in range(len(words_list)):
    # Counter统计列表的元素的数量并返回一个key：value列表，key是原来的元素值，value是这个元素出现的次数
    count = Counter(words_list[i])
    count_list.append(count)
print(count_list)
```

    [Counter({'this': 1, 'is': 1, 'the': 1, 'first': 1, 'document': 1}), Counter({'second': 2, 'this': 1, 'is': 1, 'the': 1, 'document': 1}), Counter({'and': 1, 'the': 1, 'third': 1, 'one': 1}), Counter({'is': 1, 'this': 1, 'the': 1, 'first': 1, 'document': 1})]


5. 定义计算tf、idf、tf-idf参数的函数


```python
# 定义函数
def tf(word, count):
    """计算某单词在文本中出现的频率除以该文本的总词数"""
    return count[word] / sum(count.values())


def idf(word, count_list):
    """计算某单词在多少文档中出现"""
    # 每次遍历到一个满足条件的元素就返回一个一
    # n_contain是这些1的和，代表了一个词语在几个文档中出现
    n_contain = sum([1 for count in count_list if word in count])
    # 某一特定词语的IDF，由总文件数目除以包含该词语之文件的数目，再将得到的商取对数得到
    # 加一是为了防止除数为0
    # 因为取了对数，idf值可能小于0
    return math.log(len(count_list) / (1 + n_contain))


def tf_idf(word, count, count_list):
    """结合tf、idf参数，计算tf-idf参数"""
    # 计算某词的综合参考参数tf——idf,即将二者相乘
    return tf(word, count) * idf(word, count_list)
```

6. 计算每个文档的每个单词的tf——idf参数，降序排列后输出


```python
# 输出结果
# enumerate函数将链表添加上下标，形如：[(0, "a"), (1, "b")]
for i, count in enumerate(count_list):
    print("第{}个文档的统计信息".format(i + 1))
    # 求取当前文档的每一个词语的tf_idf参数指标，每一个word的值由遍历count列表得到
    scores = {word: tf_idf(word, count, count_list) for word in count}
    # 用lambda表达式来定义用于比较的元素
    # x代表从迭代对象中得到的对象，x[1]表示实际用来比较的值
    # reverse = True表示降序排列
    sorted_word = sorted(scores.items(), key=lambda x: x[1], reverse=True)
    for word, score in sorted_word:
        # round函数表示只取5位小数
        print("\tword: {}, TF-IDF: {}".format(word, round(score, 5)))
```
```
    第1个文档的统计信息
    	word: first, TF-IDF: 0.05754
    	word: this, TF-IDF: 0.0
    	word: is, TF-IDF: 0.0
    	word: document, TF-IDF: 0.0
    	word: the, TF-IDF: -0.04463
    第2个文档的统计信息
    	word: second, TF-IDF: 0.23105
    	word: this, TF-IDF: 0.0
    	word: is, TF-IDF: 0.0
    	word: document, TF-IDF: 0.0
    	word: the, TF-IDF: -0.03719
    第3个文档的统计信息
    	word: and, TF-IDF: 0.17329
    	word: third, TF-IDF: 0.17329
    	word: one, TF-IDF: 0.17329
    	word: the, TF-IDF: -0.05579
    第4个文档的统计信息
    	word: first, TF-IDF: 0.05754
    	word: is, TF-IDF: 0.0
    	word: this, TF-IDF: 0.0
    	word: document, TF-IDF: 0.0
    	word: the, TF-IDF: -0.04463
```
