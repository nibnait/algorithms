package cc.tianbin.algorithm_practice.LeetCode.code500;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
 * <p>
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * <p>
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * <p>
 * <p>
 * <p>
 * Created by nibnait on 2023/03/15
 */
public class M560_和为K的子数组 {

    @Test
    public void test() {
        int[] nums = new int[]{1, 1, 1};
        int k = 2;
        int result = subarraySum(nums, k);
        Assert.assertEquals(2, result);

        nums = new int[]{1, 2, 3};
        k = 3;
        result = subarraySum(nums, k);
        Assert.assertEquals(2, result);

        nums = new int[]{1, 0, 1, 1};
        k = 2;
        result = subarraySum(nums, k);
        Assert.assertEquals(3, result);

        nums = new int[]{1, 0, 1, 0, 1};
        k = 2;
        result = subarraySum(nums, k);
        Assert.assertEquals(4, result);

        nums = new int[]{1, 0, 1, 0, 1, -1};
        k = 2;
        result = subarraySum(nums, k);
        Assert.assertEquals(5, result);

    }

    public int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> preSumCountMap = new HashMap<>();
        preSumCountMap.put(0, 1);

        int all = 0;
        int ans = 0;
        for (int i = 0; i< nums.length; i++) {
            all += nums[i];

            if (preSumCountMap.containsKey(all - k)) {
                ans += preSumCountMap.get(all - k);
            }

            if (preSumCountMap.containsKey(all)) {
                int times = preSumCountMap.get(all);
                preSumCountMap.put(all, times+1);
            } else {
                preSumCountMap.put(all, 1);
            }
        }

        return ans;
    }
}
