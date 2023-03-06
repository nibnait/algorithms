package cc.tianbin.algorithm_practice.LeetCode.code000;

import org.junit.Assert;
import org.junit.Test;

/*
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？



网格中的障碍物和空位置分别用 1 和 0 来表示。

说明：m 和 n 的值均不超过 100。

示例 1:

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
public class M063_不同路径2 {
    @Test
    public void testCase() {
        int[][] obstacleGrid = new int[][]{
                {0,0,0},
                {0,1,0},
                {0,0,0}};
        int expected = 2;
        Assert.assertEquals(expected, uniquePathsWithObstacles(obstacleGrid));

        obstacleGrid = new int[][]{
                {1,0}};
        expected = 0;
        Assert.assertEquals(expected, uniquePathsWithObstacles(obstacleGrid));

        obstacleGrid = new int[][]{
                {0,0},
                {0,1}};
        expected = 0;
        Assert.assertEquals(expected, uniquePathsWithObstacles(obstacleGrid));

        obstacleGrid = new int[][]{
                {0,0,0,0,0},
                {0,0,0,0,1},
                {0,0,0,1,0},
                {0,0,1,0,0}};
        expected = 0;
        Assert.assertEquals(expected, uniquePathsWithObstacles(obstacleGrid));

    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0) {
            return 0;
        }

        int n = obstacleGrid[0].length;
        if (obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }

        int[][] dp = new int[m][n];

        boolean blockFlag = false;
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1 || blockFlag) {
                dp[i][0] = 0;
                blockFlag = true;
            } else {
                dp[i][0] = 1;
            }
        }

        blockFlag = false;
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1 || blockFlag) {
                dp[0][i] = 0;
                blockFlag = true;
            } else {
                dp[0][i] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[m-1][n-1];
    }
}
