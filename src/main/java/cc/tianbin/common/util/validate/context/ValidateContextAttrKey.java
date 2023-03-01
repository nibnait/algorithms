package cc.tianbin.common.util.validate.context;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * Created by nibnait on 2021/08/22
 */
@Getter
@AllArgsConstructor
public enum ValidateContextAttrKey {

    TAG_NAME_MAP("tagMap", Map.class),

    ;

    private String description;
    private Class attrClass;

}
