package algorithm_practice.Coding_Interview_Guide_2ndEdition.Chapter_01_栈和队列;

import common.util.StandardInit;
import common.util.SysOut;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Stack;

/*
【题目】
一个栈中元素的类型为整型，现在想将该栈从顶到底按从大到小的顺序排序，只许申请一个栈。除此之外，可以申请新的变量，但不能申请额外的数据结构。如何完成排序？

【难度】
士 ★☆☆☆
 */
public class P05_用一个栈实现另一个栈的排序 extends TestCase {

    @Test
    public void testCase() {

        Stack<Integer> stack = StandardInit.initStack();
        SysOut.printStack(stack);
        System.out.println("sorted:");
        Stack<Integer> sortedStack = stackSortStack(stack);
        SysOut.printStack(sortedStack);

        stack = StandardInit.initStack();
        SysOut.printStack(stack);
        System.out.println("sorted:");
        Stack<Integer> answer_stackSortStack = Standard_stackSortStack(stack);
        SysOut.printStack(answer_stackSortStack);
    }

    /**
     * 重构
     * @param stack
     * @return
     */
    private Stack<Integer> Standard_stackSortStack(Stack<Integer> stack) {
        Stack<Integer> assistStack = new Stack<>();
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            while (!assistStack.isEmpty() && pop < assistStack.peek()) {
                stack.push(assistStack.pop());
            }
            assistStack.push(pop);
        }
        return assistStack;
    }

    /**
     * 从顶到底 按从大到小排序
     *
     * 代码的堆砌！
     *
     * @param stack
     * @return
     */
    private Stack<Integer> stackSortStack(Stack<Integer> stack) {
        Stack<Integer> assistStack = new Stack<>();
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            if (assistStack.isEmpty()) {
                assistStack.push(pop);
            } else {
                Integer peek = assistStack.peek();
                if (pop > peek) {
                    assistStack.push(pop);
                } else {
                    Integer assistPop = peek;
                    while (pop > assistPop) {
                        assistPop = assistStack.pop();
                        stack.push(assistPop);
                    }
                    assistStack.push(pop);
                }
            }
        }
        return assistStack;
    }
}
