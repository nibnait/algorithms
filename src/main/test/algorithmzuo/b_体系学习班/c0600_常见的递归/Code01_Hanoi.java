package algorithmzuo.b_体系学习班.c0600_常见的递归;

import com.google.common.collect.Lists;
import common.CommonConstants;
import common.util.SysOut;
import common.util.SysRandom;
import io.github.nibnait.common.utils.DataUtils;
import io.github.nibnait.common.utils.compare.CompareUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by nibnait on 2022/11/06
 */
public class Code01_Hanoi {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int n = SysRandom.random(100);
        List<String> hanoiRecur = hanoi1(n);
        List<String> hanoiNoRecur = hanoi2(n);

        if (!CompareUtils.match(hanoiRecur, hanoiNoRecur)) {
            SysOut.printf("n = {}", n);
            SysOut.printListWrap(hanoiRecur);
            SysOut.printSeparator();
            SysOut.printListWrap(hanoiNoRecur);
        }
    }

    /**
     * 递归
     */
    public List<String> hanoi1(int n) {
        if (n <= 0) {
            return Lists.newArrayList();
        }
        List<String> step = new ArrayList<>();
        process(n, "左", "右", "中", step);
        return step;
    }

    /**
     * 先把 n-1 左 -> 中
     * 再把 n 左 -> 右
     * 再把 n-1 中 -> 右
     */
    private void process(int n, String from, String to, String other, List<String> step) {
        if (n == 1) {
            step.add(DataUtils.format("Move {} from {} to {}", n, from, to));
            return;
        }

        process(n - 1, from, other, to, step);
        step.add(DataUtils.format("Move {} from {} to {}", n, from, to));
        process(n - 1, other, to, from, step);
    }

    /**
     * 非递归
     */
    public List<String> hanoi2(int N) {
        List<String> step = new ArrayList<>();
        if (N < 1) {
            return step;
        }
        Stack<Record> stack = new Stack<>();
        stack.add(new Record(false, N, "左", "右", "中"));
        while (!stack.isEmpty()) {
            Record cur = stack.pop();
            if (cur.base == 1) {
                step.add("Move 1 from " + cur.from + " to " + cur.to);
                if (!stack.isEmpty()) {
                    stack.peek().finish1 = true;
                }
            } else {
                if (!cur.finish1) {
                    stack.push(cur);
                    stack.push(new Record(false, cur.base - 1, cur.from, cur.other, cur.to));
                } else {
                    step.add("Move " + cur.base + " from " + cur.from + " to " + cur.to);
                    stack.push(new Record(false, cur.base - 1, cur.other, cur.to, cur.from));
                }
            }
        }

        return step;
    }

    private class Record {
        public boolean finish1;
        public int base;
        public String from;
        public String to;
        public String other;

        public Record(boolean f1, int b, String f, String t, String o) {
            finish1 = false;
            base = b;
            from = f;
            to = t;
            other = o;
        }
    }

}
