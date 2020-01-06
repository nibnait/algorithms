package algorithm_practice.SwordOffer.old;

/**
 * 题目：输入一个字符串，打印出该字符串中字符的所有组合。
 * 例如 输入字符串 abc。（输入字符不重复）
 *      输出：a、b、c、ab、ac、bc、abc、
 *
 * 法1：
 *   分解的思路，“abc”
 *   a开头： a,
 *           ab, abc,
 *           ac,
 *   b开头： b,
 *           bc,
 *   c开头： c
 *
 * 【法2】：
 *     位图的思想：
 *     abc, 一共3位
 * 全组合：001,010,011,100,101,110,111  7种 = 2^3-1
 *          c, b, bc, a, ac, ab, abc   √
 *
 *
 * Created by nibnait on 2016/9/28.
 */
public class d28_字符串的组合 {

    public static void main(String[] args) {

        String str = new String();
        str = "abc";
        combination(str);

        System.out.println();
        combination2(str);
    }


    private static void combination(String str) {
        if (str.isEmpty()){
            return;
        }
        char[] chars = str.toCharArray();
        StringBuffer output = new StringBuffer(chars.length);

        combination(chars, chars.length, output, 0);
    }

    private static void combination(char[] chars, int length, StringBuffer output, int begin) {
        if (begin == length){
            return;
        } else {
            for (int i = begin; i < length; i++) {
                output.append(chars[i]);
                System.out.print(output.toString() + " ");
                combination(chars, length, output, i+1);
                output.deleteCharAt(output.length()-1);
            }
        }
    }


    /**
     * 位图的思想：
     * @param str
     */
    private static void combination2(String str) {
        char[] chars = str.toCharArray();
        int length = chars.length;
        int n = 1 << length;  // (2^length)
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < length; j++) {
                if ((i&(1<<j)) !=0){
                    System.out.print(chars[j]);
                }
            }
            System.out.print(" ");
        }



    }



}
