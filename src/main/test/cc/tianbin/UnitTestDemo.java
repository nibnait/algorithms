import cn.hutool.core.lang.Assert;
import cc.tianbin.common.CommonConstants;
import cc.tianbin.common.util.CompareUtils;
import cc.tianbin.common.util.SysOut;
import cc.tianbin.common.util.SysRandom;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by nibnait on 2022/07/06
 */
@Slf4j
public class UnitTestDemo {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int maxLen = 20;
        int maxValue = 30;
        int[] arr = SysRandom.generateArrNaturalNum(maxLen);
        int aim = SysRandom.generateNaturalNum(maxValue);
        int ans1 = test(arr, aim);
        int ans2 = test(arr, aim);

        if (ans1 != ans2) {
            SysOut.printArray(arr);
            SysOut.printf("ans1: {}, ans2: {}", ans1, ans2);
            throw new RuntimeException();
        }
    }

    @Test
    public void test2() {
        int[] arr = SysRandom.randomArrNaturalNumRandomLength(20);

        int ans1 = test();
        int ans2 = test();

        if (ans1 != ans2) {
            SysOut.printArray(arr);
            SysOut.printf("ans1: {}, ans2: {}", ans1, ans2);
            throw new RuntimeException();
        }

        SysOut.printArray(arr);
        SysOut.println(ans1);
    }

    @Test
    public void test1() {
        String str1 = "a12b3c456d";
        String str2 = "1ef23ghi4j56k";
        int ans1 = test(str1, str2);
        int ans2 = test(str1, str2);
        Assert.equals(ans1, ans2);

        str1 = "abcde";
        str2 = "ace";
        ans1 = test(str1, str2);
        ans2 = test(str1, str2);
        Assert.equals(ans1, ans2);
    }

    @Test
    public void testCase1() {
        int[] arr = SysRandom.generateArrNaturalNum();
        int[] copyArr = Arrays.copyOf(arr, arr.length);

        if (!CompareUtils.isSortAsc(arr)) {
            log.error("原数组: {}", copyArr);
            log.error("排序后: {}", arr);
            throw new RuntimeException();
        }
    }

    public int test(Object... args) {
        boolean f1 = true;
        boolean f2 = false;

        f1 = f1 || f2;
        System.out.println(f1);

        return 0;
    }

}
