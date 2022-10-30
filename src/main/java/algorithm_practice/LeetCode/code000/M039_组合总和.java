package algorithm_practice.LeetCode.code000;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/*
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。
解集不能包含重复的组合。 
示例 1：

输入：candidates = [2,3,6,7], target = 7,
所求解集为：
[
  [7],
  [2,2,3]
]
示例 2：

输入：candidates = [2,3,5], target = 8,
所求解集为：
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 

提示：

1 <= candidates.length <= 30
1 <= candidates[i] <= 200
candidate 中的每个元素都是独一无二的。
1 <= target <= 500


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combination-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M039_组合总和 {

    @Test
    public void testCase() {
        int[] candidates = new int[]{2,3,6,7};
        int target = 7;
        List<List<Integer>> excepted = Lists.newArrayList(
                Lists.newArrayList(2,2,3),
                Lists.newArrayList(7));
        Assert.assertEquals(excepted, combinationSum(candidates, target));

        candidates = new int[]{2,3,5};
        target = 8;
        excepted = Lists.newArrayList(
                Lists.newArrayList(2,2,2,2),
                Lists.newArrayList(2,3,3),
                Lists.newArrayList(3,5));
        Assert.assertEquals(excepted, combinationSum(candidates, target));

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();

        dfs(res, path, 0, candidates.length, candidates, target);
        return res;
    }

    private void dfs(List<List<Integer>> res, Deque<Integer> path, int begin, int end, int[] candidates, int target) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            res.add(new ArrayList<>(path));
        }

        for (int i = begin; i < end; i++) {
            path.addLast(candidates[i]);

            dfs(res, path, i, end, candidates, target - candidates[i]);

            path.removeLast();
        }
    }

}
