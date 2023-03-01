package cc.tianbin.algorithmzuo.b_体系学习班.c0700_动态规划;

import cc.tianbin.common.util.SysOut;
import org.junit.Test;

/**
 * Created by nibnait on 2022/11/13
 */
public class Code15_BobDie {

    @Test
    public void test() {
        int row = 6;
        int col = 6;
        int k = 10;
        int N = 50;
        int M = 50;
        double ans1 = livePosibility1(row, col, k, N, M);
        double ans2 = livePosibility2(row, col, k, N, M);

        if (ans1 != ans2) {
            SysOut.printf("ans1: {}, ans2: {}", ans1, ans2);
            throw new RuntimeException();
        }
    }

    /**
     * 暴力递归
     */
    public double livePosibility1(int row, int col, int k, int N, int M) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            return 0;
        }

        int liveWays = process(N, M, row, col, k);
        return liveWays / Math.pow(4, k);
    }

    private int process(int n, int m, int curRow, int curCol, int rest) {
        if (curRow < 0 || curRow >= n || curCol < 0 || curCol >= m) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }

        // 上
        int ways = process(n, m, curRow - 1, curCol, rest - 1);
        // 下
        ways += process(n, m, curRow + 1, curCol, rest - 1);
        // 左
        ways += process(n, m, curRow, curCol - 1, rest - 1);
        // 右
        ways += process(n, m, curRow, curCol + 1, rest - 1);
        return ways;
    }

    /**
     * 动态规划
     * 优化 -> 二维
     */
    public double livePosibility2(int row, int col, int k, int n, int m) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return 0;
        }

        int[][][] dp = new int[n][m][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j][0] = 1;
            }
        }

        for (int rest = 1; rest <= k; rest++) {
            for (int curRow = 0; curRow < n; curRow++) {
                for (int curCol = 0; curCol < m; curCol++) {
                    // 上
                    int ways = getValue(dp, n, m, curRow - 1, curCol, rest - 1);
                    // 下
                    ways += getValue(dp, n, m, curRow + 1, curCol, rest - 1);
                    // 左
                    ways += getValue(dp, n, m, curRow, curCol - 1, rest - 1);
                    // 右
                    ways += getValue(dp, n, m, curRow, curCol + 1, rest - 1);
                    dp[curCol][curRow][rest] = ways;
                }
            }

        }

        return dp[row][col][k] / Math.pow(4, k);
    }

    private int getValue(int[][][] dp, int n, int m, int curRow, int curCol, int rest) {
        if (curRow < 0 || curRow >= n || curCol < 0 || curCol >= m) {
            return 0;
        }
        return dp[curRow][curCol][rest];
    }
}
