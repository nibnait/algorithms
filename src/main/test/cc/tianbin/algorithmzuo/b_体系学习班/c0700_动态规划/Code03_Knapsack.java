package cc.tianbin.algorithmzuo.b_体系学习班.c0700_动态规划;

import org.junit.Test;

/**
 * Created by nibnait on 2022/11/12
 */
public class Code03_Knapsack {

    @Test
    public void test() {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }

    /**
     * @param weights i号物品的重量
     * @param values  i好物品的价值
     * @param bag     背包的容量
     * @return 背包能装下的最大价值
     */
    public int maxValue(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length != values.length || weights.length == 0) {
            return 0;
        }

        return process(weights, values, 0, bag);
    }

//        private int process(int[] weights, int[] values, int index, int bagRest) {
//        if (bagRest < 0) {
//            return 0;
//        }
//        if (index == weights.length) {
//            return 0;
//        }
//        if (weights[index] > bagRest) {
//            return process(weights, values, index + 1, bagRest);
//        }
//        return Math.max(process(weights, values, index + 1, bagRest), values[index] + process(weights, values, index + 1, bagRest - weights[index]));
//    }
    // 上面这个 确实是自然智慧想出来的。但是不太好转dp
    private int process(int[] weights, int[] values, int index, int bagRest) {
        if (bagRest < 0) {
            return -1;
        }
        if (index == weights.length) {
            return 0;
        }

        int p1 = process(weights, values, index + 1, bagRest);
        int p2 = 0;
        int next = process(weights, values, index + 1, bagRest - weights[index]);
        if (next != -1) {
            p2 = values[index] + next;
        }
        return Math.max(p1, p2);
    }

    /**
     * 转成 dp 数组
     */
    public int dp(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length != values.length || weights.length == 0) {
            return 0;
        }

        // bag 0 ~ bag
        // index 0 ~ length
        int length = weights.length;
        int[][] dp = new int[length + 1][bag + 1];

        // dp[i][length] = 0, 已完成

        // dp[index][bagRest] 依赖 Math.max(dp[index+1][bagRest], values[index] + dp[index+1][bagRest - weights[index])
        for (int index = length - 1; index >= 0; index--) {
            for (int bagRest = 0; bagRest <= bag; bagRest++) {
                int p1 = dp[index + 1][bagRest];
                int p2 = 0;
                if (bagRest - weights[index] >= 0) {
                    int next = dp[index + 1][bagRest - weights[index]];
                    p2 = next + values[index];
                }
                dp[index][bagRest] = Math.max(p1, p2);
            }
        }

        return dp[0][bag];
    }

}
