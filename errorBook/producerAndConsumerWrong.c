#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>
#include <stdlib.h>

int fd; //文件描述符 
int in = 0, out = 0;
char buffer1[1]; //存放生产者从文件中读取的字符
char buffer[4]; //缓冲区，大小为3
int count = 1; //生产者每次从文件中读取一个字符
sem_t empty;
sem_t full;
pthread_mutex_t mutex;

void producer(void *args) {

    int i = *((int *)args) + 1;

    for (int j = 0; j < 1; j++){
    sem_wait(&empty);
    pthread_mutex_lock(&mutex);

    //每次读取一个字符，写入缓冲区
    read(fd, buffer1, count);
    buffer[in] = buffer1[0];
    in = (in + 1) % 4;
    printf("[tid](%ld)-[produer](%d)write to buffer success\n", (long)pthread_self(), i);

    pthread_mutex_unlock(&mutex);
    sem_post(&full);
    }

}

void consumer(void *args) {

    int i = *((int *)args) + 1;
    for(int j = 0; j < 3; j++) {
    sem_wait(&full);
    pthread_mutex_lock(&mutex);

    //从缓冲区读出数据
    printf("[tid](%ld)-[consumer](%d): read from buffer success(%c)\n", (long)pthread_self(), i, buffer[out]);
    out = (out + 1) % 4;

    pthread_mutex_unlock(&mutex);
    sem_post(&empty);
    }
}

int main() {

    pthread_mutex_init(&mutex,NULL); //初始化互斥量

    sem_init(&empty, 0, 3); //初始化信号量empty为3
    sem_init(&full, 0, 0); //初始化信号量full为0

    //主线程打开要读取的文件，获取文件描述符
    fd = open("resource.txt", O_RDONLY);

    pthread_t producers[4];
    pthread_t consumers[4];
    int args[4];

    //创建三个生产者线程

    for(int i = 0; i < 4; i++) {
        args[i] = i;
        pthread_create(&producers[i], NULL, (void*)producer, (void*)&args[i]);
    }

    //创建4个消费者线程
    for(int i = 0; i < 4; i++) {
        args[i] = i;
        pthread_create(&consumers[i], NULL, (void *)consumer, (void*)&args[i]);
    }


    //主线程等待生产者线程结束 
    for(int i = 0; i < 3; i++) {
        pthread_join(producers[i], NULL);
    }
    //漏掉了一个生产者线程没有join，导致后面的的消费者线程变成单线程执行

    //主线程等待消费者线程结束
    for(int i = 0; i < 4; i++) {
        pthread_join(consumers[i], NULL);
    }

    //主线程退出
    exit(0);
}