package algorithmzuo.b_体系学习班.c0101_排序;

import common.CommonConstants;
import common.util.CompareUtils;
import common.util.SwapUtil;
import common.util.SysRandom;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by nibnait on 2022/06/21
 */
@Slf4j
public class Code03_InsertionSort {

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
        insertSort(arr);

        if (!CompareUtils.isSortAsc(arr)) {
            log.error("原数组: {}", copyArr);
            log.error("排序后: {}", arr);
            throw new RuntimeException();
        }
    }

    /**
     * 从小到大排（打扑克 抓牌、码牌）
     * i=1, j=i，每一轮比较，都把arr[i] 安排到一个正确的位置
     * if(arr[j] < arr[j-1]) swap;
     */
    private void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j - 1 >= 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    SwapUtil.swap(arr, j, j - 1);
                }
            }
        }
    }

}
