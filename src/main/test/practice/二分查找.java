package practice;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by nibnait on 2019-08-23
 */
public class 二分查找 extends TestCase {

    @Test
    public void testM() {
        int[] a = {1, 3, 4, 5};
        System.out.println(serch(a, 3));
        System.out.println(serch(a, 1));
    }

    public int serch(int[] a, int target) {
        int NOT_FOUND = -1;
        if (a == null || a.length < 1) {
            return NOT_FOUND;
        }
        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            int mid = (left + right + 1) / 2;
            if (a[mid] == target) {
                return mid;
            } else if (a[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return NOT_FOUND;
    }

}
