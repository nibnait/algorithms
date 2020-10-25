package localtest.simpleTest;

import junit.framework.TestCase;
import org.junit.Test;

public class BooleanTest extends TestCase {

    @Test
    public void testParseBoolean() {
        boolean b = Boolean.parseBoolean("1");
        System.out.println(b);
    }
}
