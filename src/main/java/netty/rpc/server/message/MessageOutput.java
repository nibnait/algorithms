package netty.rpc.server.message;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by nibnait on 2019-08-21
 */
@Data
@AllArgsConstructor
public class MessageOutput {

    private String requestId;
    private String type;
    private Object payload;
}
