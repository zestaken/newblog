package com.zestaken;

import java.awt.*;
import java.util.TreeMap;

public class Line {
    //起点坐标
    int x = 380;
    int y = 180;
    //终点坐标
    int endx = 500;
    int endy = 500;
    //线的钩爪图片
    Image hook = Toolkit.getDefaultToolkit().getImage("imgs/hook.png");
    //窗口元素,用于获取窗口中物体元素的宽高从而实现抓取判断
    GameWindow gameWindow;

    //通过固定线长改变角度来获取不同角度下的横纵坐标来使直线动起来
    //基本线长
    double length = 100;
    //最大最小线长
    double Min_length = 100;
    double Max_length =850;
    //角度计算量度
    double n = 0;
    //旋转方向，dir为正，顺时针
    int dir = 1;
    //控制线的伸长（1），到尽头收回（2），摇摆（0），抓取返回（3）四种状态
    //抓取到物体返回时因为物体的质量属性而使返回速度有不同程度的减慢
    int state = 0;

    public Line(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    void logic() {
        //获取窗口的金块集合元素并遍历，以此判断每个金块
        for(Object obj : this.gameWindow.objs) {
            if(endx > obj.x && endx < (obj.x + obj.width)
                    && endy > obj.y && endy < (obj.y + obj.height)) {
                //检测到红线触碰了物体则红线转到抓取返回状态
                state = 3;
                //使金块标记为已被抓取可以移动
                obj.flag = true;
            }
        }

    }

    /**
     * 绘制线
     * @param g 画笔
     */
    void line(Graphics g) {
        //todo 计算机中的坐标轴情况
        endx = (int) (x + (length * Math.cos(n * Math.PI)));
        endy = (int) (y + (length * Math.sin(n * Math.PI)));
        //设置颜色为红色
        g.setColor(Color.red);
        //根据始末坐标绘制线
        g.drawLine(x, y, (int)endx, (int)endy);
        g.drawLine(x - 1, y, (int)endx - 1, (int)endy);
        g.drawLine(x + 1, y, (int)endx + 1, (int)endy);
        //绘制钩爪,并使线接在钩爪中央
        g.drawImage(hook, endx - 36, endy - 2, null);
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
                if(length < Max_length && endx > 0 && endx < 768) {
                    length += 10;
                    line(g);
                } else {
                    //转到收回状态
                    state = 2;
                }
                break;
            case 2:
                if(length > Min_length) {
                    length -= 10;
                    line(g);
                } else {
                    //转到摇摆状态
                    state = 0;
                }
                break;
            case 3:
                if(length > Min_length) {
                    int m = 0;
                    length -= 10;
                    line(g);
                    //使金块被抓回
                    for(Object obj : this.gameWindow.objs) {
                        //已是被抓取状态的金块才可以移动
                        if(obj.flag) {
                            //获取物体的质量
                            m = obj.m;
                            //使金块与线的末端坐标同步以实现与线同步移动的效果
                            obj.x = endx - obj.width / 2; //物体是二维图像，设置一定偏移量效果更逼真
                            obj.y = endy;
                            if(length <= 100) {
                                //当线收缩到矿工处时，通过将金块移到窗口外面使金块消失
                                obj.x = -150;
                                obj.y = -150;
                                obj.flag = false;
                                //增加对应的积分
                                Bg.totalScore += obj.score;
                                //关闭药水（不管使用没有药水，确保使用了的药水在收回完毕后失效）
                                Bg.waterFlag = false;
                            }

                            //如果点击右键使用了药水
                            if(Bg.waterFlag) {
                                //对于金块是加速收回效果
                                if(obj.type == 0) {
                                    m = 5;
                                }
                                //如果是石块则是使石块直接爆破，回到无抓取返回状态（2）
                                if(obj.type == 1) {
                                    obj.x = -150;
                                    obj.y = -150;
                                    obj.flag = false;
                                    Bg.waterFlag = false;
                                }
                            }

                            //通过延时来实现收缩缓慢的视觉效果
                            try {
                                Thread.sleep(m);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                } else {
                    //转到摇摆状态
                    state = 0;
                }
        }
    }

    /**
     * 重置线对象的所有属性
     */
    void reGame() {
        n = 0;
        length = 100;
        dir = 1;
        state = 0;
    }
}
