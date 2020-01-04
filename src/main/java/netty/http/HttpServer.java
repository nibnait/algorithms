package netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author nibnait
 * @version $Id: HttpServer.java, v 0.1 2019-08-19 下午8:34 nibnait Exp $$
 */
public class HttpServer {
    public static void start() {
        //1. 创建两个线程组
        NioEventLoopGroup boss = new NioEventLoopGroup();     //接收请求的事件循环组
        NioEventLoopGroup worker = new NioEventLoopGroup();   //处理请求的事件循环组

        //2. 创建一个服务端启动类对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        //3. 将创建两个线程组添加到bootstrap，
        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)      //通过反射 创建一个channel
                .childHandler(new ServerInitializer());     //子处理器，绑定在worker

        //4. 绑定端口
        try {
            serverBootstrap.bind(8848).sync();
//            ChannelFuture channelFuture = serverBootstrap.bind(8848).sync();
//            channelFuture.serverChannel().close().sync();

            /*
              channel的作用
              控制到当前网络的连接状态
              网络连接的配置参数：设置缓冲区大小
              提供一个异步的网络I/O操作：建立连接、读、写、绑定端口
                sync()方法直接返回

              ChannelHandler
              处理IO事件、拦截IO操作，并将请求转到管道中
             */

        } catch (InterruptedException e) {
            e.printStackTrace();
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}