package Pacman.service;

import Pacman.UI.Login;
import Pacman.UI.Pacman;
import Pacman.message.Game;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
    //请求的ip+端口
    static String host;
    static int port;
    static Login login;
    static Pacman pacman;
    static boolean connected;
    static Client client;
    //通道
    static ChannelFuture f;
    public void connect() {
        //事件循环组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //辅助类
            Bootstrap b = new Bootstrap();
            //加入这个行程组
            b.group(workerGroup)
                 //设置通道
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                 //事件处理器
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new GameDecoder());				     //把ByteBuf转换成对象
                        ch.pipeline().addLast(new GameEncoder());                  //把对象转换成ByteBuf进行传送
                        ch.pipeline().addLast(new ClientHandler());				 //ClientHandler为我们自定义的事件处理类
                    }
                });
            f = b.connect(host, port).sync();
            //等待连接完成后执行下一步操作
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            //关闭线程
            workerGroup.shutdownGracefully();
        }
    }
// 192.168.43.62
    public static void  main(String[] args) {
        host = "127.0.0.1";
        port = 8002;
        pacman = new Pacman();
        login = new Login();
        client = new Client();
        client.connect();

    }

    public static void sendLoginObject(Game a) {
        f.channel().writeAndFlush(a);
        System.out.println("发送成功");
    }
}