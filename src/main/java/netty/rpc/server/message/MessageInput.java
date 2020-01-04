package netty.rpc.server.message;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

/**
 * Created by nibnait on 2019-08-21
 */
@Data
@AllArgsConstructor
public class MessageInput {

    private String type;
    private String requestId;
    private String payload;

    // 因为我们想直接拿到对象，所以要提供对象的类型参数
    public <T> T getPayload(Class<T> clazz) {
        if (payload == null) {
            return null;
        }
        return JSON.parseObject(payload, clazz);
    }


}
