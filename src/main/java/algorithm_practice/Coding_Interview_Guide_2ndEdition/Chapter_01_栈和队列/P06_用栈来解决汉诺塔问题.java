package algorithm_practice.Coding_Interview_Guide_2ndEdition.Chapter_01_栈和队列;

import common.util.SysOut;
import junit.framework.TestCase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Test;

import java.util.Stack;

/*
【题目】
汉诺塔问题比较经典，这里修改一下游戏规则：现在限制不能从最左侧的塔直接移动到最右侧，也不能从最右侧直接移动到最左侧，而是必须经过中间。求当塔有N 层的时候，打印最优移动过程和最优移动总步数。
例如，当塔数为两层时，最上层的塔记为1，最下层的塔记为2，则打印：

Move 1 from left to mid
Move 1 from mid to right
Move 2 from left to mid
Move 1 from right to mid
Move 1 from mid to left
Move 2 from mid to right
Move 1 from left to mid
Move 1 from mid to right
It will move 8 steps.
注意：关于汉诺塔游戏的更多讨论，将在本书递归与动态规划的章节中继续。

【要求】
用以下两种方法解决。
. 方法一：递归的方法；
. 方法二：非递归的方法，用栈来模拟汉诺塔的三个塔。

【难度】
校 ★★★☆
 */
public class P06_用栈来解决汉诺塔问题 extends TestCase {

    @Test
    public void testCase() {
        int nums = 2;
        SysOut.printSeparator("递归");
        SysOut.println("It will move %s steps.", hanoiUsingRecursive(nums));
        SysOut.printSeparator("非递归（用栈）");
        SysOut.println("It will move %s steps.", hanoiUsingStack(nums));
//        SysOut.printSeparator("非递归（按照递归的思路转义，建立两个函数栈）");
//        SysOut.println("It will move %s steps.", hanoiUsingStack(nums));
//        SysOut.printSeparator("非递归（根据奇偶性，将盘子分为两类）");
//        SysOut.println("It will move %s steps.", hanoiUsingStack(nums));
//        SysOut.printSeparator("非递归（二叉树）");    //TODO
//        SysOut.println("It will move %s steps.", hanoiUsingStack(nums));

    }

    @AllArgsConstructor
    @Getter
    private enum Action {
        LToM(LEFT, MIDDLE),
        MToL(MIDDLE, LEFT),
        RToM(RIGHT, MIDDLE),
        MToR(MIDDLE, RIGHT);

        private String from;
        private String to;

        public static boolean isBetweenLeftAndMid(Action action) {
            return LToM.equals(action) || MToL.equals(action);
        }

        public static boolean isBetweenRightAndMid(Action action) {
            return RToM.equals(action) || MToR.equals(action);
        }
    }

    /**
     * 经分析：L->M, M->L, R->M, M->R
     * <p>
     * “小压大”、“相邻不可逆”
     * 每个动作的下一个动作 只能是二选一
     * <p>
     * 即：因为要从最左移到最右，
     * 第一个动作一定是 L->M，
     * 【相邻不可逆】下一个动作不可能是M->L
     * 一定是“R->M”,"M->R" 二选一【小压大原则】
     */
    private int hanoiUsingStack(int nums) {
        Stack<Integer> leftStack = initHanoiStack();
        Stack<Integer> middleStack = initHanoiStack();
        Stack<Integer> rightStack = initHanoiStack();

        leftStack = initDishes(nums, leftStack);

        Action nowAction = Action.LToM;
        move(nowAction, leftStack, middleStack);
        int step = 1;

        while (rightStack.size() != nums + 1) {
            if (Action.isBetweenLeftAndMid(nowAction)) {
                if (middleStack.peek() < rightStack.peek()) {
                    nowAction = Action.MToR;
                    step += move(nowAction, middleStack, rightStack);
                } else {
                    nowAction = Action.RToM;
                    step += move(nowAction, rightStack, middleStack);
                }
            } else if (Action.isBetweenRightAndMid(nowAction)) {
                if (leftStack.peek() < middleStack.peek()) {
                    nowAction = Action.LToM;
                    step += move(nowAction, leftStack, middleStack);
                } else {
                    nowAction = Action.MToL;
                    step += move(nowAction, middleStack, leftStack);
                }
            }
        }
        return step;
    }

    private Stack<Integer> initDishes(int nums, Stack<Integer> leftStack) {
        for (int i = nums; i > 0; i--) {
            leftStack.push(i);
        }
        return leftStack;
    }

    private int move(Action action, Stack<Integer> fromStack, Stack<Integer> toStack) {
        Integer pop = fromStack.pop();
        toStack.push(pop);
        SysOut.println(MOVE_FORMAT, pop, action.getFrom(), action.getTo());
        return 1;
    }

    private Stack<Integer> initHanoiStack() {
        Stack<Integer> hanoiStack = new Stack<>();
        hanoiStack.push(Integer.MAX_VALUE);
        return hanoiStack;
    }

    private static final String LEFT = "left";
    private static final String MIDDLE = "MIDDLE";
    private static final String RIGHT = "right";
    private static final String MOVE_FORMAT = "Move %d from %s to %s";

    private int hanoiUsingRecursive(int nums) {
        if (nums < 1) {
            return 0;
        }
        return recursiveHanoi(nums, LEFT, RIGHT);
    }

    /**
     * 将nums层盘子从 from侧移动到to侧
     *
     * @param nums
     * @param from
     * @param to
     * @return
     */
    private int recursiveHanoi(int nums, String from, String to) {
        if (nums == 1) {
            if (MIDDLE.equalsIgnoreCase(from) || MIDDLE.equalsIgnoreCase(to)) {
                SysOut.println(MOVE_FORMAT, 1, from, to);
                return 1;
            } else {
                SysOut.println(MOVE_FORMAT, 1, from, MIDDLE);
                SysOut.println(MOVE_FORMAT, 1, MIDDLE, to);
                return 2;
            }
        }
        if (MIDDLE.equalsIgnoreCase(from) || MIDDLE.equalsIgnoreCase(to)) {
            int step = 0;
            // 1 ~ N-1 从“左”移到“右”
            step += recursiveHanoi(nums - 1, from, to);
            // N 从“左”移到“中”
            SysOut.println(MOVE_FORMAT, nums, from, MIDDLE);
            step += 1;

            // 1 ~ N-1 从“右”移到“中”
            step += recursiveHanoi(nums - 1, to, MIDDLE);
            return step;
        } else {
            int step = 0;
            // 1 ~ N-1 从“左”移到“右”
            step += recursiveHanoi(nums - 1, from, to);

            // N 从“左”移到“中”
            SysOut.println(MOVE_FORMAT, nums, from, MIDDLE);
            step += 1;

            // 1 ~ N-1 从“右”移到“左”
            step += recursiveHanoi(nums - 1, to, from);

            // N 从“中”移到“右”
            SysOut.println(MOVE_FORMAT, nums, MIDDLE, to);
            step += 1;

            // 1 ~ N-1 从“左”移到“右”
            step += recursiveHanoi(nums - 1, from, to);
            return step;
        }
    }

}
