package algorithm_practice.LeetCode.code000;

public class H044_通配符匹配 {
  /*
给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。

'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。
两个字符串完全匹配才算匹配成功。

说明:

s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
示例 2:

输入:
s = "aa"
p = "*"
输出: true
解释: '*' 可以匹配任意字符串。
示例 3:

输入:
s = "cb"
p = "?a"
输出: false
解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
示例 4:

输入:
s = "adceb"
p = "*a*b"
输出: true
解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
示例 5:

输入:
s = "acdcb"
p = "a*c?b"
输出: false

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/wildcard-matching
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */

  public static void main(String[] args) {
    H044_通配符匹配 obj = new H044_通配符匹配();
    assert obj.isMatch("aa", "*");
    assert !obj.isMatch("aa", "a");
    assert !obj.isMatch("cb", "*a");
    assert obj.isMatch("adceb", "*a*b");
    assert !obj.isMatch("acdcb", "a*c?b");
  }

  /*
  1.动态规划
状态转移方程：
模式p[j] ！= '*'且 p[j] != '?'时， dp[i][j] = (p[j] == s[i]) && dp[i-1][j-1]
模式p[j] == '?'时，dp[i][j] =dp[i-1][j-1]
模式p[j] == '*'时，*可以匹配0个词，也可以匹配1个词并且*还存在，这样下次计算*还存在的时候，*还可以匹配0或1个词，以此类推*可以匹配0或无限多个字。
所以这时候dp[i][j] = dp[i-1][j] (这时候匹配了一个词) || dp[i][j-1](这时候匹配了0个词)
边界条件：
为了满足某一方为空的场景，所以dp数组的大小是s.length+1行，p.length+1列。
其中dp[0][0]是两个空串匹配场景，所以dp[0][0] = true；
而空的p模式无法与任何非空字符串匹配，所以dp[any][0] = false;
*可以匹配0个字母，所以dp[0][从头到尾最后一个连续的*的位置] = true，其余为false。
   */
  public boolean isMatch(String s, String p) {
    int sl = s.length();
    int pl = p.length();
    boolean[][] dp = new boolean[sl + 1][pl + 1];

    // 初始化dp数组边界条件
    dp[0][0] = true;
    for (int i = 1; i <= pl; i++) {
      if (p.charAt(i - 1) == '*') {
        dp[0][i] = true;
      } else {
        break;
      }
    }

    for (int i = 1; i <= sl; i++) {
      for (int j = 1; j <= pl; j++) {
        if (p.charAt(j - 1) == '*') {
          dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
        } else if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
          dp[i][j] = dp[i - 1][j - 1];
        }
      }
    }
    return dp[sl][pl];
  }
}
