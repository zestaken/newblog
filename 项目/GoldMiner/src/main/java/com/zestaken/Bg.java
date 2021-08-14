package com.zestaken;

import java.awt.*;

/**
 * 绘制背景图片
 * @author zestaken
 */
public class Bg {

    //todo 读取图片方式研究
    Image bg = Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
    Image bg1 = Toolkit.getDefaultToolkit().getImage("imgs/bg1.jpg");
    Image peo = Toolkit.getDefaultToolkit().getImage("imgs/peo.png");

    /**
     * 绘制图片
     * @param g 传入的画笔
     */
    //todo 画笔绘制图片研究
    void paintSelf(Graphics g) {
        g.drawImage(bg1, 0, 0, null);
        g.drawImage(bg, 0, 200, null);
        g.drawImage(peo, 310, 50, null);
    }
}
