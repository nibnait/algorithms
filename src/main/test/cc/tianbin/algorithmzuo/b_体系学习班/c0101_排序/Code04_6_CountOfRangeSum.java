package cc.tianbin.algorithmzuo.b_体系学习班.c0101_排序;

import io.github.nibnait.common.utils.compare.CompareUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by nibnait on 2022/07/21
 */
@Slf4j
public class Code04_6_CountOfRangeSum {

    /**
     * https://leetcode.cn/problems/count-of-range-sum/
     */
    @Test
    public void testCase() {
        int[] arr = new int[]{-2, 5, -1};
        int lower = -2;
        int upper = 2;
        int result = countRangeSum(arr, lower, upper);
        int expect = 3;
        if (!CompareUtils.matchObject(result, expect)) {
            log.error("arr: {}\n actual: {}, expect: {}", arr, result, expect);
            throw new RuntimeException();
        }

        arr = new int[]{0};
        lower = 0;
        upper = 0;
        result = countRangeSum(arr, lower, upper);
        expect = 1;
        if (!CompareUtils.matchObject(result, expect)) {
            log.error("arr: {}\n actual: {}, expect: {}", arr, result, expect);
            throw new RuntimeException();
        }

        arr = new int[]{0, 0};
        lower = 0;
        upper = 0;
        result = countRangeSum(arr, lower, upper);
        expect = 3;
        if (!CompareUtils.matchObject(result, expect)) {
            log.error("arr: {}\n actual: {}, expect: {}", arr, result, expect);
            throw new RuntimeException();
        }
    }

    /**
     * 这道题 有点绕
     * 要想求 nums[] 有多少个区间的和，在 [lower, upper]上。
     * 1. 先搞一个前缀和数组 sum[]
     * sum[i] 代表: nums[0] ~ nums[i] 这个区间的和
     * sum[i] - sum[j] 代表: nums[j] ~ nums[i] 这个区间的和
     * 2. 借用归并排序，sum[left, mid], sum[mid+1, right] 的各自范围上都是有序的
     * 题目想求 以i位置结尾的子数组和在 [lower, upper] 范围上，
     * 即在每次 merge 时，求有多少个前缀和在 [sum[i] - upper, sum[i] - lower) 上
     *
     */
    private int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        return process(sum, 0, sum.length - 1, lower, upper);
    }

    private int process(long[] sum, int left, int right, int lower, int upper) {
        if (left == right) {
            return sum[left] >= lower && sum[left] <= upper ? 1 : 0;
        }
        int mid = left + ((right - left) >> 1);
        return process(sum, left, mid, lower, upper)
                + process(sum, mid + 1, right, lower, upper)
                + merge(sum, left, mid, right, lower, upper);
    }

    private int merge(long[] sum, int left, int mid, int right, int lower, int upper) {
        int p1 = left;
        int p2 = left;
        int res = 0;
        // [p1, p2)
        for (int i = mid + 1; i <= right; i++) {
            long minValue = sum[i] - upper;
            long maxValue = sum[i] - lower;
            while (p1 <= mid && sum[p1] < minValue) {
                p1++;
            }
            while (p2 <= mid && sum[p2] <= maxValue) {
                p2++;
            }
            res += p2 - p1;
        }

        long[] help = new long[right - left + 1];
        int index = 0;
        p1 = left;
        p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            help[index++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }

        while (p1 <= mid) {
            help[index++] = sum[p1++];
        }
        while (p2 <= right) {
            help[index++] = sum[p2++];
        }
        for (int i = 0; i < help.length; i++) {
            sum[left + i] = help[i];
        }

        return res;
    }

}
