package algorithm_practice.LeetCode.code400;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。

nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。

 

示例 1:

输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
输出: [-1,3,-1]
解释:
    对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
    对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
    对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
示例 2:

输入: nums1 = [2,4], nums2 = [1,2,3,4].
输出: [3,-1]
解释:
    对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 

提示：

nums1和nums2中所有元素是唯一的。
nums1和nums2 的数组大小都不超过1000。


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/next-greater-element-i
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class E496_下一个更大元素1 {

    @Test
    public void testCase() {
        int[] nums1 = new int[]{4,1,2};
        int[] nums2 = new int[]{1,3,4,2};
        int[] excepted = new int[]{-1,3,-1};
        Assert.assertArrayEquals(excepted, nextGreaterElement(nums1, nums2));

        nums1 = new int[]{2,4};
        nums2 = new int[]{1,2,3,4};
        excepted = new int[]{3,-1};
        Assert.assertArrayEquals(excepted, nextGreaterElement(nums1, nums2));

        nums1 = new int[]{1,3,5,2,4};
        nums2 = new int[]{6,5,4,3,2,1,7};
        excepted = new int[]{7,7,7,7,7};
        Assert.assertArrayEquals(excepted, nextGreaterElement(nums1, nums2));

    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        Map<Integer, Integer> nums2IndexMap = new HashMap<>(nums2.length);
        for (int i = 0; i < nums2.length; i++) {
            nums2IndexMap.put(nums2[i], i);
        }

        // 单调栈核心代码 begin
        int[] res = new int[nums2.length];      // nums2 每个数字的下一个最大数
        Stack<Integer> stack = new Stack<>();
        for (int i = nums2.length-1; i >= 0; i--) {

            while (!stack.isEmpty() && stack.peek()<=nums2[i]) {
                stack.pop();
            }

            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums2[i]);
        }

        // 单调栈核心代码 end

        int[] nums1Res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            Integer index = nums2IndexMap.get(nums1[i]);
            nums1Res[i] = res[index];
        }
        return nums1Res;
    }

}
