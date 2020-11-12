乐观锁与悲观锁是一种广义上的概念，体现了看待线程同步的不同角度。在Java和数据库中都有此概念对应的实际应用。

先说概念。对于同一个数据的并发操作，悲观锁认为自己在使用数据的时候一定有别的线程来修改数据，因此在获取数据的时候会先加锁，确保数据不会被别的线程修改。Java中，synchronized关键字和Lock的实现类都是悲观锁。

而乐观锁认为自己在使用数据时不会有别的线程修改数据，所以不会添加锁，只是在更新数据的时候去判断之前有没有别的线程更新了这个数据。如果这个数据没有被更新，当前线程将自己修改的数据成功写入。如果数据已经被其他线程更新，则根据不同的实现方式执行不同的操作（例如报错或者自动重试）。

乐观锁在Java中是通过使用无锁编程来实现，最常采用的是CAS算法，Java原子类中的递增操作就通过CAS自旋实现的。

## 比较

 - 悲观锁适合写操作多的场景，先加锁可以保证写操作时数据正确。
 - 乐观锁适合读操作多的场景，不加锁的特点能够使其读操作的性能大幅提升。

**1. 锁的实现**  

synchronized 是 JVM 实现的，而 ReentrantLock 是 JDK 实现的。

**2. 性能**  

新版本 Java 对 synchronized 进行了很多优化，例如自旋锁等，synchronized 与 ReentrantLock 大致相同。

**3. 等待可中断**  

当持有锁的线程长期不释放锁的时候，正在等待的线程可以选择放弃等待，改为处理其他事情。

ReentrantLock 可中断，而 synchronized 不行。

**4. 公平锁**  

公平锁是指多个线程在等待同一个锁时，必须按照申请锁的时间顺序来依次获得锁。

synchronized 中的锁是非公平的，ReentrantLock 默认情况下也是非公平的，但是也可以是公平的。

**5. 锁绑定多个条件**  

一个 ReentrantLock 可以同时绑定多个 Condition 对象。

### 使用选择

除非需要使用 ReentrantLock 的高级功能，否则优先使用 synchronized。这是因为 synchronized 是 JVM 实现的一种锁机制，JVM 原生地支持它，而 ReentrantLock 不是所有的 JDK 版本都支持。并且使用 synchronized 不用担心没有释放锁而导致死锁问题，因为 JVM 会确保锁的释放。

## CAS

```java
public class AtomicInteger extends Number implements java.io.Serializable {
    private static final long serialVersionUID = 6214790243416807050L;

    // setup to use Unsafe.compareAndSwapInt for updates
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                (AtomicInteger.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    private volatile int value;

```

 - unsafe： 获取并操作内存的数据。
 - valueOffset： 存储value在AtomicInteger中的偏移量。
 - value： 存储AtomicInteger的int值，该属性需要借助volatile关键字保证其在线程间是可见的。

```java
    // ------------------------- JDK 8 -------------------------
    // AtomicInteger 自增方法
    public final int incrementAndGet() {
        return unsafe.getAndAddInt(this, valueOffset, 1) + 1;
    }

    // ------------------------- OpenJDK 8 -------------------------
    // Unsafe.java
    public final int getAndAddInt(Object o, long offset, int delta) {
        int v;
        do {
            v = getIntVolatile(o, offset);
        } while (!compareAndSwapInt(o, offset, v, v + delta));
        return v;
    }
```

整个“比较+更新”操作封装在`compareAndSwapInt()`中，在JNI里是借助于一个CPU指令完成的，属于原子操作，可以保证多个线程都能够看到同一个变量的修改值。

后续JDK通过CPU的cmpxchg指令，去比较寄存器中的 A 和 内存中的值 V。如果相等，就把要写入的新值 B 存入内存中。如果不相等，就将内存值 V 赋值给寄存器中的值 A。然后通过Java代码中的while循环再次调用cmpxchg指令进行重试，直到设置成功为止。

CAS虽然很高效，但是它也存在三大问题，这里也简单说一下：
1. ABA ==> 加版本号 AtomicStampedReference
2. 循环时间长开销大 ==》超时时间
3. 只能保证一个共享变量的原子操作 ==> AtomicReference<>

### 如果两个线程同时CAS，一个成功了，另一个会一直死循环下去吗？

哈哈 不可能。上面已经说了：操作的那个变量是volatile修饰的，具有线程间的可见性。  
线程A在第一次发现内存中的值V与期望的值E不相等时，系统就会直接把V赋值给线程A的E变量，然后线程A继续去拿最新的这个期望的值（V）去和内存中的值做比较，如果相等，说明没有其他人改过本变量，给他赋成新值update。至此CAS 一个原子操作完成。




