package cc.tianbin.netty.rpc.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * RPC请求对象
 * Created by nibnait on 2019-08-21
 */
@Data
@AllArgsConstructor
public class RPCRequest {
    private String requestId;
    private String type;
    private Object payload;
}
