package cc.tianbin.algorithmzuo.b_体系学习班.c0700_动态规划;

import cc.tianbin.common.CommonConstants;
import cc.tianbin.common.util.SysOut;
import cc.tianbin.common.util.SysRandom;
import org.junit.Test;

/**
 * Created by nibnait on 2022/11/12
 */
public class Code04_ConvertToLetterString {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        String s = SysRandom.generateStringNaturalNum(30);
//        s = "111";
//        s = "1263121";
        int ans1 = numbers(s);
        int ans2 = dp(s);

        if (ans1 != ans2) {
            SysOut.printf("ans1: {}, ans2: {}", ans1, ans2);
            throw new RuntimeException();
        }

        SysOut.printf("s:{}, ans: {}", s, ans1);
    }

    /**
     * 暴力递归
     */
    public int numbers(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        return process(str, 0);
    }

    private int process(char[] str, int index) {
        if (index == str.length) {
            return 1;
        }
        if (str[index] == '0') {
            return 0;
        }

        int p1 = process(str, index + 1);
        int p2 = 0;
        if (index + 1 < str.length && (str[index] - '0') * 10 + str[index + 1] - '0' <= 26) {
            p2 = process(str, index + 2);
        }
        return p1 + p2;
    }

    /**
     * dp数组
     */
    public int dp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        int[] dp = new int[str.length + 1];
        dp[str.length] = 1;
        for (int index = str.length - 1; index >= 0; index--) {
            if (str[index] == '0') {
                continue;
            }
            int ways = dp[index + 1];
            if (index + 1 < str.length && (str[index] - '0') * 10 + str[index + 1] - '0' <= 26) {
                ways += process(str, index + 2);
            }
            dp[index] = ways;
        }

        return dp[0];
    }

}
