package cc.tianbin.algorithmzuo.b_体系学习班.c0700_动态规划;

import cc.tianbin.common.CommonConstants;
import cc.tianbin.common.util.SysOut;
import cc.tianbin.common.util.SysRandom;
import org.junit.Test;

/**
 * Created by nibnait on 2022/11/13
 */
public class Code14_MinCoinsNoLimit {


    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int maxLen = 20;
        int maxValue = 30;
        int[] arr = SysRandom.generateArrPositiveIntegerNoRepeat(maxLen);
        int aim = SysRandom.generatePositiveInt(maxValue);

//        arr = new int[]{1, 2, 3};
//        aim = 4;
        int ans1 = coinWays1(arr, aim);
        int ans2 = coinWays2(arr, aim);

        if (ans1 != ans2) {
            SysOut.printf("arr:{}, aim: {}", arr, aim);
            SysOut.printf("ans1: {}, ans2: {}", ans1, ans2);
            throw new RuntimeException();
        }
    }

    /**
     * 暴力递归
     */
    private int coinWays1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }

        int ans = process(arr, aim, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int process(int[] arr, int rest, int index) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int cur = arr[index];
        int abs = Integer.MAX_VALUE;
        for (int zhang = 0; zhang <= rest / cur; zhang++) {
            int next = process(arr, rest - zhang * cur, index + 1);
            if (next == Integer.MAX_VALUE) {
                continue;
            }
            abs = Math.min(abs, zhang + next);
        }

        return abs;
    }

    /**
     * 动态规划
     */
    private int coinWays2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        int len = arr.length;
        int[][] dp = new int[aim + 1][len + 1];

        // 最后一列
        dp[0][len] = 0;
        for (int rest = 1; rest <= aim; rest++) {
            dp[rest][len] = Integer.MAX_VALUE;
        }

        // 普通位置，依赖后面的一列
        for (int index = len - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                int cur = arr[index];
                for (int zhang = 0; zhang <= rest / cur; zhang++) {
                    int next = dp[rest - zhang * cur][index + 1];
                    if (next == Integer.MAX_VALUE) {
                        continue;
                    }
                    ans = Math.min(ans, zhang + next);
                }
                dp[rest][index] = ans;
            }
        }

        return dp[aim][0] == Integer.MAX_VALUE ? -1 : dp[aim][0];
    }

}
