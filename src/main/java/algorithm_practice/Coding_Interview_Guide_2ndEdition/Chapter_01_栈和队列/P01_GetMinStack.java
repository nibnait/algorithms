package algorithm_practice.Coding_Interview_Guide_2ndEdition.Chapter_01_栈和队列;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Stack;

/*
设计一个有getMin 功能的栈

【题目】
实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。

【要求】
1．pop、push、getMin 操作的时间复杂度都是O(1)。

2．设计的栈类型可以使用现成的栈结构。

【难度】
士 ★☆☆☆
 */
public class P01_GetMinStack extends TestCase {

    @Test
    public void testCase() {

    }

    class MyStack {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;    //加一个最小值栈。getMin直接 stackMin.peek()
    }
}
