package algorithm_practice.SwordOffer.old;


import common.util.StringUtil;

import static algorithm_practice.SwordOffer.old.f42_1$翻转单词顺序.Reverse;

/**
 * 题目二：字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
 * 比如输入字符串“abcdefg”和数字 2，
 * 该函数将返回左旋转 2 位得到的结果“cdefgab”。
 *
 *
 * 【解】：与上一题类似：
 *  第一步，翻转前半部分的字符：ba cdefg
 *  第二步，翻转后半部分的字符：ba gfedc
 *  第三部，翻转整个字符串：cdegfab
 *
 * 【左神做法】：/src/Algorithm.nowcoder/b_2nd_Season/bk161012/src/
 *
 *      `小块换过来的东西，定住！
 *
 *
 * Created by nibnait on 2016/10/2.
 */
public class f42_2$左旋转字符串 {

    public static void main(String[] args) {
        System.out.println(new String(leftRotateString("abcdefg", 2)));
        System.out.println(new String(leftRotateString("abcdefg", 1)));
        System.out.println(new String(leftRotateString("abcdefg", 6)));
        System.out.println(new String(leftRotateString("abcdefg", 7)));
        System.out.println(new String(leftRotateString("abcdefg", 0)));
    }

    private static String leftRotateString(String str, int n) {
        if (StringUtil.isBlank(str) || n<=0 || n>=str.length()){    //n==0，n==str.length 都不用翻转！
            return str;
        }
        char[] chars = str.toCharArray();
        Reverse(chars, 0, n-1);
        Reverse(chars, n, chars.length-1);
        Reverse(chars, 0, chars.length-1);
        return new String(chars);
    }
}
