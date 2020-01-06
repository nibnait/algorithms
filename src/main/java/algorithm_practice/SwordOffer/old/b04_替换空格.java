package algorithm_practice.SwordOffer.old;

/**
 * 自然想法，遇到空格就替换成'20%'，长度是n的字符串，遇到一个空格，就需要向后移动n个字符，所以时间复杂度为O（N^2)
 *
 * 【思路】
 * 双指针，从后往前遍历替换：
 *      p1：指向字符串末尾，
 *      p2：指向替换之后的字符串的末尾（需提前遍历一遍str，数一下一共有多少个空格）
 *  然后p1和p2一起向前跑，当p1遇到空格，则将' '变成'%20'，然后继续向前跑。
 *
 * Created by nibnait on 2016/9/20.
 */
public class b04_替换空格 {

    public static void main(String[] args) {
        String OriginalString = "We are happy";
        System.out.println(OriginalString);
        char[] str = new char[50];
        char[] strCharArr = OriginalString.toCharArray();
        for (int i = 0; i < strCharArr.length; i++) {
            str[i] = strCharArr[i];
        }
        System.out.println(replaceBlank(str, strCharArr.length));
    }

    /**
     * @param str   需要替换的字符串数组
     * @param size  str中OriginalString的长度
     * @return
     */
    private static String replaceBlank(char[] str, int size) {
        if (str==null || size<1){
            return null;
        }

        int cnt = 0;
        for (int i = 0; i < size; i++) {
            if (str[i] == ' '){
                cnt++;
            }
        }
        int p1 = size-1;
        int p2 = p1+(2*cnt);
        if (p2 > str.length){
            throw new RuntimeException("Invalid args: str有点小，不够装！");
        }
        while (p1 >= 0){
            if (str[p1] == ' '){
                str[p2--] = '0';
                str[p2--] = '2';
                str[p2--] = '%';
            } else {
                str[p2--] = str[p1];
            }
            p1--;
        }

        return new String(str);
    }

}
