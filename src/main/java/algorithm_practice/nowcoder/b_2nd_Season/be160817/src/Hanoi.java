package algorithm_practice.nowcoder.b_2nd_Season.be160817.src;

import java.util.Stack;

public class Hanoi {

    public static int hanoiProblem1(int num, String left, String mid, String right) {
        if (num < 1) {
            return 0;
        }
        return process(num, left, mid, right, left, right);
    }

    public static int process(int num, String left, String mid, String right, String from, String to) {
        if (num == 1) {        //只剩1个盘了
            if (from.equals(mid) || to.equals(mid)) {
                System.out.println("Move 1 from " + from + " to " + to);
                return 1;
            } else {        //从左move到右，需要跨过mid
                System.out.println("Move 1 from " + from + " to " + mid);
                System.out.println("Move 1 from " + mid + " to " + to);
                return 2;
            }
        }
        if (from.equals(mid) || to.equals(mid)) {        //from和to 挨着（只需3步）
            String another = (from.equals(left) || to.equals(left)) ? right : left;        //不管from和to，是mid还是left，最终another一定是right
            int part1 = process(num - 1, left, mid, right, from, another);        //将1~(n-1) 从from到another
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + to);
            int part3 = process(num - 1, left, mid, right, another, to);        //再将1~(n-1)的东西 移回来
            return part1 + part2 + part3;
        } else {        //如果是从最左移动到最右（5步）
            int part1 = process(num - 1, left, mid, right, from, to);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + mid);
            int part3 = process(num - 1, left, mid, right, to, from);
            int part4 = 1;
            System.out.println("Move " + num + " from " + mid + " to " + to);
            int part5 = process(num - 1, left, mid, right, from, to);
            return part1 + part2 + part3 + part4 + part5;
        }
    }

    public static enum Action {
        No, LToM, MToL, MToR, RToM
    }

    public static int hanoiProblem2(int num, String left, String mid, String right) {
        Stack<Integer> lS = new Stack<Integer>();
        Stack<Integer> mS = new Stack<Integer>();
        Stack<Integer> rS = new Stack<Integer>();
        lS.push(Integer.MAX_VALUE);
        mS.push(Integer.MAX_VALUE);
        rS.push(Integer.MAX_VALUE);
        for (int i = num; i > 0; i--) {
            lS.push(i);
        }
        Action[] record = {Action.No};        //record相当于一个全局变量，里面放的是当前fStackTotStack当前动作
        int step = 0;
        while (rS.size() != num + 1) {
            step += fStackTotStack(record, Action.MToL, Action.LToM, lS, mS, left, mid);
            step += fStackTotStack(record, Action.LToM, Action.MToL, mS, lS, mid, left);
            step += fStackTotStack(record, Action.RToM, Action.MToR, mS, rS, mid, right);
            step += fStackTotStack(record, Action.MToR, Action.RToM, rS, mS, right, mid);
        }
        return step;
    }

    /**
     * 检测此动作 是否达标
     *
     * @param record
     * @param preNoAct //上一个不能够发生的动作
     * @param nowAct
     * @param fStack
     * @param tStack
     * @param from
     * @param to
     * @return
     */
    public static int fStackTotStack(Action[] record, Action preNoAct, Action nowAct, Stack<Integer> fStack,
                                     Stack<Integer> tStack, String from, String to) {
        if (record[0] == preNoAct || fStack.peek() >= tStack.peek()) {
            return 0;
        }
        tStack.push(fStack.pop());
        System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);
        record[0] = nowAct;
        return 1;
    }

    public static void main(String[] args) {
        int num = 4;

        // solution 1
        int steps1 = hanoiProblem1(num, "left", "mid", "right");
        System.out.println("It will move " + steps1 + " steps.");
        System.out.println("===================================");

        // solution 2
        int steps2 = hanoiProblem2(num, "left", "mid", "right");
        System.out.println("It will move " + steps2 + " steps.");
        System.out.println("===================================");

    }

}