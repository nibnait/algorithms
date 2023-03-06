package cc.tianbin.demo.java.集合;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author nibnait
 * @version $Id: Map.java, v 0.1 2019-08-12 下午2:16 nibnait Exp $$
 */
public class Map类 {
    public static void main(String[] args) {
        Hashtable hashtable;

        LinkedHashMap linkedHashMap;

        HashMap hashMap;
        WeakHashMap weakHashMap;
        IdentityHashMap identityHashMap;

        EnumMap enumMap;

        TreeMap treeMap;

        ConcurrentHashMap concurrentHashMap;
        ConcurrentSkipListMap concurrentSkipListMap;
    }

    @Test
    public void hashMap() {
        HashMap map = new HashMap();

        map.put(1, 1);
        map.put(2, 2);

        map.get(1);
        map.remove(1);

    }

    @Test
    public void concurrentHashMap() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

        concurrentHashMap.put(1, 1);
    }
}