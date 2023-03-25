## Java中有哪几种方式来创健线程执行任务？

### 1. 继承 Thread 类  
[ThreadDemo1](./ThreadDemo1.java)

总结：重写的是run()方法，而不是start()方法，但是占用了继承的名额，Java中的类是单继承的。

### 2. 实现 Runnable 接口  
[ThreadDemo2](./ThreadDemo2.java)

总结：实现Runnable接口，实现run()方法，使用依然要用到Thread，这种方式更常用  

### 3. 实现 Callable 接口  
[ThreadDemo3](./ThreadDemo3.java)

总结：实现Callable接口，实现call()方法，得使用Thread+FutureTask配合，这种方式支持拿到异步执行任务的结果

### 4. 利用线程池来创建线程  
[ThreadDemo4](./ThreadDemo4.java)

总结：实现 Callable 接口或者 Runnable 接口都可以
注意，工作中不建议使用 Executors 来创建线程池

**总结**
以上几种方式，底层都是基于 Runnable。

## 为什么不建议使用 Executors 来创建线程池？
### 1. FixedThreadPool  
当我们使用 Executors 创建 FixedThreadPool 时，对应的构造方法为：
```java
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
    }
```
发现创建的队列为LinkedBlockingQueue,是一个无界阻塞队列，如果使用该线程池执行任务，如果任务过多就会不断的添加到队列中，任务越多占用的内存就越多，最终可能耗尽内存，导致 OOM。

### 2. SingleThreadExecutor  
当我们使用Executors创建SingleThreadExecutor时，对应的构造方法为：
```java
    public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
    }
```    
也是LinkedBlockingQueue,所以同样可能会耗尽内存。

**总结**
除开有可能造成 OOM 之外，我们使用 Executors 来创建线程池也不能自定义线程的名字，不利于排查问题，所以建议直接使用ThreadPoolExecutor来定义线程池，这样可以灵活控制。

## 线程池有哪几种状态？每种状态分别表示什么？
### 1.RUNNING
`Accept new tasks and process queued tasks`
表示线程池正常运行，既能接受新任务，也会正常处理队列中的任务
### 2.SHUTDOWN
`Don't accept new tasks, but process queued tasks`
当调用线程池的 shutdown()方法时，线程池就进入SHUTDOWN状态，表示线程池处于正在关闭状态，此状态下线程池不会接受新任务，但是会继续把队列中的任务处理完
### 3.STOP
`Don't accept new tasks,don't process queued tasks, and interrupt in-progress tasks`
当调用线程池的 shutdownnow()方法时，线程池就进入 STOP 状态，表示线程池处于正在停止状态，此状态下线程池既不会接受新任务了，也不会处理队列中的任务，并且正在运行的线程也会被中断
### 4.TIDYING
`All tasks have terminated, workerCount is zero, the thread transitioning to state TIDYING will run the terminated()hook method`  
线程池中没有线程在运行后，线程池的状态就会自动变为 TIDYING，并且会调用 terminated()，该方法是空方法，留给程序员进行扩展。
### 5.TERMINATED
`terminated()has completed`  
terminated() 方法执行完之后，线程池状态就会变为 TERMINATED


## Synchronized 和 ReentrantLock有哪些不同点？

| sychronized                      | ReentrantLock                    |
| -------------------------------- | -------------------------------- |
| Java中的一个关键字               | JDK提供的一个类                  |
| 自动加锁与释放锁                 | 需要手动加锁与释放锁             |
| JVM层面的锁                      | API层面的锁                      |
| 非公平锁                         | 公平锁或非公平锁                 |
| 锁的是对象，锁信息保存在对象头中 | int类型的state标识来标识锁的状态 |
| 底层有锁升级过程                 | 没有锁升级过程                   |

## Java死锁如何避免？
造成死锁的几个原因：  
1. 一个资源每次只能被一个线程使用
2. 一个线程在阻塞等待某个资源时，不释放已占有资源
3. 一个线程已经获得的资源，在未使用完之前，不能被强行剥夺
4. 若干线程形成头尾相接的循环等待资源关系

这是造成死锁必须要达到的4个条件，如果要避免死锁，只需要不满足其中某一个条件即可。而其中前3个条件是作为锁要符合的条件，所以要避免死锁就需要打破第4个条件，不出现循环等待锁的关系。

在开发过程中：  
1. 要注意加锁顺序，保证每个线程按同样的顺序进行加锁
2. 要注意加锁时限，可以针对所设置一个超时时间
3. 要注意死锁检查，这是一种预防机制，确保在第一时间发现死锁并进行解决


## 线程池的底层工作原理
线程池内部是通过队列+线程实现的，当我们利用线程池执行任务时：
1.  ﻿﻿如果此时线程池中的线程数量小于corePoolsize，即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务。
2.  ﻿﻿﻿如果此时线程池中的线程数量等于corePoolsize， 但是缓冲队列workQueue末满，那么任务被放入缓冲队列。
3.  ﻿﻿如果此时线程池中的线程数量大于等于corePooisize，缓冲队列workQueue满，并且线程池中的数量小于maximumPoolsize，建新的线程来处理被添加的任务：  
4. 如果此时线程池中的线程数量大于corePoolsize，缓中队列workQueue满，并且线程池中的数量等于maximumPoolSize，那么通过 handler 所指定的策略来处理此任务。
5. 当线程池中的线程数量大于 corePoolsize 时，如果某线程空闲时间超过 keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数

## 线程池为什么是先添加列队而不是先创建最大线程？

当线程池中的核心线程都在忙时，如果继续往线程池中添加任务，那么任务会先放入队列，队列满了之后，才会新开线程。这就相当于—个公司本来有10个程序员，本来这10个程序员能正常的处理各种需求，但是随着公司的发展，需求在慢慢的增加，但是一开始这些需求只会增加在待开发列表中，然后这10个程序员加班加点的从待开发列表中获取需求并进行处理，但是某一天待开发列表满了，公司发现现有的10个程序员是真的处理不过来了，所以就开始新招员工了。





