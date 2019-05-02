package zzzTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by nibnait on 2016/8/6.
 */
public class RandomTest {

    public static void main(String[] args) {

        List<String> applyIds = new ArrayList<>();
        applyIds.add("123");
        int max = applyIds.size();
        int index = (int) (Math.random()*max);
        System.out.println(index);

    }

    private void test01() {
        //int[] a = new int[10];
        //SysOut.println(SysRandom.random(a));
        System.out.println(UUID.randomUUID().toString());
    }
}
