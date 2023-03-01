package cc.tianbin.algorithm_practice.LeetCode.code100;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/*
给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。
说明:

如果不存在这样的转换序列，返回 0。
所有单词具有相同的长度。
所有单词只由小写字母组成。
字典中不存在重复的单词。
你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
示例 1:

输入:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

输出: 5

解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     返回它的长度 5。
示例 2:

输入:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

输出: 0

解释: endWord "cog" 不在字典中，所以无法进行转换。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-ladder
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 Created by nibnait on 2020/11/5
 */
public class M127_单词接龙 {

    @Test
    public void testCase() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Lists.newArrayList("hot","dot","dog","lot","log","cog");
        int excepted = 5;
        Assert.assertEquals(excepted, ladderLength(beginWord, endWord, wordList));

        beginWord = "hit";
        endWord = "cog";
        wordList = Lists.newArrayList("hot","dot","dog","lot","log");
        excepted = 0;
        Assert.assertEquals(excepted, ladderLength(beginWord, endWord, wordList));

    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visitedSet = new HashSet<>(wordSet.size());
        visitedSet.add(beginWord);

        int step = 1;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                String currentWord = queue.poll();

                if (modifyOneLetterCanBeEndWord(currentWord, endWord,
                        queue, wordSet, visitedSet)) {
                    return step + 1;
                }
            }

            step++;
        }

        return 0;
    }

    /**
     * 修改 currentWord 任意一个字母，可以变成 endWord
     */
    private boolean modifyOneLetterCanBeEndWord(String currentWord, String endWord,
                                                Queue<String> queue, Set<String> wordSet, Set<String> visitedSet) {
        char[] currentWordArray = currentWord.toCharArray();

        for (int i = 0; i < currentWord.length(); i++) {
            char originChar = currentWordArray[i];

            for (char c = 'a'; c <= 'z'; c++) {
                if (c == originChar) {
                    continue;
                }

                currentWordArray[i] = c;
                String nextWord = String.valueOf(currentWordArray);
                if (wordSet.contains(nextWord)) {
                    if (nextWord.equals(endWord)) {
                        return true;
                    }
                    if (!visitedSet.contains(nextWord)) {
                        queue.add(nextWord);
                        visitedSet.add(nextWord);
                    }
                }
            }

            currentWordArray[i] = originChar;
        }

        return false;
    }
}
