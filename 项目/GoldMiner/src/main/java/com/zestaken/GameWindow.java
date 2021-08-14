package com.zestaken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 使用swing绘制游戏窗口
 * @author zestaken
 */
//todo JFrame使用研究
public class GameWindow extends JFrame {

    //创建背景图片类
    Bg bg = new Bg();
    //创建线类
    Line line = new Line();
    //创建金块
    Gold gold = new Gold();
    /**
     * 窗口启动设置
     */
    void launch() {
        //设置窗口是否可见
        this.setVisible(true);
        //设置窗口大小
        this.setSize(768, 1000);
        //设置窗口位置（居中)
        this.setLocationRelativeTo(null);
        //设置窗口标题
        this.setTitle("张杰的黄金矿工");
        //点击右上角的X可以关闭窗口
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //todo 监听鼠标动作
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //鼠标左键是1，右键是2，滚轮是3
                if(e.getButton() == 1) {
                    //点击鼠标左键，线伸长
                    line.state = 1;
                }
            }
        });
        //通过无线循环不停的重新绘制来实现动态效果
        while(true) {
            //重新绘制的方法
            repaint();
            //设置睡眠时间，降低运动速度
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    /**
     * 传入画笔，绘制图片
     */
    public void paint(Graphics g) {
        bg.paintSelf(g);
        line.paintSelf(g);
        gold.paintSelf(g);
    }

    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.launch();
    }
}
