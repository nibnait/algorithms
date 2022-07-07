package algorithm_practice.algorithmzuo.b_体系学习班.class04;

import common.CommonConstants;
import common.util.SysOut;
import org.junit.Test;

/**
 * Created by nibnait on 2022/07/21
 */
public class Code03_ReversePair {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int arr[] = new int[]{1,3,4,2,5};
        int smallSum = mergeSort(arr);
        SysOut.println(smallSum);
        SysOut.printArray(arr);
    }

    private int mergeSort(int[] arr) {



        return 0;
    }


}
