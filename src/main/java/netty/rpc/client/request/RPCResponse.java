package netty.rpc.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * RPC响应对象
 * Created by nibnait on 2019-08-21
 */
@Data
@AllArgsConstructor
public class RPCResponse {
    private String requestId;
    private String type;
    private Object payload;
}
