package algorithm_practice.LeetCode.code300;

import junit.framework.TestCase;
import org.junit.Test;

/*
有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？

如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。

你允许：

装满任意一个水壶
清空任意一个水壶
从一个水壶向另外一个水壶倒水，直到装满或者倒空
示例 1: (From the famous "Die Hard" example)

输入: x = 3, y = 5, z = 4
输出: True
示例 2:

输入: x = 2, y = 6, z = 5
输出: False


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/water-and-jug-problem
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
Created by nibnait on 2020-03-21
 */
public class M365_水壶问题 extends TestCase {

    @Test
    public void testCase() {
        int x = 3, y = 5, z = 6;
        System.out.println(canMeasureWater(x, y, z));

        int x1 = 2, y1 = 6, z2 = 5;
        System.out.println(canMeasureWater(x1, y1, z2));


    }

    public boolean canMeasureWater(int x, int y, int z) {

        if (x == z || y == z || z == 0) {
            return true;
        }

        if (z > x + y) {
            return false;
        }

        int gcd;
        if (x == 0 || y == 0) {
            gcd = x + y;
        } else {
            gcd = getGcd(x, y);
        }

        return z % gcd == 0;
    }

    private int getGcd(int x, int y) {
        if (x % y == 0) {
            return y;
        }
        return getGcd(y, x % y);
    }

}
