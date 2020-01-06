package jdk.proxy.cglib;

import lombok.Data;

/**
 * 没有实现接口，需要CGlib动态代理的目标类
 *
 * @author nibnait
 * @version $Id: Person.java, v 0.1 2019-09-02 下午3:37 nibnait Exp $$
 */
@Data
public class Person {

    private String name;

    public Person() {
        this.name = "Jerry";
    }

    public String eat(String food) {
        System.out.println(this.name + " eat " + food);
        return food;
    }

    public int walk(int step) {
        System.out.println(this.name + " walked " + step + " step");
        return step;
    }

    public int sleep(int count) {
        System.out.println(this.name + " sleeped " + count + " hour");
        return count;
    }

    final public int play(int count) {
        System.out.println(this.name + " played " + count + " hour");
        return count;
    }

}