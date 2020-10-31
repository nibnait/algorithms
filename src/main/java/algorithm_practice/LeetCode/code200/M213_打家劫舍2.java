package algorithm_practice.LeetCode.code200;

import org.junit.Assert;
import org.junit.Test;

/*
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。

给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。

 

示例 1：

输入：nums = [2,3,2]
输出：3
解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
示例 2：

输入：nums = [1,2,3,1]
输出：4
解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     偷窃到的最高金额 = 1 + 3 = 4 。
示例 3：

输入：nums = [0]
输出：0
 

提示：

1 <= nums.length <= 100
0 <= nums[i] <= 1000


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/house-robber-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M213_打家劫舍2 {
    @Test
    public void testCase() {
        int[] nums = new int[]{1, 2, 3, 1};
        int excepted = 4;
        Assert.assertEquals(excepted, rob(nums));

        nums = new int[]{2, 3, 2};
        excepted = 3;
        Assert.assertEquals(excepted, rob(nums));

        nums = new int[]{0};
        excepted = 0;
        Assert.assertEquals(excepted, rob(nums));

        nums = new int[]{1, 1};
        excepted = 1;
        Assert.assertEquals(excepted, rob(nums));

        nums = new int[]{200, 3, 140, 20, 10};
        excepted = 340;
        Assert.assertEquals(excepted, rob(nums));

        nums = new int[]{1, 3, 1, 3, 100};
        excepted = 103;
        Assert.assertEquals(excepted, rob(nums));

    }

    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        if (length == 2) {
            return Math.max(nums[0],nums[1]);
        }

        return Math.max(rob(nums, 0, length - 2),
                rob(nums, 1, length - 1));
    }

    private int rob(int[] nums, int begin, int end) {
        int[] dp = new int[nums.length];
        dp[begin] = nums[begin];
        dp[begin+1] = Math.max(nums[begin], nums[begin+1]);

        for (int i = begin+2; i <= end; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[end];
    }

}
