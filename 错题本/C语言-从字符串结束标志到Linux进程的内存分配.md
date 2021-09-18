---
title: C语言-从字符串结束标志到Linux进程的内存分配
tags: C
categories: 专业基础
date: 2021-06-01 16:55:28
---

# C语言-从字符串结束标志到Linux进程的内存分配

## 字符串结束标志

* 众所周知（除了本人）C语言中字符串的实质就是一个字符数组，但是字符串这个字符数组有点特殊，它的最后一位一定是`'\0'`（即空字符）用来标识字符串的结束。
* C语言最令人头痛事情之一访问越界，特别是数组访问越界，如果发生了是什么样子？答案马上揭晓。。。

## 错误示范

* 尘封已久的C语言现在被我拿来干了啥可悲的事情呢？
```c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//整个程序的目的是依次读入16位的数（4个16进制数），然后累加，中途结果超出16位则去掉最高位再在末尾加一，如果读取的数不够4位16进制数，则在末尾补零到4位
int main(int argc, char *argv[]) {
    u_int32_t sum = 0;

    char *data = argv[1];
    char temp[4];
    int i = 0;
    int flag = 0;
    while(data[i] != '\0' && flag == 0) {
        for(int j = 0; j < 4; j++) {
            if(data[i + j] != '\0') {
                temp[j] = data[i + j];
            } else {
                //错误点：如果还差2位才到4位，那么第一次读到'\0'之后的一次循环在干什么呢？那岂不是直接超出数组边界，在访问一个未定义的空间
                    temp[j] = '0';     
                    flag = 1;
            }
        }
        if(flag == 0) {
            i = i  + 4;
        }
        sum += strtol(temp, NULL, 16);
        if(sum > 0xFFFF) {
            sum = sum - 0x10000 + 0x0001;
        }
    }
     //测试越界后得到的最后四位字符是什么
     printf("最后四位字符：");
     for(i = 0; i < 4; i++) {
             printf("%c",temp[i]);
     } 
     printf("\n");
    u_int16_t checksum = ~sum;
    printf("%04x\n", checksum);
}
```
* 我这个铁憨憨不仅忘了字符串只有最后一位是`'\0'`，而且还很自然地实现了越界访问（菜，是一种怎样的忧伤）。
* 看一看越界后会有什么效果：
![J5zLVA](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/J5zLVA.png)
  * 所以T和E是两个什么玩意儿？虽然它们很不正常，但是为什么每次越界后得到的都是T和E？越界之后的结果不应该是随机的？
  * 本次测试使用的是MacOS操作系统，clang 11.0.0编译器

## 探索越界之谜

### 编译器

* 首先怀疑的是越界之后的结果和不同的编译器有关，因为每次越界的结果都一样。
* MacOS虽然明面上使用的是gcc命令编译，但是实质上用的是clang（LLVM clang）。
![jbwrRj](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/jbwrRj.png)
* 对于当前主流桌面操作系统而言，可使用 Visual C++、GCC 以及 LLVM Clang 这三大编译器。
  * Visual C++（简称 MSVC）是由微软开发的，只能用于 Windows 操作系统；
  * GCC 和 LLVM Clang 除了可用于 Windows 操作系统之外，主要用于 Unix/Linux 操作系统。
* 给Mac电脑装一个真正的gcc试试：(`brew install gcc`)
![Bh7lSd](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/Bh7lSd.png)
  * 用gcc编译之后再运行：
  ![h0YSc8](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/h0YSc8.png)
  * 结果竟然一模一样！难道不是编译器不同导致的!

### 越界之后的结果查看

* 之所以怀疑是编译器的问题主要是之前认为编译器会自动识别程序的越界访问，然后用一些固定的字符来提示用户，现在恍然大悟，我怕是想多了。（我的专业课可能上了个寂寞）
* 打印越界之后的更多信息试一试：
```c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//整个程序的目的是依次读入16位的数（4个16进制数），然后累加，中途结果超出16位则去掉最高位再在末尾加一，如果读取的数不够4位16进制数，则在末尾补零到4位
int main(int argc, char *argv[]) {
    u_int32_t sum = 0;

    char *data = argv[1];
    char temp[4];
    int i = 0;
    int flag = 0;
    while(data[i] != '\0' && flag == 0) {
        for(int j = 0; j < 4; j++) {
            if(data[i + j] != '\0') {
                temp[j] = data[i + j];
            } else {
                //错误点：如果还差2位才到4位，那么第一次读到'\0'之后的一次循环在干什么呢？那岂不是直接超出数组边界，在访问一个未定义的空间
                /**打印越界之后的信息**/
		    printf("%dover%d:%s\n",i, j,&data[i+j]);
		    printf("%dvaluei%d:%c\n",i, j,data[i+j]);
                    temp[j] = '0';     
                    flag = 1;
            }
        }
            /**打印越界之后的信息**/
		    printf("%dover4:%s\n", i ,&data[i+4]);
		    printf("%dvalue4:%c\n",i, data[i+4]);
		    printf("%dover5:%s\n", i, &data[i+5]);
		    printf("%dvalue5:%c\n",i, data[i+5]);
		    printf("%dover6:%s\n",i,  &data[i+6]);
		    printf("%dvalue6:%c\n",i, data[i+6]);
		    printf("%dover7:%s\n",i, &data[i+7]);
		    printf("%dvalue7:%c\n",i,data[i+7]);

        if(flag == 0) {
            i = i  + 4;
        }
        sum += strtol(temp, NULL, 16);
        if(sum > 0xFFFF) {
            sum = sum - 0x10000 + 0x0001;
        }
    }
     //测试越界后得到的最后四位字符是什么
     printf("最后四位字符：");
     for(i = 0; i < 4; i++) {
             printf("%c",temp[i]);
     } 
     printf("\n");
    u_int16_t checksum = ~sum;
    printf("%04x\n", checksum);
}
```
  * 运行结果如图：
    ![rnHYOO](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/rnHYOO.png)
