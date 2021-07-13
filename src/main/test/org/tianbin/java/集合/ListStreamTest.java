package org.tianbin.java.集合;

import common.model.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nibnait on 2021/05/20
 */
public class ListStreamTest {

    @Test
    public void test01() {


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
        System.out.println(collect);

        List<Integer> collectReversed = personList.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .map(Person::getAge)
                .collect(Collectors.toList());
        System.out.println(collectReversed);
    }

}
