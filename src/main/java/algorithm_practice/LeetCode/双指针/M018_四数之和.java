package algorithm_practice.LeetCode.双指针;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

/*
给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
注意：答案中不可以包含重复的四元组。

示例：
给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
满足要求的四元组集合为：
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/4sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M018_四数之和 extends TestCase {

    @Test
    public void testCase() {

        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        int target = 0;

        System.out.println(fourSum(nums, target));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {

        return null;
    }
}
