package cc.tianbin.algorithmzuo.b_体系学习班.c0700_动态规划;

import cc.tianbin.common.CommonConstants;
import cc.tianbin.common.util.SysOut;
import cc.tianbin.common.util.SysRandom;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nibnait on 2022/11/13
 */
public class Code13_CoinsWaySameValueSamePapper {

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
        int[] arr = SysRandom.generateArrPositiveInteger(maxLen);
        int aim = SysRandom.generateNaturalNum(maxValue);

//        arr = new int[]{1, 2, 1, 1};
//        aim = 4;
        int ans1 = coinWays1(arr, aim);
        int ans2 = coinWays2(arr, aim);

        if (ans1 != ans2) {
            SysOut.printArray(arr);
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

        Info info = getInfo(arr);

        return process(info.coins, info.nums, aim, 0);
    }

    private int process(int[] coins, int[] nums, int rest, int index) {
        if (rest == 0) {
            return 1;
        }
        if (index == coins.length) {
            return 0;
        }

        int cur = coins[index];
        int ways = 0;
        for (int zhang = 0; zhang <= nums[index]; zhang++) {
            ways += process(coins, nums, rest - cur * zhang, index + 1);
        }

        return ways;
    }

    private Info getInfo(int[] arr) {
        Map<Integer, Integer> info = new HashMap<>();
        for (int coin : arr) {
            info.merge(coin, 1, Integer::sum);
        }

        int[] coins = new int[info.size()];
        int[] nums = new int[info.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : info.entrySet()) {
            coins[i] = entry.getKey();
            nums[i++] = entry.getValue();
        }

        return new Info(coins, nums);
    }

    private class Info {
        public int[] coins;
        public int[] nums;

        public Info(int[] coins, int[] nums) {
            this.coins = coins;
            this.nums = nums;
        }
    }

    /**
     * 动态规划
     * 优化 -> 一维
     */
    private int coinWays2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] nums = info.nums;

        int len = coins.length;
        int[][] dp = new int[aim + 1][len];
        // 第一行
        for (int index = 0; index < len; index++) {
            dp[0][index] = 1;
        }

        // 最后一列
        for (int rest = 1; rest <= aim; rest++) {
            int cur = coins[len - 1];
            if (rest % cur == 0 && rest / cur <= nums[len - 1]) {
                dp[rest][len - 1] = 1;
            }
        }

        // 普通位置，依赖后面的一列
        for (int index = len - 2; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int cur = coins[index];
                int ways = 0;
                for (int zhang = 0; zhang <= nums[index]; zhang++) {
                    if (rest - cur * zhang >= 0) {
                        ways += dp[rest - cur * zhang][index + 1];
                    }
                }
                dp[rest][index] = ways;
            }
        }
        return dp[aim][0];
    }

}
