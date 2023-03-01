package cc.tianbin.algorithmzuo.b_体系学习班.c0103_异或运算;

import cc.tianbin.common.CommonConstants;
import cc.tianbin.common.util.SysOut;
import cc.tianbin.common.util.SysRandom;
import io.github.nibnait.common.utils.DataUtils;
import org.junit.Test;

import static cc.tianbin.common.util.SwapUtil.swap;

/**
 * Created by nibnait on 2022/06/23
 */
public class Code03_KM {

    /**
     * 一个数组中有一种数出现K次，其他数都出现了M次，
     * 已知M > 1，K < M，找到出现了K次的数
     * 要求额外空间复杂度O(1)，时间复杂度O(N)
     */
    @Test
    public void testCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_10; i++) {
            int a = SysRandom.generateNaturalNum(9) + 1;
            int b = SysRandom.generateNaturalNum(9) + 1;
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }

            int mTimeNumCount = SysRandom.generateNaturalNum(9);
            int arrLength = k + m * mTimeNumCount;
            int[] arr = new int[arrLength];

            // 填充出现了 k 次的数
            int kTimesNum = SysRandom.generateInt(-200, 200);
            for (int j = 0; j < k; j++) {
                arr[j] = kTimesNum;
            }
            // 填充出现了 m 次的数
            int index = k;
            int mTimesNum = SysRandom.generateInt(-200, 200);
            for (int l = 0; l < m; l++) {
                for (int n = 0; n < mTimeNumCount; n++) {
                    arr[index++] = mTimesNum;
                }
            }

            // j 位置的数和 arr里任意位置的的一个数
            for (int j = 0; j < arrLength; j++) {
                int randomIndex = SysRandom.generateNaturalNum(arrLength);
                swap(arr, j, randomIndex);
            }

            int ans = onlyKTimes(arr, k, m);
            if (ans != kTimesNum) {
                SysOut.println("%s, %s, %s \n kTimesNum=%s, ans=%s",
                        "new int[]" + DataUtils.toJsonStringArray(arr).replace("[", "{").replace("]", "}"), k, m,
                        kTimesNum, ans);
            }
        }
    }

    @Test
    public void testSpecialCase() {
        int ans = onlyKTimes(
                new int[]{1, 1, 2, 3, 2}, 1, 2
        );

        SysOut.println(ans);
    }

    // 请保证arr中，只有一种数出现了K次，其他数都出现了M次
    private int onlyKTimes(int[] arr, int k, int m) {

        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                if (((num >> i) & 1) == 1) {
                    // num 在 i 位置上是1，则 t[i] ++
                    t[i]++;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (t[i] % m != 0) {
                // ans 的第i位置上有1
                ans |= (1 << i);
            }
        }

        return ans;
    }
}
