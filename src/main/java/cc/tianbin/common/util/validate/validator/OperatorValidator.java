package cc.tianbin.common.util.validate.validator;

import cc.tianbin.common.model.Person;
import cc.tianbin.common.util.validate.Validator;
import cc.tianbin.common.util.validate.context.ValidateContext;
import io.github.nibnait.common.exception.ClientViewException;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by nibnait on 2021/08/22
 */
public class OperatorValidator implements Validator<Person> {

    /**
     * 执行验证
     * <p/>
     * 如果发生错误内部需要调用{@link ValidateContext#addErrorMsg(String)}方法，也即<code>context.addErrorMsg(String)
     * </code>来添加错误，该错误会被添加到结果存根{@link ValidateContext#result()} 的错误消息列表中。
     *
     * @param context 验证上下文
     * @param person  待验证对象
     */
    @Override
    public void validate(ValidateContext context, Person person) {
        if (StringUtils.isBlank(person.getName())) {
            throw new ClientViewException("用户名 不能为空");
        }
    }
}
