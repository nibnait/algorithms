package algorithmzuo.b_体系学习班.c0101_排序;

import common.CommonConstants;
import common.util.SysRandom;
import io.github.nibnait.common.utils.compare.CompareUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by nibnait on 2022/07/21
 */
@Slf4j
public class Code04_4_ReversePair {

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
        int expectResult = comparator(arr);
        int reversePairCount = mergeSort(arr);
        if (!CompareUtils.matchObject(reversePairCount, expectResult)) {
            log.error("arr: {}", copyArr);
            log.error("actual: {}, expect: {}", reversePairCount, expectResult);
            throw new RuntimeException();
        }
    }

    /**
     * 逆序对问题
     * 在一个数组中，任何一个前面的数a，和任何一个后面的数b，如果(a,b)是降序的，就称为降序对
     * 给定一个数组arr，求数组的降序对总数量
     */
    private int mergeSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return 0;
        }

        return process(arr, 0, arr.length - 1);
    }

    private int process(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }

        int mid = left + ((right - left) >> 1);
        return process(arr, left, mid)
                + process(arr, mid + 1, right)
                + merge(arr, left, mid, right);
    }

    private int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int p1 = mid;
        int p2 = right;
        int index = help.length - 1;
        int reversePairCount = 0;
        while (p1 >= left && p2 > mid) {
            if (arr[p1] > arr[p2]) {
                reversePairCount += p2 - mid;
                help[index--] = arr[p1--];
            } else {
                help[index--] = arr[p2--];
            }
        }

        while (p1 >= left) {
            help[index--] = arr[p1--];
        }

        while (p2 > mid) {
            help[index--] = arr[p2--];
        }

        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }

        return reversePairCount;
    }

    //-------------------------- 比较器 --------------------------//
    private int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
