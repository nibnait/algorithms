package algorithm_practice.LeetCode.code900;

import org.junit.Assert;
import org.junit.Test;

/*
在二维网格 grid 上，有 4 种类型的方格：

1 表示起始方格。且只有一个起始方格。
2 表示结束方格，且只有一个结束方格。
0 表示我们可以走过的空方格。
-1 表示我们无法跨越的障碍。
返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目。

每一个无障碍方格都要通过一次，但是一条路径中不能重复通过同一个方格。

 

示例 1：

输入：[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
输出：2
解释：我们有以下两条路径：
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
示例 2：

输入：[[1,0,0,0],[0,0,0,0],[0,0,0,2]]
输出：4
解释：我们有以下四条路径：
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
示例 3：

输入：[[0,1],[2,0]]
输出：0
解释：
没有一条路能完全穿过每一个空的方格一次。
请注意，起始和结束方格可以位于网格中的任意位置。
 

提示：

1 <= grid.length * grid[0].length <= 20


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-paths-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class H980_不同路径3 {
    @Test
    public void testCase() {
        int[][] grid = new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}};
        int expected = 2;
        Assert.assertEquals(expected, uniquePathsIII(grid));

        grid = new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}};
        expected = 4;
        Assert.assertEquals(expected, uniquePathsIII(grid));

        grid = new int[][]{{0, 1}, {2, 0}};
        expected = 0;
        Assert.assertEquals(expected, uniquePathsIII(grid));

    }

    /*
       1. 找出起点
       2. 统计0的个数，确定回溯的结束条件
       3. 开始回溯
     */
    public int uniquePathsIII(int[][] grid) {

        int step = 0; // 0的个数
        int startX = 0, startY = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                    continue;
                }

                if (grid[i][j] == 0) {
                    step++;
                }
            }
        }

        // 走完所有的grid[][]==0，走到grid[][]==2时，也要耗费1步。
        return dfs(grid, step+1, startX, startY);
    }

    private int dfs(int[][] grid, int step, int x, int y) {

        if (x < 0 || y < 0
                || x >= grid.length || y >= grid[0].length
                || grid[x][y] == -1) {
            return 0;
        }

        if (grid[x][y] == 2) {
            return step == 0 ? 1 : 0;
        }

        int res = 0;

        grid[x][y] = -1;
        res += dfs(grid, step-1, x+1, y);
        res += dfs(grid, step-1, x, y-1);
        res += dfs(grid, step-1, x-1, y);
        res += dfs(grid, step-1, x, y+1);
        grid[x][y] = 0;

        return res;
    }
}
