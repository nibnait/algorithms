package cc.tianbin.algorithmzuo.b_体系学习班.c0700_动态规划;

import cc.tianbin.common.util.SysOut;
import cc.tianbin.common.util.SysRandom;
import org.junit.Test;

/**
 * Created by nibnait on 2022/11/13
 */
public class Code10_MinPathSum {

    @Test
    public void test() {
        int rowSize = 10;
        int colSize = 10;
        int[][] m = SysRandom.generateMatrixNaturalNum(rowSize, colSize);
        int ans1 = minPathSum1(m);
        int ans2 = minPathSum2(m);

        if (ans1 != ans2) {
            SysOut.printArray(m);
            SysOut.printf("ans1: {}, ans2: {}", ans1, ans2);
            throw new RuntimeException();
        }
    }

    /**
     * 暴力递归
     */
    public int minPathSum1(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }

        return process(m, 0, 0);
    }

    private int process(int[][] m, int x, int y) {
        int xLen = m.length;
        int yLen = m[0].length;
        if (x == xLen - 1 && y == yLen - 1) {
            return m[xLen - 1][yLen - 1];
        }

        // 往右走
        int p1 = Integer.MAX_VALUE;
        if (x + 1 < xLen) {
            p1 = m[x][y] + process(m, x + 1, y);
        }
        // 往下走
        int p2 = Integer.MAX_VALUE;
        if (y + 1 < yLen) {
            p2 = m[x][y] + process(m, x, y + 1);
        }
        return Math.min(p1, p2);
    }

    /**
     * 动态规划
     * 优化 -> 一维
     */
    public int minPathSum2(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }

        int xLen = m.length;
        int yLen = m[0].length;
        int[][] dp = new int[xLen][yLen];
        dp[xLen - 1][yLen - 1] = m[xLen - 1][yLen - 1];
        // 最后一行
        for (int x = yLen - 2; x >= 0; x--) {
            dp[xLen - 1][x] = m[xLen - 1][x] + dp[xLen - 1][x + 1];
        }
        // 最后一列
        for (int y = xLen - 2; y >= 0; y--) {
            dp[y][yLen - 1] = m[y][yLen - 1] + dp[y+1][yLen - 1];
        }

        for (int x = xLen - 2; x >= 0; x--) {
            for (int y = yLen - 2; y >= 0; y--) {
                // 往右走
                int p1 = m[x][y] + dp[x + 1][y];
                // 往下走
                int p2 = m[x][y] + dp[x][y + 1];
                dp[x][y] = Math.min(p1, p2);
            }
        }

        return dp[0][0];
    }

}
