package algorithm_practice.LeetCode.code000;

import java.util.Stack;

public class H032_最长有效括号 {

  /*
给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

示例 1:

输入: "(()"
输出: 2
解释: 最长有效括号子串为 "()"
示例 2:

输入: ")()())"
输出: 4
解释: 最长有效括号子串为 "()()"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-valid-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {
    H032_最长有效括号 obj = new H032_最长有效括号();
    assert 2 == obj.longestValidParentheses("(()");
    assert 4 == obj.longestValidParentheses(")()())");
    assert 6 == obj.longestValidParentheses("(()())");
  }

  /*
  思路：遇到(，当前坐标入栈。
       遇到)，pop栈内的元素，如果pop之后栈为空，就把当前坐标入栈。如果pop之后不为空，就在max和（当前坐标 - 栈顶元素）之间取最大值。
       为了计算的正确性，一开始把-1 push到栈中。
       时间复杂度O(n)，空间复杂度O(n)
       这样栈最底下的元素一定是上一次没能进行左匹配的右括号的坐标
   */
  public int longestValidParentheses(String s) {
    char[] c = s.toCharArray();

    Stack<Integer> stack = new Stack<>();
    stack.push(-1);
    int maxans = 0;
    for (int i = 0; i < c.length; i++) {
      if (c[i] == '(') {
        stack.push(i);
      } else {
        stack.pop();
        if (stack.isEmpty()) {
          stack.push(i);
        } else {
          maxans = Math.max(maxans, i - stack.peek());
        }
      }
    }
    return maxans;
  }
}
