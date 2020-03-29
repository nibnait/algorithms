package practice;

import common.util.SysOut;
import junit.framework.TestCase;
import org.junit.Test;

/*

Created by nibnait on 2020-01-26
 */
public class 堆排序 extends TestCase {

    @Test
    public void testCase() {

        // 73 为完全二叉树的根节点
        int[] nums = new int[]{73, 98, 27, 36, 77, 22, 6, 32, 83, 69};
        SysOut.printArray(nums);

        nums = heapSort(nums);
        SysOut.printArray(nums);

    }

    private int[] heapSort(int[] nums) {
        int lastIndex = nums.length - 1;

        for (int i = lastIndex / 2; i >= 0; i--) {
            sink(nums, i, lastIndex);
        }


        while (lastIndex > 0) {
            swap(nums, 0, lastIndex);

            lastIndex--;

            sink(nums, 0, lastIndex);
        }

        return nums;
    }

    private void sink(int[] nums, int rootIndex, int lastIndex) {
        while (rootIndex * 2 < lastIndex) {
            int j = rootIndex * 2 + 1;

            if (j + 1 <= lastIndex && nums[j] < nums[j + 1]) {
                j++;
            }

            if (nums[rootIndex] < nums[j]) {
                swap(nums, rootIndex, j);
                rootIndex = j;
            } else {
                break;
            }

        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
