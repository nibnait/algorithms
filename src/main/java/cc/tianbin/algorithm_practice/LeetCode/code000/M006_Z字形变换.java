package cc.tianbin.algorithm_practice.LeetCode.code000;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/*
将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：

P   A   H   N
A P L S I I G
Y   I   R
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。

请你实现这个将字符串进行指定行数变换的函数：
string convert(string s, int numRows);


示例 1：
输入：s = "PAYPALISHIRING", numRows = 3
输出："PAHNAPLSIIGYIR"

示例 2：
输入：s = "PAYPALISHIRING", numRows = 4
输出："PINALSIGYAHRPI"
解释：
P     I    N
A   L S  I G
Y A   H R
P     I
示例 3：
输入：s = "A", numRows = 1
输出："A"

提示：
1 <= s.length <= 1000
s 由英文字母（小写和大写）、',' 和 '.' 组成
1 <= numRows <= 1000


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/zigzag-conversion
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M006_Z字形变换 {

    @Test
    public void test() {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        Assert.assertEquals("PAHNAPLSIIGYIR", convert(s, numRows));

        s = "PAYPALISHIRING";
        numRows = 4;
        Assert.assertEquals("PINALSIGYAHRPI", convert(s, numRows));

        s = "A";
        numRows = 1;
        Assert.assertEquals("A", convert(s, numRows));

        s = "AB";
        numRows = 1;
        Assert.assertEquals("AB", convert(s, numRows));

        s = "ABCD";
        numRows = 2;
        Assert.assertEquals("ACBD", convert(s, numRows));

    }

    public int ROW_FLAG = 1;

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        Map<Integer, String> map = new HashMap<>();
        int row = 0;
        for (int i = 0; i < s.length(); i++) {
            row = getNextRowNum(row, numRows);
            String str = map.get(row) == null ? "" : map.get(row);

            str += s.charAt(i);
            map.put(row, str);
        }

        StringBuilder result = new StringBuilder();
        for (String value : map.values()) {
            result.append(value);
        }

        return result.toString();
    }

    private int getNextRowNum(int row, int numRows) {
        if (row == numRows) {
            ROW_FLAG = -1;
        }
        if (row == 1) {
            ROW_FLAG = 1;
        }
        return row + ROW_FLAG;
    }
}
