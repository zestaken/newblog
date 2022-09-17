---
layout: w
title: docker
date: 2020-11-23 16:34:04
tags: [后端, Docker]
categories: 技术笔记
---

# Docker概述

[参考教程](https://www.runoob.com/docker/docker-tutorial.html)
* Docker 是一个开源的应用容器引擎，基于 Go 语言 并遵从 Apache2.0 协议开源。
* Docker的应用场景：
  * Web 应用的自动化打包和发布。
  * 自动化测试和持续集成、发布。
  * 在服务型环境中部署和调整数据库或其他的后台应用。
  * 从头编译或者扩展现有的 OpenShift 或 Cloud Foundry 平台来搭建自己的 PaaS 环境。

## Docker架构

* Docker 包括三个基本概念:
  * 镜像（Image）：Docker 镜像（Image），就相当于是一个 root 文件系统。比如官方镜像 ubuntu:16.04 就包含了完整的一套 Ubuntu16.04 最小系统的 root 文件系统。
  * 容器（Container）：镜像（Image）和容器（Container）的关系，就像是面向对象程序设计中的类和实例一样，镜像是静态的定义，容器是镜像运行时的实体。容器可以被创建、启动、停止、删除、暂停等。
  * 仓库（Repository）：仓库可看成一个代码控制中心，用来保存镜像。
* Docker 使用客户端-服务器 (C/S) 架构模式，使用远程API来管理和创建Docker容器。
* Docker 容器通过 Docker 镜像来创建。容器与镜像的关系类似于面向对象编程中的对象与类。

# Docker安装

[参考资料](https://www.runoob.com/docker/ubuntu-docker-install.html)

# Docker的基础使用

## 运行helloworld

* 命令：`docker run ubuntu:15.10 /bin/echo "Hello world"`
  * docker: Docker 的二进制执行文件。
  * run: 与前面的 docker 组合来运行一个容器。
  * ubuntu:15.10 指定要运行的镜像，Docker 首先从本地主机上查找镜像是否存在，如果不存在，Docker 就会从镜像仓库 Docker Hub 下载公共镜像。
  * /bin/echo "Hello world": 在启动的容器里执行的命令。
* 以上命令完整的意思可以解释为：Docker 以 ubuntu15.10 镜像创建一个新容器，然后在容器里执行 bin/echo "Hello world"，然后输出结果。

## 运行交互式容器

* 我们通过 docker 的两个参数 -i -t，让 docker 运行的容器实现"对话"的能力；
  * -t: 在新容器内指定一个伪终端或终端。
  * -i: 允许你对容器内的标准输入 (STDIN) 进行交互。
* 命令示例：`docker run -i -t ubuntu:15.10`，-i，-t两个参数必须同时使用，如果只使用-t则会只有一个终端界面，但是输入命令不会有反应。
* 我们可以通过运行 exit 命令或者使用 CTRL+D 来退出容器。

## 启动容器（后台模式）

* 命令:`docker run -d ubuntu:15.10 /bin/echo "helloworld"`
  * 在输出中，我们没有看到期望的 "hello world"，而是一串长字符串2b1b7a428627c51ab8810d541d759f072b4fc75487eed05812646b8534a2fe63。这个长字符串叫做容器 ID，对每个容器来说都是唯一的，我们可以通过容器 ID 来查看对应的容器发生了什么。
* 确认容器有在运行，可以通过 docker ps 来查看：
  * 输出详情介绍：
    * CONTAINER ID: 容器 ID。
    * IMAGE: 使用的镜像。
    * COMMAND: 启动容器时运行的命令。
    * CREATED: 容器的创建时间。
    * STATUS: 容器状态。
      * 状态有7种：
        * created（已创建）
        * restarting（重启中）
        * running 或 Up（运行中）
        * removing（迁移中）
        * paused（暂停）
        * exited（停止）
        * dead（死亡）
    * PORTS: 容器的端口信息和使用的连接类型（tcp\udp）。
    * NAMES: 自动分配的容器名称。
* 在宿主主机内使用 docker logs 命令，查看容器内的标准输出：
  * 示例：`docker logs 2b1b7a428627`(这串数字是容器id的前面一部分)
* 我们使用 docker stop 命令来停止容器：
  * 示例：`docker stop 2b1b7a428627`

# Docker容器使用

## Docker客户端

* docker 客户端非常简单 ,我们可以直接输入 `docker `命令来查看到 Docker 客户端的所有命令选项。
* 可以通过命令 `docker <command> --help `更深入的了解指定的 Docker 命令使用方法。例如我们要查看 docker stats 指令的具体使用方法：`docker stats --help`

## 容器的使用

### 获取镜像

* 如果我们本地没有 ubuntu 镜像，我们可以使用 docker pull 命令来载入 ubuntu 镜像：`docker pull ubuntu`

### 启动容器

* 以下命令使用 ubuntu 镜像启动一个容器，参数为以命令行模式进入该容器：` docker run -it ubuntu /bin/bash`;
* 参数说明：
  * -i: 交互式操作。
  * -t: 终端。
  * ubuntu: ubuntu 镜像。
  * /bin/bash：放在镜像名后的是命令，这里我们希望有个交互式 Shell，因此用的是 /bin/bash。（省略/bin/bash也能达到同样的效果）
* 要退出终端，直接输入 exit;
* docker的许多命令都需要使用`sudo`权限；

### 启动已停止的容器

* 查看所有的容器命令：`docker ps -a`,不加参数名，则只有表头，没有具体容器的信息。
* 查看最后一次创建的容器`docker ps -l`。
* 使用 docker start 启动一个已停止的容器：`docker start b750bbbcfd88`,最后那串数字是容器id的开头一部分，终端会自动识别出整个容器ID。但是这样容器启动后并不会输出相应的数据，我们能看见的只是终端上**打印出的容器id**.

### 后台运行

* 在大部分的场景下，我们希望 docker 的服务是在后台运行的，我们可以过参数`-d` 指定容器的运行模式为后台运行。
* 加了`-d` 参数默认不会进入容器，想要进入容器需要使用指令 `docker exec`
* 后台运行的指令成功后，终端只会打印容器的id。示例:`docker run -itd --name ubuntu-test ubuntu /bin/bash`

### 停止容器

* 停止容器的命令如下：`docker stop <容器 ID>`
* 停止的容器和正在运行的容器可以通过 docker restart 重启：`docker restart <容器 ID>`

### 进入容器

* 在使用 -d 参数时，容器启动后会进入后台。此时想要进入容器，可以通过以下指令进入：
* `docker attach`：
  * 示例：`docker attach 1e560fca3906 `,没有参数之类的东西，直接使用容器id进入即可。
* `docker exec`：推荐大家使用 docker exec 命令，因为使用此命令在退出容器终端后，不会导致容器的停止。
  * 示例：`docker exec -it 243c32535da7 /bin/bash`,就像普通打开一个容器一样，docker exec命令后要有完整的参数，容器id和运行的命令（此处的/bin/bash）不可省略，退出终端仍然使用exit。

### 导出和导入容器

* 如果要导出本地某个容器，可以使用`docker export` 命令。
  * 示例:`docker export 1e560fca3906 > ./test/ubuntu.tar`,导出容器 1e560fca3906 快照到本地文件 ubuntu.tar。
* 可以使用`docker import` 从容器快照文件中再导入为镜像，以下实例将快照文件 ubuntu.tar 导入到镜像 test/ubuntu:v1:`cat test/ubuntu.tar | sudo docker import - ubuntu1`.
* 此外，也可以通过指定 URL 或者某个目录来导入，例如：`docker import http://example.com/exampleimage.tgz example/imagerepo`
* 导出和导入的实际上都是镜像，要使用的话，还需根据镜像创建容器。

### 删除容器

* 删除容器使用 docker rm 命令，删除的容器必须是终止的，否则会报错。
  * 示例：`docker rm -f 1e560fca390`,这串数字是容器id。
* 下面的命令可以清理掉所有处于终止状态的容器:`docker container prune`

## web应用使用

### 运行一个web应用

* 我们尝试使用 docker 构建一个 web 应用程序。我们将在docker容器中运行一个 Python Flask 应用来运行一个web应用。
* 载入镜像：`docker pull training/webapp`  
* 后台运行：`docker run -d -P training/webapp python app.py`
  * 参数说明:
    * -d:让容器在后台运行。
    * -P:将容器内部使用的网络端口随机映射到我们使用的主机上。

### 查看web应用容器

* 使用`sudo docker ps -a`来查看web应用容器。
  * 这里多了端口信息。
```
PORTS
0.0.0.0:32769->5000/tcp
```
  * Docker 开放了 5000 端口（默认 Python Flask 端口）映射到主机端口 32769 上。这时我们可以通过浏览器访问WEB应用. 在浏览器地址栏输入`localhost:32769`即可访问web应用。
* 我们也可以通过`-p` 参数来设置不一样的端口：` docker run -d -p 5000:5000 training/webapp python app.py`容器内部的 5000 端口映射到我们本地主机的 5000 端口上。 
* 查看端口号的快捷方式：`docker port <容器id>/<容器名>`来直接查看容器的端口的映射情况。

### 查看web应用的日志信息

* `docker logs <容器id>/<容器名>` 可以查看容器内部的标准输出。示例：
```
docker logs -f bf08b7f2cd89
 * Running on http://0.0.0.0:5000/ (Press CTRL+C to quit)
192.168.239.1 - - [09/May/2016 16:30:37] "GET / HTTP/1.1" 200 -
192.168.239.1 - - [09/May/2016 16:30:37] "GET /favicon.ico HTTP/1.1" 404 -
```
* -f: 让 docker logs 像使用 tail -f 一样来输出容器内部的标准输出。

### 查看web应用容器的进程

* 我们还可以使用 `docker top <容器id>/<容器名> ` 来查看容器内部运行的进程。

### 检查web应用程序

* 使用`docker inspect <容器id>/<容器名>` 来查看 Docker 的底层信息。它会返回一个 JSON 文件记录着 Docker 容器的配置和状态信息.


# Docker镜像使用

* 当运行容器时，使用的镜像如果在本地中不存在，docker 就会自动从 docker 镜像仓库中下载，默认是从 Docker Hub 公共镜像源下载。

## 列出镜像列表

* 我们可以使用 `docker images` 来列出本地主机上的镜像。
```
runoob@runoob:~$ docker images           
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
ubuntu              14.04               90d5884b1ee0        5 days ago          188 MB
php                 5.6                 f40e9e0f10c8        9 days ago          444.8 MB
nginx               latest              6f8d099c3adc        12 days ago         182.7 MB
mysql               5.6                 f2e8d6c772c0        3 weeks ago         324.6 MB
httpd               latest              02ef73cf1bc0        3 weeks ago         194.4 MB
ubuntu              15.10               4e3b13c8a266        4 weeks ago         136.3 MB
hello-world         latest              690ed74de00f        6 months ago        960 B
training/webapp     latest              6fae60ef3446        11 months ago       348.8 MB
```
* 各个选项说明:
  * REPOSITORY：表示镜像的仓库源
  * TAG：镜像的标签
    * 同一仓库源可以有多个 TAG，代表这个仓库源的不同个版本，如 ubuntu 仓库源里，有 15.10、14.04 等多个不同的版本，我们使用 REPOSITORY:TAG 来定义不同的镜像。
    * 如果你不指定一个镜像的版本标签，例如你只使用 ubuntu，docker 将默认使用 ubuntu:latest 镜像。
  * IMAGE ID：镜像ID
  * CREATED：镜像创建时间
  * SIZE：镜像大小

## 获取新镜像

* 当我们在本地主机上使用一个不存在的镜像时 Docker 就会自动下载这个镜像。如果我们想预先下载这个镜像，我们可以使用 `docker pull <镜像名>:<标签>`命令来下载它。

## 查找镜像

* 我们可以从 Docker Hub 网站来搜索镜像，Docker Hub 网址为： https://hub.docker.com/
* 我们也可以使用`docker search <镜像名>` 命令来搜索镜像。比如我们需要一个 httpd 的镜像来作为我们的 web 服务。我们可以通过 `docker search httpd` 来寻找适合我们的镜像。
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20201125085937.jpg)
* 选项说明：
  * NAME: 镜像仓库源的名称
  * DESCRIPTION: 镜像的描述
  * OFFICIAL: 是否 docker 官方发布
  * stars: 类似 Github 里面的 star，表示点赞、喜欢的意思。
  * AUTOMATED: 自动构建。

## 删除镜像

* 镜像删除使用`docker rmi <镜像名>:<标签>` 命令，比如我们删除 hello-world 镜像：`docker rmi hello-world`,省略标签，则默认只会删除标签为latest的镜像。

## 创建镜像

* 当我们从 docker 镜像仓库中下载的镜像不能满足我们的需求时，我们可以通过以下两种方式对镜像进行更改。
  1. 从**已经创建的容器中更新镜像**，并且提交这个镜像;
  2. 使用 **Dockerfile 指令**来创建一个新的镜像;

### 更新镜像

* 更新镜像之前，我们需要使用镜像来**创建一个容器**。
* 示例：
  * `docker run -t -i ubuntu:15.10 /bin/bash`
  * 在运行的容器内使用 apt-get update 命令进行更新。
  * 在完成操作之后，输入 exit 命令来退出这个容器。
  * 此时 ID 为 e218edb10161 的容器，是按我们的需求更改的容器。我们可以通过命令`docker commit`来提交容器副本。
  * `docker commit -m="has update" -a="runoob" e218edb10161 runoob/ubuntu:v2`
* `docker commit`的各个参数说明：
  * -m: 提交的描述信息
  * -a: 指定镜像作者
  * e218edb10161：容器 ID
  * runoob/ubuntu:v2: 指定要创建的目标镜像名

### 构建镜像

* 我们使用命令`docker build` ， 从零开始来创建一个新的镜像。为此，我们需要创建一个`Dockerfile`文件，其中包含一组指令来告诉 Docker 如何构建我们的镜像。
* 示例：
```
runoob@runoob:~$ cat Dockerfile 
FROM    centos:6.7
MAINTAINER      Fisher "fisher@sudops.com"

RUN     /bin/echo 'root:123456' |chpasswd
RUN     useradd runoob
RUN     /bin/echo 'runoob:123456' |chpasswd
RUN     /bin/echo -e "LANG=\"en_US.UTF-8\"" >/etc/default/local
EXPOSE  22
EXPOSE  80
CMD     /usr/sbin/sshd -D
```
* 每一个指令都会在镜像上创建一个新的层，每一个指令的前缀都必须是大写的。
* 第一条FROM，指定使用哪个**镜像源**
* RUN 指令告诉docker 在镜像内执行命令，安装了什么。。。
* 然后，我们使用Dockerfile 文件，通过 docker build 命令来构建一个镜像。
```
runoob@runoob:~$ docker build -t runoob/centos:6.7 .
Sending build context to Docker daemon 17.92 kB
Step 1 : FROM centos:6.7
 ---&gt; d95b5ca17cc3
Step 2 : MAINTAINER Fisher "fisher@sudops.com"
 ---&gt; Using cache
 ---&gt; 0c92299c6f03
Step 3 : RUN /bin/echo 'root:123456' |chpasswd
 ---&gt; Using cache
 ---&gt; 0397ce2fbd0a
Step 4 : RUN useradd runoob
......
```
* 参数说明：
  * -t ：指定要创建的目标镜像名
  * . ：Dockerfile 文件所在目录，可以指定Dockerfile 的绝对路径

## 设置镜像标签

* 我们可以使用`docker tag`命令，为镜像添加一个新的标签。
* 示例：`docker tag 860c279d2fec runoob/centos:dev`
* `docker tag 镜像ID 用户名称/镜像源名(repository name):新的标签名(tag)`

# Docker容器连接

* 容器中可以运行一些网络应用，要让外部也可以访问这些应用，可以通过 `-P` 或 `-p` 参数来指定端口映射。

## 网络端口映射

* 创建了一个 python 应用的容器：`docker run -d -P training/webapp python app.py`
* 我们使用 `-P` 参数创建一个容器，使用 docker ps 可以看到容器端口 5000 绑定主机端口 32768。
```
runoob@runoob:~$ docker ps
CONTAINER ID    IMAGE               COMMAND            ...           PORTS                     NAMES
fce072cc88ce    training/webapp     "python app.py"    ...     0.0.0.0:32768->5000/tcp   grave_hopper
```
* 我们也可以使用 `-p` 标识来指定容器端口绑定到主机端口。
* 两种方式的区别是:
  * `-P` :是容器内部端口**随机**映射到主机的高端口。
  * `-p` : 是容器内部端口**绑定**到指定的主机端口,需要自己指定映射关系(如果不指定，则会报错）。
  * `docker run -d -p 5000:5000 training/webapp python app.py`
* 另外，我们可以指定容器绑定的**网络地址**，比如绑定 127.0.0.1：` docker run -d -p 127.0.0.1:5001:5000 training/webapp python app.py`.
* 上面的例子中，默认都是绑定 tcp 端口，如果要绑定 UDP 端口，可以在端口后面加上 `/udp`。`docker run -d -p 127.0.0.1:5000:5000/udp training/webapp python app.py`。

## Docker容器互联

* 端口映射并不是唯一把docker 连接到另一个容器的方法。docker 有一个连接系统允许将多个容器连接在一起，共享连接信息。docker 连接会创建一个父子关系，其中父容器可以看到子容器的信息。

### 容器命名

* 当我们创建一个容器的时候，docker 会**自动对它进行命名**。另外，我们也可以使用 `--name`标识来命名容器，例如：`docker run -d -P --name runoob training/webapp python app.py`

### 新建网络

* 创建一个新的 Docker 网络:`docker network create -d bridge test-net`
* 参数说明：
  * -d：参数指定 Docker 网络类型，有 bridge、overlay。
  * `docker nerwork create`是固定的指令
  * `test-net`是网络的名称。
* 通过`docker network ls`来查看已经存在的网络。

### 连接容器

* 运行一个容器并连接到新建的 test-net 网络:`docker run -itd --name test1 --network test-net ubuntu /bin/bash`
  * `--network`参数指定要连接的网络名。
* 打开新的终端，再运行一个容器并加入到 test-net 网络:`docker run -itd --name test2 --network test-net ubuntu /bin/bash`
* 下面通过 `ping` 来证明 test1 容器和 test2 容器建立了互联关系。
  * 如果 test1、test2 容器内中无 ping 命令，则在容器内执行以下命令安装 ping（即学即用：可以在一个容器里安装好，提交容器到镜像，在以新的镜像重新运行以上两个个容器）。
```shell
apt-get update
apt install iputils-ping
```
* 在test1容器内输入`ping test2`（test2中同理），即可连接。

* 如果有多个容器之间需要互相连接，推荐使用 Docker Compose。

### 配置DNS

* 我们可以在宿主机的 /etc/docker/daemon.json 文件中增加以下内容来设置全部容器的 DNS：
```
{
  "dns" : [
    "114.114.114.114",
    "8.8.8.8"
  ]
}
```
* 设置后，启动容器的 DNS 会自动配置为 114.114.114.114 和 8.8.8.8。配置完，需要重启 docker 才能生效。
* 查看容器的 DNS 是否生效可以使用以下命令，它会输出容器的 DNS 信息：`docker run -it --rm  ubuntu  cat etc/resolv.conf`
* 如果只想在**指定的容器设置 DNS**，则可以使用以下命令：`docker run -it --rm -h host_ubuntu  --dns=114.114.114.114 --dns-search=test.com ubuntu`
* 参数说明：
  * --rm：容器退出时自动清理容器内部的文件系统。
  * -h HOSTNAME 或者 --hostname=HOSTNAME： 设定容器的主机名，它会被写到容器内的 /etc/hostname 和 /etc/hosts。
  * --dns=IP_ADDRESS： 添加 DNS 服务器到容器的 /etc/resolv.conf 中，让容器用这个服务器来解析所有不在 /etc/hosts 中的主机名。
  * --dns-search=DOMAIN： 设定容器的搜索域，当设定搜索域为 .example.com 时，在搜索一个名为 host 的主机时，DNS 不仅搜索` host，还会搜索 host.example.com。
* 如果在容器启动时没有指定 --dns 和 --dns-search，Docker 会默认用宿主主机上的 /etc/resolv.conf 来配置容器的 DNS。

# Docker仓库管理

* 仓库（Repository）是集中存放镜像的地方。以下介绍一下 Docker Hub。当然不止 docker hub，只是远程的服务商不一样，操作都是一样的。

## DockerHub

* 目前 Docker 官方维护了一个公共仓库 Docker Hub。大部分需求都可以通过在 Docker Hub 中直接下载镜像来实现。

### 注册

* 在 [DockerHub](https://hub.docker.com) 免费注册一个 Docker 账号。

### 登录和退出

* 登录需要输入用户名和密码，登录成功后，我们就可以从 docker hub 上拉取自己账号下的全部镜像。`docker login`;
* 退出 docker hub 可以使用以下命令：`docker logout`

### 查找和拉取镜像

* 你可以通过 docker search 命令来查找官方仓库中的镜像，并利用 docker pull 命令来将它下载到本地。
* 查找：`docker search <镜像名>`,必须带有镜像名；
* 拉取：`docker pull <镜像名>`.

### 推送镜像

* 在登录进了dockerhub的前提下：
* 首先需要将本地的镜像名改为`(dockerhub的用户名)/镜像名`的格式。示例：`docker tag ubuntu:18.04 zestaken/ubuntu:18.04`(其中zestaken是我的dockerhub用户名)。
* 之后再使用`docker push <镜像名>`将镜像推送到dockerhub。
* 被自己推送到自己仓库的镜像可以直接使用`docker pull <镜像名>`拉取下来，但是search不能够搜到(因为开启这个功能需要花钱升级dockerhub账号到pro版）。

# Dockerfile

# Dockermachine

# docker安装的Ubuntu问题

* 通过镜像安装的ubuntu容器为纯净的环境，其好多命令执行不了，即缺少很多可执行脚本，安装即可~~~

执行一切安装之前请先执行 apt-get update

1. lsb_release

apt-get install lsb-release

2. ifconfig

apt install net-tools

3. ping

apt install iputils-ping

4. sudo

apt-get install sudo

5. vim 

apt-get install vim 

6. add-apt-repository

sudo apt-get install software-properties-common 

同理可安装git ,Python等

（

 apt-get install git python-virtualenv libssl-dev libffi-dev build-essential libpython-dev python2.7-minimal authbind 
）

