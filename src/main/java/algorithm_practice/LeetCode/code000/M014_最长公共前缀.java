package algorithm_practice.LeetCode.code000;

import junit.framework.TestCase;
import org.junit.Test;

/*
编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

示例 1:

输入: ["flower","flow","flight"]
输出: "fl"
示例 2:

输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。
说明:

所有输入只包含小写字母 a-z 。

https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1014/
https://leetcode-cn.com/problems/longest-common-prefix/
Created by nibnait on 2020-01-04
 */
public class M014_最长公共前缀 extends TestCase {

    @Test
    public void testCase() {
        String[] strings = new String[]{"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strings));
        String[] strings2 = new String[]{"dog", "racecar", "car"};
        System.out.println(longestCommonPrefix(strings2));
        String[] strings3 = new String[]{"aaa","aa","aaa"};
        System.out.println(longestCommonPrefix(strings3));
        String[] strings4 = new String[]{"abab","aba",""};
        System.out.println(longestCommonPrefix(strings4));

    }

    public String longestCommonPrefix(String[] strs) {
        String result = "";
        if (strs == null || strs.length == 0) {
            return result;
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int minLength = strs[0].length();
        for (int i = 0; i < strs.length; i++) {
            minLength = Math.min(minLength, strs[i].length());
        }

        int p = 1;
        String compareStr = "";
        boolean finishFlag = false;
        for (int i = 0; i < minLength; i++) {
            for (int j = 0; j < strs.length; j++) {
                String str = strs[j];
                String substring = str.substring(0, p);
                if (j == 0) {
                    compareStr = substring;
                }
                if (!compareStr.equals(substring)) {
                    finishFlag = true;
                    break;
                }
            }
            if (finishFlag) {
                break;
            }
            p++;
            result = compareStr;
        }

        return compareStr.length() == result.length() ? compareStr : result;
    }
}