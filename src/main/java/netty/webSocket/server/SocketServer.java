package netty.webSocket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by nibnait on 2019-08-20
 */
public class SocketServer {

    public static void start() {
        NioEventLoopGroup accept = new NioEventLoopGroup();
        NioEventLoopGroup handler = new NioEventLoopGroup();

        //创建启动类对象
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(accept, handler)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerInitializer());

        try {
            bootstrap.bind(8866).sync();
        } catch (Exception e) {
            e.printStackTrace();
            accept.shutdownGracefully();
            handler.shutdownGracefully();
        }
    }
}
