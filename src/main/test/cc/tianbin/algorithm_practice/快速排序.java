package algorithm_practice;

import cc.tianbin.common.util.SysOut;
import org.junit.Test;

import static cc.tianbin.common.util.SwapUtil.swap;

/**
 * Created by nibnait on 2020/11/23
 */
public class 快速排序 {
    @Test
    public void testCase() {

        // 73 为完全二叉树的根节点
        int[] nums = new int[]{73, 98, 27, 36, 77, 22, 6, 32, 83, 69};
        SysOut.printArray(nums);

        quickSort(nums, 0, nums.length-1);
        SysOut.printArray(nums);

    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int p = partition(nums, left, right);
        quickSort(nums, left, p-1);
        quickSort(nums, p+1, right);
    }


    private int partition(int[] nums, int left, int right) {

        int flag = left;
        for (int i = left; i <= right; i++) {
            // 降序
            if (nums[i] > nums[right]) {
                swap(nums, i, flag);
                flag++;
            }
        }
        swap(nums, right, flag);
        return flag;
    }


}
