package algorithm_practice;

import common.CommonConstants;
import common.util.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by nibnait on 2020/11/24
 */
public class TempTest {

    @Test
    public void testCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            int[] arr = SysRandom.generateArr();
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
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int end = arr.length - 1; end > 0; end--) {
            int maxValueIndex = 0;
            for (int i = 1; i <= end; i++) {
                maxValueIndex = arr[maxValueIndex] > arr[i] ? maxValueIndex : i;
            }
            SwapUtil.swap(arr, maxValueIndex, end);
        }
    }


}
