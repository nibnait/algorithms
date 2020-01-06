package algorithm_practice.SwordOffer.old;

import common.util.SwapUtil;

/**
 * 题目：在一个长度为n的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，
 * 那么对应的输出是重复的数字 2 或者 3。
 *
 * 【法1】：一言不合先排序，排序之后双指针往后跑，直到找到相同的数字，return
 *      时间复杂度：O（N*logN）+ O（N）
 * 【法2】：用HashMap，遍历一下数组即可
 *      时间复杂度：O（N）+ N*O（1）
 *      空间复杂度：O（N）
 * 【法3】：依照题意，数组中的数字都在[0,n-1]的范围内，
 *      所以如果没有重复数字的话，每个数字就应该都在他对应的下标下面
 *      但是有重复的数字，数字还不是有序的：
 *          我们可以依次遍历整个数组，当遍历到第i个数字时，
 *          arr[i] == i, i++
 *          while(arr[i] != i){ //直到i这个位置插上了i，或者找到了一对重复的数字
 *              arr[arr[i]] == arr[i], 找到了重复的数字
 *              arr[arr[i]] != i, swap(arr[arr[i]], arr[i])
 *          }
 *
 *       时间复杂度：O（N），数组中的每个元素只需遍历或交换一遍，即可找到那个重复的数字
 *       空间复杂度：O（1）
 *
 * Created by nibnait on 2016/10/3.
 */
public class h51_数组中重复的数字 {

    public static void main(String[] args) {
        int[] numbers = {2, 3, 1, 4, 5, 1, 3};
        System.out.println(duplicate(numbers));
//        int[] numbers1 = {2, 1, 3, 1, 4};
//        System.out.println(duplicate(numbers1));
//        int[] numbers2 = {2, 4, 3, 1, 4};
//        System.out.println(duplicate(numbers2));
//        int[] numbers3 = {2, 4, 2, 1, 4};
//        System.out.println(duplicate(numbers3));
//        int[] numbers4 = {2, 1, 3, 0, 4};
//        System.out.println(duplicate(numbers4));
//        int[] numbers5 = {2, 1, 3, 5, 4};
//        System.out.println(duplicate(numbers5));
    }

    private static int duplicate(int[] arr) {
        if (arr==null || arr.length<=0){
            throw new IllegalArgumentException("args should not be null or empty");
        }
        int len = arr.length;
        for(int i: arr){
            if (i<0 || i>len-1){
                throw new IllegalArgumentException("Invalid args");
            }
        }

        for (int i = 0; i < len; i++) {
            while (arr[i] != i){
                if (arr[arr[i]] == arr[i]){
                    return arr[i];
                } else {
                    SwapUtil.swap(arr, arr[i], i);
                }
            }
        }

        return -1;
    }


}
