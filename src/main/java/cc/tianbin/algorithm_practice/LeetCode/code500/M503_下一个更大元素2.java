package cc.tianbin.algorithm_practice.LeetCode.code500;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/*
给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。

示例 1:

输入: [1,2,1]
输出: [2,-1,2]
解释: 第一个 1 的下一个更大的数是 2；
数字 2 找不到下一个更大的数；
第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
注意: 输入数组的长度不会超过 10000。



来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/next-greater-element-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M503_下一个更大元素2 {

    @Test
    public void testCase() {
        int[] nums = new int[]{1,2,1};
        int[] excepted = new int[]{2,-1,2};
        Assert.assertEquals(excepted, nextGreaterElements(nums));

        nums = new int[]{2,4};
        excepted = new int[]{1,-1};
        Assert.assertEquals(excepted, nextGreaterElements(nums));


    }

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();

        for(int i=2*n-1; i>=0; i--) {
            while(!stack.isEmpty() && stack.peek() <= nums[i%n]) {
                stack.pop();
            }

            res[i%n] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i%n]);
        }
        return res;
    }
}
