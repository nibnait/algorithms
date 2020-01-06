package algorithm_practice.SwordOffer.old;

import java.util.Stack;

/**
 * 题目：输入两个整数序列，第一个序列表示栈的压入顺序，请判断二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
     * 例如序列1、2、3 、4、5 是某栈压栈序列，
     *     序列4、5、3、2、1 是该压栈序列对应的一个弹出序列，
     *       但4、3、5、1、2 就不可能是该压棋序列的弹出序列。
 *
 *
 * Created by nibnait on 2016/9/26.
 */
public class d22_栈的压入$弹出序列 {

    public static void main(String[] args) {
        int[] push = {1, 2, 3, 4, 5};
        int[] pop1 = {4, 5, 3, 2, 1};
        int[] pop2 = {3, 5, 4, 2, 1};
        int[] pop3 = {4, 3, 5, 1, 2};
        int[] pop4 = {3, 5, 4, 1, 2};
        System.out.println("true: " + isPopOrder(push, pop1));
        System.out.println("true: " + isPopOrder(push, pop2));
        System.out.println("false: " + isPopOrder(push, pop3));
        System.out.println("false: " + isPopOrder(push, pop4));
        int[] push5 = {1};
        int[] pop5 = {2};
        System.out.println("false: " + isPopOrder(push5, pop5));
        int[] push6 = {1};
        int[] pop6 = {1};
        System.out.println("true: " + isPopOrder(push6, pop6));
        System.out.println("false: " + isPopOrder(null, null));
    }

    private static boolean isPopOrder(int[] push, int[] pop) {
        if (push==null || pop==null || push.length==0  || pop.length==0 || push.length!=pop.length){
            return false;
        }
        int pushIndex = 0;
        int popIndex = 0;
        Stack<Integer> stack = new Stack<>();
        while (popIndex<pop.length){
            while (pushIndex<push.length && (stack.isEmpty() || pop[popIndex]!=stack.peek())){
                stack.push(push[pushIndex++]);
            }
            if (pop[popIndex] == stack.peek()){
                stack.pop();
                popIndex++;
            } else {
                return false;
            }
        }
        return true;
    }

}
