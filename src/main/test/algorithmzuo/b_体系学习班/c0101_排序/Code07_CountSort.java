package algorithmzuo.b_体系学习班.c0101_排序;

import common.CommonConstants;
import common.util.CompareUtils;
import common.util.SysRandom;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by nibnait on 2022/10/23
 */
@Slf4j
public class Code07_CountSort {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        // 全是自然数，范围 [0-100)
        int[] arr = SysRandom.generateArrNaturalNum();
        int[] copyArr = Arrays.copyOf(arr, arr.length);
        countSort(arr);

        if (!CompareUtils.isSortAsc(arr)) {
            log.error("原数组: {}", copyArr);
            log.error("排序后: {}", arr);
        }
    }

    /**
     * 比较适用于 年龄排序
     * （待排序的数组的范围是有限的）
     * 准备若干个桶就好了。遍历到的数字是几，就放到几号桶里
     */
    private void countSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }

        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }

}
