package algorithm_practice.a_Sorting;


import common.util.SysOut;
import common.util.SysRandom;

/**
 * Created by nibnait on 2016/8/8.
 */
public class d_Merge {

    public static void main(String[] args) {
        int[] a = new int[7];
        a = SysRandom.random(a);
        SysOut.printArray(a);

        divide(a, 0, a.length - 1);
        SysOut.printArray(a);
    }

    /**
     * 时间复杂度：N*lgN
     * 但是 需要辅助数组。
     */
    public static void divide(int[] a, int lo, int hi) {
        if (lo >= hi) {    //lo >= hi 的时候，就说明 不能再分了，可以开始归并了
            return;
        }
        int mid = (lo + hi) / 2;
        //递归的将数组分到不能再分
        divide(a, lo, mid);
        divide(a, mid + 1, hi);

        //开始Merge
        Merge(a, lo, mid, hi);
    }

    private static void Merge(int[] a, int lo, int mid, int hi) {
        //新建一个辅助数组
        int aux[] = new int[a.length + 1];
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }

        //开始合并！
        int p1 = lo, p2 = mid + 1;
        for (int i = lo; i <= hi; i++) {
            if ((aux[p1] <= aux[p2] && p1 <= mid) || p2 > hi) {
                a[i] = aux[p1++];
            } else {
                a[i] = aux[p2++];
            }
        }
    }

}
