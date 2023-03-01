package cc.tianbin.algorithmzuo.b_体系学习班.c0600_常见的递归;

import com.google.common.collect.Lists;
import cc.tianbin.common.util.SwapUtil;
import cc.tianbin.common.util.SysOut;
import org.junit.Test;

import java.util.List;

/**
 * Created by nibnait on 2022/11/08
 */
public class Code03_PrintAllPermutations {

    @Test
    public void test() {
        String s = "acc";
        List<String> ans1 = permutation1(s);
        SysOut.printListWrap(ans1);
        System.out.println("=======");

        List<String> ans2 = permutation2(s);
        SysOut.printListWrap(ans2);
        System.out.println("=======");

    }

    /**
     * 打印一个字符串的全部排列
     */
    public List<String> permutation1(String s) {
        List<String> allPermutation = Lists.newArrayList();
        if (s == null || s.length() == 0) {
            return allPermutation;
        }

        char[] str = s.toCharArray();
        process1(str, 0, allPermutation);
        return allPermutation;
    }

    /**
     *
     * @param str
     * @param index
     * @param allPermutation
     * @return
     */
    private void process1(char[] str, int index, List<String> allPermutation) {
        if (index == str.length) {
            allPermutation.add(String.valueOf(str));
        } else {
            for (int i = index; i < str.length; i++) {
                SwapUtil.swap(str, index, i);
                process1(str, index + 1, allPermutation);
                SwapUtil.swap(str, index, i);
            }
        }
    }

    /**
     * 全排列，不重复
     */
    public List<String> permutation2(String s) {
        List<String> allPermutation = Lists.newArrayList();
        if (s == null || s.length() == 0) {
            return allPermutation;
        }

        char[] str = s.toCharArray();
        process2(str, 0, allPermutation);
        return allPermutation;
    }

    /**
     *
     * @param str
     * @param index
     * @param allPermutation
     * @return
     */
    private void process2(char[] str, int index, List<String> allPermutation) {
        if (index == str.length) {
            allPermutation.add(String.valueOf(str));
        } else {
            boolean[] visited = new boolean[256];
            for (int i = index; i < str.length; i++) {
                if (visited[str[i]]) {
                    continue;
                }
                visited[str[i]] = true;
                // str[i] 这个字符，在出现在了第 x 位置时的所有可能性：
                SwapUtil.swap(str, index, i);
                process2(str, index + 1, allPermutation);
                SwapUtil.swap(str, index, i);
            }
        }
    }

}
