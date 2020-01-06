package jdk.concurrent.AQS;

import sun.misc.Unsafe;

import java.io.Serializable;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

/**
 * Created by nibnait on 2019-08-27
 */
public class ConditionObject implements Serializable {
    
    private static final long serialVersionUID = 1173984872572414699L;
    /** First node of condition queue. */
    private transient Node firstWaiter;
    /** Last node of condition queue. */
    private transient Node lastWaiter;

    /**
     * Creates a new {@code ConditionObject} instance.
     */
    public ConditionObject() { }

    // Internal methods

    /**
     * Adds a new waiter to wait queue.
     * @return its new wait node
     */
    private Node addConditionWaiter() {
        Node t = lastWaiter;
        // 尾节点已经Cancel, 直接进行清除.

        /**
         * 当Condition进行 awiat 超时或被中断时, Condition里面的节点是没有被删除掉的,
         * 需要其他await 在将线程加入 Condition Queue 时调用addConditionWaiter而进而删除,
         * 或 await 操作差不多结束时, 调用 "node.nextWaiter != null" 进行判断而删除
         * (PS: 通过 signal 进行唤醒时 node.nextWaiter 会被置空, 而中断和超时时不会)
         */
        if (t != null && t.waitStatus != Node.CONDITION) {
            /**
             * 调用 unlinkCancelledWaiters 对 "waitStatus != Node.CONDITION" 的节点进行删除
             * (在Condition里面的Node的waitStatus 要么是CONDITION(正常), 要么就是 0
             * (signal/timeout/interrupt))
             */
            unlinkCancelledWaiters();
            t = lastWaiter;
        }
        // 将线程封装成 node 准备放入 Condition Queue 里面
        Node node = new Node(Thread.currentThread(), Node.CONDITION);
        if (t == null) {
            //Condition Queue 是空的
            firstWaiter = node;
        } else {
            // 追加到 queue 尾部
            t.nextWaiter = node;
        }
        lastWaiter = node;
        return node;
    }


    /**
     * Unlinks cancelled waiter nodes from condition queue.
     * Called only while holding concurrent. This is called when
     * cancellation occurred during condition wait, and upon
     * insertion of a new waiter when lastWaiter is seen to have
     * been cancelled. This method is needed to avoid garbage
     * retention in the absence of signals. So even though it may
     * require a full traversal, it comes into play only when
     * timeouts or cancellations occur in the absence of
     * signals. It traverses all nodes rather than stopping at a
     * particular target to unlink all pointers to garbage nodes
     * without requiring many re-traversals during cancellation
     * storms.
     */
    private void unlinkCancelledWaiters() {
        Node t = firstWaiter;
        Node trail = null;
        while (t != null) {
            Node next = t.nextWaiter;
            if (t.waitStatus != Node.CONDITION) {
                t.nextWaiter = null;
                if (trail == null) {
                    firstWaiter = next;
                } else {
                    trail.nextWaiter = next;
                }
                if (next == null) {
                    lastWaiter = trail;
                }
            } else {
                trail = t;
            }
            t = next;
        }
    }


    /**
     * Setup to support compareAndSet. We need to natively implement
     * this here: For the sake of permitting future enhancements, we
     * cannot explicitly subclass AtomicLong, which would be
     * efficient and useful otherwise. So, as the lesser of evils, we
     * natively implement using hotspot intrinsics API. And while we
     * are at it, we do the same for other CASable fields (which could
     * otherwise be done with atomic field updaters).
     */
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;
    private static final long waitStatusOffset;
    private static final long nextOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset
                    (AbstractQueuedLongSynchronizer.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset
                    (AbstractQueuedLongSynchronizer.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset
                    (AbstractQueuedLongSynchronizer.class.getDeclaredField("tail"));
            waitStatusOffset = unsafe.objectFieldOffset
                    (Node.class.getDeclaredField("waitStatus"));
            nextOffset = unsafe.objectFieldOffset
                    (Node.class.getDeclaredField("next"));

        } catch (Exception ex) { throw new Error(ex); }
    }

    /**
     * CAS head field. Used only by enq.
     */
    private final boolean compareAndSetHead(Node update) {
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }

    /**
     * CAS tail field. Used only by enq.
     */
    private final boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    /**
     * CAS waitStatus field of a node.
     */
    private static final boolean compareAndSetWaitStatus(Node node,
                                                         int expect,
                                                         int update) {
        return unsafe.compareAndSwapInt(node, waitStatusOffset,
                expect, update);
    }

    /**
     * CAS next field of a node.
     */
    private static final boolean compareAndSetNext(Node node,
                                                   Node expect,
                                                   Node update) {
        return unsafe.compareAndSwapObject(node, nextOffset, expect, update);
    }
}
