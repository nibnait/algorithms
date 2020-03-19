package algorithm_practice.LeetCode.code1100;

import junit.framework.TestCase;
import org.junit.Test;

/*
给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。

假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。

注意：每次拼写时，chars 中的每个字母都只能用一次。

返回词汇表 words 中你掌握的所有单词的 长度之和。

 

示例 1：

输入：words = ["cat","bt","hat","tree"], chars = "atach"
输出：6
解释：
可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
示例 2：

输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
输出：10
解释：
可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
 

提示：

1 <= words.length <= 1000
1 <= words[i].length, chars.length <= 100
所有字符串中都仅包含小写英文字母


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
Created by nibnait on 2020-03-17
 */
public class E1160_拼写单词 extends TestCase {

    @Test
    public void testCase() {
        String[] words = new String[]{"cat", "bt", "hat", "tree"};
        String chars = "atach";
        System.out.println(countCharacters(words, chars));

        String[] words1 = new String[]{"hello", "world", "leetcode"};
        String chars1 = "welldonehoneyr";
        System.out.println(countCharacters(words1, chars1));

        String[] words2 = new String[]{"dyiclysmffuhibgfvapygkorkqllqlvokosagyelotobicwcmebnpznjbirzrzsrtzjxhsfpiwyfhzyonmuabtlwin","ndqeyhhcquplmznwslewjzuyfgklssvkqxmqjpwhrshycmvrb","ulrrbpspyudncdlbkxkrqpivfftrggemkpyjl","boygirdlggnh","xmqohbyqwagkjzpyawsydmdaattthmuvjbzwpyopyafphx","nulvimegcsiwvhwuiyednoxpugfeimnnyeoczuzxgxbqjvegcxeqnjbwnbvowastqhojepisusvsidhqmszbrnynkyop","hiefuovybkpgzygprmndrkyspoiyapdwkxebgsmodhzpx","juldqdzeskpffaoqcyyxiqqowsalqumddcufhouhrskozhlmobiwzxnhdkidr","lnnvsdcrvzfmrvurucrzlfyigcycffpiuoo","oxgaskztzroxuntiwlfyufddl","tfspedteabxatkaypitjfkhkkigdwdkctqbczcugripkgcyfezpuklfqfcsccboarbfbjfrkxp","qnagrpfzlyrouolqquytwnwnsqnmuzphne","eeilfdaookieawrrbvtnqfzcricvhpiv","sisvsjzyrbdsjcwwygdnxcjhzhsxhpceqz","yhouqhjevqxtecomahbwoptzlkyvjexhzcbccusbjjdgcfzlkoqwiwue","hwxxighzvceaplsycajkhynkhzkwkouszwaiuzqcleyflqrxgjsvlegvupzqijbornbfwpefhxekgpuvgiyeudhncv","cpwcjwgbcquirnsazumgjjcltitmeyfaudbnbqhflvecjsupjmgwfbjo","teyygdmmyadppuopvqdodaczob","qaeowuwqsqffvibrtxnjnzvzuuonrkwpysyxvkijemmpdmtnqxwekbpfzs","qqxpxpmemkldghbmbyxpkwgkaykaerhmwwjonrhcsubchs"};
        String chars2 = "usdruypficfbpfbivlrhutcgvyjenlxzeovdyjtgvvfdjzcmikjraspdfp";
        System.out.println(countCharacters(words2, chars2));

    }

    /**
     * chars 中的每个字母不限使用次数
     */
    public int countCharacters(String[] words, String chars) {
        int result = 0;
        int[] charMap = new int[26];

        for (int i = 0; i < chars.length(); i++) {
            charMap[chars.charAt(i) - 'a']++;
        }

        for (int i = 0; i < words.length; i++) {
            int[] wordMap = new int[26];
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                wordMap[word.charAt(j) - 'a'] ++;
            }

            int j = 0;
            for (; j < word.length(); j++) {
                int wordCOunt = wordMap[word.charAt(j) - 'a'];
                int charCount = charMap[word.charAt(j) - 'a'];
                if (wordCOunt > charCount) {
                    break;
                }
            }
            if (j == word.length()) {
                result += word.length();
            }
        }

        return result;
    }

    /**
     * chars 中的每个字母都只能用一次！
     */
    public int countCharacters2(String[] words, String chars) {
        int result = 0;
        int[] charMap = new int[26];

        for (int i = 0; i < chars.length(); i++) {
            charMap[chars.charAt(i) - 'a']++;
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int j = 0;
            for (; j < word.length(); j++) {
                if (charMap[word.charAt(j) - 'a'] == 0) {
                    break;
                }
            }

            if (j == word.length()) {
                for (int k = 0; k < word.length(); k++) {
                    charMap[word.charAt(k) - 'a']--;
                }

                result += word.length();
            }
        }

        return result;
    }
}
