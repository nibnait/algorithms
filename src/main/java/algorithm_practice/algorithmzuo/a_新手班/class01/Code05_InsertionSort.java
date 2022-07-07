package algorithm_practice.algorithmzuo.a_新手班.class01;

import common.CommonConstants;
import common.util.CompareUtils;
import common.util.SysOut;
import common.util.SysRandom;
import common.util.SystemUtil;
import org.junit.Assert;
import org.junit.Test;

import static common.util.SwapUtil.swap;

/**
 * Created by nibnait on 2022/06/21
 */
public class Code05_InsertionSort {

    @Test
    public void testCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT; i++) {
            int arr[] = SysRandom.randomArr();
            SysOut.printArray(arr);

            insertSort(arr);
            SysOut.printArray(arr);
            if (!CompareUtils.isSortAsc(arr)) {
                SysOut.println("。。。有问题❗️❗️❗️");
                Assert.assertTrue(false);
            }

            SystemUtil.printLiteCuttingLine();
        }
    }

    private void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int length = arr.length;
        for (int end = 1; end < length; end++) {
            int newNumIndex = end;
            while (newNumIndex - 1 >= 0 && arr[newNumIndex - 1] > arr[newNumIndex]) {
                swap(arr, newNumIndex - 1, newNumIndex);
                newNumIndex--;
            }
        }
    }

}
