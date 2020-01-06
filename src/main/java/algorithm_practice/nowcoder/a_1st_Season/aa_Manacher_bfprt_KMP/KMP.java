package algorithm_practice.nowcoder.a_1st_Season.aa_Manacher_bfprt_KMP;

/**
 * 字符串匹配问题
 *     如果str1中包含str2，则返回str2在str1中开始的位置，否则返回-1
 * Created by nibnait on 2016/9/18.
 */
public class KMP {

    public static void main(String[] args) {
        String str1 = "abcabcababaccc";
        String str2 = "ababa";
        System.out.println(getIndexOf(str1, str2));
    }

    private static int getIndexOf(String str1, String str2) {
        if (str1 == null || str2 == null || str2.length() < 1 || str1.length() < str2.length()) {
            return -1;
        }
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int ps1 = 0;    //遍历str1用的指针
        int ps2 = 0;    //遍历str2用的指针
        int[] nextArr = getNextArray(s2);
        while (ps1 < s1.length && ps2 < s2.length) {
            if (s1[ps1] == s2[ps2]) {
                ps1++;
                ps2++;
            } else if (nextArr[ps2] == -1) {
                ps1++;
            } else {
                ps2 = nextArr[ps2];
            }

        }
        return ps2 == s2.length ? ps1 - ps2 : -1;
    }

    private static int[] getNextArray(char[] s2) {
        if (s2.length == 1) {
            return new int[]{-1};
        }
        int[] nextArr = new int[s2.length];
        nextArr[0] = -1;
        nextArr[1] = 0;
        int pos = 2;    //当前位置
        int cn = 0;     //最长匹配前缀的下一个字符的位置
        while (pos < s2.length) {
            if (s2[pos - 1] == s2[cn]) {
                nextArr[pos++] = nextArr[pos - 1] + 1;
                cn++;
            } else if (cn > 0) {
                cn = nextArr[cn];
            } else {
                nextArr[pos++] = 0;
            }
        }
        return nextArr;
    }
}
