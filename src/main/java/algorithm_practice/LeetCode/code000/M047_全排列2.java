package algorithm_practice.LeetCode.code000;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/*
给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:

输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/permutations-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M047_全排列2 {

    @Test
    public void testCase() {
        int[] nums = new int[]{1,1,2};
        List<List<Integer>> excepted = Lists.newArrayList(
                Lists.newArrayList(1,1,2),
                Lists.newArrayList(1,2,1),
                Lists.newArrayList(2,1,1));
        List<List<Integer>> result = permuteUnique(nums);
        Assert.assertEquals(excepted, result);


    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[nums.length];

        return backTrack(result, path, nums, used);
    }

    private List<List<Integer>> backTrack(List<List<Integer>> result, Deque<Integer> path, int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            if (!result.contains(path)) {
                result.add(new ArrayList(path));
            }
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            used[i] = true;
            path.add(nums[i]);

            backTrack(result, path, nums, used);

            used[i] = false;
            path.remove(path.size() - 1);
        }

        return result;
    }

}
