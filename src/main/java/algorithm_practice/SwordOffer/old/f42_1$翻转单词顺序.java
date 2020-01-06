package algorithm_practice.SwordOffer.old;

import common.util.StringUtil;

/**
 * 题目一：输入一个英文句子，翻转句子中单词的顺序，但单词内字啊的顺序不变。为简单起见，标点符号和普通字母一样处理。
 * 例如输入字符串”I am a student. ”，
 *   则输出”student. a am I”。
 *
 *
 * 【解】：翻转两次字符串即可
 *  第一步，翻转句子中的所有字符
 *      比如翻转“I am a student. ”中所有的字符得到“.tneduts am a I”，
 *  第二步，再翻转每个单词中字符的顺序
 *      就得到了“student. a am I”。
 *
 *
 * Created by nibnait on 2016/10/2.
 */
public class f42_1$翻转单词顺序 {

    /**
     * 自然解法：利用StringBuffer.append方法的完成翻转
     * @param str
     * @return
     */
    private static String reverseSentence2(String str) {
        if (StringUtil.isBlank(str)){
            return "";
        }
        String[] arr = str.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = arr.length - 1; i >= 0; i--) {
            sb.append(arr[i]).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static void main(String[] args) {
        System.out.println(new String(reverseSentence2("I am a student.")));
        System.out.println(new String(reverseSentence2("Wonderful")));
        System.out.println(new String(reverseSentence2("")));
        System.out.println(new String(reverseSentence2("    ")));
    }

    private static String reverseSentence(String str) {
        if (StringUtil.isBlank(str)){
            return "";
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        Reverse(chars, 0, len-1);

        //下面开始翻转每个单词
        int lo = 0;
        int hi = 0;
        while (lo < len){
            hi++;
            if (hi==len || chars[hi]==' '){
                Reverse(chars, lo, hi-1);
                hi++;
                lo = hi;
            }
        }
        return new String(chars);
    }

    public static void Reverse(char[] chars, int lo, int hi) {
        if (chars==null || chars.length<=0 || lo<0 || hi>=chars.length || lo>hi){
            return;
        }
        char tmp;
        while (lo < hi){
            tmp = chars[lo];
            chars[lo] = chars[hi];
            chars[hi] = tmp;
            lo++;
            hi--;
        }
    }

}
