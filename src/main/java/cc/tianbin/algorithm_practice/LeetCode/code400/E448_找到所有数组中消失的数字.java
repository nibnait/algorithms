package cc.tianbin.algorithm_practice.LeetCode.code400;

import cc.tianbin.common.util.AssertUtils;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 * 示例 1：
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 *
 * 示例 2：
 * 输入：nums = [1,1]
 * 输出：[2]
 *
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
 *
 * https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/
 * Created by nibnait on 2023/03/25
 */
public class E448_找到所有数组中消失的数字 {

    @Test
    public void test() {
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> result = findDisappearedNumbers(nums);
        List<Integer> expect = Lists.newArrayList(5, 6);
        AssertUtils.compareIntegerList(expect, result);

        nums = new int[]{1, 1};
        result = findDisappearedNumbers(nums);
        expect = Lists.newArrayList(2);
        AssertUtils.compareIntegerList(expect, result);

        nums = new int[]{5, 4, 6, 7, 9, 3, 10, 9, 5, 6};
        result = findDisappearedNumbers(nums);
        expect = Lists.newArrayList(1, 2, 8);
        AssertUtils.compareIntegerList(expect, result);
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i]) {
                send(nums, i);
            }
        }

        List<Integer> ans = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i]) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    /**
     * 把 num[curIndex] 发生到 num[curIndex] - 1 的位置上去
     */
    private void send(int[] nums, int curIndex) {
        while (nums[curIndex] != curIndex + 1) {
            int nextIndex = nums[curIndex] - 1;
            if (nextIndex + 1 == nums[nextIndex]) {
                break;
            }
            swap(nums, curIndex, nextIndex);
        }
    }

    private void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
