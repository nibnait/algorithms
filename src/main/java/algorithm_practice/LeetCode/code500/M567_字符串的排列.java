package algorithm_practice.LeetCode.code500;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

换句话说，第一个字符串的排列之一是第二个字符串的子串。

示例1:

输入: s1 = "ab" s2 = "eidbaooo"
输出: True
解释: s2 包含 s1 的排列之一 ("ba").


示例2:

输入: s1= "ab" s2 = "eidboaoo"
输出: False


注意：

输入的字符串只包含小写字母
两个字符串的长度都在 [1, 10,000] 之间

https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1016/
https://leetcode-cn.com/problems/permutation-in-string/
Created by nibnait on 2020-01-04
 */
public class M567_字符串的排列 extends TestCase {

    @Test
    public void testCase() {
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(checkInclusion(s1, s2));

        String s3 = "ab";
        String s4 = "eidboaoo";
        System.out.println(checkInclusion(s3, s4));

        String s5 = "dinitrophenylhydrazine";
        String s6 = "acetylphenylhydrazine";
        System.out.println(checkInclusion(s5, s6));

        String s7 = "trinitrophenylmethylnitramine";
        String s8 = "dinitrophenylhydrazinetrinitrophenylmethylnitramine";
        System.out.println(checkInclusion(s7, s8));

        String s9 = "adc";
        String s10 = "dcda";
        System.out.println(checkInclusion(s9, s10));

    }

    /*
    字母出现次数频率相减，出现负数，直接return false
    否则，将s2的字符串窗口缩小，直到相减 出现str1的charCounts[] 全为0


     */
    public boolean checkInclusion5(String str1, String str2) {


        return true;
    }

    /*
    字符串的排列
    [剑指Offer 第28题](../../../SwordOffer/old/d28_字符串的排列.java), 中有递归和非递归方法，在这里都会导致内存超限。

    此处使用【滑动窗口】的概念
        题意：在s2中连续的s1串长度内，出现与s1串中各个字母出现次数相同即为true
        思路：在s2中圈出一个s1.length 大的窗口，依次计算每个字母出现的次数与s1中各个字母出现的次数是否相同

             s1中各个字母出现的次数，可以用一个int[26]来装

     */
    public boolean checkInclusion(String str1, String str2) {
        if (str1.isEmpty() || str2.isEmpty() || str1.length() > str2.length()) {
            return false;
        }

        int[] strCharCounts = getStrCharCounts(str1.toCharArray());
        int begin = 0;
        int end = str1.length()-1;
        for (; end < str2.length(); begin++, end++) {
            if (match(strCharCounts, getStrCharCounts(str2.substring(begin, end+1).toCharArray()))) {
                return true;
            }
        }

        return false;
    }

    private boolean match(int[] s1Count, int[] s2Count) {
        for (int i = 0; i < 26; i++) {
            if (s1Count[i] != s2Count[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] getStrCharCounts(char[] chars) {
        int[] result = new int[26];
        for (int i = 0; i < chars.length; i++) {
            result[chars[i] - 'a'] += 1;
        }
        return result;
    }


    /*
    非递归求字符串的排列组合
    1234 1243
        1324、1342
        1432、1423

     */
    public boolean checkInclusion4(String str1, String str2) {
        if (str1.isEmpty() || str2.isEmpty() || str1.length() > str2.length()) {
            return false;
        }
        List<String> stringList = new ArrayList<>();
        char[] chars = str1.toCharArray();
        Arrays.sort(chars);
        stringList.add(new String(chars));

        int p1 = chars.length - 1;
        int pEnd = chars.length - 1;
        int p2, pFind;
        while (p1 > 0) {
            p2 = p1;
            p1--;
            if (chars[p1] < chars[p2]) {
                pFind = pEnd;
                while (chars[pFind] < chars[p1]) {
                    pFind--;
                }
                swap(chars, p1, pEnd);
                stringList.add(new String(chars));
                p1 = pEnd;
            }
        }
        return !stringList.stream().filter(str -> str2.contains(str)).collect(Collectors.toList()).isEmpty();
    }

    /**
     * 递归求字符串的排列，s5和s6，超出了内存限制
     */
    public boolean checkInclusion3(String str1, String str2) {
        if (str1.isEmpty() || str2.isEmpty() || str1.length() > str2.length()) {
            return false;
        }

        char[] chars = str1.toCharArray();
        List<String> stringList = new ArrayList<>();
        permutation(chars, 0, stringList);

        return !stringList.stream().filter(str -> str2.contains(str)).collect(Collectors.toList()).isEmpty();
    }

    private void permutation(char[] chars, int begin, List<String> stringList) {
        if (chars == null || chars.length <= 0) {
            return;
        }

        int length = chars.length;
        if (begin == length - 1) {
            stringList.add(new String(chars));
        } else {
            for (int i = begin; i < length; i++) {
                if (different(chars, begin, i)) {
                    swap(chars, begin, i);
                    permutation(chars, begin + 1, stringList);
                    swap(chars, begin, i);
                }
            }
        }
    }

    private void swap(char[] a, int i, int j) {
        char tmp;
        tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private boolean different(char[] chars, int begin, int end) {
        for (; begin < end; begin++) {
            if (chars[begin] == chars[end]) {
                return false;
            }
        }
        return true;
    }

    /**
     * KMP算法
     * 未考虑到要给s1 先排列的问题
     */
    public boolean checkInclusion2(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < 1 || str2.length() < str1.length()) {
            return false;
        }

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int ps1 = 0;
        int ps2 = 0;
        int[] nextArray = getNextArray(s1);
        while (ps1 < s1.length && ps2 < s2.length) {
            if (s1[ps1] == s2[ps2]) {
                ps1++;
                ps2++;
            } else if (nextArray[ps1] == -1) {
                ps2++;
            } else {
                ps1 = nextArray[ps1];
            }
        }

        return ps1 == s1.length;
    }

    private static int[] getNextArray(char[] s2) {
        if (s2.length == 1) {
            return new int[]{-1};
        }
        int[] nextArr = new int[s2.length];
        nextArr[0] = -1;
        nextArr[1] = 0;
        int pos = 2;    //当前位置
        int cn = 0;     //最长匹配前缀的下一个字符的位置
        while (pos < s2.length) {
            if (s2[pos - 1] == s2[cn]) {
                nextArr[pos++] = nextArr[pos - 1] + 1;
                cn++;
            } else if (cn > 0) {
                cn = nextArr[cn];
            } else {
                nextArr[pos++] = 0;
            }
        }
        return nextArr;
    }
}