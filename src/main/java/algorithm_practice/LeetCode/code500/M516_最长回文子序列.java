package algorithm_practice.LeetCode.code500;

import org.junit.Assert;
import org.junit.Test;

/*
给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。

 

示例 1:
输入:

"bbbab"
输出:

4
一个可能的最长回文子序列为 "bbbb"。

示例 2:
输入:

"cbbd"
输出:

2
一个可能的最长回文子序列为 "bb"。

 

提示：

1 <= s.length <= 1000
s 只包含小写英文字母


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M516_最长回文子序列 {

    @Test
    public void testCase() {
        String s = "bbbab";
        int excepted = 4;
        Assert.assertEquals(excepted, longestPalindromeSubseq(s));

        s = "cbbd";
        excepted = 2;
        Assert.assertEquals(excepted, longestPalindromeSubseq(s));
        
    }

    /*
        求的是：可能的最长回文子串的长度。典型的动态规划
        dp[i][j]: 字符串s[i...j]的回文子串的长度
        
        if (s[i-1] == s[j+1]) {
            dp[i][j] = dp[i+1][j-1] + 2;  
        } else {
            dp[i][j] = max(dp[i][j-1], dp[i-1][j]);
        }
        
        base case:
        i == j : dp[i][j] = 1;
        i > j : dp[i][j] = 0;
     */
    public int longestPalindromeSubseq(String s) {

        int length = s.length();
        int[][] dp = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                }
                if (i > j) {
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = length-2; i >= 0; i--) {
            for (int j = i+1; j < length; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }

        return dp[0][length-1];
    }

}
