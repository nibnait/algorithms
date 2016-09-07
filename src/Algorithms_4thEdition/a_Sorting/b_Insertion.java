package Algorithms_4thEdition.a_Sorting;

import Standard.std;
import Standard.stdOut;
import Standard.stdRandom;

/**
 * Created by nibnait on 2016/8/7.
 */
public class b_Insertion {
    public static void main(String[] args) {
        int[] a = new int[10];
        a = stdRandom.random(a);
        stdOut.print(a);

        a = Insertion_Sort(a);
        stdOut.print(a);
    }

    /**
     * 适用于元素基本（接近）有序的数组，
     */
    public static int[] Insertion_Sort(int[] a) {

        int length = a.length;

        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0 && a[j]<a[j-1]; j--) {
                std.exch(a,j,j-1);
            }
        }
        return a;
    }

}
