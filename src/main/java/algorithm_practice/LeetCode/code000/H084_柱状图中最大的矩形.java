package algorithm_practice.LeetCode.code000;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 *
 * 输入： heights = [2,4]
 * 输出： 4
 *
 * https://leetcode.cn/problems/largest-rectangle-in-histogram/
 * Created by nibnait on 2022/11/16
 */
public class H084_柱状图中最大的矩形 {

    @Test
    public void test() {
        int[] heights = new int[]{2,1,5,6,2,3};
        int ans = largestRectangleArea(heights);
        Assert.assertEquals(10, ans);

        heights = new int[]{2,4};
        ans = largestRectangleArea(heights);
        Assert.assertEquals(4, ans);
    }

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - k - 1) * heights[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (heights.length - k - 1) * heights[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

}
