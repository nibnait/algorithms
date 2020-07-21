package algorithm_practice.LeetCode.code000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class H097_交错字符串 {
  /*
  给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。

示例 1:

输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
输出: true
示例 2:

输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
输出: false

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/interleaving-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */

  public static void main(String[] args) {
    H097_交错字符串 obj = new H097_交错字符串();
    System.out.println(obj.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    System.out.println(obj.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
  }

  public boolean isInterleave(String s1, String s2, String s3) {
    int m = s1.length();
    int n = s2.length();
    int p = s3.length();

    if (n + m != p) {
      // directly fail
      return false;
    }

    // init dp array
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;

    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        int l = i + j - 1;
        if (i > 0) {
          dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(l));
        }
        if (j > 0) {
          dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(l));
        }
      }
    }
    return dp[m][n];
  }

}
