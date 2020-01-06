package os.jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试GC
 */
public class HeapOOM {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            map.put(i, i + "");
        }

    }

}