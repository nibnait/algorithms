package zzzTest;

import Standard.stdOut;
import Standard.stdRandom;

import java.util.UUID;

/**
 * Created by nibnait on 2016/8/6.
 */
public class RandomTest {

    public static void main(String[] args) {
//        int[] a = new int[10];
//        stdOut.print(stdRandom.random(a));
        System.out.println(UUID.randomUUID().toString());
    }
}
