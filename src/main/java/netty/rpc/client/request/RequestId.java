package netty.rpc.client.request;

import java.util.UUID;

/**
 * 请求ID生成器
 * Created by nibnait on 2019-08-21
 */
public class RequestId {
    public static String next() {
        return UUID.randomUUID().toString();
    }

}
