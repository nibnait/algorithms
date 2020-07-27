package algorithm_practice.LeetCode.code000;

public class M064_最小路径和 {

  /*
  给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

示例:

输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-path-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */

  public static void main(String[] args) {
    int[][] path = new int[][]{new int[]{1, 3, 1}, new int[]{1, 5, 1}, new int[]{4, 2, 1}};
    M064_最小路径和 obj = new M064_最小路径和();
    System.out.println(obj.minPathSum(path));
  }

  /*
  解法：
1.动态规划
数组：动态规划数组大小dp[m][n]，和arry相同大小。数组内容代表到达这点的最小路径消耗。
转移方程：dp[i][j]的大小取决于它上面和左边元素的大小，我们有dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + array[i][j]
边界条件：dp[0][0]就是array[0][0]，第一行就是累加，第一列也是累加。
   */
  public int minPathSum(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    int[][] dp = new int[m][n];
    dp[0][0] = grid[0][0];
    for (int i = 1; i < m; i++) {
      dp[i][0] = dp[i - 1][0] + grid[i][0];
    }
    for (int j = 1; j < n; j++) {
      dp[0][j] = dp[0][j - 1] + grid[0][j];
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
      }
    }

    return dp[m - 1][n - 1];
  }
}
