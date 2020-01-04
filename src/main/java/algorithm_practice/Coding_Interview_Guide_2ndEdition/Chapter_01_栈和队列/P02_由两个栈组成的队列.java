package algorithm_practice.Coding_Interview_Guide_2ndEdition.Chapter_01_栈和队列;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Stack;

/*
由两个栈组成的队列

【题目】
编写一个类，用两个栈实现队列，支持队列的基本操作（add、poll、peek）。

【难度】
尉 ★★☆☆
 */
public class P02_由两个栈组成的队列 extends TestCase {

    @Test
    public void testCase() {

    }


    class TwoStackQueue {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public TwoStackQueue() {
            this.stackPush = new Stack<>();
            this.stackPop = new Stack<>();
        }

        //stackPop不为空，stackPush绝对不能像stackPop压入数据
        private void pushToPop() {
            if (stackPop.isEmpty()) {
                while (!stackPop.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void add(Integer pushInt) {
            stackPush.push(pushInt);
            pushToPop();
        }

        public Integer poll() {
            if (stackPush.isEmpty() && stackPop.isEmpty())  {
                throw new RuntimeException("Queue is empty");
            }
            pushToPop();
            return stackPop.pop();
        }

        public Integer peek() {
            if (stackPush.isEmpty() && stackPop.isEmpty())  {
                throw new RuntimeException("Queue is empty");
            }
            pushToPop();
            return stackPop.peek();
        }
    }
}
