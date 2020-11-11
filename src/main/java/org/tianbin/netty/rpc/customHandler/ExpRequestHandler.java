package org.tianbin.netty.rpc.customHandler;

import io.netty.channel.ChannelHandlerContext;
import org.tianbin.netty.rpc.server.message.IMessageHandler;
import org.tianbin.netty.rpc.server.message.MessageOutput;

/**
 * Created by nibnait on 2019-08-21
 */
public class ExpRequestHandler implements IMessageHandler<ExpRequest> {

    @Override
    public void handle(ChannelHandlerContext ctx, String requestId, ExpRequest message) {
        int base = message.getBase();
        int exp = message.getExp();
        long start = System.nanoTime();
        long res = 1;
        for (int i = 0; i < exp; i++) {
            res *= base;
        }
        long cost = System.nanoTime() - start;
        ctx.writeAndFlush(new MessageOutput(requestId, "exp_res", new ExpResponse(res, cost)));
    }

}