package algorithm_practice.LeetCode.code000;

import org.junit.Assert;
import org.junit.Test;

/*
实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须原地修改，只允许使用额外常数空间。

以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1



来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/next-permutation
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/10
 */
public class M031_下一个排列 {

    @Test
    public void testCase() {
        int[] nums = new int[]{1,2,3};
        int[] excepted = new int[]{1,3,2};
        nextPermutation(nums);
        Assert.assertArrayEquals(excepted, nums);

        nums = new int[]{3,2,1};
        excepted = new int[]{1,2,3};
        nextPermutation(nums);
        Assert.assertArrayEquals(excepted, nums);

        nums = new int[]{1,1,5};
        excepted = new int[]{1,5,1};
        nextPermutation(nums);
        Assert.assertArrayEquals(excepted, nums);
    }

    /**
     * 无聊的一道题
     *
     * 多写几个全排列，找规律
     *
     * 1. 从后往前找，先找到第一个顺序对(nums[i], nums[i+1])，满足nums[i] < nums[i+1]，此时nums[i+1] ~ nums[length-1] 必然是一个下降序列
     * 2. 如果找到了顺序对，再在区间 [i+1, n) 中查找第一个元素nums[j]，满足a[i] < a[j]
     *  交换a[i] 与 a[j]，此时区间[i+1, n) 一定是降序
     * 3. reverse(nums, i+1, length-1)，使其变成升序。
     *
     */
    public void nextPermutation(int[] nums) {
        int length = nums.length;
        int i = length - 2;

        while (i >= 0 && nums[i] >= nums[i+1]) {
            i--;
        }

        if (i >= 0) {
            int j = length - 1;
            while (j > 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i+1);
    }

    private void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    private void swap(int[] nums, int p1, int p2) {
        int temp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = temp;
    }
}
