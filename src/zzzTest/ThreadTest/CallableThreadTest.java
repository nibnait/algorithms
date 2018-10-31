package zzzTest.ThreadTest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.*;

public class CallableThreadTest {

    public static void main(String[] args) {

        MyCallableThread callableThread = new MyCallableThread(new Person("张老三", 99));
        // Callable需要在线程池中执行，还有一种方法，见下文。
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Person> result = executor.submit(callableThread);
        try {
            // main线程会在这里等待，直到callableThread任务执行完成
            String name = result.get().getName();
            System.out.println(name);
            // 停止线程池。
            executor.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}

@AllArgsConstructor
class MyCallableThread implements Callable<Person> {
    private Person p;
    @Override
    public Person call() throws Exception {
        return this.p;
    }

}

@Data
@AllArgsConstructor
class Person {
    public String name;
    public int age;
}