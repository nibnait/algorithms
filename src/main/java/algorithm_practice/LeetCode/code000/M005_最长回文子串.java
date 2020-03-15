package algorithm_practice.LeetCode.code000;

import junit.framework.TestCase;
import org.junit.Test;

/*
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：
输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。

示例 2：
输入: "cbbd"
输出: "bb"


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-palindromic-substring
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M005_最长回文子串 extends TestCase {

    @Test
    public void testCase() {
        String str = "cabaddabacdd";
        System.out.println(maxLcpsLength(str));

        String str2 = "tattarrattat";
        System.out.println(maxLcpsLength(str2));

        String str3 = "babad";
        System.out.println(maxLcpsLength(str3));

        String str4 = "cbbd";
        System.out.println(maxLcpsLength(str4));

    }

    public String longestPalindrome(String s) {


        return "";
    }

    /**
     * Manacher算法： 最长回文字串的长度
     * <p>
     * 每个元素之间插入一个字符'#'，抵消奇回文与偶回文的差别（任意字符都行，不会影响最终的计算结果）
     * - pArr[i]：i位置上，所能扩到的最大回文半径
     * - maxRight：记录回文半径扫到最右位置的下一个位置。（即将到达的位置）
     * - index：当pR更新的时候，此时回文中心的位置
     */
    private int maxLcpsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] strArr = str.toCharArray();
        int length = 2 * strArr.length + 1;
        char[] charArr = new char[length];
        charArr[length - 1] = '#';
        //字符串预处理
        int cnt = 0;
        for (int i = 0; i < length - 1; ) {
            charArr[i++] = '#';
            charArr[i++] = strArr[cnt++];
        }
        int MaxLen = Integer.MIN_VALUE;
        int[] pArr = new int[length];   //i位置上，所能扩到的最大回文半径
        int maxRight = -1;      //记录回文半径所能扫到最右位置。（maxRight = i+pArr[i]-1）
        int index = -1;        //当maxRight更新的时候，此时回文中心的位置
        for (int i = 0; i < length; i++) {
            if (i < maxRight) {
                pArr[i] = Math.min(pArr[2 * index - i], maxRight - i);    //看pArr[j]的长度
            } else {
                pArr[i] = 1;    //i在maxRight的右边，从1开始扩
            }
            //尝试扩展
            while (i + pArr[i] < length && i - pArr[i] >= 0 && charArr[i - pArr[i]] == charArr[i + pArr[i]]) {
                pArr[i]++;
            }
            //尝试更新maxRight和index
            if (i + pArr[i] > maxRight) {
                maxRight = i + pArr[i] - 1;
                index = i;
            }
            MaxLen = Math.max(MaxLen, pArr[i]);
        }
        return MaxLen - 1;    //因为MaxLen为带有'#'的回文半径，所以不带'#'回文长度也就为MaxLen-1
    }
}
