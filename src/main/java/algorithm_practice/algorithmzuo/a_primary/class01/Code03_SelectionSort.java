package algorithm_practice.algorithmzuo.a_primary.class01;

import common.util.CompareUtils;
import common.util.SysOut;
import common.util.SysRandom;
import common.util.SystemUtil;
import org.junit.Test;

import static common.util.SwapUtil.swap;

/**
 * Created by nibnait on 2022/06/21
 */
public class Code03_SelectionSort {

    @Test
    public void testCase() {
        for (int i = 0; i < 10; i++) {
            int arr[] = SysRandom.randomArr();
            SysOut.printArray(arr);
            int[] tmp = CompareUtils.copyArray(arr);
            selectSort(arr);
            SysOut.printArray(arr);
            if (!CompareUtils.isSortAsc(arr)) {
                System.out.println("！！！有问题：");
                SysOut.printArray(tmp);
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
