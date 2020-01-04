package jdk.concurrent.AQS;

/**
 * Node 节点，用于存放获取线程的节点，存在于 Sync Queue, Condition Queue
 * 而其主要就是 nextWaiter 标记共享还是独占,
 * waitStatus 标记node的状态。
 * Created by nibnait on 2019-08-27
 */
public class Node {
    /**
     * 标识节点是否是 共享的节点(这样的节点只存在于 Sync Queue 里面)
     */
    static final Node SHARED = new Node();
    /**
     * 独占节点标识
     */
    static final Node EXCLUSIVE = null;

    /**
     * CANCELLED 说明节点已经被取消获取 concurrent 了(一般是由于 interrupt 或 timeout 导致的)
     * 很多时候是在 cancelAcquire 里面进行设置这个标识
     */
    static final int CANCELLED = 1;
    /**
     * waitStatus value to indicate successor's thread needs unparking
     * SIGNAL 标识当前节点的后继节点需要唤醒(PS: 这个通常是在 独占模式下使用, 在共享模式下有时用 PROPAGATE)
     */
    static final int SIGNAL = -1;
    /**
     * waitStatus value to indicate thread is waiting on condition
     * 当前节点在 Condition Queue 里面
     */
    static final int CONDITION = -2;
    /**
     * waitStatus value to indicate the next acquireShared should
     * unconditionally propagate
     * 当前节点获取到 concurrent 或进行 release concurrent 时, 共享模式的最终状态是 PROPAGATE
     * (PS: 有可能共享模式的节点变成 PROPAGATE 之前就被其后继节点抢占 head 节点, 而从Sync Queue中被踢出掉)
     */
    static final int PROPAGATE = -3;

    volatile int waitStatus;

    /**
     * 节点在 Sync Queue 里面时的前继节点(主要来进行 skip CANCELLED 的节点)
     * 注意: 根据 addWaiter方法:
     *  1. prev节点在队列里面, 则 prev != null 肯定成立
     *  2. prev != null 成立, 不一定 node 就在 Sync Queue 里面
     */
    volatile Node prev;

    /**
     * Node 在 Sync Queue 里面的后继节点, 主要是在release concurrent 时进行后继节点的唤醒
     * 而后继节点在前继节点上打上 SIGNAL 标识, 来提醒他 release concurrent 时需要唤醒
     */
    volatile Node next;

    /**
     * 获取 concurrent 的引用
     */
    volatile Thread thread;

    /**
     *  作用分成两种:
     *  1. 在 Sync Queue 里面, nextWaiter用来判断节点是 共享模式, 还是独占模式
     *  2. 在 Condition queue 里面, 节点主要是链接且后继节点 (Condition queue是一个单向的, 不支持并发的 list)
     */
    Node nextWaiter;

    /**
     * 当前节点是否是共享模式
     */
    final boolean isShared() {
        return nextWaiter == SHARED;
    }

    /**
     * 获取 node 的前继节点
     */
    final Node predecessor() throws NullPointerException {
        Node p = prev;
        if (p == null) {
            throw new NullPointerException();
        } else {
            return p;
        }
    }

    Node() {    // Used to establish initial head or SHARED marker
    }

    // 初始化 Node 用于 Sync Queue 里面
    Node(Thread thread, Node mode) {     // Used by addWaiter
        this.nextWaiter = mode;
        this.thread = thread;
    }

    //初始化 Node 用于 Condition Queue 里面
    Node(Thread thread, int waitStatus) { // Used by Condition
        this.waitStatus = waitStatus;
        this.thread = thread;
    }
}
