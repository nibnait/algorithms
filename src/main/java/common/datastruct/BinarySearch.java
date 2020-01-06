package common.datastruct;

/**
 * Created by nibnait on 2016/10/2.
 */
public class BinarySearch {
    /**
     * 二分查找
     * @param arr
     * @param num
     * @return
     */
    public static int search(int[] arr, int num) {
        int lo = 0;
        int hi = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (lo <= hi) {
            mid = (lo + hi) >> 1;    //防止溢出，移位也更高效。
            if (arr[mid] >= num) {
                res = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return res;
    }

}
