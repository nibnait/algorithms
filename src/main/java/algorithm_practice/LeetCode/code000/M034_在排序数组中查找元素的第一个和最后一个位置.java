package algorithm_practice.LeetCode.code000;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.junit.Test;

/*
给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
你的算法时间复杂度必须是 O(log n) 级别。
如果数组中不存在目标值，返回 [-1, -1]。

示例 1:
输入: nums = [5,7,7,8,8,10], target = 8
输出: [3,4]

示例 2:
输入: nums = [5,7,7,8,8,10], target = 6
输出: [-1,-1]


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by nibnait on 2019-08-07
 */
public class M034_在排序数组中查找元素的第一个和最后一个位置 extends TestCase {

    @Test
    public void testCase() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int target1 = 6;

        System.out.println(JSON.toJSONString(searchRange(nums, target)));
        System.out.println(JSON.toJSONString(searchRange(nums, target1)));

        int[] nums2 = {2, 2};
        int target2 = 2;
        System.out.println(JSON.toJSONString(searchRange(nums2, target2)));
    }

    public int[] searchRange(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        int[] result = {-1, -1};

        while (lo <= hi) {
            int mid = (lo + hi + 1) / 2;
            if (nums[mid] == target) {
                result[0] = getFirstIndex(nums, mid, target);
                result[1] = getLastIndex(nums, mid, target);
                return result;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return result;
    }

    private int getFirstIndex(int[] nums, int mid, int target) {
        int i = mid - 1;
        for (; i >= 0; i--) {
            if (nums[i] != target) {
                break;
            }
        }
        return i + 1;
    }

    private int getLastIndex(int[] nums, int mid, int target) {
        int i = mid + 1;
        for (; i < nums.length; i++) {
            if (nums[i] != target) {
                break;
            }
        }
        return i - 1;
    }
}
