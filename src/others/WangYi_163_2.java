package others;

import java.util.Scanner;

/**
 * 暗黑字符串
 * <p>
 * Created by nibnait on 2016/9/12.
 */
public class WangYi_163_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        if (num == 1) {
            System.out.println(3);
            return;
        }
        if (num == 2) {
            System.out.println(9);
            return;
        }
        int[] r = new int[100];
        int[] n = new int[100];
        r[2] = 3;
        n[2] = 6;
        for (int i = 3; i<=num; i++){
            r[i] = r[i-1]+n[i-1];
            n[i] = n[i-1]+r[i-1]*2;
        }

        System.out.println(r[num]+n[num]);
    }

}
