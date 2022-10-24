package algorithmzuo.b_体系学习班.c0105_栈和队列;

import org.junit.Test;

import java.util.Stack;

/**
 * 两个栈实现队列
 * Created by nibnait on 2022/07/06
 */
public class Code06_TwoStacksImplementQueue {

    @Test
    public void testCase() {
        TwoStacksQueue test = new TwoStacksQueue();
        test.offer(1);
        test.offer(2);
        test.offer(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }

    public static class TwoStacksQueue<T> {
        private Stack<T> pushStack;
        private Stack<T> popStack;

        public TwoStacksQueue() {
            pushStack = new Stack<>();
            popStack = new Stack<>();
        }

        public void offer(T value) {
            pushStack.push(value);
            pushToPop();
        }

        private void pushToPop() {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
        }

        public T poll() {
            if (popStack.isEmpty() && pushStack.isEmpty()) {
                throw new RuntimeException("队列空了");
            }
            pushToPop();
            return popStack.pop();
        }

        public T peek() {
            if (popStack.isEmpty() && pushStack.isEmpty()) {
                throw new RuntimeException("队列空了");
            }
            pushToPop();
            return popStack.peek();
        }
    }


}
