package a_Sorting;

import Standard.std;
import Standard.stdOut;
import Standard.stdRandom;

/**
 * Created by nibnait on 2016/8/9.
 */
public class e_Quick {
    public static void main(String[] args) {
        int[] a = new int[15];
        a = stdRandom.random(a);
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

        int flag = lo;  //以第一个元素为基准
        for (int i = lo+1; i < hi; i++) {
            if (a[i] < a[lo]){
                std.exch(a, i, lo);
                lo = i;
            }
        }
        return flag;
    }
}
