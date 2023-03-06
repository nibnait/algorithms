package cc.tianbin.algorithm_practice.LeetCode.code000;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/*
给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

所有数字（包括目标数）都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
示例 2:

输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combination-sum-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M040_组合总和2 {

    @Test
    public void testCase() {
        int[] candidates = new int[]{10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> excepted = Lists.newArrayList(
                Lists.newArrayList(1, 2, 5),
                Lists.newArrayList(1,7),
                Lists.newArrayList(2, 6),
                Lists.newArrayList(1, 1, 6));
        Assert.assertEquals(excepted, combinationSum2(candidates, target));

        candidates = new int[]{2,5,2,1,2};
        target = 5;
        excepted = Lists.newArrayList(
                Lists.newArrayList(1,2,2),
                Lists.newArrayList(5));
        Assert.assertEquals(excepted, combinationSum2(candidates, target));

    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();

        // 先排序，为了方便后面好判断是否真正只用了1次 candidates 里面的数字
        Arrays.sort(candidates);

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

            // candidates[i] 不是begin位置的第一个数字
            // 且 上一个candidates[i-1] 如果与当前的 candidates[i]相等，说明与同一层的选择重复，需剪枝。
            if (i>begin && candidates[i-1] == candidates[i]) {
                continue;
            }

            path.addLast(candidates[i]);
            dfs(res, path, i+1, end, candidates, target-candidates[i]);
            path.removeLast();
        }

    }

    /**
     * 耗时有点高。。
     * 每次都有重新遍历 res，重新排序。判断是否contains
     */
    private void dfs_V1(List<List<Integer>> res, Deque<Integer> path, int begin, int end, int[] candidates, int target) {
        if (target < 0) {
            return;
        }

        if (target == 0 && !contains(res, path)) {
            res.add(new ArrayList<>(path));
        }

        for (int i = begin; i < end; i++) {

            path.addLast(candidates[i]);

            dfs_V1(res, path, i+1, end, candidates, target - candidates[i]);

            path.removeLast();
        }
    }

    private boolean contains(List<List<Integer>> res, Deque<Integer> path) {
        List<List<Integer>> sortedRes = new ArrayList<>();
        for (List<Integer> oldPath : res) {
            sortedRes.add(oldPath.stream().sorted().collect(Collectors.toList()));
        }

//        res = sortedRes;
        return sortedRes.contains(path.stream().sorted().collect(Collectors.toList()));
    }

}
