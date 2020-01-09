package algorithm_practice.LeetCode.code600;

import junit.framework.TestCase;
import org.junit.Test;

/*
给定一个未经排序的整数数组，找到最长且连续的的递增序列。

示例 1:

输入: [1,3,5,4,7]
输出: 3
解释: 最长连续递增序列是 [1,3,5], 长度为3。
尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
示例 2:

输入: [2,2,2,2,2]
输出: 1
解释: 最长连续递增序列是 [2], 长度为1。
注意：数组长度不会超过10000。



来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
Created by nibnait on 2020-01-12
 */
public class E674_最长连续递增序列 extends TestCase {

    @Test
    public void testCase() {
        int[] nums1 = new int[]{1, 3, 5, 4, 7};
        System.out.println(findLengthOfLCIS(nums1));

        int[] nums2 = new int[]{2, 2, 2, 2, 2};
        System.out.println(findLengthOfLCIS(nums2));

        int[] nums3 = new int[]{2, 4, 3, 5, 6, 7, 4, 6, 7, 8, 9, 3, 4};
        System.out.println(findLengthOfLCIS(nums3));

        int[] nums4 = new int[]{1, 3, 5, 7};
        System.out.println(findLengthOfLCIS(nums4));

    }

    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }

        int result = 0;
        int p1Start = 0, p1 = 0;
        int p2 = p1 + 1;
        while (p2 < nums.length) {
            if (nums[p1++] >= nums[p2++]) {
                result = Math.max(result, p1 - p1Start);
                p1Start = p1;
                p2 = p1 + 1;
            } else {
                result = Math.max(result, p2 - p1Start);
            }
        }
        return result;
    }

}
