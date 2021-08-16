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

    /**
     * 绘制图片
     * @param g
     */
    void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }
}
