package netty.rpc.server.message;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nibnait on 2019-08-21
 */
public class MessageHandlers extends TestCase {

    private Map<String, IMessageHandler<?>> handlers = new HashMap<>();
    public DefaultHandler defaultHandler = new DefaultHandler();

    public void register(String type, IMessageHandler<?> handler) {
        handlers.put(type, handler);
    }

    public IMessageHandler<?> get(String type) {
        IMessageHandler<?> handler = handlers.get(type);
        return handler;
    }


}
