package algorithm_practice.LeetCode.code000;

import common.util.SysOut;
import junit.framework.TestCase;
import org.junit.Test;

/*
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 O(log n) 级别。

示例 1:

输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4
示例 2:

输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
Created by nibnait on 2020-01-09
 */
public class M033_搜索旋转排序数组 extends TestCase {

    @Test
    public void testCase() {
        int[] nums1 = new int[]{4, 5, 6, 7, 0, 1, 2};
        int target11 = 0;
        System.out.println(search(nums1, target11));
        int target12 = 3;
        System.out.println(search(nums1, target12));

        SysOut.printSeparator();
        int[] nums2 = new int[]{};
        int target21 = 0;
        System.out.println(search(nums2, target21));

        SysOut.printSeparator();
        int[] nums3 = new int[]{1};
        int target31 = 2;
        System.out.println(search(nums3, target31));

        SysOut.printSeparator();
        int[] nums4 = new int[]{3, 5, 1};
        int target41 = 2;
        System.out.println(search(nums4, target41));
        int target42 = 3;
        System.out.println(search(nums4, target42));
        int target43 = 5;
        System.out.println(search(nums4, target43));

        SysOut.printSeparator();
        int[] nums5 = new int[]{5, 1, 3};
        int target51 = 5;
        System.out.println(search(nums5, target51));
        int target52 = 1;
        System.out.println(search(nums5, target52));
        int target53 = 3;
        System.out.println(search(nums5, target53));
        int target54 = 4;
        System.out.println(search(nums5, target54));


    }

    private static final int NOT_FOUND = -1;

    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return NOT_FOUND;
        }

        int[] sortedNums = new int[nums.length];
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] < nums[i - 1]) {
                index = i;
                break;
            }
        }

        int startIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i + index < nums.length) {
                sortedNums[i] = nums[i + index];
            } else {
                sortedNums[i] = nums[startIndex++];
            }
        }

        int targetIndex = halfSearch(sortedNums, target, 0, nums.length - 1);

        return getIndex(index, targetIndex, nums.length);
    }

    private int getIndex(int index, int targetIndex, int length) {
        if (targetIndex == NOT_FOUND) {
            return NOT_FOUND;
        }

        return (index + targetIndex) % length;
    }

    private int halfSearch(int[] nums, int target, int left, int right) {
        if (left > right) {
            return NOT_FOUND;
        }

        int mid = (left + right) / 2;
        if (nums[mid] > target) {
            return halfSearch(nums, target, left, mid - 1);
        } else if (nums[mid] == target) {
            return mid;
        } else {
            return halfSearch(nums, target, mid + 1, right);
        }
    }
}