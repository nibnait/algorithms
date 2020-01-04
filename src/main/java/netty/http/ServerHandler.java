package netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author nibnait
 * @version $Id: ClientHandler.java, v 0.1 2019-08-19 下午8:47 nibnait Exp $$
 */
public class ServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    /**
     * 真正处理HTTP请求
     * @param channelHandlerContext
     * @param message
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject message) throws Exception {
        if (message instanceof HttpRequest) {
            //HttpRequest 可以直接获取请求路径
            HttpRequest request = (HttpRequest) message;
            String uri = request.uri();
            System.out.println("URI: " + uri);

            //构造响应内容
            ByteBuf content = Unpooled.copiedBuffer("Hello world", CharsetUtil.UTF_8);
            //构造响应对象，并设置HTTP版本，设置响应状态
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            //构造响应头信息
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            channelHandlerContext.writeAndFlush(response);
        } else {
            System.out.println("*********");
        }
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
    }

    /**
     * 通道被注册是的回调方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("serverChannel Registered");
        super.channelRegistered(ctx);
    }

    /**
     * 通道被注销是的回调方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("serverChannel Unregistered");
        super.channelUnregistered(ctx);
    }

    /**
     * 通道活动状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("serverChannel Active");
        super.channelActive(ctx);
    }

    /**
     * 非活动状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("serverChannel Inactive");
        super.channelInactive(ctx);
    }

    /**
     * 读取完成
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("serverChannel ReadComplete");
        super.channelReadComplete(ctx);
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
    }
}