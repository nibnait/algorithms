package cc.tianbin.java.集合;

import io.github.nibnait.common.utils.DataUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nibnait on 2022/11/10
 */
public class Set类 {

    @Test
    public void test() {
        Set<Integer> set = new HashSet();
        set.add(1);
        set.add(222);
        set.add(3333);
        set.add(4444);
        set.add(5555);
        set.add(6666);
        set.add(7777);
        set.add(888);
        System.out.println("\n" + DataUtils.toJsonStringArray(set) + "\n");



    }

}
