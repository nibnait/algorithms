package cc.tianbin.algorithm_practice.LeetCode.code000;

import cc.tianbin.common.util.CompareUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*

给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。


示例:
给定 nums = [2, 7, 11, 15], target = 9
因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]

思路：
数组中两数相加，得到target

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/two-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class E001_两数之和 {

    @Test
    public void test() {
        int[] nums = new int[]{3,2,4};
        int target = 6;
        Assert.assertTrue(CompareUtils.compareArrayIgnoreOrder(twoSum_v2(nums, target), twoSum(nums, target)));

        nums = new int[]{2,7,11,15};
        target = 9;
        Assert.assertTrue(CompareUtils.compareArrayIgnoreOrder(twoSum_v2(nums, target), twoSum(nums, target)));

        nums = new int[]{3,3};
        target = 6;

        int[] arr1 = twoSum_v2(nums, target);
        int[] arr2 = twoSum(nums, target);
        Assert.assertTrue(CompareUtils.compareArrayIgnoreOrder(arr1, arr2));

    }

    /**
     * 法3：先排序，[升序]
     *     再让i 从左往右遍历
     *        j  从右往左遍历
     *     i + j > target    j不动，i++
     *     i + j < target    i不动, j--
     *     直到 ==
     *
     *     时间复杂度：O(n^2) + O(n)
     */
    private int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            if (map.get(nums[i]) != null && nums[i] * 2 == target) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(nums[i], i);
        }

        Arrays.sort(nums);

        int p1 = 0;
        int p2 = nums.length - 1;
        while (p1 < p2) {
            int sum = nums[p1] + nums[p2];
            if (sum == target) {
                return new int[]{map.get(nums[p1]), map.get(nums[p2])};
            } else if (sum > target) {
                p2 --;
            } else {
                p1 ++;
            }
        }

        return new int[2];
    }

    /**
     * 使用HashMap，来找 和为target的下一个数字。
     * 此方法只需遍历一次nums数组
     *      时间复杂度：O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSum_v2(int[] nums, int target) {
        int[] result = new int[]{};
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer secondIndex = numMap.get(target - nums[i]);
            if (secondIndex != null) {
                return new int[]{i, secondIndex};
            }
            numMap.put(nums[i], i);
        }

        return result;
    }

}
