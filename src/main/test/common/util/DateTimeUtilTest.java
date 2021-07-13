package common.util;

import org.junit.Test;

import java.util.Date;

/**
 * Created by nibnait on 2021/04/07
 */
public class DateTimeUtilTest {
    @Test
    public void test01() {
        Date date = new Date();
        System.out.println(date.getTime());
    }
}
