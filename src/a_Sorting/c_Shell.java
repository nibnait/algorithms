package a_Sorting;

import Standard.std;
import Standard.stdOut;
import Standard.stdRandom;

/**
 * Created by nibnait on 2016/8/7.
 */
public class c_Shell {
    public static void main(String[] args) {
        int[] a = new int[15];
        a = stdRandom.random(a);
        stdOut.print(a);

        a = Shell_Sort(a);
        stdOut.print(a);
    }

    //对于大规模乱序数组
    //将数组整理成【h有序数组】，
    //h：1, 4, 13, 41, 121, 364, 1093...  3倍的倍数递增。
    public static int[] Shell_Sort(int[] a) {

        int length = a.length;
        int h = 1;
        while (h < length/3){
            h = 3*h -1;
        }

        while (h>=1){
            for (int i = h; i < length; i++) {//将数组整理成【h有序数组】，
                for (int j = i; j > 0 && a[j]<a[j-h]; j--) {
                    std.exch(a,j,j-h);
                }
            }
//            stdOut.print(a);
            h = h/3;
        }



        return a;
    }
}
