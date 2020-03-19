package algorithm_practice.Coding_Interview_Guide_2ndEdition.Chapter_04_递归和动态规划;

import junit.framework.TestCase;
import org.junit.Test;

/*

 */
public class P01_斐波那契数列 extends TestCase {

    @Test
    public void testCase() {
        // O(2^N)
        System.out.println(f1(4));

        // O(N)
        System.out.println(f2(4));

        //
    }



    private int f2(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        int n1 = 1; // n - 1
        int n2 = 1; // n - 2
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = n1 + n2;
            n2 = n1;
            n1 = result;
        }

        return result;
    }

    private int f1(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        return f1(n - 1) + f1(n - 2);
    }
}
