package algorithm_practice.algorithmzuo.b_体系学习班.class04;

import common.CommonConstants;
import common.util.SysOut;
import org.junit.Test;

/**
 * Created by nibnait on 2022/07/18
 */
public class Code02_SmallSum {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int arr[] = new int[]{1,3,4,2,5};
        int smallSum = mergeSort(arr);
        SysOut.println(smallSum);
        SysOut.printArray(arr);
    }

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


        int mid = (left + right) / 2;
        return process(arr, left, mid)
                + process(arr, mid + 1, right)
                + merge(arr, left, mid, right);
    }

    private int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[arr.length];
        for (int i = left; i <= right; i++) {
            help[i] = arr[i];
        }

        int p1 = left;
        int p2 = mid + 1;
        int index = left;
        int smallSum = 0;
        while (p1 <= mid && p2 <= right) {
            if (help[p1] < help[p2]) {
                smallSum += (right - p2 + 1) * arr[p1];
                arr[index++] = help[p1++];
            } else {
                arr[index++] = help[p2++];
            }
        }

        while (p1 <= mid) {
            arr[index++] = help[p1++];
        }

        while (p2 <= right) {
            arr[index++] = help[p2++];
        }

        return smallSum;
    }

}
