package algorithm_practice.LeetCode.code300;

import org.junit.Assert;
import org.junit.Test;

/*
给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。

示例:

nums = [1, 2, 3]
target = 4

所有可能的组合为：
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

请注意，顺序不同的序列被视作不同的组合。

因此输出为 7。
进阶：
如果给定的数组中含有负数会怎么样？
问题会产生什么变化？
我们需要在题目中添加什么限制来允许负数的出现？



来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combination-sum-iv
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M377_组合总和4 {

    @Test
    public void testCase() {
        int[] nums = new int[]{1, 2, 3};
        int target = 4;
        int excepted = 7;
        Assert.assertEquals(excepted, combinationSum4(nums, target));

    }

    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }

        // dp[i]: 使用nums 凑成和为 i 的组合数
        int[] dp = new int[target + 1];

        // 即:啥都不用。
        dp[0] = 1;

        // 数组元素 顺序不同，视为不同的组合
        for (int j = 1; j <= target; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j-nums[i] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j] + dp[j-nums[i]]);
                }
            }
        }

        return dp[target];
    }

}
