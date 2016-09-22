package xiaozhao;

import java.util.Scanner;

/**
 * Created by nibnait on 2016/9/19.
 */
public class LeShi_01 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n==0){
                System.out.println(0);
                return;
            }
            int step = 0;
            int sum = 0;
            while (true){
                sum+=++step;
                if ( n == sum){
                    System.out.println(step);
                    break;
                }
                if (n > sum){       //如果n不是 1+2+3+4+5的情况
                    int x = n-sum-1;      //比上一档多几
                    int xx = (step+1)*step/2 -1;   //基数
                    if (xx < step){
                        continue;
                    }
                    System.out.println(2*(xx+x)+1);
                    break;
                }
            }
        }
    }

}
