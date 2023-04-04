package cc.tianbin.algorithm_practice.LeetCode.code700;

import cc.tianbin.common.util.AssertUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 *
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 *
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *
 *
 * 提示：
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 *
 * https://leetcode.cn/problems/daily-temperatures
 * Created by nibnait on 2023/03/26
 */
public class M739_每日温度 {

    @Test
    public void test() {
        int[] temperatures = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures(temperatures);
        int[] expect = new int[]{1, 1, 4, 2, 1, 1, 0, 0};
        AssertUtils.compareIntegerArray(expect, result);

        temperatures = new int[]{30, 40, 50, 60};
        result = dailyTemperatures(temperatures);
        expect = new int[]{1, 1, 1, 0};
        AssertUtils.compareIntegerArray(expect, result);

        temperatures = new int[]{30, 60, 90};
        result = dailyTemperatures(temperatures);
        expect = new int[]{1, 1, 0};
        AssertUtils.compareIntegerArray(expect, result);
    }


    public int[] dailyTemperatures(int[] temperatures) {

        Stack<List<Integer>> stack = new Stack<>();
        int[] ans = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek().get(0)] < temperatures[i]) {
                List<Integer> popList = stack.pop();
                for (Integer index : popList) {
                    ans[index] = i - index;
                }
            }

            if (!stack.isEmpty() && temperatures[stack.peek().get(0)] == temperatures[i]) {
                stack.peek().add(i);
            } else {
                // temperatures[stack.peek().get(0)] > temperatures[i]
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }

        return ans;
    }
}
