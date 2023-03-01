package cc.tianbin.algorithm_practice.LeetCode.code000;

import cn.hutool.core.lang.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Stack;

/*
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rainwatertrap.png
上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。

示例:
输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/trapping-rain-water
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
Created by nibnait on 2020-01-26
 */
public class H042_接雨水 extends TestCase {

    @Test
    public void testCase() {
        // 6
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        Assert.equals(6, trap(height));

        // 2
        int[] height2 = new int[]{2, 0, 2};
        Assert.equals(2, trap(height2));

        // 1
        int[] height3 = new int[]{4, 2, 3};
        Assert.equals(1, trap(height3));

        // 10
        int[] height4 = new int[]{4, 2, 0, 3, 2, 4, 3, 4};
        Assert.equals(10, trap(height4));

        // 6
        int[] height5 = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        Assert.equals(6, trap(height5));

        // 7
        int[] height6 = new int[]{0,7,1,4,6};
        Assert.equals(7, trap(height6));


    }

    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int len = height.length;

        int leftMax = height[0];
        int rightMax = height[len - 1];
        int p1 = 1;
        int p2 = len - 2;
        int sum = 0;
        while (p1 < p2) {
            if (leftMax >= rightMax) {
                // 右边更小
                sum += Math.max(0, rightMax - height[p2]);
                rightMax = Math.max(rightMax, height[p2--]);
            } else {
                // 左边更小
                sum += Math.max(0, leftMax - height[p1]);
                leftMax = Math.max(leftMax, height[p1++]);
            }
        }

        return sum;
    }


    /*
      单调栈结构：

     */
    public int trap2(int[] height) {
        int length = height.length;
        if (length < 3) {
            return 0;
        }

        int result = 0;

        // 单调栈里存的是索引
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int pop = stack.pop();

                if (stack.isEmpty()) {
                    break;
                }

                int currentWidth = i - stack.peek() - 1;
                int currentHeight = Math.min(height[i], height[stack.peek()]) - height[pop];

                result += currentWidth * currentHeight;
            }

            stack.push(i);
        }

        return result;
    }

}
