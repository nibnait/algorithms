package algorithm_practice.LeetCode.双指针;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

/*
给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
注意：答案中不可以包含重复的三元组。

例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/3sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M015_三数之和 extends TestCase {

    @Test
    public void testCase() {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        int[] nums2 = new int[]{-2,0,0,2,2};

        System.out.println(threeSum(nums));

    }

    /*
        先排序
        三指针
        p1 = 0, 然后就简化为了两数之和问题

     */
    private List<List<Integer>> threeSum(int[] nums) {

        return null;
    }
}
