package netty.rpc.client.request;

/**
 * 定义客户端异常，用于统一抛出RPC错误
 * Created by nibnait on 2019-08-21
 */
public class RPCException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RPCException(String message, Throwable cause) {
        super(message, cause);
    }

    public RPCException(String message) {
        super(message);
    }

    public RPCException(Throwable cause) {
        super(cause);
    }
}
