package algorithm_practice.algorithmzuo.b_体系学习班.class02;

import common.CommonConstants;
import common.util.SysOut;
import common.util.SysRandom;
import io.github.nibnait.common.utils.DataUtils;
import org.junit.Test;

import static common.util.SwapUtil.swap;

/**
 * Created by nibnait on 2022/06/23
 */
public class Code03_KM {

    @Test
    public void testCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_MIN; i++) {
            int a = SysRandom.random(9) + 1;
            int b = SysRandom.random(9) + 1;
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }

            int mTimeNumCount = SysRandom.random(9);
            int arrLength = k + m * mTimeNumCount;
            int[] arr = new int[arrLength];

            // 填充出现了 k 次的数
            int kTimesNum = SysRandom.randomInt(-200, 200);
            for (int j = 0; j < k; j++) {
                arr[j] = kTimesNum;
            }
            // 填充出现了 m 次的数
            int index = k;
            int mTimesNum = SysRandom.randomInt(-200, 200);
            for (int l = 0; l < m; l++) {
                for (int n = 0; n < mTimeNumCount; n++) {
                    arr[index++] = mTimesNum;
                }
            }

            // j 位置的数和 arr里任意位置的的一个数
            for (int j = 0; j < arrLength; j++) {
                int randomIndex = SysRandom.random(arrLength);
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
