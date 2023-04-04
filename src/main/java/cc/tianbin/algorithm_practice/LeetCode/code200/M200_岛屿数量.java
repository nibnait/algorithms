package cc.tianbin.algorithm_practice.LeetCode.code200;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *
 * 示例 1：
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 *
 * 示例 2：
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 *
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 *
 * https://leetcode.cn/problems/number-of-islands/
 * Created by nibnait on 2023/03/30
 */
public class M200_岛屿数量 {

    @Test
    public void test() {
        char[][] grid = new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        int result = numIslands(grid);
        int except = 1;
        Assert.assertEquals(except, result);


        grid = new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        result = numIslands(grid);
        except = 3;
        Assert.assertEquals(except, result);
    }


    private class Dot {

    }

    private class UnionFind {
        private Map<Dot, Integer> sizeMap = new HashMap<>();
        private Map<Dot, Dot> fatherMap = new HashMap<>();

        public Integer size() {
            return this.sizeMap.size();
        }

        private void addDot(Dot dot) {
            sizeMap.put(dot, 1);
            fatherMap.put(dot, dot);
        }

        public void union(Dot dot1, Dot dot2) {
            Dot head1 = findAncestor(dot1);
            Dot head2 = findAncestor(dot2);

            if (head1 == head2) {
                return;
            }

            Integer h1Size = sizeMap.get(head1);
            Integer h2Size = sizeMap.get(head2);
            Dot big = h1Size > h2Size ? head1 : head2;
            Dot small = h1Size > h2Size ? head2 : head1;

            fatherMap.put(small, big);
            sizeMap.put(big, h1Size + h2Size);
            sizeMap.remove(small);
        }

        public Dot findAncestor(Dot dot) {
            Stack<Dot> stack = new Stack<>();
            while (dot != fatherMap.get(dot)) {
                stack.push(dot);
                dot = fatherMap.get(dot);
            }

            while (!stack.isEmpty()) {
                Dot pop = stack.pop();
                fatherMap.put(pop, dot);
            }

            return dot;
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        UnionFind unionFind = new UnionFind();
        Dot[][] dot = new Dot[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dot[i][j] = new Dot();
                    unionFind.addDot(dot[i][j]);
                }
            }
        }

        // 第一列
        for (int i = 1; i < grid.length; i++) {
            if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
                unionFind.union(dot[i - 1][0], dot[i][0]);
            }
        }

        // 第一行
        for (int j = 1; j < grid[0].length; j++) {
            if (grid[0][j - 1] == '1' && grid[0][j] == '1') {
                unionFind.union(dot[0][j - 1], dot[0][j]);
            }
        }

        // 上 + 左
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                // 上
                if (grid[i - 1][j] == '1' && grid[i][j] == '1') {
                    unionFind.union(dot[i - 1][j], dot[i][j]);
                }

                // 左
                if (grid[i][j - 1] == '1' && grid[i][j] == '1') {
                    unionFind.union(dot[i][j - 1], dot[i][j]);
                }
            }
        }

        return unionFind.size();
    }
}
