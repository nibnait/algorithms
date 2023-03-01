package cc.tianbin.common.util.validate.context;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 验证器包装类
 * Created by nibnait on 2021/08/22
 */
@Getter
@AllArgsConstructor
public class ValidateElement<V> {
    /**
     * 待验证对象
     */
    private Object target;

    /**
     * 验证器
     */
    private Class<V> validator;

}
