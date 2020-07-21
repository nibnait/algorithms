package algorithm_practice.LeetCode.code100;

import java.util.Arrays;
import java.util.List;

public class M120_三角形最小路径和 {

  /*
  给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。

 

例如，给定三角形：

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

 

说明：

如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/triangle
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {
    List<Integer> level1 = Arrays.asList(2);
    List<Integer> level2 = Arrays.asList(3, 4);
    List<Integer> level3 = Arrays.asList(6, 5, 7);
    List<Integer> level4 = Arrays.asList(4, 1, 8, 3);
    List<List<Integer>> triangle = Arrays.asList(level1, level2, level3, level4);
    System.out.println(minimumTotal(triangle));
  }

  /*
  1.动态规划
转移方程：记动态规划数组dp[i][j]为到达i,j点的最小路径长度，我们有dp[i][j] = min(dp[i-1][j-1], dp[i][j-1]) + triangle[i][j])，
最终triangle[n-1]行中最小的元素就是我们要的
边界条件：dp[0][0] = triangle[i][j], dp[i][0] = sum(triangle[0 to i][0]),dp [i][i] = sum(triangle[o to i][0 to i])。

   */
  public static int minimumTotal(List<List<Integer>> triangle) {
    int n = triangle.size();
    int[][] dp = new int[n][n];
    dp[0][0] = triangle.get(0).get(0);
    for (int i = 1; i < n; i++) {
      dp[i][0] = triangle.get(i).get(0) + dp[i - 1][0];
      for (int j = 1; j < i; j++) {
        dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
      }
      dp[i][i] = triangle.get(i).get(i) + dp[i - 1][i - 1];
    }
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      if (dp[n - 1][i] < min) {
        min = dp[n - 1][i];
      }
    }
    return min;
  }
}
