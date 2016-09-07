package others;

import java.util.Scanner;

/**
 * 京东  第k个幸运数字
 * Created by nibnait on 2016/9/5.
 */
public class K_lucky {

    public static void main(String[] args) {
//        int[] x = new int[18];
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int[][] x = new int[t][18];
        int tt = 0;
        while (t-- > 0) {
            int n = in.nextInt();
//            System.out.println(n);
            int length;
            for (int i = 2; ; i++) {
                if (n <= Math.pow(2, i) - 2) {
                    length = i - 1;
                    break;
                }
            }
            int i = 0;
            while (length-- > 0) {
                if (n > Math.pow(2, length + 1) - 2 + Math.pow(2, length)) {
                    x[tt++][i++] = 7;
//                    x[i++] = 7;
                    n = n - (int) Math.pow(2, length + 1);
                } else {
                    x[tt++][i++] = 4;
//                    x[i++] = 4;
                    n = n - (int) Math.pow(2, length);
                }
            }
            /*for (int j = 0; j < x.length; j++) {
                if (x[j] == 0) break;
                System.out.print(x[j]);
            }*/
        }
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                if (x[i][j] == 0) break;
                System.out.print(x[j]);
            }
        }
    }
}

/*    public static void main(String[] args) {
        boolean[] b = new boolean[18];
        Scanner in = new Scanner(System.in);
        int c = in.nextInt();
        for(; c-->0; ){
            int i, k, sum, dig;
            k = in.nextInt();
            for(i=1, sum=0; k>(sum+=Math.pow(2,i)); i++);
            dig = i;

            double ord = k-(sum-Math.pow(2,i)) -1;
            for (int j = 0; j < dig; j++) {
                b[j] = false;
            }
            i = -1;
            do {
                if (ord%2!=0){
                    b[++i] = true;
                }else {
                    b[++i] = false;
                }
                ord = ord/2;
            }while (ord!=0);

            for (i = dig-1; i>=0; i--){
                if (b[i]){
                    System.out.print("7");
                }else {
                    System.out.print("4");
                }
            }
            System.out.println();
        }

    }*/
