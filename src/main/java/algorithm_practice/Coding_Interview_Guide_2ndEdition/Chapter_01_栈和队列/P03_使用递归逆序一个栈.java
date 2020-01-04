package algorithm_practice.Coding_Interview_Guide_2ndEdition.Chapter_01_栈和队列;

import common.util.StandardInit;
import common.util.SysOut;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Stack;

/*
【题目】
一个栈依次压入1、2、3、4、5，那么从栈顶到栈底分别为5、4、3、2、1。将这个栈转置后，从栈顶到栈底为1、2、3、4、5，也就是实现栈中元素的逆序，但是只能用递归函数来实现，不能用其他数据结构。

【难度】
尉 ★★☆☆
 */
public class P03_使用递归逆序一个栈 extends TestCase {

    @Test
    public void testCase() {
        Stack<Integer> stack = StandardInit.initStack();
        reverseStackUsingRecursive(stack);

        SysOut.printStack(stack);
    }

    /**
     * 设计递归
     * @param stack
     */
    private void reverseStackUsingRecursive(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return ;
        }
        Integer lastElement = getStackLastElement(stack);
        reverseStackUsingRecursive(stack);
        stack.push(lastElement);
    }

    private Integer getStackLastElement(Stack<Integer> stack) {
        Integer result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            Integer stackLastElement = getStackLastElement(stack);
            stack.push(result);
            return stackLastElement;
        }
    }
}
