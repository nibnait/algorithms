package algorithmzuo.b_体系学习班.c0101_排序;

import common.CommonConstants;
import common.util.SysRandom;
import io.github.nibnait.common.utils.compare.CompareUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by nibnait on 2022/07/18
 */
@Slf4j
public class Code04_3_SmallSum {

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
        int expectResult = comparator(arr);

        int smallSum = mergeSort(arr);
        if (!CompareUtils.matchObject(smallSum, expectResult)) {
            log.error("arr: {}", copyArr);
            log.error("actual: {}, expect: {}", smallSum, expectResult);
            throw new RuntimeException();
        }
    }

    /**
     * 小和问题
     * 在一个数组中，一个数左边比它小的数的总和，叫该数的小和。所有数的小和累加起来，叫数组小和
     * 例子： [1,3,4,2,5]
     * 1左边比1小的数：没有
     * 3左边比3小的数：1
     * 4左边比4小的数：1、3
     * 2左边比2小的数：1
     * 5左边比5小的数：1、3、4、2
     * 所以数组的小和为1+1+3+1+1+3+4+2=16
     * 给定一个数组arr，求数组小和
     */
    private int mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
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
        int p1 = left;
        int p2 = mid + 1;
        int index = 0;
        int smallSum = 0;
        while (p1 <= mid && p2 <= right) {
            if (arr[p1] < arr[p2]) {
                smallSum += (right - p2 + 1) * arr[p1];
                help[index++] = arr[p1++];
            } else {
                help[index++] = arr[p2++];
            }
        }

        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }
        while (p2 <= right) {
            help[index++] = arr[p2++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }

        return smallSum;
    }

    //-------------------------- 对数器 --------------------------//
    private int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }
}
