package algorithm_practice.nowcoder.b_2nd_Season.ba160720;

/**
 * 求最长递增子序列的长度
 * 自然想法：
 * 求出以数组中每一位置结尾的最长递增子序列，
 * 即：利用一个辅助数组h[]， h(i)：表示当必须以arr的第i号元素结尾的情况下 最长递增子序列的长度的值。
 * 但是这样，你每求一个位置的h(i)都要先遍历一遍arr中他前面的值，都要找到 【比cur(当前值)小的arr[i] 对应的h[i]中最大那个长度 】 然后再加1.，简称：【枚举过程】
 * 时间复杂度：O(N^2)
 * <p>
 * 得分方法：
 * 时间复杂度：O(N*log(N) )
 * 加速枚举过程，
 * 重新定义辅助数组h[]，h(i)：代表当前遍历到cur这个数时，有效区中，h(i)遍历到 当前时刻为止，长度为i+1的最长递增子序列的**最小末尾**
 * 这样【因为h[]是有序的】，所以当遍历到cur的时候，cur应该放的位置 可以用二分查找 直接找到！
 * <p>
 * Created by nibnait on 2016/9/9.
 */
public class arrUp {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 6, 4, 5, 2, 7, 4};
        System.out.println(arrUp(arr));
    }

    public static int arrUp(int[] arr) {

        int[] h = new int[arr.length];
        int j = 0;      //有效区的范围
        h[j] = arr[0];  //h[0]表示 当遍历到arr[0]的时候，长度为1的最长递增子序列的最小末尾：就是arr[0]他自己么
        for (int i = 1; i < arr.length; i++) {
            int lo = 0;
            int hi = j;
            while (lo <= hi) {  //因为h[]是有序的，所以可以利用二分查找，时间复杂度：O(logN)
                int mid = (lo + hi) / 2;
                if (h[mid] < arr[i]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            h[lo] = arr[i];     //将arr[i]放到他应该放的位置上

            j = lo>j? lo: j;
        }
        return j+1;
    }

}
