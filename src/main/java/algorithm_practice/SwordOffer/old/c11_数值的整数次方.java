package algorithm_practice.SwordOffer.old;

/**
 * 实现函数double Power(double base, int exponent)，求base的exponent次方。
 * 不得使用库函数，同时不需要考虑大数问题。
 *
 * 【注意】
 * - 底数(base)是0且指数(exponent)是负数时的，throw new RuntimeException("参数异常")
 * - 0的0次方，无意义.任何数的0次方都 return 1;
 * - 任何数的1次方 都等于他本身，return base;
 * - 判断两个小数相等：由于计算机在表示小数时(包括float和double型的小数)都有误差，所以只要两数之差的绝对值在一个很小的范围内，
 *          （小于0.0000001），就可以认为两数相等了！
 * - 下面开始求a的n次方：
 *      n为偶数时，a^n = a^(n/2) * a^(n/2);
 *      n为奇数时，a^n = a^((n-1)/2) * a^((n-1)/2) * a
 *
 * Created by nibnait on 2016/9/20.
 */
public class c11_数值的整数次方 {

    public static double Power(double base, int exponent){

        if (equal(base, 0.0) && exponent<0){
            throw new IllegalArgumentException("Invalid args");
        }
        if (exponent == 0){
            return 1;
        }
        if (exponent == 1){
            return base;
        }
        //如果exponent为负数，则先求base的(-exponent)次方，然后return 1/result
        if (exponent < 0){
            exponent = -exponent;
        }
        double result = powWithRecursion(base, exponent);
        if (exponent < 0){
            return 1.0 / result;
        }
        return result;
    }

    private static double powWithRecursion(double base, int exponent) {
        if (exponent == 0){
            return 1;
        }
        if (exponent == 1){
            return base;
        }
        double result = powWithRecursion(base, exponent>>1);
        result *= result;
        if (exponent % 2 != 0){     //如果exponent为奇数
            return result *= base;  //还得乘一次base
        }
        return result;
    }

    private static boolean equal(double base, double num) {
        if ((base-num)>-0.0000001 && (base-num)<0.0000001){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(0.0000000000000000000000001111 == 0);
        System.out.println(0.0000000000000000000000000000 == 0);

        System.out.println(Power(3,6));
        System.out.println(Power(2, -4));
        System.out.println(Power(2, 4));
        System.out.println(Power(2, 0));
        System.out.println(Power(0.00000000000000000000000000001, 1));
//        System.out.println(Power(0.00000000000000000000000000001, -1));
//        System.out.println(Power(0.00000000000000000000000000001, 0));
//        System.out.println(Power(0.00000000000000000000000000000, 0));
    }

}
