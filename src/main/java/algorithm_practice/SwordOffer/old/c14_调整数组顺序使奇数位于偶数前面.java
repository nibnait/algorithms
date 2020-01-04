package algorithm_practice.SwordOffer.old;

import common.util.SwapUtil;
import common.util.SysOut;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位予数组的后半部分。
 *
 *  O(N^2)的解法：
 *    插入排序的变形：将交换的判断条件改为（arr[j-1]为偶数 && arr[j]为奇数）
 *  O(N)的解法：
 *    两个指针：p1 = arr[0], p2 = arr[length-1]
 *      while(p1<=p2){
 *          p1往右走，遇到偶数即停
 *          p2往左走，遇到奇数即停
 *          swap(arr, p1, p2);
 *      }
 *   【注意】
 *      为了增加程序的鲁棒性：
 *          可将p1、p2的判断条件 写成一个独立的函数，
 *          这样前置的就不仅可以是奇数、也可以将非负数放在前面、将所有能被3整除的数放在前面...
 *
 * Created by nibnait on 2016/9/21.
 */
public class c14_调整数组顺序使奇数位于偶数前面 {

    //O(N)
    public static void Reorder(int[] arr) {
        if (arr==null || arr.length<=1){
            return;
        }

        int p1 = -1;
        int p2 = arr.length;
        while (p1 < p2){
            while (p1 < p2 && flag(arr[++p1])) {
                ;
            }
            while (p1 < p2 && !flag(arr[--p2])) {
                ;
            }
            SwapUtil.swap(arr, p1, p2);
        }
    }

    //O(N^2)
    private static void reorderOddEvenFromInsertSort(int[] arr) {

        if (arr==null || arr.length<=1){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j >0 && !flag(arr[j-1]) && flag(arr[j]); j--) {
                SwapUtil.swap(arr, j-1, j);
            }
        }
    }

    /**
     * 判断条件：i是奇数
     * @param i
     * @return
     */

    private static boolean flag(int i) {
        if (i % 2 ==0){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 5};
        SysOut.printArray(arr);
        reorderOddEvenFromInsertSort(arr);
        SysOut.printArray(arr);
        Reorder(arr);
        SysOut.printArray(arr);
    }

}