* 真是拨云见日，在data数组之后存放的是一个字符串`TERM_SESSION_ID=w0t0p0:6389D1E3-6A5A-4F0C-9AAB-7BBF04CC0875`,每次越界之后读取的就是这个字符串中的内容了，T和E这两个奇怪的东西就是这样来的。
* 但是还有一个问题，为什么每次运行程序的结果都一样，我的data数组紧邻的为什么就一定是这个奇怪的字符串。

### Linux的全局环境变量

* 使用`printenv`或`env`命令可以查看linux的全局环境变量（mac也适用),部分全局环境变量截图如下：
![BSX2O1](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/BSX2O1.png)
* 全局变量的第一条便是`TERM_SESSION_ID=w0t0p0:6389D1E3-6A5A-4F0C-9AAB-7BBF04CC0875`，看来我们越界之后极有可能访问到来存储全局环境变量字符串的空间。
* 感觉有点不放心，在网上看了一种能直接在程序内查看当前进程环境变量的方法：
```c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//整个程序的目的是依次读入16位的数（4个16进制数），然后累加，中途结果超出16位则去掉最高位再在末尾加一，如果读取的数不够4位16进制数，则在末尾补零到4位
int main(int argc, char *argv[]) {

    //。。。
    //省略原来的代码
    // ...

    //查看当前进程的环境变量
    printf("-------------------------\n");
    extern char **environ;
    for(int i = 0; environ[i]!= NULL; i++){
  	 printf("%s\n",environ[i]);
   }
   return 0;
}
```
  * 结果：
    ![ZNXP0L](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/ZNXP0L.png)
    * 也出现了`TERM_SESSION_ID=w0t0p0:6389D1E3-6A5A-4F0C-9AAB-7BBF04CC0875`而且也在第一个。
    * 当前进程的环境变量应该是继承而来的。

### Linux的内存分配

* 通过前面的尝试发现，在我定义的数组之后应该存储的是进程的环境变量。所以，linux中，一个进程的内存究竟是如何组织的？
* 进程的内存组织：
![h0oHPe](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/h0oHPe.png)
* 如果是按照这种组织，那么我在进程运行时创建的数组应该在堆中，那么才有可能访问到紧邻的静态数据。堆的数据一般是通过malloc分配的。
* 不太确定这种方式定义的数组是在堆中分配的，将我们的数组用malloc分配试一试。
  * 原来：
  ```c
  char *data = argv[1]
  ```
  * 使用malloc动态分配：
  ```c
    char *data = (char *)malloc(sizeof(argv[1]));
    data = argv[1];
  ```
![ZH5ILP](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/ZH5ILP.png)
  * 结果和原来一模一样，越界之后依然是`TERM_SESSION_ID=w0t0p0:6389D1E3-6A5A-4F0C-9AAB-7BBF04CC0875`。那么证明了，我创建的字符数组，是存放在堆中的。
* 哪些数据存放在哪些位置：
  * `.text`（代码)段：程序源代码编译后得到的机器指令放在这个地方。也就是说是它是可执行程序在内存中的镜像。代码段需要防止在运行时被非法修改，所以只准许读取操作，而不允许写入（修改）操作——它是不可写的。
  * `.data`:数据段用来存放可执行文件中已初始化全局变量，换句话说就是存放程序已初始化的局部静态分配的变量和全局变量。
  * `.bss`: 为未初始化的全局变量和局部静态变量预留位置。在内存中的bss段全部置零。
  * **堆（heap）**：堆是用于存放进程运行中被动态分配的内存段，它的大小并不固定，可动态扩张或缩减。当进程调用malloc等函数分配内存时，新分配的内存就被动态添加到堆上（堆被扩张）；当利用free等函数释放内存时，被释放的内存从堆中被剔除（堆被缩减）。
  * **栈（stack）**：栈是用户存放程序临时创建的局部变量，也就是说我们函数括弧“{}”中定义的变量（但不包括static声明的变量，static意味着在数据段中存放变量）。除此以外，在函数被调用时，其参数也会被压入发起调用的进程栈中，并且待到调用结束后，函数的返回值也会被存放回栈中。由于栈的先进先出特点，所以栈特别方便用来保存/恢复调用现场。从这个意义上讲，我们可以把堆栈看成一个寄存、交换临时数据的内存区。
  * **共享库的内存映射区域**：存放一些共享的对象，如动态链接库。
![r2gQFe](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/r2gQFe.png)
