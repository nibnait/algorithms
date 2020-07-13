package algorithm_practice.LeetCode.code300;

public class M309_最佳买卖股票时机含冷冻期 {

  /*
  给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
示例:

输入: [1,2,3,0,2]
输出: 3
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {
//    System.out.println(maxProfit(new int[]{1, 2, 3, 0, 2}));
//    System.out.println(maxProfit(new int[]{3, 2, 6, 5, 0, 3}));
//    System.out.println(maxProfit(new int[]{2, 1, 4, 5, 2, 9, 7}));
    System.out.println(maxProfit(new int[]{2,6,8,7,8,7,9,4,1,2,4,5,8}));
  }

  /*

   */
  public static int maxProfit(int[] prices) {
    if(prices.length == 0) {
      return 0;
    }
    int[][] dp = new int[prices.length][3];
    dp[0][0] =  - prices[0];
    for(int i=1; i<dp.length; i++) {
      dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] - prices[i]);
      dp[i][1] = dp[i-1][0] + prices[i];
      dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]);
    }
    return Math.max(dp[prices.length-1][1], dp[prices.length-1][2]);
  }
}
