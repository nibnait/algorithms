package algorithm_practice.SwordOffer.old;

/**
 * 题目：实现一个函数 stringToInt，实现把字符串转换成整数这个功能，不能使用 atoi 或者其他类似的库函数。
 *
 * 【解】：
 *   原理很简单，就是从前往后遍历，前一个数×10，加上当前数。
 *
 *   但要注意特殊输入：
 *    - 空指针null
 *    - 空字符串""
 *    - 正负号
 *    - 溢出
 *    - 非数字字符
 *
 * Created by nibnait on 2016/10/2.
 */
public class g49_把字符串转换成整数 {

    public static void main(String[] args) {
        System.out.println(Integer.parseInt(Integer.MIN_VALUE + ""));
        System.out.println(0x8000_0000L);
//        System.out.println(stringToInt(""));
        System.out.println(stringToInt("123"));
        System.out.println(stringToInt("+123"));
        System.out.println(stringToInt("-123"));
//        System.out.println(stringToInt("1a123"));
        System.out.println(stringToInt("+2147483647"));
        System.out.println(stringToInt("-2147483647"));
//        System.out.println(stringToInt("+2147483648"));
        System.out.println(stringToInt("-2147483648"));
//        System.out.println(stringToInt("+2147483649"));
//        System.out.println(stringToInt("-2147483649"));
//        System.out.println(stringToInt("+"));
//        System.out.println(stringToInt("-"));
    }

    private static int stringToInt(String str){

        if (str==null || str.length()<=0){
            throw new IllegalArgumentException("args should not be null or empty");
        }
        char[] chars = str.toCharArray();
        if (chars[0] == '+'){
            return parseString(chars, 1, true);
        } else if (chars[0]>='0' && chars[0]<='9'){
            return parseString(chars, 0, true);
        } else if (chars[0] == '-'){
            return parseString(chars, 1, false);
        } else {
            throw new IllegalArgumentException("Invalid args");
        }
    }

    /**
     * 开始解析StringToInt
     * @param chars
     * @param begin 开始的位置
     * @param isPositive    是正数
     * @return
     */
    private static int parseString(char[] chars, int begin, boolean isPositive) {
        if (begin >= chars.length){
            throw new IllegalArgumentException("Invalid args");
        }
        int result = isPositive? 1: -1;
        long tmp = 0;

        while (begin<chars.length){
            if (!isDigit(chars[begin])){
                throw new IllegalArgumentException("Invalid args");
            }
            tmp = tmp*10 + chars[begin]-'0';
            if (tmp > 0x80000000L || (isPositive && tmp==0x80000000L)){
                throw new RuntimeException("额(⊙﹏⊙)，溢出了！！！");
            }
            begin++;
        }

        return (int) (result*tmp);
    }

    private static boolean isDigit(char x){
        return x>='0' && x<='9';
    }

}
