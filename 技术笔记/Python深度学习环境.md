---
title: Python深度学习环境
date: 2021-06-06 08:02:19
tags: [Python, 深度学习]
categories: 技术笔记
---

# Anaconda

* 简介：
  * Anaconda包括Conda、Python以及一大堆安装好的180多个科学包及其依赖项，比如：numpy、pandas等。
  * Anaconda 是在 conda（一个包管理器和环境管理器）上发展出来的。
  * conda是一个开源的包、环境管理器，可以用于在同一个机器上安装不同版本的软件包及其依赖，并能够在不同的环境之间切换。
  * 如果计算机上已经安装了 Python，安装Anaconda不会对你有任何影响。实际上，脚本和程序使用的默认 Python 是 Anaconda 附带的 Python，所以安装完Anaconda已经自带安装好了Python，不需要你再安装Python了。
* 安装 Anaconda:
  1. Anaconda 可用于多个平台（ Windows、Mac OS X 和 Linux）。你可以在[官网](https://docs.anaconda.com/anaconda/install/)上找到安装程序和安装说明。根据你的操作系统是32位还是64位选择对应的版本下载。
  2. 启动下载的Anaconda安装器(如果是windows 10系统，注意在安装Anaconda软件的时候，右击安装软件→选择以管理员的身份运行。)
* 最终效果：
![](https://gitee.com/zhangjie0524/picgo/raw/master/20210613111434.png)

# Jupyter notebook

* Jupyter notebook（http://jupyter.org/） 是一种 Web 应用，能让用户将说明文本、数学方程、代码和可视化内容全部组合到一个易于共享的文档中。
* 安装Jupyter notebook：安装 Jupyter 的最简单方法是使用 Anaconda。该发行版附带了 Jupyter notebook。你能够在默认环境下使用 notebook。所以在安装好了Anaconda之后，我们已经有了Jupyter。

# Pycharm

* PyCharm是一种Python IDE（Integrated Development Environment，集成开发环境），带有一整套可以帮助用户在使用Python语言开发时提高其效率的工具，比如调试、语法高亮、Project管理、代码跳转、智能提示、自动完成、单元测试、版本控制。做一个项目还是需要Pycharm。
* [官网](https://www.jetbrains.com/zh-cn/pycharm/)下载安装，用学生邮箱白嫖专业版。

# tensorflow

* [tensorflow官网](https://tensorflow.google.cn/)
* 简介：TensorFlow 是由 Google Brain 团队为深度神经网络（DNN）开发的功能强大的开源软件库，它在图形分类、音频处理、推荐系统和自然语言处理等场景下有着丰富的应用，除了 Python，TensorFlow 也提供了 C/C++、Java、Go、R 等其它编程语言的接口。
* 安装：`pip install tensorflow-gpu`，因为电脑有一张Nvidia的GTX 1660 ti的显卡，所以使用GPU版本的tensorflow。
* 因为要使用NVIDA的显卡，所以得安装CUDA和CUDNN：[官网](https://developer.nvidia.com/cuda-downloads) 
  * CUDA(ComputeUnified Device Architecture)，是显卡厂商NVIDIA推出的运算平台。 CUDA是一种由NVIDIA推出的通用并行计算架构，该架构使GPU能够解决复杂的计算问题.
    * CUDA可以在官网获得一个安装程序，运行安装即可。	
  * NVIDIA cuDNN是用于深度神经网络的GPU加速库。它强调性能、易用性和低内存开销。NVIDIA cuDNN可以集成到更高级别的机器学习框架中，如谷歌的Tensorflow、加州大学伯克利分校的流行caffe软件。[下载CUDNN](https://developer.nvidia.com/cudnn-download-survey)
    * CUDNN下载获得一个压缩文件，解压缩后将CUDNN中的文件复制到CUDA对应的目录中去。
	![](https://gitee.com/zhangjie0524/picgo/raw/master/20210613132548.png)	
  * 将`C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v11.3\lib\x64`添加到环境变量
* 使用`import tensorflow as tf`检查tensorflow是否安装好。




