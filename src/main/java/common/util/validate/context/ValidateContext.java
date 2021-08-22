package common.util.validate.context;

import org.apache.commons.collections4.MapUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证器在执行调用过程中的上下文
 * 1.验证器中的数据传递共享
 * 2.验证结果数据缓存以作后续使用
 * Created by nibnait on 2021/08/22
 */
public class ValidateContext {
    /**
     * 验证器均可以共享使用的属性键值对
     */
    private Map<ValidateContextAttrKey, Object> attributes;

    /**
     * 调用结果对象
     */
    public ValidateResult result = new ValidateResult();

    /**
     * 添加错误信息
     *
     * @param msg 错误信息
     */
    public void addErrorMsg(String msg) {
        result.addError(msg);
    }

    public ValidateResult result() {
        return result;
    }

    /**
     * 设置属性值
     *
     * @param key   键
     * @param value 值
     */
    public void setAttribute(ValidateContextAttrKey key, Object value) {
        if (attributes == null) {
            attributes = new HashMap<>();
        }
        attributes.put(key, value);
    }

    /**
     * 获取对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T get(ValidateContextAttrKey key) {
        return (T) getAttribute(key);
    }
    
    /**
     * 获取属性
     *
     * @param key 键
     * @return 值
     */
    public Object getAttribute(ValidateContextAttrKey key) {
        if (MapUtils.isNotEmpty(attributes)) {
            return attributes.get(key);
        }
        return null;
    }

    /**
     * 获取String值
     *
     * @param key
     * @return
     */
    public String getString(ValidateContextAttrKey key) {
        return (String) getAttribute(key);
    }

    /**
     * 获取Integer值
     *
     * @param key
     * @return
     */
    public Integer getInteger(ValidateContextAttrKey key) {
        return (Integer) getAttribute(key);
    }

    /**
     * 获取Boolean值
     *
     * @param key
     * @return
     */
    public Boolean getBoolean(ValidateContextAttrKey key) {
        return (Boolean) getAttribute(key);
    }


    /**
     * 获取Long值
     *
     * @param key
     * @return
     */
    public Long getLong(ValidateContextAttrKey key) {
        return (Long) getAttribute(key);
    }

    /**
     * 获取BigDecimal值
     *
     * @param key
     * @return
     */
    public BigDecimal getBigDecimal(ValidateContextAttrKey key) {
        return (BigDecimal) getAttribute(key);
    }
    
}
