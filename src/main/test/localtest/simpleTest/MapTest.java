package localtest.simpleTest;

import junit.framework.TestCase;
import localtest.Person;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*

Created by nibnait on 2020-01-15
 */
public class MapTest extends TestCase {


    @Test
    public void testMapValue() {
        Person person = null;
        Map<String, Person> map = new HashMap<>();

        map.put("1", person);

        System.out.println(map);
    }


    public static final Map<String, Integer> SHARE_TARGETS_TYPE_MAP = new LinkedHashMap<String, Integer>() {
        {
            put("qq_session", 4);
            put("weixin_session", 0);
        }
    };

    @Test
    public void testStreamMap() {

        SHARE_TARGETS_TYPE_MAP.forEach((k, v)-> {
            Map<String, String> shareSession = new LinkedHashMap<>();
            shareSession.put("type", v.toString());

        });

    }

    @Test
    public void testMapKeyEnum() {
        Map<Key, String> map = new HashMap<>();
        map.put(Key.aa, "aaa");
        System.out.println(map.get(Key.aa));
        System.out.println(map.get(Key.bb));
    }

    private enum Key {
        aa,
        bb;
    }


}