package org.tianbin.netty.rpc;

import org.tianbin.netty.rpc.customHandler.ExpRequest;
import org.tianbin.netty.rpc.customHandler.ExpRequestHandler;
import org.tianbin.netty.rpc.customHandler.FibRequestHandler;
import org.tianbin.netty.rpc.server.RPCServer;

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

