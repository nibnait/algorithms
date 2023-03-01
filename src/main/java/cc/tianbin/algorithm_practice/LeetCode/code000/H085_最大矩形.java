package cc.tianbin.algorithm_practice.LeetCode.code000;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/*
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],
 *                ["1","0","1","1","1"],
 *                ["1","1","1","1","1"],
 *                ["1","0","0","1","0"]]
 * 输出：6
 *
 * 示例 2：
 * 输入：matrix = []
 * 输出：0
 *
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 *
 * 示例 4：
 * 输入：matrix = [["1"]]
 * 输出：1
 *
 * 示例 5：
 * 输入：matrix = [["0","0"]]
 * 输出：0
 *
 * https://leetcode.cn/problems/maximal-rectangle/?favorite=2cktkvj
 */
public class H085_最大矩形 {


    @Test
    public void test() {
        char[][] matrix = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'0', '0', '0', '1', '0'},
        };
        int ans = maximalRectangle(matrix);
        Assert.assertEquals(6, ans);

        matrix = new char[][]{};
        ans = maximalRectangle(matrix);
        Assert.assertEquals(0, ans);

        matrix = new char[][]{{'0'}};
        ans = maximalRectangle(matrix);
        Assert.assertEquals(0, ans);

        matrix = new char[][]{{'1'}};
        ans = maximalRectangle(matrix);
        Assert.assertEquals(1, ans);

        matrix = new char[][]{{'0', '0'}};
        ans = maximalRectangle(matrix);
        Assert.assertEquals(0, ans);

    }

    /**
     * 跟上一题一样。单调栈。
     * 1. 求出第1层的最大矩形
     * 2. 再求第2层的最大矩形，遍历第2层的时候，遇到0则直接高度归0，非0则高度累加
     * 3. 重复步骤2，遍历3、4、5... 层，
     * 4. 循环结束。得到最大的矩形面积
     *
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int[][] m = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                m[i][j] = matrix[i][j] == '0' ? 0 : 1;
            }
        }
        int maxArea = 0;
        for (int i = 0; i < m.length; i++) {
            if (i >= 1) {
                for (int k = 0; k < m[0].length; k++) {
                    m[i][k] = m[i][k] == 0 ? 0 : m[i-1][k] + m[i][k];
                }
            }

            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < m[0].length; j++) {
                while (!stack.isEmpty() && m[i][j] <= m[i][stack.peek()]) {
                    int pop = stack.pop();
                    int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                    int curArea = m[i][pop] * (j - leftIndex - 1);
                    maxArea = Math.max(maxArea, curArea);
                }

                stack.push(j);
            }

            while (!stack.isEmpty()) {
                int pop = stack.pop();
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                int curArea = m[i][pop] * (m[0].length - leftIndex - 1);
                maxArea = Math.max(maxArea, curArea);
            }
        }

        return maxArea;
    }



}
