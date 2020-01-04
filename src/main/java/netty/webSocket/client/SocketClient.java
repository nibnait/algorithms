package netty.webSocket.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by nibnait on 2019-08-20
 */
public class SocketClient {

    public static void start() {
        NioEventLoopGroup handler = new NioEventLoopGroup();

        //创建启动类对象
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(handler)
                .channel(NioSocketChannel.class)
                .handler(new ClientInitializer());

        try {
            Channel channel = bootstrap.connect("127.0.0.1", 8866).sync().channel();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                channel.writeAndFlush(bufferedReader.readLine() + "\r\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
            handler.shutdownGracefully();
        }
    }
}
