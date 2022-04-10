package org.tianbin.java.反射;

import common.model.Person;
import common.model.PersonDTO;
import common.util.CommonBeanUtil;
import io.github.nibnait.common.utils.date.DateTimeCalcUtils;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Created by nibnait on 2021/03/23
 */
public class CopyProperties_性能测试 {

    private static final int cycleTimes = 10000000;

    @Test
    public void main() {
        test_BeanCopier();
        test_BeanUtils();
//        test_fieldGetSet();
//        test_methodInvoke();
//        test_methodInvoke_V2();


        /*
            copyProperties_BeanCopier   960ms
            copyProperties_BeanUtils    2158ms
            copyProperties_fieldGetSet  130803ms
            copyProperties_methodInvoke 45393ms
            copyProperties_methodInvoke_V2 49951ms
         */
    }

    @Test
    public void test_BeanCopier() {
        Person person = new Person();
        person.setName("");
        person.setAge(0);
        person.setGender((byte)0);
        person.setPhone("13288889999");

        LocalDateTime startTime = LocalDateTime.now();
        for (int i = 0; i < cycleTimes; i++) {
            CommonBeanUtil.copyProperties_BeanCopier(person, PersonDTO::new);
        }
        LocalDateTime endTime = LocalDateTime.now();

        System.out.println("copyProperties_BeanCopier " + DateTimeCalcUtils.calcTimeBetween(startTime, endTime).getMillis() + "ms");

    }

    @Test
    public void test_BeanUtils() {
        Person person = new Person();
        person.setName("");
        person.setAge(0);
        person.setGender((byte)0);
        person.setPhone("13288889999");

        LocalDateTime startTime = LocalDateTime.now();
        for (int i = 0; i < cycleTimes; i++) {
            CommonBeanUtil.copyProperties_BeanUtils(person, PersonDTO::new);
        }
        LocalDateTime endTime = LocalDateTime.now();

        System.out.println("copyProperties_BeanUtils " + DateTimeCalcUtils.calcTimeBetween(startTime, endTime).getMillis() + "ms");

    }

    @Test
    public void test_fieldGetSet() {
        Person person = new Person();
        person.setName("");
        person.setAge(0);
        person.setGender((byte)0);
        person.setPhone("13288889999");

        LocalDateTime startTime = LocalDateTime.now();
        for (int i = 0; i < cycleTimes; i++) {
            CommonBeanUtil.copyProperties_fieldGetSet(person, PersonDTO::new);
        }
        LocalDateTime endTime = LocalDateTime.now();

        System.out.println("copyProperties_fieldGetSet " + DateTimeCalcUtils.calcTimeBetween(startTime, endTime).getMillis() + "ms");

    }

    @Test
    public void test_methodInvoke() {
        Person person = new Person();
        person.setName("");
        person.setAge(0);
        person.setGender((byte)0);
        person.setPhone("13288889999");

        LocalDateTime startTime = LocalDateTime.now();
        for (int i = 0; i < cycleTimes; i++) {
            CommonBeanUtil.copyProperties_methodInvoke(person, PersonDTO::new);
        }
        LocalDateTime endTime = LocalDateTime.now();

        System.out.println("copyProperties_methodInvoke " + DateTimeCalcUtils.calcTimeBetween(startTime, endTime).getMillis() + "ms");

    }

    @Test
    public void test_methodInvoke_V2() {
        Person person = new Person();
        person.setName("");
        person.setAge(0);
        person.setGender((byte)0);
        person.setPhone("13288889999");

        LocalDateTime startTime = LocalDateTime.now();
        for (int i = 0; i < cycleTimes; i++) {
            CommonBeanUtil.copyProperties_methodInvoke_V2(person, PersonDTO::new);
        }
        LocalDateTime endTime = LocalDateTime.now();

        System.out.println("copyProperties_methodInvoke_V2 " + DateTimeCalcUtils.calcTimeBetween(startTime, endTime).getMillis() + "ms");

    }
}
