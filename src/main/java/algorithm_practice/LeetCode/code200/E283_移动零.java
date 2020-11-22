package algorithm_practice.LeetCode.code200;

import common.util.SysOut;
import org.junit.Assert;
import org.junit.Test;

/**
 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

 示例:

 输入: [0,1,0,3,12]
 输出: [1,3,12,0,0]
 说明:

 必须在原数组上操作，不能拷贝额外的数组。
 尽量减少操作次数。


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/move-zeroes
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/19
 */
public class E283_移动零 {

    @Test
    public void testCase() {
        int[] nums = new int[]{0,1,0,3,12};
        int[] excepted = new int[]{1,3,12,0,0};
        moveZeroes(nums);
        SysOut.printArray(nums);
        Assert.assertArrayEquals(excepted, nums);

        nums = new int[]{2,1};
        excepted = new int[]{2,1};
        moveZeroes(nums);
        SysOut.printArray(nums);
        Assert.assertArrayEquals(excepted, nums);

    }

    /**
     * 双指针
     *
     * p1 指向当前已经处理好的序列的尾部
     * p2 指向待处理序列的头部。
     *
     * p2 不断向右移动：每次p2 指向非零数，则将左p2 对应的数交换，同时p1 右移。
     *
     * p1 左边均为非零数；
     * p2 左边直到p1 处均为零。
     *
     * 因此每次交换，都是将p1 的零与p2 的非零数交换，且非零数的相对顺序并未改变。
     */
    public void moveZeroes(int[] nums) {
        int p1 = 0;
        int p2 = 0;

        while (p2 < nums.length) {
            if (nums[p2] != 0) {
                swap(nums, p1, p2);
                p1++;
            }
            p2++;
        }
    }

    private void swap(int[] nums, int i, int p) {
        int temp = nums[i];
        nums[i] = nums[p];
        nums[p] = temp;
    }
}
