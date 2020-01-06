package algorithm_practice.SwordOffer.old;

/**
 * 题目：请实现一个函数用来匹配包含‘.’和‘*’的正则表达式。模式中的字符’.’表示任意一个字符，而‘*’表示它前面的字符可以出现任意次（含0次）。
 * 本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串“aaa”与模式“a.a”和“ab*ac*a”匹配，但与“aa.a”及“ab*a”均不匹配。
 *
 *
 *
 * Created by nibnait on 2016/10/3.
 */
public class h53_正则表达式匹配 {
    
    public static boolean match(String input, String pattern) {
        if (input == null || pattern == null) {
            return false;
        }
        return matchCore(input, 0, pattern, 0);
    }

    private static boolean matchCore(String input, int i, String pattern, int p) {
        // 匹配串和模式串都到达尾，说明成功匹配
        if (i >= input.length() && p >= pattern.length()) {
            return true;
        }
        // 只有模式串到达结尾，说明匹配失败
        if (i != input.length() && p >= pattern.length()) {
            return false;
        }
        // 模式串未结束，匹配串有可能结束有可能未结束
        // p位置的下一个字符中为*号
        if (p + 1 < pattern.length() && pattern.charAt(p + 1) == '*') {
            // 匹配串已经结束
            if (i >= input.length()) {
                return matchCore(input, i, pattern, p + 2);
            }
            // 匹配串还没有结束
            else {
                if (pattern.charAt(p) == input.charAt(i) || pattern.charAt(p) == '.') {
                    return
                            // 匹配串向后移动一个位置，模式串向后移动两个位置
                            matchCore(input, i + 1, pattern, p + 2)
                                    // 匹配串向后移动一个位置，模式串不移动
                                    || matchCore(input, i + 1, pattern, p)
                                    // 匹配串不移动，模式串向后移动两个位置
                                    || matchCore(input, i, pattern, p + 2);
                } else {
                    return matchCore(input, i, pattern, p + 2);
                }
            }
        }
        //
        // 匹配串已经结束
        if (i >= input.length()) {
            return false;
        }
        // 匹配串还没有结束
        else {
            if (input.charAt(i) == pattern.charAt(p) || pattern.charAt(p) == '.') {
                return matchCore(input, i + 1, pattern, p + 1);
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(match("", "") + "--true");
        System.out.println(match("", ".*") + "--false");
        System.out.println(match("", ".") + "--false");
        System.out.println(match("", "c*") +  "--true");
        System.out.println();
        System.out.println(match("a", ".*") +  "--true");
        System.out.println(match("a", "a.") + "--false");
        System.out.println(match("a", "") + "--false");
        System.out.println(match("a", ".") +  "--true");
        System.out.println(match("a", "ab*") +  "--true");
        System.out.println(match("a", "ab*a") + "--false");
        System.out.println();
        System.out.println(match("aa", "aa") +  "--true");
        System.out.println(match("aa", "a*") +  "--true");
        System.out.println(match("aa", ".*") +  "--true");
        System.out.println(match("aa", ".") + "--false");
        System.out.println();
        System.out.println(match("ab", ".*") +  "--true");
        System.out.println(match("ab", ".*") +  "--true");
        System.out.println();
        System.out.println(match("aaa", "aa*") +  "--true");
        System.out.println(match("aaa", "aa.a") + "--false");
        System.out.println(match("aaa", "a.a") +  "--true");
        System.out.println(match("aaa", ".a") + "--false");
        System.out.println(match("aaa", "a*a") +  "--true");
        System.out.println(match("aaa", "ab*a") + "--false");
        System.out.println(match("aaa", "ab*ac*a") +  "--true");
        System.out.println(match("aaa", "ab*a*c*a") +  "--true");
        System.out.println(match("aaa", ".*") +  "--true");
        System.out.println();
        System.out.println(match("aab", "c*a*b") +  "--true");
        System.out.println();
        System.out.println(match("aaca", "ab*a*c*a") +  "--true");
        System.out.println(match("aaba", "ab*a*c*a") + "--false");
        System.out.println(match("bbbba", ".*a*a") +  "--true");
        System.out.println(match("bcbbabab", ".*a*a") + "--false");
    }
}
