package netty.webSocket.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by nibnait on 2019-08-21
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    //定义Channel组
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String message) throws Exception {

        channelGroup.forEach(ch -> {
            ch.writeAndFlush(message+"\n");
        });
    }

    /**
     * 通道被添加
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler added");
        super.handlerAdded(ctx);

        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(channel.remoteAddress() + " 进入了聊天室");
    }

    /**
     * 通道被注册是的回调方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Registered");
        super.channelRegistered(ctx);
    }

    /**
     * 通道被注销是的回调方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Unregistered");
        super.channelUnregistered(ctx);
    }

    /**
     * 通道活动状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Active");
        super.channelActive(ctx);

        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 上线了");
    }

    /**
     * 非活动状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Inactive");
        super.channelInactive(ctx);
    }

    /**
     * 读取完成
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel ReadComplete");
        super.channelReadComplete(ctx);

        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 下线了");
    }

    /**
     * handler移除
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler Removed");
        super.handlerRemoved(ctx);

        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(channel.remoteAddress() + " 离开了聊天室");
    }
}
