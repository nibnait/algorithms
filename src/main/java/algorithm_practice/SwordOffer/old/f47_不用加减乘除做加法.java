package algorithm_practice.SwordOffer.old;

/**
 * 题目：写一个函数，求两个整数之和，要求在函数体内不得使用＋、－、×、÷四则运算符号。
 *
 * 【解】：
 *  不用加减乘除，那就只剩下位运算了
 *  回到了计算机中是如何做加法的：
 *      求 A + B
 *      while(B != 0){
 *          1. sum = A ^ B;
 *          2. carry = (A & B) << 1;  //只有A和B都是1时，才有进位
 *                                    //如果又进位，则sum与carry还要再异或一次。
 *          3. A = sum, B = carry
 *      }
 *
 * Created by nibnait on 2016/10/2.
 */
public class f47_不用加减乘除做加法 {

    public static void main(String[] args) {
        System.out.println(add(1, 2) + ", " + (1 + 2));
        System.out.println(add(13, 34)+ ", " + (13 + 34));
        System.out.println(add(19, 85)+ ", " + (19 + 95));
        System.out.println(add(865, 245)+ ", " + (865 + 245));
    }

    private static int add(int num1, int num2){
        int sum = 0;
        int carry = 0;
        while (num2 != 0){
            sum = num1 ^ num2;
            carry = (num1 & num2) << 1;
            num1 = sum;
            num2 = carry;
        }
        return num1;
    }

}
