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

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // dp[i][j]: matrix[i][j]位置的最长递增格数
        int[][] dp = new int[m][n];
        int res = 1;




        return res;
    }

}
