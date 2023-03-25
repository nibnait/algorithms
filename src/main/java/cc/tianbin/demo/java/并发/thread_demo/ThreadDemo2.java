package cc.tianbin.demo.java.并发.thread_demo;

/**
 * Created by nibnait on 2023/03/06
 */
public class ThreadDemo2 implements Runnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadDemo2());

        thread.start();
    }

    @Override
    public void run() {
        System.out.println("hello world");
    }
}
