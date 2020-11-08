package jdk.concurrent.悲观锁vs乐观锁.CAS;

import common.model.Person;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by nibnait on 2020/11/8
 */
public class AtomicReferenceExample {

    private Person person;
    private AtomicReference<Person> atomicPerson;

    @Test
    public void testAtomicPerson() throws InterruptedException {
        person = new Person("Tom", 18);
        atomicPerson = new AtomicReference<>(person);

        System.out.println("Atomic Person is " + atomicPerson.get().toString());

        Thread t1 = new Thread(new Task3());
        Thread t2 = new Thread(new Task4());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Now Atomic Person is " + atomicPerson.get().toString());

    }

    class Task3 implements Runnable {
        public void run() {
            atomicPerson.getAndSet(new Person("Tom3", atomicPerson.get().getAge() + 1));

            System.out.println("Thread3 Values " + atomicPerson.toString());
        }
    }

    class Task4 implements Runnable {
        public void run() {
            atomicPerson.getAndSet(new Person("Tom4", atomicPerson.get().getAge() + 2));

            System.out.println("Thread4 Values " + atomicPerson.toString());
        }
    }

    @Test
    public void testPerson() throws InterruptedException {
        person = new Person("Tom", 18);
        System.out.println("Person is " + person.toString());

        Thread t1 = new Thread(new Task1());
        Thread t2 = new Thread(new Task2());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Now Person is " + person.toString());
    }

    class Task1 implements Runnable {
        public void run() {
            person.setAge(person.getAge() + 1);
            person.setName("Tom1");

            System.out.println("Thread1 Values " + person.toString());
        }
    }

    class Task2 implements Runnable {
        public void run() {
            person.setAge(person.getAge() + 2);
            person.setName("Tom2");

            System.out.println("Thread2 Values "
                    + person.toString());
        }
    }

}
