package Pacman.service;

import Pacman.message.Game;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class Server {
    static int score1;
    static int score2;
    static  int a[][] = {  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
                            {1,2,2,1,2,1,2,2,2,1,2,1,2,2,1},
                            {1,2,2,1,2,1,1,2,1,1,2,1,2,2,1},
                            {1,2,2,1,1,1,1,2,1,1,1,1,2,2,1},
                            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
                            {1,2,1,1,2,1,1,2,2,2,2,2,2,2,1},
                            {1,2,1,2,2,2,1,1,1,2,2,2,2,2,1},
                            {1,2,1,2,2,2,2,2,1,2,2,2,2,2,1},
                            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
                            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    public void start(int port) throws Exception {
        //开启两个事件循环组，事件循环组会自动构建EventLoop，服务器一般开启两个，提高效率
        //1 第一个线程组 是用于接收Client端连接的
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //2 第二个线程组 是用于实际的业务处理操作的
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //3 创建一个辅助类Bootstrap，就是对我们的Server进行一系列的配置
            ServerBootstrap b = new ServerBootstrap();
            //把俩个工作线程组加入进来
            b.group(bossGroup, workerGroup)
                    //我要指定使用NioServerSocketChannel这种类型的通道
                    .channel(NioServerSocketChannel.class)
                    //一定要使用 childHandler 去绑定具体的 事件处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(new GameDecoder());   //把ByteBuf流转换成Game对象
                            sc.pipeline().addLast(new GameEncoder());   //把对象转换成ByteBuf进行传输
                            sc.pipeline().addLast(new ServerHandler()); //ServerHandler为我们自定义的事件处理类
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            //绑定指定的端口 进行监听
            ChannelFuture f = b.bind(port).sync();

            //Thread.sleep(1000000);
            f.channel().closeFuture().sync();  //没有这句话则不会在此阻塞等待客户端的连接，而是直接执行后面代码
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.start(8002);
    }


}

