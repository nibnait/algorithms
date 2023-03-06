package cc.tianbin.algorithmzuo.b_体系学习班.c0700_动态规划;

import cc.tianbin.common.CommonConstants;
import cc.tianbin.common.util.SysOut;
import org.junit.Test;

/**
 * Created by nibnait on 2022/11/13
 */
public class Code17_SplitNumber {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int test = 4;
        int ans1 = ways(test);
        int ans2 = dp1(test);
        int ans3 = dp2(test);

        if (ans1 != ans2 || ans2 != ans3) {
            SysOut.printf("ans1: {}, ans2: {}, ans3: {}", ans1, ans2, ans3);
            throw new RuntimeException();
        }

    }

    /**
     * 暴力递归
     */
    public int ways(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        return process(1, n);
    }

    /**
     * @param pre  前面已经拆出来的数的和
     * @param rest 还剩 rest 需要去拆
     * @return 拆的方法数
     */
    private int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int i = pre; i <= rest; i++) {
            ways += process(i, rest - i);
        }
        return ways;
    }

    /**
     * 动态规划
     */
    public int dp1(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[][] dp = new int[n + 1][n + 1];

        // 第一列，第i行i列
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }

        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                int ways = 0;
                for (int i = pre; i <= rest; i++) {
                    ways += dp[i][rest - i];
                }
                dp[pre][rest] = ways;
            }
        }

        return dp[1][n];
    }

    /**
     * 对 dp[i][rest - i] 的枚举值的优化
     * 通过观察 得出 dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
     */
    public int dp2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[][] dp = new int[n + 1][n + 1];

        // 第一列，第i行i列
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }

        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
            }
        }

        return dp[1][n];
    }

}
