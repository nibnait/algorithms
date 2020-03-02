package algorithm_practice.LeetCode.code100;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashSet;

/*
给定一个未排序的整数数组，找出最长连续序列的长度。

要求算法的时间复杂度为 O(n)。

示例:

输入: [100, 4, 200, 1, 3, 2]
输出: 4
解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
Created by nibnait on 2020-01-26
 */
public class H128_最长连续序列 extends TestCase {

    @Test
    public void testCase() {
        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums));
    }

    /*
    1. 放到HashSet中
    2. for循环数组，
        判断x-1是否存在
            存在，跳过
            不存在，x有可能为当前"连续递增序列"的最小值，开始找x+1是否存在
     */
    public int longestConsecutive(int[] nums) {
        int result = 0;
        HashSet<Integer> hashSet = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            hashSet.add(nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (!hashSet.contains(nums[i] - 1)) {
                int count = 1;
                while (hashSet.contains(x + count)) {
                    count++;
                }
                result = Math.max(result, count);
            }
        }

        return result;
    }

}
