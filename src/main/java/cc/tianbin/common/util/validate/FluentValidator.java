package cc.tianbin.common.util.validate;

import cc.tianbin.common.context.ApplicationContextHelper;
import cc.tianbin.common.util.validate.context.ValidateContext;
import cc.tianbin.common.util.validate.context.ValidateContextAttrKey;
import cc.tianbin.common.util.validate.context.ValidateElement;
import cc.tianbin.common.util.validate.context.ValidateResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 链式调用验证器，参考fluent-validator的简单实现
 * https://github.com/neoremind/fluent-validator
 */
@Slf4j
public class FluentValidator {
    /**
     * 验证器链，惰性求值期间就是不断的改变这个链表，及时求值期间就是遍历链表依次执行验证
     */
    private List<ValidateElement> validateElementList = new ArrayList<>();

    /**
     * 验证器上下文
     */
    private ValidateContext context = new ValidateContext();

    /**
     * 私有构造方法,只能通过checkAll创建对象
     */
    private FluentValidator() {
    }

    /**
     * 创建FluentValidator对象
     *
     * @return
     */
    public static FluentValidator checkAll() {
        return new FluentValidator();
    }

    /**
     * 使用验证器进行验证
     *
     * @param validator 验证器
     * @return
     */
    public <T> FluentValidator on(Class<T> validator) {
        validateElementList.add(new ValidateElement(null, validator));
        return this;
    }

    /**
     * 使用验证器验证指定对象
     *
     * @param t         待验证对象
     * @param validator 验证器
     * @return
     */
    public <T, V> FluentValidator on(T t, Class<V> validator) {
        validateElementList.add(new ValidateElement(t, validator));
        return this;
    }

    /**
     * 使用验证器验证指定对象
     *
     * @param t         待验证对象
     * @param validator 验证器
     * @param condition 条件，为true时才会将验证器加入验证器列表中
     * @return
     */
    public <T, V> FluentValidator on(T t, Class<V> validator, boolean condition) {
        if (condition) {
            validateElementList.add(new ValidateElement(t, validator));
        }
        return this;
    }

    /**
     * 执行各个验证器中的验证逻辑
     *
     * @return
     */
    public FluentValidator doValidate() {
        if (CollectionUtils.isEmpty(validateElementList)) {
            log.info("Nothing to validate");
            return null;
        }
        try {
            for (ValidateElement element : validateElementList) {
                Object target = element.getTarget();
                Validator validator = (Validator) ApplicationContextHelper.getBean(element.getValidator());
                validator.validate(context, target);
            }
        } catch (Exception e) {
            log.error("FluentValidator.doValidate error ", e);
            throw e;
        }
        return this;
    }

    /**
     * 转换为对外的验证结果，在<code>FluentValidator.on(..).on(..).doValidate()</code>这一连串计算后的“及时求值”收殓出口。
     *
     * @return 对外验证结果
     */
    public ValidateResult result() {
        return context.result();
    }

    /**
     * 将键值对放入上下文
     *
     * @param key   键
     * @param value 值
     * @return FluentValidator
     */
    public FluentValidator putAttribute2Context(ValidateContextAttrKey key, Object value) {
        if (context == null) {
            context = new ValidateContext();
        }
        context.setAttribute(key, value);
        return this;
    }

    /**
     * 获取验证器上下文
     */
    public ValidateContext getContext() {
        return context;
    }

}
