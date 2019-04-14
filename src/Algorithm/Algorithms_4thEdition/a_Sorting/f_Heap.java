package Algorithm.Algorithms_4thEdition.a_Sorting;

import Standard.Std;
import Standard.StdOut;

/**
 * Created by nibnait on 2016/8/10.
 */
public class f_Heap {
    public static void main(String[] args) {
//        int[] a = new int[10];
//        a = StdRandom.random(a);
        int[] a = {73,98,27,36,77,22,6,32,83,69};
        StdOut.print(a);

        a = Heap_Sort(a);
        StdOut.print(a);
    }

    public static int[] Heap_Sort(int[] a) {

        int N = a.length-1;
        //建立一支 二叉大根堆，先序遍历
        for (int k = N/2; k >= 0; k--) {    //从 从右像左数 第一个含有子节点的非叶子结点开始调整
            sink(a, k, N);
            StdOut.print(a);
        }

        while (N > 0) {
            Std.swap(a, 0, N--);    //将堆的最大元素a[0]和a[N]交换，
            sink(a, 0, N);          //调整堆， 知道堆空
            StdOut.print(a);
        }

        return a;
    }

    //自下向上的堆有序化【下沉】
    private static void sink(int[] a, int k, int N) {
        while (2 * k < N) {
            int j = 2 * k+1;    //a[j]为 a[k]的左子结点
            if (j < N && a[j] < a[j + 1]) {
                j++;        //保证a[j]指向 a[k]的较大的那个孩子结点
            }
            if (a[k] > a[j]) { //如果a[k] 比他的最大的子结点还大，则无需调整
                break;
            } else {    //否则 交换a[k] 和a[j]
                Std.swap(a, j, k);      //为了保证a[k]（堆顶）（父亲永远比儿子大）
                k = j;  // 然后 继续 a[k]的子结点和a[k]的孙结点进行比较
            }
        }
    }
}