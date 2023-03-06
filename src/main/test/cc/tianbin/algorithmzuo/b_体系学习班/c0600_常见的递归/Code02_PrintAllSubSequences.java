package cc.tianbin.algorithmzuo.b_体系学习班.c0600_常见的递归;

import com.google.common.collect.Lists;
import cc.tianbin.common.util.SysOut;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by nibnait on 2022/11/08
 */
public class Code02_PrintAllSubSequences {

    @Test
    public void test() {
        String test = "acccc";
        List<String> ans1 = subs(test);
        SysOut.printListWrap(ans1);
        SysOut.printSeparator();

        List<String> ans2 = subsNoRepeat(test);
        SysOut.printListWrap(ans2);
    }


    /**
     * 全部子序列
     */
    public List<String> subs(String s) {
        char[] str = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        processSubs(str, ans, 0, path);
        return ans;
    }

    /**
     *
     * @param str 固定的入参字符串
     * @param ans 把所有生成的子序列，放到 ans 中
     * @param index 当前来到 index 位置
     * @param path 已经确定的生成的字符串
     */
    private void processSubs(char[] str, List<String> ans, int index, String path) {
        if (index == str.length) {
            ans.add(path);
            return;
        }

        // 要当前位置的字符串
        processSubs(str, ans, index + 1, path + str[index]);
        // 不要当前位置的字符串
        processSubs(str, ans, index + 1, path);
    }

    /**
     * 全部无重复值的子序列
     */
    public List<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        String path = "";
        Set<String> ans = new HashSet<>();
        processSubsNoRepeat(str, ans, 0, path);
        return Lists.newArrayList(ans);
    }

    private void processSubsNoRepeat(char[] str, Set<String> ans, int index, String path) {
        if (index == str.length) {
            ans.add(path);
            return;
        }

        processSubsNoRepeat(str, ans, index + 1, path);
        processSubsNoRepeat(str, ans, index + 1, path + str[index]);
    }

}
