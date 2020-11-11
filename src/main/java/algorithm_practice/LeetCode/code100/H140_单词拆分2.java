package algorithm_practice.LeetCode.code100;

import com.google.common.collect.Lists;
import common.util.StringUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/*
给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。

说明：

分隔时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。
示例 1：

输入:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
输出:
[
  "cats and dog",
  "cat sand dog"
]
示例 2：

输入:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
输出:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
解释: 注意你可以重复使用字典中的单词。
示例 3：

输入:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
输出:
[]


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-break-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/9
 */
public class H140_单词拆分2 {

    @Test
    public void testCase() {
        String s = "catsanddog";
        List<String> wordDict = Lists.newArrayList("cat", "cats", "and", "sand", "dog");
        List<String> excepted = Lists.newArrayList("cat sand dog", "cats and dog");
        Assert.assertEquals(excepted, wordBreak(s, wordDict));

        s = "pineapplepenapple";
        wordDict = Lists.newArrayList("apple", "pen", "applepen", "pine", "pineapple");
        excepted = Lists.newArrayList("pine apple pen apple", "pine applepen apple", "pineapple pen apple");
        Assert.assertEquals(excepted, wordBreak(s, wordDict));

        s = "catsandog";
        wordDict = Lists.newArrayList("cats", "dog", "sand", "and", "cat");
        excepted = Lists.newArrayList();
        Assert.assertEquals(excepted, wordBreak(s, wordDict));

        s = "aaaaaaa";
        wordDict = Lists.newArrayList("aaaa","aaa");
        excepted = Lists.newArrayList("aaa aaaa", "aaaa aaa");
        Assert.assertEquals(excepted, wordBreak(s, wordDict));

        s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        wordDict = Lists.newArrayList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        excepted = Lists.newArrayList();
        Assert.assertEquals(excepted, wordBreak(s, wordDict));

    }

    /**
     * 上一题的动态规划 + 回溯
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);

        // dp[i]: s[0...i] 能否拆分成句子
        int length = s.length();
        boolean[] dp = new boolean[length+1];
        dp[0] = true;

        for (int i = 1; i <= length; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (wordSet.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        dp[0] = wordSet.contains(s.substring(0,1));

        List<String> res = new ArrayList<>();
        if (!dp[length]) {
            return res;
        }

        Deque<String> path = new LinkedList<>();
        dfs(res, path, wordSet, s, 0, dp);

        return res;
    }

    private void dfs(List<String> res, Deque<String> path, Set<String> wordSet, String s, int beginIndex, boolean[] dp) {
        if (beginIndex == s.length()) {
            res.add(String.join(" ", path));
            return;
        }

        for (int i = beginIndex+1; i <= s.length(); i++) {
            if (!wordSet.contains(s.substring(beginIndex, i)) || !dp[i]) {
                continue;
            }

            path.addLast(s.substring(beginIndex, i));
            dfs(res, path, wordSet, s, i, dp);
            path.removeLast();
        }
    }

    /**
     * 一个普通的回溯，全是 a 的那个case超时。。
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if (StringUtil.isBlank(s)) {
            return result;
        }

        Map<Character, List<String>> map = new HashMap<>();
        for(String str: wordDict) {
            char c = str.charAt(0);
            if (map.containsKey(c)) {
                List<String> strings = map.get(c);
                strings.add(str);
                map.put(c, strings);
            } else {
                ArrayList<String> value = new ArrayList<>();
                value.add(str);
                map.put(c, value);
            }
        }

        StringBuilder sb = new StringBuilder();
        backTrack(result, sb, s, 0, map);

        return result;
    }

    private void backTrack(List<String> result, StringBuilder sb, String s, int i, Map<Character, List<String>> map) {
        if (i == s.length()) {
            String e = sb.toString();
            result.add(e.substring(0, e.length()-1));
        }

        if (i >= s.length()) {
            return;
        }

        char c = s.charAt(i);
        List<String> strings = map.get(c);
        if (strings == null || strings.size() == 0) {
            return;
        }

        String temp;
        for(String str: strings) {
            if (i+str.length() > s.length() || !str.equals(s.substring(i, i+str.length()))) {
                continue;
            }

            temp = sb.toString();
            sb.append(str + " ");

            backTrack(result, sb, s, i+str.length(), map);

            sb = new StringBuilder(temp);
        }

    }
}
