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

        return nums;
    }

}
