package algorithm_practice.LeetCode.code100;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

说明：

拆分时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。
示例 1：

输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
示例 2：

输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     注意你可以重复使用字典中的单词。
示例 3：

输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-break
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/9
 */
public class M139_单词拆分 {

    @Test
    public void testCase() {
        String s = "catsanddog";
        List<String> wordDict = Lists.newArrayList("cat", "cats", "and", "sand", "dog");
        boolean excepted = true;
        Assert.assertEquals(excepted, wordBreak(s, wordDict));

        s = "pineapplepenapple";
        wordDict = Lists.newArrayList("apple", "pen", "applepen", "pine", "pineapple");
        excepted = true;
        Assert.assertEquals(excepted, wordBreak(s, wordDict));

        s = "catsandog";
        wordDict = Lists.newArrayList("cats", "dog", "sand", "and", "cat");
        excepted = false;
        Assert.assertEquals(excepted, wordBreak(s, wordDict));

        s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        wordDict = Lists.newArrayList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        excepted = false;
        Assert.assertEquals(excepted, wordBreak(s, wordDict));

    }


    public boolean wordBreak(String s, List<String> wordDict) {
        int length = s.length();

        // dp[i]: s[0...i] 能否拆分成句子
        boolean[] dp = new boolean[length+1];
        Set<String> wordSet = new HashSet<>(wordDict);
        dp[0] = true;

        for (int i = 1; i <= length; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (wordSet.contains(s.substring(j,i)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[length];
    }
}
