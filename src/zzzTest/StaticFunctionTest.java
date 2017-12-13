package zzzTest;

import java.util.List;

public class StaticFunctionTest {
    static class test {
        int id;
        String name;

        public test(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "test{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        String xx = null;

        test t = new test(1, xx);
        System.out.println(t.toString());
        setTestId(t);
        System.out.println(t.toString());
    }

    public static void setTestId(test t) {
        t.setId(2);
    }
}
