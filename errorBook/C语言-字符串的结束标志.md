# 字符串结束标志

* 众所周知（除了本人）C语言中字符串的实质就是一个字符数组，但是字符串这个字符数组有点特殊，它的最后一位一定是`'\0'`（即空字符）用来标识字符串的结束。
* C语言最令人头痛事情之一访问越界，特别是数组访问越界，如果发生了是什么样子？答案马上揭晓。。。

# 错误示范

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

# 探索越界之谜

## 编译器

* 首先怀疑的是越界之后的结果和不同的编译器有关。
* MacOS虽然明面上使用的是gcc命令编译，但是实质上用的是clang（LLVM clang）。
![jbwrRj](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/jbwrRj.png)
* 对于当前主流桌面操作系统而言，可使用 Visual C++、GCC 以及 LLVM Clang 这三大编译器。
  * Visual C++（简称 MSVC）是由微软开发的，只能用于 Windows 操作系统；
  * GCC 和 LLVM Clang 除了可用于 Windows 操作系统之外，主要用于 Unix/Linux 操作系统。
* 