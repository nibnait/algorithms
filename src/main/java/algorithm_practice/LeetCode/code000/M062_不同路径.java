package algorithm_practice.LeetCode.code000;

public class M062_不同路径 {

  /*
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？

示例 1:

输入: m = 3, n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右
示例 2:

输入: m = 7, n = 3
输出: 28

提示：

1 <= m, n <= 100
题目数据保证答案小于等于 2 * 10 ^ 9
   */
  public static void main(String[] args) {
    assert uniquePaths(3, 2) == 3;
    assert uniquePaths(7, 3) == 28;
  }

  /*
  1. 时间复杂度O(n^2),空间复杂度O(n^2)
状态转移方程：
机器人可以向下或者向右移动，所以数组中的某点dp[i][j]应当是dp[i-1][j] + dp[i][j-1]。
边界条件：
到达初始位置只有一种可能，所以dp[0][0] =1。
沿着初始的行和列行走都只有一种可能，所以dp[any][0] = 1, dp[0][any] = 1.

   */
  public static int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
      dp[i][0] = 1;
    }
    for (int j = 0; j < n; j++) {
      dp[0][j] = 1;
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
      }
    }
    return dp[m - 1][n - 1];
  }
}
