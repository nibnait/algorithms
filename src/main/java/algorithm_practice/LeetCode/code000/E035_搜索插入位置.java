package algorithm_practice.LeetCode.code000;

import junit.framework.TestCase;
import org.junit.Test;

/*
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

你可以假设数组中无重复元素。

示例 1:
输入: [1,3,5,6], 5
输出: 2

示例 2:
输入: [1,3,5,6], 2
输出: 1

示例 3:
输入: [1,3,5,6], 7
输出: 4

示例 4:
输入: [1,3,5,6], 0
输出: 0

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-insert-position
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by nibnait on 2019-08-07
 */
public class E035_搜索插入位置 extends TestCase {

    @Test
    public void testCase() {
        int[] nums = {1, 3, 5, 6};
        int target = 5;     //2
        int target1 = 2;    //1
        int target2 = 7;    //4
        int target3 = 0;    //0

        System.out.println(searchInsert(nums, target));
        System.out.println(searchInsert(nums, target1));
        System.out.println(searchInsert(nums, target2));
        System.out.println(searchInsert(nums, target3));
    }

    public int searchInsert(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            //右中位数
            int mid = (lo + hi + 1) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
