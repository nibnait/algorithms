package algorithm_practice.SwordOffer.old;

/**
 * 典型的位运算
 *
 * Created by nibnait on 2016/9/20.
 */
public class b10_二进制中1的个数 {

    /**
     * 【惊喜做法】
     *  一个数减去1：（如果一个数不是0，那么它的二进制中至少有1位是1）
     *   - 假设这个数的最后一位是1，那么减去1之后，只是最后一位由1变成了0，其他位不变。
     *   - 最后一位不是1，假设这个数第m位是1，减去1之后，
     *      第m位由1变成了0，m后面的0全部变成了1，m前面的数字不变
     *      这时 n & (n-1)，就可以把m后面的那些1右变回了0，然后再继续 判断、减1
     * @param n
     * @return
     */
    public static int NumberOf1InBinary(int n){
        int cnt = 0;
        while (n != 0){
            n = n & (n-1);
            cnt++;      //所以cnt就记录着 n一共被减了多少次1，即n的二进制表示中1的个数
        }
        return cnt;
    }

    /**
     * 自然想法：一个int有32位，那n的二进制的每一位都跟 00000000 ... ... 00000001与一下，
     *          结果为1，则cnt++
     *          否则 cnt不变
     * @param n
     * @return
     */
    private static int numberOfOne1(int n) {
        int cnt = 0;
        for (int i = 0; i < 32; i++) {  //Java语言规范中，int整形占四个字节，总计32位
            cnt += (n & 1);
            n = n >> 1;
        }
        return cnt;
    }


    public static void main(String[] args) {
//        int num = 0B00000000_00000000_00000000_00000000;    // 0
//        int num = 0B00000000_00000000_00000000_00000001;    // 1
//        int num = 0B11111111_11111111_11111111_11111111;    // -1
//        int num = 0B01111111_11111111_11111111_11111111;    // Integer.MAX_VALUE
//        int num = 0B10000000_00000000_00000000_00000000;    // Integer.MIN_VALUE
        int num = 3;

        System.out.println(NumberOf1InBinary(num));

        System.out.println(0B01111111_11111111_11111111_11111111 == Integer.MAX_VALUE);
        System.out.println(0B10000000_00000000_00000000_00000000 == Integer.MIN_VALUE);
    }

}
