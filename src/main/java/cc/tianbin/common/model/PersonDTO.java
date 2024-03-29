package cc.tianbin.common.model;

import lombok.Data;

/**
 * 实体类
 * Created by nibnait on 2020-02-24
 */
@Data
public class PersonDTO {

    private String name;
    private Integer age;
    private Byte gender;
    private String phone;
    private Boolean isBigBoolean;
    private boolean isSmallBoolean;
}