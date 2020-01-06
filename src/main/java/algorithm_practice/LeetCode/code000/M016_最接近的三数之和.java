package algorithm_practice.LeetCode.code000;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

/*
给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/3sum-closest
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M016_最接近的三数之和 extends TestCase {

    @Test
    public void testCase() {
        int[] nums = new int[]{-1, 2, 1, -4};
        int target = 1;

        System.out.println(threeSumClosest(nums, target));
    }

    /*
        1. 排序
        2. p1 = 0不动
        p2 = 1, p3 = length-1
        记录最小的distance，返回
     */
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) {
            throw new RuntimeException("nums长度<3");
        }
        Arrays.sort(nums);

        int distance = 0;
        int p1 = 0, p2 = 1, p3 = nums.length-1;
        int result = nums[p1] + nums[p2] + nums[p3];
        for (int i = 0; i < nums.length; i++) {
            p1 = i; p2 = i + 1; p3 = nums.length - 1;
            while (p2 != p3 && p2 < nums.length && p3 > i+1) {
                distance = nums[p1] + nums[p2] + nums[p3] - target;
                if (Math.abs(result - target) > Math.abs(distance)) {
                    result = target + distance;
                }
                if (distance < 0) {
                    p2++;
                } else {
                    p3--;
                }
            }
        }

        return result;
    }
}
