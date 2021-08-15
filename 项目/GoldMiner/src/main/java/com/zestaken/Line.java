package com.zestaken;

import java.awt.*;

public class Line {
    //起点坐标
    int x = 380;
    int y = 180;
    //终点坐标
    double endx = 500;
    double endy = 500;
    //窗口元素,用于获取窗口中物体元素的宽高从而实现抓取判断
    GameWindow gameWindow;

    //通过固定线长改变角度来获取不同角度下的横纵坐标来使直线动起来
    double length = 100;
    //角度计算量度
    double n = 0;
    //旋转方向，dir为正，顺时针
    int dir = 1;
    //控制线的伸长（1），到尽头收回（2），摇摆（0），抓取返回（3）四种状态
    int state = 0;

    public Line(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    void logic() {
        if(endx > gameWindow.gold.x && endx < (gameWindow.gold.x + gameWindow.gold.width)
            && endy > gameWindow.gold.y && endy < (gameWindow.gold.y + gameWindow.gold.height)) {
            //检测到红线触碰了物体则红线转到抓取返回状态
            state = 3;
        }
    }

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
        //绘制红线前，判断是否抓取到金块
        logic();

        //根据按键控制，判断红线的状态是应该摆动，伸长还是收回
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
                break;
            case 3:
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
