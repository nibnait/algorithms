package cc.tianbin.demo.java.反射;

import com.alibaba.fastjson.JSON;
import cc.tianbin.common.model.Person;
import cc.tianbin.common.model.PersonDTO;
import cc.tianbin.common.util.CommonBeanUtil;
import net.sf.cglib.beans.BeanCopier;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nibnait on 2021/01/15
 */
public class CommonBeanUtilTest {

    @Test
    public void testBeanCopier() {
        Person person = new Person();
        person.setName("123");
        person.setAge(1);
        person.setGender((byte)2);
        person.setPhone("13288889999");
        person.setIsBigBoolean(true);
        person.setSmallBoolean(true);

        person.getIsBigBoolean();

        person.isSmallBoolean();

        BeanCopier beanCopier = BeanCopier.create(Person.class, PersonDTO.class, false);
        PersonDTO personDTO = new PersonDTO();
        beanCopier.copy(person, personDTO, null);

        System.out.println(JSON.toJSONString(personDTO));
    }

    @Test
    public void copyProperties() {
        Person source = new Person();
        source.setName("111");
        source.setAge(22);

        PersonDTO personDTO = CommonBeanUtil.copyProperties_methodInvoke(source, PersonDTO::new);

        System.out.println(JSON.toJSONString(personDTO));
    }

    @Test
    public void copyListProperties() {
        List<Person> personList = new ArrayList<>();
        Person p1 = new Person();
        p1.setName("111");
        p1.setAge(22);
        personList.add(p1);
        Person p2 = new Person();
        p2.setName("333");
        p2.setAge(44);
        personList.add(p2);

        List<PersonDTO> personDTOList = CommonBeanUtil.copyListProperties(personList, PersonDTO::new);
        System.out.println(JSON.toJSONString(personDTOList));
    }
}
