package cc.tianbin.algorithmzuo.b_体系学习班.c0700_动态规划;

import cc.tianbin.common.CommonConstants;
import cc.tianbin.common.util.SysOut;
import cc.tianbin.common.util.SysRandom;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by nibnait on 2022/11/13
 */
public class Code19_SplitSumClosedSizeHalf {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int maxLen = 10;
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

        sum /= 2;
        int len = arr.length / 2;
        if (arr.length % 2 == 0) {
            return process(arr, sum, 0, len);
        } else {
            return Math.max(
                    process(arr, sum, 0, len),
                    process(arr, sum, 0, len + 1)
            );
        }

    }

    private int process(int[] arr, int sumRest, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 0 : -1;
        }

        // 不要
        int sum1 = process(arr, sumRest, index + 1, rest);

        // 要
        int sum2 = -1;
        int next = -1;
        if (sumRest >= arr[index]) {
            next = process(arr, sumRest - arr[index], index + 1, rest - 1);
        }
        if (next != -1) {
            sum2 = arr[index] + next;
        }

        return Math.max(sum1, sum2);
    }

    /**
     * 动态规划
     */
    public int minSum2(int[] arr) {
        return 0;
    }

}
