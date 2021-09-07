#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//整个程序的目的是依次读入16位的数（4个16进制数），然后累加，中途结果超出16位则去掉最高位再在末尾加一，如果读取的数不够4位16进制数，则在末尾补零到4位
int main(int argc, char *argv[]) {
    u_int32_t sum = 0;

    char *data = argv[1];
    for(int i = 0; i < 7; i++) {
	    
    	data[i] = argv[1][i];
    }
    char temp[4];
    int i = 0;
    int flag = 0;
    while(data[i] != '\0' && flag == 0) {
        for(int j = 0; j < 4; j++) {
            if(data[i + j] != '\0') {
                temp[j] = data[i + j];
            } else {
                //错误点：如果还差2位才到4位，那么第一次读到'\0'之后的一次循环在干什么呢？那岂不是直接超出数组边界，在访问一个未定义的空间
		    printf("%dover%d:%s\n",i, j,&data[i+j]);
		    printf("%dvaluei%d:%c\n",i, j,data[i+j]);
                    temp[j] = '0';     
                    flag = 1;
            }
        }
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

    //查看当前进程的环境变量
    printf("-------------------------\n");
    extern char **environ;
    for(int i = 0; environ[i]!= NULL; i++){
  	 printf("%s\n",environ[i]);
   }
   return 0;
}
