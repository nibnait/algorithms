package algorithm_practice.LeetCode.code100;

import org.junit.Assert;
import org.junit.Test;

/*
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。

注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:

输入: [2,4,1], k = 2
输出: 2
解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
示例 2:

输入: [3,2,6,5,0,3], k = 2
输出: 7
解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class H188_买卖股票的最佳时机4 {

    @Test
    public void testCase() {
        int[] prices = new int[]{2,4,1};
        int k = 2;
        int maxProfit = 2;
        Assert.assertEquals(maxProfit, maxProfit(k, prices));

        prices = new int[]{3,2,6,5,0,3};
        k = 2;
        maxProfit = 7;
        Assert.assertEquals(maxProfit, maxProfit(k, prices));

        prices = new int[]{1,2,4,2,5,7,2,4,9,0};
        k = 4;
        maxProfit = 15;
        Assert.assertEquals(maxProfit, maxProfit(k, prices));

        prices = new int[]{2,1,4};
        k = 2;
        maxProfit = 3;
        Assert.assertEquals(maxProfit, maxProfit(k, prices));

    }

    /**
     * 将三维数组 去掉天数维度，优化为 二维数组
     */
    public int maxProfit(int maxK, int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }

        if (maxK > n / 2) {
            return maxProfixWithKInfinity(prices);
        }

        int[][] dp = new int[maxK+1][2];

        for (int k = maxK; k >= 0 ; k--) {
            dp[k][0] = 0;
            dp[k][1] = -prices[0];
        }

        for (int i = 0; i < n; i++) {
            for (int k = maxK; k >= 1; k--) {
                // 第 i 天，第 k 次 买入
                dp[k-1][1] = Math.max(dp[k-1][1], dp[k-1][0] - prices[i]);

                // 第 i 天，第 k 次 卖出
                dp[k][0] = Math.max(dp[k][0], dp[k-1][1] + prices[i]);
            }
        }

        return dp[maxK][0];
    }

    /**
     * 最原始的想法 dp数组
     */
    public int maxProfit_base(int maxK, int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }

        if (maxK > n / 2) {
            return maxProfixWithKInfinity(prices);
        }

        int[][][] dp = new int[n][maxK+1][2];
        for (int k = maxK; k > 0; k--) {
            dp[0][k][0] = 0;
            dp[0][k][1] = -prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int k = maxK; k >= 1; k--) {
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        }

        return dp[n-1][maxK][0];
    }

    private int maxProfixWithKInfinity(int[] prices) {
        int n = prices.length;

        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }

        return dp[n-1][0];
    }

}
