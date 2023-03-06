package cc.tianbin.algorithmzuo.b_体系学习班.c0101_排序;

import cc.tianbin.common.CommonConstants;
import cc.tianbin.common.util.CompareUtils;
import cc.tianbin.common.util.SysRandom;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by nibnait on 2022/10/23
 */
@Slf4j
public class Code04_2_MergeSort_Non_Recursion {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int[] arr = SysRandom.generateArrNaturalNum();
        int[] copyArr = Arrays.copyOf(arr, arr.length);
        mergeSort(arr);

        if (!CompareUtils.isSortAsc(arr)) {
            log.error("原数组: {}", copyArr);
            log.error("排序后: {}", arr);
            throw new RuntimeException();
        }
    }

    /**
     * 归并排序，非递归版
     */
    private void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int mergeSize = 1;
        int length = arr.length;
        while (mergeSize < length) {

            // 按照步长，开始 merge
            int left = 0;
            while (left < length) {
                if (length - left < mergeSize) {
                    // 此时已经不存在右边了。不需要再merge了
                    break;
                }

                int mid = left + mergeSize - 1;
                int right = mid + Math.min(mergeSize, length - mid - 1);

                Code04_1_MergeSort_Recursion.merge(arr, left, mid, right);
                left = right + 1;
            }

            // 防止 mergeSize * 2 之后，导致 Integer 溢出
            if (mergeSize > length / 2) {
                break;
            }
            mergeSize <<= 1;
        }

    }


}
