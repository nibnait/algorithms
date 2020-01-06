package algorithm_practice.LeetCode.code000;

import junit.framework.TestCase;
import org.junit.Test;

/*
给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
你可以假设 nums1 和 nums2 不会同时为空。

示例 1:
nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0

示例 2:
nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class H004_寻找两个有序数组的中位数 extends TestCase {

    @Test
    public void testCase() {
//        int[] nums1 = new int[]{};
//        int[] nums2 = new int[]{2, 3};

        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};

//        int[] nums1 = new int[]{1, 2};
//        int[] nums2 = new int[]{3, 4};

        System.out.println(findMedianSortedArrays(nums2, nums1));
        System.out.println(Standard_findMedianSortedArrays(nums2, nums1));
    }

    /**
     *
     * @param nums1
     * @param nums2
     * @return
     */
    private double Standard_findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {    //确保 A.length <= B.length
            int[] temp = nums1; nums1 = nums2; nums2 = temp;
        }
        A = nums1; B = nums2;
        m = A.length; n = B.length;

        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;

        return getMedian(iMin, iMax);
    }

    private int[] A = new int[]{};
    private int[] B = new int[]{};
    private int m = 0;
    private int n = 0;
    private double getMedian(int iMin, int iMax) {
        if (iMin > iMax) {
            return 0.0;
        }
        int i = (iMin + iMax) / 2;
        int j = (m + n + 1) / 2 - i;
        if (i < iMax && A[i] < B[j - 1]) {
            // i 太小了
            return getMedian(i + 1, iMax);
        } else if (i > iMin && A[i - 1] > B[j]) {
            return getMedian(iMin, iMax - 1);
        } else {
            double maxLeft = 0;
            if (i == 0) {
                maxLeft = B[j - 1]; //此时A[i-1]不存在
            } else if (j == 0) {
                maxLeft = A[i - 1];  //此时B[j-1]不存在
            } else {
                maxLeft = Math.max(A[i - 1], B[j - 1]);
            }
            if ((m + n) % 2 == 1) {
                return maxLeft; //奇数个
            }

            double minRight = 0;
            if (i == m) {
                minRight = B[j];    //此时A[i] 不存在
            } else if (j == n) {
                minRight = A[i]; //此时B[j] 不存在
            } else {
                minRight = Math.min(A[i], B[j]);
            }

            return (maxLeft + minRight) / 2;
        }
    }


    /**
     * 偶数个，median = (index为length/2 + length/2 - 1)/2
     * 奇数个，median = index为【length/2】
     *
     * @param nums1
     * @param nums2
     * @return
     */
    private double findMedianSortedArrays(int[] nums1, int[] nums2) {

        double result = 0;
        int length = nums1.length + nums2.length;
        int countAmount = length / 2 - isODD(length);

        int i = 0, j = 0;
        int count = 0;
        while (count < countAmount) {
            if (getValue(i, nums1) < getValue(j, nums2)) {
                i++;
            } else {
                j++;
            }
            count++;
        }

        if (length % 2 == 0) {
            //偶数个
            double midian1 = 0;
            if (getValue(i, nums1) < getValue(j, nums2)) {
                midian1 = nums1[i];
                i++;
            } else {
                midian1 = nums2[j];
                j++;
            }

            double midian2 = Math.min(getValue(i, nums1), getValue(j, nums2));
            result = (midian1 + midian2) / 2;
        } else {
            result = Math.min(getValue(i, nums1), getValue(j, nums2));
        }
        return result;
    }

    private int isODD(int length) {
        //length是奇数返回0，偶数返回1
        return length % 2 == 0 ? 1 : 0;
    }

    private int getValue(int i, int[] nums) {
        if (i < nums.length) {
            return nums[i];
        } else {
            return Integer.MAX_VALUE;
        }
    }
}
