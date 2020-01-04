package netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * @author nibnait
 * @version $Id: ClientInitializer.java, v 0.1 2019-08-19 下午8:44 nibnait Exp $$
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpServerCodec());    //NIO自己的字符编码处理器
        pipeline.addLast(new ServerHandler());

        /*
          管道
          工厂的流水线，
         */
    }

}