package cc.tianbin.algorithmzuo.b_体系学习班.c0700_动态规划;

import org.junit.Test;

/**
 * Created by nibnait on 2022/11/06
 */
public class Code01_RobotWalk {

    @Test
    public void test() {

        int N = 9;
        int M = 1;
        int K = 4;
        int P = 5;
        int ans1 = robotWalkRecur(N, M, K, P);
        System.out.println(ans1);

        int ans2 = robotWalkDp(N, M, K, P);
        System.out.println(ans2);
    }

    /**
     * @param n 格子范围 1 ~ n
     * @param m 当前在 m 位置
     * @param k 必须走 k 步
     * @param p 最终到达 p 位置
     * @return 一共有多少种走法
     */
    public int robotWalkRecur(int n, int m, int k, int p) {
        if (m <= 0 || m >= n || p <= 0 || p >= n) {
            return -1;
        }
        return process1(n, m, k, p);
    }

    private int process1(int n, int cur, int rest, int p) {
        if (rest == 0) {
            return cur == p ? 1 : 0;
        }
        if (cur == n) {
            return process1(n, cur - 1, rest - 1, p);
        }
        if (cur == 1) {
            return process1(n, cur + 1, rest - 1, p);
        }
        return process1(n, cur - 1, rest - 1, p) + process1(n, cur + 1, rest - 1, p);
    }

    /**
     * @param n 格子范围 1 ~ n
     * @param m 当前在 m 位置
     * @param k 必须走 k 步
     * @param p 最终到达 p 位置
     * @return 一共有多少种走法
     */
    public int robotWalkDp(int n, int m, int k, int p) {
        if (m <= 0 || m >= n || p <= 0 || p >= n) {
            return -1;
        }

        int[][] dp = new int[n + 1][k + 1];
        // 还剩0步时，只有到达 p 位置，才算有1种走法
        dp[p][0] = 1;

        for (int col = 1; col <= k; col++) {
            // 第1行，只依赖 第2行,前一列 位置的值
            dp[1][col] = dp[2][col - 1];

            for (int row = 2; row <= n-1; row++) {
                // [row][col] 位置
                dp[row][col] = dp[row - 1][col - 1] + dp[row + 1][col - 1];
            }

            // 最后一行，只依赖 倒数第2行,前一列 位置的值
            dp[n][col] = dp[n-1][col - 1];
        }
        return dp[m][k];
    }
}
