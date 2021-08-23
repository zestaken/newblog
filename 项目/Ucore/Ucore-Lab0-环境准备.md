# 1. 准备Linux环境

* 可以使用虚拟机安装如Ubuntu等，我使用的是购买云服务器安装Ubuntu操作系统，在云服务器中运行实验代码。

# 2. 一些必要工具

* Vim编辑器：Vim是一款极方便的文本编辑软件，是UNIX下的同类型软件VI的改进版本。
* exuberant-ctags ： exuberant-ctags 可以为程序语言对象生成索引，其结果能够被一个文本编辑器或者其他工具简捷迅速的定位。支持的编辑器有 Vim、Emacs 等。
* diff：为 Linux 命令，用于比较文本或者文件夹差异；
* patch：使用 patch 命令可以对文件或者文件夹应用修改。
* gcc编译环境：编译C程序，汇编等。
* make：GNU make(简称make)是一种代码维护工具，在大中型项目中，它将根据程序各个模块的更新情况，自动的维护和生成目标代码。
* gdb：gdb 是功能强大的调试程序，可完成如下的调试任务：设置断点，监视程序变量的值，程序的单步(step in/step over)执行，显示/修改变量的值，显示/修改寄存器，查看程序的堆栈情况，远程调试，调试线程。在可以使用 gdb 调试程序之前，必须使用 -g 或 –ggdb编译选项编译源文件。
* 硬件模拟器qemu：QEMU用于模拟一台x86计算机，让ucore能够运行在QEMU上。[qemu官网](http://wiki.qemu.org/Download)。下面是源码安装qemu过程：
  1. 下载源码：`git clone https://gitlab.com/qemu-project/qemu.git`
  2. 进入源码目录：`cd qemu`
  3. 初始化子模块：
     1. `git submodule init`
     2. `git submodule update --recursive`
  4. 编译安装：
     1. 配置qemu，可模拟X86-32硬件环境: `./configure --target-list="i386-softmmu"`
        1. 运行confgure时有很多问题，主要都是因为缺少了一些依赖的东西，根据报错提示安装好即可。（其中提示缺少"pixman-1"需要安装dev版本）
     2. 编译qemu: `make`(可以使用如`make -j4`进行多线程编译（4是线程数，可根据实际情况修改)
     3. 安装qemu：`sudo make install`
        1. 安装后仍然没有qemu命令，需要在`/usr/bin`目录中建立符号链接：`sudo ln -s /usr/bin/qemu-system-i386 /usr/bin/qemu`

# 做实验的方式

1. 将[实验代码](https://github.com/chyyuu/os_kernel_lab/tree/master)下载到Linux上:`git clone git@github.com:chyyuu/os_kernel_lab.git`
2. 其中labcodes中有按照实验1到8一次编号的文件夹，其中有我们每一次实验需要查看和修改的代码。labcodes_answer中是已经修改好可以使用的每个实验代码（参考答案）：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20210722225300.png)
3. 代码中需要我们修改添加的部分有注释批注
4. 修改好每个实验的代码后通过`make`编译，之后使用`make qemu`来检测是否能能够正常运行。



