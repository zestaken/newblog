from collections import Counter
import math


# 创建语料库
corpus = ['this is the first document',
        'this is the second second document',
        'and the third one',
        'is this the first document']
# 创建一个空列表与words_list = []等效
words_list = list()
# len(corpus)计算语料库容器的元素数量，即有4个字符串
# range生成一个连续的链表，如此处len(corpus)=4,就生成从0到3的连续链表，实质是使计数变量i从0到3递增变化
for i in range(len(corpus)):
    # spilt函数用指定的分隔符将字符串拆分为一个列表，将这个列表作为一个元素存入words_list列表
    words_list.append(corpus[i].split(' '))
print(words_list)

# 统计词语数量
count_list = list()
for i in range(len(words_list)):
    # Counter统计列表的元素的数量并返回一个key：value列表，key是原来的元素值，value是这个元素出现的次数
    count = Counter(words_list[i])
    count_list.append(count)
print(count_list)

# 定义函数
def tf(word, count):
    """计算某单词在文本中出现的频率除以该文本的总词数"""
    return count[word] / sum(count.values())

def idf(word, count_list):
    # 每次遍历到一个满足条件的元素就返回一个一，n_contain是这些1的和，代表了
    n_contain = sum([1 for count in count_list if word in count])
    print("n_contain:{}".format(n_contain))
    return math.log(len(count_list) / (1 + n_contain))

def tf_idf(word, count, count_list):
    return tf(word, count) * idf(word, count_list)

# 输出结果

for i, count in enumerate(count_list):
    print("第{}个文档的统计信息".format(i + 1))
    scores = {word : tf_idf(word, count, count_list) for word in count}
    sorted_word = sorted(scores.items(), key = lambda x : x[1], reverse = True)
    for word, score in sorted_word:
        print("\tword: {}, TF-IDF: {}".format(word, round(score, 5)))

