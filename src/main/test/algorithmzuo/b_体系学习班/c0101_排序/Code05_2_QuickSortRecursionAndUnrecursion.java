package algorithmzuo.b_体系学习班.c0101_排序;

import common.CommonConstants;
import common.util.CompareUtils;
import common.util.SwapUtil;
import common.util.SysRandom;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by nibnait on 2022/07/22
 */
@Slf4j
public class Code05_2_QuickSortRecursionAndUnrecursion {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int[] arr = SysRandom.generateArrNaturalNum(10);
        int[] copyArr = Arrays.copyOf(arr, arr.length);
        quickSortWithStack(arr);

        if (!CompareUtils.isSortAsc(arr)) {
            log.error("原数组: {}", copyArr);
            log.error("排序后: {}", arr);
            throw new RuntimeException();
        }
    }

    /**
     * 手写快排3.0
     */
    public void quickSortRecursion(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        SwapUtil.swap(arr, right, SysRandom.generateNaturalNum(right - left + 1));
        int[] equalArea = partition(arr, left, right);
        process(arr, left, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, right);
    }

    private int[] partition(int[] arr, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }
        int less = left - 1;
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
     * 快排3.0 非递归
     * 用栈实现
     */
    public void quickSortWithStack(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        int length = arr.length;
        Stack<SortArea> stack = new Stack<>();

        SwapUtil.swap(arr, length - 1, SysRandom.generateNaturalNum(length));
        int[] partition = partition(arr, 0, length - 1);
        stack.push(new SortArea(0, partition[0] - 1));
        stack.push(new SortArea(partition[1] + 1, length - 1));
        while (!stack.isEmpty()) {
            SortArea sortArea = stack.pop();
            if (sortArea.left < sortArea.right) {
                SwapUtil.swap(arr, sortArea.right, sortArea.left + SysRandom.generateNaturalNum(sortArea.right - sortArea.left + 1));
                partition = partition(arr, sortArea.left, sortArea.right);
                stack.push(new SortArea(sortArea.left, partition[0] - 1));
                stack.push(new SortArea(partition[1] + 1, sortArea.right));
            }
        }

    }

    private static class SortArea {
        public int left;
        public int right;

        public SortArea(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

}
