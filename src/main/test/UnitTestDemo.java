import common.CommonConstants;
import org.junit.Test;

/**
 * Created by nibnait on 2022/07/06
 */
public class UnitTestDemo {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }
    
    @Test
    public void testCase() {

    }


}
