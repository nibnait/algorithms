package algorithm_practice.LeetCode.code000;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
给定一个 没有重复 数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/permutations
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M046_全排列 {

    @Test
    public void testCase() {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> excepted = Lists.newArrayList(
                                            Lists.newArrayList(1,2,3),
                                            Lists.newArrayList(1,3,2),
                                            Lists.newArrayList(2,1,3),
                                            Lists.newArrayList(2,3,1),
                                            Lists.newArrayList(3,1,2),
                                            Lists.newArrayList(3,2,1));
        List<List<Integer>> result = permute(nums);
        Assert.assertEquals(excepted, result);

    }

    /*
    回溯框架

    result = []

    def backtrack(路径，选择列表) {
        if (满足借宿条件) {
            result.add(选择);
        }

        for 选择 in 选择列表:
            if 选择不满足当前选择条件:
                continue;

            做选择;
            backtrace(路径, 选择列表);
            撤销选择;

    }

     */

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        return backTrack(result, path, nums);
    }

    private List<List<Integer>> backTrack(List<List<Integer>> result, List<Integer> path, int[]nums) {
        if (path.size() == nums.length) {
            result.add(newArrayList(path.iterator()));
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (path.contains(num)) {
                continue;
            }

            path.add(num);

            backTrack(result, path, nums);

            path.remove(path.size()-1);
        }

        return result;
    }

    private List<Integer> newArrayList(Iterator<Integer> iterator) {
        ArrayList list = new ArrayList<>();

        while(iterator.hasNext()) {
            list.add(iterator.next());
        }

        return list;
    }

}
