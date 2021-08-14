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
    //宽高
    int width;
    int height;
    //图片
    Image img;

    /**
     * 绘制图片
     * @param g
     */
    void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }
}
