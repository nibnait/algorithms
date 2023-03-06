package cc.tianbin.algorithm_practice.LeetCode.code300;

import org.junit.Assert;
import org.junit.Test;

/*
给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

说明:
最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。

示例:

输入: nums = [-2,5,-1], lower = -2, upper = 2,
输出: 3
解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/count-of-range-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/7
 */
public class H327_区间和的个数 {

    @Test
    public void testCase() {
        int[] nums = new int[]{-2, 5, -1};
        int lower = -2;
        int upper = 2;
        int excepted = 3;
        Assert.assertEquals(excepted, countRangeSum(nums, lower, upper));

        nums = new int[]{0, 0};
        lower = 0;
        upper = 0;
        excepted = 3;
        Assert.assertEquals(excepted, countRangeSum(nums, lower, upper));

        nums = new int[]{-2147483647, 0, -2147483647, 2147483647};
        lower = -564;
        upper = 3864;
        excepted = 3;
        Assert.assertEquals(excepted, countRangeSum(nums, lower, upper));

    }

    /**
     * 动态规划 空间优化
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return nums[0] >= lower || nums[0] <= upper ? 1 : 0;
        }

        long[] dp = new long[nums.length];
        int res = 0;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    dp[i] = nums[i];
                    if (dp[i] >= lower && dp[i] <= upper) {
                        res++;
                    }
                }
            }
        }

        for (int i = length - 2; i >= 0; i--) {
            long dp_i_1 = dp[i];
            for (int j = i + 1; j < length; j++) {
                dp_i_1 += nums[j];
                if (dp_i_1 >= lower && dp_i_1 <= upper) {
                    res++;
                }
            }
        }

        return res;
    }

    /**
     * V1 动态规划，超出内存限制。。
     */
    public int countRangeSumV1(int[] nums, int lower, int upper) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return nums[0] >= lower || nums[0] <= upper ? 1 : 0;
        }

        long[][] dp = new long[length][length];

        int res = 0;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    dp[i][j] = nums[i];
                    if (dp[i][j] >= lower && dp[i][j] <= upper) {
                        res++;
                    }
                }
            }
        }

        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = dp[i][j - 1] + nums[j];
                if (dp[i][j] >= lower && dp[i][j] <= upper) {
                    res++;
                }
            }
        }

        return res;
    }
}
