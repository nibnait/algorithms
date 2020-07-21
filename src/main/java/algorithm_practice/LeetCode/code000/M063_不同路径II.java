package algorithm_practice.LeetCode.code000;

public class M063_不同路径II {

  /*
  一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

网格中的障碍物和空位置分别用 1 和 0 来表示。

说明：m 和 n 的值均不超过 100。

示例 1:

输入:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
输出: 2
解释:
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-paths-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {
    int[][] grid = new int[3][3];
    grid[1][1] = 1;
    assert uniquePathsWithObstacles(grid) == 2;
  }

  /*
  动态规划
1.时间复杂度O(n^2),空间复杂度O(n^2)
状态转移方程：
和62题类似，但是这时候加入了block，所以dp数组的取值还和block也就是grid m,n点是否有障碍物有关。
我们有dp[m][n] = dp[m-1][n] + dp[m][n-1] (if grid[m][n] != 1)
			= 0 (if grid[m][n] == 1, blocked)
边界条件：
在初始位置同行同列中，我们可以知道在遇到block之前都是1，遇到block之后必然都是0.

   */
  public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
    for (int i = 0; i < obstacleGrid.length; i++) {
      if (obstacleGrid[i][0] == 1) {
        break;
      }
      dp[i][0] = 1;
    }
    for (int j = 0; j < obstacleGrid[0].length; j++) {
      if (obstacleGrid[0][j] == 1) {
        break;
      }
      dp[0][j] = 1;
    }
    for (int i = 1; i < obstacleGrid.length; i++) {
      for (int j = 1; j < obstacleGrid[0].length; j++) {
        if (obstacleGrid[i][j] == 1) {
          dp[i][j] = 0;
        } else {
          dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
      }
    }
    return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
  }
}
