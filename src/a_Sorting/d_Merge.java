package a_Sorting;

import Standard.StdOut;
import Standard.StdRandom;

/**
 * Created by nibnait on 2016/8/8.
 */
public class d_Merge {

    public static void main(String[] args) {
        int[] a = new int[15];
        a = StdRandom.random(a);
        StdOut.print(a);

        a = Merge_Sort(a);
        StdOut.print(a);
    }

    public static int[] Merge_Sort(int[] a) {

        int N = a.length;
        //递归的将数组分到不能再分
        Sort(a, 0, N/2);
        Sort(a, N/2+1, N-1);

        return a;
    }

    private static void Sort(int[] a, int lo, int hi) {
        if (lo>=hi){    //lo > == hi 的时候，就说明 不能再分了，可以开始归并了
            return;
        }
        int mid = (lo+hi)/2;
        Sort(a, lo, mid);
        Sort(a, mid+1, hi);

        //开始Merge
        Merge(a, lo, mid, hi);
    }

    private static void Merge(int[] a, int lo, int mid, int hi) {
        //新建一个辅助数组
        int aux[] = new int[a.length+1];
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }

        //开始合并！
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if ((aux[i]<=aux[j] && i<=mid) || j>hi){
                a[k] = aux[i++];
            }else {
                a[k] = aux[j++];
            }
        }
    }

}
