package tool;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.UUID;

/*
生成博客的UUID
 */
public class GenerateUUID extends TestCase {

    @Test
    public void testCase() {
        System.out.println(UUID.randomUUID().toString());
    }
}
