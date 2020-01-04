package algorithm_practice.SwordOffer.old;

import common.util.SwapUtil;

/**
 * 题目：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字
 *  例如输入一个长度为 9 的数组｛ 1, 2, 3, 2, 2, 2, 5, 4, 2｝。
 *  由于数字 2 在数组中出现了 5 次，超过数组长度的一半，因此输出 2 。
 *
 *     此处求的数字在统计学上可以称为“中位数”，即长度为n的数组中第n/2大的数字
 *
 * 【法1】：基于快排的Partition函数思想的O（N）算法
 *     即每次选择一个基准值，如果比选中的数字大则放在基准值的右边，比基准值小则放在左边
 *     分完之后，如果基准值的下标刚好是n/2，则返回这个基准值
 *              如果下标大于n/2，则继续将左边的数字Partition
 *              否则 划分右边数组
 *
 *【法2】：O(N)
 *     换个角度思考：我们要找的是出现次数超过数组长度一半的数字，
 *             也就是说 他出现的次数比其他所有数字出现次数的总和还要多！
 *     因此：我们可以利用两个辅助变量：
 *      result = arr[i]
 *      times：此数字出现的次数
 *      如果arr[i] != 上一个数字，则将times--
 *                              当times == 0时，将result替换为当前的arr[i]，并将times=1
 *      继续往下遍历。
 *      那么要找的数字就是最后一次把次数设为1时，对应的数字
 *
 *
 * Created by nibnait on 2016/9/27.
 */
public class e29_数组中出现次数超过一半的数字 {

    /**
     * 法2：
     * @param arr
     * @return
     */
    private static int MoreThanHalfNum2(int[] arr) {
        if (arr==null || arr.length<=0){
            throw new RuntimeException("args should not be null or empty");
        }
        int result = arr[0];
        int times = 1;
        for (int i = 1; i < arr.length; i++) {
            if (result == arr[i]){
                times++;
            } else {
                if (times == 0){
                    result = arr[i];
                    times = 1;
                } else {
                    times--;
                }
            }
        }
        if (!checkMoreThanHalf(arr, result)){
            throw new RuntimeException("not exist");
        }
        return result;
    }

    /**
     * 法1：
     * @param arr
     * @return
     */
    private static int MoreThanHalfNum(int[] arr) {

        if (arr==null || arr.length<=0){
            throw new IllegalArgumentException("args should not be null or empty");
        }
        int length = arr.length;
        int mid = length/2;
        int index = partition(arr, 0, length-1);
        while (index != mid){
            if (index > mid){
                index = partition(arr, 0, index-1);
            }else {
                index = partition(arr, index+1, length-1);
            }
        }
        int result = arr[mid];
        if (!checkMoreThanHalf(arr, result)){
            throw new RuntimeException("not exist");
        }
        return result;
    }

    private static boolean checkMoreThanHalf(int[] arr, int num) {
        int times = 0;
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            if (num == arr[i]){
                times++;
            }
        }
        if (times * 2 <= length){
            return false;
        }
        return true;
    }

    public static int partition(int[] arr, int lo, int hi) {
        if (arr==null || arr.length<=0 || lo<0 || hi>=arr.length){
            throw new RuntimeException("输入有误");
        }
        int flag = lo;
        //以arr[hi]为基准 划分
        for (int i = lo; i < hi; i++) {
            if (arr[i] <= arr[hi]){
                SwapUtil.swap(arr, i, flag++);
            }
        }
        SwapUtil.swap(arr, hi, flag);
        return flag;
    }


    public static void main(String[] args) {
        // 存在出现次数超过数组长度一半的数字
        int numbers[] = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(MoreThanHalfNum2(numbers));
        // 出现次数超过数组长度一半的数字都出现在数组的前半部分
        int numbers2[] = {2, 2, 2, 2, 2, 1, 3, 4, 5};
        System.out.println(MoreThanHalfNum2(numbers2));
        // 出现次数超过数组长度一半的数字都出现在数组的后半部分
        int numbers3[] = {1, 3, 4, 5, 2, 2, 2, 2, 2};
        System.out.println(MoreThanHalfNum2(numbers3));
        // 只有一个数
        int numbers4[] = {1};
        System.out.println(MoreThanHalfNum2(numbers4));
        // 输入空指针
//        MoreThanHalfNum(null);
        // 不存在出现次数超过数组长度一半的数字
        int numbers5[] = {1, 2, 3, 2, 4, 2, 5, 2, 3};
//        MoreThanHalfNum(numbers5);
    }

}
