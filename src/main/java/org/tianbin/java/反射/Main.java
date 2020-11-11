package org.tianbin.java.反射;

import junit.framework.TestCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.lang.reflect.Field;

/*

 */
public class Main extends TestCase {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Person {
        public int age;

        public String phone;

        String getName() {
            return "xxx";
        }

        String getAddress() {
            return "lll";
        }

    }

    @Test
    public void testCase() throws IllegalAccessException {
        Field[] fields = null;
        Class[] declaredClasses = null;
        try {
            Class clz = Class.forName(Person.class.getName());
            fields = clz.getDeclaredFields();
            declaredClasses = clz.getDeclaredClasses();
        } catch (Exception e) {

        }

        if (declaredClasses != null) {
            for (int i = 0; i < declaredClasses.length; i++) {
                String name = declaredClasses[i].getName();
                System.out.println(name);
            }
        }


        Person person = new Person();
        person.setAge(1);
        if (fields != null) {
            for (int i = 0; i < fields.length; i++) {
                String name = fields[i].getName();
                System.out.println(name);
                String age = String.valueOf(fields[i].get(person));
                System.out.println(age);
//                fields[i].setInt(person, 2);
//                fields[i].setAccessible(true);
            }
        }

    }
}
