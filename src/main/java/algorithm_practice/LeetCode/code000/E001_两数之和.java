package algorithm_practice.LeetCode.code000;

import com.alibaba.fastjson.JSON;

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
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,4};
        int target = 6;
        System.out.println(JSON.toJSONString(twoSum_v1(nums, target)));
        System.out.println(JSON.toJSONString(twoSum_v2(nums, target)));
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

    /**
     * 简单又暴力
     *      时间复杂度：O(n^2)
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSum_v1(int[] nums, int target) {
        int[] result = new int[]{};
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    result = new int[]{i, j};
                }
            }
        }
        return result;
    }
}
