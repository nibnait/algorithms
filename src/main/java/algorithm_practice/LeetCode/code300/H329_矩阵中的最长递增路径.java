package algorithm_practice.LeetCode.code300;

import org.junit.Assert;
import org.junit.Test;

/*
给定一个整数矩阵，找出最长递增路径的长度。

对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。

示例 1:

输入: nums =
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
输出: 4
解释: 最长递增路径为 [1, 2, 6, 9]。
示例 2:

输入: nums =
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
输出: 4
解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/22
 */
public class H329_矩阵中的最长递增路径 {

    @Test
    public void testCase() {
        int[][] matrix = new int[][]{
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };
        int excepted = 4;
        Assert.assertEquals(excepted, longestIncreasingPath(matrix));

        matrix = new int[][]{
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
        };
        excepted = 4;
        Assert.assertEquals(excepted, longestIncreasingPath(matrix));

    }

    /**
     * dfs + memo
     */

    private int[][] direction = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
    private int maxLen = 0;
    private int[][] matrix;
    private int[][] memo;
    int m, n;

    public int longestIncreasingPath(int[][] matrix) {
        this.matrix = matrix;
        this.m = matrix.length;
        this.n = m==0 ? 0 : matrix[0].length;

        memo = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j);
            }
        }

        return maxLen;
    }

    private int dfs(int x, int y) {
        if (memo[x][y] != 0) {
            return memo[x][y];
        }

        memo[x][y] = 1;

        for (int[] dir : direction) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (inRange(newX, newY) && matrix[newX][newY] > matrix[x][y]) {
                memo[x][y] = Math.max(memo[x][y], dfs(newX, newY) + 1);
            }
        }

        maxLen = Math.max(maxLen, memo[x][y]);
        return memo[x][y];
    }

    private boolean inRange(int newX, int newY) {
        return newX >= 0 && newX < m && newY >= 0 && newY < n;
    }

}
