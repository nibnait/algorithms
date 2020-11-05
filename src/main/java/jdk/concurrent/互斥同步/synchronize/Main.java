package jdk.concurrent.互斥同步.synchronize;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    @Test
    public void testCase1_1() {
        SynchronizedExample synchronizedExample = new SynchronizedExample();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {synchronizedExample.func1();});
        executorService.execute(() -> {synchronizedExample.func1();});
    }

    @Test
    public void testCase1_2() {
        SynchronizedExample synchronizedExample = new SynchronizedExample();
        SynchronizedExample synchronizedExample2 = new SynchronizedExample();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {synchronizedExample.func1();});
        executorService.execute(() -> {synchronizedExample2.func1();});
    }

    @Test
    public void testCase2_1() {
        SynchronizedExample synchronizedExample = new SynchronizedExample();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {synchronizedExample.func2();});
        executorService.execute(() -> {synchronizedExample.func2();});
    }

    @Test
    public void testCase2_2() {
        SynchronizedExample synchronizedExample = new SynchronizedExample();
        SynchronizedExample synchronizedExample2 = new SynchronizedExample();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {synchronizedExample.func2();});
        executorService.execute(() -> {synchronizedExample2.func2();});
    }

    @Test
    public void testCase3_1() {
        SynchronizedExample synchronizedExample = new SynchronizedExample();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {synchronizedExample.func3();});
        executorService.execute(() -> {synchronizedExample.func3();});
    }

    @Test
    public void testCase3_2() {
        SynchronizedExample synchronizedExample = new SynchronizedExample();
        SynchronizedExample synchronizedExample2 = new SynchronizedExample();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {synchronizedExample.func3();});
        executorService.execute(() -> {synchronizedExample2.func3();});
    }

    @Test
    public void testCase4_1() {
        SynchronizedExample synchronizedExample = new SynchronizedExample();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {synchronizedExample.func4();});
        executorService.execute(() -> {synchronizedExample.func4();});
    }

    @Test
    public void testCase4_2() {
        SynchronizedExample synchronizedExample = new SynchronizedExample();
        SynchronizedExample synchronizedExample2 = new SynchronizedExample();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {synchronizedExample.func4();});
        executorService.execute(() -> {synchronizedExample2.func4();});
    }



}
