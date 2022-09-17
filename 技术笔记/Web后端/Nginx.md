---
title: Nginx
tags:
  - Nginx
  - 后端
categories:
  - 技术笔记
  - Web后端
abbrlink: 9355
date: 2020-10-07 17:53:06
---

# Nginx概述

[官方文档](http://nginx.org/en/docs/)
* Nginx(engine x)是一款轻量级的Web 服务器/反向代理服务器及电子邮件（IMAP/POP3）代理服务器，在BSD-like 协议下发行。其特点是占有内存少，并发能力强，事实上nginx的并发能力在同类型的网页服务器中表现较好，中国大陆使用nginx网站用户有：百度、京东、新浪、网易、腾讯、淘宝等。
* Nginx的应用场景：
  1. http服务器：Nginx是一个http服务器，可以独立提供http服务，可以用作网页静态服务器。
  2. 虚拟主机：可以实现在一台服务器虚拟出多个网站。
  3. 反向代理，负载均衡：当网站的访问量达到一定程度后，单台服务器不能满足用户的请求时，需要使用多台服务器集群时，可以使用Nginx做反向代理，并且多台服务器可以平均分担负载（即负载均衡），不会出现某台服务器负载高而宕机，而另外的某台服务器闲置的情况。

# Nginx在linux下的安装

[参考文档](https://www.nginx.cn/install)

1. 准备编译环境(Ubuntu系列）：
```shell
apt-get install build-essential
apt-get install libtool
```
2. 安装PCRE库：
   1. [官网下载](https://ftp.pcre.org/pub/pcre/)
   2. 解压到`/usr/local/src`下，需要sudo权限（在这个目录下的操作大多都需要sudo权限）sd
   3. 运行configure文件，生成makefile文件
   4. `make`
   5. `sudo make install`,需要sudo权限。
3. 安装Zlib库：
   1. [官网下载](http://zlib.net/)
   2. 解压到`/usr/local/src`下，需要sudo权限
   3. 运行configure文件，生成makefile文件
   4. `make`
   5. `sudo make install`,需要sudo权限
4. 安装ssl
   1. [官网下载](https://www.openssl.org/source/)
   2. 解压到`/usr/local/src`下，需要sudo权限
5. 安装Nginx
   1. [官网下载](http://nginx.org/en/download.html)
   2. 解压到`/usr/local/src`下，需要sudo权限
   3. 运行configure文件，生成makefile文件，不过要配置一些参数(其中依赖库的版本要与自己安装的对应）：
```shell
./configure --sbin-path=/usr/local/nginx/nginx \
--conf-path=/usr/local/nginx/nginx.conf \
--pid-path=/usr/local/nginx/nginx.pid \
--with-http_gzip_static_module \
--with-http_stub_status_module \
--with-file-aio \
--with-http_realip_module \
--with-http_ssl_module \
--with-pcre=/usr/local/src/pcre-8.44 \
--with-zlib=/usr/local/src/zlib-1.2.11 \
--with-openssl=/usr/local/src/openssl-1.1.1i
```
   4. `make -j2`，用两个线程编译，这一步用时稍微要久一点
   5. `make install`。
* 安装好后，会在`/usr/local`目录下生成nginx目录
* 直接运行nginx目录下的nginx文件，即可启动nginx服务器，默认为80端口，可直接用本机的ip地址访问该服务器。

# 常用控制命令

* `./nginx -s stop`:中止服务器，非正常关闭;
* `./nginx -s quit`:正常关闭服务器;
* `./nginx -s reload`:重新加载服务器的配置文件(在服务器启动的情况下才能重新加载)
* `./nginx -s reopen`:重新打开日志文件

# Nginx部署静态页面

* 在`/usr/local/nginx`目录下，创建一个存储静态资源的目录，比如说`/usr/local/nginx/data`
* 之后修改`/usr/local/nginx`目录下的`nginx.conf`文件的server模块。
```conf
http {
    include       mime.types;
    default_type  application/octet-stream;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;

    server {
        listen       80;# 默认端口号
        server_name  localhost; # 域名或者ip

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / { 
            root    data # 默认访问资源目录 
            index  index.html index.htm; #默认访问资源文件名
        }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html; # 错误资源
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }
```

# 配置虚拟主机

* 虚拟主机可以理解为在同一个服务器里面部署多个项目。
* 只需要在`nginx.conf`文件中，增加`server`模块即可。
* 端口绑定：
  * 即修改server模块中的listen选项，用不同的端口号来访问不同的资源。
* 域名绑定：
  * 一个ip可以对应多个域名，所以本机的ip地址可以设置多个对应的域名（需要修改本机的hosts文件）
  * 然后修改server模块的server_name为不同的域名，即可以用不同的域名来访问不同的资源。

# Nginx的反向代理和负载均衡

* **正向代理(代理)**:意思是一个位于客户端和原始服务器(origin server)之间的服务器，为了从原始服务器取得内容，客户端向代理发送一个请求并指定目标(原始服务器)，然后**代理向原始服务器转交请求并将获得的内容返回给客户端**。客户端才能使用正向代理。
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20210203122231.jpeg)
* **反向代理**:反向代理服务器位于用户与目标服务器之间，但是对于用户而言，反向代理服务器就相当于目标服务器，即用户**直接访问反向代理服务器就可以获得目标服务器的资源**。同时，用户不需要知道目标服务器的地址，也无须在用户端作任何设定。反向代理服务器通常可用来作为Web加速，即使用反向代理作为Web服务器的前置机来降低网络和服务器的负载，提高访问效率。
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20210203122317.jpeg)

## 配置Nginx的反向代理

* 修改`nginx.conf`配置文件;
```conf
  http {
    include       mime.types;
    default_type  application/octet-stream;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;

    upstream tomcat1 { # tomcat1是为被代理服务器自己取的名字
       server 192.168.177.129:8080 # 被代理服务器的ip地址和端口
    }

    server {
        listen       80;# 默认端口号
        server_name  localhost; # 域名或者ip

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / { 
            root    data # 默认访问资源目录 
            proxy_pass   http://tomcat1; # 被代理服务器的的名字作为后面部分
            index  index.html index.htm; #默认访问资源文件名
        }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html; # 错误资源
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }
```

## 配置Nginx的负载均衡

* 修改`nginx.conf`的配置文件：
```conf
  http {
    include       mime.types;
    default_type  application/octet-stream;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;

    #如果配置了多台服务器在这里面，nginx会按照权重值分配对这三台服务器的访问，如果没有设置，则权重值默认为1
    upstream tomcats { # tomcats是为被代理服务器自己取的名字
       server 192.168.177.129:8080 weight = 2; # 被代理服务器的ip地址和端口
       server 192.168.177.129:8081;# 被代理服务器的ip地址和端口
       server 192.168.177.129:8082; # 被代理服务器的ip地址和端口
    }

    server {
        listen       80;# 默认端口号
        server_name  localhost; # 域名或者ip

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / { 
            root    data # 默认访问资源目录 
            proxy_pass   http://tomcats; # 被代理服务器的的名字作为后面部分
            index  index.html index.htm; #默认访问资源文件名
        }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html; # 错误资源
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }

