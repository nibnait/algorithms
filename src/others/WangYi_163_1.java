package others;

import java.util.Scanner;

/**
 * 网易第一题
 * 数字反转
 * Created by nibnait on 2016/9/12.
 */
public class WangYi_163_1 {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int x =sc.nextInt();
        int y = sc.nextInt();
        System.out.println(rev(rev(x)+rev(y)));

    }

    private static int rev(Integer x) {
        String s = x.toString();
        StringBuilder str=new StringBuilder(s);
        str.reverse();
        return Integer.parseInt(str.toString());
    }
}
