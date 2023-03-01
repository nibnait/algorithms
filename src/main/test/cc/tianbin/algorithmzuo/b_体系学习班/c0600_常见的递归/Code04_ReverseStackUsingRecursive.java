package cc.tianbin.algorithmzuo.b_体系学习班.c0600_常见的递归;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by nibnait on 2022/11/08
 */
public class Code04_ReverseStackUsingRecursive {

    @Test
    public void test() {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        reverse(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

    }

    /**
     * 使用递归，将栈逆序
     */
    public void reverse(Stack<Integer> stack) {
       if (stack == null || stack.isEmpty()) {
           return;
       }

       int last = process(stack);
       reverse(stack);
       // 把每次的栈底 push 进来
       stack.push(last);
    }

    /**
     * 每次都返回当前的栈底
     */
    private int process(Stack<Integer> stack) {
        Integer result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        }

        int last = process(stack);
        stack.push(result);
        return last;
    }


}
