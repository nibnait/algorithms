package algorithm_practice.SwordOffer.old;

/**
 * Created by nibnait on 2016/9/20.
 */
public class b09_斐波那契数列 {

    public static void main(String[] args) {
        System.out.println(Fib1(10));
        System.out.println(Fibonacci(10));
    }

    private static long Fibonacci(int n) {

        if (n <= 0){
            return 0;
        } else if (n == 1){
            return 1;
        } else {
            long fibNMinusOne = 0;
            long fibNMinusTwo = 1;
            long fibN = 0;
            for (int i = 2; i <= n; i++) {
                fibN = fibNMinusOne + fibNMinusTwo;
                fibNMinusOne = fibNMinusTwo;
                fibNMinusTwo = fibN;
            }
            return fibN;
        }
    }

    private static long Fib1(int n) {
        return n<2? (n<1?0:1) : Fib1(n-1)+Fib1(n-2);
    }

}
