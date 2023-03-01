package cc.tianbin.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

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
    private Byte gender;
    private String phone;
    private Boolean isBigBoolean;
    private boolean isSmallBoolean;
    private List<Integer> children;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public static Person buildByAge(int age) {
        Person person = new Person();
        person.setAge(age);
        return person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}