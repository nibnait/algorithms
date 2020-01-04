package algorithm_practice.SwordOffer.old;

/**
 * 题目：我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求从小到大的顺序的第 1500个丑数。
 * 例如 6、8 都是丑数，但 14 不是，它包含因子 7。习惯上我们把 1 当做第一个丑数。
 *
 * 【解】：用空间换时间
     * 假设当前已经求到了第M个UglyNumber
     * 则第M+1个丑数一定是前面的某个数字 *2、*3、*5的最小值！
     * 是谁，谁对应的指针++，
     * 其他的指针也要跟进到 *2、*3、*5之后的值<=UglyNumber[M+1]!!
 *
 *
 * Created by nibnait on 2016/10/1.
 */
public class e34_丑数 {

    /**
     * 求第n个丑数：
     * @param n
     * @return
     */
    private static int getUglyNumber(int n) {
        if (n<=0){
            return 0;
        }
        int[] UglyNumbers = new int[n];
        UglyNumbers[0] = 1; //第1个丑数是1
        int nextIndex = 1;
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;

        while (nextIndex < n){
            int min = min(UglyNumbers[p2]*2, UglyNumbers[p3]*3, UglyNumbers[p5]*5);
            UglyNumbers[nextIndex] = min;
            while (UglyNumbers[p2]*2 <= UglyNumbers[nextIndex]){
                p2++;
            }
            while (UglyNumbers[p3]*3 <= UglyNumbers[nextIndex]){
                p3++;
            }
            while (UglyNumbers[p5]*5 <= UglyNumbers[nextIndex]){
                p5++;
            }
            nextIndex++;
        }
        return UglyNumbers[--nextIndex];
    }

    private static int min(int a, int b, int c) {
        int min = a<b? a: b;
        return c<min? c: min;
    }

    public static void main(String[] args) {
        System.out.println(getUglyNumber(1)); // 1
        System.out.println(getUglyNumber(2)); // 2
        System.out.println(getUglyNumber(3)); // 3
        System.out.println(getUglyNumber(4)); // 4
        System.out.println(getUglyNumber(5)); // 5
        System.out.println(getUglyNumber(6)); // 6
        System.out.println(getUglyNumber(7)); // 8
        System.out.println(getUglyNumber(8)); // 9
        System.out.println(getUglyNumber(9)); // 10
        System.out.println(getUglyNumber(10)); // 12
        System.out.println(getUglyNumber(11)); // 15
        System.out.println(getUglyNumber(1500)); // 859963392
        System.out.println(getUglyNumber(0)); // 0
    }

}
