package localtest.simpleTest;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by nibnait on 2020-01-06
 */
public class StringTest extends TestCase {

    @Test
    public void testSubString() {
        String str = "123";
        System.out.println(str.substring(0, str.length() - 1));
    }

    @Test
    public void testSplit() {
        String str = "1234,1243";
        System.out.println(str.split(",")[0]);
    }
}
