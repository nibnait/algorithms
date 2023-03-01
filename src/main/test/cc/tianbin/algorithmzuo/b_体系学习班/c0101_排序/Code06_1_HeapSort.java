package cc.tianbin.algorithmzuo.b_体系学习班.c0101_排序;

import cc.tianbin.common.CommonConstants;
import cc.tianbin.common.util.CompareUtils;
import cc.tianbin.common.util.SwapUtil;
import cc.tianbin.common.util.SysRandom;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by nibnait on 2022/10/22
 */
@Slf4j
public class Code06_1_HeapSort {

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
        heapSort(arr);

        if (!CompareUtils.isSortAsc(arr)) {
            log.error("原数组: {}", copyArr);
            log.error("排序后: {}", arr);
            throw new RuntimeException();
        }
    }

    private void heapSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }

        // heapInsert 构建一个堆。时间复杂度 O(N*logN)
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }

        // heapify 构建一个堆。时间复杂度 O(N)
        for (int i = arr.length / 2; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        int heapSize = arr.length;
        SwapUtil.swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            SwapUtil.swap(arr, 0, --heapSize);
        }
    }

    private void heapify(int[] arr, int curIndex, int heapSize) {
        int leftChildIndex = curIndex * 2 + 1;
        while (leftChildIndex < heapSize) {
            int largerChildIndex = leftChildIndex + 1 < heapSize && arr[leftChildIndex + 1] > arr[leftChildIndex] ? leftChildIndex + 1 : leftChildIndex;
            if (arr[largerChildIndex] < arr[curIndex]) {
                break;
            }

            SwapUtil.swap(arr, curIndex, largerChildIndex);
            curIndex = largerChildIndex;
            leftChildIndex = curIndex * 2 + 1;
        }
    }

    private void heapInsert(int[] arr, int curIndex) {
        int fatherIndex = (curIndex - 1) / 2;
        while (arr[fatherIndex] < arr[curIndex]) {
            SwapUtil.swap(arr, curIndex, fatherIndex);
            curIndex = fatherIndex;
            fatherIndex = (curIndex - 1) / 2;
        }
    }

}
