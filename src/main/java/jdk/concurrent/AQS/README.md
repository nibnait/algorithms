整个 AQS 分为以下几部分:

- **Node** 节点， 用于存放获取线程的节点， 存在于 Sync Queue, Condition Queue, 这些节点主要的区分在于 waitStatus 的值(下面会详细叙述)
- **Condition Queue**，这个队列是用于独占模式中，只有用到 Condition.awaitXX 时才会将 node加到 tail 上(PS: 在使用 Condition的前提是已经获取 Lock)
- **Sync Queue**，独占共享的模式中均会使用到的存放 Node 的 CLH queue(主要特点是队列中总有一个 dummy 节点，后继节点获取锁的条件由前继节点决定，前继节点在释放 lock 时会唤醒sleep中的后继节点)
- **ConditionObject**，用于独占的模式，主要是线程释放lock，加入Condition Queue， 并进行相应的 signal 操作

独占的获取lock (acquire release) 例如 ReentrantLock。

共享的获取lock (acquireShared releaseShared)。 例如 ReeantrantReadWriteLock, Semaphore, CountDownLatch

# Node

waitStatus 变化过程:

1. 独占模式下: 0(初始) -> SIGNAL(被后继节点标记为release需要唤醒后继节点) -> 0 (等释放好lock, 会恢复到0)
2. 独占模式 + 使用 Condition情况下: 0(初始) -> SIGNAL(被后继节点标记为release需要唤醒后继节点) -> 0 (等释放好lock, 会恢复到0)其上可能涉及 中断与超时, 只是多了一个 CANCELLED, 当节点变成 CANCELLED, 后就等着被清除。
3. 共享模式下: 0(初始) -> PROPAGATE(获取 lock 或release lock 时) (获取 lock 时会调用 setHeadAndPropagate 来进行 传递式的唤醒后继节点, 直到碰到 独占模式的节点)
4. 共享模式 + 独占模式下: 0(初始) -> SIGNAL(被后继节点标记为release需要唤醒后继节点) -> 0 (等释放好lock, 会恢复到0)

其上的这些状态变化主要在: doReleaseShared , shouldParkAfterFailedAcquire 里面。

# Condition Queue

