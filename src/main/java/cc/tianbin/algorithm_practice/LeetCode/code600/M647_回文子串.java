package cc.tianbin.algorithm_practice.LeetCode.code600;

import cc.tianbin.common.util.AssertUtils;
import org.junit.Test;

/**
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 *
 * 回文字符串 是正着读和倒过来读一样的字符串。
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 *
 * 示例 1：
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 *
 * 示例 2：
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 *
 * https://leetcode.cn/problems/palindromic-substrings
 * Created by nibnait on 2023/03/27
 */
public class M647_回文子串 {

    @Test
    public void test() {
        String s = "abc";
        int result = countSubstrings(s);
        int expect = 3;
        AssertUtils.compareInteger(expect, result);

        s = "aaa";
        result = countSubstrings(s);
        expect = 6;
        AssertUtils.compareInteger(expect, result);
    }


    public int countSubstrings(String s) {
        int[] pArr = getManacherDP(s);

        int ans = 0;
        for (int i = 0; i < pArr.length; i++) {
            ans += pArr[i] / 2;
        }
        return ans;
    }

    private int[] getManacherDP(String s) {
        char[] str = manacherStr(s);
        // pArr[i] 表示 i位置上，能扩到的最大回文半径
        int[] pArr = new int[str.length];
        // 记录回文半径能扫到的最右为止
        int maxRight = -1;
        // 当 maxRight 更新时，此时回文中心的位置
        int index = -1;
        for (int i = 0; i < str.length; i++) {
            if (maxRight > i) {
                pArr[i] = Math.min(pArr[2 * index - i], maxRight - i);
            } else {
                pArr[i] = 1;
            }

            // 尝试扩展
            while (i + pArr[i] < str.length && i - pArr[i] >= 0) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }

            // 尝试更新 maxRight 和 index
            if (i + pArr[i] > maxRight) {
                maxRight = i + pArr[i] - 1;
                index = i;
            }
        }

        return pArr;
    }

    private char[] manacherStr(String s) {
        char[] str = s.toCharArray();
        char[] res = new char[s.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = ((i & 1) == 0) ? '#' : str[index++];
        }
        return res;
    }
}
