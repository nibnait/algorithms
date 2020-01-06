package algorithm_practice.SwordOffer.old;

import common.util.SysOut;

/**
 * 题目一：输入一个递增排序的数组和一个数字 s，在数组中查找两个数，得它们的和正好是 s。如果有多对数字的和等于 s，输出任意一对即可。
 * 例如输入数组｛1 、2 、4、7 、11 、15 ｝和数字 15。
 *  由于 4+11 = 15 ，因此输出 4 和 11 。
 *
 * 【解】：双指针
 *    两个指针：p1 = arr[0], p2 = arr[length-1]
 *    do{
 *       p1+p2 > s , p2--
 *       p1+p2 == s , return p1、p2
 *       p1+p2 < s , p1++
 *    }while(p1<p2);
 *
 *
 * Created by nibnait on 2016/10/2.
 */
public class f41_1$和为s的两个数字 {

    public static void main(String[] args) {
        int[] data1 = {1, 2, 4, 7, 11, 15};
        SysOut.printArray(findNumbersWithSum(data1, 15));
        int[] data2 = {1, 2, 4, 7, 11, 16};
        SysOut.printArray(findNumbersWithSum(data2, 17));
        int[] data3 = {1, 2, 4, 7, 11, 16};
        SysOut.printArray(findNumbersWithSum(data3, 10));
    }

    private static int[] findNumbersWithSum(int[] arr, int s) {
        if (arr==null || arr.length<2){
            throw new IllegalArgumentException("Invalid args");
        }
        int len = arr.length;
        int[] res = new int[2];
        int p1 = 0;
        int p2 = len-1;
        long sum;   //统计和，防止溢出
        while (p1 < p2){
            sum = arr[p1]+arr[p2];
            if (sum > s){
                p2--;
            } else if (sum == s){
                res[0] = arr[p1];
                res[1] = arr[p2];
                return res;
            } else {
                p1++;
            }
        }
        //没找到：
        res[0] = Integer.MIN_VALUE;
        res[1] = Integer.MIN_VALUE;
        return res;
    }


}
