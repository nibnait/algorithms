package cc.tianbin.algorithm_practice.LeetCode.code400;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/*
给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

注意:

每个数组中的元素不会超过 100
数组的大小不会超过 200
示例 1:

输入: [1, 5, 11, 5]

输出: true

解释: 数组可以分割成 [1, 5, 5] 和 [11].
 

示例 2:

输入: [1, 2, 3, 5]

输出: false

解释: 数组不能分割成两个元素和相等的子集.


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M416_分割等和子集 extends TestCase {

    @Test
    public void testCase() {
        int[] nums = new int[]{1, 5, 11, 5};
        boolean canPartition = canPartition(nums);
        Assert.assertTrue(canPartition);

        nums = new int[]{1, 2, 3, 5};
        canPartition = canPartition(nums);
        Assert.assertEquals(false, canPartition);

        nums = new int[]{2, 2, 1, 1};
        canPartition = canPartition(nums);
        Assert.assertTrue(canPartition);

        nums = new int[]{2, 2, 3, 5};
        canPartition = canPartition(nums);
        Assert.assertEquals(false, canPartition);

        nums = new int[]{1, 1, 1, 1};
        canPartition = canPartition(nums);
        Assert.assertTrue(canPartition);


    }

    /*
    ==> 给定数组 nums，每个元素只能选1次，问：能否凑成和为 sum / 2
     */

    /**
     * 一维数组
     */
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        if (nums.length == 2) {
            return nums[0] == nums[1];
        }

        int sum = nums[0];
        int maxNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxNum = Math.max(nums[i], maxNum);
            sum += nums[i];
        }

        if (sum % 2 == 1) {
            return false;
        }

        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int j = 1; j <= target; j++) {
            dp[j] = j == nums[0];
        }

        int num;
        for (int i = 1; i < nums.length; i++) {
            num = nums[i];
            for (int j = target; j >= num; j--) {

                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[target];
    }

    public boolean canPartition_二维数据(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        if (nums.length == 2) {
            return nums[0] == nums[1];
        }

        int sum = nums[0];
        int maxNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxNum = Math.max(nums[i], maxNum);
            sum += nums[i];
        }

        if (sum % 2 == 1) {
            return false;
        }

        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }

        boolean[][] dp = new boolean[nums.length][target + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for (int j = 1; j <= target; j++) {
            dp[0][j] = j == nums[0];
        }

        int num;
        for (int i = 1; i < dp.length; i++) {
            num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (dp[i - 1][j] || j == num) {
                    dp[i][j] = true;
                    continue;
                }
                if (j > num) {
                    dp[i][j] = dp[i - 1][j - num];
                }
            }
        }

        return dp[nums.length - 1][target];
    }
}
