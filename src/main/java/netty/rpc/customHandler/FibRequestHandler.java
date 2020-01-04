package netty.rpc.customHandler;

import io.netty.channel.ChannelHandlerContext;
import netty.rpc.server.message.IMessageHandler;
import netty.rpc.server.message.MessageOutput;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nibnait on 2019-08-21
 */
public class FibRequestHandler implements IMessageHandler<Integer> {

    private List<Long> fibs = new ArrayList<>();

    {
        fibs.add(1L); // fib(0) = 1
        fibs.add(1L); // fib(1) = 1
    }

    @Override
    public void handle(ChannelHandlerContext ctx, String requestId, Integer n) {
        for (int i = fibs.size(); i < n + 1; i++) {
            long value = fibs.get(i - 2) + fibs.get(i - 1);
            fibs.add(value);
        }
        ctx.writeAndFlush(new MessageOutput(requestId, "fib_res", fibs.get(n)));
    }

}