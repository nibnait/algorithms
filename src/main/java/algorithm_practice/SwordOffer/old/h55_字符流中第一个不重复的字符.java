package algorithm_practice.SwordOffer.old;

import common.util.StringUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 题目：请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符“go”时，第一个只出现一次的字符是‘g’。
 *      当从该字符流中读出前六个字符“google”时，第一个只出现 1 次的字符是”l”。
 *
 * 【自然想法】：
 *      用LinkedHashMap，
 *          以这个字符做为key
 *          当这个字符第一次出现时，将其value置为1，
 *          当这个字符出现了第二次的时候，直接将其value设为-1，
 *      最后遍历map.values()，找到第一个是1的那个Entry，返回key √
 *
 * 【书上的做法】：new了一个 CharStatistics
 *      里面有一个occurrence[256]这么大的数组：相当于HashMap中的key（以字符的ASCII码为index）
 *                                   书上是以该字符在字符串中的位置作为其 val。
 *      然后先将occurrence[]中的所有元素置为-1（代表从未出现过）
 *      然后开始遍历字符串，index = 0
 *              第一次出现-->occurrence[ch] = index++;
 *              第二次出现(occurrence[ch]!=-1)   -->occurrence[ch] = -2;
 *      最后遍历occurrence[]数组，
 *          找到第一个occurrence[i] != -1的字符，返回。
 *
 *
 * Created by nibnait on 2016/10/3.
 */
public class h55_字符流中第一个不重复的字符 {


    private static char firstAppearingOnce(String str) {
        if (StringUtil.isBlank(str)){
            return '\0';
        }
        HashMap<Character, Integer> map = new LinkedHashMap<>();
        char[] chars = str.toCharArray();
        for(char ch : chars){
            if (map.get(ch) == null){
                map.put(ch, 1);
            } else {
                map.put(ch, -1);
            }
        }

        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            if (entry.getValue() == 1){
                return entry.getKey();
            }
        }
        return '\0';
    }

    /**
     * 书上的做法：（有点高端。）
     */
    private static class CharStatistics {
        // 出现一次的标识
        private int index = 0;
        private int[] occurrence = new int[256];
        public CharStatistics() {
            for (int i = 0; i < occurrence.length; i++) {
                occurrence[i] = -1;
            }
        }
        private void insert(char ch) {
            if (ch > 255) {
                throw new IllegalArgumentException( ch + "must be a ASCII char");
            }
            // 只出现一次
            if (occurrence[ch] == -1) {
                occurrence[ch] = index;
            } else {
                // 出现了两次
                occurrence[ch] = -2;
            }
            index++;
        }
        public char firstAppearingOnce(String data) {
            if (data == null) {
                throw new IllegalArgumentException(data);
            }
            for (int i = 0; i < data.length(); i++) {
                insert(data.charAt(i));
            }
            char ch = '\0';
            // 用于记录最小的索引，对应的就是第一个不重复的数字
            int minIndex = Integer.MAX_VALUE;
            for (int i = 0; i < occurrence.length; i++) {
                if (occurrence[i] >= 0 && occurrence[i] < minIndex) {
                    ch = (char) i;
                    minIndex = occurrence[i];   //不知为何要多此一举？？
                }
            }
            return ch;
        }
    }

    public static void main(String[] args) {
        System.out.println(firstAppearingOnce("")); // '\0'
        System.out.println(firstAppearingOnce("g")); // 'g'
        System.out.println(firstAppearingOnce("go")); // 'g'
        System.out.println(firstAppearingOnce("goo")); // 'g'
        System.out.println(firstAppearingOnce("goog")); // '\0'
        System.out.println(firstAppearingOnce("googl")); // l
        System.out.println(firstAppearingOnce("google")); // l

        System.out.println();

        System.out.println(new CharStatistics().firstAppearingOnce("")); // '\0'
        System.out.println(new CharStatistics().firstAppearingOnce("g")); // 'g'
        System.out.println(new CharStatistics().firstAppearingOnce("go")); // 'g'
        System.out.println(new CharStatistics().firstAppearingOnce("goo")); // 'g'
        System.out.println(new CharStatistics().firstAppearingOnce("goog")); // '\0'
        System.out.println(new CharStatistics().firstAppearingOnce("googl")); // l
        System.out.println(new CharStatistics().firstAppearingOnce("google")); // l
    }

}
