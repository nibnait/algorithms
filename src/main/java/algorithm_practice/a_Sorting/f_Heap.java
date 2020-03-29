package algorithm_practice.a_Sorting;

import common.util.SysOut;
import common.util.SwapUtil;

/**
 * Created by nibnait on 2016/8/10.
 */
public class f_Heap {
    public static void main(String[] args) {
//        int[] a = new int[10];
//        a = SysRandom.random(a);
        int[] a = {73, 98, 27, 36, 77, 22, 6, 32, 83, 69};  // 73 为完全二叉树的根节点
        SysOut.printArray(a);

        a = Heap_Sort(a);
        SysOut.printArray(a);
    }

    public static int[] Heap_Sort(int[] a) {

        int N = a.length - 1;
        //建立一支 二叉大根堆，先序遍历
        for (int k = N / 2; k >= 0; k--) {    //从右像左数 第一个含有子结点的非叶子结点开始调整
            sink(a, k, N);
            SysOut.printArray(a);
        }

        while (N > 0) {
            SwapUtil.swap(a, 0, N--);    //将堆的最大元素a[0]和a[N]交换，
            sink(a, 0, N);          //调整堆， 直到堆空
            SysOut.printArray(a);
        }

        return a;
    }

    //自上向下调整子树，使父亲永远大于儿子
    private static void sink(int[] a, int k, int N) {
        while (2 * k < N) {
            int j = 2 * k + 1;    //a[j]为 a[k]的左子结点
            if (j < N && a[j] < a[j + 1]) {
                j++;        //保证a[j]指向 a[k]的较大的那个孩子结点
            }
            if (a[k] > a[j]) { //如果a[k] 比他的最大的子结点还大，则无需调整
                break;
            } else {    //否则 交换a[k] 和a[j]
                SwapUtil.swap(a, j, k);      //为了保证a[k]（堆顶）（父亲永远比儿子大）
                k = j;  // 然后 继续 a[k]的子结点和a[k]的孙结点进行比较
            }
        }
    }
}