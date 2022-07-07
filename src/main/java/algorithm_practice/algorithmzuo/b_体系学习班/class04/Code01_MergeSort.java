package algorithm_practice.algorithmzuo.b_体系学习班.class04;

import common.CommonConstants;
import common.util.CompareUtils;
import common.util.SysOut;
import common.util.SysRandom;
import common.util.SystemUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by nibnait on 2022/07/07
 */
public class Code01_MergeSort {

    @Test
    public void testCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            int arr[] = SysRandom.randomNaturalNumArr();
//            int arr[] = SysRandom.randomArr();
            SysOut.printArray(arr);
//            recursiveMergeSort(arr);
            mergeSort(arr);
            SysOut.printArray(arr);
            if (!CompareUtils.isSortAsc(arr)) {
                SysOut.println("。。。有问题❗️❗️❗️");
                Assert.assertTrue(false);
            }

            SystemUtil.printLiteCuttingLine();
        }
    }

    /**
     * 归并排序 非递归
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

                merge(arr, left, mid, right);
                left = right + 1;
            }

            // 防止 mergeSize * 2 之后，导致 Integer 溢出
            if (mergeSize > length / 2) {
                break;
            }
            mergeSize <<= 1;
        }

    }

    /**
     * 归并排序 递归版
     */
    private void recursiveMergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process(arr, 0, arr.length - 1);
    }

    private void process(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }

        int mid = (left + right) / 2;
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int p1 = left;
        int p2 = mid + 1;

        int[] help = new int[arr.length];
        for (int i = left; i <= right; i++) {
            help[i] = arr[i];
        }

        int index = left;
        while (p1 <= mid && p2 <= right) {
            arr[index++] = help[p1] < help[p2] ? help[p1++] : help[p2++];
        }

        while (p1 <= mid) {
            arr[index++] = help[p1++];
        }

        while (p2 <= right) {
            arr[index++] = help[p2++];
        }
    }

}
