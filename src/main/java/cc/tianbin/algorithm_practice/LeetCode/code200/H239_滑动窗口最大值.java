package cc.tianbin.algorithm_practice.LeetCode.code200;

import cc.tianbin.common.util.AssertUtils;
import org.junit.Test;

import java.util.LinkedList;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 *
 *
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 *
 *
 * 提示：
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 *
 * https://leetcode.cn/problems/sliding-window-maximum/?favorite=2cktkvj
 * Created by nibnait on 2023/03/26
 */
public class H239_滑动窗口最大值 {

    @Test
    public void test() {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] result = maxSlidingWindow(nums, k);
        int[] expect = new int[]{3, 3, 5, 5, 6, 7};
        AssertUtils.compareIntegerArray(expect, result);

        nums = new int[]{1};
        k = 1;
        result = maxSlidingWindow(nums, k);
        expect = new int[]{1};
        AssertUtils.compareIntegerArray(expect, result);

        nums = new int[]{1, -1};
        k = 1;
        result = maxSlidingWindow(nums, k);
        expect = new int[]{1, -1};
        AssertUtils.compareIntegerArray(expect, result);

    }

    public int[] maxSlidingWindow(int[] nums, int k) {

        // 双端队列 左->右，存的index对应的数字，从 大->小
        LinkedList<Integer> maxq = new LinkedList<>();

        int[] ans = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!maxq.isEmpty() && nums[maxq.peekLast()] <= nums[i]) {
                maxq.pollLast();
            }

            maxq.addLast(i);
            while (i - maxq.peekFirst() >= k) {
                // 把左边已经在窗口之外的index pop出去
                maxq.pollFirst();
            }
            if (i >= k - 1) {
                // 可以开始写 ans 了
                ans[index++] = nums[maxq.peekFirst()];
            }
        }

        return ans;
    }

}
