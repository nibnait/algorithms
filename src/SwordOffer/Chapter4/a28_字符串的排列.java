package SwordOffer.Chapter4;

/**
 * 题目：输入一个字符串，打印出该字符串中字符的所有排列。
 * 例如 输入字符串 abc。
 *     则打印出由字符 a、b、c 所能排列出来的所有字符串 abc、acb、bac 、bca、cab 和 cba 。
 *
 * 【解】：
 *      递归！
 *
 *  【扩展】
 *      如果不是求字符的所有排列，而是求字符的所有组合？
 *   解：
 *      我们可以把求n个字符组成长度为m的组合问题分解成两个子问题：
 *       - 分别求n-1个字符串中长度为m-1的组合，
 *       - 以及求n-1个字符的长度为m的组合。
 *
 * Created by nibnait on 2016/9/26.
 */
public class a28_字符串的排列 {

    public static void main(String[] args) {

        String str = new String();
        str = "abc";
        str = "abcd";
        permutation(str);
    }

    private static void permutation(String str) {
        if (str.isEmpty()){
            return;
        }
        char[] chars = str.toCharArray();
        permutation(chars, 0);
    }

    private static void permutation(char[] chars, int begin) {
        int length = chars.length;
        if (begin == length-1){
            System.out.print(new String(chars) + " ");
        } else {
            char tmp;
            for (int i = begin; i < length; i++) {
                tmp = chars[begin];
                chars[begin] = chars[i];
                chars[i] = tmp;
                permutation(chars, begin+1);
            }
        }

    }

}
