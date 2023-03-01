package cc.tianbin.algorithmzuo.b_体系学习班.c0700_动态规划;

import cc.tianbin.common.CommonConstants;
import cc.tianbin.common.util.SysOut;
import cc.tianbin.common.util.SysRandom;
import org.junit.Test;

/**
 * Created by nibnait on 2022/11/13
 */
public class Code12_CoinsWayNoLimit {

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
        int aim = SysRandom.generateNaturalNum(maxValue);

//        arr = new int[]{1, 2};
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
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return process(arr, aim, 0);
    }

    private int process(int[] arr, int rest, int index) {
        if (rest == 0) {
            return 1;
        }
        if (index == arr.length) {
            return 0;
        }

        int ways = 0;
        int cur = arr[index];
        int zhang = rest / cur;
        for (int j = 0; j <= zhang; j++) {
            ways += process(arr, rest - j * cur, index + 1);
        }

        return ways;
    }

    /**
     * 动态规划
     */
    private int coinWays2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int len = arr.length;
        int[][] dp = new int[aim + 1][len];
        // 第一行
        for (int index = 0; index < len; index++) {
            dp[0][index] = 1;
        }

        // 最后一列
        for (int rest = 1; rest <= aim; rest++) {
            int cur = arr[len - 1];
            if (rest % cur == 0 && rest / cur > 0) {
                // 当前来到了 arr[len - 1] 这张牌。
                // 就看 用 x张 arr[len-1] 能不能组成 rest。能就算一种方法。
                dp[rest][len - 1] = 1;
            }
        }

        // 普通位置，依赖后面的一列
        for (int rest = 0; rest <= aim; rest++) {
            for (int index = len - 2; index >= 0; index--) {
                int ways = 0;
                int cur = arr[index];
                int zhang = rest / cur;
                for (int j = 0; j <= zhang; j++) {
                    ways += dp[rest - j * cur][index + 1];
                }
                dp[rest][index] = ways;
            }
        }

        return dp[aim][0];
    }

}
