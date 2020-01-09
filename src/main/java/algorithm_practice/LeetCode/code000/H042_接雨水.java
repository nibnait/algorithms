package algorithm_practice.LeetCode.code000;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
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
        System.out.println(trap(height));

        // 2
        int[] height2 = new int[]{2, 0, 2};
        System.out.println(trap(height2));

        // 1
        int[] height3 = new int[]{4, 2, 3};
        System.out.println(trap(height3));

        // 10
        int[] height4 = new int[]{4, 2, 0, 3, 2, 4, 3, 4};
        System.out.println(trap(height4));

        // 6
        int[] height5 = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height5));

        // 7
        int[] height6 = new int[]{0,7,1,4,6};
        System.out.println(trap(height6));


    }

    /*
      单调栈结构：

     */
    public int trap(int[] height) {
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
