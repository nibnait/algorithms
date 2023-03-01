package cc.tianbin.demo.java.异常;

import org.junit.Test;

/**
 * Created by nibnait on 2020/11/24
 */
public class Main {

    @Test
    public void testThrowable() throws Throwable {

        throw new Throwable();

    }

    @Test
    public void testException() throws Exception {

        throw new Exception();
    }


    @Test
    public void testError() {

        throw new Error();
    }


}
