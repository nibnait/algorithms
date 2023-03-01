package algorithm_practice.LeetCode.code000;

import com.google.common.collect.Lists;
import common.util.AssertUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nibnait on 2023/01/19
 */
public class M017_电话号码的字母组合 {

    @Test
    public void test() {
        String digits = "23";
        List<String> result = letterCombinations(digits);
        List<String> expect = Lists.newArrayList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
        AssertUtils.compareStringList(expect, result);
    }


    private static final char[][] phone = new char[][] {
            {'.'},
            {'.'},
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList();
        }

        char[] str = digits.toCharArray();
        List<String> ans = new ArrayList<>();
        process(ans, str, 0, "");
        return ans;
    }

    private void process(List<String> ans, char[] str, int index, String path) {
        if (index == str.length) {
            ans.add(path);
            return;
        }

        for (char c : phone[str[index] - '0']) {
            process(ans, str, index + 1, path + c);
        }
    }

}
