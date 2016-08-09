package a_Sorting;

import Standard.std;
import Standard.stdOut;
import Standard.stdRandom;

import java.util.NavigableMap;

/**
 * Created by nibnait on 2016/8/10.
 */
public class f_Deap {
    public static void main(String[] args) {
        int[] a = new int[10];
        a = stdRandom.random(a);
//        int[] a = {99,92,76,25,55,64,63,17,69,33};
        stdOut.print(a);

        a = Deap_Sort(a);
        stdOut.print(a);
    }

    public static int[] Deap_Sort(int[] a) {

        int N = a.length-1;
        //建立一支 二叉大根堆
        for (int k = N/2; k >= 0; k--) {
            sink(a, k, N);
        }

        while (N > 0) {
            std.exch(a, 0, N--);    //将堆的最大元素a[0]和a[N]交换，
            sink(a, 0, N);          //调整堆， 知道堆空
        }

        return a;
    }

    //自下向上的堆有序化【下沉】
    private static void sink(int[] a, int k, int N) {
        while (2 * k < N) {
            int j = 2 * k+1;    //a[j]为 a[k]的左子结点
            if (j < N && a[j] < a[j + 1]) {
                j++;        //如果左子结点比右子结点大， 则j++
            }
            if (a[k] > a[j]) { //如果a[k] 比他的最大的子结点还大，则无需调整
                break;
            } else {    //否则 交换a[k] 和a[j]
                std.exch(a, j, k);
                k = j;  // 然后 继续 a[k]的子结点和a[k]的孙结点进行比较
            }
        }
    }
}