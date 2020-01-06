package algorithm_practice.a_Sorting;

import common.util.SwapUtil;
import common.util.SysOut;
import common.util.SysRandom;

/**
 * Created by nibnait on 2016/8/7.
 */
public class b_Insertion {
    public static void main(String[] args) {
        int[] a = new int[10];
        a = SysRandom.random(a);
        SysOut.printArray(a);

        a = Insertion_Sort(a);
        SysOut.printArray(a);
    }

    /**
     * 适用于元素基本（接近）有序的数组，
     */
    public static int[] Insertion_Sort(int[] a) {

        int length = a.length;

        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0 && a[j]<a[j-1]; j--) {
                SwapUtil.swap(a,j,j-1);
            }
        }
        return a;
    }

}
