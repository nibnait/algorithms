package cc.tianbin.algorithm_practice.LeetCode.code000;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 输入：grid = [[1,3,1],
 * ************ [1,5,1],
 * ************ [4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * 输入：grid = [[1,2,3],
 * ************ [4,5,6]]
 * 输出：12
 * <p>
 * https://leetcode.cn/problems/minimum-path-sum/?favorite=2cktkvj
 * Created by nibnait on 2023/03/09
 */
public class M064_最小路径和 {

    @Test
    public void test() {

        int[][] grid = new int[][] {
                {1,3,1},{1,5,1},{4,2,1}
        };
        int result = minPathSum(grid);
        Assert.assertEquals(7, result);

        grid = new int[][] {
                {1,2,3},{4,5,6}
        };
        result = minPathSum(grid);
        Assert.assertEquals(12, result);

    }

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int x = 1; x < grid.length; x++) {
            dp[x][0] = dp[x-1][0] + grid[x][0];
        }
        for (int y = 1; y < grid[0].length; y++) {
            dp[0][y] = dp[0][y-1] + grid[0][y];
        }

        for (int x = 1; x < grid.length; x++) {
            for (int y = 1; y < grid[0].length; y++) {
                dp[x][y] = grid[x][y] + Math.min(dp[x-1][y], dp[x][y-1]);
            }
        }

        return dp[grid.length-1][grid[0].length-1];
    }

    public int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        return process(grid, 0, 0, grid[0][0]);
    }

    private int process(int[][] grid, int x, int y, int sum) {
        if (x == grid.length - 1 && y == grid[0].length - 1) {
            return sum;
        }


        if (x == grid.length - 1) {
            return process(grid, x, y+1, sum+grid[x][y+1]);
        }

        if (y == grid[0].length - 1) {
            return process(grid, x+1, y, sum+grid[x+1][y]);
        }

        return Math.min(
                process(grid, x+1, y, sum+grid[x+1][y]),
                process(grid, x, y+1, sum+grid[x][y+1])
        );
    }
}
