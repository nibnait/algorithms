package common.util.validate.context;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 内部用验证结果
 *
 */
@Data
public class ValidateResult {

    /**
     * 是否成功，一旦发生错误，即置为false，默认为{@value}
     */
    private boolean isSuccess = true;

    /**
     * 验证错误
     */
    private List<String> errorMsg = new ArrayList<>();

    /**
     * 添加错误
     *
     * @param error 错误
     */
    public void addError(String error) {
        errorMsg.add(error);
    }

}
