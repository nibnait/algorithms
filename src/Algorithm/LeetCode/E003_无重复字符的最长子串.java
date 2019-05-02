package Algorithm.LeetCode;

import utils.SysOut;
import utils.StringUtil;
import junit.framework.TestCase;
import org.junit.Test;

/*
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:
输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

示例 2:
输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:
输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

 */
public class E003_无重复字符的最长子串 extends TestCase {

    @Test
    public void testCase() {
        String demo1 = "abcabcbb";
        String demo2 = "bbbbb";
        String demo3 = "pwwkew";
        String demo4 = " ";
        String demo5 = "au";
        String demo6 = "nfpdmpi";
        System.out.println(lengthOfLongestSubstring(demo4));
    }

    /**
     * 法2（答案）：滑动窗口
     * @param s
     * @return
     */
    private int lengthOfLongestSubstringV2(String s) {
        String longestSubString = StringUtil.EMPTY_STRING;

        return longestSubString.length();
    }


    /**
     * 玩转字符串
     * @param s
     * @return
     */
    private int lengthOfLongestSubstring(String s) {
        String longestSubString = StringUtil.EMPTY_STRING;
        StringBuilder currentStringBuilder = new StringBuilder("");
        for (int i = 0; i < s.length(); i++) {
            Character currentChar = s.charAt(i);
            String currentString = currentStringBuilder.toString();
            //此字符串已存在于currentString中
            if (currentString.contains(currentChar.toString())) {
                //1. 获取该字符在currentString中的位置index
                int index = currentString.indexOf(currentChar);

                //2. 将此字符之前的字符串切掉，构建新的字符串。
                String substring = currentString.substring(index + 1, currentString.length());
                currentStringBuilder = new StringBuilder(substring);
            }
            currentStringBuilder.append(currentChar);
            //3. 新字符串的长度 > longestSubString时，赋上新值
            if (longestSubString.length() < currentStringBuilder.length()) {
                longestSubString = currentStringBuilder.toString();
            }
        }

        SysOut.println("\nSourceString: %s, LongestSubstring: %s", s, longestSubString);
        return longestSubString.length();
    }
}