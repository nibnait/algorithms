package netty.rpc;

import netty.rpc.client.RPCClient;
import netty.rpc.client.request.RPCException;
import netty.rpc.customHandler.ExpRequest;
import netty.rpc.customHandler.ExpResponse;

/**
 * Created by nibnait on 2019-08-21
 */
public class ClientApp {

    private RPCClient client;

    public ClientApp(RPCClient client) {
        this.client = client;
        this.client.rpc("fib_res", Long.class).rpc("exp_res", ExpResponse.class);
    }

    public long fib(int n) {
        return (Long) client.send("fib", n);
    }

    public ExpResponse exp(int base, int exp) {
        return (ExpResponse) client.send("exp", new ExpRequest(base, exp));
    }

    public static void main(String[] args) throws InterruptedException {
        RPCClient client = new RPCClient("localhost", 8888);
        ClientApp demo = new ClientApp(client);
        for (int i = 0; i < 30; i++) {
            try {
                System.out.printf("fib(%d) = %d\n", i, demo.fib(i));
                Thread.sleep(100);
            } catch (RPCException e) {
                i--; // retry
            }
        }
        for (int i = 0; i < 30; i++) {
            try {
                ExpResponse res = demo.exp(2, i);
                Thread.sleep(100);
                System.out.printf("exp2(%d) = %d cost=%dns\n", i, res.getValue(), res.getCostInNanos());
            } catch (RPCException e) {
                i--; // retry
            }
        }
        client.close();
    }
}
