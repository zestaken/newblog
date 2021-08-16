package com.zestaken;

import java.awt.*;

public class Gold extends Object {
    Gold() {
        //出现坐标随机获取，以防止多个金块出现在同一个地方重叠(限制随机范围，以控制金块出现位置)
        //金块出现坐标
        this.x = (int) (Math.random() * 700);
        this.y = (int) (Math.random() * 550 + 200);
        //金块长宽
        this.width = 52;
        this.height = 52;
        //金块图片
        this.img = Toolkit.getDefaultToolkit().createImage("imgs/gold1.gif");
    }
}
