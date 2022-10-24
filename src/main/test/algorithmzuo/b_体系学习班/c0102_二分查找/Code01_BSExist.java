package algorithmzuo.b_体系学习班.c0102_二分查找;

import common.CommonConstants;
import common.util.SysOut;
import common.util.SysRandom;
import io.github.nibnait.common.utils.DataUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * 二分查找
 * Created by nibnait on 2022/06/21
 */
public class Code01_BSExist {

    @Test
    public void testCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            int[] arr = SysRandom.randomArr();
            Arrays.sort(arr);
            int num = 11;
            int ans = find(arr, num);
            int violenceAns = violenceBreak(arr, num);
            if (violenceAns != ans) {
                SysOut.println("\n arr: %s \n num: %s \n violenceAns: %s, ans: %s", DataUtils.toJsonStringArray(arr),
                        num,
                        violenceAns, ans);
            } else {
                SysOut.println("OK");
            }
        }

    }

    private int find(int[]arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == num) {
                return mid;
            } else if (arr[mid] > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 暴力查找
     */
    private int violenceBreak(int[]arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return i;
            }
        }
        return -1;
    }
}
