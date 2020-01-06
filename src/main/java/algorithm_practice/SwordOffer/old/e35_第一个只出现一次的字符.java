package algorithm_practice.SwordOffer.old;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：在字符串中找出第一个只出现一次的字符。
 * 例如输入"abaccdeff"，则输出'b'。
 *
 * 【解】：
 *     用HashMap统计每个字符出现的次数
 *     然后再扫描一次字符数组，当此字符出现次数为1时，直接return
 *     时间复杂度：2 * O(N)
 *
 * Created by nibnait on 2016/10/1.
 */
public class e35_第一个只出现一次的字符 {

    public static void main(String[] args) {
        System.out.println(firstNotRepeatingChar("google")); // l
        System.out.println(firstNotRepeatingChar("aabccdbd")); // '\0'
        System.out.println(firstNotRepeatingChar("abcdefg")); // a
        System.out.println(firstNotRepeatingChar("gfedcba")); // g
        System.out.println(firstNotRepeatingChar("zgfedcba")); // g
    }

    private static char firstNotRepeatingChar(String str) {
        if (str==null || str.length()<=0){
            throw new IllegalArgumentException("Arg should not be null or empty");
        }
        char[] chars = str.toCharArray();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int value = 0;
        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == null){
                map.put(chars[i], 1);
            } else {
                value = map.get(chars[i]);
                value++;
                map.put(chars[i], value);
            }
        }

        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1){
                return chars[i];
            }
        }
        return '\0';
    }
}
