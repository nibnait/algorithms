package SwordOffer;

/**
 * 求1000以内的素数
 * Created by nibnait on 2016/9/18.
 */
public class Prime {

    public static void main(String[] args) {
        int n = 1000;
        for (int i = 0; i < 1000; i++) {
            if (prime(i)) {
                System.out.println(i);
            }
        }
    }

    /**
     * n如果是素数，return true
     * @param n
     * @return
     */
    private static boolean prime(int n) {

        if (n%2 == 0){      //少检验了1/2个数字
            return n==2;
        }
        if (n%3 ==0){       //少检验了1/3个数字
            return n==3;
        }
        if (n%5 ==0){
            return n==5;
        }
        for (int i = 7; i*i < n; i++) {     //避免调用sqrt函数，节省开销
            if (n%i == 0){
                return false;
            }
        }
        return true;
    }

}
