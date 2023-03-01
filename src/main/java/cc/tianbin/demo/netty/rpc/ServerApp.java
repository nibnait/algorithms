package cc.tianbin.demo.netty.rpc;

import cc.tianbin.demo.netty.rpc.customHandler.ExpRequest;
import cc.tianbin.demo.netty.rpc.customHandler.ExpRequestHandler;
import cc.tianbin.demo.netty.rpc.customHandler.FibRequestHandler;
import cc.tianbin.demo.netty.rpc.server.RPCServer;

/**
 * Created by nibnait on 2019-08-21
 */
public class ServerApp {

    public static void main(String[] args) {
        RPCServer server = new RPCServer("localhost", 8888, 2, 16);
        server.service("fib", Integer.class, new FibRequestHandler()).service("exp", ExpRequest.class,
                new ExpRequestHandler());
        server.start();
    }
}

