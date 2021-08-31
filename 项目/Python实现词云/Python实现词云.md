---
title: Python实现词云
date: 2021-06-13 08:00:19
tags: [Python, 深度学习]
categories: 项目
---

# 准备工作

* 什么是词云：
  * “词云”就是通过形成“关键词云层”或“关键词渲染”，对网络文本中出现频率较高的“关键词”的视觉上的突出。
  * 示例：
	![](https://gitee.com/zhangjie0524/picgo/raw/master/20210613142756.png)
* wordcloud包：wordcloud是优秀的词云展示第三方库，[官网](http://amueller.github.io/word_cloud/index.html)
* Matplotlib：是 Python 的绘图库。 它可与 NumPy 一起使用，提供了一种有效的 MatLab 开源替代方案。[官网](https://matplotlib.org/)
* jieba: jieba库是一款优秀的 Python 第三方中文分词库.[github地址](https://github.com/fxsjy/jieba)
* PIL: Python图像库PIL(Python Image Library)是python的第三方图像处理库，但是由于其强大的功能与众多的使用人数，几乎已经被认为是python官方图像处理库了。
* 安装以上库：`pip install wordcloud matplotlib jieba pil`，如果使用pycharm的话，也可以不装，在使用到的时候被报错提醒再装。

# 针对英文文本简单尝试

```python
# 导入相关包
from wordcloud import WordCloud
import matplotlib.pyplot as plt

# 打开要读的文档
text = open('English.txt').read()

# 生成wordcloud对象
wc = WordCloud().generate(text)

# 根据wordCloud对象绘图
plt.imshow(wc, interpolation='bilinear') #绘制图片 interpolation是设置图片的效果
plt.axis("off") #关闭坐标轴
plt.show()	#展示结果图

# 将生成的wordcloud对象转化为图片文件存储
wc.to_file("wordcloud.png")
```
* 效果：
  ![](https://gitee.com/zhangjie0524/picgo/raw/master/20210613153856.png)
* 文本：
```
Everybody has to face bad days in their life. Some face ailments, some face fears, some face poverty, some face helplessness etc etc. No one can escape from it. It is a rule of life. Two things always combines together like joy and sadness, laugh and cry, health and illness, profit and loss, morning and night, confidence and inferiority complex, victory and failure likewise good days and bad days.
```

# WordCloud()的参数

1. width:指定词云对象生成图片的宽度,默认400像素,`w=wordcloud.WordCloud(width=600)`
2. height:指定词云对象生成图片的高度,默认200像素 `w=wordcloud.WordCloud(height=400)`
3. min_font_size:指定词云中字体的最小字号，默认4号 `w=wordcloud.WordCloud(min_font_size=10)`
4. max_font_size:指定词云中字体的最大字号，根据高度自动调节, `w=wordcloud.WordCloud(max_font_size=20)`
5. font_step:指定词云中字体字号的步进间隔，默认为1, `w=wordcloud.WordCloud(font_step=2)`
6. font_path:指定文体文件的路径，默认None, `w=wordcloud.WordCloud(font_path="msyh.ttc")`
7. max_words:指定词云显示的最大单词数量,默认200, `w=wordcloud.WordCloud(max_words=20)`
9. stop_words:指定词云的排除词列表，即不显示的单词列表, `w=wordcloud.WordCloud(stop_words="Python")`
10. mask:蒙版，指定词云形状，默认为长方形，需要读取图片来设置
```python
from scipy.msc import imread
mk=imread("pic.png")
w=wordcloud.WordCloud(mask=mk)
```
11. background_color:指定词云图片的背景颜色，默认为黑色, w=wordcloud.WordCloud(background_color="white")
12. mode: 颜色模式，默认为RGB模式，如果为RGBA模式且background_color设置为None，则背景将透明。

# 中文词云

* 因为英文文本用空格分隔，所以不用分词，但是中文文本需要进行分词处理，才能使结果有意义。同时还需要注意编码方式的指定。同时也要注意**wrodcloud**的中文字体的指定
```python
from wordcloud import WordCloud
import matplotlib.pyplot as plt
import jieba

# 打开要读的文档
file = open('kyj.txt', encoding='utf-8').read()

# 用jieba库来分词
file = ' '.join(jieba.cut(file))
# 生成wordcloud对象
wc = WordCloud(font_path='2.ttf').generate(file) #一定要指定中文字体，不然无法显示

# 根据wordCloud对象绘图
plt.imshow(wc, interpolation='bilinear') #绘制图片 interpolation是设置图片的效果
plt.axis("off") #关闭坐标轴
plt.show()	#展示结果图

# 将生成的wordcloud对象转化为图片文件存储
wc.to_file("wordcloud2.png")
```
* 分词的效果：
  ![](https://gitee.com/zhangjie0524/picgo/raw/master/20210613171040.png)

# 蒙版的使用

* 需要一张蒙版图片，最后词云会对应图片中白色的部分不显示内容，黑色的部分显示内容
  * 原始图片
	![](https://gitee.com/zhangjie0524/picgo/raw/master/20210613173258.jpg)
  * 词云样式：
	![](https://gitee.com/zhangjie0524/picgo/raw/master/20210613173325.png)
* 代码
```python
from wordcloud import WordCloud
import matplotlib.pyplot as plt
import jieba
import numpy as np
from PIL import Image

# 打开要读的文档
file = open('kyj.txt', encoding='utf-8').read()

file = ' '.join(jieba.cut(file))
print(file)
# 生成wordcloud对象
mask = np.array(Image.open("3.jpg")) #根据图片生成mask，实质是个数组
wc = WordCloud(font_path='2.ttf', mask=mask, mode='RGBA', background_color=None).generate(file)

# 根据wordCloud对象绘图
plt.imshow(wc, interpolation='bilinear') #绘制图片 interpolation是设置图片的效果
plt.axis("off") #关闭坐标轴
plt.show()	#展示结果图

# 将生成的wordcloud对象转化为图片文件存储
wc.to_file("wordcloud2.png")
```

# 词云跟随图片色彩

* 需要导入wordcloud的ImageColorGenerator()方法
* 代码：
```python
from wordcloud import WordCloud
import matplotlib.pyplot as plt
import jieba
import numpy as np
from PIL import Image
from wordcloud import ImageColorGenerator

# 打开要读的文档
file = open('kyj.txt', encoding='utf-8').read()

file = ' '.join(jieba.cut(file))
print(file)
# 生成wordcloud对象
mask = np.array(Image.open("3.jpg")) #根据图片生成mask，实质是个数组
wc = WordCloud(font_path='2.ttf', mask=mask, mode='RGBA', background_color=None).generate(file)

# 让词云跟随图片色彩
image_colors = ImageColorGenerator(mask) # 获取图片各个部分的颜色
wc.recolor(color_func=image_colors) #将这些颜色设置给wordcloud对象

# 根据wordCloud对象绘图
plt.imshow(wc, interpolation='bilinear') #绘制图片 interpolation是设置图片的效果
plt.axis("off") #关闭坐标轴
plt.show()	#展示结果图

# 将生成的wordcloud对象转化为图片文件存储
wc.to_file("wordcloud2.png")
```
* 效果；
  ![](https://gitee.com/zhangjie0524/picgo/raw/master/20210613174516.png)

# 去掉无实意的词

* 有一些词如：“的，一些”等，虽然出现频率很高，但并没意义。所以我们需要去掉这些词，这在wordcloud中叫做停用词。
* 对于英文：
  * wordcloud自带了一个停用词表，是一个集合数据类型。
  * 没有停用词
	![](https://gitee.com/zhangjie0524/picgo/raw/master/20210613184511.png)
  * 有停用词：
	![](https://gitee.com/zhangjie0524/picgo/raw/master/20210613184706.png)
  * 代码：需要引入wordcloud的STOPWORDS
```python
from wordcloud import WordCloud
import matplotlib.pyplot as plt
import jieba
import numpy as np
from PIL import Image
from wordcloud import ImageColorGenerator
from wordcloud import STOPWORDS


# 打开要读的文档
file = open('English.txt', encoding='utf-8').read()

# file = ' '.join(jieba.cut(file))
print(file)
# 生成wordcloud对象
# 停用词
stopwords = STOPWORDS #获取wordcloud自带的停用词表
stopwords.add("T") # 可以自己在这个停用词集合中添加词
mask = np.array(Image.open("3.jpg")) #根据图片生成mask，实质是个数组
wc = WordCloud(stopwords=stopwords,font_path='2.ttf', mask=mask, mode='RGBA', background_color=None).generate(file)

# 让词云跟随图片色彩
image_colors = ImageColorGenerator(mask) # 获取图片各个部分的颜色
wc.recolor(color_func=image_colors) #将这些颜色设置给wordcloud对象



# 根据wordCloud对象绘图
plt.imshow(wc, interpolation='bilinear') #绘制图片 interpolation是设置图片的效果
plt.axis("off") #关闭坐标轴
plt.show()	#展示结果图

# 将生成的wordcloud对象转化为图片文件存储
wc.to_file("wordcloud2.png")
```
* 对于中文，原理一样，但是需要自己去准备停用词表，[下载链接](https://github.com/goto456/stopwords)
  * 没有停用词
	  ![](https://gitee.com/zhangjie0524/picgo/raw/master/20210613174516.png)
  * 有停用词
	![](https://gitee.com/zhangjie0524/picgo/raw/master/20210613185335.png)
  * 代码：
```python
from wordcloud import WordCloud
import matplotlib.pyplot as plt
import jieba
import numpy as np
from PIL import Image
from wordcloud import ImageColorGenerator



# 打开要读的文档
file = open('kyj.txt', encoding='utf-8').read()

file = ' '.join(jieba.cut(file))
print(file)

# 生成wordcloud对象
# 停用词
stopwords = set() #设为集合
content = [line.strip() for line in open('cn_stopwords.txt',encoding='utf-8').readlines()] #从停用词表中获取数据
stopwords.update(content) #将停用词表存入集合中
mask = np.array(Image.open("3.jpg")) #根据图片生成mask，实质是个数组
wc = WordCloud(stopwords=stopwords,font_path='2.ttf', mask=mask, mode='RGBA', background_color=None).generate(file)

# 让词云跟随图片色彩
image_colors = ImageColorGenerator(mask) # 获取图片各个部分的颜色
wc.recolor(color_func=image_colors) #将这些颜色设置给wordcloud对象


# 根据wordCloud对象绘图
plt.imshow(wc, interpolation='bilinear') #绘制图片 interpolation是设置图片的效果
plt.axis("off") #关闭坐标轴
plt.show()	#展示结果图

# 将生成的wordcloud对象转化为图片文件存储
wc.to_file("wordcloud2.png")
```