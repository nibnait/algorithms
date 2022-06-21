package cc.tianbin.java.集合;

import com.google.common.collect.Lists;
import common.model.Person;
import io.github.nibnait.common.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by nibnait on 2021/05/20
 */
@Slf4j
public class ListStreamTest {

    @Test
    public void groupBy() {
        List<Person> personList = Lists.newArrayList(
                new Person("tom", 1),
                new Person("jerry", 2),
//                new Person(null, 4),
                new Person("", 3)
        );

        Map<String, List<Integer>> owner2ItemsIdMap = personList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.mapping(Person::getAge, Collectors.toList())));

        log.info(DataUtils.toJsonStringObject(owner2ItemsIdMap));

    }


    @Test
    public void comparing() {
        List<Person> personList = new ArrayList<>();

        Person p1 = new Person();
        p1.setAge(1);
        personList.add(p1);
        Person p3 = new Person();
        p3.setAge(3);
        personList.add(p3);
        Person p2 = new Person();
        p2.setAge(2);
        personList.add(p2);

        System.out.println(personList);
        List<Integer> collect = personList.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .map(Person::getAge)
                .collect(Collectors.toList());
        System.out.println("(默认)正序: " + collect);

        List<Integer> collectReversed = personList.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .map(Person::getAge)
                .collect(Collectors.toList());
        System.out.println("倒序: "+ collectReversed);
    }

}
