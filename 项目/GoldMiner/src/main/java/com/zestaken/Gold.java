package com.zestaken;

import java.awt.*;


public class Gold extends Object {
    Gold() {
        //出现坐标随机获取，以减少多个金块出现在同一个地方重叠的现象(限制随机范围，以控制金块出现位置)
        //金块出现坐标
        this.x = (int) (Math.random() * 700);
        this.y = (int) (Math.random() * 550 + 200);
        //金块长宽
        this.width = 52;
        this.height = 52;
        this.m = 30;
        //金块图片
        this.img = Toolkit.getDefaultToolkit().createImage("imgs/gold1.gif");
    }
}
//todo 一个文件中多个类的应用
class GoldMini extends Gold {
    GoldMini() {
        //重置一些参数
        this.width = 36;
        this.height = 36;
        this.m = 15;
        //金块图片
        this.img = Toolkit.getDefaultToolkit().createImage("imgs/gold0.gif");
    }
}

class GoldPlus extends Gold {
    GoldPlus() {
        //因为比较大，需要调整出现的范围
        this.x = (int) (Math.random() * 600);
        this.y = (int) (Math.random() * 550 + 200);
        //重置一些参数
        this.width = 105;
        this.height = 105;
        this.m = 60;
        //金块图片
        this.img = Toolkit.getDefaultToolkit().createImage("imgs/gold2.gif");
    }
}
