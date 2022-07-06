package algorithm_practice.algorithmzuo.b_体系学习班.class03;

import common.datastruct.Node;
import io.github.nibnait.common.utils.compare.CompareUtils;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 用双链表实现栈和队列
 * Created by nibnait on 2022/07/06
 */
public class Code03_DoubleEndsQueueToStackAndQueue {

    public static class DoubleEndsQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        public void addFromHead(T value) {
            Node newHead = new Node(value);
            if (head == null) {
                head = newHead;
                tail = newHead;
            } else {
                head.prev = newHead;
                newHead.next = head;

                head = head.prev;
            }
        }

        public void addFromTail(T value) {
            Node newHead = new Node(value);
            if (head == null) {
                head = newHead;
                tail = newHead;
            } else {
                tail.next = newHead;
                newHead.prev = tail;

                tail = tail.next;
            }
        }

        public T popFromHead() {
            if (isEmpty()) {
                return null;
            }

            Node<T> curHead = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.prev = null;

                curHead.next = null;
            }
            return curHead.value;
        }

        public T popFromTail() {
            if (isEmpty()) {
                return null;
            }

            Node<T> curTail = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;

                curTail.prev = null;
            }

            return curTail.value;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    public class MyStack<T> {
        private DoubleEndsQueue<T> queue;

        public MyStack() {
            this.queue = new DoubleEndsQueue<>();
        }

        public void push(T value) {
            queue.addFromTail(value);
        }

        public T pop() {
            return queue.popFromTail();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public class MyQueue<T> {
        private DoubleEndsQueue<T> queue;

        public MyQueue() {
            this.queue = new DoubleEndsQueue<>();
        }

        public void offer(T value) {
            queue.addFromTail(value);
        }

        public T poll() {
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    @Test
    public void testCase() {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!CompareUtils.matchObject(myStack.pop(), stack.pop())) {
                            System.out.println("oops!");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (queue.isEmpty()) {
                    myQueue.offer(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.offer(numq);
                        queue.offer(numq);
                    } else {
                        if (!CompareUtils.matchObject(myQueue.poll(), queue.poll())) {
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}
