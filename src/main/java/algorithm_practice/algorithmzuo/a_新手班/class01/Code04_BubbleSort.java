package algorithm_practice.algorithmzuo.a_新手班.class01;

import common.CommonConstants;
import common.util.CompareUtils;
import common.util.SysOut;
import common.util.SysRandom;
import common.util.SystemUtil;
import org.junit.Test;

import static common.util.SwapUtil.swap;

/**
 * Created by nibnait on 2022/06/21
 */
public class Code04_BubbleSort {

    @Test
    public void testCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT; i++) {
            int arr[] = SysRandom.randomArr();
            SysOut.printArray(arr);
            int[] tmp = CompareUtils.copyArray(arr);
            bubbleSort(arr);
            SysOut.printArray(arr);
            if (!CompareUtils.isSortAsc(arr)) {
                System.out.println("！！！有问题：");
                SysOut.printArray(tmp);
            }

            SystemUtil.printLiteCuttingLine();
        }
    }

    private void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int length = arr.length;
        for (int end = length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }

    }

}
