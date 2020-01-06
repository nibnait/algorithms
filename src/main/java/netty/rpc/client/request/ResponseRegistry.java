package netty.rpc.client.request;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应类型注册中心
 * Created by nibnait on 2019-08-21
 */
public class ResponseRegistry {
    private static Map<String, Class<?>> clazzes = new HashMap<>();
    public static void register(String type, Class<?> clazz) {
        clazzes.put(type, clazz);
    }
    public static Class<?> get(String type) {
        return clazzes.get(type);
    }

}
