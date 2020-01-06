package netty.rpc.customHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 指数RPC的输入
 * Created by nibnait on 2019-08-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpRequest {
    private int base;
    private int exp;
}
