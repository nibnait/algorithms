package algorithm_practice.LeetCode.code000;

import common.util.AssertUtils;
import junit.framework.TestCase;
import org.junit.Test;

/*
给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

示例 1:

输入: num1 = "2", num2 = "3"
输出: "6"
示例 2:

输入: num1 = "123", num2 = "456"
输出: "56088"
说明：

num1 和 num2 的长度小于110。
num1 和 num2 只包含数字 0-9。
num1 和 num2 均不以零开头，除非是数字 0 本身。
不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。

https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1015/
https://leetcode-cn.com/problems/multiply-strings/
Created by nibnait on 2020-01-05
*/
public class M043_字符串相乘 extends TestCase {

    @Test
    public void testCase() {
        String num1 = "123";
        String num2 = "456";
        AssertUtils.compareString("56088", multiply(num1, num2));
        AssertUtils.compareString("56088", multiply2(num1, num2));

        num1 = "123";
        num2 = "0";
        AssertUtils.compareString("0", multiply(num1, num2));
        AssertUtils.compareString("0", multiply2(num1, num2));

        num1 = "123456789";
        num2 = "987654321";
        AssertUtils.compareString("121932631112635269", multiply(num1, num2));
        AssertUtils.compareString("121932631112635269", multiply2(num1, num2));

        num1 = "999";
        num2 = "999";
        AssertUtils.compareString("998001", multiply(num1, num2));
        AssertUtils.compareString("998001", multiply2(num1, num2));
    }

    public String multiply2(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        // 长的数字
        char[] n1 = num1.length() >= num2.length() ? num1.toCharArray() : num2.toCharArray();
        // 短的数字
        char[] n2 = num1.length() >= num2.length() ? num2.toCharArray() : num1.toCharArray();

        int p2 = n2.length - 1;
        int carry = 0;
        String product = "";
        while (p2 >= 0) {
            StringBuilder curSum = new StringBuilder();
            int p1 = n1.length - 1;
            while (p1 >= 0) {
                int val = (carry + (intVal(n1[p1]) * intVal(n2[p2]))) % 10;
                carry = (carry + (intVal(n1[p1]) * intVal(n2[p2]))) / 10;

                curSum.append(val);
                p1--;
            }

            if (carry != 0) {
                curSum.append(carry);
            }
            curSum = curSum.reverse();

            for (int i = 0; i < n2.length - 1 - p2; i++) {
                curSum.append("0");
            }
            product = sum(product, curSum.toString());

            p2--;
            carry = 0;
        }

        return product;
    }

    private String sum(String s1, String s2) {
        int p1 = s1.length() - 1;
        int p2 = s2.length() - 1;

        StringBuilder sum = new StringBuilder();
        int carry = 0;
        while (p1 >= 0 && p2 >= 0) {
            int val = (carry + intVal(s1.charAt(p1)) + intVal(s2.charAt(p2))) % 10;
            carry = (carry + intVal(s1.charAt(p1)) + intVal(s2.charAt(p2))) / 10;

            sum.append(val);
            p1--;
            p2--;
        }

        while (p1 >= 0) {
            int val = (carry + intVal(s1.charAt(p1))) % 10;
            carry = (carry + intVal(s1.charAt(p1))) / 10;

            sum.append(val);
            p1--;
        }

        while (p2 >= 0) {
            int val = (carry + intVal(s2.charAt(p2))) % 10;
            carry = (carry + intVal(s2.charAt(p2))) / 10;

            sum.append(val);
            p2--;
        }

        if (carry != 0) {
            sum.append(carry);
        }

        return sum.reverse().toString();
    }

    private int intVal(char c) {
        return c - '0';
    }


    public String multiply(String num1, String num2) {
        char[] charsA = num1.toCharArray();
        char[] charsB = num2.toCharArray();
        int[] result = new int[charsA.length + charsB.length + 1];

        int[] numA = new int[charsA.length];
        for (int i = 0; i < charsA.length; i++) {
            numA[i] = charsA[i] - '0';
        }

        int carry = 0;
        for (int i = charsB.length - 1; i >= 0; i--) {
            result = multiply(numA, charsB[i], result, carry++);
        }

        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (int i = 0; i < result.length; i++) {
            if (result[i] == 0 && flag) {
                continue;
            } else {
                flag = false;
                sb.append(result[i]);
            }
        }
        return sb.toString().isEmpty() ? "0" : sb.toString();
    }

    private int[] multiply(int[] numA, char charB, int[] result, int carry) {
        int[] productResult = new int[numA.length + 1];
        int productIndex = numA.length;
        for (int i = numA.length - 1; i >= 0; i--) {
            int product = numA[i] * (charB - '0');
            product = product + productResult[productIndex];
            productResult[productIndex] = product % 10;
            productResult[productIndex - 1] = product / 10;
            productIndex--;
        }

        productIndex = productResult.length - 1;
        for (int i = result.length - 1 - carry; i > 0 && productIndex >= 0; i--) {
            int sum = result[i] + productResult[productIndex--];
            result[i] = sum % 10;
            result[i - 1] += sum / 10;
        }
        return result;
    }

}