package algorithm_practice.algorithmzuo.b_体系学习班.class02;

import common.util.SysOut;
import org.junit.Test;

/**
 * Created by nibnait on 2022/06/22
 */
public class Code02_EvenTimesOddTimes {

    // arr中，只有一种数，出现奇数次
    @Test
    public void printOddTimesNum1() {
        int[] arr = {3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1};

        if (arr.length == 0) {
            return;
        }
        if (arr.length == 1) {
            SysOut.println(arr[0]);
        }
        int eor = arr[0];
        for (int i = 1; i < arr.length; i++) {
            eor ^= arr[i];
        }
        SysOut.println(eor);
    }

    // 一个int类型的数，提取出它二进制中最右侧的1来
    @Test
    public void bit1counts() {
        int num = 1744;
        // 011011010000
        // 100100110000

        int rightOne = num & (~num + 1);
        // 000000010000

        SysOut.println(rightOne);

    }

    // arr中，有两种数，出现奇数次
    @Test
    public void printOddTimesNum2() {
        int[] arr = {4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2};

        int eor = arr[0];
        for (int i = 1; i < arr.length; i++) {
            eor ^= arr[i];
        }

        // 此时 eor = a ^ b
        // 找出 eor 的二进制最右面的那个1的位置。a 和 b 在 rightOne 位置一定一个是0，一个是1
        int rightOne = eor & (-eor);
        int a = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                a ^= arr[i];
            }
        }

        SysOut.println("a = %s, b = %s", a, a ^ eor);
    }

}
