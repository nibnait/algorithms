package common.util.validate;

import common.util.validate.context.ValidateContext;

/**
 * 验证器接口。
 * <p/>
 * 泛型<t>T</t>表示待验证对象的类型
 *
 */
public interface Validator<T> {

    /**
     * 执行验证
     * <p/>
     * 如果发生错误内部需要调用{@link ValidateContext#addErrorMsg(String)}方法，也即<code>context.addErrorMsg(String)
     * </code>来添加错误，该错误会被添加到结果存根{@link ValidateContext#result()} 的错误消息列表中。
     *
     * @param context 验证上下文
     * @param t       待验证对象
     */
    void validate(ValidateContext context, T t);

}
