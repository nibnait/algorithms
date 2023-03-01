package algorithm_practice.LeetCode.code000;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Stack;

/**
 * 给定 n 个非负整数,用来表示柱状图中各个柱子的高度。每个柱子彼此相邻,且宽度为 1 。
 * 求在该柱状图中,能够勾勒出来的矩形的最大面积。
 * <p>
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域,面积为 10
 * <p>
 * 输入： heights = [2,4]
 * 输出： 4
 * <p>
 * https://leetcode.cn/problems/largest-rectangle-in-histogram/
 * Created by nibnait on 2022/11/16
 */
public class H084_柱状图中最大的矩形 {

    @Test
    public void test() {
        int[] heights = new int[]{2, 1, 5, 6, 2, 3};
        int ans = largestRectangleArea(heights);
        Assert.assertEquals(10, ans);

        heights = new int[]{2, 4};
        ans = largestRectangleArea(heights);
        Assert.assertEquals(4, ans);

        heights = new int[]{1, 1};
        ans = largestRectangleArea(heights);
        Assert.assertEquals(2, ans);
        
        heights = new int[]{1, 2, 3, 3, 4, 3, 3, 5, 1};
        ans = largestRectangleArea(heights);
        Assert.assertEquals(18, ans);
    }

    /**
     * 法1：
     * 1. 单调栈,找到每个元素,最近的 左、右 两边的第一个比他小的index
     * 2. 再遍历一遍数组,求以每个元素为高时,其对应的矩形的面积
     */
    public int largestRectangleArea1(int[] heights) {

        int[][] nearestSmallIndex = nearestSmallIndex(heights);
        int[] nearLeftSmall = new int[heights.length];
        int[] nearRightSmall = new int[heights.length];
        for (int i = 0; i < nearestSmallIndex.length; i++) {
            nearLeftSmall[i] = nearestSmallIndex[i][0];
            nearRightSmall[i] = nearestSmallIndex[i][1];
        }

        int maxArea = heights[0] * getWidth(-1, nearRightSmall[0], heights.length);
        for (int i = 1; i < heights.length; i++) {
            maxArea = Math.max(maxArea, heights[i] * getWidth(nearLeftSmall[i], nearRightSmall[i], heights.length));
        }
        return maxArea;
    }

    private int getWidth(int left, int right, int length) {
        if (left == -1 && right == -1) {
            return length;
        }
        if (left == -1) {
            return right;
        }
        if (right == -1) {
            return length - left - 1;
        }
        return right - left - 1;
    }

    private int[][] nearestSmallIndex(int[] heights) {
        int[][] result = new int[heights.length][2];
        Stack<List<Integer>> stack = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek().get(0)] > heights[i]) {
                List<Integer> popIndexs = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer popIndex : popIndexs) {
                    result[popIndex][0] = leftLessIndex;
                    result[popIndex][1] = i;
                }
            }

            if (!stack.isEmpty() && heights[stack.peek().get(0)] == heights[i]) {
                stack.peek().add(i);
            } else {
                stack.add(Lists.newArrayList(i));
            }
        }

        while (!stack.isEmpty()) {
            List<Integer> popIndexs = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer popIndex : popIndexs) {
                result[popIndex][0] = leftLessIndex;
                result[popIndex][1] = -1;
            }
        }

        return result;
    }

    /**
     * 法2：
     *
     */
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
