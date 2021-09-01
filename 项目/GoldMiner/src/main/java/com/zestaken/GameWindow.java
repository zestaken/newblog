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

    //5种游戏状态: 未开始（0），运行中(1), 商店(2), 游戏失败（3）， 成功过关（4）
    static int state = 0;
    //创建背景图片类
    Bg bg = new Bg();
    //创建线类
    Line line = new Line(this);
    //创建一个集合来存储金块，以实现同时出现多个金块
   List<Object> objs = new ArrayList<Object>();
   //设置一个静态代码块来初始化金块集合

    //todo 静态代码块使用研究
    {
        //根据关卡不同创建不同的数量
        int nums = bg.goalScore / 2;
        if(bg.goalScore > 41) {
            nums = 41;
        }

        for(int i = 0; i < nums + 3; ) {
            //按照不同的概率添加不同类型的金块
            double random = Math.random();
            //用于临时存储当前生成的金块
            Gold gold;
            if(random < 0.2) {
                gold = new GoldMini();
            } else if( 0.2 <= random && random < 0.8) {
                gold = new Gold();
            } else {
                gold = new GoldPlus();
            }
            //检测金块是否重叠
            boolean flag = true;
            for(Object obj : objs) {
                if(gold.getRec().intersects(obj.getRec())) {
                    flag = false;
                }
            }
            //如果没有重叠则添加到集合
            if(flag) {
                objs.add(gold);
                //如果重叠则本次创建失败，i保持原值不变
                i++;
            }
        }

        //根据关卡不同创建不同的数量的石块
        for(int i = 0; i < nums;) {
            //临时存储当前创建的石块
            Rock rock = new Rock();
            //判断是否重叠
            //检测金块是否重叠
            boolean flag = true;
            for(Object obj : objs) {
                if(rock.getRec().intersects(obj.getRec())) {
                    flag = false;
                }
            }
            //如果没有重叠则添加到集合
            if(flag) {
                objs.add(rock);
                //如果重叠则本次创建失败，i保持原值不变
                i++;
            }

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
                switch(state) {
                    case 0:
                        //未开始时点击鼠标右键则进入游戏状态
                        if(e.getButton() == 3) {
                            state = 1;
                            //进入游戏状态时获取开始时间
                            Bg.startTime = System.currentTimeMillis();
                        }
                        break;
                    case 1:
                        //游戏状态
                        //鼠标左键是1，右键是2，滚轮是3
                        //线在左右摇摆时点击左键伸长
                        if(e.getButton() == 1 && line.state == 0) {
                            //点击鼠标左键，线伸长
                            line.state = 1;
                        }
                        if(e.getButton() == 1 && Bg.nextLevel == true) {

                        }
                        //线在抓取返回状态时点击右键使用药水
                        if(e.getButton() == 3 && line.state == 3) {
                            if(Bg.waterNum > 0) {
                                Bg.waterNum--;
                                Bg.waterFlag = true;
                            }
                        }
                        break;
                    case 2:
                        //点击鼠标右键确认购买
                        if(e.getButton() == 3) {
                            Bg.shop = true;
                        }
                        //如果点击鼠标左键进入下一关
                        if(e.getButton() == 1) {
                            state = 1;
                        }
                        break;
                    case 3:
                        //游戏失败状态去掉break，可以与游戏成功状态进行同样的操作
                    case 4:
                        //游戏成功或者失败点击鼠标右键重新开始
                        if(e.getButton() == 3) {
                            bg.reGame();
                            line.reGame();
                            state = 0;
                        }
                        break;
                    default:
                }

            }
        });

        //通过无线循环不停的重新绘制来实现动态效果
        while(true) {
            //重新绘制的方法
            repaint();
            //是否进入下一关
            nextLevel();
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

        //将各种图案绘制到画布中,先画物体再画线使抓取时线在物体上面
        bg.paintSelf(gImage);

        //仅在游戏状态才绘制物体和钩子
        if(state == 1) {
            //遍历金块集合，将每个金块绘制出来
            for(Object obj : objs) {
                obj.paintSelf(gImage);
            }
            line.paintSelf(gImage);
        }

        //将画布绘制到窗口中,使用传入的画笔
        g.drawImage(offScreenImage, 0, 0, null);

    }

    /**
     * 判断进入下一关
     */
    public void nextLevel() {
        //仅在游戏状态中并且倒计时结束才判断是否进入下一关
        if(state == 1 && bg.gameTime()) {
            if(Bg.totalScore >= bg.goalScore) {
                //8关则成功通关
                if(Bg.level == 8) {
                    //转到游戏成功状态
                    state = 4;
                } else {
                    //关卡数加一
                    Bg.level++;
                    //进入下一关标志开启，暂停一会儿显示过关信息
                    Bg.nextLevel = true;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //进入商店状态
                    state = 2;
                }
                //todo 释放当前窗口
                dispose();
                GameWindow gameWindow = new GameWindow();
                gameWindow.launch();
            } else {
                //游戏失败,进入游戏失败状态
                state = 3;
            }
        }

    }

    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.launch();
    }
}
