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
    //开始时间和结束时间
    static long startTime;
    static long endTime;
    //药水价格 每一关随机
    int price = (int)(Math.random() * 20);
    //进入购买药水状态
    static boolean shop = false;

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
        //游戏状态
        g.drawImage(bg1, 0, 0, null);
        g.drawImage(bg, 0, 200, null);
        g.drawImage(peo, 310, 50, null);
        switch (GameWindow.state) {
            case 0:
                //未开始状态
                drawWord(g, Color.GREEN, 80,"点击鼠标右键开始！", 50, 400);
                break;
            case 1:
                drawWord(g, Color.BLACK, 30,"积分"+totalScore, 30, 150);
                //绘制药水
                g.drawImage(water, 450, 40, null);
                drawWord(g, Color.BLACK, 30,"*"+waterNum, 510, 70);
                //关卡数
                drawWord(g, Color.BLACK, 30,"第"+level+"关", 30, 60);
                //目标积分
                drawWord(g, Color.BLACK, 30,"目标"+goalScore, 30, 110);
                //如果要进入下一关
                if(nextLevel) {
                    drawWord(g, Color.RED, 80,"总分" + totalScore + "恭喜过关!", 110, 500);
                    nextLevel = false;
                    //时间重置
                    startTime = System.currentTimeMillis();
                }
                //显示倒计时
                endTime = System.currentTimeMillis();
                long time = 20 - (endTime - startTime)/1000;//毫秒转化为秒，倒计时20秒
                drawWord(g, Color.BLACK, 30,"时间"+(time>0?time:0), 520, 150);
                break;
            case 2:
                //商店状态
                g.drawImage(water, 300 , 400, null);
                drawWord(g, Color.BLACK, 30,"价格:" + price, 300, 500);
                drawWord(g, Color.BLACK, 80,"积分"+totalScore, 200, 300);
                drawWord(g, Color.BLACK, 30,"是否购买？" , 300, 550);
                drawWord(g, Color.gray, 20,"点击鼠标右键确认购买药水" , 10, 600);
                drawWord(g, Color.gray, 20,"点击鼠标右键确认购买药水" , 10, 600);
                //如果要购买药水
                if(shop) {
                    //如果分数足够则购买
                    if(totalScore >= price) {
                        totalScore -= price;
                        waterNum++;
                        drawWord(g, Color.RED, 30,"购买成功！" , 300, 350);
                        //返回到游戏状态
                        GameWindow.state = 1;
                        //时间重置
                        startTime = System.currentTimeMillis();
                        shop = false;
                    } else {
                        drawWord(g, Color.RED, 30,"积分不足！" , 300, 350);
                        shop = false;
                        //时间重置
                        startTime = System.currentTimeMillis();
                        //返回到游戏状态
                        GameWindow.state = 1;
                    }
                }
                break;
            case 3:
                //失败状态
                drawWord(g, Color.BLACK, 80,"游戏失败！", 250, 350);
                drawWord(g, Color.BLACK, 80,"积分"+totalScore, 200, 450);
                drawWord(g, Color.RED, 50,"点击鼠标右键重新开始！", 50, 550);
                break;
            case 4:
                //成功状态
                drawWord(g, Color.RED, 80,"恭喜通关！", 200, 350);
                drawWord(g, Color.RED, 80,"积分"+totalScore, 200, 450);
                drawWord(g, Color.RED, 80,"你是YYDS！", 200, 550);
                drawWord(g, Color.RED, 50,"点击鼠标右键重新开始！", 50, 650);
                break;

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

    /**
     * 判断倒计时是否结束
     * @return true表示倒计时完成
     */
    boolean gameTime() {
        long time = (endTime - startTime)/1000;
        if(time >= 20) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 重置背景元素中的所有属性以重新开始游戏
     */
    void reGame() {
        //总分数
        totalScore = 0;
        //药水数量,初始为3
        waterNum = 3;
        //药水状态，默认是false，true表示正在使用
        waterFlag = false;
        //关卡数, 初始为1
        level = 1;
        //目标积分
        goalScore = (level + 1) * (level + 1) + level;
        //判断是否进入了下一关的标志
        nextLevel = false;
        //开始时间和结束时间
        startTime = 0;
        endTime = 0;
    }
}
