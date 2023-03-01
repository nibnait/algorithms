package cc.tianbin.algorithmzuo.b_体系学习班.c0700_动态规划;

import cc.tianbin.common.util.SysOut;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nibnait on 2022/11/12
 */
public class Code05_StickersToSpellWord {

    @Test
    public void test() {
        String[] stickers = new String[]{"ba", "c", "abcd"};
        String target = "babac";

        int ans1 = minStickers1(stickers, target);
        int ans2 = minStickers2(stickers, target);
        int ans3 = minStickers3(stickers, target);

        if (ans1 != ans2 || ans2 != ans3) {
            SysOut.printf("ans1: {}, ans2: {}, ans3: {}", ans1, ans2, ans3);
        }
    }

    /**
     * https://leetcode.com/problems/stickers-to-spell-word
     * 暴力递归
     */
    public int minStickers1(String[] stickers, String target) {
        if (stickers == null || stickers.length == 0) {
            return -1;
        }

        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int process1(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String rest = minus1(target, sticker);
            if (rest.length() != target.length()) {
                min = Math.min(min, process1(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    private String minus1(String target, String sticker) {
        int[] targetCounts = new int[26];
        for (char c : target.toCharArray()) {
            targetCounts[c - 'a']++;
        }

        for (char c : sticker.toCharArray()) {
            if (targetCounts[c - 'a'] > 0) {
                targetCounts[c - 'a']--;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < targetCounts.length; i++) {
            if (targetCounts[i] > 0) {
                for (int j = 0; j < targetCounts[i]; j++) {
                    builder.append((char) ('a' + i));
                }
            }
        }
        return builder.toString();
    }

    /**
     * 词频统计 + 剪枝
     */
    public int minStickers2(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }

        int[][] sCounts = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            for (char c : stickers[i].toCharArray()) {
                sCounts[i][c - 'a']++;
            }
        }

        int min = process2(sCounts, target);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int process2(int[][] stickers, String targetStr) {
        if (targetStr.length() == 0) {
            return 0;
        }

        int[] target = new int[26];
        char[] targetChar = targetStr.toCharArray();
        for (char c : targetChar) {
            target[c - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            // 每次只尝试含有 targetChar[0] 字符的贴纸。
            if (sticker[targetChar[0] - 'a'] > 0) {
                String rest = minus2(target, sticker);
                min = Math.min(min, process2(stickers, rest));
            }
        }

        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    private String minus2(int[] target, int[] sticker) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            int nums = target[i] - sticker[i];
            for (int j = 0; j < nums; j++) {
                builder.append((char) ('a' + i));
            }
        }
        return builder.toString();
    }

    /**
     * 此题无法转成dp严格依赖数组
     * 只能加个 dp数组 缓存优化
     */
    private int minStickers3(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }

        int[][] sCounts = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            for (char c : stickers[i].toCharArray()) {
                sCounts[i][c - 'a']++;
            }
        }

        Map<String, Integer> dp = new HashMap<>();
        dp.put("", 0);

        int min = process3(sCounts, target, dp);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int process3(int[][] stickers, String targetStr, Map<String, Integer> dp) {
        if (dp.containsKey(targetStr)) {
            return dp.get(targetStr);
        }

        int[] target = new int[26];
        char[] targetChar = targetStr.toCharArray();
        for (char c : targetChar) {
            target[c - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            // 每次只尝试含有 targetChar[0] 字符的贴纸。
            if (sticker[targetChar[0] - 'a'] > 0) {
                String rest = minus2(target, sticker);
                min = Math.min(min, process3(stickers, rest, dp));
            }
        }

        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(targetStr, ans);
        return ans;
    }

}
