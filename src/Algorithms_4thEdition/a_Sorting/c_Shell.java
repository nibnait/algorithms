package Algorithms_4thEdition.a_Sorting;

import Standard.std;
import Standard.stdOut;
import Standard.stdRandom;

/**
 * Created by nibnait on 2016/8/7.
 */
public class c_Shell {
    public static void main(String[] args) {
//        int[] Algorithms_4thEdition = new int[15];
//        Algorithms_4thEdition = stdRandom.random(Algorithms_4thEdition);
        int[] a = {2,1,3,4,5,6,0,2};
        stdOut.print(a);

        a = Shell_Sort(a);
        stdOut.print(a);
    }

    //对于大规模乱序数组
    //将数组整理成【h有序数组】，==> 即a[0]< Algorithms_4thEdition[h]< Algorithms_4thEdition[2*h]<...，Algorithms_4thEdition[1]< Algorithms_4thEdition[h+1]< Algorithms_4thEdition[2*h+1]<...
    //h：1, 4, 13, 41, 121, 364, 1093...  3倍的倍数递增。
    public static int[] Shell_Sort(int[] a) {

        int length = a.length;
        int h = 1;     //步长
        while (h < length/3){
            h = 3*h +1;
        }

        while (h>0){
            for (int i = h; i < length; i++) {//将数组整理成【h有序数组】，
                for (int j = i; j >= h && a[j]<a[j-h]; j-=h) {
                    std.exch(a,j,j-h);
                }
            }
            stdOut.print(a);
            h = h/3;
        }

        return a;
    }
}
