package algorithmzuo.b_体系学习班.c0700_动态规划;

import common.CommonConstants;
import common.util.SysOut;
import common.util.SysRandom;
import org.junit.Test;

/**
 * Created by nibnait on 2022/11/13
 */
public class Code11_CoinsWayEveryPaperDifferent {

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
        int[] arr = SysRandom.generateArrNaturalNum(maxLen);
        int aim = SysRandom.generateNaturalNum(maxValue);

//        arr = new int[]{1, 1, 1};
//        aim = 2;
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

        // 选 arr[index]
        int ways = process(arr, rest - arr[index], index + 1);
        // 不选 arr[index]
        ways += process(arr, rest, index + 1);
        return ways;
    }

    /**
     * 动态规划
     * 优化 -> 一维
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
            if (rest == arr[len - 1]) {
                dp[rest][len - 1] = 1;
            }
        }

        // 普通位置 依赖后面的一行
        for (int index = len - 2; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                // 选 arr[index]
                int ways = 0;
                if (rest - arr[index] >= 0) {
                    ways = dp[rest - arr[index]][index + 1];
                }
                // 不选 arr[index]
                ways += dp[rest][index + 1];
                dp[rest][index] = ways;
            }
        }

        return dp[aim][0];
    }

}
