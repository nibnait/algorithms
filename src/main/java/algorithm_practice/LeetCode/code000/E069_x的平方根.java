package algorithm_practice.LeetCode.code000;

import junit.framework.TestCase;
import org.junit.Test;

/*
实现 int sqrt(int x) 函数。

计算并返回 x 的平方根，其中 x 是非负整数。

由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

示例 1:
输入: 4
输出: 2

示例 2:
输入: 8
输出: 2
说明: 8 的平方根是 2.82842...,
     由于返回类型是整数，小数部分将被舍去。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sqrtx
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by nibnait on 2019-08-07
 */
public class E069_x的平方根 extends TestCase {

    @Test
    public void testCase() {
        System.out.println(mySqrt(2));
        System.out.println(mySqrt(4));
        System.out.println(mySqrt(8));
        System.out.println(mySqrt(2147395599));
    }

    public int mySqrt(int x) {

        long lo = 0;
        long hi = x;
        long mid = 0;
        while (lo < hi) {
            mid = (lo + hi + 1) / 2;
            long square = mid * mid;
            if (square > x) {
                hi = mid - 1;
            } else {
                lo = mid;
            }
        }

        return (int) lo;
    }
}
