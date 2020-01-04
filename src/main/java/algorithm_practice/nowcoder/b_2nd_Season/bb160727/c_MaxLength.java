package algorithm_practice.nowcoder.b_2nd_Season.bb160727;

import common.datastruct.BinarySearch;

import java.util.HashMap;

/**
 * 未排序正数数组中累加和为给定值的最长子数组长度
 * <p>
 * Created by nibnait on 2016/9/10.
 */
public class c_MaxLength {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 1, 1, 1};
        System.out.println(getMaxLength2(arr, 3));
    }

    /**
     * @param arr   该数组无序，其中元素可正、可负、可0
     * @param k     求arr所有的子数组中累加和 <= k的最长子数组长度。
     * @return
     *
     * 时间复杂度(N* logN)
     */
    public static int getMaxLength3(int[] arr, int k) {

        int[] h = new int[arr.length];
        int sum = arr[0];
        h[0] = sum;
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
            h[i] = Math.max(sum, h[i-1]);
        }//h[]数组装载完毕

        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            pre = BinarySearch.search(h, sum - k);
            len = pre==1? 0: i-pre+1;
            res = Math.max(res, len);
        }
        return res;
    }

    /**
     * @param arr   该数组无序，其中元素可正、可负、可0
     * @param k     求arr的所有子数组中所有元素相加和 == k的最长子数组长度。
     * @return
     *
     * 时间复杂度O(N)
     *
     * 使用HashMap保存sum[i]最早出现的位置
     */
    public static int getMaxLength2(int[] arr, int k) {
        if (arr == null || arr.length < 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);     //累加和为0的子数组，最早出现在-1的位置
        int len = 0;
        int sum = 0;
        for (int i=0; i<arr.length; i++){
            sum += arr[i];
            if (map.containsKey(sum-k)){
                len = Math.max(i-map.get(sum-k), len);
            }
            if ( !map.containsKey(sum)){
                map.put(sum, i);
            }
        }

        return len;
    }

    /**
     * @param arr   该数组无序，但每个值均为正数
     * @param k     求arr的所有子数组中所有元素相加和为k的最长子数组长度。
     * @return
     *
     * 时间复杂度O(N)，额外空间复杂度O(1)
     *
     * 双指针卡位
     */
    public static int getMaxLength1(int[] arr, int k) {
        if (arr == null || arr.length < 0 || k <= 0) {
            return 0;
        }

        int L = 0, R = 1;
        int sum = arr[0];
        int len = 1;
        while (R <= arr.length) {
            if (sum < k) {
                sum += arr[R++];
            }else if (sum == k) {
                len = Math.max(len, R - L);
                sum -= arr[L++];
                if (R == arr.length){
                    break;
                }
            }else {
                sum -= arr[L++];
            }
        }
        return len;
    }
}
