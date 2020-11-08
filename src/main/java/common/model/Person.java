package common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类
 * Created by nibnait on 2020-02-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String name;
    private Integer age;

}