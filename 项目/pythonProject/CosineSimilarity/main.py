import numpy as np

# 创建语料库
corpus = ['this is the first document',
          'this is the second second document',
          'and the third one',
          'is this the first document']


# * 将句子拆分为单词

# 创建一个空列表与words_list = []等效
words_list = list()
# len(corpus)计算语料库容器的元素数量，即有4个字符串
# range生成一个连续的链表，如此处len(corpus)=4,就生成从0到3的连续链表，实质是使计数变量i从0到3递增变化
for i in range(len(corpus)):
    # spilt函数用指定的分隔符将字符串拆分为一个列表，将这个列表作为一个元素存入words_list列表
    words_list.append(corpus[i].split(' '))
print(words_list)


# * 得到文本中出现的所有词

# 获取文本中的所有词（不消除重复）
temp = list()
for words in words_list:
    temp += words
# 通过set集合的唯一性去除重复的词，并转换为列表
key_words = list(set(temp))
print(key_words)


# * 创建四个以所有词列表长度为长度的向量，用0来填充

word_vector1 = np.zeros(len(key_words))
word_vector2 = np.zeros(len(key_words))
word_vector3 = np.zeros(len(key_words))
word_vector4 = np.zeros(len(key_words))
print(word_vector1)


# * 将这四个向量组织到一个列表中，方便遍历

# 创建一个列表存储所有的向量
vectors = list()
vectors.append(word_vector1)
vectors.append(word_vector2)
vectors.append(word_vector3)
vectors.append(word_vector4)
print(vectors)


# * 遍历关键词列表和每个句子的单词列表，确定每个向量每个位置的值

for i in range(len(key_words)):
    for j in range(len(vectors)):
        for word in words_list[j]:
            if key_words[i] == word:
                vectors[j][i] += 1
print(key_words)
for vector in vectors:
    print(vector)


# * 计算余弦值比较其余三个句子和第一个句子的相似性

cosine12 = float( (np.sum(vectors[0] * vectors[1])) / (np.linalg.norm(vectors[0]) * np.linalg.norm(vectors[1])))
print(cosine12)

cosine13 = float( (np.sum(vectors[0] * vectors[2])) / (np.linalg.norm(vectors[0]) * np.linalg.norm(vectors[2])))
print(cosine13)

cosine14 = float( (np.sum(vectors[0] * vectors[3])) / (np.linalg.norm(vectors[0]) * np.linalg.norm(vectors[3])))
print(cosine14)


# * 根据余弦值的大小得出结论9越大，相似度越高）句子3和句子1相似度最高，句子2和句子1相似度最低。而人为判断也是这样。





