import common.CommonConstants;
import common.util.CompareUtils;
import common.util.SysRandom;
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
        int arr[] = SysRandom.randomArrNaturalNum();
        int[] copyArr = Arrays.copyOf(arr, arr.length);

        if (!CompareUtils.isSortAsc(arr)) {
            log.error("原数组: {}", copyArr);
            log.error("排序后: {}", arr);
            throw new RuntimeException();
        }
    }

}
