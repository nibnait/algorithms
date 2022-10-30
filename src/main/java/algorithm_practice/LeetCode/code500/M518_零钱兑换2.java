package algorithm_practice.LeetCode.code500;

import org.junit.Assert;
import org.junit.Test;

/*
给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 

 

示例 1:

输入: amount = 5, coins = [1, 2, 5]
输出: 4
解释: 有四种方式可以凑成总金额:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
示例 2:

输入: amount = 3, coins = [2]
输出: 0
解释: 只用面额2的硬币不能凑成总金额3。
示例 3:

输入: amount = 10, coins = [10]
输出: 1


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/coin-change-2
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M518_零钱兑换2 {

    @Test
    public void testCase() {
        int[] coins = new int[]{1, 2, 5};
        int amount = 5;
        int excepted = 4;
        Assert.assertEquals(excepted, change(amount, coins));

        coins = new int[]{2};
        amount = 3;
        excepted = 0;
        Assert.assertEquals(excepted, change(amount, coins));

        coins = new int[]{10};
        amount = 10;
        excepted = 1;
        Assert.assertEquals(excepted, change(amount, coins));

        coins = new int[]{};
        amount = 0;
        excepted = 1;
        Assert.assertEquals(excepted, change(amount, coins));

        coins = new int[]{};
        amount = 10;
        excepted = 0;
        Assert.assertEquals(excepted, change(amount, coins));

    }


    public int change(int amount, int[] coins) {
        if (coins.length == 0) {
            return amount == 0 ? 1 : 0;
        }

        // dp[i]: 凑成 i 元钱的硬币组合数
        int[] dp = new int[amount+1];
        dp[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i] >= 0) {
                    dp[j] = dp[j] + dp[j-coins[i]];
                }
            }
        }

        return dp[amount];
    }
}
