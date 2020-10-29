package algorithm_practice.LeetCode.code200;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

说明：

所有数字都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: k = 3, n = 7
输出: [[1,2,4]]
示例 2:

输入: k = 3, n = 9
输出: [[1,2,6], [1,3,5], [2,3,4]]


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combination-sum-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M216_组合总和3 {

    @Test
    public void testCase() {
        int k = 3;
        int n = 9;
        List<List<Integer>> excepted = Lists.newArrayList(
                Lists.newArrayList(1,2,6),
                Lists.newArrayList(1,3,5),
                Lists.newArrayList(2,3,4));
        Assert.assertEquals(excepted, combinationSum3(k, n));

    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();

        dfs(res, path, 1, 9, n, k);
        return res;
    }

    private void dfs(List<List<Integer>> res, Deque<Integer> path, int begin, int end, int target, int k) {
        if (target < 0) {
            return;
        }

        if (target == 0 && path.size() == k) {
            res.add(new ArrayList<>(path));
        }

        for (int i = begin; i <= end; i++) {
            path.addLast(i);
            dfs(res, path, i+1, end, target-i, k);
            path.removeLast();
        }
    }
}
