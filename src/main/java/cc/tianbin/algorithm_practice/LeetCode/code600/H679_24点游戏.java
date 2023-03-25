package cc.tianbin.algorithm_practice.LeetCode.code600;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个长度为4的整数数组 cards 。你有 4 张卡片，每张卡片上都包含一个范围在 [1,9] 的数字。您应该使用运算符 ['+', '-', '*', '/'] 和括号 '(' 和 ')' 将这些卡片上的数字排列成数学表达式，以获得值24。
 * <p>
 * 你须遵守以下规则:
 * <p>
 * 除法运算符 '/' 表示实数除法，而不是整数除法。
 * 例如， 4 /(1 - 2 / 3)= 4 /(1 / 3)= 12 。
 * 每个运算都在两个数字之间。特别是，不能使用 “-” 作为一元运算符。
 * 例如，如果 cards =[1,1,1,1] ，则表达式 “-1 -1 -1 -1” 是 不允许 的。
 * 你不能把数字串在一起
 * 例如，如果 cards =[1,2,1,2] ，则表达式 “12 + 12” 无效。
 * 如果可以得到这样的表达式，其计算结果为 24 ，则返回 true ，否则返回 false 。
 * <p>
 * <p>
 * 示例 1:
 * 输入: cards = [4, 1, 8, 7]
 * 输出: true
 * 解释: (8-4) * (7-1) = 24
 * <p>
 * 示例 2:
 * 输入: cards = [1, 2, 1, 2]
 * 输出: false
 * <p>
 * <p>
 * 提示:
 * cards.length == 4
 * 1 <= cards[i] <= 9
 * <p>
 * https://leetcode.cn/problems/24-game/description/
 * Created by nibnait on 2023/03/25
 */
public class H679_24点游戏 {

    @Test
    public void test() {
        int[] cards = new int[]{4, 1, 8, 7};
        boolean result = judgePoint24(cards);
        Assert.assertEquals(true, result);

        cards = new int[]{1, 2, 1, 2};
        result = judgePoint24(cards);
        Assert.assertEquals(false, result);

        // 8 / ( 3 - (8/3) )
        cards = new int[]{3, 3, 8, 8};
        result = judgePoint24(cards);
        Assert.assertEquals(true, result);
    }

    public boolean judgePoint24(int[] cards) {
        List<Double> list = new ArrayList(4);

        for (int num : cards) {
            list.add((double) num);
        }

        return process(list);
    }

    private boolean process(List<Double> list) {
        int size = list.size();
        if (size == 1) {
            return Math.abs(list.get(0) - 24) <= 1e-6;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    continue;
                }

                List<Double> list2 = new ArrayList<>();

                for (int k = 0; k < size; k++) {
                    if (k != i && k != j) {
                        list2.add(list.get(k));
                    }
                }

                for (int k = 1; k <= 6; k++) {
                    list2.add(calc(list.get(i), list.get(j), k));

                    if (process(list2)) {
                        return true;
                    }

                    list2.remove(list2.size() - 1);
                }
            }
        }

        return false;
    }

    private double calc(double a, double b, int i) {
        switch (i) {
            case 1:
                return a + b;
            case 2:
                return a - b;
            case 3:
                return b - a;
            case 4:
                return a * b;
            case 5:
                if (b != 0) {
                    return a / b;
                } else {
                    return Integer.MIN_VALUE;
                }
            case 6:
                if (a != 0) {
                    return b / a;
                } else {
                    return Integer.MIN_VALUE;
                }
        }
        return Integer.MIN_VALUE;
    }
}
