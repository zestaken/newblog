---
title: 自动同步github脚本
date: 2020-12-29 21:00:23
tags:
---
# 起因

* 因为在寝室使用一台Windows写笔记，出门用一台Mac写笔记，这就需要使这两台设备的笔记内容始终同步，于是采用将笔记同步到github的方式来解决这个问题。
* 但是，因为经常会在一台电脑上写好了笔记之后忘记上传，然后又在另一台电脑上对相同的笔记进行了修改，导致了笔记的冲突问题，每次合并冲突都很麻烦，所以决定写脚本来实现关机自动上传和开机自动拉取。

# Windows

* 编写push脚本
```bat
@echo off 

start cmd /c "cd C:\02Permanent\Blog\source && git push orgin master"
```

    * `@echooff`:关闭所有命令的显示（@关闭其后命令的显示）
    * `start`: 是用来启动一个应用的，使用方式为：start 程序名
    * `cmd /c`:表示执行完cmd命令后关闭命令窗口
    *  `cmd /k`:表示执行完cmd命令后不关闭命令窗口
    * 要依次执行多条cmd命令，不同命令之间用`&&`连接，并将命令放在双引号中。
* 设置关机自动执行
  * 使用 组策略： gpedit.msc，windows+r搜索gpedit.msc
  * 之后进入：
![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20201229211947.webp)

* 编写pull脚本
```bat
@echo off

start cmd /c "cd C:\02Permanent\Blog\source && git pull orgin master"
```
* 设置开机自动运行：
  * 将bat文件放在：C:\Users\Administrator\AppData\Roaming\Microsoft\Windows\Start Menu\Programs\Startup下，开机即可自动运行。

**开关机自动执行均失败，未能找到可行办法。。。**