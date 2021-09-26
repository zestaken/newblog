---
title: Arch
date: 2021-03-07 09:37:25
tags: [Linux, Arch]
categories: 技术笔记
---

# Arch虚拟机安装

[参考Archwiki](https://wiki.archlinux.org/index.php/Installation_guide)
1. 在[官网](https://archlinux.org/download/)选择一个镜像源下载iso文件。
2. 在VMware安装iso镜像；
3. 启动虚拟机后，经过一会儿的等待，会自动进入命令行界面；
4. arch安装需要连接网络，检查网络是否畅通，可使用`ping`命令：
   ![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20210307094657.png)
5. 更新系统时间：`timedatectl set-ntp true`
6. 检查引导模式：` ls /sys/firmware/efi/efivars`
   1. 如果显示目录，则是EFI模式；
   2. 如果有错，不显示，则是BIOS模式
   ![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20210307101020.png)
7. 分区：
   1. 查看目前分区情况:`fdisk -l`
    ![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20210307101409.png)
   2. 创建分区：`fdisk /dev/sda
    ![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20210307102000.png)
   3. 格式化分区：
      1. 格式化swap分区，并启动：`mkswap /dev/sda1`,`swapon /dev/sda1`
        ![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20210307102440.png)
      2. 将另外一个大容量的分区，格式化为ETX4格式,并挂载到`/mnt`目录：`mkfs.ext4 /dev/sda2`,`mount /dev/sda2 /mnt`
8. 安装软件包：
   1. 配置镜像源：暂时省略,因为现在在live环境中使用reflector进行镜像的管理，貌似你一连接网络，live系统会自动执行reflector命令来帮你选择镜像源，默认的是根据下载速率进行排序，
   2. 安装基本包：`pacstrap /mnt base linux linux-firmware`，
      1. 这里要注意的是，上面的命令并不包括所有的基本程序，如网络管理程序、文本编辑器等，如果你想安装这些程序，可以将名字添加到pacstrap后，并用空格隔开。你也可以在Chroot进新系统后使用`pacman`手动安装软件包或组。
9. 配置系统:
   1. 生成自动挂载分区的fstab文件:`genfstab -U /mnt >> /mnt/etc/fstab`
    ![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20210307105154.png)
   2. 切换到新系统,Chroot意为Change root，相当于把操纵权交给我们新安装（或已经存在）的Linux系统，执行了这步以后，我们的操作都相当于在磁盘上新装的系统中进行。`arch-chroot /mnt`
    ![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20210307105356.png)
   3. 更改时区，依次执行如下命令设置我们的时区为上海并生成相关文件：`ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime`,`hwclock --systohc`
   4. 安装一个vim，便于之后的操作：`pacman -S vim`
   5. 设置Locale，即语言选项,执行如下命令来编辑/etc/locale.gen文件：`vim /etc/locale.gen`
      1. 在文件中找到`en_US.UTF-8 UTF-8`,`zh_CN.UTF-8 UTF-8`,`zh_HK.UTF-8 UTF-8`,`zh_TW.UTF-8 UTF-8` 这四行，去掉行首的#号，保存并退出。
      2. 然后执行：`locale-gen`
      3. 打开（不存在时会创建）`/etc/locale.conf`文件,在文件的第一行加入以下内容：`LANG=en_US.UTF-8`
   6. 网络配置：
      1. 设置主机名：打开/etc/hostname文件,在文件的第一行输入你自己设定的一个myhostname(我的是zestaken)
      2. 编辑`etc/hosts`文件，在文件末添加如下内容：
       ```
       127.0.0.1	localhost
       ::1		    localhost
       127.0.1.1	myhostname.localdomain	myhostname
       ```
       3. 安装dhcpcd,dhcpd 是 Internet Systems Consortium DHCP 的服务，它被用作局域网环境中的路由管理。`pacman -S dhcpcd`
       4. 启动dhcpcd服务：`systemctl enable dhcpcd.service`
    7. 设置ROOT用户密码：
       1. `#`代表以Root用户执行命令，``$代表以普通用户执行命令
       2. 通过`passwd`设置ROOT密码，我设置的是`1`.
    8. 安装引导工具Grub2:
       1. 首先安装os-prober和ntfs-3g这两个包，它可以配合Grub检测已经存在的系统，自动设置启动选项:`pacman -S os-prober ntfs-3g`
       2. 如果为BIOS/MBR引导方式:
          1. 安装grub包：`pacman -S grub`
          2. 部署grub：`grub-install --target=i386-pc /dev/sdx` （将sdx换成你安装的硬盘）注意这里的sdx应该为硬盘（例如/dev/sda），而不是形如/dev/sda1这样的分区。
          3. 生成配置文件：`grub-mkconfig -o /boot/grub/grub.cfg`
    9. 退出新安装好的系统：`exit`
    10. 写在已经挂载好的分区：`umount -R /mnt`
    11. 重启:`reboot`
    12. 再次打开会有如下页面,输入root，再输入之前设置的密码，显示出命令提示符，恭喜你，你已经成功安装ArchLinux！
        ![](https://gitee.com/zhangjie0524/picgo/raw/master/img/20210307113916.png)

# 美化Arch

* 安装xorg服务，xorg服务是linux桌面的硬件接口，所有的窗管都是x窗口的实现：`pacman -S xorg xterm xorg-xinit`
* 安装显示管理器：显示管理器也就是Linux启动之后的启动界面。世界上有很多个显示管理器，这里使用sddm，一个不错的显示管理器。
  * 安装：`pacman -S sddm`
  * 设置开机自启动：`systemctl enable sddm.service`
* 安装KDE（Plasma）桌面：
  * `pacman -S plasma kde-applications`
* 重启之前先创建用户(使用默认设置）：`useradd -m username`





