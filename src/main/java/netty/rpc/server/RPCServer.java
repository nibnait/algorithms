package netty.rpc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.CharsetUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import netty.rpc.server.message.IMessageHandler;
import netty.rpc.server.message.MessageHandlers;
import netty.rpc.server.message.MessageRegistry;

/**
 * Created by nibnait on 2019-08-20
 */
@Data
@AllArgsConstructor
public class RPCServer {

    private String ip;
    private int port;
    private int ioThreads; // 用来处理网络流的读写线程
    private int workerThreads; // 用于业务处理的计算线程池

    public RPCServer(String ip, int port, int ioThreads, int workerThreads) {
        this.ip = ip;
        this.port = port;
        this.ioThreads = ioThreads;
        this.workerThreads = workerThreads;
    }

    private Channel serverChannel;
    private NioEventLoopGroup group;
    private ServerHandler serverHandler;

    private MessageHandlers handlers = new MessageHandlers();
    private MessageRegistry registry = new MessageRegistry();

    public RPCServer service(String type, Class<?> reqClass, IMessageHandler<?> handler) {
        registry.register(type, reqClass);
        handlers.register(type, handler);
        return this;
    }

    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap();

        group = new NioEventLoopGroup(ioThreads);
        serverHandler = new ServerHandler(handlers, registry, workerThreads);
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        //如果客户端60秒没有任何请求，就关闭客户端链接
                        pipeline.addLast(new ReadTimeoutHandler(60));
                        //防止TCP粘包，挂上分隔符拆包器
                        pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()));
                        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                        pipeline.addLast(serverHandler);
                    }
                });

        try {
            bootstrap.option(ChannelOption.SO_BACKLOG, 100) //客户端套接字接收队列大小
                    .option(ChannelOption.SO_REUSEADDR, true)   //reuse addr，重复利用地址，避免端口冲突
                    .option(ChannelOption.TCP_NODELAY, true)    //关闭小流合并，保证消息的及时性
                    .childOption(ChannelOption.SO_KEEPALIVE, true);  //长时间没动静的链接自动关闭
            serverChannel = bootstrap.bind(this.ip, this.port).channel();

            System.out.printf("server started @ %s:%d\n", ip, port);
        } catch (Exception e) {
            e.printStackTrace();
            group.shutdownGracefully();
        }
    }

    public void stop() {
        // 先关闭服务端套件字
        serverChannel.close();
        // 再斩断消息来源，停止io线程池
        group.shutdownGracefully();
        // 最后停止业务线程
        serverHandler.closeGracefully();
    }
}
