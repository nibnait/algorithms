package a_Sorting;

import Standard.std;
import Standard.stdOut;
import Standard.stdRandom;

/**
 * Created by nibnait on 2016/8/5.
 */
public class a_Selection {
    public static void main(String[] args) {
        int[] a = new int[10];
        a = stdRandom.random(a);
        stdOut.print(a);

        a = Selection_Sort(a);
        stdOut.print(a);
    }

    /**
     * 不断的选择剩余元素中的最小者
     * 比较 N*(N-1)/2次
     * 交换 N-1次
     *
     * 时间复杂度：O（N^2)
     */
    public static int[] Selection_Sort(int[] a) {

        int N = a.length;
        int min = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (a[min] > a[j]) {
                    min = j;
                }
            }
            std.exch(a, i, min);
        }
        return a;
    }
}
