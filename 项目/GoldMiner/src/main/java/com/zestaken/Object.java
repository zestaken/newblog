package com.zestaken;

import java.awt.*;

/**
 * 金块石块等物体的父类
 * @author zestaken
 */
public class Object {
    //坐标
    int x;
    int y;
    //宽高，把所有物体看作是一个矩形，宽高用于抓取判定
    int width;
    int height;
    //图片
    Image img;
    //判断金块是否被抓取可以移动的状态标记
    boolean flag = false;
    //质量属性，用于影响抓取物体后的线返回速度
    int m;
    //积分属性，用于计算玩家获得的积分
    int score;
    //标志是金块（0）还是石块（1）
    int type;

    /**
     * 绘制图片
     * @param g
     */
    void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    /**
     * 获取物体的矩形对象
     * @return Rectangle是java.awt包中的对象，自带检测矩形的方法，只需传入坐标和长宽即可生成
     */
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
