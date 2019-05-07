package Pacman.service;


import Pacman.message.Game;
import Pacman.message.Recivemsg;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import javax.swing.JOptionPane;

import static Pacman.service.Client.login;
import static Pacman.service.Client.pacman;

public class ClientHandler extends ChannelHandlerAdapter {
    public Game remsg;
    public int[][] a =new int[20][15];
    public int score1,score2;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端已连接，准备发送登录数据。");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        remsg = (Game)msg;
        switch (remsg.getTag()) {
            case 1:
                if (remsg.getRtag() == 1){
                    JOptionPane.showMessageDialog(null, "登录成功！");
                    login.setVisible(false);
                }
                else if (remsg.getRtag() == 2){
                    JOptionPane.showMessageDialog(null, "密码错误！");
                }
                else if (remsg.getRtag() == 3){
                    JOptionPane.showMessageDialog(null, "用户名不存在！");
                }
                break;
            case 2:
                if (remsg.getRtag()==1) {
                    JOptionPane.showMessageDialog(null, "注册成功！");
                }else {
                    JOptionPane.showMessageDialog(null, "注册失败！");
                }
                break;
            case 3:
                a = remsg.getArray();
                score2 = remsg.getScore2();
                score1 = remsg.getScore1();
                pacman.pan.validate();
                pacman.pan.repaint();
                pacman.repaint(a,score1,score2);
                if (score1+score2 == 870){
                    if (score1> score2){
                        JOptionPane.showMessageDialog(null, "haha胜利");
                        pacman.setVisible(false);
                    }else if (score1 == score2){
                        JOptionPane.showMessageDialog(null, "平局");
                        pacman.setVisible(false);
                    }else {
                        JOptionPane.showMessageDialog(null, "lala胜利");
                        pacman.setVisible(false);
                    }

                }
                break;
            case 4:
                a = remsg.getArray();
                score2 = remsg.getScore2();
                score1 = remsg.getScore1();
                pacman.pan.validate();
                pacman.pan.repaint();
                pacman.repaint(a,score1,score2);
                if (score1+score2 == 870){
                    if (score1> score2){
                        JOptionPane.showMessageDialog(null, "haha胜利");
                        pacman.setVisible(false);
                    }else if (score1 == score2){
                        JOptionPane.showMessageDialog(null, "平局");
                        pacman.setVisible(false);
                    }else {
                        JOptionPane.showMessageDialog(null, "lala胜利");
                        pacman.setVisible(false);
                    }
                }
                break;

        }
    }

//        ImageArray a = (ImageArray) msg;
//        int [][] array = new int[10][10];
//        array = a.getArray();
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array[i].length; j++) {
//                System.out.print(array[i][j]);
//            }
//            System.out.println();
//        }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
