package com.zestaken;

import java.awt.*;

public class Line {
    //起点坐标
    int x = 380;
    int y = 180;
    //终点坐标
    int endx = 500;
    int endy = 500;
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
        //获取窗口的金块集合元素并遍历，以此判断每个金块
        for(Object gold : this.gameWindow.golds) {
            if(endx > gold.x && endx < (gold.x + gold.width)
                    && endy > gold.y && endy < (gold.y + gold.height)) {
                //检测到红线触碰了物体则红线转到抓取返回状态
                state = 3;
                //使金块标记为已被抓取可以移动
                gold.flag = true;
            }
        }

    }

    void line(Graphics g) {
        //todo 计算机中的坐标轴情况
        endx = (int) (x + (length * Math.cos(n * Math.PI)));
        endy = (int) (y + (length * Math.sin(n * Math.PI)));
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
                if(length < 700) {
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
                    //使金块被抓回
                    for(Object gold : this.gameWindow.golds) {
                        //已是被抓取状态的金块才可以移动
                        if(gold.flag) {
                            //使金块与线的末端坐标同步以实现与线同步移动的效果
                            gold.x = endx - 26; //金块使二维图像，设置一定偏移量效果更逼真
                            gold.y = endy;
                            if(length <= 100) {
                                //当线收缩到矿工处时，通过将金块移到窗口外面使金块消失
                                gold.x = -150;
                                gold.y = -150;
                                gold.flag = false;
                            }
                        }
                    }
                } else {
                    //转到摇摆状态
                    state = 0;
                }
        }
    }
}
