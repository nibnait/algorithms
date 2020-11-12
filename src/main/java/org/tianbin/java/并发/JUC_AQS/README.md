## CountDownLatch

用来控制一个或者多个线程等待多个线程。

维护了一个计数器 cnt，每次调用 countDown() 方法会让计数器的值减 1，减到 0 的时候，那些因为调用 await() 方法而在等待的线程就会被唤醒。

所有线程自己执行自己的，每个线程 只要调用一次 countDownLatch.countDown(); 计数器就减一  
走到 countDownLatch.await(); 时，快的线程会等待慢的线程，执行完。  
再继续往下走。  

或者 当 countDown减到0时，即使慢的线程还没执行完，也会继续往下走。。不等了。。。

## CyclicBarrier

用来控制多个线程互相等待，只有当多个线程都到达时，这些线程才会继续执行。

和 CountdownLatch 相似，都是通过维护计数器来实现的。线程执行 await() 方法之后计数器会减 1，并进行等待，直到计数器为 0，所有调用 await() 方法而在等待的线程才能继续执行。

CyclicBarrier 和 CountdownLatch 的一个区别是，CyclicBarrier 的计数器通过调用 reset() 方法可以循环使用，所以它才叫做循环屏障。

cyclicBarrier.await() 之后，线程进入主动等待状态。  
直到 cyclicBarrier计数器归零。

主线程 和 线程池中的所有线程继续并发执行

## Semaphore

Semaphore 类似于操作系统中的信号量，可以控制对互斥资源的访问线程数。

