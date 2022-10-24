package algorithmzuo.b_体系学习班.c0102_二分查找;

import common.CommonConstants;
import common.util.SysOut;
import common.util.SysRandom;
import io.github.nibnait.common.utils.DataUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by nibnait on 2022/06/21
 */
public class Code03_BSNearRight {

    @Test
    public void testCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            int[] arr = SysRandom.randomArr();
            Arrays.sort(arr);
            int num = 11;
            int ans = nearestIndex(arr, num);
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

    /**
     * 在arr上，找满足<=value的最右位置
     */
    private int nearestIndex(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] <= num) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    /**
     * 暴力查找
     */
    private int violenceBreak(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= num) {
                return i;
            }
        }
        return -1;
    }
}
