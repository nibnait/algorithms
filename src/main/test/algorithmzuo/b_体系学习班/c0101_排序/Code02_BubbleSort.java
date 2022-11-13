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
public class Code02_BubbleSort {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int[] arr = SysRandom.generateArr();
        int[] copyArr = Arrays.copyOf(arr, arr.length);
        bubbleSort(arr);

        if (!CompareUtils.isSortAsc(arr)) {
            log.error("原数组: {}", copyArr);
            log.error("排序后: {}", arr);
            throw new RuntimeException();
        }
    }

    /**
     * 从小到大排
     * arr[i] arr[i+1] 两两比较，每一轮，都比较出一个最大值，放到最后
     */
    private void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }

        for (int end = arr.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    SwapUtil.swap(arr, i, i + 1);
                }
            }
        }
    }

}
