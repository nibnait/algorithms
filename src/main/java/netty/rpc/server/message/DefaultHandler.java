package netty.rpc.server.message;

import io.netty.channel.ChannelHandlerContext;

/**
 * 找不到类型的消息统一使用默认处理器处理
 * Created by nibnait on 2019-08-21
 */
public class DefaultHandler implements IMessageHandler<MessageInput> {

    @Override
    public void handle(ChannelHandlerContext ctx, String requestId, MessageInput message) {
        System.out.println("unrecognized message type=" + message.getType() + " comes");
    }
}
