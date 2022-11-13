package algorithmzuo.b_体系学习班.c0700_动态规划;

import common.util.SysOut;
import org.junit.Test;

/**
 * Created by nibnait on 2022/11/13
 */
public class Code07_PalindromeSubsequence {

    @Test
    public void test() {
        String str = "a12b3c43def2ghi1kpm";
        int ans1 = longestPalindromeSubseq1(str);
        int ans2 = longestPalindromeSubseq2(str);

        if (ans1 != ans2) {
            SysOut.printf("ans1: {}, ans2: {}", ans1, ans2);
        }

    }

    /**
     * https://leetcode.com/problems/longest-palindromic-subsequence/
     * <p>
     * 返回这个字符串的最长回文子序列长度
     */
    public int longestPalindromeSubseq1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        return process(str.toCharArray(), 0, str.length() - 1);
    }

    private int process(char[] str, int left, int right) {
        if (left == right) {
            return 1;
        }

        if (left + 1 == right) {
            return str[left] == str[right] ? 2 : 1;
        }

        int p1 = process(str, left + 1, right);
        int p2 = process(str, left, right - 1);
        int p3 = str[left] == str[right] ? 2 : 0;
        p3 += process(str, left + 1, right - 1);
        return Math.max(p1, Math.max(p2, p3));
    }

    /**
     * 转成 dp 数组
     */
    public int longestPalindromeSubseq2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int length = str.length();
        int[][] dp = new int[length][length];

        char[] s = str.toCharArray();
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
            if (i + 1 < length) {
                dp[i][i + 1] = s[i] == s[i + 1] ? 2 : 1;
            }
        }

        for (int left = length - 3; left >= 0; left--) {
            for (int right = left + 2; right < length; right++) {
                int p1 = dp[left + 1][right];
                int p2 = dp[left][right - 1];
                int p3 = s[left] == s[right] ? 2 : 0;
                p3 += dp[left + 1][right - 1];
                dp[left][right] = Math.max(p1, Math.max(p2, p3));
            }
        }

        return dp[0][length - 1];
    }

}
