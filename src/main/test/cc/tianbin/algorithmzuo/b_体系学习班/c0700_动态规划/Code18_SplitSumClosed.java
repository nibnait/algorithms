package cc.tianbin.algorithmzuo.b_体系学习班.c0700_动态规划;

import cc.tianbin.common.CommonConstants;
import cc.tianbin.common.util.SysOut;
import cc.tianbin.common.util.SysRandom;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by nibnait on 2022/11/13
 */
public class Code18_SplitSumClosed {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int maxLen = 20;
        int maxValue = 50;
        int[] arr = SysRandom.generateArrNaturalNum(maxLen, maxValue);
        int ans1 = minSum1(arr);
        int ans2 = minSum2(arr);

        if (ans1 != ans2) {
            SysOut.printf("arr: {}, sum={}", arr, Arrays.stream(arr).sum());
            SysOut.printf("ans1: {}, ans2: {}", ans1, ans2);
            throw new RuntimeException();
        }

    }

    /**
     * 暴力递归
     */
    public int minSum1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return process(arr, sum / 2, 0);
    }

    /**
     * 求从 arr 中选出一个子集的累加和 是 <= sum/2 && 最大的
     */
    private int process(int[] arr, int rest, int index) {
        if (index == arr.length) {
            return 0;
        }
        if (rest == 0) {
            return 0;
        }

        // 要 arr[index]
        int sum1 = Integer.MIN_VALUE;
        if (rest >= arr[index]) {
            sum1 = arr[index] + process(arr, rest - arr[index], index + 1);
        }
        // 不要 arr[index]
        int sum2 = process(arr, rest, index + 1);
        return Math.max(sum1, sum2);
    }

    /**
     * 动态规划
     */
    public int minSum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int sum = 0;
        for (int i : arr) {
            sum += i;
        }

        sum /= 2;
        int len = arr.length;
        int[][] dp = new int[sum + 1][len];
        // 最后一列
        for (int rest = arr[len - 1]; rest <= sum; rest++) {
            dp[rest][len - 1] = arr[len - 1];
        }

        // 普通位置，依赖后面的一列
        for (int index = len - 2; index >= 0; index--) {
            for (int rest = 0; rest <= sum; rest++) {
                int sum1 = Integer.MIN_VALUE;
                if (rest >= arr[index]) {
                    sum1 = arr[index] + dp[rest - arr[index]][index + 1];
                }
                // 不要 arr[index]
                int sum2 = dp[rest][index + 1];
                dp[rest][index] = Math.max(sum1, sum2);
            }
        }

        return dp[sum][0];
    }

}
