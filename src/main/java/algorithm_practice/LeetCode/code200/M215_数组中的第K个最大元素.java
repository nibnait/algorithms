package algorithm_practice.LeetCode.code200;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

/*
未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
说明:

你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。



来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
Created by nibnait on 2020-01-12
 */
public class M215_数组中的第K个最大元素 extends TestCase {

    @Test
    public void testCase() {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int k = 4;
        System.out.println(findKthLargest(nums, k));
    }

    /**
     * 徒手撸个堆排序吧：
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }


}
