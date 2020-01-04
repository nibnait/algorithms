package netty.rpc.customHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 指数RPC的输出
 * Created by nibnait on 2019-08-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpResponse {
    private long value;
    private long costInNanos;
}
