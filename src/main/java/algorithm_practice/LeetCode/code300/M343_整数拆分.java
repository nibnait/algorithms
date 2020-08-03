package algorithm_practice.LeetCode.code300;

public class M343_整数拆分 {
  /*
  给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。

示例 1:

输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1。
示例 2:

输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
说明: 你可以假设 n 不小于 2 且不大于 58。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/integer-break
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */

  public static void main(String[] args) {
    System.out.println(new M343_整数拆分().integerBreak(2));
    System.out.println(new M343_整数拆分().integerBreak(10));


  }

  /*
  解法：
1.动态规划
数组：动态规划数组大小为dp[n+1]，记录的是到第i位的时候最大的拆分乘积，
我们可以看到的是数组中的第i个数可以拆分成k和n-k，如果对n-k进行继续拆分，
就是k * dp[n-k]，如果n-k不进行继续拆分，就是k * （n-k）.
转移方程：综上我们可以看到，第n个位置的数其实是取决于其前n-1位的所有的结果，
所以我们可以进行正向dp从I = 2开始进行dp，dp[i] = 遍历所有的i的拆分下max(dp[i-k] * k, (i-k) * k)
边界条件：因为0和1都是不可拆分的，所以dp[0] = dp[1] = 0;
复杂度分析：从2到n每次都要遍历i-1次，所以时间复杂度是O(N^2），空间复杂度是dp数组的大小O(N+1)
   */
  public int integerBreak(int n) {
    int[] dp = new int[n + 1];
    for (int i = 2; i <= n; i++) {
      int curMax = 0;
      for (int j = 1; j < i; j++) {
        curMax = Math.max(curMax, Math.max(dp[i - j] * j, (i - j) * j));
      }
      dp[i] = curMax;
    }
    return dp[n];
  }

}
