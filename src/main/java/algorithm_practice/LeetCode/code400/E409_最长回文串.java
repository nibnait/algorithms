package algorithm_practice.LeetCode.code400;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashSet;

/*
给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。

在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。

注意:
假设字符串的长度不会超过 1010。

示例 1:

输入:
"abccccdd"

输出:
7

解释:
我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
Created by nibnait on 2020-03-19
 */
public class E409_最长回文串 extends TestCase {

    @Test
    public void testCase() {
        String s = "abccccdd";
        System.out.println(longestPalindrome(s));

        String s1 = "abccccdd";
        System.out.println(longestPalindrome(s1));


    }

    public int longestPalindrome(String s) {
        if (s == null) {
            return 0;
        }

        if (s.length() <= 1) {
            return s.length();
        }

        int result = 0;
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (set.contains(ch)) {
                set.remove(ch);
                result += 2;
            } else {
                set.add(ch);
            }
        }

        if (!set.isEmpty()) {
            result += 1;
        }
        return result;
    }
}
