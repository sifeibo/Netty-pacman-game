package Pacman.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Pacman.message.Game;
import Pacman.service.Client;

public class Pacman extends JFrame implements KeyListener{
    public JPanel pan = new JPanel();


    //一个格子大小
    int gridSize;

    public Pacman(){
        super.setTitle("Pacman");
        pan.setLayout(null);
        pan.setBackground(Color.black);


        super.addKeyListener(this);// 加入key监听
        super.add(pan);
        super.setSize(500, 500);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    public void repaint(int[][] array, int score1,int score2){
        Graphics g;
        g=getGraphics();
        msg(g, score1, score2);
        gridSize = 25;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                switch (array[i][j]){
                    case 1:
                        wall(g,i*gridSize+15, j*gridSize+40);
                        break;
                    case 2:
                        pellet(g,i*gridSize+23,j*gridSize+48);
                        break;
                    case 3:
                        user(g,i*gridSize+15,j*gridSize+40);
                        break;
                    case 4:
                        user2(g,i*gridSize+15,j*gridSize+40);
                        break;
                }
            }
        }
    }
    /* 绘制pacman */
    public void user(Graphics g, int x, int y)
    {
        g.setColor(Color.RED);
        g.fillOval(x,y,20,20);
    }
    /* 绘制pacman */
    public void user2(Graphics g, int x, int y)
    {

        g.setColor(Color.GREEN);
        g.fillOval(x,y,20,20);
    }
    /* 绘制墙 */
    public void wall(Graphics g, int x, int y)
    {
        g.setColor(Color.BLUE);
        g.fillRect(x,y,20,20);
    }
    /* 绘制一个单独的小球*/
    public void pellet(Graphics g, int x, int y)
    {
        g.setColor(Color.YELLOW);
        g.fillOval(x,y,4,4);
    }
    /*绘制username，score*/
    public void msg(Graphics g,int score1, int score2){
        g.setColor(Color.white);
        Font oFont = new Font("Futura Hv", Font.PLAIN, 15);
        g.setFont(oFont);
        g.drawString("ID:haha", 370, 150 );
        g.drawString("Socre: " + Integer.toString(score1), 370, 170);

        g.drawString("对手ID:lala", 370, 250 );
        g.drawString("对手Socre: " + Integer.toString(score2), 370, 270);
    }

    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.white);
        Font oFont = new Font("Futura Hv", Font.PLAIN, 30);
        g.setFont(oFont);
        g.drawString("按S开始游戏", 160, 250);
    }



    /*按键信息*/
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case 83://s开始
                Game k = new Game(4,'k',null,0,0);
                Client.sendLoginObject(k);
                break;
            case KeyEvent.VK_UP:
                Game w = new Game(4,'w',null,0,0);
                Client.sendLoginObject(w);
                break;
            case KeyEvent.VK_DOWN:
                Game s = new Game(4,'s',null,0,0);
                Client.sendLoginObject(s);
                break;
            case KeyEvent.VK_LEFT:
                Game a = new Game(4,'a',null,0,0);
                Client.sendLoginObject(a);
                break;
            case KeyEvent.VK_RIGHT:
                Game d = new Game(4,'d',null,0,0);
                Client.sendLoginObject(d);
                break;
        }
    }

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}



}




