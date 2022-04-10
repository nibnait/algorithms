package algorithm_practice.LeetCode.code1000;

import junit.framework.TestCase;
import org.junit.Test;

/*
对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。

示例 1：

输入：str1 = "ABCABC", str2 = "ABC"
输出："ABC"
示例 2：

输入：str1 = "ABABAB", str2 = "ABAB"
输出："AB"
示例 3：

输入：str1 = "LEET", str2 = "CODE"
输出：""
 

提示：

1 <= str1.length <= 1000
1 <= str2.length <= 1000
str1[i] 和 str2[i] 为大写英文字母

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/greatest-common-divisor-of-strings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
Created by nibnait on 2020-03-13
 */
public class E1071_字符串的最大公因子 extends TestCase {

    @Test
    public void testCase() {
        String str1 = "ABCABC", str2 = "ABC";
        System.out.println(gcdOfStrings(str1, str2));

        String str3 = "ABABAB", str4 = "ABAB";
        System.out.println(gcdOfStrings(str3, str4));

        String str5 = "LEET", str6 = "CODE";
        System.out.println(gcdOfStrings(str5, str6));

        String str7 = "aaa", str8 = "";
        System.out.println(gcdOfStrings(str7, str8));

        String str9 = "", str0 = "aaa";
        System.out.println(gcdOfStrings(str9, str0));


    }

    public String gcdOfStrings(String str1, String str2) {

        String result = "";
        if (str1.isEmpty() || str2.isEmpty()) {
            return result;
        }

        if (!(str1 + str2).equals(str2 + str1)) {
            return result;
        }

        int gcd = gcd(str1.length(), str2.length());
        return str1.substring(0, gcd);
    }

    private int gcd(int len1, int len2) {
        if (len2 > len1) {
            return len1 == 0 ? len2 : gcd(len1, len2 % len1);
        }
        return len2 == 0 ? len1 : gcd(len2, len1 % len2);
    }


}
