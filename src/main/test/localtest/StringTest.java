package localtest;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by nibnait on 2020-01-06
 */
public class StringTest extends TestCase {

    @Test
    public void testCase() {
        String str = "1234,1243";
        System.out.println(str.split(",")[0]);
    }
}
