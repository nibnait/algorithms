package jdk.concurrent.悲观锁vs乐观锁.reentrantLock.线程间的协作;

public class JoinExample {
    static class A extends Thread {
        @Override
        public void run() {
            System.out.println("A");
        }
    }

    static class B extends Thread {

        private A a;

        B(A a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        }
    }

}
