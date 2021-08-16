package com.zestaken;

import java.awt.*;

public class Rock extends Object {
    Rock() {
        //出现坐标随机获取，以减少多个石块出现在同一个地方重叠的现象(限制随机范围，以控制石块出现位置)
        //石块出现坐标
        this.x = (int) (Math.random() * 700);
        this.y = (int) (Math.random() * 550 + 200);
        //石块长宽
        this.width = 71;
        this.height = 71;
        this.m = 80;
        //石块图片
        this.img = Toolkit.getDefaultToolkit().createImage("imgs/rock1.png");
    }
}
