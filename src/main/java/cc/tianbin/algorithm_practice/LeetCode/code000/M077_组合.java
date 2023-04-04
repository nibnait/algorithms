package cc.tianbin.algorithm_practice.LeetCode.code000;

import cc.tianbin.common.util.AssertUtils;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 * <p>
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * <p>
 * 示例 2：
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 * <p>
 * <p>
 * 提示：
 * 1 <= n <= 20
 * 1 <= k <= n
 * <p>
 * https://leetcode.cn/problems/combinations/
 * Created by nibnait on 2023/03/28
 */
public class M077_组合 {

    @Test
    public void testCase() {
        int n = 4;
        int k = 2;
        List<List<Integer>> result = combine(n, k);
        List<List<Integer>> expect = Lists.newArrayList(
                Lists.newArrayList(1, 2),
                Lists.newArrayList(1, 3),
                Lists.newArrayList(1, 4),
                Lists.newArrayList(2, 3),
                Lists.newArrayList(2, 4),
                Lists.newArrayList(3, 4));
        AssertUtils.compareListList(expect, result);

        n = 1;
        k = 1;
        result = combine(n, k);
        expect = Arrays.asList(Arrays.asList(1));
        AssertUtils.compareListList(expect, result);

    }

    public List<List<Integer>> combine(Integer n, Integer k){

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList();

        process(path, ans, n, k, 1);

        return ans;
    }

    private void process(List<Integer> path, List<List<Integer>> ans, int n, int k, int index) {
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i <= n; i++) {
            path.add(i);
            process(path, ans, n, k, i + 1);
            path.remove(path.size() - 1);
        }

    }

}
