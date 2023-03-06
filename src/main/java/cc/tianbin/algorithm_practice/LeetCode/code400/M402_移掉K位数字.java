package cc.tianbin.algorithm_practice.LeetCode.code400;

import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * <p>
 * 注意:
 * <p>
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 * <p>
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 * <p>
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 * <p>
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-k-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/18
 */
public class M402_移掉K位数字 {

    @Test
    public void testCase() {
        String num = "1432219";
        int k = 3;
        String excepted = "1219";
        Assert.assertEquals(excepted, removeKdigits(num, k));

        num = "10200";
        k = 1;
        excepted = "200";
        Assert.assertEquals(excepted, removeKdigits(num, k));

        num = "10";
        k = 2;
        excepted = "0";
        Assert.assertEquals(excepted, removeKdigits(num, k));

        num = "112";
        k = 1;
        excepted = "11";
        Assert.assertEquals(excepted, removeKdigits(num, k));

        num = "1234567890";
        k = 9;
        excepted = "0";
        Assert.assertEquals(excepted, removeKdigits(num, k));

    }

    /**
     * 单调栈
     */
    public String removeKdigits(String num, int k) {
        Deque<Character> queue = new LinkedList<>();
        int length = num.length();

        for (int i = 0; i < length; i++) {
            char digit = num.charAt(i);

            while (!queue.isEmpty() && k>0 && digit < queue.peekLast()) {
                queue.pollLast();
                k--;
            }

            queue.add(digit);
        }

        for (int i = 0; i < k; i++) {
            queue.pollLast();
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character digit = queue.pollFirst();
            if (!isFistZero(sb, digit)) {
                sb.append(digit);
            }
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    private boolean isFistZero(StringBuilder sb, char c) {
        return sb.length() == 0 && c == '0';
    }

}
