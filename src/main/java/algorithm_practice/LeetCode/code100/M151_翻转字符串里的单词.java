package algorithm_practice.LeetCode.code100;

import junit.framework.TestCase;
import org.junit.Test;

/*
给定一个字符串，逐个翻转字符串中的每个单词。



示例 1：

输入: "the sky is blue"
输出: "blue is sky the"
示例 2：

输入: "  hello world!  "
输出: "world! hello"
解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
示例 3：

输入: "a good   example"
输出: "example good a"
解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。


说明：

无空格字符构成一个单词。
输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。


进阶：

请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。

https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1011/
https://leetcode-cn.com/problems/reverse-words-in-a-string/
Created by nibnait on 2020-01-06
*/
public class M151_翻转字符串里的单词 extends TestCase {

    @Test
    public void testCase() {
        String s1 = "the sky is blue";
        System.out.println(reverseWords(s1));

        String s2 = "  hello world!  ";
        System.out.println(reverseWords(s2));

        String s3 = "a good   example";
        System.out.println(reverseWords(s3));

        String s4 = "a, yqo! qjktum ym. .fumuhau";
        System.out.println(reverseWords(s4));

    }

    /*
    刚开始被 字符串的翻转（双指针）绕晕了。。。
     */
    public String reverseWords(String s) {
        String[] split = s.trim().split(" ");

        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            if (!split[i].isEmpty()) {
                sb.append(split[i]).append(" ");
            }
        }
        return sb.toString().trim();
    }
}