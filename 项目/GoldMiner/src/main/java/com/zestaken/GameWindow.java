package com.zestaken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用swing绘制游戏窗口
 * @author zestaken
 */
//todo JFrame使用研究
public class GameWindow extends JFrame {

    //创建背景图片类
    Bg bg = new Bg();
    //创建线类
    Line line = new Line(this);
    //创建一个集合来存储金块，以实现同时出现多个金块
   List<Object> golds = new ArrayList<Object>();
   //设置一个静态代码块来初始化金块集合
    //todo 静态代码块使用研究
    {
        //暂时初始化三个金块
        for(int i = 0; i < 3; i++) {
            golds.add(new Gold());
        }
    }

    //定义画布，用于实现双缓存
    //todo 双缓存解决闪动原理
    Image offScreenImage;
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
        //初始化实现双缓存的画布，和窗口大小相同
        offScreenImage = this.createImage(768, 1000);

        //创建属于画布的画笔，调用这个画笔就是向这个画布上绘制
        Graphics gImage = offScreenImage.getGraphics();

        //将各种图案绘制到画布中
        bg.paintSelf(gImage);
        line.paintSelf(gImage);
        //遍历金块集合，将每个金块绘制出来
        for(Object gold : golds) {
            gold.paintSelf(gImage);
        }

        //将画布绘制到窗口中,使用传入的画笔
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.launch();
    }
}
