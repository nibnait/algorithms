package algorithm_practice.SwordOffer.old;

import common.util.SwapUtil;

import java.util.Arrays;

/**
 * 题目：输入一个字符串，打印出该字符串中字符的所有排列。
 * 例如 输入字符串 abc。
 *     则打印出由字符 a、b、c 所能排列出来的所有字符串 abc、acb、bac 、bca、cab 和 cba 。
 *
 * 解：
 *      递归！【注意：去重！！】
 *  每一次递归都将字符串分成两部分： 第1个字符，和他后面的所有字符
    *  先将第一个字符与其后面的字符依次交换，然后 求其后面的所有字符的全排列
 * 【去重】：
 *      就是从第一个字符开始，每个字符与其后面非重复的字符交换
 *     即：当chars[begin]与chars[i]交换时，[begin,i)中没有与chars[i]相同的字符
 *
 * 【法2】：非递归
 *  我们先来看一下全排列的输出过程：
 *  例如输入“1234”
     *  1234、1243、
     *        1324、1342、
     *        1432、1423、
     *  2134、2143、
     *        2314、2341、
     *        2431、2413、
     *  3214、3241、
     *        3124、3142、
     *        3412、3421、
     *  4231、4213、
     *        4321、4312、
     *        4132、4123、
 *   总结规律如下：
 *    - 先从后往前找到第一对递增序列，比如"1243"，从后往前的第一对递增序列为："24"
 *      - 这里的"2"叫做“替换数”，"4"叫做“替换点”
 *    - 然后再从后往前 找一个比替换数大的最小数（3）。将其与替换数交换。-->"1342"
 *    - 最后将"替换点"即其后面的数字，颠倒一下，-->"1324"，即得到了下一个全排列数字。
 *
 *
 *  时间复杂度：O(N！)
 *
 * Created by nibnait on 2016/9/26.
 */
public class d28_字符串的排列 {

    /**
     * 非递归：
     *
     * @param str
     */
    private static void permutation2(String str){
        if (str.isEmpty()){
            return;
        }
        char[] chars = str.toCharArray();
        //第一步：先将chars按照升序排个序
        Arrays.sort(chars);
        System.out.print(new String(chars) + " ");  //把第一个序列输出，

        int p1 = chars.length-1;
        int pEnd = chars.length-1;
        int p2, pFind;
        while (p1 > 0){
            p2 = p1;
            p1--;
            if (chars[p1] < chars[p2]){     //第一个递增序列
                pFind = pEnd;
                while (chars[pFind] < chars[p1]){   //从后往前找比替换点大的第一个数
                    --pFind;
                }
                SwapUtil.swap(chars, p1, pFind);
                Reverse(chars, p2, pEnd);   //替换点后面的数全部反转
                System.out.print(new String(chars) + " ");
                p1 = pEnd;
            }
        }
    }

    private static void Reverse(char[] chars, int p2, int length) {
        while (p2 < length){
            SwapUtil.swap(chars, p2++, length--);
        }
    }


    public static void main(String[] args) {
        String str = new String();
        str = "1234";
        permutation(str);

        System.out.println();
        permutation2(str);
    }

    private static void permutation(String str) {
        if (str.isEmpty()){
            return;
        }
        char[] chars = str.toCharArray();
        permutation(chars, 0);
    }

    private static void permutation(char[] chars, int begin) {
        if (chars==null || chars.length<=0){
            return;
        }

        int length = chars.length;
        if (begin == length-1){
            System.out.print(new String(chars) + " ");
        } else {
            for (int i = begin; i < length; i++) {
                if (IsSwap(chars, begin, i)){
                    SwapUtil.swap(chars, begin, i);
                    permutation(chars, begin+1);
                    SwapUtil.swap(chars, begin, i);
                }
            }
        }

    }

    private static boolean IsSwap(char[] chars, int begin, int end) {
        for (; begin < end ; begin++) {
            if (chars[begin] == chars[end]){
                return false;
            }
        }
        return true;
    }

}
