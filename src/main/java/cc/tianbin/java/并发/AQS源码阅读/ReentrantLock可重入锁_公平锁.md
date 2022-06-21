
## lock (sync.lock())

```java
    final void lock() {
        acquire(1);
    }

    public final void acquire(int arg) {
        // tryAcquire == true：获取锁成功。啥也不干
        // tryAcquire == false: 获取锁失败，addWaiter()，acquireQueued
        if (!tryAcquire(arg) &&
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg)) {
            
            selfInterrupt();
        }
    }

    protected final boolean tryAcquire(int acquires) {
        final Thread current = Thread.currentThread();
        int c = getState();
        if (c == 0) {
            // c == 0 && 没有等待队列 && casState(0, 1)成功： 
            if (!hasQueuedPredecessors() &&
                compareAndSetState(0, acquires)) {
                setExclusiveOwnerThread(current);
                return true;
            }
        } else if (current == getExclusiveOwnerThread()) {
            // 重入的情况：state+1
            int nextc = c + acquires;
            if (nextc < 0)
                throw new Error("Maximum lock count exceeded");
            setState(nextc);
            return true;
        }
        return false;
    }    

    private Node addWaiter(Node mode) {
        // Node.EXCLUSIVE = null;  独占锁
        Node node = new Node(Thread.currentThread(), mode);
        
        // tail != null，说明其他线程抢先casTail成功，当前node需要放到 tail的后面，重新casTail(tail, node) 
        Node pred = tail;
        if (pred != null) {
            node.prev = pred;
            if (compareAndSetTail(pred, node)) {
                pred.next = node;
                return node;
            }
        }
    
        // 将node 链到 等待队列的末尾。
        enq(node);
        return node;
    }

    private Node enq(final Node node) {
        for (;;) {
            Node t = tail;
            if (t == null) { 
                // 第一次进入for循环，将 head 和 tail 都指向 new Node()
                if (compareAndSetHead(new Node()))
                    tail = head;
            } else {
                // 第二次进入for循环，node.prev = t, casTail(t, node), t.next = node
                node.prev = t;
                if (compareAndSetTail(t, node)) {
                    t.next = node;
                    return t;
                }
            }
        }
    }

    final boolean acquireQueued(final Node node, int arg) {
        boolean failed = true;
        try {
            boolean interrupted = false;
            for (;;) {
                // p = node.prev
                final Node p = node.predecessor();
    
                // 如果 node.prev == head，重新 tryAcquire
                if (p == head && tryAcquire(arg)) {
                    setHead(node);
                    p.next = null; // help GC
                    failed = false;
                    return interrupted;
                }
    
                // 抢锁失败之后 判断是否要要阻塞
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())
                    interrupted = true;
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }
    }

    private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
        int ws = pred.waitStatus;
        if (ws == Node.SIGNAL)
            /*
             * pred.waitStatus == SIGNAL == -1，prev(前一个节点)已经被唤醒，所以node(当前节点)可以安全的被阻塞
             */
            return true;
        if (ws > 0) {
            /*
             * prev节点已经被取消，需要去判断 prev.prev.waitStatus，
             * 直到prev.waitStatus <= 0: prev.next = node;
             * 
             * 即：循环清除掉所有已经取消的节点。
             */
            do {
                node.prev = pred = pred.prev;
            } while (pred.waitStatus > 0);
            pred.next = node;
        } else {
            /*
             * waitStatus must be 0 or PROPAGATE(-3).  Indicate that we
             * need a signal, but don't park yet.  Caller will need to
             * retry to make sure it cannot acquire before parking.
             *
             * casWaitStatus(prev, ws, SIGNAL)
             * 死循环 将prev(前一个节点) 设为SIGNAL，直到设置成功，return true
             */
            compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
        }
        return false;
    }

```


## unlock(sync.release(1))

```java
    public void unlock() {
        sync.release(1);
    }

    public final boolean release(int arg) {
        if (tryRelease(arg)) {
            Node h = head;
            if (h != null && h.waitStatus != 0)
                unparkSuccessor(h);
            return true;
        }
        return false;
    }

    // 可重入锁
    protected final boolean tryRelease(int releases) {
        int c = getState() - releases;
        if (Thread.currentThread() != getExclusiveOwnerThread())
            throw new IllegalMonitorStateException();
        boolean free = false;
        if (c == 0) {
            free = true;
            setExclusiveOwnerThread(null);
        }
        setState(c);
        return free;
    }

    /**
       1. 判断node.waitStatus 
            < 0: 直接将waitStatus 设为0
       2. 尝试唤醒node = node.next 
            如果node.next.waitStauts = 1(取消状态)，则从 tail往前遍历
            unpark 从 tail往前 第一个非取消状态的线程。
     */
    private void unparkSuccessor(Node node) {
        /*
         * If status is negative (i.e., possibly needing signal) try
         * to clear in anticipation of signalling.  It is OK if this
         * fails or if status is changed by waiting thread.
         */
        int ws = node.waitStatus;
        if (ws < 0)
            compareAndSetWaitStatus(node, ws, 0);

        /*
         * Thread to unpark is held in successor, which is normally
         * just the next node.  But if cancelled or apparently null,
         * traverse backwards from tail to find the actual
         * non-cancelled successor.
         */
        Node s = node.next;
        if (s == null || s.waitStatus > 0) {
            s = null;
            for (Node t = tail; t != null && t != node; t = t.prev)
                if (t.waitStatus <= 0)
                    s = t;
        }
        if (s != null)
            LockSupport.unpark(s.thread);
    }

```