package algorithm_practice.algorithmzuo.b_体系学习班.class03;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 两个队列实现栈
 * Created by nibnait on 2022/07/06
 */
public class Code07_TwoQueueImplementStack {

    @Test
    public void testCase() {
        System.out.println("test begin");
        TwoQueuesStack<Integer> myStack = new TwoQueuesStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.poll().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");
    }

    public static class TwoQueuesStack<T> {
        private Queue<T> queue;
        private Queue<T> help;

        public TwoQueuesStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public void push(T value) {
            queue.offer(value);
        }

        public T poll() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T ans = queue.poll();

            Queue<T> tmp = queue;
            queue = help;
            help = tmp;

            return ans;
        }

        public T peek() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T ans = queue.poll();
            help.offer(ans);

            Queue<T> tmp = queue;
            queue = help;
            help = tmp;

            return ans;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

}
