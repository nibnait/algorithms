package algorithm_practice.SwordOffer.old;

import java.util.Stack;

/**
 * Created by nibnait on 2016/9/20.
 */
public class b07_用两个栈实现队列 {

    public static void main(String[] args) {
        QueueWithTwoStacks test = new QueueWithTwoStacks();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
    }

    private static class QueueWithTwoStacks {
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        public QueueWithTwoStacks() {
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }

        public void add(int pushInt) {
            stackPush.push(pushInt);
        }

        public int poll() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            } else if (stackPop.empty()) {
                while (!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.pop();
        }

        public int peek() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            } else if (stackPop.empty()) {
                while (!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.peek();
        }
    }

}
