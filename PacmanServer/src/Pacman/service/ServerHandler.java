package Pacman.service;

import Pacman.db.Jdbc;
import Pacman.message.Game;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import Pacman.service.Server;

public class ServerHandler extends ChannelHandlerAdapter {
    public Game game;

    //1p:3-5,4  2p:4-7,10
    public int x1;
    public int y1;
    public int x2;
    public int y2;

    public void refresh(int x1,int y1,int x2,int y2){
        Server.a[x1][y1] = 3;
        Server.a[x2][y2] = 4;
    }


    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channelGroup.writeAndFlush(ctx.channel().remoteAddress() + "上线了\n");
        System.out.println(ctx.channel().remoteAddress() + "上线了\n");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        game = (Game) msg;
        Jdbc d  =  new Jdbc();
        int m;
        switch (game.getTag()) {
            case 1:
                System.out.println("收到登陆信息，进行对比");
                m = d.compare(game.getUsername(),game.getPassword());
                game.setRtag(m);
                ctx.writeAndFlush(game);
                break;
            case 2:
                System.out.println("收到注册信息，进行插入");
                m = d.insert(game.getUsername(),game.getPassword());
                game.setRtag(m);
                ctx.writeAndFlush(game);
                break;
            case 3:
                if (game.getKey() == 'k'){
                    System.out.println("初始化界面");
                    x1 = d.getX1();
                    x2 = d.getX2();
                    y1 = d.getY1();
                    y2 = d.getY2();
                    System.out.println(x1);
                    refresh(x1,y1,x2,y2);
                    game.setArray(Server.a);
                    channelGroup.forEach(channel ->{
                        if(ctx.channel() != channel){
                            channel.writeAndFlush(game);
                        }else {
                            channel.writeAndFlush(game);
                        }
                    });
                }else if (game.getKey() == 'w'){
                    System.out.println("向上");
                    x1 = d.getX1();
                    x2 = d.getX2();
                    y1 = d.getY1();
                    y2 = d.getY2();
                    if (Server.a[x1][y1-1] != 1) {
                        if (Server.a[x1][y1-1] == 2){
                            Server.score1 += 10;
                        }
                        Server.a[x1][y1] = 0;
                        y1 -= 1;
                        refresh(x1,y1,x2,y2);
                        d.updatePost1(x1,y1);
                    }
                    game.setScore1(Server.score1);
                    game.setScore2(Server.score2);
                    game.setArray(Server.a);
                    channelGroup.forEach(channel ->{
                        if(ctx.channel() != channel){
                            channel.writeAndFlush(game);
                        }else {
                            channel.writeAndFlush(game);
                        }
                    });
                }else if (game.getKey() == 's'){
                    System.out.println("向下");
                    x1 = d.getX1();
                    x2 = d.getX2();
                    y1 = d.getY1();
                    y2 = d.getY2();
                    if (Server.a[x1][y1+1] != 1) {
                        if (Server.a[x1][y1+ 1] == 2){
                            Server.score1 += 10;
                        }
                        Server.a[x1][y1] = 0;
                        y1 += 1;
                        refresh(x1,y1,x2,y2);
                        d.updatePost1(x1,y1);
                    }
                    game.setScore1(Server.score1);
                    game.setScore2(Server.score2);
                    game.setArray(Server.a);
                    channelGroup.forEach(channel ->{
                        if(ctx.channel() != channel){
                            channel.writeAndFlush(game);
                        }else {
                            channel.writeAndFlush(game);
                        }
                    });
                }else if(game.getKey() == 'a'){
                    System.out.println("向左");
                    x1 = d.getX1();
                    x2 = d.getX2();
                    y1 = d.getY1();
                    y2 = d.getY2();
                    if (Server.a[x1-1][y1] != 1) {
                        if (Server.a[x1-1][y1] == 2){
                            Server.score1 += 10;
                        }
                        Server.a[x1][y1] = 0;
                        x1 -= 1;
                        refresh(x1,y1,x2,y2);
                        d.updatePost1(x1,y1);
                    }
                    game.setScore1(Server.score1);
                    game.setScore2(Server.score2);
                    game.setArray(Server.a);
                    channelGroup.forEach(channel ->{
                        if(ctx.channel() != channel){
                            channel.writeAndFlush(game);
                        }else {
                            channel.writeAndFlush(game);
                        }
                    });
                }else if(game.getKey() == 'd'){
                    System.out.println("向右");
                    x1 = d.getX1();
                    x2 = d.getX2();
                    y1 = d.getY1();
                    y2 = d.getY2();
                    if (Server.a[x1+1][y1] != 1) {
                        if (Server.a[x1+1][y1] == 2){
                            Server.score1 += 10;
                        }
                        Server.a[x1][y1] = 0;
                        x1 += 1;
                        refresh(x1,y1,x2,y2);
                        d.updatePost1(x1,y1);
                    }
                    game.setScore1(Server.score1);
                    game.setScore2(Server.score2);
                    game.setArray(Server.a);
                    channelGroup.forEach(channel ->{
                        if(ctx.channel() != channel){
                            channel.writeAndFlush(game);
                        }else {
                            channel.writeAndFlush(game);
                        }
                    });
                }
                break;

            case 4:
                if (game.getKey() == 'k'){
                    System.out.println("初始化界面");
                    x1 = d.getX1();
                    x2 = d.getX2();
                    y1 = d.getY1();
                    y2 = d.getY2();
                    refresh(x1,y1,x2,y2);
                    game.setArray(Server.a);
                    channelGroup.forEach(channel ->{
                        if(ctx.channel() != channel){
                            channel.writeAndFlush(game);
                        }else {
                            channel.writeAndFlush(game);
                        }
                    });
                }else if (game.getKey() == 'w'){
                    System.out.println("向上");
                    x1 = d.getX1();
                    x2 = d.getX2();
                    y1 = d.getY1();
                    y2 = d.getY2();
                    if (Server.a[x2][y2-1] != 1) {
                        if (Server.a[x2][y2-1] == 2){
                            Server.score2 += 10;
                        }
                        Server.a[x2][y2] = 0;
                        y2 -= 1;
                        refresh(x1,y1,x2,y2);
                        d.updatePost2(x2,y2);
                    }
                    game.setScore1(Server.score1);
                    game.setScore2(Server.score2);
                    game.setArray(Server.a);
                    channelGroup.forEach(channel ->{
                        if(ctx.channel() != channel){
                            channel.writeAndFlush(game);
                        }else {
                            channel.writeAndFlush(game);
                        }
                    });
                }else if (game.getKey() == 's'){
                    System.out.println("向下");
                    x1 = d.getX1();
                    x2 = d.getX2();
                    y1 = d.getY1();
                    y2 = d.getY2();
                    if (Server.a[x2][y2+1] != 1) {
                        if (Server.a[x2][y2+1] == 2){
                            Server.score2 += 10;
                        }
                        Server.a[x2][y2] = 0;
                        y2 += 1;
                        refresh(x1,y1,x2,y2);
                        d.updatePost2(x2,y2);
                    }
                    game.setScore1(Server.score1);
                    game.setScore2(Server.score2);
                    game.setArray(Server.a);
                    channelGroup.forEach(channel ->{
                        if(ctx.channel() != channel){
                            channel.writeAndFlush(game);
                        }else {
                            channel.writeAndFlush(game);
                        }
                    });
                }else if(game.getKey() == 'a'){
                    System.out.println("向左");
                    x1 = d.getX1();
                    x2 = d.getX2();
                    y1 = d.getY1();
                    y2 = d.getY2();
                    if (Server.a[x2-1][y2] != 1) {
                        if (Server.a[x2-1][y2] == 2){
                            Server.score2 += 10;
                        }
                        Server.a[x2][y2] = 0;
                        x2 -= 1;
                        refresh(x1,y1,x2,y2);
                        d.updatePost2(x2,y2);
                    }
                    game.setScore1(Server.score1);
                    game.setScore2(Server.score2);
                    game.setArray(Server.a);
                    channelGroup.forEach(channel ->{
                        if(ctx.channel() != channel){
                            channel.writeAndFlush(game);
                        }else {
                            channel.writeAndFlush(game);
                        }
                    });
                }else if(game.getKey() == 'd'){
                    System.out.println("向右");
                    x1 = d.getX1();
                    x2 = d.getX2();
                    y1 = d.getY1();
                    y2 = d.getY2();
                    if (Server.a[x2+1][y2] != 1) {
                        if (Server.a[x2+1][y2] == 2){
                            Server.score2 += 10;
                        }
                        Server.a[x2][y2] = 0;
                        x2 += 1;
                        refresh(x1,y1,x2,y2);
                        d.updatePost2(x2,y2);
                    }
                    game.setScore1(Server.score1);
                    game.setScore2(Server.score2);
                    game.setArray(Server.a);
                    channelGroup.forEach(channel ->{
                        if(ctx.channel() != channel){
                            channel.writeAndFlush(game);
                        }else {
                            channel.writeAndFlush(game);
                        }
                    });
                }
                break;
        }


//        //有write操作则可以不释放msg
//        ctx.writeAndFlush(a);
//        Thread.sleep(1000);


    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server 推送的消息："+ ctx.channel().remoteAddress() + "注册进了服务器\n");
        channelGroup.writeAndFlush("server 推送的消息："+ ctx.channel().remoteAddress() + "注册进了服务器\n");
        channelGroup.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channelGroup.writeAndFlush("server 推送的消息："+ ctx.channel().remoteAddress() + "离开了服务器\n");
        System.out.println("server 推送的消息："+ ctx.channel().remoteAddress() + "离开了服务器\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channelGroup.writeAndFlush(ctx.channel().remoteAddress() + "下线了\n");
        System.out.println(ctx.channel().remoteAddress() + "下线了\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
