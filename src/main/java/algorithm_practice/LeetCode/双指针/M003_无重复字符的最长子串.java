package algorithm_practice.LeetCode.双指针;

import common.util.SysOut;
import common.util.StringUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashSet;

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

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M003_无重复字符的最长子串 extends TestCase {

    @Test
    public void testCase() {
        String demo1 = "abcabcbb";
        String demo2 = "bbbbb";
        String demo3 = "pwwkew";
        String demo4 = " ";
        String demo5 = "au";
        String demo6 = "nfpdmpi";
        String demo7 = "";
        String demo8 = "dd";
        String demo9 = "df";
        System.out.println(lengthOfLongestSubstring(demo9));
        System.out.println(lengthOfLongestSubstringV2(demo9));
    }

    /**
     * 法2：滑动窗口   O (N)
     * p1 = 0，指向滑动窗口的起点
     * p2 = 1，指向滑动窗口的终点的下一个元素
     * 用HashSet来装当前窗口内的所有字符。
     * <p>
     * 用HashSet来查询窗口内是否有当前最新字符arr[p2]，
     *  有则记下当前 p1++，并hashSet.remore(arr[p1])
     *  否则配hashSet.add(arr[p2])，result = Math.max(currentHashSet.size, result)，p2++
     *
     * 注意边界条件！
     *
     * @param s
     * @return
     */
    private int lengthOfLongestSubstringV2(String s) {
        if (s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        int result = 0;

        char[] chars = s.toCharArray();
        int p1 = 0;
        int p2 = 1;
        HashSet hashSet = new HashSet();
        hashSet.add(chars[p1]);

        while (p1 < chars.length && p2 < chars.length) {
            if (hashSet.contains(chars[p2])) {
                hashSet.remove(chars[p1]);
                p1++;
            } else {
                hashSet.add(chars[p2]);
                result = Math.max(result, hashSet.size());
                p2++;
            }
        }

        return result;
    }


    /**
     * 玩转字符串
     *
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