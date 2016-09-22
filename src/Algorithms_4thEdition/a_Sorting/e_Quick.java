package Algorithms_4thEdition.a_Sorting;

import Standard.std;
import Standard.stdOut;
import Standard.stdRandom;

/**
 * Created by nibnait on 2016/8/9.
 */
public class e_Quick {
    public static void main(String[] args) {
//        int[] a = new int[10];
//        a = stdRandom.random(a);
        int[] a = {3,4,0,1,6,2,5,1};
        stdOut.print(a);

        a = Quick_Sort(a, 0, a.length-1);
        stdOut.print(a);
    }

    /**
     *
     */
    public static int[] Quick_Sort(int[] a, int lo, int hi) {

        if (lo>=hi)
            return a;
        int j = partition(a, lo, hi);
        Quick_Sort(a, lo, j-1);
        Quick_Sort(a, j+1, hi);
        return a;
    }

    private static int partition(int[] a, int lo, int hi) {

//        int flag = lo;
//        for (int i = lo; i <= hi; i++) {
//            if (a[i] < a[hi]){      //以a[hi]为基准
//                std.exch(a, i, flag++);
//            }
//        }
//        std.exch(a,flag,hi);

        int flag = lo+1;    //以a[lo]为基准
        for (int i = lo+1; i <= hi; i++) {
            if (a[i]<a[lo]) {
                std.exch(a,i,flag++);
            }
        }
        std.exch(a,--flag,lo);
        return flag;
    }
}
