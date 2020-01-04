package common.util;

import java.util.Stack;

/**
 * 标准初始化一个结构体
 */
public class StandardInit {

    public static Stack<Integer> initStack() {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        return stack;
    }

}
