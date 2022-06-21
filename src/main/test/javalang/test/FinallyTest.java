package javalang.test;

import io.github.nibnait.common.exception.ClientViewException;
import org.junit.Test;

/**
 * Created by nibnait on 2022/05/11
 */
public class FinallyTest {

    @Test
    public void test01() {
        try {
            System.out.println("try");
            throw new RuntimeException("123");
        } catch (Exception e) {
            System.out.println("catch");
            return;
        } finally {
            System.out.println("finally");
        }
    }

    @Test
    public void test02() {
        try {
            System.out.println("try");
            return;
        } catch (Exception e) {
            System.out.println("catch");
        } finally {
            System.out.println("finally");
        }
    }

    @Test
    public void test03() {
        try {
            throw new ClientViewException("123");
        } finally {
            System.out.println(111);
        }

    }
}
