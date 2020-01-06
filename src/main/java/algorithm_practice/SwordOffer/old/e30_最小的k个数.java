package algorithm_practice.SwordOffer.old;

import common.util.SysOut;
import common.util.SwapUtil;

import static algorithm_practice.SwordOffer.old.e29_数组中出现次数超过一半的数字.partition;

/**
 * 题目： 输入n个整数，找出其中最小的k个数。
 * 例如输入 4 、5 、1、6、2、7、3 、8 这 8 个数字，
 *    则最小的 4 个数字是 1 、2、3 、4
 *
 * 【法1】：O（N）(改变了数组原有的顺序)
 *     基于上一题的Partition函数：
 *     以第k个数为基准，从头开始遍历，
 *      使得比第k个数字小放在arr[k]的左边，比第k个数字大的放在右边
 *
 * 【法2】：O（N*logk) 基于堆排的思想
 *      先建立一个含有k个元素的大顶堆，
 *      每次新来一个数 和堆顶比较，如果大于堆顶，跳过
 *                               如果小于堆顶，交换，然后重新调整二叉堆
 *      直到arr结束
 *
 * Created by nibnait on 2016/9/30.
 */
public class e30_最小的k个数 {

    /**
     * 法2
     * @param arr
     * @param k
     */
    private static void getLeastNumbers2(int[] arr, int k) {
        if (arr==null || arr.length<=0 || k>arr.length){
            throw new RuntimeException("Invalid args");
        }

        int[] output = new int[k];
        for (int i = 0; i < k; i++) {
            output[i] = arr[i];
        }
        //建立大顶堆
        int N = k-1;
        for (int i = k/2; i >= 0; i--) {    //从 从右像左数 第一个含有子结点的非叶子结点开始调整
            sink(output, i, N);
        }
        //下面开始调整堆
        int i = k;
        while (i<arr.length){
            if (arr[i]<output[0]){
                output[0] = arr[i];
                sink(output, 0, N);
            }
            i++;
        }

        SysOut.printArray(output);
    }

    /**
     * 自下向上的堆有序化【下沉】
     * @param arr   待调整的堆
     * @param k     开始调整的结点
     * @param N     结束的位置
     */
    private static void sink(int[] arr, int k, int N) {
        while (2*k < N){
            int j = 2*k + 1;
            if (j<N && arr[j]<arr[j+1]){
                j++;    //arr[j]永远指向 a[k]的大儿子
            }
            if (arr[k]>arr[j]){
                break;  //a[k]比他的大儿子还大，无需调整
            } else {
                SwapUtil.swap(arr, k, j);
                k = j;
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 6, 2, 7, 3, 8};
        int k = 4;

        SysOut.printArray(arr);
        System.out.println("k = "+k);
        getLeastNumbers2(arr, k);
        getLeastNumbers(arr, k);
    }

    /**
     * 法1
     * @param arr
     * @param k
     */
    private static void getLeastNumbers(int[] arr, int k) {
        if (arr==null || arr.length<=0 || k>arr.length){
            throw new IllegalArgumentException("Invalid args");
        }

        int index = partition(arr, 0, arr.length-1);
        while (index != k-1){
            if (index > k-1){
                index = partition(arr, 0, index-1);
            } else {
                index = partition(arr, index+1, arr.length-1);
            }
        }

        for (int i = 0; i < k; i++) {
            System.out.print(arr[i] + ", ");
        }
    }


}
