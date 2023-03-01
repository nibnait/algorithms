package cc.tianbin.algorithmzuo.b_体系学习班.c0700_动态规划;

import cc.tianbin.common.CommonConstants;
import cc.tianbin.common.util.SysOut;
import cc.tianbin.common.util.SysRandom;
import org.junit.Test;

/**
 * Created by nibnait on 2022/11/13
 */
public class Code16_KillMonster {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int nMax = 10;
        int mMax = 10;
        int kMax = 10;

        int n = SysRandom.generateNaturalNum(nMax);
        int m = SysRandom.generateNaturalNum(mMax);
        int k = SysRandom.generateNaturalNum(kMax);

        double ans1 = killProbability1(n, m, k);
        double ans2 = killProbability2(n, m, k);

        if (ans1 != ans2) {
            SysOut.printf("n={}, m={}, k={}", n, m, k);
            SysOut.printf("ans1: {}, ans2: {}", ans1, ans2);
            throw new RuntimeException();
        }
    }

    /**
     * 暴力递归
     */
    private double killProbability1(int n, int m, int k) {
        if (m * k < n || m <= 0 || k <= 0) {
            return 0;
        }

        long kill = process(m, n, k);
        return kill / Math.pow(m + 1, k);
    }

    private long process(int m, int restBlood, int restTimes) {
        if (restTimes == 0) {
            return restBlood <= 0 ? 1 : 0;
        }
        if (restBlood <= 0) {
            return (long) Math.pow(m + 1, restTimes);
        }

        long ways = 0;
        for (int i = 0; i <= m; i++) {
            ways += process(m, restBlood - i, restTimes - 1);
        }
        return ways;
    }

    /**
     * 动态规划
     */
    private double killProbability2(int n, int m, int k) {
        if (m * k < n || m <= 0 || k <= 0) {
            return 0;
        }
        long[][] dp = new long[n + 1][k + 1];
        // 第一列，只有 0滴血，省0次时，才算1种方法
        dp[0][0] = 1;

        // 从第2列开始，每次填一列
        // 依赖 前一列的值
        for (int restBlood = 0; restBlood <= n; restBlood++) {
            for (int restTimes = 1; restTimes <= k; restTimes++) {
                long ways = 0;
                for (int i = 0; i <= m; i++) {
                    if (restBlood - i <= 0) {
                        ways += (long) Math.pow(m + 1, restTimes - 1);
                    } else {
                        ways += dp[restBlood - i][restTimes - 1];
                    }
                }
                dp[restBlood][restTimes] = ways;
            }
        }

        return dp[n][k] / Math.pow(m + 1, k);
    }

}
