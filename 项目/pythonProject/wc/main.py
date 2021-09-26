from wordcloud import WordCloud
import matplotlib.pyplot as plt
import jieba
import numpy as np
from PIL import Image

# 打开要读的文档
file = open('resources/kyj.txt', encoding='utf-8').read()

file = ' '.join(jieba.cut(file))
print(file)

# 生成wordcloud对象
# 停用词
stopwords = set() #设为集合
content = [line.strip() for line in open('resources/cn_stopwords.txt', encoding='utf-8').readlines()] #从停用词表中获取数据
stopwords.update(content) #将停用词表存入集合中
mask = np.array(Image.open("resources/3.jpg")) #根据图片生成mask，实质是个数组
wc = WordCloud(stopwords=stopwords, font_path='resources/2.ttf', mask=mask, mode='RGBA', background_color=None).generate(file)

# 让词云跟随图片色彩
# image_colors = ImageColorGenerator(mask) # 获取图片各个部分的颜色
# wc.recolor(color_func=image_colors) #将这些颜色设置给wordcloud对象


# 根据wordCloud对象绘图
plt.imshow(wc, interpolation='bilinear') #绘制图片 interpolation是设置图片的效果
plt.axis("off") #关闭坐标轴
plt.show()	#展示结果图

# 将生成的wordcloud对象转化为图片文件存储
wc.to_file("resources/wordcloud2.png")
