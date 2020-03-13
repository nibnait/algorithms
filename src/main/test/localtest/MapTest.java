package localtest;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/*
  
Created by nibnait on 2020-02-15
 */
public class MapTest extends TestCase {

    @Test
    public void testCase() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "11");
        map.put("text", "22");
        map.put("url", "33");

        for (Object obj : map.keySet()) {
            System.out.println(obj);
        }
    }

}
