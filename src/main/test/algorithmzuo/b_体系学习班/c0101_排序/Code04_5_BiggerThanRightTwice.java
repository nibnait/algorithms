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
public class Code04_5_BiggerThanRightTwice {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int arr[] = SysRandom.randomArrNaturalNum();
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
     * 大于两倍问题
     * 在一个数组中，对于任何一个数num，求有多少个(后面的数*2)依然<num，返回总个数
     * 比如：[3,1,7,0,2]
     * 3的后面有：1，0
     * 1的后面有：0
     * 7的后面有：0，2
     * 0的后面没有
     * 2的后面没有
     * 所以总共有5个
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
        int res = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid) {
            while (p2 <= right && (long) arr[p2] * 2 < arr[p1]) {
                p2++;
            }
            res += p2 - (mid + 1);
            p1++;
        }

        int[] help = new int[right - left + 1];
        p1 = left;
        p2 = mid + 1;
        int index = 0;
        while (p1 <= mid && p2 <= right) {
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
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

        return res;
    }
    
    //-------------------------- 比较器 --------------------------//
    private int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
