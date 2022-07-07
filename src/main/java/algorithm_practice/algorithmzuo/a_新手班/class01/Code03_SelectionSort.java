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
public class Code03_SelectionSort {

    @Test
    public void testCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT; i++) {
            int arr[] = SysRandom.randomArr();
            SysOut.printArray(arr);
            selectSort(arr);
            SysOut.printArray(arr);
            if (!CompareUtils.isSortAsc(arr)) {
                SysOut.println("。。。有问题❗️❗️❗️");
                Assert.assertTrue(false);
            }

            SystemUtil.printLiteCuttingLine();
        }
    }

    private void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            int minValueIndex = i;
            for (int j = i+1; j < length; j++) {
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr, i, minValueIndex);
        }

    }

}
