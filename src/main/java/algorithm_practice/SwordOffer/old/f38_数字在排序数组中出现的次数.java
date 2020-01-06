package algorithm_practice.SwordOffer.old;

/**
 * 题目：统计一个数字：在排序数组中出现的次数。
 * 例如输入排序数组｛ 1, 2, 3, 3, 3, 3, 4, 5｝和数字 3 ，
 *      由于 3 在这个数组中出现了 4 次，因此输出 4 。
 *
 * 【解】：
 *      既然是排好序的数组,
 *      所以我们可以直接使用两次二分查找，得到数字第一次出现和最后一次出现的下标，
 *      时间复杂度：2 * O(logN)
 *
 *
 * Created by nibnait on 2016/10/1.
 */
public class f38_数字在排序数组中出现的次数 {

    public static void main(String[] args) {
        // 查找的数字出现在数组的中间
        int[] data1 = {1, 2, 3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(data1, 3)); // 4
        // 查找的数组出现在数组的开头
        int[] data2 = {3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(data2, 3)); // 4
        // 查找的数组出现在数组的结尾
        int[] data3 = {1, 2, 3, 3, 3, 3};
        System.out.println(getNumberOfK(data3, 3)); // 4
        // 查找的数字不存在
        int[] data4 = {1, 3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(data4, 2)); // 0
        // 查找的数字比第一个数字还小，不存在
        int[] data5 = {1, 3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(data5, 0)); // 0
        // 查找的数字比最后一个数字还大，不存在
        int[] data6 = {1, 3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(data6, 0)); // 0
        // 数组中的数字从头到尾都是查找的数字
        int[] data7 = {3, 3, 3, 3};
        System.out.println(getNumberOfK(data7, 3)); // 4
        // 数组中的数字从头到尾只有一个重复的数字，不是查找的数字
        int[] data8 = {3, 3, 3, 3};
        System.out.println(getNumberOfK(data8, 4)); // 0
        // 数组中只有一个数字，是查找的数字
        int[] data9 = {3};
        System.out.println(getNumberOfK(data9, 3)); // 1
        // 数组中只有一个数字，不是查找的数字
        int[] data10 = {3};
        System.out.println(getNumberOfK(data10, 4)); // 0
    }

    private static int getNumberOfK(int[] arr, int k) {
        if (arr==null || arr.length<=0){
            return 0;
        }
        int len = arr.length;
        int first = getFirstK(arr, k, 0, len-1);
        int last = getLastK(arr, k, 0, len-1);
        int num = 0;
        if (first!=-1 && last!=-1){
            num = last-first+1;
        }
        return num;
    }

    private static int getLastK(int[] arr, int k, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }
        int mid = (lo+hi)>>1;
        int len = arr.length;
        if (k > arr[mid]){
            lo = mid + 1;
        } else if (k == arr[mid]){
            if ((mid<len-1 && arr[mid+1]>k) || mid==len-1 ){    // mid还有可能等于0
                return mid;
            } else {
                lo = mid + 1;
            }
        } else {
            hi = mid - 1;
        }
        return getLastK(arr, k, lo, hi);
    }

    private static int getFirstK(int[] arr, int k, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }
        int mid = (lo+hi)>>1;
        if (k > arr[mid]){
            lo = mid + 1;
        } else if (k == arr[mid]){
            if ((mid>0 && arr[mid-1]<k) || mid==0 ){    // mid还有可能等于0
                return mid;
            } else {
                hi = mid-1;
            }
        } else {
            hi = mid - 1;
        }
        return getFirstK(arr, k, lo, hi);
    }
}
