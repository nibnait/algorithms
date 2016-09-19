package others;

import java.util.Scanner;

/**
 * n的阶乘末尾0的个数
 * Created by nibnait on 2016/9/18.
 */
public class DD_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ret = 0;
        while (n!=0){
            ret += n/5;
            n /= 5;
        }
        System.out.println(ret);
    }
}
