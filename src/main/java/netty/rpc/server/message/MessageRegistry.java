package netty.rpc.server.message;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息类型注册中心
 * Created by nibnait on 2019-08-21
 */
public class MessageRegistry {
    private Map<String, Class<?>> clazzes = new HashMap<>();

    public void register(String type, Class<?> clazz) {
        clazzes.put(type, clazz);
    }

    public Class<?> get(String type) {
        return clazzes.get(type);
    }
}
