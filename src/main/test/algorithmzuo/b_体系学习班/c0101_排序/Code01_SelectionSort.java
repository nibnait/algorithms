package algorithmzuo.b_体系学习班.c0101_排序;

import common.CommonConstants;
import common.util.CompareUtils;
import common.util.SwapUtil;
import common.util.SysRandom;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by nibnait on 2022/10/23
 */
@Slf4j
public class Code01_SelectionSort {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int arr[] = SysRandom.randomArr();
        int[] copyArr = Arrays.copyOf(arr, arr.length);
        selectSort(arr);

        if (!CompareUtils.isSortAsc(arr)) {
            log.error("原数组: {}", copyArr);
            log.error("排序后: {}", arr);
            throw new RuntimeException();
        }
    }

    /**
     * 从小到大排：
     * i=0,j=i+1，每一轮都比较出一个最大值，放到最前面
     */
    private void selectSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minValueIndex = arr[minValueIndex] < arr[j] ? minValueIndex : j;
            }
            SwapUtil.swap(arr, minValueIndex, i);
        }
    }

}
