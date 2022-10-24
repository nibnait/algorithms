package algorithmzuo.b_体系学习班.c0101_排序;

import common.CommonConstants;
import common.util.CompareUtils;
import common.util.SwapUtil;
import common.util.SysRandom;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by nibnait on 2022/07/22
 */
@Slf4j
public class Code05_1_PartitionAndQuickSort {

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
        quickSort3(arr);

        if (!CompareUtils.isSortAsc(arr)) {
            log.error("原数组: {}", copyArr);
            log.error("排序后: {}", arr);
            throw new RuntimeException();
        }
    }

    /**
     * 快排1.0
     * 即：荷兰国旗1.0
     * arr[L...R], 以 arr[R] 为标准将数组划分成
     * <= arr[R] || > arr[R] 两部分
     */
    protected void quickSort1(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }

        process1(arr, 0, arr.length - 1);
    }

    private void process1(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = netherlandsFlag1(arr, left, right);
        process1(arr, left, mid - 1);
        process1(arr, mid + 1, right);
    }

    // 返回 <= 区，的右边界
    private int netherlandsFlag1(int[] arr, int left, int right) {
        if (left > right) {
            return -1;
        }
        if (left == right) {
            return left;
        }
        int lessEqual = left - 1;
        int index = left;
        while (index < right) {
            if (arr[index] <= arr[right]) {
                SwapUtil.swap(arr, index++, ++lessEqual);
            } else {
                index++;
            }
        }
        SwapUtil.swap(arr, ++lessEqual, right);
        return lessEqual;
    }


    /**
     * 快排2.0
     * 即：荷兰国旗问题2.0
     * arr[L...R], 以 arr[R] 为标准将数组划分为
     * < arr[R] || = arr[R] || > arr[R]  三部分
     */
    protected void quickSort2(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    private void process2(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] equalArea = netherlandsFlag2(arr, left, right);
        process2(arr, left, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, right);
    }

    // 返回 =区 的左边界+右边界
    private int[] netherlandsFlag2(int[] arr, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }

        // < 区的右边界
        int less = left - 1;
        // > 区的左边界
        int more = right;
        int index = left;
        while (index < more) {
            if (arr[index] < arr[right]) {
                SwapUtil.swap(arr, index++, ++less);
            } else if (arr[index] == arr[right]) {
                index++;
            } else {
                SwapUtil.swap(arr, index, --more);
            }
        }

        SwapUtil.swap(arr, more, right);
        return new int[]{less + 1, more};
    }

    /**
     * 快排3.0
     * 在快排2.0的基础上，随机选一个数组中的数，作为标准值。（不再指定 arr[R]）
     */
    protected void quickSort3(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    private void process3(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        SwapUtil.swap(arr, left + (int) (Math.random() * (right - left + 1)), right);
        int[] equalArea = netherlandsFlag2(arr, left, right);
        process3(arr, left, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, right);
    }
}
