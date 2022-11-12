package algorithmzuo.b_体系学习班.c0300_贪心;

import common.CommonConstants;
import common.util.SysRandom;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by nibnait on 2022/11/02
 */
public class Code02_LessMoneySplitGold {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int arrLen = SysRandom.random(20);
        int[] arr = SysRandom.randomArrNaturalNum(arrLen, 1000);

        int result = lessMoney(arr);
        Assert.assertEquals(comparator(arr), result);
    }

    /**
     * 花最少的钱 去分割金条
     */
    private int lessMoney(int[] arr) {
        return 0;
    }

    //-------------------------- 比较器 --------------------------//
    private int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }

    // 等待合并的数都在arr里，pre之前的合并行为产生了多少总代价
    // arr中只剩一个数字的时候，停止合并，返回最小的总代价
    private int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
            }
        }
        return ans;
    }

    private int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int ansi = 0;
        for (int arri = 0; arri < arr.length; arri++) {
            if (arri != i && arri != j) {
                ans[ansi++] = arr[arri];
            }
        }
        ans[ansi] = arr[i] + arr[j];
        return ans;
    }
}
