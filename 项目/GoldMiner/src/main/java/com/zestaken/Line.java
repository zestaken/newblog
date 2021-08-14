package com.zestaken;

import java.awt.*;

public class Line {
    //起点坐标
    int x = 380;
    int y = 180;
    //终点坐标
    double endx = 500;
    double endy = 500;

    //通过固定线长改变角度来获取不同角度下的横纵坐标来使直线动起来
    double length = 100;
    //角度计算量度
    double n = 0;
    //旋转方向，dir为正，顺时针
    int dir = 1;
    //控制线的伸长（1），收回（2），摇摆（0）三种状态
    int state = 0;

    void line(Graphics g) {
        //todo 计算机中的坐标轴情况
        endx = (x + (length * Math.cos(n * Math.PI)));
        endy = (y + (length * Math.sin(n * Math.PI)));
        //设置颜色为红色
        g.setColor(Color.red);
        //根据始末坐标绘制线
        g.drawLine(x, y, (int)endx, (int)endy);
    }

    /**
     * 绘制红线
     * @param g
     */
    void paintSelf(Graphics g) {
        switch (state) {
            case 0:
                if(n < 0.1) {
                    dir = 1;
                } else if(n > 0.9) {
                    dir = -1;
                }
                //每一次绘制的角度有变化使线动起来
                n = n + 0.005 * dir ;
                line(g);
                break;
            case 1:
                if(length < 500) {
                    length += 10;
                    line(g);
                } else {
                    //转到收回状态
                    state = 2;
                }
                break;
            case 2:
                if(length > 100) {
                    length -= 10;
                    line(g);
                } else {
                    //转到摇摆状态
                    state = 0;
                }
        }
    }
}
