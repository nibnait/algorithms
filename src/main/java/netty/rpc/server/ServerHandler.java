package netty.rpc.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.rpc.server.message.IMessageHandler;
import netty.rpc.server.message.MessageHandlers;
import netty.rpc.server.message.MessageInput;
import netty.rpc.server.message.MessageRegistry;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

/**
 * Created by nibnait on 2019-08-21
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    //业务线程池
    private ThreadPoolExecutor executor;
    private MessageHandlers handlers;
    private MessageRegistry registry;

    //业务队列最大1000，避免堆积
    private static final int ARRAY_BLOCKING_QUEUE_SIZE = 1000;

    public ServerHandler(MessageHandlers handlers, MessageRegistry registry, int workerThreads) {
        this.handlers = handlers;
        this.registry = registry;

        ThreadFactory factory = new ThreadFactory() {
            AtomicInteger seq = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("rpc-"+seq.getAndIncrement());
                return thread;
            }
        };

        this.executor = new ThreadPoolExecutor(1, workerThreads,
                30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(ARRAY_BLOCKING_QUEUE_SIZE),
                factory,
                new CallerRunsPolicy());    //如果子线程处理不过来，io线程也会加入处理业务逻辑
    }

    public void closeGracefully() {
        // 优雅一点关闭，先通知，再等待，最后强制关闭
        this.executor.shutdown();
        try {
            this.executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
        this.executor.shutdownNow();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 客户端来了一个新链接
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " connection comes");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 客户端走了一个
        System.out.println("connection leaves");
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof MessageInput) {
            System.out.println("read a message");
            // 用业务线程池处理消息
            this.executor.execute(() -> {
                this.handleMessage(ctx, (MessageInput) msg);
            });
        }
    }

    private void handleMessage(ChannelHandlerContext ctx, MessageInput msg) {
        //业务逻辑在这里
        Class<?> clazz = registry.get(msg.getType());
        if (clazz == null) {
            //没注册的消息用默认的处理器处理
            handlers.defaultHandler.handle(ctx, msg.getRequestId(), msg);
            return;
        }

        Object payload = msg.getPayload(clazz);
        IMessageHandler<Object> handler = (IMessageHandler<Object>) handlers.get(msg.getType());
        if (handler != null) {
            handler.handle(ctx, msg.getRequestId(), payload);
        } else {
            // 用默认的处理器处理吧
            handlers.defaultHandler.handle(ctx, msg.getRequestId(), msg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 此处可能因为客户端机器突发重启
        // 也可能是客户端链接闲置时间超时，后面的ReadTimeoutHandler抛出来的异常
        // 也可能是消息协议错误，序列化异常
        // etc.
        // 不管它，链接统统关闭，反正客户端具备重连机制
        System.out.println("connection error");
        cause.printStackTrace();
        ctx.close();

    }
}
