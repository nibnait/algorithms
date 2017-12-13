package zzzTest;


import java.io.IOException;

public class MainTestFuncation {

    static class Person {
        int age;
        String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name=" + name +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        Person p = new Person();
        p.setAge(1);
        p.setName("kkk");
        System.out.println(p.toString());
        setPerson(p);
        System.out.println(p.toString());
    }

    private static void setPerson(Person p) {
        p.setAge(2);
        p.setName("sdfa");
    }
}

