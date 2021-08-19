package com.zestaken;

import java.awt.*;

/**
 * 绘制背景图片
 * @author zestaken
 */
public class Bg {

    //总分数
    static int totalScore = 0;
    //药水数量,初始为3
    static int waterNum = 3;
    //药水状态，默认是false，true表示正在使用
    static boolean waterFlag = false;
    //关卡数, 初始为1
    static int level = 1;
    //每一关的目标积分
    int goalScore = (level + 1) * (level + 1) + level;
    //判断是否进入了下一关的标志
    static boolean nextLevel = false;

    //todo 读取图片方式研究
    Image bg = Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
    Image bg1 = Toolkit.getDefaultToolkit().getImage("imgs/bg1.jpg");
    Image peo = Toolkit.getDefaultToolkit().getImage("imgs/peo.png");
    Image water = Toolkit.getDefaultToolkit().getImage("imgs/water.png");


    /**
     * 绘制图片
     * @param g 传入的画笔
     */
    //todo 画笔绘制图片研究
    void paintSelf(Graphics g) {
        switch (GameWindow.state) {
            case 0:
                //未开始状态
                drawWord(g, Color.BLACK, 80,"点击鼠标右键开始！", 110, 400);
                break;
            case 1:
                //游戏状态
                g.drawImage(bg1, 0, 0, null);
                g.drawImage(bg, 0, 200, null);
                g.drawImage(peo, 310, 50, null);
                drawWord(g, Color.BLACK, 30,"积分"+totalScore, 30, 150);
                //绘制药水
                g.drawImage(water, 450, 40, null);
                drawWord(g, Color.BLACK, 30,"*"+waterNum, 510, 70);
                //关卡数
                drawWord(g, Color.BLACK, 30,"第"+level+"关", 30, 60);
                //目标积分
                drawWord(g, Color.BLACK, 30,"目标"+goalScore, 30, 110);
                break;
            case 2:
                //商店状态
                break;
            case 3:
                //失败状态
                break;
            case 4:
                //成功状态
                break;

        }

        //如果要进入下一关
        if(nextLevel) {
            drawWord(g, Color.RED, 80,"总分" + totalScore + "恭喜过关!", 110, 500);
            nextLevel = false;
        }
    }

    /**
     * 绘制字符串
     * @param g
     * @param color
     * @param size
     * @param str
     * @param x
     * @param y
     */
    public static void drawWord(Graphics g, Color color, int size, String str, int x, int y) {
        g.setColor(color);
        g.setFont(new Font("仿宋", Font.BOLD, size));
        g.drawString(str, x, y);
    }
}
