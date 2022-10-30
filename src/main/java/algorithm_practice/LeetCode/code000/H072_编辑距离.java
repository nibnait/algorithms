package algorithm_practice.LeetCode.code000;

import org.junit.Assert;
import org.junit.Test;

/*
给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

插入一个字符
删除一个字符
替换一个字符
 

示例 1：

输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
示例 2：

输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/edit-distance
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class H072_编辑距离 {

    @Test
    public void testCase() {
        String word1 = "horse";
        String word2 = "ros";
        int minDistance = 3;
        Assert.assertEquals(minDistance, minDistance(word1, word2));

        word1 = "intention";
        word2 = "execution";
        minDistance = 5;
        Assert.assertEquals(minDistance, minDistance(word1, word2));

    }

    /**
     * 空间优化
     */
    public int minDistance(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();

        if (l1 == 0) {
            return l2;
        }

        if (l2 == 0) {
            return l1;
        }

        int dp_i_1 = 1;
        int dp_j_1 = 1;
        int dp_i_1_j_1 = 0;
        int dp_i_j = 0;

        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp_i_j = dp_i_1_j_1;
                    // todo 相邻位置的变化

                } else {
                    dp_i_j = Math.min(dp_i_1 + 1, Math.min(dp_j_1 + 1, dp_i_1_j_1 + 1));
                    // todo 相邻位置的变化

                }
            }
        }

        return dp_i_j;
    }

    /**
     * 动态规划
     */
    public int minDistance_normal(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();

        // dp[i][j]: w1[0...i-1] 与 w2[0...j-1] 的最短编辑距离
        int[][] dp = new int[l1+1][l2+1];

        for (int i = 0; i <= l1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= l2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = min(
                            dp[i][j - 1] + 1,       // word2的 j 往前移动1位：word1新增一个字符
                            dp[i - 1][j] + 1,       // word1的 i 往前移动1位：word1直接删除一个字符
                            dp[i - 1][j - 1] + 1    // word1 和 word2 一起移动，代表直接替换字符
                    );
                }
            }
        }

        return dp[l1][l2];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
