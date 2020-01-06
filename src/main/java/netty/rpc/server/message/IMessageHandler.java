package netty.rpc.server.message;

import io.netty.channel.ChannelHandlerContext;

/**
 * 消息处理器接口，每个自定义服务必须实现handle方法
 * Created by nibnait on 2019-08-21
 */
public interface IMessageHandler<T> {
    void handle(ChannelHandlerContext ctx, String requestId, T message);
}
