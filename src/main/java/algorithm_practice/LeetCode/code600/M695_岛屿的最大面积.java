package algorithm_practice.LeetCode.code600;

import junit.framework.TestCase;
import org.junit.Test;

/*
给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。

找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)

示例 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。

示例 2:

[[0,0,0,0,0,0,0,0]]
对于上面这个给定的矩阵, 返回 0。

注意: 给定的矩阵grid 的长度和宽度都不超过 50。



来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/max-area-of-island
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
Created by nibnait on 2020-01-09
 */
public class M695_岛屿的最大面积 extends TestCase {

    @Test
    public void testCase() {
        int[][] grid = new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        System.out.println(maxAreaOfIsland(grid));

        int[][] grid2 = new int[][]{{1, 1, 1, 0, 1, 1, 1, 1, 1}};
        System.out.println(maxAreaOfIsland(grid2));


    }

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        if (grid.length == 0) {
            return maxArea;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    maxArea = Math.max(maxArea, findMaxArea(grid, i, j, 1));
                }

            }
        }

        return maxArea;
    }

    private int findMaxArea(int[][] grid, int i, int j, int result) {
        if (i > 0 && grid[i - 1][j] == 1) {
            grid[i - 1][j] = 0;
            result += findMaxArea(grid, i - 1, j, 1);
        }
        if (i + 1 < grid.length && grid[i + 1][j] == 1) {
            grid[i + 1][j] = 0;
            result += findMaxArea(grid, i + 1, j, 1);
        }
        if (j > 0 && grid[i][j - 1] == 1) {
            grid[i][j - 1] = 0;
            result += findMaxArea(grid, i, j - 1, 1);
        }
        if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {
            grid[i][j + 1] = 0;
            result += findMaxArea(grid, i, j + 1, 1);
        }
        return result;
    }

}