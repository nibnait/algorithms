package algorithm_practice.SwordOffer.old;

import java.util.Stack;

/**
 * 题目： 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小素的 min 函数。
 * 在该栈中，调用 min、push 及 pop 的时间复杂度都是 0(1)
 *
 * 【解】：
 *      在push()的时候，再用一个辅助栈，来存放当前最小值
 * Created by nibnait on 2016/9/26.
 */
public class d21_包含min函数的栈 {

    public static void main(String[] args) {
        StackWithMin<Integer> stack = new StackWithMin<>();
        stack.push(3);
        System.out.println(stack.min() == 3);
        stack.push(4);
        System.out.println(stack.min() == 3);
        stack.push(2);
        System.out.println(stack.min() == 2);
        stack.push(3);
        System.out.println(stack.min() == 2);
        stack.pop();
        System.out.println(stack.min() == 2);
        stack.pop();
        System.out.println(stack.min() == 3);
        stack.push(0);
        System.out.println(stack.min() == 0);
    }

    public static class StackWithMin<T extends Comparable<T>>{
        private Stack<T> dataStack;
        private Stack<Integer> minStack;    //放的是最小元素的位置
        public StackWithMin(){
            this.dataStack = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(T t){
            if (t == null){
                throw new RuntimeException("can not push null");
            }
            if (dataStack.isEmpty()){
                dataStack.push(t);
                minStack.push(0);
            } else {
                dataStack.push(t);
                T e = dataStack.get(minStack.peek());
                minStack.push(t.compareTo(e)<0? dataStack.size()-1: minStack.peek());
            }
        }

        public T pop(){
            if (dataStack.isEmpty()){
                throw new RuntimeException("Stack is empty!");
            }
            minStack.pop();
            return dataStack.pop();
        }

        public T min(){
            if (minStack.isEmpty()){
                throw new RuntimeException("Stack is empty!");
            }
            return dataStack.get(minStack.peek());
        }

    }

}
